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

import java.io.IOException;


import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.EclServerImplPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskSuiteDescriptor;

public class IndexHandler extends Q7AbstractHandler {
	public static String URI = "/";

	@Override
	public boolean handle(Request request, Response response, Callback callback)
			throws IOException {

		if (!Request.getPathInContext(request).equals(URI)) {
			return false;
		}

		String declaredContent = Q7HttpUtils
				.getResource("/webroot/templates/index.xml");
		StringBuilder buffer = new StringBuilder();

		buffer.append("Running suites:<br/>");
		buffer.append("<div id=\"content\">\n"
				+ "<div id=\"tableContainer\">\n"
				+ "<table><tr class=\"th\">\n");

		buffer.append(th("Name"));
		buffer.append(th("Tasks left"));
		buffer.append(th("Total tasks"));
		buffer.append(th("Failed tasks"));
		buffer.append(th("Agents in use"));
		buffer.append(th("Agents max"));
		buffer.append(th("Actions"));
		java.util.Collection<TaskSuiteDescriptor> suites = getTaskQueue()
				.getSuiteDescriptors();
		for (TaskSuiteDescriptor suite : suites) {

			String terminateURI = TerminateSessionHandle.URI + "?" + suite.getSuiteId();
			buffer.append("<tr>")
					.append(td(suite.getSuiteId()))
					.append(td(suite.getTaskCount()))
					.append(td(suite.totalTaskCount()))
					.append(td(suite.getFailedTaskCount()))
					.append(td(suite.getRunningAgents()))
					.append(td(suite.getAgentCount()))
					.append(td(" <a href=\"" + terminateURI
							+ "\">Terminate</a>")).append("</tr>");
		}
		buffer.append("</table></div></div>");

		writeMenuAndContent(response, declaredContent, buffer);
		callback.succeeded();
		return true;
	}

	private TaskQueue getTaskQueue() {
		return EclServerImplPlugin.getDefault().getTaskQueue();
	}
}
