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
package org.eclipse.rcptt.cloud.agent.autManager.impl.http;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.rcptt.cloud.agent.autManager.impl.BaseAutProvider;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;
import org.eclipse.rcptt.cloud.util.IOUtil.ServerSrc;

public class ServerUriAutProvider extends BaseAutProvider {

	private static final String SCHEME_SERVER = "server";
	private static String serverHost;
	private static int serverPort = 0;

	@Override
	public boolean isSupport(AutInfo aut) {
		return isSupported(aut.getUri());
	}

	public static boolean isSupported(String uriValue) {
		if (serverHost == null || serverPort == 0) {
			return false;
		}
		try {
			URI uri = new URI(uriValue);
			return SCHEME_SERVER.equals(uri.getScheme());
		} catch (URISyntaxException ex) {
			return false;
		}
	}


	@Override
	protected ISrcFactory getMd5SourceFactory(AutInfo aut, String classifier) {
		return new ServerSrc(getUri(aut.getUri()) + ".md5");
	}


	@Override
	protected ISrcFactory getZipSourceFactory(AutInfo aut, String classifier) {
		return new ServerSrc(getUri(aut.getUri()));
	}
	public static String getUri(AutInfo aut) {
		return getUri(aut.getUri());
	}
	public static String getUri(String uriValue) {
		try {
			URI uri = new URI(uriValue);
			URI newURI = new URI("http", uri.getUserInfo(), serverHost,
					serverPort, uri.getPath(), uri.getQuery(),
					uri.getFragment());
			return newURI.toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// Use original
		return uriValue;
	}

	public static void setServerInfo(String serverHost, int serverPort) {
		ServerUriAutProvider.serverHost = serverHost;
		ServerUriAutProvider.serverPort = serverPort;
	}
}
