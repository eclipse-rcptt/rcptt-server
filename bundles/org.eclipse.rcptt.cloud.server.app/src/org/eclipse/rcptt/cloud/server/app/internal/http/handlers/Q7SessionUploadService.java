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

public class Q7SessionUploadService extends HttpServlet {
	private static final long serialVersionUID = -2428089214664215537L;
	private final IServerContext context;

	public Q7SessionUploadService(IServerContext context) {
		this.context = requireNonNull(context);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try (PrintWriter outp = resp.getWriter()) {
			String suiteID = req.getHeader("suiteid");
			String unZip = req.getHeader("unzip");
			String fileName = req.getHeader("filename");
			String contentType = req.getContentType();

			if (!"application/q7-filedata".equals(contentType)) {
				// TODO: in 2.8.0 return jakarta.servlet.http.HttpServletResponse.SC_NOT_ACCEPTABLE or otherwise a proper error 
				return;
			}
			
			final ExecutionRegistry executions = context.getExecutionRegistry();
			ExecutionEntry artifacts = executions.getSuiteHandle(suiteID);
			if (artifacts == null) {
				// TODO: in 2.8.0 return jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND or otherwise a proper error
				outp.print("No suite " + suiteID);
			} else {
				try (ServletInputStream stream = req.getInputStream()) {
					URI location = executions.makeRelativePath(artifacts.recieveAUT(stream, fileName, unZip));
					// TODO: in 2.8.0 add prefix "artifacts" here, remove from client
					// Client should not be aware of server layout and should be provided with complete URLs
					// In other words, partial URLs should not be used in network communication
					// @see org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpServer.initializeArtifactsFileStore()
					outp.print(location);
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
