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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;

public class ListAgentsService implements ICommandService {

	public ListAgentsService() {
	}

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		AgentRegistry reg = ServerPlugin.getDefault().getAgentRegistry();
		List<AgentInfo> agents = reg.getAgents();
		Collections.sort(agents, SORTER);
		int id = 1;
		for (AgentInfo agent : agents) {
			AgentInfoDetails lastPing = reg.getLastPing(agent);
			AgentInfoObject details = ServerCommandsFactory.eINSTANCE
					.createAgentInfoObject();
			details.setClassifier(agent.getClassifier());
			details.setLaunchId(agent.getLaunchId());
			details.setUri(agent.getUri());
			details.setTakenTasks(getTaskQueue().getAgentTakenTasks(agent));
			details.setId(Integer.toString(id));
			if (lastPing != null) {
				details.setCpuCount(lastPing.getCpuCount());
				details.setCpuUsage(lastPing.getCpuUsage());
				details.setFreeDiskSpace(lastPing.getFreeDiskSpace());
				details.setFreeMemory(lastPing.getFreeMemory());
				details.setTotalDiskSpace(lastPing.getTotalDiskSpace());
				details.setTotalMemory(lastPing.getTotalMemory());
				details.setUptime(lastPing.getUptime());
				details.setTime(lastPing.getTime());
			}
			// details.setFreeDiskSpace(preSchedule.getSpaceLeft());
			EList<String> skipTags = agent.getSkipTags();
			for (String tag : skipTags) {
				details.getSkipTags().add(tag);
			}
			context.getOutput().write(details);
			id++;
			// }
		}
		context.getOutput().close(Status.OK_STATUS);
		return Status.OK_STATUS;
	}

	private static Comparator<AgentInfo> SORTER = new Comparator<AgentInfo>() {

		public int compare(AgentInfo o1, AgentInfo o2) {
			return o1.getUri().compareTo(o2.getUri());
		}
	};

	private static TaskQueue getTaskQueue() {
		return EclServerImplPlugin.getDefault().getTaskQueue();
	}
}
