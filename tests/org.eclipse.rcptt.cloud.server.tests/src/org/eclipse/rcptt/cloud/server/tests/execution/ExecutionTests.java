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

import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.junit.Assert;
import org.junit.Test;

import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.WaitListener;
import org.eclipse.rcptt.cloud.util.RemoteEclClient;

@SuppressWarnings("static-method")
public class ExecutionTests {
	public final static String WIN_PL = "win32.win32.x86";

	@Test
	public void execution1() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 10);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(10, suite.processed);
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void testCancel() throws Throwable {
		final EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			final int total = 1000;
			final TestsSuite suite = TestUtils.createSuite("my_suite_1", total);
			suite.send(server);
			suite.execute(new WaitListener() {

				@Override
				public void executed(ProcessStatus resultStatus, String string) {
					if (suite.processed > 2) {
						try (RemoteEclClient client = TestUtils.TestsSuite.createClient(server)) {
							suite.cancel(client);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							throw new AssertionError(e);
						}
					} else {
						Assert.assertEquals(0, suite.getCancelledCount());
					}
						
				}

				@Override
				public void done() {
					Assert.assertNotEquals(0, suite.processed);
					Assert.assertNotEquals(0, suite.passed);
					Assert.assertNotEquals(total, suite.processed);
					Assert.assertTrue("Cancelled count: " + suite.getCancelledCount(),  suite.getCancelledCount() > 0);
				}
			}, WIN_PL);
			Assert.assertNotEquals(total, suite.passed);
			Assert.assertNotEquals(0, suite.passed);
			Assert.assertNotEquals(0, suite.processed);
			Assert.assertNotEquals(total, suite.processed);
		} finally {
			server.stop();
			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	// @Test
	public void executionSameSuite() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			for (int i = 0; i < 10; i++) {
				TestsSuite suite = TestUtils.createSuite("my_suite_1", 10);
				suite.send(server);

				suite.execute(WIN_PL);
				Assert.assertEquals(10, suite.processed);
				// TestCase.assertEquals(0, TestUtils.errCount);
				Thread.sleep(500);
			}
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void execution2() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 2));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 20);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(20, suite.processed);
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void execution3() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 1);
			suite.addTestcase(TestCaseState.failtoload);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(2, suite.processed);
			Assert.assertEquals(1, suite.failed);
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void execution4() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 1);
			suite.addTestcase(TestCaseState.autfailure_deploy);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(2, suite.processed);
			Assert.assertEquals(1, suite.failed);
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void execution5() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 1);
			suite.addTestcase(TestCaseState.autfailure_start);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(2, suite.processed);
			Assert.assertEquals(0, suite.skipped);
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void execution6() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 1);
			suite.addTestcase(TestCaseState.timeout);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(2, suite.processed);
			Assert.assertEquals(1, suite.failed);
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void executionWithAutFailureStartOnly() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo.agent(WIN_PL, 1));
		try {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 2, TestCaseState.autfailure_start);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(2, suite.processed);
			Assert.assertEquals(0, suite.skipped);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

}
