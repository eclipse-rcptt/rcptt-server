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
package org.eclipse.rcptt.cloud.agent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.cloud.common.AutUtil;
import org.eclipse.rcptt.cloud.common.UriUtil;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.util.FileUtil;

/**
 * Manages things like workspaces, console logs and stuff like that
 * 
 * @author ivaninozemtsev
 * 
 */
public class AutFileUtil {
	private final File baseDir;

	public AutFileUtil(File baseDir) {
		this.baseDir = baseDir;
	}

	public void clearWorkspaces(AutInfo aut) {
		File root = getAutBaseDir(aut);
		if (root.exists()) {
			FileUtil.deleteFile(root);
		}
	}

	public File getWorkspace(AutInfo aut, int launchIndex, boolean clean) {
		File result = null;

		while (true) {
			result = new File(getAutBaseDir(aut), String.format(
					WORKSPACE_DIR_TEMPLATE, launchIndex));
			if (result.exists() && clean) {
				// attempting to delete workspace directory and
				// log warnings if it cannot be deleted
				if (!FileUtil.deleteFile(result)) {
					launchIndex++;
					continue;
				}
			}
			break;
		}
		result.mkdirs();
		return result;
	}

	public PrintStream getFileStream(AutInfo aut, File file)
			throws CoreException {
		try {
			return new PrintStream(file);
		} catch (FileNotFoundException e) {
			throw AgentPlugin.createException("Can't write to file " + file, e);
		}
	}

	public PrintStream getErrStream(AutInfo aut, int launchIndex)
			throws CoreException {
		return getFileStream(aut, getErrStreamFile(aut, launchIndex));
	}

	public File getErrStreamFile(AutInfo aut, int launchIndex) {
		return new File(getAutBaseDir(aut), String.format(
				PROCESS_ERR_FILE_TEMPLATE, launchIndex));
	}

	public PrintStream getOutStream(AutInfo aut, int launchIndex)
			throws CoreException {
		return getFileStream(aut, getOutStreamFile(aut, launchIndex));
	}

	public File getOutStreamFile(AutInfo aut, int launchIndex) {
		return new File(getAutBaseDir(aut), String.format(
				PROCESS_OUT_FILE_TEMPLATE, launchIndex));
	}

	/**
	 * Base directory of AUT, for example auts/com.xored.f4.ide
	 * 
	 * @param aut
	 * @return
	 */
	public File getAutBaseDir(AutInfo aut) {
		return new File(baseDir, AutUtil.getName(aut));
	}

	/**
	 * AUT directory containing features and plugins, for example
	 * auts/com.xored.f4.ide/f4
	 * 
	 * This differs from getAutBaseDir because some AUT archives can contain
	 * root folder
	 * 
	 * @param aut
	 * @return
	 */
	public File getAutExecDir(AutInfo aut) {
		File result = getAutBaseDir(aut);
		if (!result.exists()) {
			return result;
		}
		String[] childs = result.list();
		if (childs != null && childs.length == 1) {
			return new File(result, result.list()[0]);
		}
		return result;
	}

	public File getAutFile(AutInfo aut) {
		return new File(baseDir, getAutFileName(aut));
	}

	public File getAutHashFile(AutInfo aut) {
		return new File(baseDir, getAutHashFileName(aut));
	}

	public byte[] getLocalHash(AutInfo aut) {
		try {
			File f = getAutHashFile(aut);
			if (!f.exists()) {
				return null;
			}
			return FileUtil.getContents(f);
		} catch (IOException e) {
			return null;
		}
	}

	public Date getAutDownloadDate(AutInfo aut) {
		File f = getAutHashFile(aut);
		if (f == null) {
			return null;
		}
		return new Date(f.lastModified());
	}

	private static String getExtension(AutInfo aut) {
		return UriUtil.getFilenameExtension(URI.create(aut.getUri()).getPath());
	}
	protected static String getAutFileName(AutInfo aut) {
		
		return String.format("%s.%s", aut.getId(), getExtension(aut));
	}

	protected static String getAutHashFileName(AutInfo aut) {
		return String.format("%s.%s.md5", aut.getId(), getExtension(aut));
	}

	private static final String WORKSPACE_DIR_TEMPLATE = "workspace%d";
	private static final String PROCESS_OUT_FILE_TEMPLATE = "out%d.txt";
	private static final String PROCESS_ERR_FILE_TEMPLATE = "err%d.txt";

}
