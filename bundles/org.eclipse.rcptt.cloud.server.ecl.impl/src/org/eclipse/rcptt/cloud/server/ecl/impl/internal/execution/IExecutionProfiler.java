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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution;

import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;

public interface IExecutionProfiler {
	public String getSuiteID();

	public void start();

	public Report generateSkippedReport(TaskDescriptor ref, String cause);

	public void processReport(TaskDescriptor task, Report report);

	public Reporter getReporter();

	public ExecutionState getExecutionState();

	public TaskDescriptor.Listener getMonitor();

	public void cancel(IStatus status);

	public void reportProblem(AgentInfo agent, String cause);

	public int getTotalTestCount();

	public int getExecutedTestCount();

	public int getFailedTestCount();

	public int getSkippedTestCount();

	public String getTestcaseName(TaskDescriptor task);

	public boolean isComplete();

	/**
	 * Mark profiler as complete, all results are processed to client.
	 */
	public void markComplete();

	public void reportAUTLogs(AgentInfo agent, Map<String, String> files,
			AutStartupStatus status);

	public void reportAgentLog(AgentInfo agent, ProcessStatus status,
			AgentLogEntryType agentLogEntryType);

	public File getReportFile();

	public void sendClientMessage(String msg);
}
