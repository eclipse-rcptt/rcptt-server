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

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Closer;

/**
 * Per classifier suite.
 * Manages association with agents and event propagation to profilers.
 * 
 * Lock order: TaskQueue -> TaskDescriptor -> TaskSuiteDescriptor
 */
public class TaskSuiteDescriptor {
	private static final int FAILED_TO_START_PER_AUT = 3;
	
	enum State {
		RUNNING,
		CANCELLED
	}

	private State state = State.RUNNING;

	private final List<TaskDescriptor> tasks = new ArrayList<TaskDescriptor>();
	private final List<TaskDescriptor> completedTasks = new ArrayList<TaskDescriptor>();
	// Per agent map for running tasks
	private Map<String, List<TaskDescriptor>> runningTasks = new HashMap<String, List<TaskDescriptor>>();
	private AutInfo aut;
	private Set<String> associatedAgents = new HashSet<String>();
	private Map<TaskDescriptor, FailedToStartEntry> failedToStart = new HashMap<TaskDescriptor, TaskSuiteDescriptor.FailedToStartEntry>();
	private final Map<String, TaskDescriptor> tasksById = Maps.newHashMap();

	private int agentCount = 0;

	private final int tasksPerAgent;

	private final int maxAgents;

	private final TaskDescriptor.Listener.Composite taskListeners = new TaskDescriptor.Listener.Composite();
	private final Listener.Composite suiteListeners = new Listener.Composite();

	public interface Listener {
		void onCancel(TaskSuiteDescriptor suite, Throwable reason, int tasksLeft);

		public void reportAgentLog(AgentInfo agent, ProcessStatus status, AgentLogEntryType agentLogEntryType);

		public void reportAUTLogs(AgentInfo agent, Map<String, String> files, AutStartupStatus status);

		public void onClientMessage(String string);

		class Adapter implements Listener {

			@Override
			public void onCancel(TaskSuiteDescriptor suite, Throwable reason, int tasksLeft) {
			}

			@Override
			public void reportAgentLog(AgentInfo agent, ProcessStatus status, AgentLogEntryType agentLogEntryType) {
			}

			@Override
			public void reportAUTLogs(AgentInfo agent, Map<String, String> files, AutStartupStatus status) {
			}

			@Override
			public void onClientMessage(String string) {
			}

		}
		public static class Composite implements Listener {
			private final List<Listener> listeners = new ArrayList<Listener>();

			@Override
			public void onCancel(TaskSuiteDescriptor suite, Throwable reason, int tasksLeft) {
				for (Listener listener : getListeners()) {
					listener.onCancel(suite, reason, tasksLeft);
				}
			}

			public synchronized void add(Listener listener) {
				listeners.add(listener);
			}

			public synchronized void remove(Listener listener) {
				listeners.remove(listener);
			}

			@Override
			public void reportAgentLog(AgentInfo agent, ProcessStatus status, AgentLogEntryType agentLogEntryType) {
				for (Listener listener : getListeners()) {
					listener.reportAgentLog(agent, status, agentLogEntryType);
				}
			}

			@Override
			public void reportAUTLogs(AgentInfo agent, Map<String, String> files, AutStartupStatus status) {
				for (Listener listener : getListeners()) {
					listener.reportAUTLogs(agent, files, status);
				}
			}

			@Override
			public void onClientMessage(String string) {
				for (Listener listener : getListeners()) {
					listener.onClientMessage(string);
				}
			}

			private List<Listener> getListeners() {
				return ImmutableList.copyOf(listeners);
			}

		}
	}

	public void addSuiteListener(Listener listener) {
		suiteListeners.add(listener);
	}

	public void removeSuiteListener(Listener listener) {
		suiteListeners.remove(listener);
	}

	public void addTaskListener(TaskDescriptor.Listener listener) {
		taskListeners.addListener(listener);
	}


	private static class FailedToStartEntry {
		int count = 0;
		final List<String> agentsIds = Lists.newArrayList();
	}

	private final int totalTaskCount;
	private int failedTaskCount = 0;

	private final String suiteId;

	public synchronized int totalTaskCount() {
		return totalTaskCount;
	}

	private synchronized void ensureInvariants() {
		int tmp = getTaskCount() + completedTasks.size();
		if (totalTaskCount != tmp)
			throw new AssertionError(String.format("Total task count: %d, was: %d", tmp, totalTaskCount));
	}

	public TaskSuiteDescriptor(String suiteId, AutInfo aut,
			IQ7Monitor errorMonitor, int tasksPerAgent, int maxAgents, Collection<TaskDescriptor> tasks) {
		this.suiteId = suiteId;
		this.tasksPerAgent = tasksPerAgent;
		this.maxAgents = maxAgents;
		checkNotNull(suiteId);
		checkNotNull(aut);
		checkNotNull(errorMonitor);
		this.aut = aut;
		suiteListeners.onClientMessage(
				"set minimum tasks per agent: " + tasksPerAgent
						+ " set max agents to use: "
						+ (maxAgents == -1 ? "Unlimited" : maxAgents));
		for (TaskDescriptor task : tasks) {
			addTask(task);
		}
		totalTaskCount = tasks.size();
	}

	/**
	 * Verifies every task.
	 * Errors are reported to listeners
	 * 
	 * @param dependencyResolver
	 *            -
	 * @param pm
	 */
	public void initialize(Map<String, Q7ArtifactRef> dependencyResolver, IProgressMonitor pm) {
		synchronized (this) {
			if (state != State.RUNNING)
				throw new IllegalStateException("Can't initialize tasks in " + state
						+ " state. Call completeBuild() first.");
		}
		SubMonitor monitor = SubMonitor.convert(pm);
		monitor.beginTask("Initializing " + this.getSuiteId() + "_" + this.getClassifier(), totalTaskCount);
		for (TaskDescriptor task : ImmutableList.copyOf(tasks)) {
			if (monitor.isCanceled())
				return;
			try {
				monitor.subTask("Initializing " + task.getTaskName());
				// Those that fail to initialize notify us and are removed by listeners
				task.initialize(dependencyResolver);
				monitor.worked(1);
			} catch (Throwable e) {
				cancel(e);
				break;
			}
		}
		monitor.done();
		ensureInvariants();
	}

	public TaskDescriptor getTask(String id) {
		TaskDescriptor task = tasksById.get(id);
		if (task == null)
			throw new NullPointerException("Can find task " + id + " in suite "
					+ getSuiteId());
		return task;
	}

	/**
	 * Adds a task to the suite.
	 * Can't be called after {@link #initialize()}
	 * 
	 * @param task
	 *            - an uninitialized task
	 * */
	private synchronized void addTask(TaskDescriptor task) {
		task.addListener(taskListeners);
		task.setSuiteId(getSuiteId());
		tasks.add(task);
		tasksById.put(task.getId(), task);
	}

	/** A number of incomplete tasks */
	public synchronized int getTaskCount() {
		return tasks.size() + getRunningTasksCount();
	}

	public int getFailedTaskCount() {
		return failedTaskCount;
	}


	/**
	 * Remaining count of tasks that an agent is able to execute
	 */
	public synchronized int getTaskCount(AgentInfo agent) {
		int count = 0;
		for (TaskDescriptor task : tasks) {
			if (task.canExecute(agent)) {
				count++;
			}
		}
		return count;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public String getClassifier() {
		return aut.getClassifier();
	}

	/**
	 * Get next task for specified agent.
	 * 
	 * @param agent
	 * @return
	 */
	private synchronized TaskDescriptor internalGet(AgentInfo agent) {
		for (TaskDescriptor task : tasks) {
			if (task.isReady() && task.canExecute(agent)) {
				return task;
			}
		}
		return null;
	}

	public synchronized void associate(AgentInfo agent) {
		associatedAgents.add(AgentRegistry.getAgentID(agent));
	}

	private synchronized void deAssociate(AgentInfo agent) {
		associatedAgents.remove(AgentRegistry.getAgentID(agent));
	}

	public synchronized void cleanAssociaton() {
		associatedAgents.clear();
	}

	public void cancel(final Throwable reason) {
		synchronized (this) {
			if (state == State.CANCELLED)
				return;
			state = State.CANCELLED;
		}
		final List<TaskDescriptor> tasks = new ArrayList<TaskDescriptor>();
		tasks.addAll(getPendingTasks());
		tasks.addAll(getRunningTasks());
		try {
			for (TaskDescriptor task : tasks) {
				IStatus status = RcpttPlugin.createStatus(reason);
				if (!status.matches(IStatus.CANCEL)) {
					status = new Status(IStatus.CANCEL, getClass(), 0, "Cancelled", reason);
				}
				task.complete(ReportUtil.generateReport(task.getId(),
						task.getTaskName(), status));
			}
		} finally {
			suiteListeners.onCancel(this, reason, tasks.size());
		}
	}

	public synchronized int getAgentTasks(AgentInfo agent) {
		int count = 0;
		List<TaskDescriptor> agentTasks = runningTasks.get(AgentRegistry
				.getAgentID(agent));
		if (agentTasks != null) {
			count += agentTasks.size();
		}
		return count;
	}

	public synchronized void setAgentsCount(int count) {
		agentCount = count;
		if (maxAgents != -1) {
			if (agentCount > maxAgents) {
				agentCount = maxAgents;
			}
		}
	}

	public void reduceExtraAgents() {
		IStatus status = RcpttPlugin.createStatus("Agents were assigned to another suite");
		List<TaskDescriptor> list = new ArrayList<>();
		synchronized (this) {
			runningTasks.values().removeIf(l -> l.isEmpty());
			associatedAgents.removeIf(agent -> !runningTasks.containsKey(agent));
			int toCancel = runningTasks.size() - agentCount;
			for (Map.Entry<String, List <TaskDescriptor>> entry : runningTasks.entrySet()) {
				if (toCancel <= 0) {
					break;
				}
				list.addAll(entry.getValue());
				toCancel--;
			}
		}
		for (TaskDescriptor task: list) {
			task.temporaryProblem(status);
		}
		synchronized (this) {
			runningTasks.values().removeIf(l -> l.isEmpty());
			associatedAgents.removeIf(agent -> !runningTasks.containsKey(agent));
		}
	}

	public int getAgentCount() {
		return agentCount;
	}

	public synchronized boolean isAssociated(AgentInfo agent) {
		return associatedAgents.contains(AgentRegistry.getAgentID(agent));
	}

	public synchronized boolean isRunning(AgentInfo agent) {
		return runningTasks.containsKey(AgentRegistry.getAgentID(agent));
	}

	public synchronized int getAssociationCount() {
		return associatedAgents.size();
	}

	public synchronized TaskDescriptor get(AgentInfo agent) {
		ensureInvariants();
		synchronized (this) {
			if (state == State.CANCELLED)
				return null;
		}


		int associatedAgents = getAssociationCount();
		int agentCount = getAgentCount();
		// Count of tasks could be executed by this agent.
		int taskCount = getTaskCount(agent);

		if (taskCount == 0) {
			// No tasks could be run by this agent at this time.
			if (isAssociated(agent) && !isRunning(agent)) {
				deAssociate(agent);
			}
			return null;
		}
		// If already associated, then just give next task
		if (isAssociated(agent)) {
			return internalGet(agent);
		}

		// Have free space for agent.
		if (associatedAgents < agentCount) {
			if (associatedAgents == 0
					|| (taskCount > getMinTaskPerAgent() * associatedAgents)) {
				associate(agent);
				return internalGet(agent);
			}
		}
		// Can't associate

		ensureInvariants();
		return null;
	}

	public synchronized int getRunningTasksCount() {
		return runningTasks.values().stream().mapToInt(List::size).sum();
	}

	public synchronized Collection<TaskDescriptor> getRunningTasks() {
		return runningTasks.values().stream().flatMap(List::stream).toList();
	}

	public synchronized Collection<TaskDescriptor> getPendingTasks() {
		return ImmutableList.copyOf(tasks);
	}
	
	public void cleanIncompatible(List<AgentInfo> presentAgents) {
		ensureInvariants();
		List<TaskDescriptor> copy;
		synchronized (this) {
			copy = List.copyOf(tasks);
		}
		
		copy.forEach(t -> t.ensureCompatible(presentAgents));
		ensureInvariants();
	}

	public void agentRemoved(AgentInfo agent) {
		String agentID = AgentRegistry.getAgentID(agent);
		final List<TaskDescriptor> list = Lists.newArrayList();
		synchronized (this) {
			ensureInvariants();
			List<TaskDescriptor> running = runningTasks.get(agentID);
			if (running != null)
				list.addAll(running);
			associatedAgents.remove(agentID);
		}
		for (TaskDescriptor running : list) {
			running.temporaryProblem(RcpttPlugin.createStatus("Agent " + agent.getUri()
					+ " disconnected. Cancelling " + running));
		}
		ensureInvariants();
	}

	@SuppressWarnings("resource")
	public void agentTimeout(AgentInfo agent) {
		IStatus status = RcpttPlugin.createStatus("Agent " + agent.getUri() + " has timed out");
		String agentID = AgentRegistry.getAgentID(agent);
		try (Closer closer = Closer.create() ) {
			synchronized (this) {
				ensureInvariants();
				List<TaskDescriptor> list = runningTasks.getOrDefault(agentID, emptyList());
				for (TaskDescriptor task : list) {
					closer.register(() -> task.temporaryProblem(status));
				}
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		ensureInvariants();
	}

	public synchronized boolean checkCancel(AgentInfo agent) {
		String agentID = AgentRegistry.getAgentID(agent);
		List<TaskDescriptor> list = runningTasks.get(agentID);
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	public  void reportAgentProblem(AgentInfo agent, IStatus iStatus) {
		String agentID = AgentRegistry.getAgentID(agent);
		List<TaskDescriptor> list;
		synchronized (this) {
			list = runningTasks.getOrDefault(agentID, Collections.emptyList());
		}
		for (TaskDescriptor task : ImmutableList.copyOf(list)) {
			task.temporaryProblem(iStatus);
		}
	}

	public void reportAUTLogs(AgentInfo agent, Map<String, String> files,
			AutStartupStatus status) {
		boolean needNotify = false;
		synchronized (this) {
			String agentID = AgentRegistry.getAgentID(agent);
			List<TaskDescriptor> list = runningTasks.get(agentID);
			if (list != null && list.size() > 0) {
				// if we have some task
				// running
				// on this agent.
				needNotify = true;
			}
		}
		boolean shutdown = AutStartupStatus.SHUTDOWN_ON_TIMEOUT.equals(status)
				|| AutStartupStatus.SHUTDOWN_ON_OPTION.equals(status);
		if (needNotify || shutdown) {
			suiteListeners.reportAUTLogs(agent, files, status);
		}
	}

	public void reportAgentLog(AgentInfo agent, ProcessStatus status,
			AgentLogEntryType agentLogEntryType) {
		suiteListeners.reportAgentLog(agent, status, agentLogEntryType);
	}

	public synchronized int getRunningAgents() {
		return runningTasks.size();
	}

	public int getMinTaskPerAgent() {
		return tasksPerAgent;
	}

	public AutInfo getAut() {
		return aut;
	}

	private final TaskDescriptor.Listener listener = new TaskDescriptor.Listener() {

		@Override
		public void onExecute(TaskDescriptor taskDescriptor) {
			synchronized (TaskSuiteDescriptor.this) {
				if (!tasks.remove(taskDescriptor)) {
					throw new RuntimeException(
							"Can't execute unscheduled task "
									+ taskDescriptor.getTaskName());
				}
				String agentId = taskDescriptor.getAgent()
						.getUri();
				List<TaskDescriptor> list = runningTasks.get(agentId);
				if (list == null) {
					list = new ArrayList<TaskDescriptor>();
					runningTasks.put(agentId, list);
				}
				list.add(taskDescriptor);
				ensureInvariants();
			}

		}

		@Override
		public void onComplete(TaskDescriptor taskDescriptor, AgentInfo agent, Report report) {
			synchronized (TaskSuiteDescriptor.this) {
				ensureInvariants();
				try {
					if (agent != null) {
						List<TaskDescriptor> list = runningTasks.get(agent.getUri());
						if (list == null) {
							throw new NullPointerException(
									"Failed to find agent " + agent.getUri()
											+ " in suite "
											+ TaskSuiteDescriptor.this
											+ " while cancelling "
											+ taskDescriptor);
						}
						if (!list.remove(
								taskDescriptor)) {
							throw new IllegalStateException("Can't find task "
									+ taskDescriptor.getTaskName()
									+ " in running tasks");
						}
						if (list.isEmpty())
							runningTasks.remove(agent.getUri());
					} else {
						if (!tasks.remove(taskDescriptor)) {
							throw new IllegalStateException("Can't find task "
									+ taskDescriptor.getTaskName()
									+ " in scheduled tasks");
						}
					}
					completedTasks.add(taskDescriptor);
					if (ReportUtil.getStatus(report) == SimpleSeverity.ERROR)
						failedTaskCount++;
				} finally {
					failedToStart.remove(taskDescriptor);
				}
				ensureInvariants();
			}
		}

		@Override
		public void onCancel(final TaskDescriptor taskDescriptor, final AgentInfo agent, final IStatus reason) {
			synchronized (TaskSuiteDescriptor.this) {
				ensureInvariants();
				FailedToStartEntry entry = failedToStart.get(taskDescriptor);
				if (entry == null) {
					entry = new FailedToStartEntry();
					failedToStart.put(taskDescriptor, entry);
				}
				entry.count += 1;
				if (agent != null) {
					entry.agentsIds.add(agent.getUri());
					List<TaskDescriptor> list = runningTasks.get(agent.getUri());
					list.remove(taskDescriptor);
					if (list.isEmpty())
						runningTasks.remove(agent.getUri());
					tasks.add(taskDescriptor);
					if (entry.count >= FAILED_TO_START_PER_AUT) {
						new Job("Cancelling task "
								+ taskDescriptor.getTaskName()) {
							@Override
							protected IStatus run(IProgressMonitor monitor) {
								MultiStatus status = new MultiStatus(ServerPlugin.PLUGIN_ID, 0, new IStatus[]{reason}, "Task failed to start "
										+ FAILED_TO_START_PER_AUT
												+ " times, last agent:" + agent.getUri(), null) {
									{
										setSeverity(IStatus.ERROR);
									}
								};
								Report report = ReportUtil
										.generateReport(
												taskDescriptor.getId(),
												taskDescriptor.getTaskName(),
												ProcessStatusConverter.toProcessStatus(status));
								taskDescriptor.complete(report);
								return Status.OK_STATUS;
							}
						}.schedule();
					} else {
					}

				} else {
					assert tasks.contains(taskDescriptor);
				}
				ensureInvariants();
			}
		}

		@Override
		public void onError(TaskDescriptor taskDescriptor, Throwable e, boolean fatal) {
			if (fatal) {
				cancel(e);
			}
		}
	};

	{
		taskListeners.addListener(listener);
	}

	@Override
	public String toString() {
		return getSuiteId() + "_" + getClassifier();
	}

	@SuppressWarnings("resource")
	public final void checkTimeout() {
		try (Closer closer = Closer.create()) {
			synchronized (this) {
				runningTasks.values().forEach(agentTasks -> {
					agentTasks.forEach(t -> {
						int prefetched = agentTasks.size();
						closer.register(() -> { // avoid concurrent modification
							t.timeoutCheck(prefetched);
						});
					});
				});
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
