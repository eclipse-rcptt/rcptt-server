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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;

public class ISMAgents {
	public final static String WIN_PL = "win32.win32.x86";

	// @Test
	// This test just hangs and is absent in maven build.
	// Disabling it for convinience of running all tests in plugin.
	// Vasili Gulevich
	public void generateExecutionData() throws Throwable {
		List<FakeAgentApplication> setupAgents = setupAgents("localhost", 7005,
				AgentInfo.agent(WIN_PL + "#", 5));
		while (1 + 1 == 2) {
			Thread.sleep(5000);
		}

	}

	public static List<FakeAgentApplication> setupAgents(String host, int port,
			AgentInfo... agents) throws Exception {
		List<FakeAgentApplication> apps = new LinkedList<FakeAgentApplication>();
		for (final AgentInfo agentInfo : agents) {
			final boolean noAction = (agentInfo.platform != null && agentInfo.platform
					.endsWith("#"));
			if (noAction) {
				agentInfo.platform = agentInfo.platform.substring(0,
						agentInfo.platform.length() - 1);
			}
			FakeAgentApplication ag = new FakeAgentApplication() {

				@Override
				protected void joinEclServer() {
				}


				@Override
				protected void doCleanupPDE() {
				}

				@Override
				public int getWaitTicks() {
					return 0;
				}
			};
			ag.serverHost = host;
			ag.serverPort = port;
			ag.thisHost = "localhost";
			ag.port = TestUtils.findFreePort();
			ag.agentCount = agentInfo.count;
			ag.classifier = agentInfo.platform;
			ag.finder = new FakeAgentApplication.IPortFinder() {

				public int findPort() {
					return TestUtils.findFreePort();
				}
			};
			ag.run();

		}
		return apps;
	}

}
