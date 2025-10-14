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
package org.eclipse.rcptt.cloud.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.rcptt.ecl.core.CoreFactory;
import org.eclipse.rcptt.ecl.core.EclString;
import org.junit.Test;

public class EmfResourceUtilTest {

	/**
	 * This fails if a delay is added after PipedOutputStream.close() 
	 */
	@Test
	public void closeOfExhaustedStreamShouldNotThrow() throws IOException {
		final EclString eclString = CoreFactory.eINSTANCE.createEclString();
		eclString.setValue("blah");
		for (int i = 0; i < 1000; i++) {
			try (InputStream is = EmfResourceUtil.toInputStream(eclString);
					OutputStream os = OutputStream.nullOutputStream()) {
				is.transferTo(os);
			}
		}
	}

}
