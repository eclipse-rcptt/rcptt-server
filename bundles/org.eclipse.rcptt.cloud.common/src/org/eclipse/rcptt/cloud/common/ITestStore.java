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

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.TestSuite;

public interface ITestStore {

	public void clearOutDated(TestSuite newSuite, boolean clear)
			throws CoreException;

	public List<Q7ArtifactRef> getMissingArtifacts() throws CoreException;

	/**
	 * Returns artifact if it is present in directory and is not outdated, null
	 * otherwise
	 * 
	 * @param ref
	 * @return
	 * @throws IOException
	 */
	public Q7Artifact getResource(Q7ArtifactRef ref) throws IOException;

	public void putResource(Q7Artifact artifact) throws CoreException;

	public TestSuite getTestSuite();

	public void remove();

}
