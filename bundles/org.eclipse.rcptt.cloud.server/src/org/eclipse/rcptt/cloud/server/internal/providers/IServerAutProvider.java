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

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.rcptt.logging.IQ7Monitor;

import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;

public interface IServerAutProvider {

	public boolean isSupported(AutInfo aut);

	public File getAutFile(ExecutionEntry entry, AutInfo aut, String classifir);

	public void download(ExecutionEntry execution, AutInfo aut,
			String classifier, File autFile, IProgressMonitor monitor,
			IQ7Monitor log) throws CoreException;

}
