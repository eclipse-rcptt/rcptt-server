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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.BoxedValues;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.ExecutionProfiler;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.IExecutionProfiler;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite;
import org.eclipse.rcptt.cloud.util.StatusCodes;

public class ExecTestSuiteService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		ExecTestSuite cmd = (ExecTestSuite) command;

		AutInfo[] auts = getAuts(cmd);

		if (auts.length == 0) {
			return EclServerImplPlugin.createErr(
					"No AUT is provided for execution", null);
		}

		IStatus status = checkAuts(auts);
		if (!status.isOK()) {
			return status;
		}

		ExecutionRegistry executions = ExecutionRegistry.getInstance(context);

		try {
			IExecutionProfiler profiler = new ExecutionProfiler(
					cmd.getSuiteId(), auts,
					cmd.getOptions(), cmd.getMetadata(), executions);
			File reportFile = profiler.getReportFile();

			// TODO: add artifacts prefix, remove from client 
			// @see org.eclipse.rcptt.cloud.client.Q7ServerApi.downloadFile(URI, File)
			// @see https://github.com/eclipse-rcptt/rcptt-server/issues/39
			URI location = executions.makeRelativePath(
					reportFile).get();

			context.getOutput().write(BoxedValues.box(location.toASCIIString()));
		} catch (IOException e) {
			return new Status(IStatus.ERROR, ServerPlugin.PLUGIN_ID,
					e.getMessage(), e);
		}

		return Status.OK_STATUS;
	}

	protected IStatus checkAuts(AutInfo[] auts) {
		List<String> invalid = new ArrayList<String>();
		for (AutInfo aut : auts) {
			if (!getAgents().canExecute(aut)) {
				if (aut.getClassifier() == null) {
					invalid.add("any");
				} else {
					invalid.add(aut.getClassifier());
				}
			}
		}

		if (invalid.isEmpty()) {
			return Status.OK_STATUS;
		}

		String msg = String.format(
				"No agents are suitable for '%s' classifier(s).",
				invalid.toString());

		return new Status(IStatus.ERROR, getClass(), StatusCodes.NOT_READY, msg, null);

	}

	protected AutInfo[] getAuts(ExecTestSuite cmd) {
		List<AutInfo> result = new ArrayList<AutInfo>();
		for (AutInfo autInfo : cmd.getAuts()) {
			AutInfo aut = EcoreUtil.copy(autInfo);
			if (aut.getClassifier() == null) {
				// Initialize classifier from any available agent
				// TODO: Add support for no matter classifier execution
				AgentRegistry reg = ServerPlugin.getDefault()
						.getAgentRegistry();
				List<AgentInfo> agents = reg.getAgents();
				for (AgentInfo agentInfo : agents) {
					aut.setClassifier(agentInfo.getClassifier());
					break;
				}
			}
			result.add(aut);
		}
		return result.toArray(new AutInfo[result.size()]);
	}

	private static AgentRegistry getAgents() {
		return ServerPlugin.getDefault().getAgentRegistry();
	}

}
