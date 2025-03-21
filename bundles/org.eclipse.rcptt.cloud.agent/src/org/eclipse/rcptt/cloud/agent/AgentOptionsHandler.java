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

import org.eclipse.rcptt.core.OptionsHandler;
import org.eclipse.rcptt.launching.Q7Launcher;

public class AgentOptionsHandler extends OptionsHandler {

	private boolean restartAUTOnFailures = false;

	@Override
	protected boolean handleSpecialOption(String key, String value) {
		if (TEST_EXEC_TIMEOUT.equals(key)) {
			Q7Launcher.setLaunchTimeout(Integer.parseInt(value));
			return true;
		}
		if (RESTART_AUT_ON_FAILURE.equals(key)) {
			this.restartAUTOnFailures = ("true".equalsIgnoreCase(value));
			return true;
		}
		return false;
	}

	public boolean isRestartAUTOnFailures() {
		return restartAUTOnFailures;
	}
}
