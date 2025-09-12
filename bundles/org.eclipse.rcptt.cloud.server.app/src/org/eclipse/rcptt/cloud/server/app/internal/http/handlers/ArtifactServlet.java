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

/**
 * REST API for an unmodifiable key-value storage, where values are potentially large.
 * Overwrites and deletes are not supported, however, the storage may be remove entries by undefined internal processes.  
 * 
 * Sample PUT: 
 * 
 * <pre>
 *     time curl -v -v -H "Content-Type: application/octet-stream" -H "Expect: 100-continue" --upload-file "$1" http://127.0.0.1:5007/api/cache/`shasum -b -a 256 "$1" | cut -d " " -f 1 `
 * </pre>
 * 
 */
public class ArtifactServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "application/octet-stream";

	public interface Entry {
		InputStream getContents();

		long size();
	}

	public interface Repository {
		Optional<Entry> get(String key);
		/**
		 * Atomically update the storage, do nothing if the content with a given key already exists
		 * @param key - should match data
		 * @param data - content to store
		 * @throws IllegalArgumentException - if data is invalid/corrupted, for example if key is considered a hash and does not match the content
		 */
		void putIfAbsent(String key, InputStream data);
	}

	private final Repository repository;

	public ArtifactServlet(Repository repository) {
		super();
		this.repository = Objects.requireNonNull(repository);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = getKey(req);
		Entry entry = repository.get(key).orElse(null);
		if (entry == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		resp.setContentLengthLong(entry.size());
		resp.setContentType(CONTENT_TYPE);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = getKey(req);
		Entry entry = repository.get(key).orElse(null);
		if (entry == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
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
		String key = getKey(req);
		if (!CONTENT_TYPE.equalsIgnoreCase(req.getContentType())) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		// Early check for an existing key allows HTTP to prevent content upload
		if (repository.get(key).isPresent()) {
			resp.sendError(HttpServletResponse.SC_CONFLICT, "A file " + key + " has been uploaded before");
			return;
		}
		// req.getInputStream() should only be called after existing key check, as it resumes 100-continue protocol
		// If the protocol is resumed, client starts sending unnecessary data and the only way to cancel that is to drop connection
		// Dropped connection is treated by HTTP client libraries unfavorably 
		try (ServletInputStream inputStream = req.getInputStream()) {
			try {
				repository.putIfAbsent(key, inputStream);
				if (!inputStream.isFinished()) {
					resp.sendError(HttpServletResponse.SC_CONFLICT,
							"A file " + key + " has been uploaded before");
					return;
				}
			} catch (IllegalArgumentException e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getLocalizedMessage());
			}
		}
	}

	private String getKey(HttpServletRequest req) {
		String path = req.getPathInfo();
		assert path.startsWith("/");
		path = path.substring(1);
		return path;
	}

	private static final long serialVersionUID = -3131705220846105409L;
}
