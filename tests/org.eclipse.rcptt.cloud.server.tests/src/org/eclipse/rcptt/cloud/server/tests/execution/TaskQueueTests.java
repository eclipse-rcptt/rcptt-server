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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.model.RefKind;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskSuiteDescriptor;
import org.eclipse.rcptt.cloud.server.tests.FakeTestStore;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.reporting.util.RcpttReportGenerator;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.verifications.log.ErrorLogVerification;
import org.eclipse.rcptt.verifications.log.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaskQueueTests extends BaseTaskQueueTests {
	@BeforeClass
	public static void classSetUp() {
		Q7TestingHelper.TESTING = true;
	}

	@AfterClass
	public static void classtearDown() {
		Q7TestingHelper.TESTING = false;
	}

	@Test
	public void testSheduling01_mustEqual() throws Throwable {
		AgentRegistry r = setupAgentRegistry(18);

		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 1000);

		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 1000);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(9, d1.getAgentCount());
		Assert.assertEquals(9, d2.getAgentCount());

	}

	@Test
	public void testScheduling02_mustGreater() throws Throwable {
		AgentRegistry r = setupAgentRegistry(20);

		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 1000);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 500);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(13, d1.getAgentCount());
		Assert.assertEquals(7, d2.getAgentCount());

	}

	// @Test
	public void testScheduling03() throws Throwable {
		AgentRegistry r = setupAgentRegistry(50);

		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 1000);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 500);
		TaskSuiteDescriptor d3 = createTaskSuiteDescriptor("s3", 50);
		TaskSuiteDescriptor d4 = createTaskSuiteDescriptor("s4", 1000);
		TaskSuiteDescriptor d5 = createTaskSuiteDescriptor("s5", 700);

		queue.schedule(d1, d2, d3, d4, d5);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(14, d1.getAgentCount());
		Assert.assertEquals(8, d2.getAgentCount());
		Assert.assertEquals(2, d3.getAgentCount());
		Assert.assertEquals(15, d4.getAgentCount());
		Assert.assertEquals(11, d5.getAgentCount());
	}

	@Test
	public void testScheduling04() throws Throwable {
		AgentRegistry r = setupAgentRegistry(2);

		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 1000);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 500);
		TaskSuiteDescriptor d3 = createTaskSuiteDescriptor("s3", 50);
		TaskSuiteDescriptor d4 = createTaskSuiteDescriptor("s4", 1000);
		TaskSuiteDescriptor d5 = createTaskSuiteDescriptor("s5", 700);

		queue.schedule(d1, d2, d3, d4, d5);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(1, d1.getAgentCount());
		Assert.assertEquals(0, d2.getAgentCount());
		Assert.assertEquals(0, d3.getAgentCount());
		Assert.assertEquals(1, d4.getAgentCount());
		Assert.assertEquals(0, d5.getAgentCount());
	}

	@Test
	public void testSheduling05() throws Throwable {
		AgentRegistry r = setupAgentRegistry(5);
		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 100);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 100);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(2, d1.getAgentCount());
		Assert.assertEquals(3, d2.getAgentCount());
	}

	@Test
	public void testInitialization() throws CoreException {
		TestsSuite suite = TestUtils.createSuite("Initialization failure", 1,
				TestCaseState.pass);
		suite.addTestcase(TestCaseState.failtoload);
		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor(suite);
		Assert.assertEquals(1, d1.getTaskCount());
		Assert.assertEquals(1, d1.getFailedTaskCount());

	}

	@Test
	public void testVerifications() throws CoreException {
		TestsSuite suite = TestUtils.createSuite("Initialization failure", 1,
				TestCaseState.pass);
		final String verificationId = suite.testSuiteName + "verification_0"; 
		Q7ArtifactRef testRef = suite.refs.iterator().next();
		Q7ArtifactRef verRef = ModelFactory.eINSTANCE.createQ7ArtifactRef();
		verRef.setId(verificationId);
		verRef.setKind(RefKind.VERIFICATION);
		testRef.getRefs().add(verificationId);
		suite.artifacts.values().iterator().next().getRefs()
				.add(EcoreUtil.copy(verRef));
		suite.refs.add(EcoreUtil.copy(verRef));

		ErrorLogVerification verification = LogFactory.eINSTANCE.createErrorLogVerification();
		verification.setId(verificationId);
		
		Q7Artifact vartifact = ModelFactory.eINSTANCE.createQ7Artifact();
		vartifact.setContent(verification);
		vartifact.setId(verificationId);
		suite.artifacts.put(verificationId, vartifact);
		suite.updateRefs();
		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor(suite);
		Assert.assertEquals(1, d1.getTaskCount());
		Assert.assertEquals(0, d1.getFailedTaskCount());
	}
	
	@Test
	public void doNotRetryTaskOnTheSameAgent() throws CoreException {
		AgentRegistry r = setupAgentRegistry(5);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue subject = new TaskQueue(r, consoleLog, consoleLog);
		TestsSuite suite = TestUtils.createSuite("Initialization failure", 1,
				TestCaseState.pass);
		TaskSuiteDescriptor suiteDescriptor = createTaskSuiteDescriptor(suite);
		subject.schedule(suiteDescriptor);
		TaskDescriptor task1 = subject.get(agents.get(0), suiteDescriptor.getSuiteId());
		subject.reportProblem(agents.get(0), Status.error("Could not start AUT"));
		assertNull(subject.get(agents.get(0), suiteDescriptor.getSuiteId()));
		TaskDescriptor task2 = subject.get(agents.get(1), suiteDescriptor.getSuiteId());
		assertEquals(task1, task2);
	}
	
	@Test
	public void noExceptionsWhenRemovingTimedoutTasks() throws CoreException, IOException, InterruptedException {
		AgentRegistry r = setupAgentRegistry(1);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue subject = new TaskQueue(r, consoleLog, consoleLog);
		TestsSuite suite = TestUtils.createSuite("Initialization failure", 1,
				TestCaseState.wait20sec);
		FakeTestStore dir = new FakeTestStore(suite);
		TestOptions testOptions = ModelFactory.eINSTANCE.createTestOptions();
		testOptions.getValues().put("testExecTimeout", "0");
		TaskDescriptor task = new TaskDescriptor(dir, aut, testOptions, suite.refs.get(0), "taskName", resolver);
		AtomicReference<Report> report = new AtomicReference<>();
		task.addListener(new TaskDescriptor.Listener.Adapter() {
			@Override
			public void onComplete(TaskDescriptor taskDescriptor, AgentInfo agent, Report r) {
				super.onComplete(taskDescriptor, agent, r);
				report.compareAndSet(null, r);
			}
		});
		Collection<TaskDescriptor> tasks = Collections.singletonList(task);
		TaskSuiteDescriptor suiteDescriptor = new TaskSuiteDescriptor(suite.testSuiteName, aut, consoleLog, 1, 1, tasks);
		Map<String, Q7ArtifactRef> contexts = ModelUtil.dependenciesMap(dir.getTestSuite());
		suiteDescriptor.initialize(contexts, null);
		subject.schedule(suiteDescriptor);
		subject.get(agents.get(0), suiteDescriptor.getSuiteId());
		Assert.assertEquals(1, suiteDescriptor.getRunningTasksCount());
		suiteDescriptor.checkTimeout();
		 Assert.assertEquals(0, suiteDescriptor.getRunningTasksCount());
		 while (report.get() == null) {
			 Thread.yield();
		 }
		ProcessStatus status = ReportUtils.getStatus(report.get());
		StringWriter writer = new StringWriter();
		RcpttReportGenerator.writeResult(writer, 0, status);
		assertTrue(writer.toString(), writer.toString().contains("No compatible agents"));
		assertTrue(writer.toString(), writer.toString().contains("has timed out"));
	}
		
}
