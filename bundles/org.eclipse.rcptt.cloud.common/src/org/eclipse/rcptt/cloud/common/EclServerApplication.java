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
package org.eclipse.rcptt.cloud.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.rcptt.ecl.server.tcp.EclTcpServer;
import org.eclipse.rcptt.ecl.server.tcp.EclTcpServerManager;
import org.eclipse.rcptt.util.NetworkUtils;

import org.eclipse.rcptt.cloud.commandline.Arg;
import org.eclipse.rcptt.cloud.commandline.CommandLineApplication;

public class EclServerApplication extends CommandLineApplication {
	private static List<Integer> usedPorts = new LinkedList<Integer>();

	@Arg(isRequired = false)
	public int port;

	@Override
	public Object run() throws Exception {
		NetworkUtils.initTimeouts();
		if (port == 0) {
			port = findFreePort();
		}
		for (int i = 0; i < 10; i++) {
			try {
				EclTcpServerManager.Instance.startServer(port, false, false);
				CommonPlugin.logInfo(String.format(
						"Ecl server started on port %d", port));
				break;
			} catch (Exception e) {
				CommonPlugin.logInfo("ECL port is already used. ecl:"
						+ port + "try to select free ones.");
				port = findFreePort();
			}
		}
		return waitForCompletion();
	}

	public static int findFreePort() {
		for (int port = 4000; port < 30000; port++) {
			if (!usedPorts.add(port)) {
				continue;
			}
			try (ServerSocket socket = new ServerSocket(port)) {
				return port;
			} catch (IOException e) {
			}
		}
		return -1;
	}

	protected Object waitForCompletion() {
		joinEclServer();
		return 0;
	}

	public boolean isAlive() {
		EclTcpServer server = EclTcpServerManager.Instance.getServer(port);
		if (server == null) {
			return false;
		}
		return server.isAlive() && !CommonPlugin.isShutdownRequested();
	}

	protected void joinEclServer() {
		while (isAlive()) {
			try {
				EclTcpServerManager.Instance.getServer(port).join(JOIN_TIMEOUT);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

	// 5 sec timeframe is enough for shutdown checks
	public static final int JOIN_TIMEOUT = 5000;


	@Override
	public void stop() {
		EclTcpServer server = EclTcpServerManager.Instance.getServer(port);
		if (server != null) {
			server.interrupt();
		}
	}
}
