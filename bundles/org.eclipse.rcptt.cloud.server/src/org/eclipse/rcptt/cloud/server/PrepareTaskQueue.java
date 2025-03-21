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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class PrepareTaskQueue {
	private List<Thread> tasks = new ArrayList<Thread>();

	private Map<String, List<String>> lastMessages = new HashMap<String, List<String>>();

	private Exception exception;
	/*
	 * 25 minutes, Move to server options.
	 */
	private static final long pendingTimeout = 30 * 60 * 1000;

	public static interface IPrepareRunnable {
		void run() throws Exception;
	}

	public void addTask(final IPrepareRunnable r, String action) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					r.run();
				} catch (Exception e) {
					// log exception
					ServerPlugin.err(e.getMessage(), e);
					exception = e;
					// Stop all other executions,
					synchronized (tasks) {
						tasks.remove(Thread.currentThread());
						for (Thread t : tasks) {
							t.interrupt();
						}
					}
				} finally {
					synchronized (tasks) {
						tasks.remove(Thread.currentThread());
						tasks.notifyAll();
					}
				}
			}
		}, action);
		t.start();
		synchronized (tasks) {
			tasks.add(t);
		}
	}

	public List<String> getLastMessages() {
		List<String> result = new ArrayList<String>();
		synchronized (lastMessages) {
			if (lastMessages.size() > 0) {
				for (Map.Entry<String, List<String>> e : lastMessages
						.entrySet()) {
					for (String s : e.getValue()) {
						result.add(e.getKey() + ": " + s);
					}
				}
			}
			lastMessages.clear();
		}
		return result;
	}

	/**
	 * 
	 * @param notifier
	 *            - returns true is process should be interrupted
	 * @return true on success
	 * @throws Exception
	 *             - rethrows notifier exception or InterruptedException
	 */
	public boolean waitForTasks(Callable<Boolean> notifier) throws Exception {
		long start = System.currentTimeMillis();
		boolean cancelled = false;
		try {
			synchronized (tasks) {
				while (tasks.size() > 0
						&& !cancelled) {
					tasks.wait(1000);
					if (notifier != null) {
						cancelled = notifier.call();
					}
					if ((System.currentTimeMillis() - start) < pendingTimeout)
						cancelled = true;
				}
				if (cancelled) {
					// In case of timeout, lets terminate all pending tasks.
					for (Thread t : tasks) {
						t.interrupt();
					}
				}
			}
			if (exception != null) {
				return false;
			}
			return true;
		} finally {
			if (notifier != null) {
				notifier.call();
			}
		}
	}

	public Exception getException() {
		return exception;
	}

	public void addMessage(String key, String message) {
		synchronized (lastMessages) {
			List<String> list = lastMessages.get(key);
			if (list == null) {
				list = new ArrayList<String>();
				lastMessages.put(key, list);
			}
			list.add(message);
		}
	}

	public void terminate(Exception e) {
		synchronized (tasks) {
			for (Thread t : tasks) {
				t.interrupt();
			}
			tasks.clear();
			exception = e;
		}
	}
}
