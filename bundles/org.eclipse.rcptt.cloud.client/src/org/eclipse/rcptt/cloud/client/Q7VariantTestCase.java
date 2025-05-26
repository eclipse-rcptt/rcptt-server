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
package org.eclipse.rcptt.cloud.client;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.cloud.client.SuperContextSupport.ContextConfiguration;
import org.eclipse.rcptt.core.model.IContext;
import org.eclipse.rcptt.core.model.IQ7Project;
import org.eclipse.rcptt.core.model.ITestCase;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.core.workspace.RcpttCore;
import org.eclipse.rcptt.internal.core.model.OpenableElementInfo;
import org.eclipse.rcptt.internal.core.model.Q7Element;
import org.eclipse.rcptt.internal.core.model.Q7TestCase;
import org.eclipse.rcptt.util.FileUtil;
import org.eclipse.rcptt.util.StringUtils;

public class Q7VariantTestCase extends Q7TestCase {

	private Scenario scenario = null;
	private final ITestCase testCase;
	private final ContextConfiguration configuration;

	public Q7VariantTestCase(Q7Element parent, ITestCase testCase,
			ContextConfiguration configuration) {
		super(parent, testCase.getName());
		this.testCase = testCase;
		this.configuration = configuration;
	}

	@Override
	public IFile getResource() {
		return (IFile) testCase.getResource();
	}

	@Override
	public boolean exists() {
		return testCase.exists();
	}

	@Override
	public IPath getPath() {
		return testCase.getPath();
	}

	@Override
	public IQ7Project getQ7Project() {
		return testCase.getQ7Project();
	}

	@Override
	protected Scenario getScenario() throws ModelException {
		if (scenario == null) {
			Scenario original = ((Scenario) testCase.getNamedElement());
			this.scenario = EcoreUtil.copy(original);
			this.scenario.getContexts().clear();
			this.scenario.setName(this.scenario.getName() + "("
					+ this.configuration.getVariantName() + ")");
			this.scenario.setId(this.scenario.getId() + "_"
					+ FileUtil.getID(StringUtils.join(",", this.configuration.getVariantName())));
			for (IContext ctx : this.configuration.getContexts()) {
				String id = ctx.getID();
				if (!RcpttCore.DEFAULT_WORKBENCH_CONTEXT_ID.equals(id)) {
					this.scenario.getContexts().add(id);
				}
			}
		}
		return scenario;
	}

	@Override
	public Scenario getNamedElement() throws ModelException {
		return getScenario();
	}

	@Override
	public NamedElement getModifiedNamedElement() throws ModelException {
		return getScenario();
	}

	@Override
	public String getElementName() throws ModelException {
		return testCase.getElementName();
	}

	@Override
	public String getDescription() throws ModelException {
		return testCase.getDescription();
	}

	@Override
	public String getVersion() throws ModelException {
		return testCase.getVersion();
	}

	@Override
	public NamedElement getMeta() throws ModelException {
		return getScenario();
	}

	@Override
	public String getTags() throws ModelException {
		return getScenario().getTags();
	}

	@Override
	public String getID() throws ModelException {
		return getScenario().getId();
	}

	@Override
	protected boolean buildStructure(OpenableElementInfo info,
			IProgressMonitor pm,
			IResource underlyingResource) {
		return true;
	}
}
