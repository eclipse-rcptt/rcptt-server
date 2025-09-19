/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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
package org.eclipse.rcptt.cloud.server;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.rcptt.ecl.runtime.IProcess;

public interface IServerContext {
	static final String ID = org.eclipse.rcptt.cloud.server.IServerContext.class.getName();
	
	static IServerContext get(IProcess process) {
		return requireNonNull(((IServerContext) process.getSession().getProperty(IServerContext.ID)));
	}

	Optional<Supplier<InputStream>> getDataByHash(byte[] hash);

	ExecutionRegistry getExecutionRegistry();
	
	URI toUri(File file);
	Optional<URI> toUri(byte[] hash, String name);

	ExecutionIndex getExecutionIndex();
}
