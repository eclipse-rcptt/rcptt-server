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
package org.eclipse.rcptt.cloud.server.tests.auts;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.eclipse.rcptt.cloud.agent.autManager.impl.http.ServerUriAutProvider;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;

public class ServerUriAUTProviderTests {
	
	@Test
	public void testIsSupported() throws Throwable {
		ServerUriAutProvider.setServerInfo("127.0.0.1", 8080);
		ServerUriAutProvider p = new ServerUriAutProvider();
		Assert.assertTrue(p
				.isSupport(createInfo("server://localhost/artifacts/?this=query#fragment1")));
		Assert.assertFalse(p
				.isSupport(createInfo("server2://localhost/artifacts/?this=query#fragment1")));
		Assert.assertFalse(p
				.isSupport(createInfo("server2//localhost/artifacts/?this=query#fragment1")));
	}

	@Test
	public void testConversions() throws Throwable {
		ServerUriAutProvider.setServerInfo("127.0.0.1", 8080);
		ServerUriAutProvider p = new ServerUriAutProvider();
		Assert.assertEquals(
				"http://127.0.0.1:8080/artifacts/?this=query#fragment1",
				ServerUriAutProvider.getUri(createInfo("server://localhost/artifacts/?this=query#fragment1")));
		Assert.assertEquals(
				"http://127.0.0.1:8080/artifacts/?this=query#fragment1",
				ServerUriAutProvider.getUri(createInfo("server:///artifacts/?this=query#fragment1")));
		Assert.assertEquals(
				"http://test@127.0.0.1:8080/artifacts/?this=query#fragment1",
				ServerUriAutProvider.getUri(createInfo("server://test@pumpkin/artifacts/?this=query#fragment1")));
	}

	private AutInfo createInfo(String infoValue) {
		AutInfo info = ModelFactory.eINSTANCE.createAutInfo();
		info.setUri(infoValue);
		return info;
	}
}
