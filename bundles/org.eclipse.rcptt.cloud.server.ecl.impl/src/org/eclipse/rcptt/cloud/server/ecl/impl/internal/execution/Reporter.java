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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.rcptt.cloud.model.Envelope;

public class Reporter {

	private final List<Envelope> queue = new LinkedList<Envelope>();

	public Reporter() {
		super();
	}

	public synchronized void sendReport(Envelope envelope,
			IExecutionProfiler profiler) {
		store(envelope);
	}

	public synchronized Envelope[] getReports() {
		Envelope[] result = queue.toArray(new Envelope[queue.size()]);
		queue.clear();
		return result;
	}

	protected void store(Envelope envelope) {
		queue.add(envelope);
	}

	public synchronized int getQueueLength() {
		return queue.size();
	}
}
