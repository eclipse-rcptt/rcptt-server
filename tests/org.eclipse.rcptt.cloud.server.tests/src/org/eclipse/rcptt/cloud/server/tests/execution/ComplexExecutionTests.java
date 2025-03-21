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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;

public class ComplexExecutionTests {
	public final static String WIN_PL = "win32.win32.x86";

	@Test
	public void executionOn3AgentPlus1Failures() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 3));
		try {
			TestsSuite suite = TestUtils
					.createSuite("my_suite_1", 10);
			suite.addTestcase(TestCaseState.failtoload);
			// suite.addTestcase(TestCaseState.agentfailtoload);
			suite.updateRefs();
			suite.send(server);

			suite.execute(WIN_PL);
			assertEquals(11, suite.processed);
			assertEquals(1, suite.failed);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	@Test
	public void parallelExecution() throws Throwable {
		final EclServerApplication server = TestUtils.setupServer();

		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 14));
		try {
			final List<Throwable> asserts = new ArrayList<Throwable>();
			ExecutorService executor = Executors.newFixedThreadPool(10);
			for (int i = 0; i < 5; i++) {
				final int ii = i;
				executor.execute(new Runnable() {

					public void run() {
						System.out.println("execution:" + ii);
						TestsSuite suite;
						final int num = 30;
						try {
							suite = TestUtils.createSuite("my_suite__" + ii
									+ "_A", num);
							suite.addTestcase(TestCaseState.failtoload);
							suite.updateRefs();
							suite.send(server);

							suite.execute(WIN_PL);
							assertEquals(num + 1, suite.processed);
							assertEquals(num, suite.passed);
							assertEquals(1, suite.failed);
						} catch (Throwable e) {
							asserts.add(e);
						}
					}
				});
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
				Thread.sleep(500);
			}
			if (!asserts.isEmpty()) {
				throw asserts.get(0);
			}
			// TestCase.assertEquals(0, TestUtils.errCount);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}
}
