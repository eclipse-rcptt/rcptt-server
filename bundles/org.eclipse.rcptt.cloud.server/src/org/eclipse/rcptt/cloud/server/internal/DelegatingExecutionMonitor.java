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
package org.eclipse.rcptt.cloud.server.internal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.rcptt.cloud.server.ExecutionListener;
import org.eclipse.rcptt.cloud.server.IExecutionService;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;

public class DelegatingExecutionMonitor implements ExecutionListener,
IExecutionService {


	@Override
	public void created(ISMHandle<Execution> execution) {
		for (ExecutionListener listener : listeners) {
			listener.created(execution);
		}
	}


	@Override
	public void started(ISMHandle<Execution> execution) {
		for (ExecutionListener listener : listeners) {
			listener.started(execution);
		}
	}


	@Override
	public void updated(ISMHandle<Execution> execution) {
		for (ExecutionListener listener : listeners) {
			listener.updated(execution);
		}
	}


	@Override
	public void completed(ISMHandle<Execution> execution) {
		for (ExecutionListener listener : listeners) {
			listener.completed(execution);
		}
	}

	private List<ExecutionListener> listeners = new CopyOnWriteArrayList<ExecutionListener>();


	@Override
	public void addListener(ExecutionListener monitor) {
		listeners.add(monitor);
	}


	@Override
	public void removeListener(ExecutionListener monitor) {
		listeners.remove(monitor);
	}

}
