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
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;


import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.html.FullSingleTestHtmlRenderer;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Screenshot;
import org.eclipse.rcptt.sherlock.core.streams.SherlockReportIterator;

import com.google.common.base.Function;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.AgentInfoHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.IndexHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7AbstractHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.SessionLogsHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.TestSuitesHandler;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class TestHandler extends Handler.Abstract {
	public static final String URI = "/info/test";

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {

		String query = request.getHttpURI().getQuery();
		String[] split = query.split("&");
		String suiteID = split[0];
		String executionID = split[1];
		String reportID = split[2];
		String screenshotID = null;
		if (split.length == 4) {
			// This is screenshot
			if (split[3].startsWith("screenshot=")) {
				screenshotID = split[3].substring(11);
			}
		}

		String responseContent = Q7HttpUtils
				.getResource("/webroot/templates/testsuites.xml");

		StringWriter stringWriter = new StringWriter();
		try (PrintWriter w = new PrintWriter(stringWriter)) {
		if (suiteID != null && suiteID.trim().length() > 0) {
			ISMHandle<SuiteStats> handle = ISMCore.getInstance()
					.getSuiteStore().getHandle(suiteID);
			ISMHandle<Execution> execHandle = null;
			if (handle.exists()) {
				SuiteStats stats = handle.apply(getStatsCopy);
				ISMHandleStore<Execution> executions = ExecutionRegistry
						.getInstance().getExecutions(handle);

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
							final List<Screenshot> screenShots = ReportUtils.findScreenshots(resultReport.getRoot());
							if (screenshotID != null) {
								try {
									int id = Integer.parseInt(screenshotID);
									if (!sendScreenShot(response, screenShots, id)) {
										Response.writeError(request, response, callback, HttpStatus.NOT_FOUND_404);
									}
								} catch (IOException ex) {
									Response.writeError(request, response, callback, HttpStatus.NOT_FOUND_404);
									ServerPlugin.logErr(ex, "Failed to find screenshot %s", request.getHttpURI());
								}
								callback.succeeded();
								return true;
							}
							w.println("Suite Name: " + stats.getSuiteName() + "<br>");
							w.println("Global Suite index: " + stats.getSuiteName() + "<br>");
							w.println("Session: " + new Date(exec.getStartTime()) + "<br>");
							FullSingleTestHtmlRenderer renderer = new FullSingleTestHtmlRenderer(
									w, DecimalFormat.getInstance(),
									new Function<Screenshot, String>() {
										@Override
										public String apply(Screenshot input) {
											return request.getHttpURI().asString() + "&screenshot="
													+ screenShots.indexOf(input);
										}
									});
							renderer.render(resultReport);
						}
					} finally {
						iterator.close();
					}
				}
			} else {
				w.println("Unknown suiteId specified...");
			}
		}
		}

		try (PrintWriter writer = Q7AbstractHandler.htmlWriter(response)) {
			writer.write(Q7HttpUtils.getHeader());
			writer.write(Q7HttpUtils.applyParams(responseContent, new String[] {
					"generalURI", "testSuitesURI", "agentsURI", "sessionLogsURI",
					"responseContent" }, new String[] { IndexHandler.URI,
					TestSuitesHandler.URI, AgentInfoHandler.URI,
					SessionLogsHandler.URI, stringWriter.toString() }));
	
			writer.write(Q7HttpUtils.getFooter());
		}
		callback.succeeded();
		return true;
	}

	private static boolean sendScreenShot(Response response, final List<Screenshot> screenShots, int id)
			throws IOException {
		if (id >= 0 && id < screenShots.size()) {
			byte[] data = screenShots.get(id).getData();
			Q7AbstractHandler.sendPng(response, data);
			return true;
		}
		return false;
	}
}
