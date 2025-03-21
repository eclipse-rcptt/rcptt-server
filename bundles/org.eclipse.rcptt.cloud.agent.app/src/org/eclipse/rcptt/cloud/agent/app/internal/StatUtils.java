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

import java.io.File;
import java.lang.management.ManagementFactory;

import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;

public class StatUtils {
	public static long getFreeSpace(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir.getFreeSpace() / (1024 * 1024);
	}

	public static long getTotalSpace(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir.getTotalSpace() / (1024 * 1024);
	}

	public static AgentInfoDetails getDetails(File dir) {
		AgentInfoDetails details = ServerCommandsFactory.eINSTANCE
				.createAgentInfoDetails();
		details.setFreeDiskSpace(StatUtils.getFreeSpace(dir));
		details.setTotalDiskSpace(StatUtils.getTotalSpace(dir));
		details.setTotalMemory(Runtime.getRuntime().totalMemory());
		details.setFreeMemory(Runtime.getRuntime().freeMemory());
		details.setCpuCount(Runtime.getRuntime().availableProcessors());

		try {
			details.setCpuUsage((long) ManagementFactory
					.getOperatingSystemMXBean().getSystemLoadAverage());
		} catch (Throwable e) {
			// Ignore
		}
		return details;
	}
}
