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

import java.io.File;

import org.eclipse.rcptt.cloud.agent.autManager.impl.BaseAutProvider;
import org.eclipse.rcptt.cloud.common.UriUtil;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpsSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;

public class HttpsUriAutProvider extends BaseAutProvider {

	@Override
	public boolean isSupport(AutInfo aut) {
		return HttpsSrc.isSupported(aut.getUri());
	}


	@Override
	protected ISrcFactory getMd5SourceFactory(AutInfo aut, String classifier) {
		return new HttpsSrc(UriUtil.autMd5(aut.getUri(), classifier));
	}


	@Override
	protected ISrcFactory getZipSourceFactory(AutInfo aut, String classifier) {
		return new HttpsSrc(UriUtil.autZip(aut.getUri(), classifier));
	}

}
