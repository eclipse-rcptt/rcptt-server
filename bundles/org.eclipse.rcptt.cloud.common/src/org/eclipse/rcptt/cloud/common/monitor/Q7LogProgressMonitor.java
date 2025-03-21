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
package org.eclipse.rcptt.cloud.common.monitor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.rcptt.logging.IQ7Monitor;

public class Q7LogProgressMonitor implements IProgressMonitor {
	private IQ7Monitor monitor;

	public Q7LogProgressMonitor(IQ7Monitor monitor) {
		this.monitor = monitor;
	}

	public void beginTask(String name, int totalWork) {
		monitor.log(name + ": " + totalWork, null);
	}

	public void done() {
	}

	public void internalWorked(double work) {

	}

	public boolean isCanceled() {
		return false;
	}

	public void setCanceled(boolean value) {
	}

	public void setTaskName(String name) {
		monitor.log(name, null);
	}

	public void subTask(String name) {
		monitor.log("->" + name, null);
	}

	public void worked(int work) {
	}
}
