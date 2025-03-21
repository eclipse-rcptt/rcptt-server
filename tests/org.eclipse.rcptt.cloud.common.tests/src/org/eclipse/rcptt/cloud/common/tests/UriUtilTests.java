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
package org.eclipse.rcptt.cloud.common.tests;

import org.junit.Assert;
import org.junit.Test;

import org.eclipse.rcptt.cloud.common.UriUtil;

public class UriUtilTests {

	@Test
	public void testSubstitedUri() {
		String uri = "http://turbine.xored.local:8083/nexus/service/local/artifact/maven/content?"
				+ "r=vz-snapshots&g=org.vozone.sdt&a=org.vozone.sdt.rcp&v=LATEST&e=[ext]&c=[classifier]";

		String zipExpected = "http://turbine.xored.local:8083/nexus/service/local/artifact/maven/content?"
				+ "r=vz-snapshots&g=org.vozone.sdt&a=org.vozone.sdt.rcp&v=LATEST&e=zip&c=win32.win32.x86";
		String md5Expected = "http://turbine.xored.local:8083/nexus/service/local/artifact/maven/content?"
				+ "r=vz-snapshots&g=org.vozone.sdt&a=org.vozone.sdt.rcp&v=LATEST&e=zip.md5&c=win32.win32.x86";

		Assert.assertEquals(zipExpected, UriUtil.autZip(uri, CLASSIFIER));
		Assert.assertEquals(md5Expected, UriUtil.autMd5(uri, CLASSIFIER));
	}

	private static final String CLASSIFIER = "win32.win32.x86";

	@Test
	public void testBaseUri() {
		String uri = "http://maven.xored.com/nexus/content/repositories/vz-snapshots/"
				+ "org/vozone/sdt/org.vozone.sdt.rcp/4.0.0-SNAPSHOT/org.vozone.sdt.rcp-4.0.0-SNAPSHOT.zip";
		String zipExpected = "http://maven.xored.com/nexus/content/repositories/vz-snapshots/"
				+ "org/vozone/sdt/org.vozone.sdt.rcp/4.0.0-SNAPSHOT/org.vozone.sdt.rcp-4.0.0-SNAPSHOT-win32.win32.x86.zip";
		String md5Expected = "http://maven.xored.com/nexus/content/repositories/vz-snapshots/"
				+ "org/vozone/sdt/org.vozone.sdt.rcp/4.0.0-SNAPSHOT/org.vozone.sdt.rcp-4.0.0-SNAPSHOT-win32.win32.x86.zip.md5";

		Assert.assertEquals(zipExpected, UriUtil.autZip(uri, CLASSIFIER));
		Assert.assertEquals(md5Expected, UriUtil.autMd5(uri, CLASSIFIER));
	}
}
