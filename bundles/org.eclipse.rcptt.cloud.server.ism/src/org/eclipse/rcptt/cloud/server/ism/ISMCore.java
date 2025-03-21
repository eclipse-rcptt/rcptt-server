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
package org.eclipse.rcptt.cloud.server.ism;

import java.io.File;

import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.AgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class ISMCore {
	private File statsRoot = new File(Q7ServerISMPlugin.getDefault()
			.getStateLocation().append("artifacts").toOSString());
	private static ISMCore instance;
	private ISMHandleStore<AgentStats> agentStore;
	private ISMHandleStore<SuiteStats> suiteStore;

	private ISMCore() {
	}

	public synchronized static void initialize(File baseDir) {
		ISMCore core = getInstance();
		core.doInitialize(baseDir);
	}

	private void doInitialize(File baseDir) {
		this.statsRoot = baseDir;
		this.statsRoot.mkdirs();
		this.agentStore = null;
		this.suiteStore = null;
		initialize();
	}

	private void initialize() {
		if (agentStore == null || suiteStore == null) {
			agentStore = new ISMHandleStore<AgentStats>(new File(
					this.statsRoot, "agents"),
					StatsPackage.eINSTANCE.getAgentStats());
			suiteStore = new ISMHandleStore<SuiteStats>(new File(
					this.statsRoot, "suites"),
					StatsPackage.eINSTANCE.getSuiteStats());
		}
	}

	public synchronized static ISMCore getInstance() {
		if (instance == null) {
			instance = new ISMCore();
		}
		return instance;
	}

	public ISMHandleStore<AgentStats> getAgentStore() {
		initialize();
		return agentStore;
	}

	public ISMHandleStore<SuiteStats> getSuiteStore() {
		initialize();
		return suiteStore;
	}
}
