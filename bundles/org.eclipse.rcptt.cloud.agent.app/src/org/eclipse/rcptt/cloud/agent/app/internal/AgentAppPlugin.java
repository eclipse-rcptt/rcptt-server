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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class AgentAppPlugin extends Plugin {

	public static final String PLUGIN_ID = "com.xored.q7.cloud.agent.app";

	private static AgentAppPlugin plugin;

	public static AgentAppPlugin getDefault() {
		return plugin;
	}


	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}


	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static IStatus createStatus(int severity, String message,
			Throwable cause) {
		return new Status(severity, PLUGIN_ID, message, cause);
	}

	public static IStatus createErrorStatus(String message, Throwable cause) {
		return createStatus(IStatus.ERROR, message, cause);
	}

	public static CoreException createException(String message, Throwable cause) {
		return new CoreException(createErrorStatus(message, cause));
	}

	public static CoreException createException(String message) {
		return createException(message, null);
	}

}
