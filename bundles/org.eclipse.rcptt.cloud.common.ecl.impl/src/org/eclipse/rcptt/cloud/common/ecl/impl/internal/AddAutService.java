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
import org.eclipse.rcptt.ecl.runtime.SingleCommandService;

import org.eclipse.rcptt.cloud.common.CommonPlugin;
import org.eclipse.rcptt.cloud.common.commonCommands.AddAut;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;

public class AddAutService extends SingleCommandService<AddAut> {

	public AddAutService() {
		super(AddAut.class);
	}

	@Override
	protected Object serviceTyped(AddAut command) throws InterruptedException, CoreException {
		ExecutionEntry suite = ExecutionRegistry.getInstance().getSuiteHandle(
				command.getSuiteId());
		AutInfo info = command.getAut();
		info = suite.addAutForDownload(info);
		if (info == null) {
			throw new CoreException(new Status(IStatus.ERROR, CommonPlugin.PLUGIN_ID, "Failed to load AUT " + info));
		}
		return info;
	}

}
