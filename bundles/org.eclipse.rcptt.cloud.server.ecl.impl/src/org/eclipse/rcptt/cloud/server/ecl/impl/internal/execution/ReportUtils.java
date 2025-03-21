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

public class ReportUtils {

	public static String getTimeMins(long time) {
		int est = (int) (time) / 1000;
		return String.format(" %d:%02d mins", est / 60, est % 60);
	}

	public static String calculateRemaining(ExecutionProfiler profiler) {
		int processed = profiler.getExecutedTestCount();
		int total = profiler.getTotalTestCount();
		int time = (int) (System.currentTimeMillis() - profiler.getStarted())
				/ (processed + 1);
		int est = time * (total - processed) / 1000;
		return String.format(" %d:%02d mins remaining.", est / 60, est % 60);
	}
	
	
}
