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
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem;

public class ReportProblemService implements ICommandService {

	@Override
	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		TaskQueue queue = EclServerImplPlugin.getDefault().getTaskQueue();
		if (queue != null) {
			ReportProblem cmd = (ReportProblem) command;
			try {
				IStatus status = ProcessStatusConverter.toIStatus(cmd.getCause());
				queue.reportProblem(cmd.getAgent(), status);
			} catch (Exception e) {
				queue.reportProblem(cmd.getAgent(), Status.error("Failed to report a problem", e));
			}
		}

		return Status.OK_STATUS;
	}

}
