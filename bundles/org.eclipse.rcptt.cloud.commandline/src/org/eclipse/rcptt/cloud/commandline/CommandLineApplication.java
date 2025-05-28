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
package org.eclipse.rcptt.cloud.commandline;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public abstract class CommandLineApplication implements IApplication {
	public static final int EXIT_ILLEGAL_ARGUMENT = 64;
	public static final int TEST_FAIL_EXIT_CODE = 56;
	public static final int INTERNAL_ERROR = 57;
	protected IApplicationContext context;
	private static final ILog LOG = Platform.getLog(CommandLineApplication.class);


	public Object start(IApplicationContext context) throws Exception {
		this.context = context;
		try {
			CommandLineUtil.processCommandLine(this, (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS));
			return run();
		} catch (CoreException e) {
			if (e.getStatus().matches(IStatus.CANCEL)) {
				System.out.println("Cancelled");
				return 0;
			}
			LOG.log(new MultiStatus(getClass(), 0, new IStatus[] {e.getStatus()}, "Internal error", e));
			return INTERNAL_ERROR;
		} catch(InvalidCommandLineArgException e) {
			System.out.println(String.format("Invalid value for argument %s\n%s", e.argName, e.getMessage()));
			CommandLineUtil.printUsage(this);
			return EXIT_ILLEGAL_ARGUMENT;
		}
	}

	protected abstract Object run() throws Exception;


	public void stop() {
	}
}
