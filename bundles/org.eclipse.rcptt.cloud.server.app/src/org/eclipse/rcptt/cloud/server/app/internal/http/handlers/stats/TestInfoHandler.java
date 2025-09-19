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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.sherlock.core.streams.SherlockReportIterator;

import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.app.ContextEscape;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7AbstractHandler;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class TestInfoHandler extends Handler.Abstract {
	public static final String URI = "/stats/suite/test";

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {
		try (PrintWriter writer =  Q7AbstractHandler.htmlWriter(response)) {
		String[] split = request.getHttpURI().getQuery().split("&");
		String suiteID = split[0];
		String executionID = split[1];
		String reportID = split[2];

		writer.write(Q7HttpUtils.getResource("/webroot/templates/stat_header"));
		if (suiteID != null && suiteID.trim().length() > 0) {
			ISMHandle<SuiteStats> handle = ISMCore.getInstance()
					.getSuiteStore().getHandle(suiteID);
			ISMHandle<Execution> execHandle = null;
			if (handle.exists()) {
				SuiteStats stats = handle.apply(getStatsCopy);
				ISMHandleStore<Execution> executions = ContextEscape.getExecutionRegistry(getServer()).getExecutions(handle);

				Execution exec = null;
				if (executions.containsHandle(executionID)) {
					execHandle = executions.getHandle(executionID);
					exec = execHandle.apply(getExecutionCopy);
				}
				if (exec != null) {

					File reportFile = new File(execHandle.getFileRoot(),
							exec.getReportFile());
					SherlockReportIterator iterator = new SherlockReportIterator(
							reportFile);
					try {
						Report resultReport = null;
						while (iterator.hasNext()) {
							Report report = iterator.next();
							Object root = report.getRoot().getProperties()
									.get(IQ7ReportConstants.ROOT);
							Q7Info info = (Q7Info) root;
							if (info.getId().equals(reportID)) {
								resultReport = report;
								break;
							}
						}

						if (resultReport != null) {
							Object root = resultReport.getRoot()
									.getProperties()
									.get(IQ7ReportConstants.ROOT);
							Q7Info info = (Q7Info) root;
							writer.print("Detailed test information:<br/>");
							writer.print("<div id=\"content\"><div id=\"tableContainer\">");

							writer.print("<table>");
							writer.print("<tr class=\"th\">");
							writer.print("<th>Property</th>");
							writer.print("<th>Value</th></tr>");
							writer.print("<tr><td>Suite Name</td><td>"
									+ stats.getSuiteName() + "</td></tr>");
							writer.print("<tr><td>Session</td><td>"
									+ new Date(exec.getStartTime())
									+ "</td></tr>");
							writer.print("<tr><td>Test name</td><td>"
									+ resultReport.getRoot().getName()
									+ "</td></tr>");

							writer.print("<tr><td>Execution Time</td><td>"
									+ ReportUtils.getTime(resultReport
											.getRoot()) + "</td></tr>");
							SimpleSeverity severity = SimpleSeverity.create(info);
							String out = ImageUtil.getSeverityImageTag(severity);
							writer.print("<tr><td>Execution status</td><td>"
									+ out + "</td></tr>");
							writer.print("</table></div>");
							String message = ReportUtils
									.getFailMessage(resultReport.getRoot());
							if (message.trim().length() > 0) {
								writer.print("Message:<br><pre>" + message
										+ "</pre>");
							}
							writer.print("</div>");
						}
					} finally {
						iterator.close();
					}
				}
			} else {
				writer.print("Unknown suiteId specified...");
			}
		}
		writer.write(Q7HttpUtils.getResource("/webroot/templates/stat_footer"));
		}
		callback.succeeded();
		return true;
	}
}
