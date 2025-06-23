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
import static com.google.common.base.Predicates.compose;
import static com.google.common.base.Predicates.equalTo;
import static com.google.common.collect.Iterables.filter;
import static java.lang.String.format;
import static org.eclipse.core.runtime.Status.error;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.core.utils.TagsUtil;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.eclipse.rcptt.cloud.common.AutUtil;
import org.eclipse.rcptt.cloud.common.Functions;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;

/**
 * Test representation.
 * 
 * Central point for dispatching notifications related to a test state.
 * Validates test components and emits a Task object.
 * 
 * Life cycle:
 * 
 * construction initialize() execute() cancel() complete()
 * 
 * @author vasili
 * 
 */
public class TaskDescriptor {
	private final ITestStore dir;
	private final Task task;
	private final String name;

	private AgentInfo agent;
	private Long started = null;

	public interface Listener {
		/** Is called when agent is assigned */
		void onExecute(TaskDescriptor taskDescriptor);

		/**
		 * Is called on any Q7 error related to the task
		 * 
		 * @param fatal
		 *            - if true, tasksuite should also be terminated
		 * */
		void onError(TaskDescriptor taskDescriptor, Throwable reason, boolean fatal);

		/**
		 * Is called when agent asociation is revoked Might happen on agent
		 * removal, on agent error, on aut startup error.
		 * 
		 * @param agent
		 *            an agent task failed to execute on
		 * @param iStatus
		 *            description of agent failure
		 * */
		void onCancel(TaskDescriptor taskDescriptor, AgentInfo agent, IStatus iStatus);

		/**
		 * Is called when task is completely handled and won't be processed
		 * anymore
		 */
		void onComplete(TaskDescriptor taskDescriptor, Report report);

		static class Composite implements Listener {

			private List<Listener> listeners = Lists.newArrayList();

			public void addListener(Listener listener) {
				listeners.add(listener);
			}

			@Override
			public void onExecute(TaskDescriptor taskDescriptor) {
				for (Listener listener : listeners) {
					listener.onExecute(taskDescriptor);
				}
			}

			@Override
			public void onCancel(TaskDescriptor taskDescriptor, AgentInfo agent, IStatus iStatus) {
				for (Listener listener : listeners) {
					listener.onCancel(taskDescriptor, agent, iStatus);
				}
			}

			@Override
			public void onComplete(TaskDescriptor taskDescriptor, Report report) {
				for (Listener listener : listeners) {
					listener.onComplete(taskDescriptor, report);
				}
			}

			@Override
			public void onError(TaskDescriptor taskDescriptor, Throwable e, boolean fatal) {
				for (Listener listener : listeners) {
					listener.onError(taskDescriptor, e, fatal);
				}
			}

		}
	}

	private Q7ArtifactRef scenario;

	private List<String> timeoutAgents = null;

	enum State {
		UNINITIALIZED, INITIALIZED, EXECUTING, REPORTED
	}

	private State state = State.UNINITIALIZED;

	private final Listener.Composite listeners = new Listener.Composite();

	public TaskDescriptor(ITestStore dir, AutInfo aut, TestOptions testOptions,
			Q7ArtifactRef scenario, String name) throws IOException {
		super();
		checkNotNull(dir);
		checkNotNull(aut);
		checkNotNull(scenario);

		this.name = name;
		this.dir = dir;
		this.scenario = scenario;

		task = ServerCommandsFactory.eINSTANCE.createTask();
		task.setId(scenario.getId());
		checkNotNull(task.getId());

		task.setAut(EcoreUtil.copy(aut));
		if (testOptions != null) {
			task.setTestOptions(EcoreUtil.copy(testOptions));
		}
	}

	/**
	 * Verifies resources comprising test.
	 * 
	 * Immediately completes task on error
	 * */
	public synchronized void initialize(
			Map<String, Q7ArtifactRef> dependencyResolver) {
		if (state == State.REPORTED)
			return;
		try {
			if (state != State.UNINITIALIZED)
				throw new IllegalStateException("Can't initialize a task in "
						+ state + " state");
			TestSuite suite = ModelFactory.eINSTANCE.createTestSuite();
			suite.setId(dir.getTestSuite().getId());
			List<Q7ArtifactRef> refs = ImmutableList.<Q7ArtifactRef> builder()
					.addAll(allDeps(scenario, dependencyResolver))
					.add(scenario).build();
			ModelUtil.setRefs(suite,
					Lists.newArrayList(EcoreUtil.copyAll(refs)));
			task.setSuite(suite);
			verifyScenarioReference(task);
			checkIntegrity(suite, dir);
			state = State.INITIALIZED;
		} catch (Exception e) {
			reportError(new RuntimeException("Task " + this
					+ " initialization failed", e));
		}
	}

	private static final Function<Q7ArtifactRef, String> getArtifactId = new Function<Q7ArtifactRef, String>() {
		@Override
		public String apply(Q7ArtifactRef input) {
			return input.getId();
		}
	};

	private static void verifyScenarioReference(Task task) {
		String id = task.getId();
		Q7ArtifactRef scenarioRef = Iterables.getOnlyElement(
				filter(task.getSuite().getRefs(),
						compose(equalTo(id), getArtifactId)), null);
		if (scenarioRef == null)
			throw new IllegalStateException("Scenario reference for task "
					+ task.getId() + " is not found.");
	}

	private Set<Q7ArtifactRef> allDeps(Q7ArtifactRef ref,
			Map<String, Q7ArtifactRef> allContexts) {
		Set<Q7ArtifactRef> result = new HashSet<Q7ArtifactRef>();
		for (String dep : ref.getRefs()) {
			Q7ArtifactRef depRef = EcoreUtil.copy(allContexts.get(dep));
			if (depRef != null) {
				result.add(depRef); // direct dependency
				result.addAll(allDeps(depRef, allContexts)); // transient deps
			} else {
				throw new RuntimeException("Unresolved dependecy to:" + dep);
			}
		}
		return result;
	}

	public String getId() {
		return task.getId();
	}

	public String getSuiteId() {
		return task.getSuiteId();
	}

	/** Completes the task with a error */
	public void reportError(Throwable e2) {
		try {
			Report report = ReportUtil.generateFailedReport(getId(),
					getTaskName(), e2);
			complete(report);
			listeners.onError(this, e2, false);
		} catch (Throwable e) {
			listeners.onError(this, e, true);
		}
	}

	/** Should be called when task is sent to an agent */
	public synchronized void execute(AgentInfo agent) {
		try {
			if (state != State.INITIALIZED)
				throw new IllegalStateException("Task " + getTaskName()
						+ " can't be executed in " + state + " state");
			if (agent == null)
				throw new IllegalArgumentException("Null agent");
			this.agent = agent;
			started = System.currentTimeMillis();
			state = State.EXECUTING;
			listeners.onExecute(this);
		} catch (Throwable e) {
			reportError(e);
		}
	}

	/** Should be called when task is rejected by agent */
	public void cancel(IStatus iStatus) {
		AgentInfo tmp = null;
		synchronized (this) {
			if (state == State.REPORTED)
				return;
			if (state == State.INITIALIZED)
				return;
			if (state != State.EXECUTING)
				throw new IllegalStateException("Task " + getTaskName()
						+ " can't be cancelled in " + state + " state", new CoreException(iStatus));
			started = System.currentTimeMillis();
			state = State.INITIALIZED;
			tmp = getAgent();
			agent = null;
		}
		try {
			assert tmp != null;
			listeners.onCancel(this, tmp, iStatus);
		} catch (Throwable e1) {
			reportError(e1);
		}
	}

	public String getChunkName() {
		Collection<String> scenarios = Collections2.transform(
				ModelUtil.scenarioList(task.getSuite()), Functions.ARTIFACT_ID);

		return String.format("%s[%s]", task.getSuite().getId(), Joiner.on(", ")
				.join(scenarios));
	}

	/** Completes execution of the task */
	public void complete(Report report) {
		Preconditions.checkNotNull(report);
		synchronized (this) {
			if (state == State.REPORTED)
				return;
			state = State.REPORTED;
		}
		try {
			listeners.onComplete(this, report);
		} catch (RuntimeException e) {
			// Inability to report a state of a task should cause suite failure
			listeners.onError(this, new RuntimeException(
					"Unable to send report for " + this, e), true);
		}
	}

	public boolean canExecute(AgentInfo... agents) {
		List<String> list = null;
		if (state == State.REPORTED)
			return false;

		for (AgentInfo agent : agents) {
			String agentId = AgentRegistry.getAgentID(agent);
			if (timeoutAgents != null && timeoutAgents.contains(agentId)) {
				// skip this agent, since task is already timeout on
				// this agent.
				continue;
			}
			if (AgentRegistry.canExecute(getAut(), agent)) {
				EList<String> skipTags = agent.getSkipTags();
				if (!skipTags.isEmpty()) {
					if (list == null) {
						// Check tags ignore
						EObject obj = task.getSuite().getContent();
						if (obj instanceof NamedElement) {
							String tags = ((NamedElement) obj).getTags();
							if (tags != null) {
								list = TagsUtil.extractTags(tags);
							}
						}
						list = new ArrayList<String>();
					}
					if (list != null) {
						if (!TagsUtil.hasAny(skipTags, list)) {
							return true;
						}
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public Long started() {
		return started;
	}

	/**
	 * Load all required resources and creates a Task object
	 * 
	 * @return new {@link com.xored.q7.cloud.server.serverCommands.Task} object
	 * */
	public Task readTask() {
		try {
			if (state != State.EXECUTING)
				throw new IllegalStateException("Task read is meanigless in "
						+ state + " state");
			Task copy = EcoreUtil.copy(task);
			fillTaskWithResources(copy, dir);
			return copy;
		} catch (Exception e) {
			reportError(new RuntimeException(e));
			return null; // reportError always throws, so we can return anything
							// here.
		}
	}

	private static void fillTaskWithResources(Task task, ITestStore store)
			throws CoreException, IOException {
		checkIntegrity(task.getSuite(), store);
		for (Q7ArtifactRef ref : task.getSuite().getRefs()) {
			Q7Artifact resource = store.getResource(ref);
			if (resource == null)
				throw new IllegalStateException("Can't find resource "
						+ ref.getId() + " in " + store);
			task.getArtifacts().add(resource);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof TaskDescriptor) {
			TaskDescriptor other = (TaskDescriptor) obj;

			TestSuite chunk = other.task.getSuite();
			AutInfo aut = other.task.getAut();
			TestOptions options = other.task.getTestOptions();

			return task.getId().equals(other.task.getId())
					&& task.getSuiteId().equals(other.task.getSuiteId())
					&& (chunk != null && equals(chunk.getId(),
							ModelUtil.getAtrifactIds(chunk.getRefs()), aut,
							options));
		}

		return false;
	}

	@Override
	public int hashCode() {
		TestSuite chunk = task.getSuite();
		AutInfo aut = task.getAut();
		int result = chunk != null ? ModelUtil.getAtrifactIds(chunk.getRefs())
				.hashCode() : 0;
		result = result * 31 + task.getId().hashCode();
		result = result * 37 + task.getSuiteId().hashCode();
		result = result * 41 + (chunk != null ? chunk.getId().hashCode() : 1);
		result = result * 43 + AutUtil.getAutKey(aut).hashCode();

		return result;
	}

	public boolean equals(String suiteId, List<String> refs, AutInfo aut,
			TestOptions options) {
		List<String> ids = ModelUtil.getAtrifactIds(task.getSuite().getRefs());

		return task.getSuite().getId().equals(suiteId)
				&& EcoreUtil.equals(task.getTestOptions(), options)
				&& EcoreUtil.equals(task.getAut(), aut) && ids.equals(refs);
	}

	public AutInfo getAut() {
		return task.getAut();
	}

	public String getTaskName() {
		return name;
	}

	public List<String> timeout(AgentInfo agent) {
		if (timeoutAgents == null) {
			timeoutAgents = new ArrayList<String>();
		}
		timeoutAgents.add(AgentRegistry.getAgentID(agent));
		return timeoutAgents;
	}

	public String getTimeoutAgents() {
		if (timeoutAgents != null) {
			return Arrays.toString(timeoutAgents.toArray());
		}
		return "";
	}

	public boolean hashTimeouts() {
		if (timeoutAgents == null) {
			return false;
		}
		return !timeoutAgents.isEmpty();
	}

	private static void checkIntegrity(TestSuite suite, ITestStore store)
			throws CoreException, IOException {
		Map<String, Q7ArtifactRef> map = ModelUtil.refMap(suite);
		for (Q7ArtifactRef ref : map.values()) {
			Q7Artifact artifact = store.getResource(ref);
			if (artifact == null) {
				throw new CoreException(ServerPlugin.createErr(String.format(
						"Cannot find resource %s", ref.getId())));
			}
			checkRefsIncluded(artifact, map);
		}
	}

	private static void checkRefsIncluded(Q7Artifact artifact,
			Map<String, Q7ArtifactRef> refs) throws CoreException {
		for (Q7ArtifactRef ref : artifact.getRefs()) {
			if (!refs.containsKey(ref.getId())) {
				String available = Joiner.on('\n').join(refs.keySet());
				throw new CoreException(ServerPlugin.createErr(String.format(
						"Resource '%s' referenced from '%s' cannot be found. Available references:\n%s",
						ref.getId(), artifact.getId(), available)));
			}
		}
	}

	public AgentInfo getAgent() {
		return agent;
	}

	public boolean isReady() {
		return state == State.INITIALIZED;
	}
	public boolean isCompleted() {
		return state == State.REPORTED;
	}

	public void addListener(Listener listener) {
		listeners.addListener(listener);
	}

	@Override
	public String toString() {
		return getTaskName() + "(" + getId() + ")";
	}

	public void setSuiteId(String suiteId) {
		task.setSuiteId(suiteId);
	}

	public void timeoutCheck() {
		final String testExecTimeout = task.getTestOptions().getValues().get("testExecTimeout");
		final int timeout = (testExecTimeout != null ? Integer.parseInt(testExecTimeout) : 300)*3*1000;
		long elapsed = System.currentTimeMillis() - started;
		if (elapsed > timeout) {
			timeout(agent);
			cancel(error(format("Task execution %s on agent %s has timed out after %f minutes", getTaskName(), agent != null ? agent.getUri() : "null",  (double)elapsed / 60000)));
		}
	}
}
