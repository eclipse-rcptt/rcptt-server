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
package org.eclipse.rcptt.cloud.server.app.internal.http.handlers;

import static org.eclipse.rcptt.cloud.server.ServerPlugin.PLUGIN_ID;

import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.EclServerImplPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.IExecutionProfiler;

public class TerminateSessionHandle extends Handler.Abstract {
	public static final String URI = "/api/terminate";

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {
		String queryString = request.getHttpURI().getQuery();
		int cancel = 0;
		if (queryString != null) {

			ExecutionEntry suiteHandle = org.eclipse.rcptt.cloud.server.ExecutionRegistry
					.getInstance().getSuiteHandle(queryString);
			if (suiteHandle != null) {
				IExecutionProfiler profiler = (IExecutionProfiler) suiteHandle
						.getProfiler();
				if (profiler != null) {
					cancel = profiler.testsLeftCount();
					profiler.cancel(new Status(IStatus.CANCEL, PLUGIN_ID, "Cancelled on web client request from "
							+ Request.getRemoteAddr(request)));
				}
				EclServerImplPlugin
						.getDefault()
						.getTaskQueue()
						.cancel(queryString,
								new RuntimeException("Test suite "
										+ suiteHandle.getSuiteId()
										+ " is terminated through web api."));
			}
		}

		try (PrintWriter writer = Q7AbstractHandler.htmlWriter(response)) {
			writer.write(Q7HttpUtils.getResource("/webroot/templates/stat_header"));
			writer.print("Suite: " + queryString + " terminated. Canceled tasks: "
					+ cancel);
			writer.write(Q7HttpUtils.getResource("/webroot/templates/stat_footer"));
		}
		callback.succeeded();
		return true;
	}
}
