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
import java.net.URI;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;

@SuppressWarnings("serial")
public class Q7SessionUploadService extends HttpServlet {
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
		ExecutionEntry artifacts = ExecutionRegistry.getInstance()
				.getSuiteHandle(suiteID);
		if (artifacts == null) {
			outp.print("No suite " + suiteID);
		} else {
			ServletInputStream stream = req.getInputStream();
			URI location = artifacts.recieveAUT(stream, fileName, unZip);
			outp.print(location);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
