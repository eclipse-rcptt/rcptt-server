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
import org.eclipse.rcptt.util.Base64;

import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog;

public class ReportAgentLogService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		TaskQueue queue = EclServerImplPlugin.getDefault().getTaskQueue();
		if (queue != null) {
			ReportAgentLog cmd = (ReportAgentLog) command;

			queue.reportAgentLog(cmd.getSuiteId(), cmd.getAgent(),
					cmd.getStatus(), cmd.getType());
		}

		return Status.OK_STATUS;
	}

	private String decode(String log) {
		if (log != null) {
			return new String(Base64.decode(log));
		}
		return log;
	}
}
