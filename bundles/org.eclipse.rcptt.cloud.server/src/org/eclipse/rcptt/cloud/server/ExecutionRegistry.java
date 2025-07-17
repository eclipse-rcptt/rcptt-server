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

import static java.lang.System.currentTimeMillis;
import static java.util.function.Predicate.not;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.util.FileUtil;

public class ExecutionRegistry {
	private static ExecutionRegistry instance = null;
	private final Map<String, ExecutionEntry> runningSuites = Collections.synchronizedMap(new HashMap<>());

	private final Map<ISMHandle<SuiteStats>, ISMHandleStore<Execution>> executions = Collections.synchronizedMap(new HashMap<>());
	private final ListenerList<Runnable> hooks = new ListenerList<>(); 
	
	public void addNewSuiteHook(Runnable runnable) {
		hooks.add(runnable);
	}

	public static synchronized ExecutionRegistry getInstance() {
		if (instance == null) {
			instance = new ExecutionRegistry();
		}
		return instance;
	}
	
	public synchronized ExecutionEntry beginNewSuite(ISMHandle<SuiteStats> suite) throws CoreException {
		hooks.forEach(Runnable::run);
		final ISMHandleStore<Execution> executionStore = getExecutions(suite);
		ISMHandle<Execution> handle = suite
				.commit(suiteStats -> {
					int last = suiteStats.getLastSuiteID();
					String newID = Integer.toString(last + 1);
					suiteStats.setLastSuiteID(last + 1);

					ISMHandle<Execution> execHandle = executionStore
							.getHandle(getID(newID, 7));
					execHandle.commit(input -> {
						if (input.getSuiteId() == null) {
							input.setSuiteId(suiteStats.getSuiteName());
						}
						input.setStartTime(System.currentTimeMillis());
						input.setState(ExecutionState.PENDING);
						input.setId(execHandle.getFileName());
						return null;
					});
					return execHandle;
				});
		ServerPlugin.getDefault().getExecListener().created(handle);

		ExecutionEntry artifactSuite = ExecutionEntry.create(suite.getFileName(), handle);


		// Initialize test suite directory

		Object oldValue = runningSuites.put(artifactSuite.getSuiteId(), artifactSuite);
		assert oldValue == null : "Suite " + artifactSuite.getSuiteId() + " was scheduled to run twice"; 
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
		return executions.computeIfAbsent(suite, s ->
			new ISMHandleStore<Execution>(s.getFileRoot(), StatsPackage.eINSTANCE.getExecution())
		);
	}

	private static void clearAUTArtifacts(final ISMHandle<Execution> ismHandle) {
		ismHandle.commit(exec -> {
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
		);
	}

	public void removeOldExecutions(Collection<ISMHandle<SuiteStats>> suites, int maxArtifacts, int maxExecutions) {
		ExecutionRegistry executions = ExecutionRegistry.getInstance();
		List<ExecutionStart> allExecutions = suites.stream().<ExecutionStart>flatMap(s -> executions.getExecutions(s).getHandles().stream().map(e -> ExecutionStart.create(e, s)) ).sorted().collect(Collectors.toCollection(ArrayList::new));
		int deleteCount = allExecutions.size() - maxExecutions; 
		if (deleteCount > 0) {
			List<ExecutionStart> toDelete = allExecutions.subList(0, deleteCount);
			for (ExecutionStart i: toDelete) {
				if (i.done()) {
					i.execution().remove();
				}
			}
			toDelete.clear();
		}
		
		allExecutions.removeIf(not(ExecutionStart::hasAUT)); // O(N^2). Fix if observed slow.
		deleteCount = allExecutions.size() - maxArtifacts;
		if (deleteCount > 0) {
			List<ExecutionStart> toDelete = allExecutions.subList(0, deleteCount);
			for (ExecutionStart i: toDelete) {
				if (i.done()) {
					clearAUTArtifacts(i.execution());
				}
			}
		}
	}
	
	public synchronized ExecutionEntry getSuiteHandle(String suiteId) {
		return runningSuites.get(suiteId);
	}

	public synchronized void removeSuiteHandle(String suiteId) {
		ExecutionEntry execution = runningSuites.remove(suiteId);
		execution.updateStatistics(e -> {
			if (!ExecutionIndex.isDone(e)) {
				e.setState(ExecutionState.CANCELED);
				e.setEndTime(currentTimeMillis());
			}
		});
	}

	public File getRoot() {
		return ISMCore.getInstance().getSuiteStore().getBase();
	}

	public File getTempDir() {
		return new File(ISMCore.getInstance().getSuiteStore().getBase()
				.getParent(), ".temp");
	}

	public URI makeRelativePath(File file) {
		return getRoot().toURI().relativize(file.toURI());
	}

	public IStatus onStart(ISMHandle<SuiteStats> s) {
		MultiStatus status = new MultiStatus(getClass(), 0, "Some executions in suite " + s.getFileName() +" from a previous session were not completed");
		getExecutions(s).getHandles().forEach(executionHandle -> {
			executionHandle.commit(e -> {
				if (!ExecutionIndex.isDone(e)) {
					var state = e.getState();
					e.setState(ExecutionState.CANCELED);
					e.setEndTime(currentTimeMillis());
					status.add(Status.warning(executionHandle.getFileName() + " - invalid state: " + state));
				}
				if (e.getStartTime() == 0) {
					e.setStartTime(currentTimeMillis());
					status.add(Status.warning(executionHandle.getFileName() + " - startTime = 0"));
				}
				return null;
			});
		});
		return status;
	}
	
	private record ExecutionStart(long start, boolean hasAUT, boolean done, ISMHandle<Execution> execution, ISMHandle<SuiteStats> suite) implements Comparable<ExecutionStart>{
		static ExecutionStart create (ISMHandle<Execution> execution,ISMHandle<SuiteStats> suite) {
			return execution.apply(e -> {
				return new ExecutionStart(e.getStartTime(), !e.getAutArtifacts().isEmpty(), ExecutionIndex.isDone(e), execution, suite);
			});
		}

		@Override
		public int compareTo(ExecutionStart o) {
			return Long.compare(start(), o.start());
		}
	}
	
}
