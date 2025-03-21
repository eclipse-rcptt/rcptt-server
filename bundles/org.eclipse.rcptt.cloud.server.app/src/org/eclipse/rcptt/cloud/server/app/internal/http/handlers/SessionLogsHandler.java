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
package org.eclipse.rcptt.cloud.server.app.internal.http.handlers;

import java.io.IOException;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;

public class SessionLogsHandler extends Q7AbstractHandler {
	public static String URI = "/sessionLogs";

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {

		writeMenuAndContent(response, Q7HttpUtils.getResource("/webroot/templates/sessionLogs.xml"),
				new StringBuilder());

		callback.succeeded();
		return true;
	}
}
