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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;

public class EclServerImplPlugin extends Plugin {

	public static final String PLUGIN_ID = "com.xored.q7.cloud.server.ecl.impl";

	private static EclServerImplPlugin plugin;

	private TaskQueue taskQueue;

	public static EclServerImplPlugin getDefault() {
		return plugin;
	}


	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		reinit();
	}

	public void reinit() {
		taskQueue = new TaskQueue(ServerPlugin.getDefault().getAgentRegistry());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */

	@Override
	public void stop(BundleContext context) throws Exception {
		taskQueue = null;
		plugin = null;
		super.stop(context);
	}

	public TaskQueue getTaskQueue() {
		return taskQueue;
	}

	public static void err(String message, Throwable t) {
		if (hook != null) {
			hook.err(message);
		} else {
			IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message);
			getDefault().getLog().log(status);
		}
	}

	public static IStatus createErr(String message, Throwable t) {
		return new Status(IStatus.ERROR, PLUGIN_ID, message, t);
	}

	public static void warn(String message) {
		if (hook != null) {
			hook.warn(message);
		} else {
			IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, message);
			getDefault().getLog().log(status);
		}
	}

	public interface IEclServerImplPluginInfoHook {
		void info(String message);

		void err(String message);

		void warn(String message);
	}

	public static IEclServerImplPluginInfoHook hook = null;

	public static void info(String message) {
		if (hook != null) {
			hook.info(message);
		} else {
			IStatus status = new Status(IStatus.INFO, PLUGIN_ID, message);
			getDefault().getLog().log(status);
		}
	}

}
