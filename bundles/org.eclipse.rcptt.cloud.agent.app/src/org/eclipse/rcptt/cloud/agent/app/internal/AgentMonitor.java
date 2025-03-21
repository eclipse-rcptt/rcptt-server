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
package org.eclipse.rcptt.cloud.agent.app.internal;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.google.common.collect.Lists;
import org.eclipse.rcptt.cloud.agent.IAgentMonitor;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;

public class AgentMonitor implements IProgressMonitor, IAgentMonitor {

	private final AgentMonitor parent;
	private final List<AgentMonitor> children = Lists.newArrayList();
	private boolean canceled;
	private AgentThread thread;
	private String suiteID;

	public AgentMonitor(AgentMonitor parent, AgentThread thread) {
		super();
		this.parent = parent;
		this.thread = thread;
	}


	@Override
	public void beginTask(String name, int totalWork) {
	}


	@Override
	public void setTaskName(String name) {
	}


	@Override
	public void subTask(String name) {
	}


	@Override
	public void done() {
	}


	@Override
	public void internalWorked(double work) {
	}


	@Override
	public boolean isCanceled() {
		if (parent != null) {
			return parent.isCanceled();
		}
		return canceled;
	}


	@Override
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
		synchronized (children) {
			for (IProgressMonitor child : children) {
				child.setCanceled(canceled);
			}
		}
	}

	public void setSuiteID(String suiteID) {
		this.suiteID = suiteID;
	}


	@Override
	public void worked(int work) {
	}

	public AgentMonitor createSubMonitor(String name, int totalWork) {
		AgentMonitor result = new AgentMonitor(this, thread);
		result.setSuiteID(suiteID);
		result.beginTask(name, totalWork);
		synchronized (children) {
			children.add(result);
		}
		return result;
	}


	@Override
	public void logAgentMessage(String msg, LogType type) {
		if (thread != null && suiteID != null) {
			AgentLogEntryType entryType = null;
			switch (type) {
			case Both:
				entryType = AgentLogEntryType.BOTH;
				break;
			case Client:
				entryType = AgentLogEntryType.CLIENT;
				break;
			case LogOnly:
				entryType = AgentLogEntryType.LOGONLY;
				break;
			}

			thread.reportMessage(suiteID, Plugin.UTILS.createInfo(msg), entryType);
		}
	}
}
