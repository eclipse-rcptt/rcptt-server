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
package org.eclipse.rcptt.cloud.agent.autManager.impl;

import static java.lang.String.format;
import static org.eclipse.rcptt.cloud.agent.AgentPlugin.createException;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.cloud.agent.AgentPlugin;
import org.eclipse.rcptt.cloud.agent.AutFileUtil;
import org.eclipse.rcptt.cloud.agent.AutRegistry;
import org.eclipse.rcptt.cloud.agent.IAgentMonitor;
import org.eclipse.rcptt.cloud.agent.autManager.IAutProvider;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.util.IOUtil;
import org.eclipse.rcptt.cloud.util.IOUtil.IDownloadMonitor;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.util.Base64;
import org.eclipse.rcptt.util.FileUtil;

public abstract class BaseAutProvider implements IAutProvider, Closeable {
	
	private final File baseDirectory;

	public BaseAutProvider() {
		this.baseDirectory = new File(new File(AgentPlugin.getDefault().getStateLocation().toOSString()), "auts_"+getClass().getSimpleName());
		baseDirectory.mkdirs();
	}
	
	@Override
	public void close() {
	}

	@Override
	public File download(AutInfo aut, String classifier, final IProgressMonitor monitor) throws CoreException {
		IQ7Monitor log = AutRegistry.getLog();
		AutFileUtil helper = new AutFileUtil(baseDirectory);

		byte[] localHash = helper.getLocalHash(aut);
		log.log("aut local hash: "
				+ (localHash == null ? "none" : new String(localHash)), null);
		byte[] remoteHash = getRemoteHash(aut, classifier, monitor, log);

		byte[] downloadHash = null;

		log.log("aut local hash: "
				+ (localHash == null ? "none" : new String(localHash)), null);

		log.log("aut remote hash: "
				+ (remoteHash == null ? "none" : new String(remoteHash)), null);

		File autExecDir = helper.getAutExecDir(aut);
		Date autData = helper.getAutDownloadDate(aut);
		if (localHash != null && Arrays.equals(localHash, remoteHash)
				&& autExecDir.exists()) {
			log.log(String.format("AUT " + autExecDir.toString()
					+ " is up to date (downloaded at %tD %tT)", autData,
					autData), null);
			return autExecDir;
		}
		File autFile = helper.getAutFile(aut);
		File autHashFile = helper.getAutHashFile(aut);
		File autBaseDir = helper.getAutBaseDir(aut);

		int i = 0;
		for (;;) {
			if (monitor.isCanceled()) {
				throw new CoreException(Status.CANCEL_STATUS);
			}
			// Remove all old AUTs. We already need to cache one AUT (due to agent pinning) and that will be redownloaded now
			deleteChildren(baseDirectory); 
			autHashFile.delete();
			autFile.delete();
			FileUtil.deleteFile(autBaseDir);
			if (!autBaseDir.exists()) {
				break;
			}
			log.log("Can not delete directory " + autBaseDir + ". Attempt " + i++);
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new CoreException(Status.CANCEL_STATUS);
			}
		}
		if (autBaseDir.exists()) {
			throw new CoreException(Status.error("Can not delete directory " + autBaseDir));
		}
		try {
			ISrcFactory factory = getZipSourceFactory(aut, classifier);
			log.log("download aut zip: " + factory.toString(), null);

			long start = System.currentTimeMillis();
			agentLog(monitor, "begin aut download: " + factory.toString(),
					IAgentMonitor.LogType.LogOnly);

			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("md5");
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalStateException(e);
			}
			IOUtil.download(factory, autFile, monitor, md,
					new IDownloadMonitor() {

						@Override
						public void logDownloaded(String msg) {
							agentLog(monitor, msg,
									IAgentMonitor.LogType.LogOnly);
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

			agentLog(monitor, "aut downloaded: " + autFile.getName()
					+ " size: " + length + " transfer rate: " + rate
					+ "kb/sec time: " + Long.toString(end - start),
					IAgentMonitor.LogType.Both);
			
			if (length < 1024*1024) {
				throw createException(format("AUT download failed, file %s is too short: %d bytes", autFile.getName(), length));
			}

			if (remoteHash != null && downloadHash != null) {
				// Compare remote and downloaded hash
				if (Arrays.equals(remoteHash, downloadHash)) {
					// great remote and local caches are equals.
					agentLog(monitor,
							"Remote and downloaded hashes are equals. great.",
							IAgentMonitor.LogType.LogOnly);
				} else {
					agentLog(
							monitor,
							"Remote and downloaded hashes are DIFFERENT. Unzipping could fail.",
							IAgentMonitor.LogType.LogOnly);
				}
			}

			String msg = "Unzip AUT:" + autFile.toString() + " into: "
					+ autBaseDir.toString();
			log.log(msg, null);
			agentLog(monitor, msg, IAgentMonitor.LogType.LogOnly);

			start = System.currentTimeMillis();
			
			try {
				unarchive(autFile, autBaseDir);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new CoreException(Status.error("AUT unpack is interrupted", e));
			}
			autFile.delete();
			end = System.currentTimeMillis();

			agentLog(monitor,
					"unzip done. time: " + Long.toString(end - start),
					IAgentMonitor.LogType.LogOnly);

			// autFile.delete();
			ISrcFactory hashFactory = getMd5SourceFactory(aut, classifier);
			msg = "download aut md5: " + hashFactory.toString();
			log.log(msg, null);
			agentLog(monitor, msg, IAgentMonitor.LogType.LogOnly);

			if (remoteHash != null) {
				// Storing remote hash
				// Hash is downloaded at the last stage, so that
				// if anything goes wrong, the hash file won't exist
				// and download/unpack attempt will be repeated on next attempt
				try (OutputStream md5File = new BufferedOutputStream(
						new FileOutputStream(autHashFile))) {
					md5File.write(remoteHash);
				}
			}
			log.log(String.format("AUT updated at %tD %tT", autData, autData),
					null);
		} catch (IOException e) {
			log.log("aut download failed:" + e.getMessage(), e);
			throw AgentPlugin.createException("AUT download failed", e);
		}

		return helper.getAutExecDir(aut);
	}

	private static void deleteChildren(File directory) {
		FileUtil.deleteFiles(directory.listFiles());
	}

	public static void unarchive(File autFile, File autBaseDir) throws IOException, InterruptedException {
		String fileExtension = Path.fromOSString(autFile.toString()).getFileExtension();
		if (fileExtension == null) {
			throw new IOException("Aut file name does not have an extension: " + autFile.toString());
		}
		fileExtension = fileExtension.toLowerCase();
		if ("zip".equals(fileExtension)) { 
			int files =   FileUtil.unzip(autFile, autBaseDir);
			if (files == 0) {
				// Seems no files are unzipped
				throw new IOException("AUT download failed: There are no files in zip file " + autFile);
			}
		} else if ("dmg".equals(fileExtension)) {
			DmgExtract.extract(autFile.toPath(), autBaseDir.toPath());
		} else {
			throw new IOException("Aut file name has unknown extension: " + autFile.toString());
		}
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

	private void agentLog(IProgressMonitor monitor, String msg,
			IAgentMonitor.LogType type) {
		if (monitor instanceof IAgentMonitor) {
			((IAgentMonitor) monitor).logAgentMessage(msg, type);
		}
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
			AgentPlugin.logErr(
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
