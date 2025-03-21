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

import static org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ISMUtils.getStatsCopy;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.stats.SuiteHandler;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class TestSuitesHandler extends Q7AbstractHandler {
	public static final String URI = "/testSuites";

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {
		List<ISMHandle<SuiteStats>> handles = ISMCore.getInstance()
				.getSuiteStore().getHandles();

		String responseContent = Q7HttpUtils
				.getResource("/webroot/templates/testsuites.xml");

		StringBuilder buffer = new StringBuilder();

		buffer.append("<div id=\"content\"><div id=\"tableContainer\"><table>");

		buffer.append("<tr class=\"th\">").append(th("ID"))
				.append(th("Suite Name")).append(th("Executions"))
				.append("</tr>");
		int id = 1;
		Collections.sort(handles, new Comparator<ISMHandle<SuiteStats>>() {

			public int compare(ISMHandle<SuiteStats> arg0,
					ISMHandle<SuiteStats> arg1) {

				SuiteStats a1 = arg0.apply(getStatsCopy);
				SuiteStats a2 = arg1.apply(getStatsCopy);
				if (a1.getSuiteName() != null && a2.getSuiteName() != null) {
					return a1.getSuiteName().compareToIgnoreCase(a2.getSuiteName());
				}
				return 0;
			}
		});

		for (ISMHandle<SuiteStats> st : handles) {
			if (st.exists()) {
				buffer.append("<tr>");
				buffer.append(td(Integer.toString(id)));
				SuiteStats stat = st.apply(getStatsCopy);
				buffer.append("<td>");
				String suiteID = SuiteHandler.URI + "?" + st.getFileName();

				buffer.append("<a href=\"" + suiteID + "\">"
						+ "<img src='/images/tests/tsuite.gif'/> "
						+ stat.getSuiteName() + "</a></td>");
				buffer.append(td(stat.getLastSuiteID()));
				buffer.append("</tr>");
			}
			id++;
		}
		buffer.append("</table></div></div>");

		writeMenuAndContent(response, responseContent, buffer);
		callback.succeeded();
		return true;
	}
}
