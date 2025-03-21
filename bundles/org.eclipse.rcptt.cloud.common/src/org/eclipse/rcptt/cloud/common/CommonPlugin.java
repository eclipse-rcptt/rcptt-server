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
package org.eclipse.rcptt.cloud.common;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class CommonPlugin extends Plugin {
	public static final String PLUGIN_ID = "com.xored.q7.cloud.common";
	private static CommonPlugin plugin;

	public static CommonPlugin getDefault() {
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
		shutdownRequested.set(true);
		super.stop(context);
	}

	public static CoreException createException(String message, Throwable cause) {
		return new CoreException(createStatus(message, IStatus.ERROR, cause));
	}

	private static IStatus createStatus(String message, int severity,
			Throwable cause) {
		return new Status(severity, PLUGIN_ID, message, cause);
	}

	public static void error(String message, Throwable cause) {
		getDefault().getLog().log(createStatus(message, IStatus.ERROR, cause));
	}

	private TestSuiteRegistry registry;

	public synchronized TestSuiteRegistry getSuiteRegistry() {
		if (registry == null) {
			registry = new TestSuiteRegistry(new File(new File(
					getStateLocation().toOSString()), "suites"));
		}
		return registry;
	}

	public static CoreException createException(String message) {
		return createException(message, null);
	}

	private static AtomicBoolean shutdownRequested = new AtomicBoolean(false);

	public static void requestShutdown() {
		shutdownRequested.set(true);
	}

	public static boolean isShutdownRequested() {
		return shutdownRequested.get();
	}

	public static IStatus createInfo(String message) {
		return new Status(IStatus.INFO, PLUGIN_ID, message);
	}

	public static IStatus createWarn(String message) {
		return new Status(IStatus.WARNING, PLUGIN_ID, message);
	}

	public static void logInfo(String message) {
		getDefault().getLog().log(createInfo(message));
	}

	public static void warn(String message) {
		getDefault().getLog().log(createWarn(message));
	}
}
