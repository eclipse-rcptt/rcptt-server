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
package org.eclipse.rcptt.cloud.server.internal.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.ecl.internal.core.CorePlugin;
import org.osgi.framework.FrameworkUtil;
import org.eclipse.rcptt.cloud.model.AutInfo;

public class AutDownloadManager {

	private static AutDownloadManager instance;

	public static AutDownloadManager getInstance() {
		if (instance == null) {
			instance = new AutDownloadManager();
		}
		return instance;
	}

	private final static String EXTPT = FrameworkUtil.getBundle(AutDownloadManager.class).getSymbolicName() + ".autProvider";
	private final static String CLASS_ATTR = "class";

	private List<IServerAutProvider> providers;

	protected AutDownloadManager() {
	}

	public IServerAutProvider getProvider(AutInfo aut) {
		for (IServerAutProvider provider : getProviders()) {
			if (provider.isSupported(aut)) {
				return provider;
			}
		}
		return null;
	}

	protected List<IServerAutProvider> getProviders() {
		if (providers == null) {
			providers = new ArrayList<IServerAutProvider>();

			IConfigurationElement[] configs = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(EXTPT);
			for (IConfigurationElement config : configs) {
				try {
					IServerAutProvider provider = (IServerAutProvider) config
							.createExecutableExtension(CLASS_ATTR);
					providers.add(provider);
				} catch (CoreException e) {
					CorePlugin.getDefault().getLog().log(e.getStatus());
				}
			}
		}
		return providers;
	}

}
