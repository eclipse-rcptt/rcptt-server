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

import static org.eclipse.rcptt.cloud.server.ServerPlugin.PLUGIN_ID;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.IExecutionProfiler;

public class CancelSuiteService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {

		CancelSuite cmd = (CancelSuite) command;

		ExecutionEntry handle = org.eclipse.rcptt.cloud.server.ExecutionRegistry
				.getInstance(context).getSuiteHandle(cmd.getSuiteId());

		int count = 0;
		if (handle != null) {
			IExecutionProfiler profiler = (IExecutionProfiler) handle
					.getProfiler();
			if (profiler != null) {
				try {
					count = profiler.testsLeftCount();
				} catch (IllegalStateException e) {
					//Suite is uninitialized
				}
				profiler.cancel(new Status(IStatus.CANCEL, PLUGIN_ID, "Cancelled on client request"));
			}
		}
		context.getOutput().write(String.format("%d task(s) canceled.", count));
		context.getOutput().close(Status.OK_STATUS);

		return Status.OK_STATUS;
	}
}
