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
package org.eclipse.rcptt.cloud.agent;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.internal.launching.ext.Q7TargetPlatformInitializer;
import org.eclipse.rcptt.internal.launching.ext.Q7TargetPlatformManager;
import org.eclipse.rcptt.launching.target.ITargetPlatformHelper;
import org.eclipse.rcptt.launching.target.TargetPlatformManager;
import org.osgi.framework.BundleContext;

import org.eclipse.rcptt.cloud.agent.AutRegistry.ITargetPlatformManager;
import org.eclipse.rcptt.cloud.agent.autManager.AutProviderManager;

public class AgentPlugin extends Plugin {
	public static final String PLUGIN_ID = "com.xored.q7.cloud.agent";

	private static AgentPlugin plugin;

	public static AgentPlugin getDefault() {
		return plugin;
	}

	public AgentPlugin() {
	}

	private AutRegistry auts;
	private AutProviderManager manager; 

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		ITargetPlatformManager tpManager = new AutRegistry.ITargetPlatformManager() {
			
			@Override
			public ITargetPlatformHelper create(String location, IProgressMonitor monitor) throws CoreException {
				return TargetPlatformManager.createTargetPlatform(location, monitor);
			}
			
			@Override
			public void clear() {
				Q7TargetPlatformManager.clear();
			}

		};
		manager = new AutProviderManager();
		auts = new AutRegistry(new File(new File(getStateLocation()
				.toOSString()), "auts"), tpManager, manager.getProvider());
	}


	@Override
	public void stop(BundleContext context) throws Exception {
		try {
			manager.close();
		} finally {
			plugin = null;
			super.stop(context);
		}
	}

	public static String getClassifier() {
		String os = System.getProperty("osgi.os");
		String ws = System.getProperty("osgi.ws");
		String arch = System.getProperty("osgi.arch");
		return String.format("%s.%s.%s", os, ws, arch);
	}

	public synchronized AutRegistry getAutRegistry() {
		if (auts == null) {

		}
		return auts;
	}

	private AutFileUtil autFiles;

	public synchronized AutFileUtil getAutFiles() {
		if (autFiles == null) {
			autFiles = new AutFileUtil(new File(new File(getStateLocation()
					.toOSString()), "workspaces"));
		}
		return autFiles;
	}

	public static CoreException createException(String message, Throwable cause) {
		return new CoreException(createStatus(message, IStatus.ERROR, cause));
	}

	public static CoreException createException(String message) {
		return createException(message, null);
	}

	public static IStatus createStatus(String message, int severity,
			Throwable cause) {
		return new Status(severity, PLUGIN_ID, message, cause);
	}

	public static void error(String message, Throwable cause) {
		getDefault().getLog().log(createStatus(message, IStatus.ERROR, cause));
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void logInfo(String message) {
		log(createStatus(message, IStatus.INFO, null));
	}

	public static void logErr(String message, Throwable e) {
		log(createStatus(message, IStatus.ERROR, e));
	}

	public static void logWarn(String message, Exception e) {
		log(createStatus(message, IStatus.WARNING, e));
	}

	private int serverPort;
	private String serverHost;

	public static String getServerHost() {
		return getDefault().serverHost;
	}

	public static int getServerPort() {
		return getDefault().serverPort;
	}

	public static void setServerCoords(String host, int port) {
		getDefault().serverHost = host;
		getDefault().serverPort = port;
	}
}
