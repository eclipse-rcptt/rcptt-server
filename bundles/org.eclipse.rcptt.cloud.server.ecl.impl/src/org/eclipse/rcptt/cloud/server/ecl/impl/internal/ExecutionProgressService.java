/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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

import static java.util.Collections.singletonList;

import java.util.Arrays;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.IServerContext;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.IExecutionProfiler;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;

public class ExecutionProgressService extends SingleCommandService<ExecutionProgress> {

	public ExecutionProgressService() {
		super(ExecutionProgress.class);
	}

	@Override
	public Iterable<EObject> serviceTyped(ExecutionProgress command, IServerContext context)
			throws InterruptedException, CoreException {

		ExecutionProgress cmd = (ExecutionProgress) command;

		if (cmd.getSuiteId() == null) {
			ExecutionState state = ServerCommandsFactory.eINSTANCE
					.createExecutionState();
			return singletonList(state);
		}
		ExecutionEntry handle = context.getExecutionRegistry().getSuiteHandle(cmd.getSuiteId());

		if (handle == null) {
			return singletonList(findCompleted(context, cmd.getSuiteId()));
		}

		IExecutionProfiler profiler = (IExecutionProfiler) handle.getProfiler();

		if (profiler == null) {
			return singletonList(findCompleted(context, cmd.getSuiteId()));
		}

		return Arrays.asList(profiler.pollReports());
	}

	private ExecutionState findCompleted(IServerContext serverContext, String suiteId) {
		ExecutionState state = ServerCommandsFactory.eINSTANCE
				.createExecutionState();
		serverContext.getExecutionIndex().getExecution(suiteId).ifPresent(handle -> {
			handle.<Void>apply(execution -> {
				state.setSkippedTestCount(execution.getCanceledCount());
				state.setExecutedTestCount(execution.getExecutedCount());
				state.setTotalTestCount(execution.getTotalCount());
				state.setExecutionTime(execution.getEndTime() == 0 ? System.currentTimeMillis() : execution.getEndTime() - execution.getStartTime());
				state.setFailedTestCount(execution.getFailedCount());
				return null;
			});
		});
		return state;
	}

}
