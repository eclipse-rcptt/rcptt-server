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

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;
import org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo;

public class ListExecutedSuitesService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {

		Collection<String> suites = getTaskQueue().getSuites();
		for (String suite : suites) {
			SuiteInfo info = ServerCommandsFactory.eINSTANCE.createSuiteInfo();
			info.setName(suite);
			info.setPendingTasks(getTaskQueue().getSuitePendings(suite));
			context.getOutput().write(info);
		}
		context.getOutput().close(Status.OK_STATUS);

		return Status.OK_STATUS;
	}

	private static TaskQueue getTaskQueue() {
		return EclServerImplPlugin.getDefault().getTaskQueue();
	}

}
