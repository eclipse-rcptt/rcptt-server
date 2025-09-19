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
package org.eclipse.rcptt.cloud.server.tests.execution;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.RefKind;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskSuiteDescriptor;
import org.eclipse.rcptt.cloud.server.tests.FakeTestStore;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;

public class BaseTaskQueueTests {

	private static final String CLASSIFIER = "win32";
	protected ConsoleQ7Monitor consoleLog = new ConsoleQ7Monitor();
	AutInfo aut = createAut();

	public BaseTaskQueueTests() {
		super();
	}

	public static Report generateOkReport(TaskDescriptor task) {
		return ReportUtil.generateReport(task.getId(), task.getTaskName(), RcpttPlugin.createProcessStatus(IStatus.OK, "Success"));
	}

	protected TaskSuiteDescriptor createTaskSuiteDescriptor(String name, int count) {
		try {
			TestsSuite testSuite = TestUtils.createSuite(name, count);
			TaskSuiteDescriptor d1 = createTaskSuiteDescriptor(testSuite);
			return d1;
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	protected TaskSuiteDescriptor createTaskSuiteDescriptor(String name, int count, int minTestsPerAgent) {
		try {
			TestsSuite testSuite = TestUtils.createSuite(name, count);
			testSuite.minTestsPerAgent = minTestsPerAgent;
			TaskSuiteDescriptor d1 = createTaskSuiteDescriptor(testSuite);
			return d1;
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	public TaskSuiteDescriptor createTaskSuiteDescriptor(TestsSuite testSuite) {
		TaskSuiteDescriptor d1 = new TaskSuiteDescriptor(testSuite.testSuiteName, aut,
				consoleLog, testSuite.minTestsPerAgent, testSuite.maxAgents, createTasks(aut, testSuite));
		FakeTestStore dir = new FakeTestStore(testSuite);
		Map<String, Q7ArtifactRef> contexts = ModelUtil.dependenciesMap(dir
				.getTestSuite());
		d1.initialize(contexts, null);
		return d1;
	}

	protected AgentRegistry setupAgentRegistry(int count) {
		AgentRegistry r = new AgentRegistry();
		List<AgentInfo> agents = createAgents(count);

		for (AgentInfo agentInfo : agents) {
			r.addAgent(agentInfo);
		}
		return r;
	}

	private List<AgentInfo> createAgents(int count) {
		List<AgentInfo> result = new ArrayList<AgentInfo>();
		for (int i = 0; i < count; i++) {
			result.add(createAgent("agent_" + Integer.toString(i), CLASSIFIER));
		}
		return result;
	}

	private AutInfo createAut() {
		AutInfo aut = ModelFactory.eINSTANCE.createAutInfo();
		aut.setClassifier(CLASSIFIER);
		aut.setUri("a.com");
		aut.setId("aut1");
		aut.setHash(aut.getId().getBytes());
		return aut;
	}

	private Collection<TaskDescriptor> createTasks(AutInfo aut,
			TestsSuite testSuite) {
		try {
			TestsSuite suite = testSuite;
			FakeTestStore store = new FakeTestStore(suite);
			List<TaskDescriptor> rv = newArrayList();
			for (Q7ArtifactRef artifactRef : testSuite.refs) {
				if (artifactRef.getKind() != RefKind.SCENARIO)
					continue;
				TaskDescriptor task = new TaskDescriptor(store, aut, null,
						artifactRef, "taskName");
				rv.add(task);
			}
			return rv;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private AgentInfo createAgent(String name, String classifier) {
		AgentInfo a1 = ModelFactory.eINSTANCE.createAgentInfo();
		a1.setClassifier(classifier);
		a1.setUri(name);
		a1.setLaunchId(Long.toString(System.currentTimeMillis()));
		return a1;
	}
}
