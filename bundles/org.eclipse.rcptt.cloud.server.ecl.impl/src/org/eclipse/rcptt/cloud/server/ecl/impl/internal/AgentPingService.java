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
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentPing;

public class AgentPingService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		AgentRegistry agents = ServerPlugin.getDefault().getAgentRegistry();
		AgentPing cmd = (AgentPing) command;
		AgentInfo agent = cmd.getAgent();

		if (!agents.isRegistered(agent)) {
			context.getOutput().write(Integer.valueOf(-1));
			return Status.OK_STATUS;
		}
		agents.registerPing(agent, cmd.getDetails());

		if (agents.checkCancel(agent)) {
			context.getOutput().write(Integer.valueOf(1));
		} else {
			context.getOutput().write(Integer.valueOf(2));
		}

		return Status.OK_STATUS;
	}

}
