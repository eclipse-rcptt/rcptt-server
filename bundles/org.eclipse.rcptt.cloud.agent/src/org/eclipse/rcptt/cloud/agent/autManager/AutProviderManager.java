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
package org.eclipse.rcptt.cloud.agent.autManager;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.ecl.internal.core.CorePlugin;

import com.google.common.base.Preconditions;
import com.google.common.io.Closer;
import org.eclipse.rcptt.cloud.agent.autManager.IAutProvider.Composite;

public class AutProviderManager implements Closeable {
	private final Collection<IAutProvider> list = Collections.unmodifiableList(AutProviderManager.getProviders());
	private final Composite compositeProvider = new IAutProvider.Composite() {
		{
			Preconditions.checkNotNull(list);
		}

		@Override
		protected Collection<IAutProvider> getProviders() {
			return list;
		}
	};

	private final static String EXTPT = "org.eclipse.rcptt.cloud.agent.autProvider";
	private final static String CLASS_ATTR = "class";

	public IAutProvider getProvider() {
		return compositeProvider;
	}

	private static List<IAutProvider> getProviders() {
		List<IAutProvider> providers = new ArrayList<IAutProvider>();

		IConfigurationElement[] configs = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTPT);
		for (IConfigurationElement config : configs) {
			try {
				IAutProvider provider = (IAutProvider) config.createExecutableExtension(CLASS_ATTR);
				providers.add(provider);
			} catch (CoreException e) {
				CorePlugin.getDefault().getLog().log(e.getStatus());
			}
		}

		return providers;
	}

	@SuppressWarnings("resource")
	@Override
	public void close() throws IOException {
		try (Closer closer = Closer.create()) {
			for (IAutProvider provider: list) {
				if (provider instanceof Closeable closeable) {
					closer.register(closeable);
				}
			}
		}
	}
}
