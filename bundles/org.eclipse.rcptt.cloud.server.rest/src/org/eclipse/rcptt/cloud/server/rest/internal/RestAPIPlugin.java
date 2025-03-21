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
package org.eclipse.rcptt.cloud.server.rest.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class RestAPIPlugin extends Plugin {

	private static RestAPIPlugin plugin;
	public static final String PLUGIN_ID = "com.xored.q7.cloud.server.rest";

	public static RestAPIPlugin getDefault() {
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

	public static void logErr(Throwable e, String format, Object... args) {
		getDefault().getLog().log(
				new Status(IStatus.ERROR, PLUGIN_ID, String
						.format(format, args)));
	}

	public static void logErr(String format, Object... args) {
		logErr(null, format, args);
	}
}
