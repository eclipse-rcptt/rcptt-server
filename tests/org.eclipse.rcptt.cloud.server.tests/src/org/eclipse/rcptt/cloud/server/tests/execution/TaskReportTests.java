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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;

public class TaskReportTests {
	public final static String WIN_PL = "win32.win32.x86";
	private EclServerApplication server;
	private List<FakeAgentApplication> agents;

	@Test
	public void checkAllTests() throws Throwable {
		final TestsSuite suite = TestUtils.createSuite("my_suite_1",
				300 + 20, TestCaseState.pass/* , TestCaseState.wait5sec */);
		suite.addTestcase(TestCaseState.failtoload);
		// suite.addTestcase(TestCaseState.agentfailtoload);
		suite.updateRefs();
		suite.send(server);
		final Set<String> executed = new HashSet<String>();
		suite.execute(new TestUtils.WaitListener() {

			public void executed(ProcessStatus resultStatus, String id) {
				synchronized (executed) {
					if (!executed.add(id)) {
						System.out.println("duplicate test:" + id);
					}
					System.out.println("executed:" + executed.size()
							+ " from: " + suite.artifacts.size());
				}
			}

			public void done() {
			}
		}, WIN_PL);
		assertEquals(321, suite.processed);
		assertEquals(1, suite.failed);
		// TestCase.assertEquals(0, TestUtils.errCount);
	}

	@After
	public void tearDown() {
		server.stop();

		for (FakeAgentApplication fakeAgentApplication : agents) {
			fakeAgentApplication.stop();
		}
	}

	@Before
	public void setUp() throws Exception {
		server = TestUtils.setupServer();
		agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 5));
	}
}
