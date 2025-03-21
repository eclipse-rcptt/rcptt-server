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
package org.eclipse.rcptt.cloud.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Strings;
import org.eclipse.rcptt.cloud.util.EmfResourceUtil;

public class ModelUtil {

	public static Map<String, Q7ArtifactRef> dependenciesMap(TestSuite suite) {
		Map<String, Q7ArtifactRef> result = new HashMap<>();
		for (Q7ArtifactRef ref : suite.getRefs()) {
			if (ref.getKind() == RefKind.CONTEXT
					|| ref.getKind() == RefKind.VERIFICATION) {
				result.put(ref.getId(), EcoreUtil.copy(ref));
			}
		}
		return result;
	}

	public static Map<String, Q7ArtifactRef> refMap(TestSuite suite) {
		Map<String, Q7ArtifactRef> result = new HashMap<>();
		for (Q7ArtifactRef ref : suite.getRefs()) {
			result.put(ref.getId(), EcoreUtil.copy(ref));
		}
		return result;
	}

	public static List<Q7ArtifactRef> scenarioList(TestSuite suite) {
		List<Q7ArtifactRef> result = new ArrayList<>();
		for (Q7ArtifactRef ref : suite.getRefs()) {
			if (ref.getKind() == RefKind.SCENARIO) {
				result.add(EcoreUtil.copy(ref));
			}
		}
		return result;
	}

	/**
	 * Set of contexts which are required for a given scenario list
	 * 
	 * @param scenarios
	 * @param suite
	 * @return
	 */
	public static Set<Q7ArtifactRef> requiredContexts(
			List<Q7ArtifactRef> scenarios, TestSuite suite) {
		Set<Q7ArtifactRef> result = new HashSet<>();
		Map<String, Q7ArtifactRef> contextMap = dependenciesMap(suite);
		for (Q7ArtifactRef scenario : scenarios) {
			for (String refId : scenario.getRefs()) {
				assert refId != null;
				result.add(EcoreUtil.copy(contextMap.get(refId)));
			}
		}
		return result;
	}

	/**
	 * Puts listed artifact references to test suite Because we use test suite
	 * md5 hash, the artifact reference order makes sence, so this utility
	 * method should be used as it guarantees the same ref order on the same ref
	 * set
	 * 
	 * @param suite
	 * @param refs
	 * @return
	 */
	public static TestSuite setRefs(TestSuite suite, List<Q7ArtifactRef> refs) {
		Collections.sort(refs, new Comparator<Q7ArtifactRef>() {

			@Override
			public int compare(Q7ArtifactRef a, Q7ArtifactRef b) {
				return a.getId().compareTo(b.getId());
			}
		});

		suite.getRefs().clear();
		suite.getRefs().addAll(refs);
		return suite;
	}

	public static Q7ArtifactRef createSuiteRef(TestSuite suite)
			throws CoreException {
		Q7ArtifactRef result = ModelFactory.eINSTANCE.createQ7ArtifactRef();
		result.setId(suite.getId());
		result.setHash(EmfResourceUtil.md5(suite));
		result.setKind(RefKind.TEST_SUITE);
		for (Q7ArtifactRef ref : suite.getRefs()) {
			result.getRefs().add(requireNotNull(ref.getId()));
		}

		return result;
	}

	public static List<String> getAtrifactIds(List<Q7ArtifactRef> refs) {
		List<String> result = new ArrayList<>();
		for (Q7ArtifactRef ref : refs) {
			result.add(ref.getId());
		}
		return result;
	}

	public static Q7ArtifactRef createRef(Q7Artifact artifact, RefKind kind)
			throws CoreException {
		Q7ArtifactRef rv = ModelFactory.eINSTANCE.createQ7ArtifactRef();
		rv.setId(artifact.getId());
		rv.setHash(EmfResourceUtil.md5(artifact.getContent()));
		rv.setKind(kind);
		return rv;
	}
	
	private static String requireNotNull(String id) {
		if (Strings.isNullOrEmpty(id) || "null".contentEquals(id))
			throw new NullPointerException();
		return id;
	}


}
