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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.util.Base64;

import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup;

public class ReportAUTStartupService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		TaskQueue queue = EclServerImplPlugin.getDefault().getTaskQueue();
		if (queue != null) {
			ReportAUTStartup cmd = (ReportAUTStartup) command;

			Map<String, String> files = new HashMap<String, String>();

			EMap<String, String> efiles = cmd.getFiles();
			for (Entry<String, String> file : efiles.entrySet()) {
				files.put(file.getKey(), decode(file.getValue()));
			}

			queue.reportAUTLogs(cmd.getSuiteId(), cmd.getAgent(), files,
					cmd.getState());
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
