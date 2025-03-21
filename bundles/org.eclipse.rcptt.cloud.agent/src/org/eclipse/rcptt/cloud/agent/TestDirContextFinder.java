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

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.core.model.IContext;
import org.eclipse.rcptt.core.model.IQ7NamedElement;
import org.eclipse.rcptt.core.model.ITestCase;
import org.eclipse.rcptt.core.model.ITestSuite;
import org.eclipse.rcptt.core.model.IVerification;
import org.eclipse.rcptt.core.scenario.Context;
import org.eclipse.rcptt.core.scenario.GroupContext;
import org.eclipse.rcptt.core.scenario.Verification;
import org.eclipse.rcptt.core.workspace.IWorkspaceFinder;
import org.eclipse.rcptt.internal.core.model.Q7Context;
import org.eclipse.rcptt.internal.core.model.Q7Element;
import org.eclipse.rcptt.internal.core.model.Q7InternalContext;
import org.eclipse.rcptt.internal.core.model.Q7InternalVerification;

import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.RefKind;

public class TestDirContextFinder implements IWorkspaceFinder {

	private ITestStore dir;
	private Map<String, Q7ArtifactRef> contexts;

	public TestDirContextFinder(ITestStore dir) {
		this.dir = dir;
		this.contexts = ModelUtil.dependenciesMap(dir.getTestSuite());
	}

	@Override
	public IContext[] findContext(IQ7NamedElement dependant, String id) {
		if (dependant != null && !(dependant instanceof Q7Element)) {
			AgentPlugin.error(String.format(
					"Expected instanceof Q7Element but %s provided", dependant
							.getClass().getName()), null);
			return null;
		}
		Q7Element parent = (Q7Element) dependant;

		IQ7NamedElement result = find(parent, id);
		if (result instanceof IContext) {
			return new IContext[] { (IContext) result };
		}
		return null;
	}

	@Override
	public ITestCase[] findTestcase(IQ7NamedElement dependant, String id) {
		if (dependant != null && !(dependant instanceof Q7Element)) {
			AgentPlugin.error(String.format(
					"Expected instanceof Q7Element but %s provided", dependant
							.getClass().getName()), null);
			return null;
		}
		Q7Element parent = (Q7Element) dependant;

		IQ7NamedElement result = find(parent, id);
		if (result instanceof ITestCase) {
			return new ITestCase[] { (ITestCase) result };
		}
		return null;
	}

	public IQ7NamedElement find(Q7Element parent, String id) {
		Q7ArtifactRef cxRef = contexts.get(id);
		if (cxRef == null || (cxRef.getKind() != RefKind.CONTEXT && cxRef.getKind() != RefKind.VERIFICATION)) {
			AgentPlugin.error(String.format("Cannot find context %s ", id),
					null);
			return null;
		}

		try {
			EObject content = dir.getResource(cxRef).getContent();

			if (content instanceof GroupContext) {
				return getGroupContext(parent, (GroupContext) content);
			} else if (content instanceof Context) {
				Context ctx = (Context) content;
				return new Q7InternalContext(parent, id, ctx);
			} else if (content instanceof Verification) {
				return new Q7InternalVerification(parent, id, (Verification) content);
			}

			AgentPlugin.error(
					String.format("%s is not valid context reference", id),
					null);
			return null;

		} catch (Exception e) {
			AgentPlugin.error("Error getting context " + id, e);
			return null;
		}
	}

	private IContext getGroupContext(Q7Element parent, GroupContext cx)
			throws CoreException {

		Q7Context result = new Q7InternalContext(parent, cx.getId(), cx);
		for (String id : cx.getContextReferences()) {
			IQ7NamedElement child = find(result, id);
			if (child == null || !(child instanceof IContext)) {
				throw AgentPlugin.createException(String.format(
						"Group context %s refers to %s which cannot be found",
						cx.getId(), id));
			}
			cx.getContexts().add((Context) child.getNamedElement());
		}
		return result;
	}

	@Override
	public ITestSuite[] findTestsuites(IQ7NamedElement dependant, String id) {
		return null;
	}

	@Override
	public IVerification[] findVerification(IQ7NamedElement dependant, String id) {
		if (dependant != null && !(dependant instanceof Q7Element)) {
			AgentPlugin.error(String.format(
					"Expected instanceof Q7Element but %s provided", dependant
							.getClass().getName()), null);
			return null;
		}
		Q7Element parent = (Q7Element) dependant;

		IQ7NamedElement result = find(parent, id);
		if (result instanceof IVerification) {
			return new IVerification[] { (IVerification) result };
		}
		return null;
	}
}
