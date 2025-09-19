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
package org.eclipse.rcptt.cloud.common.ecl.impl.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;

public class AddTestSuiteService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		AddTestSuite addTestSuite = (AddTestSuite) command;

		ExecutionEntry suiteHandle = ExecutionRegistry.getInstance(context)
				.getSuiteHandle(addTestSuite.getSuiteId());
		suiteHandle.getTestStore().clearOutDated(addTestSuite.getSuite(), true);

		context.getOutput().close(Status.OK_STATUS);
		return Status.OK_STATUS;
	}

}
