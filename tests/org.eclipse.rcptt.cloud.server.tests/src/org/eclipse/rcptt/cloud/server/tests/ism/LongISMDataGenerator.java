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

import java.util.List;

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

public class LongISMDataGenerator {
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

	 //@Test
	public void generateExecutionData() throws Throwable {
		TestsSuite suite = TestUtils.createSuite("my_suite_1", 1);
		for (int i = 0; i < 5000; i++) {
			suite.addTestcase(TestCaseState.pass, TestCaseState.wait5sec);
		}
		suite.updateRefs();
		RemoteEclClient client = new RemoteEclClient("localhost", server.port);
		suite.send(client);
		suite.execute(client, null, null, WIN_PL);
	}
}
