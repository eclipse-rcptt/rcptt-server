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
package org.eclipse.rcptt.cloud.client;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class ClientAppPlugin extends Plugin {
	public static final String PLUGIN_ID = "com.xored.q7.cloud.client";

	private static ClientAppPlugin plugin;

	public static ClientAppPlugin getDefault() {
		return plugin;
	}

	private AutTcpConnector autTcpConnector;


	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		autTcpConnector = new AutTcpConnector();
	}


	@Override
	public void stop(final BundleContext context) throws Exception {
		autTcpConnector.dispose();
		autTcpConnector = null;
		plugin = null;
		super.stop(context);
	}

	public AutTcpConnector getAutTcpConnector() {
		return autTcpConnector;
	}

	public void info(final String message) {
		getLog().log(new Status(IStatus.INFO, PLUGIN_ID, message));
	}

	public void error(final String message, Throwable e) {
		getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message, e));
	}

	public static IStatus createStatus(int severity, String message,
			Throwable cause) {
		return new Status(severity, PLUGIN_ID, message, cause);
	}

	public static IStatus createErrorStatus(String message, Throwable cause) {
		return createStatus(IStatus.ERROR, message, cause);
	}

	public static void logErr(String message, Throwable cause) {
		getDefault().getLog().log(createErrorStatus(message, cause));
	}

	public static CoreException createException(String message, Throwable cause) {
		return new CoreException(createErrorStatus(message, cause));
	}

	public static CoreException createException(String message) {
		return createException(message, null);
	}

	public static void logWarn(String message, Exception cause) {
		getDefault().getLog()
		.log(createStatus(IStatus.WARNING, message, cause));
	}

}
