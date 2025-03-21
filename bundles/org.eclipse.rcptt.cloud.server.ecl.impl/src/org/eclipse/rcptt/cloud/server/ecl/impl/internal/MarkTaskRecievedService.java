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

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved;

public class MarkTaskRecievedService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		TaskQueue queue = EclServerImplPlugin.getDefault().getTaskQueue();
		if (queue == null) {
			throw new InterruptedException("Q7 Server stopped.");
		}

		MarkTaskRecieved cmd = (MarkTaskRecieved) command;
		AgentInfo agent = cmd.getAgent();

		queue.markTaskRecieved(agent, cmd.isState());

		return Status.OK_STATUS;
	}
}
