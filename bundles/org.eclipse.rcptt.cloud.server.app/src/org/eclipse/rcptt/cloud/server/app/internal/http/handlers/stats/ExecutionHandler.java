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
import java.util.Date;

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
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7AbstractHandler;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class ExecutionHandler extends Q7AbstractHandler {
	public static final String URI = "/info/execution";

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
				ISMHandleStore<Execution> executions = ExecutionRegistry
						.getInstance().getExecutions(handle);
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
					buffer.append("<tr><td>Suite Name</td><td>"
							+ stats.getSuiteName() + "</td></tr>");
					buffer.append("<tr><td>Session</td><td>"
							+ new Date(exec.getStartTime()) + "</td></tr>");

					buffer.append("<tr><td>Total tests</td><td>"
							+ exec.getTotalCount() + "</td></tr>");
					buffer.append("<tr><td>Failed tests</td><td>"
							+ exec.getFailedCount() + "</td></tr>");
					buffer.append("<tr><td>Total time</td><td>"
							+ ReportUtils.formatTime(exec.getEndTime()
									- exec.getStartTime()) + "</td></tr>");
					buffer.append("<tr><td>First report time</td><td>"
							+ ReportUtils.formatTime(exec.getFirstReportTime()
									- exec.getStartTime()) + "</td></tr>");
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
						File reportFile = new File(execHandle.getFileRoot(),
								exec.getReportFile());
						SherlockReportIterator iterator = new SherlockReportIterator(
								reportFile);
						try {

							StringBuilder tempBuffer = new StringBuilder();
							StringBuilder passedBuffer = new StringBuilder();

							while (iterator.hasNext()) {
								tempBuffer = new StringBuilder();
								Report report = iterator.next();
								if (report == null) {
									break;
								}
								Object root = report.getRoot().getProperties()
										.get(IQ7ReportConstants.ROOT);

								Q7Info info = (Q7Info) root;
								tempBuffer.append("<tr>");
								String testURI = TestHandler.URI + "?" + suiteID
										+ "&" + id + "&" + info.getId();

								tempBuffer.append("<td>" + "<a href=\"" + testURI
										+ "\">" + report.getRoot().getName()
										+ "</a></td>");
								SimpleSeverity severity = SimpleSeverity.create(info);
								String out =  ImageUtil.getSeverityImageTag(severity);

								tempBuffer.append("<td>" + out + "</td>");
								tempBuffer.append("<td>"
										+ ReportUtils.getTime(report.getRoot())
										+ "</td>");
								String msg = ReportUtils.getFailMessage(report
										.getRoot());
								tempBuffer.append("<td>"
										+ (msg == null ? " " : msg) + "</td>");
								tempBuffer.append("</tr>");
								if (severity == SimpleSeverity.ERROR) {
									buffer.append(tempBuffer);
								} else {
									passedBuffer.append(tempBuffer.toString());
								}
							}
							buffer.append(passedBuffer.toString());

						} finally {
							iterator.close();
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
}
