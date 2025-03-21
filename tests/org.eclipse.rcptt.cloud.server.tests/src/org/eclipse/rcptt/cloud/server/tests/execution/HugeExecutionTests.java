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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.Assert;

import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;

public class HugeExecutionTests {
	public final static String WIN_PL = "win32.win32.x86";

	// @Test
	public void hugeParallelExecution() throws Throwable {
		final EclServerApplication server = TestUtils.setupServer();
		try {
			final Random r = new Random();
			for (int j = 0; j < 1; j++) {
				int agentCount = r.nextInt(15) + 15;
				int executorsCount = r.nextInt(5) + 3;
				List<FakeAgentApplication> agents = TestUtils
						.setupAgents(AgentInfo.agent(WIN_PL, agentCount));
				try {
					final List<Throwable> asserts = new ArrayList<Throwable>();
					ExecutorService executor = Executors.newFixedThreadPool(10);
					for (int i = 0; i < executorsCount; i++) {
						final int ii = i;
						executor.execute(new Runnable() {

							public void run() {
								System.out.println("execution:" + ii);
								TestsSuite suite;
								try {
									int ct = 1300 + r.nextInt(100);
									suite = TestUtils.createSuite("my_suite__"
											+ ii + "_A", ct);
									// suite.addTestcase(TestCaseState.failtoload);
									// suite.addTestcase(TestCaseState.timeout);
									suite.updateRefs();
									suite.send(server);

									suite.execute(WIN_PL);
									Assert.assertEquals(ct /* + 2 */,
											suite.processed);
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
					// server.stop();

					for (FakeAgentApplication fakeAgentApplication : agents) {
						fakeAgentApplication.stop();
					}
				}
			}
		} finally {
			server.stop();
		}
	}
}
