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
package org.eclipse.rcptt.cloud.server.app;

import static org.eclipse.rcptt.cloud.server.ExecutionIndex.isDone;
import static org.eclipse.rcptt.cloud.server.ExecutionIndex.isPending;

import org.eclipse.rcptt.cloud.server.ism.stats.Execution;

public final class ReportHelper {
	private ReportHelper() {}
	
	public static long getCloudTime(Execution exec) {
		long endTime = isDone(exec) ? exec.getEndTime() : System
				.currentTimeMillis();

		if ((isPending(exec) || isDone(exec)) && exec.getStartTime() == 0) {
			return 0;
		}

		return endTime - exec.getStartTime();
	}
	
	public static long firstReportTime(Execution exec) {
		if (exec.getFirstReportTime() == 0) {
			return 0;
		}
		return exec.getFirstReportTime() - exec.getStartTime();
	}
}
