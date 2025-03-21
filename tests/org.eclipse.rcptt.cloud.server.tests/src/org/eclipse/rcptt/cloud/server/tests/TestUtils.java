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
package org.eclipse.rcptt.cloud.server.tests;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.rcptt.core.Scenarios;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.core.scenario.ScenarioFactory;
import org.eclipse.rcptt.ecl.core.BoxedValue;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.runtime.BoxedValues;
import org.eclipse.rcptt.ecl.server.tcp.EclTcpServerManager;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;

import org.eclipse.rcptt.cloud.agent.app.internal.AgentThread;
import org.eclipse.rcptt.cloud.client.ClientAppPlugin;
import org.eclipse.rcptt.cloud.common.CommonPlugin;
import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsFactory;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.Envelope;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Options;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.model.RefKind;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.app.internal.ServerApplication;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.EclServerImplPlugin;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;
import org.eclipse.rcptt.cloud.util.EmfResourceUtil;
import org.eclipse.rcptt.cloud.util.RemoteEclClient;
import org.eclipse.rcptt.cloud.util.RemoteEclClient.ExecResult;

public class TestUtils {
	private static List<Integer> usedPorts = new ArrayList<Integer>();
	static {
		usedPorts.add(10000);
	}
	private static ServerApplication server = null;

	public interface WaitListener {
		void executed(ProcessStatus resultStatus, String string);

		void done();
	}

	public static class AgentInfo {
		public int count;
		public String platform;

		public AgentInfo(int count, String platform) {
			super();
			this.count = count;
			this.platform = platform;
		}

		public static AgentInfo agent(String platform, int count) {
			return new AgentInfo(count, platform);
		}
	}

	public static EclServerApplication setupServer() throws Exception {
		Q7TestingHelper.TESTING = true;

		if (server != null) {
			server.stop();
			EclTcpServerManager.Instance.terminateAll();
			server = null;
		}
		ServerPlugin.getDefault().reinit();
		EclServerImplPlugin.getDefault().reinit();
		EclServerImplPlugin.hook = new EclServerImplPlugin.IEclServerImplPluginInfoHook() {

			public void warn(String message) {
				System.out.println("warn:" + message);
			}

			public void info(String message) {
				System.out.println("info:" + message);
			}

			public void err(String message) {
				System.out.println("err:" + message);
			}
		};
		// Clean metadata
		CommonPlugin.getDefault().getSuiteRegistry().removeAll();

		server = new ServerApplication() {

			@Override
			protected void joinEclServer() {

			}
		};
		server.port = findFreePort();
		server.run();
		// Thread.sleep(5000);
		return server;
	}

	public static int findFreePort() {
		for (int port = 4000; port < 50000; port++) {
			if (usedPorts.contains(port)) {
				continue;
			}
			try (ServerSocket socket = new ServerSocket(port)) {
				usedPorts.add(port);
				return port;
			} catch (BindException e) {
				continue;
			} catch (IOException e1) {
				throw new AssertionError(e1);
			}
		}
		return -1;
	}

	public static List<FakeAgentApplication> setupAgents(AgentInfo... agents)
			throws Exception {
		List<FakeAgentApplication> apps = new LinkedList<FakeAgentApplication>();
		try {
			final Set<String> registered = new HashSet<String>();
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
					protected void doInit(AgentThread agentThread) {
						super.doInit(agentThread);
					}

					@Override
					public void onRegistered(String agentID) {
						super.onRegistered(agentID);
						registered.add(agentID);
					}

					@Override
					public int getWaitTicks() {
						return 0;
					}
				};
				ag.serverHost = "localhost";
				ag.serverPort = server.port;
				ag.thisHost = "localhost";
				ag.port = findFreePort();
				ag.agentCount = agentInfo.count;
				ag.classifier = agentInfo.platform;
				ag.finder = new FakeAgentApplication.IPortFinder() {

					public int findPort() {
						return findFreePort();
					}
				};
				ag.run();
				apps.add(ag);
			}
			// Wait until all agents will be registered on server
			int i = 0;
			while (registered.size() < agents.length) {
				Thread.sleep(10);
				if (i++ > 100000)
					throw new RuntimeException("Agents failed to start");
			}
		} catch (Exception e) {
			for (FakeAgentApplication fakeAgentApplication : apps) {
				fakeAgentApplication.stop();
			}

			throw e;
		}
		return apps;
	}

	public static class TestsSuite {
		public TestSuite suite;
		public String testSuiteName;
		public Map<String, Q7Artifact> artifacts;
		public List<Q7ArtifactRef> refs;
		// private RemoteEclClient client;
		public Q7ArtifactRef testSuiteRef;
		public volatile int processed = 0;
		public int failed = 0;
		public int passed = 0;
		public int skipped = 0;
		private int sleepBetweenReports = 10;
		private String suiteID;
		public int maxAgents = -1;
		public int minTestsPerAgent = 50;

		public TestsSuite(String name) {
			artifacts = new HashMap<String, Q7Artifact>();
			refs = new LinkedList<Q7ArtifactRef>();
			testSuiteName = name;
			suite = ModelFactory.eINSTANCE.createTestSuite();
			testSuiteName = name;
			suite.setId(testSuiteName);

		}

		public int send(EclServerApplication server)
				throws UnknownHostException, Exception, CoreException,
				InterruptedException {
			RemoteEclClient client = createClient(server);
			return sendSuites(client);
		}

		public int send(RemoteEclClient server)
				throws UnknownHostException, Exception, CoreException,
				InterruptedException {
			return sendSuites(server);
		}

		private int sendSuites(RemoteEclClient client) throws CoreException,
				IOException, UnknownHostException, InterruptedException {
			updateRefs();
			// if (client == null) {
			// }
			try {
				final List<Q7ArtifactRef> requests = addTestSuite(suite, client);
				final int result[] = { 0 };
				// ExecutorService executor =
				// Executors.newFixedThreadPool(thread);
				for (final Q7ArtifactRef ref : requests) {
					// executor.execute(new Runnable() {
					//
					// public void run() {
					addTestResource(getArtifact(ref), client);
					synchronized (client) {
						result[0]++;
					}
					// }
					// // });
				}
				// executor.shutdown();
				// while (!executor.isTerminated()) {
				// Thread.sleep(500);
				// }
				return result[0];
			} finally {
				client.close();
			}
		}

		public static RemoteEclClient createClient(EclServerApplication server) {
			try {
				RemoteEclClient client = new RemoteEclClient("localhost",
						server.port);
				return client;
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
		}

		private void addTestResource(Q7Artifact artfiact, RemoteEclClient client)
				throws CoreException, InterruptedException {
			AddTestResource cmd = CommonCommandsFactory.eINSTANCE
					.createAddTestResource();
			cmd.setSuiteId(suiteID);
			cmd.setResource(artfiact);
			client.execCommand(cmd, 60000, new NullProgressMonitor());
		}

		private synchronized Q7Artifact getArtifact(Q7ArtifactRef ref) {
			return artifacts.get(ref.getId());
		}

		private synchronized List<Q7ArtifactRef> addTestSuite(TestSuite suite,
				RemoteEclClient server) throws IOException,
				UnknownHostException, CoreException, InterruptedException {
			BeginTestSuite beginTestSuite = CommonCommandsFactory.eINSTANCE
					.createBeginTestSuite();
			beginTestSuite.setClientSecret("secret");
			beginTestSuite.setClientId("client");
			beginTestSuite.setSuiteId(suite.getId());

			ExecResult result = server.execCommand(beginTestSuite, 60000,
					new NullProgressMonitor());

			Object[] content = result.getPipeContent();
			for (Object object : content) {
				if (object instanceof BoxedValue) {
					suiteID = BoxedValues.toString((BoxedValue) object);
					break;
				}
			}

			AddTestSuite addTestSuite = CommonCommandsFactory.eINSTANCE
					.createAddTestSuite();
			addTestSuite.setSuite(suite);
			addTestSuite.setSuiteId(suiteID);

			server.execCommand(addTestSuite, 60000, new NullProgressMonitor());

			ArrayList<Q7ArtifactRef> copy = new ArrayList<Q7ArtifactRef>();
			copy.addAll(suite.getRefs());
			return copy;
		}

		public void execute(String... platforms) throws InterruptedException,
				CoreException {
			execute(null, platforms);
		}

		public void execute(WaitListener listener, TestOptions options,
				String... platforms) throws InterruptedException, CoreException {
			RemoteEclClient client = createClient(server);
			execute(client, listener, options, platforms);
		}

		public void execute(WaitListener listener, String... platforms)
				throws InterruptedException, CoreException {
			RemoteEclClient client = createClient(server);
			execute(client, listener, null, platforms);
		}

		public void execute(RemoteEclClient client, WaitListener listener,
				TestOptions options, String... platforms) throws CoreException, InterruptedException {
			ExecTestSuite cmd = ServerCommandsFactory.eINSTANCE
					.createExecTestSuite();
			cmd.setSuiteId(suiteID);
			cmd.getAuts().addAll(createAutInfos(platforms));
			if (options == null) {
				options = ModelFactory.eINSTANCE.createTestOptions();
				options.getValues().put(Options.TESTS_PER_AGENT, "2");
			}
			cmd.setOptions(options);
			try {
				ExecResult result = client.execCommand(cmd, 500000,
						new NullProgressMonitor());
				IStatus status = result.getStatus();
				if (!status.isOK()) {
					throw ClientAppPlugin.createException(
							String.format("Failed to execute testsuite"
									+ status.getMessage()),
							status.getException());
				}

				waitComplete(suiteID, client, listener);
			} finally {
				client.close();
			}
		}

		private void waitComplete(String suiteID, RemoteEclClient client,
				WaitListener listener) throws CoreException {
			try {
				boolean completed = false;
				Set<String> processedNames = new HashSet<String>();
				while (!completed) {
					ExecutionProgress command = ServerCommandsFactory.eINSTANCE
							.createExecutionProgress();
					command.setSuiteId(suiteID);
					ExecResult result = client.execCommand(command, 500000,
							new NullProgressMonitor());
					if (!result.getStatus().isOK())
						throw new CoreException(result.getStatus());

					for (Object answer : result.getPipeContent()) {
						if (answer instanceof Envelope) {
							Envelope envelope = (Envelope) answer;
							Q7Info info = (Q7Info) envelope.getQ7Info();
							if (info != null) {
								if (!processedNames.add(info.getId())) {
									System.out.println("Duplicate report recieved:"
											+ info.getId());
								}
								processed++;
								switch (SimpleSeverity.create(info)){
								case CANCEL:
									skipped++;
									break;
								case ERROR:
									failed++;
									break;
								case OK:
									passed++;
									break;
								}
								if (listener != null) {
									listener.executed(info.getResult(),
											info.getId());
								}
							}
						} else if (answer instanceof ExecutionState) {
							completed = true;
							if (listener != null) {
								listener.done();
							}
						}
					}
					if (sleepBetweenReports > 0) {
						Thread.sleep(sleepBetweenReports);
					}
				}
			} catch (InterruptedException e) {

			}
		}

		private List<AutInfo> createAutInfos(String... platforms) {
			List<AutInfo> auts = new ArrayList<AutInfo>();
			if (platforms == null || platforms.length == 0) {
				auts.add(createAutInfo(null));
			}
			for (String platform : platforms) {
				AutInfo aut = createAutInfo(platform);
				auts.add(aut);
			}
			return auts;
		}

		public void cancel(RemoteEclClient client) throws InterruptedException {
			CancelSuite cancelSuite = CommonCommandsFactory.eINSTANCE.createCancelSuite();
			cancelSuite.setSuiteId(suiteID);
			try {
				ExecResult result = client.execCommand(cancelSuite, 1000, new NullProgressMonitor());
				IStatus status = result.getStatus();
				if (!status.isOK())
					throw new CoreException(status);
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
			client.close();
		}
		private AutInfo createAutInfo(String platform) {
			AutInfo aut = ModelFactory.eINSTANCE.createAutInfo();
			aut.setUri("localhost/qwe/qwe");
			aut.setClassifier(platform);
			aut.setId(testSuiteName);
			return aut;
		}

		public synchronized void addTestcase(TestCaseState... pass)
				throws CoreException {
			Q7Artifact art = ModelFactory.eINSTANCE.createQ7Artifact();
			String uid = testSuiteName + "_" + this.artifacts.size() + 1;// EcoreUtil.generateUUID();
			// while (artifacts.containsKey(uid)) {
			// uid = EcoreUtil.generateUUID();
			// }
			art.setId(uid);
			Scenario scenario = ScenarioFactory.eINSTANCE.createScenario();
			Scenarios.setEclContent(scenario, "");
			scenario.setId(uid);
			scenario.setName(uid);
			StringBuilder tags = new StringBuilder();
			for (TestCaseState st : pass) {
				if (tags.length() > 0) {
					tags.append(", ");
				}
				tags.append(st.name());
			}
			scenario.setTags(tags.toString());
			art.setContent(scenario);

			Q7ArtifactRef ref = ModelFactory.eINSTANCE.createQ7ArtifactRef();
			ref.setId(uid);
			ref.setHash(EmfResourceUtil.md5(scenario));
			ref.setKind(RefKind.SCENARIO);

			refs.add(ref);
			artifacts.put(uid, art);
		}

		public synchronized void updateRefs() throws CoreException {
			ModelUtil.setRefs(suite, refs);

			testSuiteRef = ModelFactory.eINSTANCE.createQ7ArtifactRef();
			testSuiteRef.setId(testSuiteName);
			testSuiteRef.setHash(EmfResourceUtil.md5(suite));
			testSuiteRef.setKind(RefKind.TEST_SUITE);
		}

		public void setSleepReportTime(int i) {
			this.sleepBetweenReports = i;
		}
		
		public int getCancelledCount() {
			return ServerPlugin.getDefault().getExecIndex().getExecutions(suite.getId()).stream().findFirst().map(first -> first.apply(execution -> execution.getCanceledCount())).orElse(0);
		}
	}

	public static TestsSuite createSuite(String name, int count,
			TestCaseState... states) throws CoreException {
		TestsSuite result = new TestsSuite(name);
		for (int i = 0; i < count; i++) {
			result.addTestcase(((states == null || states.length == 0) ? new TestCaseState[] { TestCaseState.pass }
					: states));
		}
		result.updateRefs();
		return result;
	}
	

	public static AutInfo createAUT(String arch, String name) {
		AutInfo aut = ModelFactory.eINSTANCE.createAutInfo();
		aut.setClassifier(arch);
		aut.setId(name);
		aut.setHash(name.getBytes());
		aut.setUri("test://" + name);
		return aut;
	}
}
