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
package org.eclipse.rcptt.cloud.server.app.internal;

import java.io.File;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.cloud.commandline.Arg;
import org.eclipse.rcptt.cloud.common.CommonPlugin;
import org.eclipse.rcptt.cloud.common.EclServerApplication;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.IServerContext;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpServer;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.RegisterAgentService;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.util.NetworkUtils;

public class ServerApplication extends EclServerApplication {
	private static final ILog LOG = Platform.getLog(ServerApplication.class);

	@Arg
	public int httpPort = 5007;

	@Arg
	public String sitesDir = getDefaultSitesDir();

	@Arg(isRequired = false, description = "Execution session artifacts location")
	public String artifactsStore = CommonPlugin.getDefault().getStateLocation()
			.append("artifacts").toOSString();

	@Arg(isRequired = false, description = "Keep only N sessions")
	public int keepSessions = 10000; // Keep no more then 100 sessions with
	// metadata.
	@Arg(isRequired = false, description = "Keep AUT for N sessions")
	public int keepAUTArtifacts = 5;

	@Arg(isRequired = false, description = "Server name used by agents to download AUTS. Will be server IP if not specified")
	public String agentServerName = null;

	private static String getDefaultSitesDir() {
		String url = Platform.getInstallLocation().getURL().getPath();
		return new File(url, "sites").getAbsolutePath();
	}

	private final Q7HttpServer server = new Q7HttpServer();

	@Override
	public Object run() throws Exception {
		NetworkUtils.initTimeouts();

		ISMCore.initialize(new File(artifactsStore));
		
		RegisterAgentService.setServerInfo(agentServerName, httpPort);

		server.start(httpPort, sitesDir, keepSessions, keepAUTArtifacts, agentServerName);
		
		setProperty(IServerContext.ID, server.getContext());
		return super.run();
	}

	@Override
	public void stop() {
		server.stop();
		super.stop();
	}
}
