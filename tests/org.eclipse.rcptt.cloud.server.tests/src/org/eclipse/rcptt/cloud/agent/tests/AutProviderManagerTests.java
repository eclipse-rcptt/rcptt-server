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
package org.eclipse.rcptt.cloud.agent.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import org.eclipse.rcptt.cloud.agent.autManager.AutProviderManager;
import org.eclipse.rcptt.cloud.model.AutInfo;

public class AutProviderManagerTests {

	@Test
	public void testGetProvider() throws IOException {
		try (AutProviderManager subject = new AutProviderManager()) {
			assertNotNull(subject.getProvider());
		}
	}

	@Test
	public void isSupport() throws IOException {
		try (AutProviderManager subject = new AutProviderManager()) {
			assertTrue(subject.getProvider().isSupport(createAut("direct://host:80/download?win32.win32.x86_64=60")));
			assertFalse(subject.getProvider().isSupport(createAut("unsupported://host:80/download?win32.win32.x86_64=60")));
		}
		
	}

	private AutInfo createAut(String string) {
		AutInfo info = org.eclipse.rcptt.cloud.model.ModelFactory.eINSTANCE.createAutInfo();
		info.setUri(string);
		return info;
	}
}
