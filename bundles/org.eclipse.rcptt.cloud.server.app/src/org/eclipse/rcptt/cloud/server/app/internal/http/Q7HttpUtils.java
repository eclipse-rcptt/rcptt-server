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
package org.eclipse.rcptt.cloud.server.app.internal.http;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.rcptt.cloud.server.app.internal.ServerAppPlugin;

public class Q7HttpUtils {

	public static String getHeader() {
		return getResource("/webroot/templates/header");
	}

	public static String getFooter() {
		return getResource("/webroot/templates/footer");
	}

	public static String getResource(String resource) {
		URL entry = ServerAppPlugin.getDefault().getBundle().getEntry(resource);
		try {
			InputStream stream = entry.openStream();
			try {
				return new String(getStreamContent(stream), "utf-8");
			} finally {
				safeClose(stream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static byte[] getStreamContent(InputStream stream)
			throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = stream.read(buffer)) > 0) {
				output.write(buffer, 0, len);
			}
		} finally {
			safeClose(stream);
		}
		return output.toByteArray();
	}

	public static void safeClose(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (Exception e) {
		}
	}

	public static String applyParams(String indexContent, String[] names,
			String[] values) {
		String result = indexContent;
		if (names.length != values.length) {
			return indexContent;
		}
		for (int i = 0; i < names.length; i++) {
			result = result.replace("${" + names[i] + "}", values[i]);
		}
		return result;
	}
}
