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
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.CoreFactory;
import org.eclipse.rcptt.ecl.core.Nullable;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.GetTask;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;

public class GetTaskService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		TaskQueue queue = EclServerImplPlugin.getDefault().getTaskQueue();
		if (queue == null) {
			throw new InterruptedException("Q7 Server stopped.");
		}

		GetTask cmd = (GetTask) command;
		AgentInfo agent = cmd.getAgent();

		if (!ServerPlugin.getDefault().getAgentRegistry().isRegistered(agent)) {
			EclServerImplPlugin.err("Agent is not registered", null);
			return EclServerImplPlugin.createErr("Agent is not registered",
					null);
		}

		Task task = readNextTask(queue, agent, cmd.getSuiteId());

		queue.registerToAgent(agent, task);

		context.getOutput().write(toNullable(task));
		context.getOutput().close(Status.OK_STATUS);

		return Status.OK_STATUS;
	}

	private static Nullable toNullable(Task task) {
		Nullable result = CoreFactory.eINSTANCE.createNullable();
		result.setType(Task.class.getName());
		result.setValue(task == null ? EcoreFactory.eINSTANCE.createEObject()
				: task);
		return result;
	}

	private static Task readNextTask(TaskQueue queue, AgentInfo agent,
			String suiteID) {
		while (true) {
			TaskDescriptor descriptor = queue.get(agent, suiteID);
			if (descriptor == null) {
				return null;
			}
			if (descriptor.isCompleted()) // retry later, this task is done already
				continue;

			return descriptor.readTask();
		}
	}


}
