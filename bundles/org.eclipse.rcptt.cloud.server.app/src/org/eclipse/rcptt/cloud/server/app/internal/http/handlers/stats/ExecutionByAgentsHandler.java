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

import static com.google.common.base.Preconditions.checkNotNull;
import static org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ISMUtils.getExecutionCopy;
import static org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ISMUtils.getStatsCopy;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.reporting.util.Q7ReportIterator;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Node;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.app.internal.ServerAppPlugin;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7AbstractHandler;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class ExecutionByAgentsHandler extends Q7AbstractHandler {
	public static final String URI = "/info/execution_agents";

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {

		String[] split = request.getHttpURI().getQuery().split("&");
		String suiteID = split[0];
		String executionID = split[1];

		String responseContent = Q7HttpUtils
				.getResource("/webroot/templates/testsuites.xml");

		final StringBuilder buffer = new StringBuilder();
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
					File reportFile = new File(execHandle.getFileRoot(), exec.getReportFile());
					Q7ReportIterator iterator = new Q7ReportIterator(reportFile);
					try {
						buffer.append(formReport(suiteID, executionID, stats, exec, iterator.iterator()));
					} catch (RuntimeException e) {
						buffer.append(ReportUtil.toString(e));
						log(makeStatus(e, "Failed to process " + reportFile));
					} finally {
						iterator.close();
					}
				}
			} else {
				buffer.append("Unknown suiteId specified...");
			}
		}

		writeMenuAndContent(response, responseContent, buffer);
		callback.succeeded();
		return true;
	}

	private Status makeStatus(RuntimeException e, String message) {
		return new Status(IStatus.ERROR, ServerAppPlugin.PLUGIN_ID, message, e);
	}

	private static void log(Status status) {
		ServerAppPlugin.getDefault().getLog().log(status);
	}

	private StringBuilder formReport(String suiteID, String executionID, SuiteStats stats, Execution exec,
			Iterator<Report> iterator) {
		checkNotNull(iterator);
		checkNotNull(suiteID);
		checkNotNull(executionID);
		checkNotNull(stats);
		checkNotNull(exec);

		StringBuilder buffer = new StringBuilder();
		buffer.append("Detailed session information:");

		buffer.append("<div id=\"content\"><div id=\"tableContainer\">");

		buffer.append("<table>");
		buffer.append("<tr class=\"th\">");
		buffer.append(th("Property"));
		buffer.append(th("Value"));

		buffer.append(tr(td("Suite Name")
				+ td(stats.getSuiteName())));

		buffer.append(tr(td("Session")
				+ td(new Date(exec.getStartTime()).toString())));

		buffer.append(tr(td("Total tests")
				+ td(exec.getTotalCount())));

		buffer.append(tr(td("Failed tests")
				+ td(exec.getFailedCount())));

		buffer.append(tr(td("Total time")
				+ td(ReportUtils.formatTime(exec.getEndTime()
						- exec.getStartTime()))));

		buffer.append(tr(td("First report time")
				+ td(ReportUtils.formatTime(exec
						.getFirstReportTime() - exec.getStartTime()))));

		buffer.append("</table></div></div>");

		int id = 1;
		Map<String, String> idToMessage = new HashMap<String, String>();
		Map<String, Long> idToTime = new HashMap<String, Long>();
		while (iterator.hasNext()) {
			Report report = iterator.next();
			if (report == null)
				continue;
			Node rootNode = report.getRoot();
			checkNotNull(rootNode);
			try {
				EMap<String, EObject> rootProperties = rootNode.getProperties();
				Object root = rootProperties
						.get(IQ7ReportConstants.ROOT);
				Q7Info info = (Q7Info) root;
				idToTime.put(info.getId(), rootNode.getEndTime() - rootNode.getStartTime());

				StringBuilder data = new StringBuilder();
				String testID = TestHandler.URI + "?" + suiteID
						+ "&" + executionID + "&" + info.getId();

				data.append(td("<a href=\"" + testID + "\">" + rootNode.getName() + "</a>"));
				final String out = ImageUtil.getSeverityImageTag(SimpleSeverity.create(info));

				data.append(td(out));
				data.append(td(ReportUtils.getTime(rootNode)));
				String msg = ReportUtils.getFailMessage(rootNode);
				data.append(td(msg == null ? " " : msg));
				idToMessage.put(info.getId(), data.toString());
			} catch (RuntimeException e) {
				throw new RuntimeException("Failed to process report " + rootNode.getName(), e);
			}
		}
		buffer.append("<a href=\"" + ExecutionHandler.URI + "?"
				+ suiteID + "&" + executionID
				+ "\">(View all tests)</a><br>");

		buffer.append("<div id=\"content\"><div id=\"tableContainer\">");
		buffer.append(tag("b", "Agents used in session:") + "<br/>");
		buffer.append("<table>");
		buffer.append("<tr class=\"th\">");
		buffer.append(th("ID"));
		buffer.append(th("AgentURI"));
		buffer.append(th("Total tests"));
		buffer.append(th("Failed tests"));
		buffer.append(th("Execution time"));
		buffer.append("</tr>");
		EList<ExecutionAgentStats> agentStats = exec
				.getAgentStats();
		int agentID = 1;
		for (ExecutionAgentStats agentStat : agentStats) {
			buffer.append("<tr>");
			buffer.append(td(agentID));
			buffer.append(td(agentStat.getAgentID()));
			long totalTime = 0;
			int failedTests = 0;
			for (ExecutionAgentTest executionAgentTest : agentStat
					.getTests()) {
				Long time = idToTime.get(executionAgentTest.getTestID());
				if (time == null) {
					log(makeStatus(IStatus.ERROR,
							"Failed to get time information for test " + executionAgentTest.getTestID()));
				} else {
					totalTime += time;
				}
				if (!executionAgentTest.getStatus().equals(
						ExecutionAgentTestStatus.PASS)) {
					failedTests++;
				}
			}

			buffer.append(td(agentStat.getTests().size()));
			buffer.append(td(failedTests));
			buffer.append(td(ReportUtils.formatTime(totalTime)));
			buffer.append("</tr>");
			agentID++;
		}

		buffer.append("</table></div></div>");

		for (ExecutionAgentStats executionAgentStats : agentStats) {
			buffer.append("<br/> Agent <b>"
					+ executionAgentStats.getAgentID()
					+ "</b> execution statistics");

			EList<ExecutionAgentTest> tests = executionAgentStats
					.getTests();
			long totalTime = 0;
			int failedTests = 0;
			for (ExecutionAgentTest executionAgentTest : tests) {
				Long time = idToTime.get(executionAgentTest
						.getTestID());
				if (time != null)
					totalTime += time;
				if (!executionAgentTest.getStatus().equals(
						ExecutionAgentTestStatus.PASS)) {
					failedTests++;
				}
			}

			buffer.append("<div id=\"content\"><div id=\"tableContainer\">");

			buffer.append("<table>");
			buffer.append("<tr class=\"th\">");
			buffer.append("<th>Property</th>");
			buffer.append("<th>Value</th></tr>");
			buffer.append("<tr><td>Total tests</td><td>"
					+ executionAgentStats.getTests().size()
					+ "</td></tr>");
			buffer.append("<tr><td>Failed tests</td><td>"
					+ failedTests + "</td></tr>");
			buffer.append("<tr><td>Total time</td><td>"
					+ ReportUtils.formatTime(totalTime)
					+ "</td></tr>");
			buffer.append("</table>");

			buffer.append("<table>");
			buffer.append("<tr class=\"th\">");
			buffer.append("<th>ID</th>");
			buffer.append("<th>Name</th>");
			buffer.append("<th>Status</th>");
			buffer.append("<th>Execution Time</th>");
			buffer.append("<th>Message</th>");
			buffer.append("</tr>");

			id = 1;
			for (ExecutionAgentTest executionAgentTest : tests) {
				String data = idToMessage.get(executionAgentTest
						.getTestID());
				buffer.append("<tr>");
				buffer.append("<td>" + Integer.toString(id)
						+ "</td>");
				buffer.append(data);
				buffer.append("</tr>");
				id++;

			}
			buffer.append("</table>");
			buffer.append("</div></div>");
		}
		return buffer;
	}

	private Status makeStatus(int severity, String message) {
		return new Status(severity, ServerAppPlugin.PLUGIN_ID, message);
	}
}
