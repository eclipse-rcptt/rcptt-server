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

import java.io.File;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.rcptt.cloud.model.AutInfo;

public interface IAutProvider {

	public boolean isSupport(AutInfo aut);

	/**
	 * Downloads and unpacks AUT if necessary
	 * @param aut
	 * @param classifier
	 * 
	 * @return AUT directory containing features and plugins, for example
	 *         auts/com.xored.f4.ide/f4
	 * 
	 * @throws CoreException
	 */
	public File download(AutInfo aut, String classifier, IProgressMonitor monitor) throws CoreException;
	
	public static abstract class Composite implements IAutProvider {		
		protected abstract Collection<IAutProvider> getProviders();
		
		
		private IAutProvider getProvider(AutInfo aut) {
			for (IAutProvider p: getProviders()) {
				if (p.isSupport(aut))
					return p;
			}
			return null;
		}

		@Override
		public boolean isSupport(AutInfo aut) {
			return getProvider(aut) != null;
		}

		@Override
		public File download(AutInfo aut, String classifier, IProgressMonitor monitor) throws CoreException {
			IAutProvider p = getProvider(aut);
			if (p == null)
				throw new IllegalArgumentException("Aut " + aut.getUri() + " is not supported");
			return p.download(aut, classifier, monitor);
		}
	}

}
