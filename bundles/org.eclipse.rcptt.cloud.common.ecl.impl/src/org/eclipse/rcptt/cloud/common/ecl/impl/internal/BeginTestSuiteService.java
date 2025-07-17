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
package org.eclipse.rcptt.cloud.common.ecl.impl.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.cloud.common.CommonPlugin;
import org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.BoxedValues;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public class BeginTestSuiteService implements ICommandService {
	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		try {
			BeginTestSuite addTestSuite = (BeginTestSuite) command;
			CommonPlugin
					.getDefault()
					.getLog()
					.log(new Status(IStatus.INFO, CommonPlugin.PLUGIN_ID,
							"BeginTestSuite " + addTestSuite.getSuiteId()));
			ISMHandleStore<SuiteStats> store = ISMCore.getInstance()
					.getSuiteStore();
			ISMHandle<SuiteStats> suiteHandle = store.getHandle(addTestSuite.getSuiteId());
			ExecutionEntry suite = ExecutionRegistry.getInstance().beginNewSuite(suiteHandle);

			context.getOutput().write(BoxedValues.box(suite.getSuiteId()));
		} catch (CoreException e) {
			CommonPlugin.getDefault().getLog().log(e.getStatus());
			throw e;
		}
		return Status.OK_STATUS;
	}
	

}
