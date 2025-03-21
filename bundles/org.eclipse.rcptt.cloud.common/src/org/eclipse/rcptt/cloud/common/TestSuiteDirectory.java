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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.util.EmfResourceUtil;

/**
 * Represents test suite on file system. Provides various methods for managing
 * contents and determining missing or outdated resources
 * 
 * @author ivaninozemtsev
 * 
 */
public class TestSuiteDirectory implements ITestStore {
	private static String INDEX_FILE_NAME = "suite.xml";
	private File baseDir;

	public TestSuite testSuite;
	private Map<String, Q7ArtifactRef> resources = new HashMap<String, Q7ArtifactRef>();

	/**
	 * This constructor is called when we getting existing test suite No
	 * integrity checks are performed
	 * 
	 * @param baseDir
	 * @throws CoreException
	 */
	public TestSuiteDirectory(File baseDir, boolean create)
			throws CoreException {
		this.baseDir = baseDir;

		this.testSuite = loadTestSuite();
		if (!create) {
			if (this.testSuite == null) {
				throw CommonPlugin.createException("Test suite does not exist");
			}
		}
		if (this.testSuite != null) {
			this.resources = ModelUtil.refMap(testSuite);
		}
		if (!baseDir.exists()) {
			if (create) {
				baseDir.mkdirs();
			} else {
				throw CommonPlugin.createException("Test suite does not exist");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.common.ITestStore#clearOutDated(com.xored.q7.cloud
	 * .model.TestSuite, boolean)
	 */

	public void clearOutDated(TestSuite newSuite, boolean clear)
			throws CoreException {
		if (this.testSuite != null) {
			if (clear && getIndexFile().exists()) {
				clearOutdatedResources(ModelUtil.refMap(newSuite));
			}
		}
		this.testSuite = EcoreUtil.copy(newSuite);
		this.resources = ModelUtil.refMap(this.testSuite);

		saveTestSuite();
	}

	private void clearOutdatedResources(Map<String, Q7ArtifactRef> curr) {
		synchronized (resources) {
			for (Entry<String, Q7ArtifactRef> entry : resources.entrySet()) {
				String id = entry.getKey();
				Q7ArtifactRef oldRef = entry.getValue();
				Q7ArtifactRef newRef = curr.get(id);
				if (newRef == null
						|| !Arrays.equals(newRef.getHash(), oldRef.getHash())) {
					getResourceFile(oldRef).delete();
				}
			}
		}
	}

	private TestSuite loadTestSuite() {
		File indexFile = getIndexFile();
		if (indexFile.exists()) {
			try {
				synchronized (TestSuiteDirectory.class) {
					return EmfResourceUtil.load(indexFile, TestSuite.class);
				}
			} catch (IOException e) {
				CommonPlugin.error("Failed to load test suite from "
						+ indexFile, e);
				return null;
			}
		}
		return null;
	}

	private File getIndexFile() {
		return new File(baseDir, INDEX_FILE_NAME);
	}

	private void saveTestSuite() throws CoreException {
		synchronized (TestSuiteDirectory.class) {
			EmfResourceUtil.save(getIndexFile(), testSuite);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xored.q7.cloud.common.ITestStore#getMissingArtifacts()
	 */

	public List<Q7ArtifactRef> getMissingArtifacts() throws CoreException {
		List<Q7ArtifactRef> result = new ArrayList<Q7ArtifactRef>();
		synchronized (resources) {
			for (Q7ArtifactRef ref : resources.values()) {
				if (!resourceExists(ref)) {
					result.add(EcoreUtil.copy(ref));
				}
			}
		}
		return result;
	}

	private synchronized boolean resourceExists(Q7ArtifactRef ref) {
		return getResourceFile(ref).exists();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.common.ITestStore#getResource(com.xored.q7.cloud.model
	 * .Q7ArtifactRef)
	 */

	@Override
	public Q7Artifact getResource(Q7ArtifactRef ref) throws IOException {
		if (!resourceExists(ref)) {
			return null;
		}
		return EmfResourceUtil.load(getResourceFile(ref), Q7Artifact.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.common.ITestStore#putResource(com.xored.q7.cloud.model
	 * .Q7Artifact)
	 */

	public synchronized void putResource(Q7Artifact artifact)
			throws CoreException {
		String id = artifact.getId();
		Q7ArtifactRef ref;
		synchronized (resources) {
			ref = resources.get(id);
			if (ref == null) {
				throw CommonPlugin.createException(
						"Attempt to put unreferenced resource", null);
			}
		}

		EmfResourceUtil.save(getResourceFile(ref), artifact);
	}

	/**
	 * Returns file where the given artifact reference should be saved
	 * 
	 * @param ref
	 * @return
	 */
	private File getResourceFile(Q7ArtifactRef ref) {
		if (ref.getHash() != null) {
			return new File(baseDir, String.format("%s-%s", ref.getId(),
					EmfResourceUtil.byteToStr(ref.getHash())));
		} else {
			return new File(baseDir, ref.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xored.q7.cloud.common.ITestStore#size()
	 */

	public int size() {
		if (resources != null) {
			return resources.size();
		}
		return 0;
	}

	public TestSuite getTestSuite() {
		return testSuite;
	}

	public void remove() {
		TestSuiteRegistry.deleteFiles(baseDir.listFiles());
		baseDir.delete();
	}

	@Override
	public String toString() {
		return baseDir.toString();
	}
}
