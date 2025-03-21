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
package org.eclipse.rcptt.cloud.agent.autManager.impl.direct;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.util.FileUtil;

import org.eclipse.rcptt.cloud.agent.AgentPlugin;
import org.eclipse.rcptt.cloud.agent.AutFileUtil;
import org.eclipse.rcptt.cloud.agent.autManager.IAutProvider;
import org.eclipse.rcptt.cloud.agent.autManager.impl.BaseAutProvider;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.util.IOUtil;
import org.eclipse.rcptt.cloud.util.IOUtil.TcpSrc;

/**
 * URI:
 * direct://<host>:<port>/download?<classifier1>=<port1>&...&<classifierN>=<
 * portN>
 * 
 * @author Ruslan
 * 
 */
public class DirectAutProvider implements IAutProvider, Closeable  {

	private static final String SCHEME = "direct";
	private static final String PATH = "/download";
	private File baseDirectory;
	
	public DirectAutProvider() {
		try {
			this.baseDirectory = Files.createTempDirectory("q7_agent_" + getClass().getSimpleName()).toFile();
		} catch (IOException e) {
			throw new IllegalStateException("Unable to create a temporary directory ", e);
		}
	}
	
	@Override
	public void close() {
		FileUtil.deleteFiles(baseDirectory);
	}


	@Override
	public boolean isSupport(AutInfo aut) {
		try {
			URI uri = new URI(aut.getUri());
			return SCHEME.equals(uri.getScheme()) && PATH.equals(uri.getPath());
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public File download(AutInfo aut, String classifier, IProgressMonitor monitor) throws CoreException {
		AutFileUtil helper = new AutFileUtil(baseDirectory);

		File autFile = helper.getAutFile(aut);
		autFile.delete();

		File autBaseDir = helper.getAutBaseDir(aut);
		FileUtil.deleteFile(autBaseDir);

		try {
			URI uri = new URI(aut.getUri());
			monitor.subTask("Download AUT zip");
			IOUtil.download(new TcpSrc(uri.getHost(), getPort(uri)), autFile,
					monitor, null, null);

			monitor.subTask("Unzip AUT");
			try {
				BaseAutProvider.unarchive(autFile, autBaseDir);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new CoreException(Status.error("AUT unpack is interrupted", e));
			}

			autFile.delete();

			Date autData = helper.getAutDownloadDate(aut);
			// AgentPlugin.logInfo(String.format("AUT updated at %tD %tT",
			// autData, autData));
		} catch (URISyntaxException ex) {
			throw AgentPlugin.createException("AUT download failed", ex);
		} catch (IllegalArgumentException ex) {
			throw AgentPlugin.createException("AUT download failed", ex);
		} catch (IOException ex) {
			throw AgentPlugin.createException("AUT download failed", ex);
		} finally {
			monitor.subTask("");
		}

		return helper.getAutExecDir(aut);
	}

	protected int getPort(URI uri) throws IllegalArgumentException {
		String[] params = uri.getRawQuery().split("&");
		if (params.length == 0) {
			invalidUriException(uri);
		}
		for (String param : params) {
			String[] pair = param.split("=");
			if (pair.length == 2) {
				if (AgentPlugin.getClassifier().equals(decode(pair[0]))) {
					return Integer.parseInt(decode(pair[1]));
				}
			}
		}
		throw new IllegalArgumentException(String.format(
				"Port for classifier '%s' not found.",
				AgentPlugin.getClassifier()));
	}

	private static void invalidUriException(URI uri)
			throws IllegalArgumentException {
		throw new IllegalArgumentException(
				String.format(
						"Expected 'direct://<host>:<port>/download?<classifier1>=<port1>&...&<classifierN>=<portN>' but '%s' provided",
						uri.toString()));
	}

	private static String decode(String value) throws IllegalArgumentException {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

}
