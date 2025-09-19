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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.IServerContext;

@SuppressWarnings("serial")
public class Q7SessionUploadService extends HttpServlet {

	private final IServerContext context;

	public Q7SessionUploadService(IServerContext context) {
		this.context = requireNonNull(context);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter outp = resp.getWriter();

		String suiteID = req.getHeader("suiteid");

		String unZip = req.getHeader("unzip");

		String fileName = req.getHeader("filename");
		String contentType = req.getContentType();
		if (!"application/q7-filedata".equals(contentType)) {
			outp.write("fail");
			return;
		}
		ExecutionEntry artifacts = context.getExecutionRegistry().getSuiteHandle(suiteID);
		if (artifacts == null) {
			outp.print("No suite " + suiteID);
		} else {
			ServletInputStream stream = req.getInputStream();
			URI location = context.toUri(artifacts.recieveAUT(stream, fileName, unZip));
			outp.print(location);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
