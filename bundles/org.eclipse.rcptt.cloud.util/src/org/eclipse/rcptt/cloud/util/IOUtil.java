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
package org.eclipse.rcptt.cloud.util;

import static java.lang.String.format;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URI;
import java.security.MessageDigest;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.rcptt.cloud.util.internal.UtilPlugin;

public class IOUtil {
	public static class DownloadOptions {
		public DownloadOptions(int attemptCount, int readTimeout) {
			this.attemptCount = attemptCount;
			this.readTimeout = readTimeout;
		}

		public DownloadOptions() {
			this(5, 2 * 60 * 1000);
		}

		public final int attemptCount;
		public final int readTimeout;
	}

	public static interface ISrcFactory {
		public InputStream reopen() throws IOException;

		public void done();
	}

	private static interface IDstFactory {
		public OutputStream reopen() throws IOException;

	}

	public static class ByteArrayDest implements IDstFactory {
		private ByteArrayOutputStream result;

		public ByteArrayOutputStream getResult() {
			return result;
		}

		public OutputStream reopen() {
			return result = new ByteArrayOutputStream();
		}

		public void done() {
		}

	}

	public static class FileDest implements IDstFactory {
		private File file;

		public FileDest(File file) {
			this.file = file;
		}

		private OutputStream out;

		public OutputStream reopen() throws IOException {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// ignore
				}
			}
			return out = new BufferedOutputStream(new FileOutputStream(file));
		}

		public void done() {
			if (out == null) {
				return;
			}
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
			}
		}
	}

	public static class TcpSrc implements ISrcFactory {
		private final String host;
		private final int port;

		private Socket socket;

		public TcpSrc(String host, int port) {
			this.host = host;
			this.port = port;
		}

		public InputStream reopen() throws IOException {
			done();
			socket = new Socket(host, port);
			return socket.getInputStream();
		}

		public void done() {
			closeSafe(socket);
			socket = null;
		}

		@Override
		public String toString() {
			return String.format("%s:%d", host, port);
		}
	}

	public static class HttpSrc implements ISrcFactory {
		private String uri;
		private HttpURLConnection connection;
		private InputStream stream;

		public HttpSrc(String uri) {
			this.uri = uri;
		}

		public InputStream reopen() throws IOException {
			done();
			connection = (HttpURLConnection) URI.create(uri).toURL().openConnection();
			connection.connect();
			return connection.getInputStream();
		}

		public void done() {
			closeSafe(stream);
			if (connection != null) {
				connection.disconnect();
			}
		}

		@Override
		public String toString() {
			return uri;
		}

		private static final String SCHEME_HTTP = "http";

		public static boolean isSupported(String uri) {
			try {
				return uri.startsWith(SCHEME_HTTP + "://");
			} catch (Exception ex) {
				return false;
			}
		}

	}

	public static class HttpsSrc implements ISrcFactory {
		private String uri;
		private HttpsURLConnection connection;
		private InputStream stream;
		private static final String SCHEME_HTTP = "https";

		public HttpsSrc(String uri) {
			this.uri = uri;
		}

		public InputStream reopen() throws IOException {
			done();
			connection = (HttpsURLConnection) URI.create(uri).toURL().openConnection();
			connection.connect();
			return connection.getInputStream();
		}

		public void done() {
			closeSafe(stream);
			if (connection != null) {
				connection.disconnect();
			}
		}

		@Override
		public String toString() {
			return uri;
		}

		public static boolean isSupported(String uri) {
			try {
				return uri.startsWith(SCHEME_HTTP + "://");
			} catch (Exception ex) {
				return false;
			}
		}
	}

	public static class ServerSrc implements ISrcFactory {
		private String uri;
		private HttpURLConnection connection;
		private InputStream stream;

		public ServerSrc(String uri) {
			this.uri = uri;
		}

		public InputStream reopen() throws IOException {
			done();
			connection = (HttpURLConnection) URI.create(uri).toURL().openConnection();

			connection.setUseCaches(false);
			connection.setDoInput(true);

			connection.connect();
			return connection.getInputStream();
		}

		public void done() {
			closeSafe(stream);
			if (connection != null) {
				connection.disconnect();
			}
		}

		@Override
		public String toString() {
			return uri;
		}
	}

	public static byte[] download(ISrcFactory src, IProgressMonitor monitor, IDownloadMonitor downloadMonitor)
			throws IOException {
		ByteArrayDest dest = new ByteArrayDest();
		downloadSafe(src, dest, null, monitor, null, downloadMonitor);
		ByteArrayOutputStream result = dest.getResult();
		if (result == null) {
			return null;
		}
		return result.toByteArray();
	}

	public static interface IDownloadMonitor {
		void logDownloaded(String msg);
	}

	public static void download(ISrcFactory src, File file, IProgressMonitor monitor, MessageDigest md,
			IDownloadMonitor downloadMonitor) throws IOException {
		downloadSafe(src, new FileDest(file), null, monitor, md, downloadMonitor);
	}

	public static void downloadSafe(ISrcFactory src, IDstFactory dest, DownloadOptions options,
			IProgressMonitor monitor, MessageDigest md, IDownloadMonitor downloadMonitor) throws IOException {
		if (options == null) {
			options = new DownloadOptions();
		}

		int count = options.attemptCount;

		Random rnd = new Random();

		monitor.beginTask("Download", 0);
		try {
			while (!monitor.isCanceled()) {
				if (md != null) {
					md.reset();
				}
				long lastReadTime = System.currentTimeMillis();
				try (InputStream input = src.reopen()) {
					monitor.done();
					int size = input.available();
					String msg = String.format("Downloading %s byte(s)",
							size == 0 ? "<unknown>" : Integer.toString(size));
					monitor.beginTask(msg, size);
					try (OutputStream outputStream = dest.reopen()) {
						ReadingThread thread = new ReadingThread(input, outputStream, monitor, md);
						thread.start();
						int readed = 0;
						long lastReport = System.currentTimeMillis();
						try {
							for (;;) {
								if (lastReadTime + options.readTimeout < System.currentTimeMillis()) {
									throw new IOException(
											format("Read has timed out after %d ms ", options.readTimeout));
								}

								try {
									thread.join(POLL_INTERVAL);
								} catch (InterruptedException e) {
									logCanceled(downloadMonitor);
									return;
								}
								if (thread.isAlive()) {
									int newReaded = thread.readed;
									if (newReaded > readed) {
										lastReadTime = System.currentTimeMillis();
									}
									readed = newReaded;

									if (System.currentTimeMillis() > lastReport + 60_000) {
										lastReport = System.currentTimeMillis();
										logDownloaded(downloadMonitor, newReaded);
									}
									continue;
								} else if (thread.completedOk()) {
									logDownloaded(downloadMonitor, readed);
									return;
								} else if (thread.ioerr != null) {
									throw thread.ioerr;
								} else {
									throw new AssertionError("Abnormal reading thread termination");
								}

							}

						} finally {
							thread.interrupt();
						}
					}

				} catch (IOException e) {
					if (e instanceof FileNotFoundException) {
						throw e;
					}
					if (--count > 0) {
						int interval = rnd.nextInt(32) * 100;
						try {
							Thread.sleep(interval);
						} catch (InterruptedException e1) {
							logCanceled(downloadMonitor);
							return;
						}
					}
					continue;
				}

			}
		} finally {
			src.done();
			monitor.done();
		}

		if (!monitor.isCanceled()) {
			throw new IOException(String.format("Cannot download from %s", src.toString()));
		}
	}

	private static void logCanceled(IDownloadMonitor downloadMonitor) {
		if (downloadMonitor != null) {
			downloadMonitor.logDownloaded("Downloading is canceled");
		}
	}

	private static void logDownloaded(IDownloadMonitor downloadMonitor, int newReaded) {
		if (downloadMonitor != null) {
			downloadMonitor.logDownloaded("downloaded: " + (newReaded / 1024) + "kb");
		}
	}

	public static void closeSafe(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (Exception ex) {
			IStatus status = new Status(IStatus.WARNING, UtilPlugin.PLUGIN_ID, ex.getMessage(), ex);
			UtilPlugin.getDefault().getLog().log(status);
		}
	}

	public static void closeSafe(Socket socket) {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (Exception ex) {
			IStatus status = new Status(IStatus.WARNING, UtilPlugin.PLUGIN_ID, ex.getMessage(), ex);
			UtilPlugin.getDefault().getLog().log(status);
		}
	}

	private static final int POLL_INTERVAL = 100;

	private static class ReadingThread extends Thread {
		public final InputStream in;
		public final OutputStream out;
		public volatile int readed = 0;
		private volatile IOException ioerr = null;

		private final IProgressMonitor monitor;
		private MessageDigest md;

		public boolean completedOk() {
			return !isAlive() && ioerr == null;
		}

		public ReadingThread(InputStream in, OutputStream out, IProgressMonitor monitor, MessageDigest md) {
			this.in = in;
			this.out = out;
			this.monitor = monitor;
			this.md = md;
		}

		@Override
		public void run() {
			try {
				BufferedInputStream bin = new BufferedInputStream(in);
				int r = 0;
				byte[] buffer = new byte[8192];
				while ((r = bin.read(buffer)) != -1 && !monitor.isCanceled()) {
					out.write(buffer, 0, r);
					if (md != null) {
						md.update(buffer, 0, r);
					}
					readed += r;
					monitor.worked(r);
				}

			} catch (IOException e) {
				this.ioerr = e;
			}
		}
	}

	public static byte[] getStreamContent(InputStream stream) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = stream.read(buffer)) > 0) {
				output.write(buffer, 0, len);
			}
		} finally {
			closeSafe(stream);
		}
		return output.toByteArray();
	}
}
