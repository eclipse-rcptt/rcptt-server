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
package org.eclipse.rcptt.cloud.server.app.internal.http.handlers.stats;

import org.eclipse.rcptt.reporting.core.SimpleSeverity;

public class ImageUtil {

	public static String getSeverityImageUrl(SimpleSeverity severity) {
		switch (severity) {
		case ERROR:
			return "/images/tests/testerr.gif";
		case OK:
			return "/images/tests/testok.gif";
		case CANCEL:
			return "/images/tests/unknown.gif";
		}
		throw new IllegalArgumentException("Can't process severity " + severity);
	}

	public static String getSeverityImageTag(SimpleSeverity severity) {
		return String.format("<img src=\"%s\"></img>", getSeverityImageUrl(severity));
	}
}
