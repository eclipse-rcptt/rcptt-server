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
package org.eclipse.rcptt.cloud.server.internal.providers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.util.Base64;

import org.eclipse.rcptt.cloud.common.UriUtil;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.util.IOUtil;
import org.eclipse.rcptt.cloud.util.IOUtil.IDownloadMonitor;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;

public abstract class BaseServerAutProvider implements IServerAutProvider {

	@Override
	public void download(ExecutionEntry execution, AutInfo aut,
			String classifier, File autFile, final IProgressMonitor monitor,
			final IQ7Monitor log) throws CoreException {
		byte[] remoteHash = getRemoteHash(aut, classifier, monitor, log);

		byte[] downloadHash = null;

		log.log("aut remote hash: "
				+ (remoteHash == null ? "none" : new String(remoteHash)), null);

		// TODO: get aut with specified hashcode from previous build

		try {
			ISrcFactory factory = getZipSourceFactory(aut, classifier);
			log.log("download aut zip: " + factory.toString(), null);

			long start = System.currentTimeMillis();
			log.log("begin aut download: " + factory.toString(), null);

			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("md5");
			} catch (NoSuchAlgorithmException e) {
				log.log("Fail to download AUT. No MD5 messageDigest is available",
						null);
				return;
			}
			File autHashFile = getAutHashFile(execution, aut, classifier);

			IOUtil.download(factory, autFile, monitor, md,
					new IDownloadMonitor() {

						@Override
						public void logDownloaded(String msg) {
							log.log(msg, null);
						}
					});
			long end = System.currentTimeMillis();

			if (md != null) {
				downloadHash = Base64.encode(md.digest())
						.getBytes();
			}

			// Calculate zip file size, and transfer rata
			long length = autFile.length();
			long rate = calculateRate(start, end, length);

			log.log("aut downloaded: " + autFile.getName() + " size: " + length
					+ " transfer rate: " + rate + "kb/sec time: "
					+ Long.toString(end - start), null);

			if (remoteHash != null && downloadHash != null) {
				// Compare remote and downloaded hash
				if (Arrays.equals(remoteHash, downloadHash)) {
					// great remote and local caches are equals.
					log.log("Remote and downloaded hashes are equals. great.",
							null);
				} else {
					log.log("Remote and downloaded hashes are DIFFERENT. download: "
							+ new String(downloadHash)
							+ " remote: "
							+ new String(remoteHash));
				}
			}

			// autFile.delete();
			// ISrcFactory hashFactory = getMd5SourceFactory(aut, classifier);
			// String msg = "download aut md5: " + hashFactory.toString();
			// log.log(msg, null);

			// Storing local hash
			// Hash is downloaded at the last stage, so that
			// if anything goes wrong, the hash file won't exist
			// and download/unpack attempt will be repeated on next attemp
			OutputStream md5File = new BufferedOutputStream(
					new FileOutputStream(autHashFile));
			md5File.write(downloadHash);
			IOUtil.closeSafe(md5File);
		} catch (IOException e) {
			log.log("aut download failed:" + e.getMessage(), e);
			throw new CoreException(ServerPlugin.createErr(
					"AUT download failed", e));
		}
	}

	@Override
	public File getAutFile(ExecutionEntry entry, AutInfo aut, String classifier) {
		String autFileName = getAutFileName(aut);
		String autZip = UriUtil.autZip(autFileName, classifier);
		return entry.getArtifactName(autZip);
	}

	protected static String getAutFileName(AutInfo aut) {
		return getAutFileNameFromUri(aut);
	}

	protected static File getAutHashFile(ExecutionEntry entry, AutInfo aut,
			String classifier) {
		String autFileName = getAutFileName(aut);
		String autMd5 = UriUtil.autMd5(autFileName, classifier);
		return entry.getArtifactName(autMd5);
	}

	private static String getAutFileNameFromUri(AutInfo aut) {
		return ExecutionEntry.getAutNameFromUri(aut.getUri(), aut.getId());
	}

	private long calculateRate(long start, long end, long length) {
		try {
			long rate = -1;
			if (length > 0) {
				long time = (end - start);
				long t = (time / 1000);
				if (t == 0) {
					t = 1;
				}
				rate = (length / 1024) / t;
			}
			return rate;
		} catch (ArithmeticException e) {
			// ignore
		}
		return 1;
	}

	protected byte[] getRemoteHash(AutInfo aut, String classifier,
			IProgressMonitor monitor, IQ7Monitor log) {
		ISrcFactory md5SourceFactory = getMd5SourceFactory(aut, classifier);
		try {
			log.log("download remote hash: " + md5SourceFactory.toString(),
					null);
			return IOUtil.download(md5SourceFactory, monitor, null);
		} catch (IOException e) {
			log.log("failed to download remote hash:  "
					+ md5SourceFactory.toString(), e);
			ServerPlugin.err(
					"Failed to download remote hash uri:" + aut.getUri()
							+ " classifier:" + classifier, e);
			return null;
		}
	}

	abstract protected ISrcFactory getMd5SourceFactory(AutInfo aut,
			String classifier);

	abstract protected ISrcFactory getZipSourceFactory(AutInfo aut,
			String classifier);

}
