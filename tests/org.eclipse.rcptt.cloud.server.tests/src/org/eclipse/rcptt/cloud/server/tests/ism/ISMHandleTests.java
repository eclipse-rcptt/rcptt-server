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
package org.eclipse.rcptt.cloud.server.tests.ism;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.ecl.core.CorePackage;
import org.eclipse.rcptt.ecl.core.EclBoolean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ISMHandleTests {
	@Rule
	public final TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test(timeout = 10000)
	public void noDeadlock() throws IOException {
		ISMHandle<EclBoolean> subject = new ISMHandle<>(temporaryFolder.newFile(), CorePackage.Literals.ECL_BOOLEAN);
		Job noise = Job.create("Capture read lock", monitor -> {
			while (!monitor.isCanceled()) {
				subject.exists();
			}
			return Status.OK_STATUS;
		});
		noise.setPriority(Job.INTERACTIVE);
		noise.schedule();
		noise.wakeUp();
		try {
			while (noise.getState() != Job.RUNNING) {
				Thread.yield();
			}
			for (int i =0; i < 10000; i++) {
				subject.commit(s -> null); // Should not hang
			}
		} finally {
			noise.cancel();
		}
	}
	
	@Test
	public void shouldNotExistAfterRemove() throws IOException {
		ISMHandle<EclBoolean> subject = new ISMHandle<>(new File(temporaryFolder.getRoot(), "test"), CorePackage.Literals.ECL_BOOLEAN);
		assertTrue(subject.exists());
		subject.remove();
		assertFalse(subject.exists());
		subject.commit(ignored -> null);
		assertTrue(subject.exists());
		subject.remove();
		assertFalse(subject.exists());
		
	}

}
