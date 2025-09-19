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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.model.Envelope;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.IExecutionProfiler;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;

public class ExecutionProgressService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {

		ExecutionProgress cmd = (ExecutionProgress) command;

		if (cmd.getSuiteId() == null) {
			ExecutionState state = ServerCommandsFactory.eINSTANCE
					.createExecutionState();
			context.getOutput().write(state);
			return Status.CANCEL_STATUS;
		}
		ExecutionEntry handle = ExecutionRegistry.getInstance(context).getSuiteHandle(cmd.getSuiteId());

		if (handle == null) {
			return returnNoSuite(context);
		}

		IExecutionProfiler profiler = (IExecutionProfiler) handle.getProfiler();

		if (profiler == null) {
			return returnNoSuite(context);
		}

		if (profiler != null) {
			for (Envelope envelope : profiler.pollReports()) {
				context.getOutput().write(envelope);
			}
		}

		context.getOutput().close(Status.OK_STATUS);
		return Status.OK_STATUS;
	}

	private IStatus returnNoSuite(IProcess context) throws CoreException {
		ExecutionState state = ServerCommandsFactory.eINSTANCE
				.createExecutionState();
		context.getOutput().write(state);
		context.getOutput().close(Status.OK_STATUS);
		return Status.OK_STATUS;
	}
}
