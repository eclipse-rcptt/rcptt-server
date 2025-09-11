/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArtifactServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "application/octet-stream";

	public interface Entry {
		InputStream getContents();
		long size();
	}

	public interface Repository {
		Optional<Entry> get(String hash);
		void putIfAbsent(String hash, InputStream data);
	}

	private final Repository repository;

	public ArtifactServlet(Repository repository) {
		super();
		this.repository = Objects.requireNonNull(repository);
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hash = getHash(req);
		Entry entry = repository.get(hash).orElse(null);
		if (entry == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hash = getHash(req);
		Entry entry = repository.get(hash).orElse(null);
		if (entry == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		resp.setContentLengthLong(entry.size());
		resp.setContentType(CONTENT_TYPE);
		try (OutputStream os = resp.getOutputStream()) {
			try (InputStream is = entry.getContents()) {
				is.transferTo(os);
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hash = getHash(req);
		if (!CONTENT_TYPE.equalsIgnoreCase(req.getContentType())) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		if (repository.get(hash).isPresent()) {
			resp.sendError(HttpServletResponse.SC_CONFLICT, "A file with hash " + hash + " has been uploaded before");
			return;
		}
		try (ServletInputStream inputStream = req.getInputStream()) {
			try {
				repository.putIfAbsent(hash, inputStream);
				if (!inputStream.isFinished()) {
					resp.sendError(HttpServletResponse.SC_CONFLICT, "A file with hash " + hash + " has been uploaded before");
					return;					
				}
			} catch (IllegalArgumentException e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getLocalizedMessage());
			}
		}
	}

	private String getHash(HttpServletRequest req) {
		String path = req.getPathInfo();
		assert path.startsWith("/");
		path = path.substring(1);
		return path;
	}

	private static final long serialVersionUID = -3131705220846105409L;
}
