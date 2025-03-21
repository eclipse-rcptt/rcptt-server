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
package org.eclipse.rcptt.cloud.server;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import org.eclipse.rcptt.cloud.server.internal.DelegatingExecutionMonitor;

public class ServerPlugin extends Plugin {
	public static final String PLUGIN_ID = "com.xored.q7.cloud.server";

	private static ServerPlugin plugin;

	public ServerPlugin() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		execMonitor = new DelegatingExecutionMonitor();
		execIndex = new ExecutionIndex();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (registry != null) {
			registry.dispose();
		}
		registry = null;
		execIndex = null;
		plugin = null;
		super.stop(context);
	}

	public static ServerPlugin getDefault() {
		return plugin;
	}

	private AgentRegistry registry;

	public synchronized AgentRegistry getAgentRegistry() {
		if (registry == null) {
			reinit();
		}
		return registry;
	}

	private ExecutionIndex execIndex;

	public ExecutionIndex getExecIndex() {
		return execIndex;
	}

	private DelegatingExecutionMonitor execMonitor;

	private String serverName;

	private int serverHttpPort;

	public ExecutionListener getExecListener() {
		return execMonitor;
	}

	public IExecutionService getExecService() {
		return execMonitor;
	}

	public static IStatus createInfo(String message) {
		return new Status(IStatus.INFO, PLUGIN_ID, message);
	}

	public static IStatus createErr(String message, Throwable err) {
		return new Status(IStatus.ERROR, PLUGIN_ID, message, err);
	}

	public static IStatus createErr(String message) {
		return createErr(message, null);
	}

	public static void logInfo(String message) {
		getDefault().getLog().log(createInfo(message));
	}

	public static void err(String message, Throwable e) {
		getDefault().getLog().log(createErr(message, e));
	}

	public static void logErr(Throwable e, String message, Object... args) {
		err(String.format(message, args), e);
	}

	public synchronized void reinit() {
		if (registry != null) {
			registry.dispose();
		}
		registry = new AgentRegistry();
	}

	public void setServerCredentials(String serverName, int httpPort) {
		this.serverName = serverName;
		this.serverHttpPort = httpPort;
	}

	public int getServerHttpPort() {
		return serverHttpPort;
	}

	public String getServerName() {
		return serverName;
	}
}
