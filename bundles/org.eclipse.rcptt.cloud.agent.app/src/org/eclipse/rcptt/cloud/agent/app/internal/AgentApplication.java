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
package org.eclipse.rcptt.cloud.agent.app.internal;

import static java.lang.String.format;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.rcptt.cloud.agent.AgentPlugin;
import org.eclipse.rcptt.cloud.agent.ITestExecutor;
import org.eclipse.rcptt.cloud.agent.TestExecutor;
import org.eclipse.rcptt.cloud.commandline.Arg;
import org.eclipse.rcptt.cloud.commandline.InvalidCommandLineArgException;
import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.util.RemoteEclClient;
import org.eclipse.rcptt.cloud.util.RemoteEclClient.ExecResult;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.util.NetworkUtils;

public class AgentApplication extends EclServerApplication {
	@Arg
	public String classifier;

	@Arg(isRequired = true, description = "RCPTT server port (should match server's -port argument)")
	public String serverHost;

	@Arg(isRequired = true, description = "RCPTT server host")
	public int serverPort;

	@Arg(isRequired = false)
	public String thisHost = "";

	@Arg(isRequired = false, argCount = -1)
	public String[] testOptions = new String[0];

	@Arg(isRequired = false, argCount = -1, name = "skipTags")
	public String[] skipTags = new String[0];

	@Arg(isRequired = false)
	public int agentCount = 1;

	@Arg(isRequired = false)
	public int fetchCount = 2; // Prefetch 2 more tasks by defauls

	public interface IPortFinder {
		int findPort();
	}

	public IPortFinder finder = new IPortFinder() {

		public int findPort() {
			return findFreePort();
		}
	};

	private Thread[] threads = null;
	protected AgentThread[] agentThreads = null;

	public void setFetchCount(int fetchCount) {
		this.fetchCount = fetchCount;
	}
	
	@Override
	public Object waitForCompletion() {
		// SimpleFileQ7Monitor.setLogToConsole(true);
		AgentPlugin.setServerCoords(serverHost, serverPort);
		NetworkUtils.initTimeouts();

		addHook();

		TestExecutor.cleanBundlePool();

		threads = new Thread[agentCount];
		agentThreads = new AgentThread[agentCount];
		for (int i = 0; i < agentCount; i++) {
			agentThreads[i] = newAgentThread(i, finder.findPort());
			threads[i] = new Thread(agentThreads[i], "agent:" + i);
			doInit(agentThreads[i]);
			threads[i].start();
		}
		try {
			return super.waitForCompletion();
		} finally {
			doCleanupPDE();
		}
	}

	protected AgentThread newAgentThread(int index, int port) {
		return new AgentThread(this, index, port);
	}

	@SuppressWarnings("unused")
	protected void doInit(AgentThread agentThread) {
	}

	protected void addHook() {
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}

	@Override
	public boolean isAlive() {
		int alive = 0;
		for (Thread ag : threads) {
			if (ag != null) {
				alive += ag.isAlive() ? 1 : 0;
			}
		}
		return super.isAlive() && alive > 0;
	}

	protected void doCleanupPDE() {
		TestExecutor.cleanupPDE();
	}

	static final int TICK_MILLIS = 1000;

	Thread shutdownHook = new Thread() {

		@Override
		public void run() {
			try {
				for (AgentThread thread : agentThreads) {
					try {
						thread.shutdownAuts(null, true, true);
					} catch (CoreException e) {
						AgentAppPlugin.getDefault().getLog().log(e.getStatus());
					}
				}
				DebugPlugin debug = DebugPlugin.getDefault();
				if (debug != null) {
					for (IProcess process : debug.getLaunchManager().getProcesses()) {
						try {
							process.terminate();
							System.out.println(String.format(
									"Process '%s' terminated", process.getLabel()));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			} catch (InterruptedException e) {
				// We are already shutting down. Ignore. 
			}
		}
	};

	@Override
	public void stop() {
		try {
			if (agentThreads != null) {
				for (AgentThread at : agentThreads) {
					at.stop();
				}
			}
			if (threads != null) {
				for (Thread t : threads) {
					t.join();
				}
			}
			super.stop();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public String getClassifier() {
		if (classifier == null) {
			String os = System.getProperty("osgi.os");
			String ws = System.getProperty("osgi.ws");
			String arch = System.getProperty("osgi.arch");
			classifier = String.format("%s.%s.%s", os, ws, arch);
		}
		return classifier;
	}

	enum State {
		UNREGISTRED, UNENGAGED, WORKED
	}

	List<? extends String> getSkipTags() {
		return Arrays.asList(skipTags);
	}

	String getSkipTagsStr() {
		return tagsToString(skipTags);
	}

	public static String tagsToString(Object[] a) {
		if (a == null) {
			return "";
		}

		int iMax = a.length - 1;
		if (iMax == -1) {
			return "";
		}

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(String.valueOf(a[i]));
			if (i == iMax) {
				return b.toString();
			}
			b.append(", ");
		}
	}

	private RemoteEclClient server;

	protected class EclClient {

		public ExecResult execCommand(Command command, int timeout, IProgressMonitor progressMonitor) throws CoreException, InterruptedException {
			return server.execCommand(command, timeout, progressMonitor);
		}

	}
	
	protected EclClient getQ7Server() throws CoreException {
		if (server == null) {
			server = new RemoteEclClient(serverHost, serverPort);
		}
		return new EclClient();
	}

	public String getServerHost() {
		return serverHost;
	}

	
	@Override
	protected void validateArguments() throws InvalidCommandLineArgException {
		super.validateArguments();
		for (String optionString : testOptions) {
			int eqIndex = optionString.indexOf('=');
			if (eqIndex == -1) {
				throw new InvalidCommandLineArgException(format("Invalid test option %s, format should be 'key = value'",
						optionString), "-testOptions");
			}
		}
		
	}
	
	synchronized String getThisHost() {
		if (thisHost.isEmpty() || thisHost.equals("localhost")) {
			try {
				thisHost = InetAddress.getByName("localhost").getHostAddress();
			} catch (UnknownHostException e1) {
				AgentPlugin.log(AgentPlugin.createStatus(e1.getMessage(),
						IStatus.ERROR, e1));
			}
			// thisHost = InetAddress.getLocalHost().getHostName();
			try {
				Enumeration<NetworkInterface> interfaces = NetworkInterface
						.getNetworkInterfaces();
				while (interfaces.hasMoreElements()) {
					NetworkInterface ni = interfaces.nextElement();
					if (ni.getName().startsWith("vmnet")) {
						continue;
					}
					if (ni.getDisplayName() != null
							&& ni.getDisplayName().indexOf("VMware") != -1) {
						continue;
					}
					Enumeration<InetAddress> inetAddresses = ni
							.getInetAddresses();
					while (inetAddresses.hasMoreElements()) {
						InetAddress address = inetAddresses.nextElement();
						if (address instanceof Inet4Address
								&& !"127.0.0.1"
										.equals(address.getHostAddress())) {
							thisHost = address.getHostAddress();
							return thisHost;
						}
					}
				}

			} catch (IOException e) {
				AgentPlugin.log(AgentPlugin.createStatus(e.getMessage(),
						IStatus.ERROR, e));
			}
		}
		return thisHost;
	}

	String generateAgentId() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss:SSS");
		String idValue = formatter.format(new Date(System.currentTimeMillis()));
		return idValue;
		// return Long.toString(System.currentTimeMillis());
	}

	void replaceOptions(TestOptions options) {
		for (String optionString : testOptions) {
			int eqIndex = optionString.indexOf('=');
			if (eqIndex == -1) {
				throw new IllegalStateException("Options has changed since validateArguments(): " + optionString);
			}
			options.getValues().put(optionString.substring(0, eqIndex).trim(),
					optionString.substring(eqIndex + 1).trim());
		}
	}

	protected ITestExecutor.Closeable createExecutor(AutInfo aut) throws CoreException {
		return new TestExecutor(aut);
	}

	public boolean isDoNotSendPing() {
		return false;
	}

	@SuppressWarnings("unused")
	public void onRegistered(String agentID) {
	}

	public int getWaitTicks() {
		return 2;
	}
}
