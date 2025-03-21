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
package org.eclipse.rcptt.cloud.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import com.google.common.io.ByteStreams;
import org.eclipse.rcptt.cloud.util.IOUtil;

public class AutTcpConnector {

	private String host;
	private List<Aut> auts;
	private List<Acceptor> acceptors;

	public AutTcpConnector() {
		super();
	}

	public String createAutUri() throws UnsupportedEncodingException {
		if (auts == null) {
			throw new IllegalStateException("AutTcpConnector stopped.");
		}

		StringBuilder params = new StringBuilder();
		char delimiter = '?';
		for (Aut aut : auts) {
			params.append(delimiter);
			params.append(encode(aut.classifier));
			params.append('=');
			params.append(String.valueOf(aut.port));

			delimiter = '&';
		}
		return String.format("direct://%s/download%s", getHost(), params);
	}

	private String getHost() {
		if (host == null || host.isEmpty() || host.equals("localhost")) {
			try {
				host = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException ex) {
				ClientAppPlugin.getDefault().error(
						"Can't initialize 'host' parameter. Use 'localhost'",
						ex);
				host = "localhost";
			}
		}
		return host;
	}

	private static String encode(String value)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(value, "UTF-8");
	}

	public void start(String host, List<Aut> auts) throws CoreException {
		if (auts == null || auts.isEmpty()) {
			throw new IllegalArgumentException("List AUTs is empty.");
		}
		if (acceptors != null) {
			throw new IllegalStateException("Already started");
		}

		this.auts = auts;

		int port = -1;
		acceptors = new ArrayList<Acceptor>();
		try {
			for (Aut aut : auts) {
				ServerSocket socket = new ServerSocket(0);
				aut.port = socket.getLocalPort();
				Acceptor acceptor = new Acceptor(aut.zipPath, socket);
				acceptor.start();
				acceptors.add(acceptor);
			}
		} catch (IOException ex) {
			dispose();
			String msg = String.format("Can't open ServerSocket on %d port",
					port);
			throw new CoreException(ClientAppPlugin.createErrorStatus(msg, ex));
		}
	}

	public void dispose() {
		auts = null;
		if (acceptors != null) {
			for (Acceptor acceptor : acceptors) {
				acceptor.interrupt();
			}
			acceptors.clear();
			acceptors = null;
		}
	}

	protected static class Acceptor extends Thread {

		private final String zipPath;
		private ServerSocket socket;

		public Acceptor(String zipPath, ServerSocket socket) {
			super();
			this.zipPath = zipPath;
			this.socket = socket;
		}


		@Override
		public void interrupt() {
			ServerSocket current = socket;
			socket = null;
			try {
				current.close();
			} catch (IOException exIgnore) {
			}
			super.interrupt();
		}


		@Override
		public void run() {
			while (socket != null) {
				try {
					new Pipe(zipPath, socket.accept()).start();
				} catch (IOException ex) {
				}
			}
		}

	}

	public static class Pipe extends Thread {

		private final String zipFile;
		private final Socket socket;

		public Pipe(String zipFile, Socket socket) {
			super();
			this.zipFile = zipFile;
			this.socket = socket;
		}


		@Override
		public void run() {
			InputStream input = null;
			OutputStream output = null;

			try {
				input = new FileInputStream(zipFile);
				output = socket.getOutputStream();
				ByteStreams.copy(input, output);
			} catch (IOException ex) {
				String msg = String.format("Can't transfer AUT '%s'", zipFile);
				ClientAppPlugin.logErr(msg, ex);
			} finally {
				IOUtil.closeSafe(input);
				IOUtil.closeSafe(output);
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

	public static class Aut {
		public final String classifier;
		public final String zipPath;
		public int port;

		public Aut(String classifier, String zipPath) {
			super();
			this.classifier = classifier;
			this.zipPath = zipPath;
		}


		@Override
		public int hashCode() {
			return classifier.hashCode() ^ zipPath.hashCode();
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (obj instanceof Aut) {
				Aut other = (Aut) obj;
				return classifier.equals(other.classifier)
						&& zipPath.equals(other.zipPath);
			}
			return false;
		}

	}

}
