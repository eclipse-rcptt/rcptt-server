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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution;

import java.io.File;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.Envelope;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

public interface IExecutionProfiler {
	public Report generateSkippedReport(TaskDescriptor ref, String cause);

	public Envelope[] pollReports();

	public ExecutionState getExecutionState();

	public void cancel(IStatus status);

	public void reportProblem(AgentInfo agent, String cause);

	public String getTestcaseName(TaskDescriptor task);

	public File getReportFile();

	public void sendClientMessage(String msg);

	int testsLeftCount();
}
