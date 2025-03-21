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
package org.eclipse.rcptt.cloud.common;

import java.io.File;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.util.FileUtil;

/**
 * Entry point for all operations related to test suite management
 * 
 * @author ivaninozemtsev
 * 
 */
public class TestSuiteRegistry {
	private File baseDir;

	public TestSuiteRegistry(File baseDir) {
		if (!baseDir.exists() && !baseDir.mkdirs()) {
			throw new IllegalArgumentException();
		}
		this.baseDir = baseDir;
	}

	public File getBaseDir() {
		return baseDir;
	}

	private WeakHashMap<String, TestSuiteDirectory> dirsCache = new WeakHashMap<String, TestSuiteDirectory>();

	public synchronized TestSuiteDirectory getDirectory(String id)
			throws CoreException {
		return getDirectory(id, false, false);
	}

	public synchronized TestSuiteDirectory getDirectory(String id,
			boolean createIfNotExists, boolean clean) throws CoreException {
		TestSuiteDirectory directory = dirsCache.get(id);
		if (directory != null && !clean) {
			return directory;
		}
		File suiteDir = new File(baseDir, id);
		if (clean) {
			FileUtil.deleteFile(suiteDir);
		}
		TestSuiteDirectory dir = new TestSuiteDirectory(suiteDir,
				createIfNotExists);
		dirsCache.put(id, dir);
		return dir;
	}

	public void removeAll() {
		deleteFiles(baseDir.listFiles());
		dirsCache.clear();
	}

	public static void deleteFiles(File[] listFiles) {
		if (listFiles == null) {
			return;
		}
		for (File file : listFiles) {
			if (file != null) {
				if (file.isDirectory()) {
					deleteFiles(file.listFiles());
				}
				file.delete();
			}
		}
	}
}
