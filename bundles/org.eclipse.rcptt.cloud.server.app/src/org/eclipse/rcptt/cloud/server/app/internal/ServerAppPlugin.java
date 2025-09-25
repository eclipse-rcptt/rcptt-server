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
package org.eclipse.rcptt.cloud.server.app.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jetty.server.Handler;
import org.osgi.framework.BundleContext;

public class ServerAppPlugin extends Plugin {

	public static final String PLUGIN_ID = "org.eclipse.rcptt.cloud.server.app";

	private static ServerAppPlugin plugin;

	public static ServerAppPlugin getDefault() {
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

	private static final String HANDLERS_EXTENSION = PLUGIN_ID + ".handler";
	private static final String HANDLER_ELEMENT = "handler";
	private static final String HANDLER_ATTR = "handler";
	private static final String PATH_ATTR = "path";

	public static Map<String, Handler> extensionHandlers() {
		Map<String, Handler> result = new HashMap<String, Handler>();
		for (IConfigurationElement element : Platform.getExtensionRegistry()
				.getConfigurationElementsFor(HANDLERS_EXTENSION)) {
			try {
				result.put(element.getAttribute(PATH_ATTR), (Handler) element
						.createExecutableExtension(HANDLER_ATTR));
			} catch (CoreException e) {
				getDefault().getLog().log(e.getStatus());
			}
		}
		return result;
	}
}
