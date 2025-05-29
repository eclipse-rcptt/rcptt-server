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
package org.eclipse.rcptt.cloud.server;

import java.io.Closeable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

import com.google.common.base.Throwables;

public class PrepareTaskQueue implements Closeable {
	private final ExecutorService executions = Executors.newVirtualThreadPerTaskExecutor();
	private final CompletionService<Void> completionService = new ExecutorCompletionService<Void>(executions);
	private final List<Future<Void>> tasks = Collections.synchronizedList(new ArrayList<>());

	public static interface IPrepareRunnable {
		void run() throws Exception;
	}

	public void addTask(final IPrepareRunnable r, String action) {
		synchronized (tasks) {
			tasks.add(completionService.submit(() -> {Thread.currentThread().setName(action); r.run(); return null;}));
		}
	}

	/**
	 * 
	 * @param notifier
	 *            - returns true is process should be interrupted
	 * @return true on success
	 * @throws Exception
	 *             - rethrows notifier exception or InterruptedException
	 */
	public boolean waitForTasks(BooleanSupplier isCancelled, Duration timeout) throws Exception {
		Instant stop = Instant.now().plus(timeout);
		executions.shutdown();
		while (!isCancelled.getAsBoolean() && Instant.now().isBefore(stop)) {
			if (tasks.isEmpty()) {
				return true;
			}
			Future<Void> result = completionService.poll(100, TimeUnit.MILLISECONDS);
			if (result == null) {
				continue;
			}
			assert result.isDone();
			try {
				if (!tasks.remove(result)) {
					throw new AssertionError("Unknown future detected");
				}
				result.get();
			} catch (InterruptedException e) {
				executions.shutdownNow();
				throw e;
			} catch (ExecutionException e) {
				executions.shutdownNow();
				Throwables.throwIfInstanceOf(e.getCause(), Exception.class);
				Throwables.throwIfUnchecked(e.getCause());
				throw new AssertionError(e);
			}
		}
		return false;
	}
	
	@Override
	public void close() {
		synchronized (tasks) {
			executions.close();
		}
	}

}
