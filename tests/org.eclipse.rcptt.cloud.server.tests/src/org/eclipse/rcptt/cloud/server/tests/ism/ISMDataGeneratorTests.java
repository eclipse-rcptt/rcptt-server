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
package org.eclipse.rcptt.cloud.server.tests.ism;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;
import org.eclipse.rcptt.cloud.util.RemoteEclClient;

public class ISMDataGeneratorTests {
	public final static String WIN_PL = "win32.win32.x86";
	private EclServerApplication server;
	private List<FakeAgentApplication> agents;

	@Before
	public void setUp() throws Exception {
		server = TestUtils.setupServer();
		agents = TestUtils.setupAgents(AgentInfo.agent(WIN_PL, 1));
	}

	@After
	public void tearDown() {
		server.stop();
		for (FakeAgentApplication agent : agents) {
			agent.stop();
		}
	}

	@Test
	public void generateExecutionData() throws Throwable {
		for (int i = 0; i < 15; i++) {
			TestsSuite suite = TestUtils.createSuite("my_suite_1", 10);
			RemoteEclClient client = new RemoteEclClient("localhost",
					server.port);
			suite.send(client);
			suite.execute(client, null, null, WIN_PL);
			assertEquals(10, suite.processed);
			assertEquals(0, suite.failed);
		}
	}

	@Test
	public void generateExecutionData2() throws Throwable {
		Random r = new Random();
		for (int j = 0; j < 7; j++) {
			int successes = r.nextInt(20) + 10;
			TestsSuite suite = TestUtils.createSuite("my_suite_2",
					successes);
			int fails = r.nextInt(50);
			for (int i = 0; i < fails; i++) {
				suite.addTestcase(TestCaseState.fail);
			}
			RemoteEclClient client = new RemoteEclClient("localhost",
					server.port);
			suite.send(client);
			suite.execute(client, null, null, WIN_PL);
			assertEquals(successes, suite.passed);
			assertEquals(fails, suite.failed);
		}
	}
}
