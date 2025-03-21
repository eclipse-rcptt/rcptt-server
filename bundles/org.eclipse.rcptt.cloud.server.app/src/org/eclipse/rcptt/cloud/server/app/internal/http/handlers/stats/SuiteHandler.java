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
package org.eclipse.rcptt.cloud.server.app.internal.http.handlers.stats;

import static org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ISMUtils.getExecutionCopy;
import static org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ISMUtils.getStatsCopy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.eclipse.jetty.server.Handler;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.AgentInfoHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.IndexHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7AbstractHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.SessionLogsHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.TestSuitesHandler;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution.ReportUtils;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class SuiteHandler extends Handler.Abstract {
	public static final String URI = "/info/suite";

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {
		try (PrintWriter writer = Q7AbstractHandler.htmlWriter(response)) {

		String suiteID = request.getHttpURI().getQuery();

		writer.write(Q7HttpUtils.getHeader());

		String responseContent = Q7HttpUtils
				.getResource("/webroot/templates/testsuites.xml");

		StringBuilder buffer = new StringBuilder();

		if (suiteID != null && suiteID.trim().length() > 0) {
			ISMHandle<SuiteStats> handle = ISMCore.getInstance()
					.getSuiteStore().getHandle(suiteID);
			if (handle.exists()) {
				SuiteStats stats = handle.apply(getStatsCopy);
				buffer.append("Detailed suite information: "
						+ stats.getSuiteName() + "<br>");

				buffer.append("<div id=\"content\"><div id=\"tableContainer\">");

				buffer.append("<table>");
				buffer.append("<tr class=\"th\">");
				buffer.append("<th>ID</th>");
				buffer.append("<th>Start Date</th>");
				buffer.append("<th>Execution time</th>");
				buffer.append("<th>Tests count</th>");
				buffer.append("<th>Tests failed</th>");
				buffer.append("<th></th>");
				buffer.append("</tr>");
				ISMHandleStore<Execution> execsStore = ExecutionRegistry
						.getInstance().getExecutions(handle);
				List<ISMHandle<Execution>> executions = ExecutionRegistry
						.getFilteredHandles(execsStore.getHandles(), null);
				Collections.reverse(executions);
				for (ISMHandle<Execution> execution : executions) {
					Execution exec = execution.apply(getExecutionCopy);
					String detailsURI = ExecutionHandler.URI + "?" + suiteID
							+ "&" + execution.getFileName();
					buffer.append("<tr>");
					buffer.append("<td><a href=\"" + detailsURI + "\">"
							+ execution.getFileName() + "</a></td>");
					buffer.append("<td>"
							+ (new Date(exec.getStartTime()).toString())
							+ "</td>");
					String time = ReportUtils.getTimeMins(exec.getEndTime()
							- exec.getStartTime());
					if (exec.getEndTime() == 0) {
						time = "Running now";
					}
					buffer.append("<td>" + time + "</td>");
					buffer.append("<td>" + exec.getTotalCount() + "</td>");
					buffer.append("<td>" + exec.getFailedCount() + "</td>");
					buffer.append("<td><a href=\"" + detailsURI
							+ "\">Details</a></td>");
					buffer.append("</tr>");
				}
				buffer.append("</table></div></div>");
			} else {
				buffer.append("Unknown suiteId specified...");
			}
		}

		writer.write(Q7HttpUtils.applyParams(responseContent, new String[] {
				"generalURI", "testSuitesURI", "agentsURI", "sessionLogsURI",
				"responseContent" }, new String[] { IndexHandler.URI,
				TestSuitesHandler.URI, AgentInfoHandler.URI,
				SessionLogsHandler.URI, buffer.toString() }));

		writer.write(Q7HttpUtils.getFooter());
	}
		callback.succeeded();
		return true;
	}
}
