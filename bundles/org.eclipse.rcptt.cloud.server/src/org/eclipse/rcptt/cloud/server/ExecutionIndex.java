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

import static org.eclipse.rcptt.cloud.server.ServerPlugin.logErr;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.CANCELED;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.FINISHED;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.PENDING;
import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.emf.common.util.EMap;

import com.google.common.base.Function;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class ExecutionIndex {
	private final ExecutionRegistry executionRegistry;

	public ExecutionIndex(List<ISMHandle<Execution>> executions, ExecutionRegistry executionRegistry) {
		this.executionRegistry = requireNonNull(executionRegistry);
		if (executions == null) {
			loadExisting();
		} else {
			for (ISMHandle<Execution> execution : executions) {
				add(execution);
			}
		}

		ServerPlugin.getDefault().getExecService()
				.addListener(new IndexingListener());

	}

	private SortedMap<Integer, ISMHandle<Execution>> byId = Collections
			.synchronizedSortedMap(new TreeMap<Integer, ISMHandle<Execution>>());

	/**
	 * Adds an execution to map. If execution already has id, uses existing,
	 * otherwise generates it
	 * 
	 * @param execution
	 */
	private void add(ISMHandle<Execution> execution) {
		int id = execution.apply(getId);
		if (id != -1) {
			byId.put(id, execution);
			return;
		}
		synchronized (byId) {
			byId.put(execution.commit(setId), execution);
		}
	}

	public Collection<ISMHandle<Execution>> getExecutions(String suiteNameFilter) {
		if (suiteNameFilter == null) {
			List<ISMHandle<Execution>> result = null;
			synchronized (byId) {
				result = new ArrayList<ISMHandle<Execution>>(byId.values());
			}
			Collections.reverse(result);
			return result;
		} else {
			ISMHandle<SuiteStats> handle = ISMCore.getInstance()
					.getSuiteStore().getHandle(suiteNameFilter);
			if (handle.exists()) {
				final List<ISMHandle<Execution>> executions = new ArrayList<ISMHandle<Execution>>(
						executionRegistry.getExecutions(handle)
								.getHandles());

				Collections.reverse(executions);
				return executions;
			}
			return new ArrayList<ISMHandle<Execution>>();
		}
	}

	public ISMHandle<Execution> getExecution(int id) {
		return byId.get(id);
	}

	/**
	 * Loads existing entries from artifacts. Transparent IDs generated based on
	 * start time
	 */
	private void loadExisting() {
		System.out.println("Processing existing builds");
		final SortedMap<Long, ISMHandle<Execution>> byTime = new TreeMap<Long, ISMHandle<Execution>>();
		for (final ISMHandle<SuiteStats> suite : ISMCore.getInstance()
				.getSuiteStore().getHandles()) {
			System.out.println("\nProcessing suite: " + suite.getFileName());
			if (!suite.exists()) {
				logNoHandle(suite);
				continue;
			}

			final String suiteID = suite.apply(getSuiteId);
			for (final ISMHandle<Execution> execution : executionRegistry.getExecutions(suite).getHandles()) {
				if (!execution.exists()) {
					logNoHandle(execution);
					continue;
				}
				System.out.print("*");
				execution.commit(new Function<Execution, Void>() {
					@Override
					public Void apply(Execution exec) {
						setSuiteID(suiteID, exec);
						setMetadata(execution, exec);
						adjustStatus(exec);
						return null;
					}
				});
				if (hasId(execution)) {
					add(execution);
				} else {
					byTime.put(execution.apply(getStartTime), execution);
				}
			}
		}

		for (ISMHandle<Execution> entry : byTime.values()) {
			add(entry);
		}
	}

	/**
	 * Considering that there are no running executions on server startup, all
	 * pending executions must be canceled
	 */
	private void adjustStatus(Execution exec) {
		if (!isDone(exec)) {
			exec.setState(CANCELED);
		}
		if (exec.getEndTime() < exec.getStartTime()) {
			exec.setEndTime(getLastReportTime(exec));
		}
	}

	private void setMetadata(final ISMHandle<Execution> execution,
			Execution exec) {
		if (!exec.getMetadata().isEmpty()) {
			return;
		}
		File metadata = new File(execution.getFileRoot(), "metadata.properties");
		if (!metadata.exists()) {
			return;
		}
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(metadata));
		} catch (IOException e) {
			// Not a big deal if we have no props
			return;
		}
		for (Entry<Object, Object> entry : props.entrySet()) {
			exec.getMetadata().put(entry.getKey().toString(),
					entry.getValue().toString());
		}
	}

	private void setSuiteID(final String suiteID, Execution exec) {
		if (exec.getSuiteId() == null) {
			exec.setSuiteId(suiteID);
		}
	}

	private static long getLastReportTime(Execution exec) {
		long result = exec.getStartTime();
		for (ExecutionAgentStats agentStats : exec.getAgentStats()) {
			for (ExecutionAgentTest test : agentStats.getTests()) {
				result = max(result, test.getEndTime());
			}
		}
		return result;
	}

	public static boolean isDone(Execution execution) {
		return doneStates.contains(execution.getState());
	}

	public static boolean isPending(Execution execution) {
		return execution.getState().equals(PENDING);
	}

	private static final List<ExecutionState> doneStates = asList(FINISHED,
			CANCELED);

	private Function<Execution, Integer> setId = new Function<Execution, Integer>() {
		private int lastId() {
			try {
				return byId.lastKey();
			} catch (NoSuchElementException e) {
				return -1;
			}
		}

		@Override
		public Integer apply(Execution execution) {
			int id = lastId() + 1;
			execution.setGlobalID(id);
			return id;
		}
	};

	public static Function<Execution, EMap<String, String>> getMetadata = new Function<Execution, EMap<String, String>>() {
		@Override
		public EMap<String, String> apply(Execution input) {
			return input.getMetadata();
		}
	};

	private static boolean hasId(ISMHandle<Execution> handle) {
		return handle.apply(getId) != -1;
	}

	private static Function<Execution, Long> getStartTime = new Function<Execution, Long>() {
		@Override
		public Long apply(Execution exec) {
			return exec.getStartTime();
		}
	};

	private static Function<Execution, Integer> getId = new Function<Execution, Integer>() {
		@Override
		public Integer apply(Execution exec) {
			return exec.getGlobalID();
		}
	};

	public static Function<SuiteStats, String> getSuiteId = new Function<SuiteStats, String>() {
		@Override
		public String apply(SuiteStats exec) {
			return exec.getSuiteName();
		}
	};

	private class IndexingListener extends ExecutionAdapter {

		@Override
		public void created(ISMHandle<Execution> execution) {
			add(execution);
		}
	}

	private static void logNoHandle(ISMHandle<?> handle) {
		logErr(new Exception("Just to show stacktrace"),
				"Handle not found: %s", handle);
	}

}
