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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.IServerContext;

public class AddTestSuiteService extends SingleCommandService<AddTestSuite> {

	public AddTestSuiteService() {
		super(AddTestSuite.class);
	}

	@Override
	public Iterable<Q7ArtifactRef> serviceTyped(AddTestSuite command, IServerContext context)
			throws InterruptedException, CoreException {
		AddTestSuite addTestSuite = (AddTestSuite) command;

		ExecutionEntry suiteHandle = context.getExecutionRegistry()
				.getSuiteHandle(addTestSuite.getSuiteId());
		suiteHandle.setTestSuite(addTestSuite.getSuite());
		return suiteHandle.getUnresolvedReferences();
	}

}
