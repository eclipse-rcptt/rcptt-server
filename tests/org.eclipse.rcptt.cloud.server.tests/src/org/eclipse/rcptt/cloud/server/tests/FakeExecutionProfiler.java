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
package org.eclipse.rcptt.cloud.server.tests;

import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.IExecutionProfiler;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.Reporter;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor.Listener;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;

public final class FakeExecutionProfiler implements IExecutionProfiler {
	private String name;
	private int failed = 0;
	private int completed = 0;

	public FakeExecutionProfiler(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FakeExecutionProfiler other = (FakeExecutionProfiler) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public void start() {
	}

	@Override
	public Report generateSkippedReport(TaskDescriptor task, String cause) {
		return null;
	}

	@Override
	public void processReport(TaskDescriptor task, Report report) {
	}

	@Override
	public Reporter getReporter() {
		return null;
	}

	@Override
	public ExecutionState getExecutionState() {
		return null;
	}

	@Override
	public Listener getMonitor() {
		return new Listener() {
			@Override
			public void onExecute(TaskDescriptor descr) {
			}

			@Override
			public void onComplete(TaskDescriptor taskDescriptor, Report report) {
				completed++;
				Q7Info info = (Q7Info) report.getRoot().getProperties()
						.get("q7");
				SimpleSeverity severity = SimpleSeverity.create(info);
				if (severity != SimpleSeverity.OK)
					failed++;
			}

			@Override
			public void onCancel(TaskDescriptor taskDescriptor, AgentInfo agent, IStatus reason) {
			}

			@Override
			public void onError(TaskDescriptor taskDescriptor, Throwable e, boolean fatal) {
				if (fatal) {
					failed += 100;
				}
			}
		};
	}

	@Override
	public void cancel(IStatus s) {

	}

	@Override
	public void reportProblem(org.eclipse.rcptt.cloud.model.AgentInfo agent,
			String cause) {
	}

	@Override
	public String getSuiteID() {
		return name;
	}

	@Override
	public int getTotalTestCount() {

		return 0;
	}

	@Override
	public int getExecutedTestCount() {

		return 0;
	}

	@Override
	public int getFailedTestCount() {

		return failed;
	}

	@Override
	public int getSkippedTestCount() {

		return 0;
	}

	@Override
	public String getTestcaseName(TaskDescriptor task) {
		return task.getId();
	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public void markComplete() {

	}

	@Override
	public void reportAUTLogs(AgentInfo agent,
			Map<String, String> files, AutStartupStatus status) {

	}

	@Override
	public void reportAgentLog(AgentInfo agent, ProcessStatus message,
			AgentLogEntryType type) {
	}

	@Override
	public File getReportFile() {
		return null;
	}

	@Override
	public void sendClientMessage(String msg) {

	}

	public void handleLicensingError() {
	}
}
