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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskSuiteDescriptor;

public class TaskQueueExecutionTests extends BaseTaskQueueTests {
	@Test
	public void testExecution01() throws Throwable {
		AgentRegistry r = setupAgentRegistry(1);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 1000);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 1000);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(1, d1.getAgentCount());
		Assert.assertEquals(0, d2.getAgentCount());

		TaskDescriptor descriptor = queue.get(agents.get(0), null);
		Assert.assertEquals(1, d1.getRunningTasksCount());

		queue.complete(agents.get(0), descriptor.getSuiteId(),
				descriptor.getId(), generateOkReport(descriptor));
		Assert.assertEquals(0, d1.getRunningTasksCount());
		Assert.assertEquals(999, d1.getTaskCount());
		for (int i = 0; i < 998; i++) {
			descriptor = queue.get(agents.get(0), null);
			Assert.assertEquals(1, d1.getRunningTasksCount());

			queue.complete(agents.get(0), descriptor.getSuiteId(),
					descriptor.getId(), generateOkReport(descriptor));
		}
		System.out.println("state:\n" + queue.toString());
		descriptor = queue.get(agents.get(0), null);
		System.out.println("state:\n" + queue.toString());
		Assert.assertEquals(1, d1.getRunningTasksCount());
		queue.complete(agents.get(0), descriptor.getSuiteId(),
				descriptor.getId(), generateOkReport(descriptor));
		System.out.println("state:\n" + queue.toString());

		// Begin execution of second suite
		System.out.println("state:\n" + queue.toString());
		descriptor = queue.get(agents.get(0), null);
		System.out.println("state:\n" + queue.toString());
		Assert.assertEquals(1, d2.getRunningTasksCount());
		queue.complete(agents.get(0), descriptor.getSuiteId(), descriptor.getId(), generateOkReport(descriptor));
		System.out.println("state:\n" + queue.toString());
		for (int i = 0; i < 999; i++) {
			descriptor = queue.get(agents.get(0), null);
			Assert.assertEquals(1, d2.getRunningTasksCount());

			queue.complete(agents.get(0), descriptor.getSuiteId(),
					descriptor.getId(), generateOkReport(descriptor));
		}
		System.out.println("state:\n" + queue.toString());
		Assert.assertEquals(0, d1.getTaskCount());
		Assert.assertEquals(0, d2.getTaskCount());
		Assert.assertEquals(0, d1.getFailedTaskCount());
		Assert.assertEquals(0, d2.getFailedTaskCount());
	}

	@Test
	public void testExecution02() throws Throwable {
		AgentRegistry r = setupAgentRegistry(2);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 1000);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 1000);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(1, d1.getAgentCount());
		Assert.assertEquals(1, d2.getAgentCount());

		// Check each suite has executor assigned
		TaskDescriptor descriptor = queue.get(agents.get(0), null);
		TaskDescriptor descriptor2 = queue.get(agents.get(1), null);

		Assert.assertEquals(1, d1.getRunningTasksCount());
		Assert.assertEquals(1, d2.getRunningTasksCount());

		queue.complete(agents.get(0), descriptor.getSuiteId(),
				descriptor.getId(), generateOkReport(descriptor));

		queue.complete(agents.get(1), descriptor2.getSuiteId(),
				descriptor2.getId(), generateOkReport(descriptor2));

		for (int i = 0; i < 500; i++) {
			descriptor = queue.get(agents.get(0), null);
			queue.complete(agents.get(0), descriptor.getSuiteId(),
					descriptor.getId(), generateOkReport(descriptor));
			descriptor2 = queue.get(agents.get(1), null);
			queue.complete(agents.get(1), descriptor2.getSuiteId(),
					descriptor2.getId(), generateOkReport(descriptor));
		}
		Assert.assertEquals(499, d1.getTaskCount());
		Assert.assertEquals(499, d2.getTaskCount());
		Assert.assertEquals(0, d1.getFailedTaskCount());
		Assert.assertEquals(0, d2.getFailedTaskCount());
	}

	@Test
	public void testExecution03() throws Throwable {
		AgentRegistry r = setupAgentRegistry(5);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 13, 7);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 14, 7);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(2, d1.getAgentCount());
		Assert.assertEquals(2, d2.getAgentCount());

		TaskDescriptor descriptor = queue.get(agents.get(0), null);
		TaskDescriptor descriptor1 = queue.get(agents.get(1), null);
		TaskDescriptor descriptor2 = queue.get(agents.get(2), null);
		TaskDescriptor descriptor3 = queue.get(agents.get(3), null);
		TaskDescriptor descriptor4 = queue.get(agents.get(4), null);
		Assert.assertNotNull(descriptor);
		Assert.assertNotNull(descriptor1);
		Assert.assertNotNull(descriptor2);
		Assert.assertNotNull(descriptor3);
		Assert.assertNull(descriptor4);

		System.out.println("status:\n" + queue.toString());
		Assert.assertEquals(0, d1.getFailedTaskCount());
		Assert.assertEquals(0, d2.getFailedTaskCount());

	}

	@Test
	public void testExecution04() throws Throwable {
		AgentRegistry r = setupAgentRegistry(3);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 10, 7);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 10, 7);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(2, d1.getAgentCount());
		Assert.assertEquals(1, d2.getAgentCount());

		String lastSuite = null;
		for (int i = 0; i < 10; i++) {
			TaskDescriptor descriptor = queue.get(agents.get(0), lastSuite);
			lastSuite = descriptor.getSuiteId();
			queue.complete(agents.get(0), lastSuite, descriptor.getId(), generateOkReport(descriptor));
		}
		TaskDescriptor descriptor = queue.get(agents.get(0), lastSuite);
		queue.complete(agents.get(0), descriptor.getSuiteId(),
				descriptor.getId(), generateOkReport(descriptor));

		System.out.println("status:\n" + queue.toString());
		Assert.assertEquals(0, d1.getTaskCount());
		Assert.assertEquals(9, d2.getTaskCount());
		Assert.assertEquals(0, d1.getFailedTaskCount());
		Assert.assertEquals(0, d2.getFailedTaskCount());
	}

	@Test
	public void testExecution06() throws Throwable {
		AgentRegistry r = setupAgentRegistry(10);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 110);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 220);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(3, d1.getAgentCount());
		Assert.assertEquals(5, d2.getAgentCount());

		String lastSuite = null;
		for (int i = 0; i < 10; i++) {
			TaskDescriptor descriptor = queue.get(agents.get(0), lastSuite);
			lastSuite = descriptor.getSuiteId();
			queue.complete(agents.get(0), lastSuite, descriptor.getId(), generateOkReport(descriptor));
		}
		TaskDescriptor descriptor = queue.get(agents.get(0), lastSuite);
		queue.complete(agents.get(0), descriptor.getSuiteId(),
				descriptor.getId(), generateOkReport(descriptor));

		System.out.println("status:\n" + queue.toString());
		Assert.assertEquals(99, d1.getTaskCount());
		Assert.assertEquals(220, d2.getTaskCount());
		Assert.assertEquals(0, d1.getFailedTaskCount());
		Assert.assertEquals(0, d2.getFailedTaskCount());
	}

	@Test
	public void testExecution07() throws Throwable {
		AgentRegistry r = setupAgentRegistry(10);
		List<AgentInfo> agents = r.getAgents();
		TaskQueue queue = new TaskQueue(r, consoleLog, consoleLog);

		TaskSuiteDescriptor d1 = createTaskSuiteDescriptor("s1", 160);
		TaskSuiteDescriptor d2 = createTaskSuiteDescriptor("s2", 220);

		queue.schedule(d1, d2);

		System.out
		.println("Task suite agent association:\n" + queue.toString());
		Assert.assertEquals(4, d1.getAgentCount());
		Assert.assertEquals(5, d2.getAgentCount());

		TaskDescriptor[] descriptor1 = {
				queue.get(agents.get(0), d1.getSuiteId()),
				queue.get(agents.get(0), d1.getSuiteId()),
				queue.get(agents.get(0), d1.getSuiteId()),
				queue.get(agents.get(0), d1.getSuiteId()),
				queue.get(agents.get(0), d1.getSuiteId()) };

		TaskDescriptor[] descriptor2 = {
				queue.get(agents.get(1), d1.getSuiteId()),
				queue.get(agents.get(1), d1.getSuiteId()),
				queue.get(agents.get(1), d1.getSuiteId()),
				queue.get(agents.get(1), d1.getSuiteId()),
				queue.get(agents.get(1), d1.getSuiteId()) };
		TaskDescriptor[] descriptor3 = {
				queue.get(agents.get(2), d1.getSuiteId()),
				queue.get(agents.get(2), d1.getSuiteId()),
				queue.get(agents.get(2), d1.getSuiteId()),
				queue.get(agents.get(2), d1.getSuiteId()),
				queue.get(agents.get(2), d1.getSuiteId()) };

		TaskDescriptor[] descriptor4 = {
				queue.get(agents.get(3), d2.getSuiteId()),
				queue.get(agents.get(4), d2.getSuiteId()),
				queue.get(agents.get(5), d2.getSuiteId()),
				queue.get(agents.get(6), d2.getSuiteId()),
				queue.get(agents.get(7), d2.getSuiteId()),
				queue.get(agents.get(8), d2.getSuiteId()),
				queue.get(agents.get(9), d2.getSuiteId()) };

		Assert.assertEquals(5 + 5 + 5, d1.getRunningTasksCount());
		Assert.assertEquals(5, d2.getRunningTasksCount());
		d1.setAgentsCount(2);
		d1.reduceExtraAgents();
		Assert.assertEquals(5 + 5, d1.getRunningTasksCount());
		Assert.assertEquals(0, d1.getFailedTaskCount());
		Assert.assertEquals(0, d2.getFailedTaskCount());

	}
}
