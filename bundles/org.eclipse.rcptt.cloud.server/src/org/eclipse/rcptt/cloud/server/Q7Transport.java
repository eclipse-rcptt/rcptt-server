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
package org.eclipse.rcptt.cloud.server;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.repository.AuthenticationFailedException;
import org.eclipse.equinox.internal.p2.repository.Transport;

import org.eclipse.rcptt.cloud.util.IOUtil;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpsSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.IDownloadMonitor;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;

final class Q7Transport extends Transport {

	private IDownloadMonitor downloadMonitor;

	public Q7Transport(IDownloadMonitor monitor) {
		this.downloadMonitor = monitor;
	}

	@Override
	public IStatus download(URI toDownload, OutputStream target, long startPos,
			IProgressMonitor monitor) {
		downloadMonitor.logDownloaded("downloading: " + toDownload.toString());
		try {
			byte[] bytes = downloadBytes(toDownload, monitor);
			if (startPos != -1) {
				target.write(bytes, (int) startPos,
						(int) (bytes.length - startPos));
			}
			target.write(bytes);

		} catch (IOException e) {
			return ServerPlugin.createErr(
					"Failed to download: " + toDownload.toString(), e);
		}
		return Status.OK_STATUS;
	}

	private byte[] downloadBytes(URI toDownload, final IProgressMonitor monitor)
			throws IOException {
		downloadMonitor.logDownloaded("downloading: " + toDownload.toString());
		byte[] bytes = IOUtil.download(getSrc(toDownload), monitor,
				downloadMonitor);
		return bytes;
	}

	private ISrcFactory getSrc(URI toDownload) {
		String uri = toDownload.toString();
		if (HttpSrc.isSupported(uri)) {
			return new HttpSrc(uri);
		} else if (HttpsSrc.isSupported(uri)) {
			return new HttpsSrc(uri);
		}
		return new HttpSrc(uri);
	}

	@Override
	public IStatus download(URI toDownload, OutputStream target,
			IProgressMonitor monitor) {
		downloadMonitor.logDownloaded("downloading: " + toDownload.toString());
		try {
			byte[] bytes = IOUtil.download(getSrc(toDownload), monitor,
					new IDownloadMonitor() {

						@Override
						public void logDownloaded(String msg) {
						}
					});
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			target.write(bytes);

		} catch (IOException e) {
			return ServerPlugin.createErr(
					"Failed to download: " + toDownload.toString(), e);
		}
		return Status.OK_STATUS;
	}

	@Override
	public InputStream stream(URI toDownload, IProgressMonitor monitor)
			throws FileNotFoundException, CoreException,
			AuthenticationFailedException {
		downloadMonitor.logDownloaded("downloading: " + toDownload.toString());
		try {
			byte[] bytes = downloadBytes(toDownload, monitor);
			return new ByteArrayInputStream(bytes);
		} catch (IOException e) {
			throw new CoreException(ServerPlugin.createErr(
					"Failed to download: " + toDownload.toString(), e));
		}
	}

	@Override
	public long getLastModified(URI toDownload, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException,
			AuthenticationFailedException {
		return 0;
	}
}
