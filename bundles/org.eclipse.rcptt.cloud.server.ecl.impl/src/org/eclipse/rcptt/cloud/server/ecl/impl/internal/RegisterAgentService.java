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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IPipe;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgent;
import org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;

public class RegisterAgentService implements ICommandService {

	private static String serverHost;
	private static int serverPort;

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		AgentRegistry agents = ServerPlugin.getDefault().getAgentRegistry();
		RegisterAgent cmd = (RegisterAgent) command;
		AgentInfo agent = cmd.getAgent();
		AgentInfo oldAgent = agents.getAgent(AgentRegistry.getAgentID(agent));

		if (oldAgent == null || !EcoreUtil.equals(oldAgent, agent)) {
			if (oldAgent != null) {
				agents.removeAgent(oldAgent);
			}
			agents.addAgent(agent);
		}

		IPipe output = context.getOutput();
		RegisterAgentResult result = ServerCommandsFactory.eINSTANCE
				.createRegisterAgentResult();
		result.setHttpServer(serverHost);
		result.setHttpPort(serverPort);
		output.write(result);

		return Status.OK_STATUS;
	}

	public static void setServerInfo(String serverHost, int httpPort) {
		RegisterAgentService.serverHost = serverHost;
		RegisterAgentService.serverPort = httpPort;
	}

}
