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

import junit.framework.Assert;

import org.junit.Test;

import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.EclServerImplPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue.ITaskListener;
import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;

public class TimeoutTests {
	public final static String WIN_PL = "win32.win32.x86";

	@Test
	public void execution1() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 10);
			suite.addTestcase(TestCaseState.pass, TestCaseState.timeout);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(11, suite.processed);
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	//@Test
	public void onServerTimeoutTest001() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		final List<FakeAgentApplication> agents = TestUtils
				.setupAgents(AgentInfo.agent(WIN_PL, 1));
		for (FakeAgentApplication fakeAgentApplication : agents) {
			fakeAgentApplication.setFetchCount(0);
			fakeAgentApplication.setWaitOnCounter(true);
		}
		long timeout = AgentRegistry.getTimeout();
		try {
			AgentRegistry.setTimeout(1000);// Set timeout to 1 seconds.
			AgentRegistry.setMonitorTimeout(100); // Each second
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 10);

			suite.addTestcase(TestCaseState.pass, TestCaseState.timeout);
			suite.addTestcase(TestCaseState.pass, TestCaseState.waitonCounter);

			EclServerImplPlugin.getDefault().getTaskQueue()
			.addTeskListener(new ITaskListener() {

				public void timeoutHappen(
						org.eclipse.rcptt.cloud.model.AgentInfo agent) {
					for (FakeAgentApplication fakeAgentApplication : agents) {
						fakeAgentApplication.setWaitOnCounter(false);
					}
				}
			});
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(12, suite.processed);
			Assert.assertEquals(10, suite.failed);
		} finally {
			// Restore timeout
			AgentRegistry.setTimeout(timeout);
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

}
