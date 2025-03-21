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
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.io.Content.Sink;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Response;

import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;

public abstract class Q7AbstractHandler extends Handler.Abstract {

	public Q7AbstractHandler() {
		super();
	}

	protected void writeMenuAndContent(Response response,
			String declaredContent, StringBuilder buffer) throws IOException {
		try (PrintWriter writer = htmlWriter(response)) {
			writer.write(Q7HttpUtils.getHeader());
			writer.write(Q7HttpUtils.applyParams(declaredContent, new String[] {
					"generalURI", "testSuitesURI", "agentsURI", "sessionLogsURI",
					"responseContent" }, new String[] { IndexHandler.URI,
					TestSuitesHandler.URI, AgentInfoHandler.URI,
					SessionLogsHandler.URI, buffer.toString() }));
	
			writer.write(Q7HttpUtils.getFooter());
		}
	}

	public String tag(String tag, String content) {
		return "<" + tag + ">" + content + "</" + tag + ">\n";
	}

	public String th(String content) {
		return tag("th", content);
	}

	public String td(String content) {
		return tag("td", content);
	}

	public String td(int content) {
		return tag("td", Integer.toString(content));
	}

	public String tr(String content) {
		return tag("th", content);
	}
	
	public static PrintWriter htmlWriter(Response response) {
		response.getHeaders().put(HttpHeader.CONTENT_TYPE, "text/html;charset=utf-8");
		response.setStatus(HttpStatus.OK_200);
		return new PrintWriter(Sink.asOutputStream(response), false, StandardCharsets.UTF_8);
	}
	
	public static void sendPng(Response response, byte[] data) throws IOException {
		response.setStatus(HttpStatus.OK_200);
		response.getHeaders().put(HttpHeader.CONTENT_TYPE, "image/png");
		response.getHeaders().put(HttpHeader.CONTENT_LENGTH, data.length);
		Sink.write(response, true, ByteBuffer.wrap(data));
	}

}
