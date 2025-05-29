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
package org.eclipse.rcptt.cloud.server.internal.providers.http;

import org.eclipse.rcptt.cloud.common.UriUtil;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.server.internal.providers.BaseServerAutProvider;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpsSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;

public class HttpServerAutProvider extends BaseServerAutProvider {

	private static final String SCHEME_HTTP = "http";
	private static final String SCHEME_HTTPS = "https";


	@Override
	public boolean isSupported(AutInfo aut) {
		return isSupported(aut, SCHEME_HTTP) || isSupported(aut, SCHEME_HTTPS);
	}

	private boolean isSupported(AutInfo aut, String scheme) {
		try {
			return aut.getUri().startsWith(scheme + "://");
		} catch (Exception ex) {
			return false;
		}
	}


	@Override
	protected ISrcFactory getMd5SourceFactory(AutInfo aut, String classifier) {
		String uri = UriUtil.autMd5(aut.getUri(), classifier);
		if (isSupported(aut, SCHEME_HTTP)) {
			return new HttpSrc(uri);
		} else {
			return new HttpsSrc(uri);
		}
	}


	@Override
	protected ISrcFactory getZipSourceFactory(AutInfo aut, String classifier) {
		String uri = UriUtil.autZip(aut.getUri(), classifier);
		return new HttpSrc(uri);
	}

}
