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
package org.eclipse.rcptt.cloud.util;

import org.eclipse.core.runtime.IStatus;

public class StatusWriter {

	private static final String EOL = System.getProperty("line.separator");
	private static final String TAB = "  ";
	private static final String PROMT = "|-";

	private final IStatus status;

	public StatusWriter(IStatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		print(status, result, 0);
		return result.toString();
	}

	private void print(IStatus status, StringBuilder result, int depth) {
		printTabs(result, depth);
		result.append(status.getMessage()).append(EOL);
		if (status.isMultiStatus()) {
			for (IStatus child : status.getChildren()) {
				print(child, result, depth + 1);
			}
		}
	}

	private void printTabs(StringBuilder result, int depth) {
		for (int index = 0; index < depth; index++) {
			result.append(TAB);
		}
		if (depth > 0) {
			result.append(PROMT);
		}
	}

}
