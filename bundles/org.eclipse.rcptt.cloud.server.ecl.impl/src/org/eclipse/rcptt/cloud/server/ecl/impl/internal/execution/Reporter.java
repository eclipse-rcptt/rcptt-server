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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.eclipse.rcptt.cloud.model.Envelope;

class Reporter {

	private final List<Envelope> queue = new LinkedList<Envelope>();
	private boolean closed = false;
	private long DELAY_MS = 500;

	public Reporter() {
		super();
	}

	public void sendReport(Envelope envelope) {
		synchronized (queue) {
			if (closed) {
				throw new IllegalStateException("Already closed");
			}
			queue.add(envelope);
			queue.notifyAll();
		}
	}
	
	public boolean close(BooleanSupplier isCancelled) throws InterruptedException {
		synchronized (queue) {
			closed = true;
			queue.notifyAll();
			while (!queue.isEmpty()) {
				if (isCancelled.getAsBoolean()) {
					return false;
				}
				queue.wait(DELAY_MS);
			}
			return true;
		}
	}

	public Envelope[] getReports() {
		synchronized (queue) {
			if (queue.isEmpty() && !closed) {
				try {
					queue.wait(DELAY_MS); // Prevent tight loops on an empty queue
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			Envelope[] result = queue.toArray(new Envelope[queue.size()]);
			queue.clear();
			queue.notifyAll();
			return result;
		}
	}

}
