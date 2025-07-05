/********************************************************************************
 * Copyright (c) 2011 Xored Software Inc and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Xored Software Inc - initial API and implementation
 ********************************************************************************/
package org.eclipse.rcptt.cloud.server;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class ExecutionRegistry {
	private static ExecutionRegistry instance = null;
	private Map<String, ExecutionEntry> runningSuites = new HashMap<String, ExecutionEntry>();
	private int keepSessions;
	private int keepAUTArtifacts;

	private Map<ISMHandle<SuiteStats>, ISMHandleStore<Execution>> executions = new HashMap<ISMHandle<SuiteStats>, ISMHandleStore<Execution>>();

	public static synchronized ExecutionRegistry getInstance() {
		if (instance == null) {
			instance = new ExecutionRegistry();
		}
		return instance;
	}

	public synchronized ExecutionEntry beginNewSuite(String suiteId,
			String clientId, String clientSecret, String organization,
			String licenseUrl) throws CoreException {

		Preconditions.checkNotNull(suiteId);
		ISMHandleStore<SuiteStats> store = ISMCore.getInstance()
				.getSuiteStore();
		ISMHandle<SuiteStats> suite = store.getHandle(suiteId);
		final ISMHandleStore<Execution> executionStore = getExecutions(suite);

		ISMHandle<Execution> handle = suite
				.commit(new Function<SuiteStats, ISMHandle<Execution>>() {
					@Override
					public ISMHandle<Execution> apply(
							final SuiteStats suiteStats) {
						int last = suiteStats.getLastSuiteID();
						String newID = Integer.toString(last + 1);
						suiteStats.setLastSuiteID(last + 1);

						ISMHandle<Execution> execHandle = executionStore
								.getHandle(getID(newID, 7));
						execHandle.commit(new Function<Execution, Void>() {
							@Override
							public Void apply(Execution input) {
								if (input.getSuiteId() == null) {
									input.setSuiteId(suiteStats.getSuiteName());
								}
								input.setState(ExecutionState.PENDING);
								input.setId(execHandle.getFileName());
								return null;
							}
						});
						return execHandle;
					}
				});
		ServerPlugin.getDefault().getExecListener().created(handle);
		clearOutOldHandles(executionStore, handle);

		ExecutionEntry artifactSuite = ExecutionEntry.create(suiteId, handle);


		// Initialize test suite directory

		runningSuites.put(artifactSuite.getSuiteId(), artifactSuite);
		return artifactSuite;
	}

	private String getID(String newID, int size) {
		StringBuffer b = new StringBuffer();
		b.append(newID);
		while (b.length() < size) {
			b.insert(0, "0");
		}
		return b.toString();
	}

	public ISMHandleStore<Execution> getExecutions(ISMHandle<SuiteStats> suite) {
		ISMHandleStore<Execution> childs = executions.get(suite);
		if (childs != null) {
			return childs;
		}

		childs = new ISMHandleStore<Execution>(suite.getFileRoot(),
				StatsPackage.eINSTANCE.getExecution());
		executions.put(suite, childs);
		return childs;
	}

	private void clearOutOldHandles(ISMHandleStore<Execution> executionStore,
			ISMHandle<Execution> handle) {
		List<ISMHandle<Execution>> handles = executionStore.getHandles();

		List<ISMHandle<Execution>> filtered = getFilteredHandles(handles,
				handle);
		if (filtered.size() > keepSessions) {
			// keep only specified amount of builds
			int toRemove = filtered.size() - keepSessions + 1;
			for (int i = 0; i < toRemove; i++) {
				ISMHandle<Execution> ismHandle = filtered.remove(0);
				executionStore.remove(ismHandle);
				ismHandle.remove();
			}

		}
		if (filtered.size() > keepAUTArtifacts) {
			// Left artifacts only for specified builds
			int toProceed = 1 + filtered.size() - keepAUTArtifacts;
			for (int i = 0; i < toProceed; i++) {
				ISMHandle<Execution> ismHandle = filtered.get(i);
				clearAUTArtifacts(ismHandle);
			}
		}

	}

	private void clearAUTArtifacts(final ISMHandle<Execution> ismHandle) {
		ismHandle.commit(new Function<Execution, Void>() {
			@Override
			public Void apply(Execution exec) {
				EList<String> list = exec.getAutArtifacts();
				File root = ismHandle.getFileRoot();
				for (String file : list) {
					File child = new File(root, file);
					if (child.exists()) {
						FileUtil.deleteFile(child);
					}
				}
				exec.getAutArtifacts().clear();
				return null;
			}
		});
	}

	public static List<ISMHandle<Execution>> getFilteredHandles(
			List<ISMHandle<Execution>> handles, ISMHandle<Execution> handle) {
		List<ISMHandle<Execution>> result = new ArrayList<ISMHandle<Execution>>();
		result.addAll(handles);
		if (handle != null) {
			result.remove(handle);
		}

		Collections.sort(result, new Comparator<ISMHandle<Execution>>() {

			@Override
			public int compare(ISMHandle<Execution> o1, ISMHandle<Execution> o2) {
				long s1 = 0, s2 = 0;
				s1 = getTimestamp(o1);
				s2 = getTimestamp(o2);

				return Long.valueOf(s1).compareTo(s2);
			}

			private long getTimestamp(ISMHandle<Execution> o1) {
				try {
					String fileName = o1.getFileName();
					for (int i = 0; i < fileName.length(); i++) {
						if (fileName.charAt(i) == '0') {
							continue;
						} else {
							String idValue = fileName.substring(i);
							return Long.parseLong(idValue);
						}
					}
				} catch (NumberFormatException e) {
				}
				return 0;
			}
		});

		return result;
	}

	public synchronized ExecutionEntry getSuiteHandle(String suiteId) {
		return runningSuites.get(suiteId);
	}

	public synchronized void removeSuiteHandle(String suiteId) {
		runningSuites.remove(suiteId);
	}

	public File getRoot() {
		return ISMCore.getInstance().getSuiteStore().getBase();
	}

	public void initialize(int keepSessions, int keepAUTArtifacts) {
		this.keepSessions = keepSessions;
		this.keepAUTArtifacts = keepAUTArtifacts;
	}

	public File getTempDir() {
		return new File(ISMCore.getInstance().getSuiteStore().getBase()
				.getParent(), ".temp");
	}

	public URI makeRelativePath(File file) {
		return getRoot().toURI().relativize(file.toURI());
	}

}
