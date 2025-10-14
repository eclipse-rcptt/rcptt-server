/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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
package org.eclipse.rcptt.cloud.common;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;

import org.eclipse.rcptt.cloud.util.EmfResourceUtil;
import org.eclipse.rcptt.ecl.core.CoreFactory;
import org.eclipse.rcptt.ecl.core.EclString;
import org.junit.Test;

import com.google.common.hash.HashCode;

public class HashTest {

	@Test
	public void hashSimpleObject() {
		final EclString eclString = CoreFactory.eINSTANCE.createEclString();
		eclString.setValue("blah");
		assertEquals("d9c876d7ff7e58b5c46460cbb506d6a8d5371770cca3519272827f94eca76360", HashCode.fromBytes(Hash.hash(eclString)).toString() );
		eclString.setValue("bleh");
		assertEquals("0f8b942dfae6c92dc22197e577d7693d0802ff49d57bd50cb08c321a78ad82a3", HashCode.fromBytes(Hash.hash(eclString)).toString());
	}
	
	@SuppressWarnings("resource")
	@Test
	public void binaryStreamMatchesEObject() throws IOException {
		final EclString eclString = CoreFactory.eINSTANCE.createEclString();
		eclString.setValue("blah");
		try (InputStream is = EmfResourceUtil.toInputStream(eclString);
			DigestInputStream dis = new DigestInputStream(is, Hash.createDigest());
			OutputStream os = OutputStream.nullOutputStream()) {
			dis.transferTo(OutputStream.nullOutputStream());
			assertEquals(HashCode.fromBytes(Hash.hash(eclString)).toString(), HashCode.fromBytes(dis.getMessageDigest().digest()).toString());
		}
		
	}

}
