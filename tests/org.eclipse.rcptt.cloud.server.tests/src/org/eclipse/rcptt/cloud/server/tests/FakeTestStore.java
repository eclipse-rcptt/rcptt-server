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
package org.eclipse.rcptt.cloud.server.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.server.tests.TestUtils.TestsSuite;

public class FakeTestStore implements ITestStore {

	private TestsSuite suite;

	public FakeTestStore(TestsSuite suite) {
		this.suite = suite;
	}


	public void clearOutDated(TestSuite newSuite, boolean clear)
			throws CoreException {
	}


	public List<Q7ArtifactRef> getMissingArtifacts() throws CoreException {
		return new ArrayList<Q7ArtifactRef>();
	}


	public Q7Artifact getResource(Q7ArtifactRef ref) {
		Q7Artifact rv = suite.artifacts.get(ref.getId());
		if (Q7TestingHelper.containsTag(rv.getContent(),
				TestCaseState.failtoload)) {
			return null;
		}
		return rv;
	}


	public void putResource(Q7Artifact artifact) throws CoreException {
		suite.artifacts.put(artifact.getId(), artifact);
	}


	public int size() {
		return suite.artifacts.size();
	}


	public TestSuite getTestSuite() {
		return suite.suite;
	}


	public void remove() {
		// TODO Auto-generated method stub

	}
}
