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

import static java.util.Objects.requireNonNull;
import static org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ISMUtils.getExecutionCopy;
import static org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ISMUtils.getStatsCopy;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.rcptt.cloud.server.IReports;
import org.eclipse.rcptt.cloud.server.app.ReportHelper;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7AbstractHandler;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.reporting.util.ReportEntry;
import org.eclipse.rcptt.reporting.util.ReportUtils;

import com.google.common.html.HtmlEscapers;

public class ExecutionHandler extends Q7AbstractHandler {
	public static final String URI = "/info/execution";
	private final IReports reports;

	public ExecutionHandler(IReports reports) {
		super();
		this.reports = requireNonNull(reports);
	}

	@Override
	public boolean handle(Request request, Response response, Callback callback)
			throws IOException {

		String[] split = request.getHttpURI().getQuery().split("&");
		String suiteID = split[0];
		String id = split[1];

		String responseContent = Q7HttpUtils
				.getResource("/webroot/templates/testsuites.xml");

		StringBuilder buffer = new StringBuilder();

		if (suiteID != null && suiteID.trim().length() > 0) {
			ISMHandle<SuiteStats> handle = ISMCore.getInstance()
					.getSuiteStore().getHandle(suiteID);
			ISMHandle<Execution> execHandle = null;
			if (handle.exists()) {
				SuiteStats stats = handle.apply(getStatsCopy);
				ISMHandleStore<Execution> executions = getExecutionRegistry().getExecutions(handle);
				Execution exec = null;
				if (executions.containsHandle(id)) {
					execHandle = executions.getHandle(id);
					exec = execHandle.apply(getExecutionCopy);
				}
				if (exec != null) {

					buffer.append("Detailed session information: ");

					buffer.append("<div id=\"content\"><div id=\"tableContainer\">");

					buffer.append("<table>");
					buffer.append("<tr class=\"th\">");
					buffer.append("<th>Property</th>");
					buffer.append("<th>Value</th></tr>");
					row(buffer, "Suite Name", stats.getSuiteName());
					row(buffer, "Session", ""+new Date(exec.getStartTime()));

					row(buffer, "Total tests", ""+exec.getTotalCount());
					row(buffer, "Failed tests", ""+exec.getFailedCount());
					row(buffer, "Total time", ReportUtils.formatTime(ReportHelper.getCloudTime(exec)));
					row(buffer, "First report time", ReportUtils.formatTime(ReportHelper.firstReportTime(exec)));
					buffer.append("</table></div></div>");
					buffer.append("<a href=\"/artifacts/" + suiteID + "/" + id
							+ "\">Produced artifacts</a><br>");
					buffer.append("Test results ");
					buffer.append("<a href=\"" + ExecutionByAgentsHandler.URI
							+ "?" + suiteID + "&" + id
							+ "\">(View per agent):</a><br>");

					buffer.append("<div id=\"content\"><div id=\"tableContainer\">");

					buffer.append("<table>");
					buffer.append("<tr class=\"th\">");
					buffer.append("<th>Name</th>");
					buffer.append("<th>Status</th>");
					buffer.append("<th>Execution Time</th>");
					buffer.append("<th>Message</th>");
					buffer.append("</tr>");

					if (exec.getReportFile() == null) {
						buffer.append("<tr><td>No report</td></tr>");
					} else {

						try (Stream<ReportEntry> entries = reports.readReports(execHandle.getFileRoot().toPath().resolve(exec.getReportFile()))) {

							var iterator = entries.iterator();
							StringBuilder tempBuffer = new StringBuilder();
							StringBuilder passedBuffer = new StringBuilder();

							while (iterator.hasNext()) {
								tempBuffer = new StringBuilder();
								ReportEntry report = iterator.next();
								if (report == null) {
									break;
								}
								tempBuffer.append("<tr>");
								String testURI = TestHandler.URI + "?" + suiteID
										+ "&" + id + "&" + report.id;

								tempBuffer.append("<td>" + "<a href=\"" + testURI
										+ "\">" + report.name
										+ "</a></td>");
								String out =  ImageUtil.getSeverityImageTag(report.getSimpleSeverity());

								tempBuffer.append("<td>" + out + "</td>");
								tempBuffer.append("<td>"
										+ ReportUtils.formatTime(report.time)
										+ "</td>");
								String msg =  ReportUtils.replaceLineBreaks(HtmlEscapers.htmlEscaper().escape(report.message));
								tempBuffer.append("<td>"
										+ (msg == null ? " " : msg) + "</td>");
								tempBuffer.append("</tr>");
								if (report.getSimpleSeverity() == SimpleSeverity.ERROR) {
									buffer.append(tempBuffer);
								} else {
									passedBuffer.append(tempBuffer.toString());
								}
							}
							buffer.append(passedBuffer.toString());
						}
					}
					buffer.append("</table>");
					buffer.append("</div></div>");
				}
			} else {
				buffer.append("Unknown suiteId specified...");
			}
		}
		writeMenuAndContent(response, responseContent, buffer);
		callback.succeeded();
		return true;
	}
	
	private void row(Appendable builder, String column1, String column2) throws IOException {
		builder.append("<tr><td>").append(column1).append("</td><td>").
				append(column2).append("</td></tr>");

	}
}
