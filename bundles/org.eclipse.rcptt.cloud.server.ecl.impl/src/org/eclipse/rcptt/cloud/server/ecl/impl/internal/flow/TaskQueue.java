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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow;

import static org.eclipse.rcptt.cloud.server.ServerPlugin.PLUGIN_ID;
import static org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter.toIStatus;
import static org.eclipse.rcptt.reporting.util.ReportUtils.getFailMessage;
import static org.eclipse.rcptt.reporting.util.ReportUtils.getStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.Q7LoggingManager;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Function;
import com.google.common.collect.Sets;
import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.AgentRegistry.IAgentRegistryListener;
import org.eclipse.rcptt.cloud.server.AgentUtils;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskSuiteDescriptor.Listener;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.AgentStats;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;

public class TaskQueue {
	public interface ITaskListener {
		void timeoutHappen(AgentInfo agent);
	}

	private static final int AGENT_TIMEOUT_COUNT = 2;
	private Map<String, TaskSuiteDescriptor> suites = Collections
			.synchronizedMap(new HashMap<String, TaskSuiteDescriptor>());
	private IAgentRegistryListener agentListener = new IAgentRegistryListener() {

		@Override
		public void removed(AgentInfo info) {
			agentRemoved(info);
		}

		@Override
		public void added(AgentInfo info) {
			agentAdded(info);
		}

		@Override
		public void agentTimeout(java.util.List<AgentInfo> timeouts) {
			processTimeout(timeouts);
		}

		@Override
		public void timeoutCheck() {
			processTimeoutCheck();
		}

		@Override
		public boolean checkCancel(AgentInfo agent) {
			return doCheckCancel(agent);
		}
	};
	private Map<String, Task> sendingTasks = new HashMap<String, Task>();
	private final Listener.Adapter suiteListener = new Listener.Adapter() {
		@Override
		public void onCancel(TaskSuiteDescriptor suite, Throwable reason, int tasksLeft) {
			synchronized (suites) {
				TaskSuiteDescriptor removed = suites.remove(getSuiteId(suite.getSuiteId(),
						suite.getClassifier()));
				assert removed == suite;
				removed.removeSuiteListener(suiteListener);
			}
		}
	};


	private List<ITaskListener> listeners = new ArrayList<ITaskListener>();

	private IQ7Monitor taskLog;
	private IQ7Monitor problemLog;
	private AgentRegistry agentRegistry;

	public TaskQueue(AgentRegistry registry) {
		this.agentRegistry = registry;
		this.agentRegistry.addListener(agentListener);
		taskLog = Q7LoggingManager.get("task.queue");
		problemLog = Q7LoggingManager.get("problem.log", "", "errors");
		notificationThread.start();
	}

	protected boolean doCheckCancel(AgentInfo agent) {
		synchronized (suites) {
			boolean cancel = true;
			for (TaskSuiteDescriptor suite : suites.values()) {
				if (!suite.checkCancel(agent)) {
					cancel = false;
					break;
				}
			}
			return cancel;
		}
	}

	public void addTeskListener(ITaskListener listener) {
		this.listeners.add(listener);
	}

	public void removeTeskListener(ITaskListener listener) {
		this.listeners.remove(listener);
	}

	protected void processTimeout(List<AgentInfo> timeouts) {
		synchronized (suites) {
			for (AgentInfo agent : timeouts) {
				// Check for all tasks assigned to agent.
				for (TaskSuiteDescriptor suite : suites.values()) {
					List<TaskDescriptor> timeout = suite.agentTimeout(agent,
							AGENT_TIMEOUT_COUNT);
					for (TaskDescriptor task : timeout) {
						String cause = "Task is marked as filed, since "
								+ AGENT_TIMEOUT_COUNT
								+ " agents are timeout at execution of task: "
								+ task.getTaskName() + " agents: "
								+ task.getTimeoutAgents();
						problemLog.log(cause, null);

						Report report = ReportUtil.generateFailedReport(
								task.getId(), task.getTaskName(), cause);
						notifyISMTaskComplete(agent, task, report);
						task.complete(report);
					}
					for (ITaskListener listener : listeners) {
						listener.timeoutHappen(agent);
					}
				}
			}
			// Remove out incompatible tests.
		}
		cleanIncompatibleTests();
	}
	
	protected void processTimeoutCheck() {
		synchronized (suites) {
			for (TaskSuiteDescriptor suit : suites.values()) {
				for (TaskDescriptor task : suit.getRunningTasks()) {
					task.timeoutCheck();
				}
			}
		}
	}

	public TaskQueue(AgentRegistry registry, IQ7Monitor taskLog,
			IQ7Monitor problemLog) {
		this.agentRegistry = registry;
		this.agentRegistry.addListener(agentListener);
		this.taskLog = taskLog;
		this.problemLog = problemLog;
	}

	private String getSuiteId(String suiteID, String classifier) {
		String rv = "_" + suiteID + "_" + classifier;
		return rv;
	}

	protected void agentAdded(AgentInfo info) {
		reschedule(false);
	}

	protected void agentRemoved(AgentInfo info) {
		cancelSendingTask(info);
		cleanIncompatibleTests();
		synchronized (this.suites) {
			// Mark all running on agent task as not running.
			for (TaskSuiteDescriptor d : this.suites.values()) {
				d.agentRemoved(info);
			}
			reschedule(false);
		}
	}

	public Collection<String> getSuites() {
		Set<String> result = Sets.newHashSet();
		synchronized (this.suites) {
			for (TaskSuiteDescriptor s : this.suites.values()) {
				result.add(s.getSuiteId());
			}
		}
		return result;
	}

	public Collection<TaskSuiteDescriptor> getSuiteDescriptors() {
		synchronized (this.suites) {
			return new ArrayList<TaskSuiteDescriptor>(this.suites.values());
		}
	}

	public TaskDescriptor get(AgentInfo agent, String suiteID) {
		TaskSuiteDescriptor descr = getSuite(agent, suiteID);
		// Check for already associated suite.
		TaskDescriptor rv = get(agent, descr);
		if (rv != null)
			return rv;

		// At this point agent will switch suite
		// Lets select some new suite
		cleanIncompatibleTests();
		synchronized (suites) {

			List<TaskSuiteDescriptor> possible = new ArrayList<TaskSuiteDescriptor>();
			for (TaskSuiteDescriptor d : this.suites.values()) {
				if (d.getClassifier().equals(agent.getClassifier())) {
					rv = get(agent, d);
					if (rv != null)
						return rv;
					possible.add(d);
				}
			}
			sortAgents(possible);
			// Try to associate after reshedule
			reschedule(false);
			for (TaskSuiteDescriptor d : possible) {
				rv = get(agent, d);
				if (rv != null)
					return rv;
			}
		}
		return null;
	}

	private TaskDescriptor get(AgentInfo agent, TaskSuiteDescriptor descr) {
		if (descr == null)
			return null;
		synchronized (this.suites) {
			TaskDescriptor task = descr.get(agent);
			if (task != null) {
				task.execute(agent);
				notifyISMTaskTaken(agent);
				return task;
			}
			return null;
		}
	}

	private void cleanIncompatibleTests() {
		Map<TaskSuiteDescriptor, List<TaskDescriptor>> toClean = new HashMap<TaskSuiteDescriptor, List<TaskDescriptor>>();

		Map<TaskSuiteDescriptor, Throwable> toCancel = new HashMap<TaskSuiteDescriptor, Throwable>();
		synchronized (this.suites) {
			List<AgentInfo> agents = agentRegistry.getAgents();
			for (TaskSuiteDescriptor s : this.suites.values()) {
				try {
					List<TaskDescriptor> tests = s
							.findIncompatibleTasks(agents);
					if (tests != null && tests.size() > 0) {
						toClean.put(s, tests);
					}
				} catch (Throwable e) {
					toCancel.put(s, e);
				}
			}
		}
		for (Map.Entry<TaskSuiteDescriptor, Throwable> entry : toCancel.entrySet()) {
			// We call cancel outside of synchronization block to prevent various listener deadlocks.
			entry.getKey().cancel(entry.getValue());
		}
		for (Map.Entry<TaskSuiteDescriptor, List<TaskDescriptor>> e : toClean
				.entrySet()) {
			for (TaskDescriptor task : e.getValue()) {
				String cause = "Task has failed, since no compatible agents are left.";
				if (task.hashTimeouts()) {
					cause = cause
							+ " Task has timed out on agents: "
							+ task.getTimeoutAgents();
				}
				Report report = ReportUtil.generateFailedReport(task.getId(), task.getTaskName(), cause);
				if (report != null) {
					AgentInfo server = ModelFactory.eINSTANCE.createAgentInfo();
					server.setUri("no_agent");

					server.setClassifier(task.getAut().getClassifier());
					task.complete(report);
				}
			}
		}
	}

	private TaskSuiteDescriptor getSuite(AgentInfo agent, String suiteID) {
		if (suiteID == null) {
			return null;
		}
		TaskSuiteDescriptor descr = null;
		synchronized (this.suites) {
			descr = this.suites.get(getSuiteId(suiteID, agent.getClassifier()));
		}
		return descr;
	}

	public void complete(AgentInfo agent, String suiteID, String taskID,
			Report report) {
		TaskSuiteDescriptor suite = getSuite(agent, suiteID);
		if (suite != null && suite.isAssociated(agent)) {
			taskLog.log(toString(), null);
			suite.getTask(taskID).complete(report);
			return;
		}
	}

	public void failureTask(AgentInfo agent, String suiteID, String taskID, Report report, boolean failedToStart) {

		TaskSuiteDescriptor suite = getSuite(agent, suiteID);

		if (suite == null || !suite.isAssociated(agent))
			return;

		try {
			TaskDescriptor task = suite.getTask(taskID);
			if (report == null) {
				report = ReportUtil.generateFailedReport(task.getId(),
						task.getTaskName(), "Report is absent");
			}
			ProcessStatus failMessage = getStatus(report.getRoot());
			if (failedToStart) {
				task.cancel(new MultiStatus(TaskQueue.class,0, new IStatus[] {toIStatus(failMessage)}, "Failed to start AUT: " + getFailMessage (report.getRoot()), null));
			} else {
				problemLog.log("Task failure: " + failMessage + " " + task.getTaskName());
				notifyISMTaskComplete(agent, task, report);
				task.complete(report);
			}
		} catch (RuntimeException e1) {
			ServerPlugin.logErr(e1, "Failed to register task failure, suite: %s, task %s", suiteID, taskID);
		}
	}

	private List<Runnable> runnables = new ArrayList<Runnable>();

	private Thread notificationThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				synchronized (runnables) {
					for (Runnable r : runnables) {
						r.run();
					}
					runnables.clear();
					try {
						runnables.wait(100);
					} catch (InterruptedException e) {
						// Ignore
					}
				}
			}
		}
	});

	private void notifyISMTaskComplete(final AgentInfo agent,
			TaskDescriptor task, final Report report) {
		synchronized (runnables) {
			runnables.add(new Runnable() {
				@Override
				public void run() {
					ISMHandle<AgentStats> handle = getAgentInfoStats(agent);
					if (handle != null) {
						synchronized (handle) {
							handle.commit(increaseFailedForAgent(report));
						}
					}
				}
			});
		}
	}

	private void notifyISMTaskTaken(final AgentInfo agent) {
		synchronized (runnables) {
			runnables.add(new Runnable() {
				@Override
				public void run() {
					ISMHandle<AgentStats> handle = getAgentInfoStats(agent);
					if (handle != null) {
						synchronized (handle) {
							handle.commit(new Function<AgentStats, Void>() {
								@Override
								public Void apply(AgentStats agentStats) {
									agentStats.setTakenTasks(agentStats
											.getTakenTasks() + 1);
									return null;
								}
							});
						}
					}
				}
			});
		}
	}

	private static Function<AgentStats, Void> increaseFailedForAgent(
			final Report report) {
		return new Function<AgentStats, Void>() {
			@Override
			public Void apply(AgentStats agentStats) {
				agentStats.setTotalCount(agentStats.getTotalCount() + 1);
				if (report != null) {
					EObject root = report.getRoot().getProperties()
							.get(IQ7ReportConstants.ROOT);
					if (root instanceof Q7Info) {
						;
						if (SimpleSeverity.create((Q7Info) root) == SimpleSeverity.ERROR) {
							agentStats.setFailedCount(agentStats
									.getFailedCount() + 1);
						}
					}
				}
				return null;
			}
		};
	}

	private ISMHandle<AgentStats> getAgentInfoStats(final AgentInfo agent) {
		ISMHandleStore<AgentStats> agentStore = ISMCore.getInstance()
				.getAgentStore();
		if (agentStore == null) {
			return null;
		}
		ISMHandle<AgentStats> handle = agentStore.getHandle(FileUtil
				.getID(agent.getUri()));
		synchronized (handle) {
			handle.commit(new Function<AgentStats, Void>() {
				@Override
				public Void apply(AgentStats stats) {
					if (stats.getUri() == null) {
						stats.setUri(agent.getUri());
					}
					if (stats.getClassifier() == null) {
						stats.setClassifier(agent.getClassifier());
					}
					if (stats.getSkipTags() == null) {
						stats.setSkipTags(Arrays
								.toString(agent.getSkipTags().toArray(
										new String[agent.getSkipTags().size()])));
					}
					if (stats.getLaunchID() == null) {
						stats.setLaunchID(agent.getLaunchId());
					}
					return null;
				}
			});
		}
		return handle;
	}

	public void cancel(String suiteId, Throwable reason) {
		taskLog.log("####### cancel suite: " + suiteId, reason);
		List<TaskSuiteDescriptor> suites = getSuite(suiteId);
		for (TaskSuiteDescriptor suite : suites) {
			suite.cancel(reason);
		}

		reschedule(false);
	}

	public synchronized List<TaskSuiteDescriptor> getSuite(String suiteId) {
		List<TaskSuiteDescriptor> toRemove = new ArrayList<TaskSuiteDescriptor>();
		for (TaskSuiteDescriptor descr : suites.values()) {
			if (descr.getSuiteId().equals(suiteId)) {
				toRemove.add(descr);
			}
		}
		return toRemove;
	}

	public int getSuitePendings(String suite) {
		synchronized (suites) {
			int count = 0;
			for (TaskSuiteDescriptor d : suites.values()) {
				if (d.getSuiteId().equals(suite)) {
					count += d.getTaskCount();
				}
			}
			return count;
		}
	}

	public int getAgentTakenTasks(AgentInfo agent) {
		int count = 0;
		synchronized (this.suites) {
			for (TaskSuiteDescriptor suite : this.suites.values()) {
				count += suite.getAgentTasks(agent);
			}
		}
		return count;
	}

	public void schedule(TaskSuiteDescriptor... newSuites) {
		synchronized (this.suites) {
			for (TaskSuiteDescriptor suite : newSuites) {
				this.suites.put(
						getSuiteId(suite.getSuiteId(), suite.getClassifier()),
						suite);
				suite.addSuiteListener(suiteListener);
			}
			reschedule(true);
		}
	}

	long lastReshedule = -1;

	/**
	 * Call directory only from testing code
	 */
	public void reschedule(boolean clean) {
		// Remove all dead tests
		StringBuilder resheduleBuffer = new StringBuilder();

		List<AgentInfo> agents = agentRegistry.getAgents();
		Map<String, Integer> agentByClassifier = AgentUtils
				.getAgentByClassifier(agents);
		synchronized (this.suites) {
			lastReshedule = System.currentTimeMillis();
			// sort suites by agent count
			List<TaskSuiteDescriptor> sorted = new ArrayList<TaskSuiteDescriptor>(
					this.suites.values());

			sortAgents(sorted);

			assignAgents(agentByClassifier, sorted, clean);

			for (TaskSuiteDescriptor ag : sorted) {
				resheduleBuffer.append("\nsuites: " + ag.getSuiteId()
						+ " assigned agents: " + ag.getAgentCount()
						+ " tasks: " + ag.getTaskCount());
			}
		}
		if (resheduleBuffer.length() > 0) {
			taskLog.log("After reshedule:" + resheduleBuffer.toString(), null);
		}
	}

	public static void assignAgents(Map<String, Integer> agentByClassifier,
			List<TaskSuiteDescriptor> suites, boolean clean) {
		// Assign at least one agent per suite, if available
		for (TaskSuiteDescriptor descr : suites) {
			Integer value = agentByClassifier.get(descr.getClassifier());
			if (value != null && value.intValue() > 0) {
				agentByClassifier.put(descr.getClassifier(),
						Integer.valueOf(value.intValue() - 1));
				descr.setAgentsCount(1);
			}
		}

		// Assign all other available agents
		int i = 0;
		for (TaskSuiteDescriptor descr : suites) {
			String cl = descr.getClassifier();
			Integer value = agentByClassifier.get(cl);
			if (value != null) {
				int available = value.intValue();
				if (available > 0) {
					int assign = calculateAgentAssignCount(suites, i, descr,
							available);
					int curAgents = descr.getAgentCount();
					int taskCount = descr.getTaskCount();
					int maxAgents = calculateMaxAgents(taskCount, descr);

					if (curAgents + assign > maxAgents) {
						assign = maxAgents - curAgents;
					}

					descr.setAgentsCount(curAgents + assign);
					assign = descr.getAgentCount() - curAgents;

					agentByClassifier.put(cl,
							Integer.valueOf(available - assign));
				}
			}
			i++;
		}

		if (clean) {
			// Reduce extra agents
			for (TaskSuiteDescriptor suite : suites) {
				suite.reduceExtraAgents();
			}
		}
	}

	private static int calculateMaxAgents(int taskCount,
			TaskSuiteDescriptor descr) {
		return taskCount / descr.getMinTaskPerAgent() + 1;
	}

	private static int calculateAgentAssignCount(
			List<TaskSuiteDescriptor> value, int index,
			TaskSuiteDescriptor suiteInfo, int available) {
		int assign = 0;
		if (available == 1) {
			assign = 1;
		} else {
			if (value.size() != 1) {
				int testSum = 0;
				for (int j = index; j < value.size(); j++) {
					testSum += (value.get(j).getTaskCount());
				}
				if (testSum == 0) {
					testSum = 1;
				}
				assign = (available * suiteInfo.getTaskCount()) / testSum;
			} else {
				assign = available;
			}
		}
		if (index == value.size() - 1 && assign < available) {
			assign = available;
		}
		return assign;
	}

	private void sortAgents(List<TaskSuiteDescriptor> value) {
		Collections.sort(value, new Comparator<TaskSuiteDescriptor>() {

			@Override
			public int compare(TaskSuiteDescriptor o1, TaskSuiteDescriptor o2) {
				return Integer.valueOf(o2.getTaskCount()).compareTo(
						Integer.valueOf(o1.getTaskCount()));
			}
		});
	}

	public void reportProblem(AgentInfo agent, IStatus iStatus) {
		if (agent != null) {
			problemLog.log("error in agent: " + agent.getUri() + " " + iStatus,
					null);
			synchronized (suites) {
				for (TaskSuiteDescriptor suite : suites.values()) {
					suite.reportAgentProblem(agent, iStatus);
				}
			}
		}
	}

	public void clearAll() {
		synchronized (this.suites) {
			suites.clear();
		}
	}

	public void runSync(Runnable runnable) {
		synchronized (this.suites) {
			runnable.run();
		}
	}

	public void remove(TaskSuiteDescriptor taskSuiteDescriptor) {
		synchronized (this.suites) {
			suites.remove(taskSuiteDescriptor);
		}
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		synchronized (this.suites) {
			List<TaskSuiteDescriptor> values = new ArrayList<TaskSuiteDescriptor>(
					this.suites.values());
			Collections.sort(values, new Comparator<TaskSuiteDescriptor>() {

				@Override
				public int compare(TaskSuiteDescriptor o1,
						TaskSuiteDescriptor o2) {
					return o1.getSuiteId().compareTo(o2.getSuiteId());
				}
			});
			for (TaskSuiteDescriptor d : values) {
				b.append("suite: ").append(d.getSuiteId())
						.append(" max agents: ").append(d.getAgentCount())
						.append(" current agents: ")
						.append(d.getAssociationCount()).append("  tasks: ")
						.append(d.getTaskCount()).append(" running tasks:")
						.append(d.getRunningTasksCount()).append("\n");
			}
		}
		return b.toString();
	}

	public void reportAUTLogs(String suiteID, AgentInfo agent,
			Map<String, String> files, AutStartupStatus status) {
		if (agent != null) {
			TaskSuiteDescriptor suite = getSuite(agent, suiteID);
			if (suite != null) {
				suite.reportAUTLogs(agent, files, status);
			} else {
				problemLog.log("Recieved AUT startup from agent: "
						+ AgentRegistry.getAgentID(agent)
						+ " but no suite with id are found: " + suiteID, null);
			}
		}
	}

	public void reportAgentLog(String suiteID, AgentInfo agent, ProcessStatus status,
			AgentLogEntryType agentLogEntryType) {
		if (agent != null) {
			TaskSuiteDescriptor suite = getSuite(agent, suiteID);
			if (suite != null) {
				suite.reportAgentLog(agent, status, agentLogEntryType);
			} else {
				problemLog
						.log("Recieved agent log message: "
								+ AgentRegistry.getAgentID(agent)
								+ " but no suite with id are found: " + suiteID,
								null);
			}
		}
	}

	public String getAgentSuite(AgentInfo agent) {
		synchronized (this.suites) {
			for (TaskSuiteDescriptor suite : this.suites.values()) {
				if (suite.isRunning(agent)) {
					return suite.getSuiteId();
				}
			}
		}
		return "";
	}


	public void registerToAgent(AgentInfo agent, Task task) {
		synchronized (sendingTasks) {
			String agentId = AgentRegistry.getAgentID(agent);
			cancelSendingTask(agent);
			sendingTasks.put(agentId, task);
		}
	}

	private void cancelSendingTask(AgentInfo agent) {
		String agentId = AgentRegistry.getAgentID(agent);
		Task task = sendingTasks.get(agentId);
		if (task != null) {
			// Taking task without notification for previous one,
			// Lets mark already recieved task as not running.
			TaskDescriptor taskDescr = getTaskDescriptor(task.getSuiteId(),
					agent.getClassifier(), task.getId());
			if (taskDescr != null)
				taskDescr.cancel(new Status(IStatus.ERROR, PLUGIN_ID, "Another task is started on agent"));
		}
	}

	private TaskDescriptor getTaskDescriptor(String suiteId, String classifier,
			String taskId) {
		TaskSuiteDescriptor suite = suites.get(getSuiteId(suiteId, classifier));
		if (suite == null)
			return null;
		assert suite.getClassifier().equals(classifier);
		TaskDescriptor rv = suite.getTask(taskId);
		return rv;
	}

	public void markTaskRecieved(AgentInfo agent, boolean recievedOk) {
		synchronized (sendingTasks) {
			if (recievedOk) {
				String agentId = AgentRegistry.getAgentID(agent);
				sendingTasks.remove(agentId);
			} else {
				cancelSendingTask(agent);
			}
		}
	}
}
