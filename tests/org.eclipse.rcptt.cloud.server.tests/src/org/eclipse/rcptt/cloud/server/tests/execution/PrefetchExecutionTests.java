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

import java.net.UnknownHostException;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.server.tests.FakeAgentApplication;
import org.eclipse.rcptt.cloud.server.tests.TestUtils;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.AgentInfo;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;

public class PrefetchExecutionTests {
	public final static String WIN_PL = "win32.win32.x86";

	@Test
	public void execution1() throws Throwable {
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 1));
		try {
			final TestsSuite suite = TestUtils.createSuite("my_suite_1", 100);
			suite.send(server);

			agents.get(0).setStopOnRun(true);
			agents.get(0).setFetchCount(2);
			Thread thread = new Thread(new Runnable() {

				public void run() {
					try {
						suite.execute(WIN_PL);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();
			Thread.sleep(6000);
			// assert there is appropriate count of pre fetched tests
			Assert.assertEquals(2, agents.get(0).getPreFetch());
			agents.get(0).setStopOnRun(false);

			thread.join();

			Assert.assertEquals(100, suite.processed);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
	}

	// @Test
	public void execution_2fetch() throws Throwable {
		for (int i = 0; i < 10; i++) {
			long time1 = doExec(0);
			long time2 = doExec(1);
			long time3 = doExec(2);
			long time4 = doExec(3);
			System.out.println(i + "time0: " + time1);
			System.out.println(i + "time1: " + time2);
			System.out.println(i + "time2: " + time3);
			System.out.println(i + "time3: " + time4);
		}
	}

	private long doExec(int fetch) throws Exception, CoreException,
	UnknownHostException, InterruptedException {
		System.out.println("exec with: " + fetch);
		long time1 = System.currentTimeMillis();
		EclServerApplication server = TestUtils.setupServer();
		List<FakeAgentApplication> agents = TestUtils.setupAgents(AgentInfo
				.agent(WIN_PL, 2));
		for (FakeAgentApplication fakeAgentApplication : agents) {
			fakeAgentApplication.setFetchCount(fetch);
		}
		try {
			final TestsSuite suite = TestUtils.createSuite("my_suite_1", 100);
			suite.send(server);

			suite.execute(WIN_PL);
			Assert.assertEquals(100, suite.processed);
		} finally {
			server.stop();

			for (FakeAgentApplication fakeAgentApplication : agents) {
				fakeAgentApplication.stop();
			}
		}
		return System.currentTimeMillis() - time1;
	}
}
