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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask;
import org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus;

public class CompleteTaskService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {

		CompleteTask cmd = (CompleteTask) command;
		if (!ServerPlugin.getDefault().getAgentRegistry()
				.isRegistered(cmd.getAgent())) {
			return Status.CANCEL_STATUS;
		}
		TaskQueue queue = EclServerImplPlugin.getDefault().getTaskQueue();
		ReportUtil.verifyReport(cmd.getReport());

		switch (cmd.getReturnCause()) {
		case FAILED_TO_START_AUT:
		case NO_MORE_HANDLES:
		case NO_SPACE_LEFT_ON_DEVICE:
		case TIMEOUT:
		case LICENSING_NOT_AVAILABLE:
		case UNKNOWN:
			boolean failedToStartAUT = cmd.getReturnCause().equals(TaskStatus.FAILED_TO_START_AUT);
			queue.failureTask(cmd.getAgent(), cmd.getSuiteId(), cmd.getId(), cmd.getReport(), failedToStartAUT);
			break;
		case OK:
			queue.complete(cmd.getAgent(), cmd.getSuiteId(), cmd.getId(), cmd.getReport());
		}

		return Status.OK_STATUS;
	}

}
