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
package org.eclipse.rcptt.cloud.util;

import java.io.Closeable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.rcptt.ecl.client.tcp.EclTcpClientManager;
import org.eclipse.rcptt.ecl.client.tcp.EclTcpSocketStatus;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.Nullable;
import org.eclipse.rcptt.ecl.runtime.IPipe;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.ecl.runtime.ISession;

import org.eclipse.rcptt.cloud.util.internal.UtilPlugin;

public class RemoteEclClient implements Closeable {
	private static int TRY_COUNT = 10;
	private static int TRY_DELAY = 50;
	private int port;
	private InetAddress address;

	public RemoteEclClient(String host, int port) throws CoreException {
		this.port = port;
		try {
			this.address = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			throw UtilPlugin.createException("unknown host:" + host + "cause: "
					+ e.getMessage(), e);
		}
	}

	private ISession session;

	protected ISession getSession() throws CoreException {
		if (session == null) {
			try {
				session = EclTcpClientManager.Instance.startClientSession(
						address, port);
			} catch (Exception e) {
				throw new CoreException(new EclTcpSocketStatus(
						UtilPlugin.createStatus("Can't create session", e)));
			}
		}
		return session;
	}

	@Override
	public void close() {
		if (session != null) {
			try {
				session.close();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			session = null;
		}
	}

	public ExecResult execCommand(Command cmd, long timeout,
			IProgressMonitor monitor) throws CoreException, InterruptedException {
		for (int i = 0; i < TRY_COUNT; i++) {
			ExecResult result = null;
			boolean retry = false;
			try {
				result = internalExecCommand(cmd, timeout, monitor);
			} catch (CoreException e) {
				retry = e.getStatus() instanceof EclTcpSocketStatus;
				if (!retry) {
					throw e;
				}
			}
			if (!retry && result != null) {
				retry = result.status instanceof EclTcpSocketStatus;
			}
			if (retry) {
				if (session != null) {
					session.close();
				}
				session = null;
				try {
					Thread.sleep(TRY_DELAY);
				} catch (Throwable e) {
					// ignore
				}
			} else {
				return result;
			}
		}
		return  internalExecCommand(cmd, timeout, monitor);
	}

	private ExecResult internalExecCommand(Command cmd, long timeout,
			IProgressMonitor monitor) throws CoreException, InterruptedException {
		ISession session = getSession();
		IPipe outPipe = session.createPipe();
		IProcess process = session.execute(cmd, null, outPipe);
		ExecResult result = new ExecResult(outPipe, process);
		result.waitFor(timeout, monitor);
		return result;
	}

	public static class ExecResult {
		private final IPipe outPipe;
		public final IProcess process;
		private IStatus status;

		public ExecResult(IPipe outPipe, IProcess process) {
			this.outPipe = outPipe;
			this.process = process;
		}

		public IStatus waitFor(long timeout, IProgressMonitor monitor) throws InterruptedException {
			status = process.waitFor(timeout, monitor);
			return status;
		}

		public IStatus getStatus() {
			return status;
		}

		private Object[] pipeContent = null;

		public <T> T getPipeContent(Class<T> clazz) throws CoreException {
			Object[] values = getPipeContent(TAKE_TIMEOUT, 1);
			String expected = String.format("Expected %s or Nullable<%s>",
					clazz.getName(), clazz.getName());

			if (values.length == 0) {
				throw UtilPlugin.createException(String.format(
						"%s but empty content provided.", expected));
			}

			if (clazz.isInstance(values[0])) {
				@SuppressWarnings("unchecked")
				T value = (T) values[0];
				return value;
			}

			if (!(values[0] instanceof Nullable)) {
				String provided = values[0].getClass().getName();
				throw UtilPlugin.createException(String.format(
						"%s but %s provided.", expected, provided));
			}

			Nullable nullable = (Nullable) values[0];
			if (isNull(nullable)) {
				return null;
			}

			if (!clazz.isInstance(nullable.getValue())) {
				String inner = nullable.getValue().getClass().getName();
				throw UtilPlugin.createException(String.format(
						"%s but Nullable<%s> provided.", expected, inner));
			}

			@SuppressWarnings("unchecked")
			T value = (T) nullable.getValue();
			return value;
		}

		private boolean isNull(Nullable nullable) {
			if (nullable.getValue() == null) {
				return true;
			}

			String name = nullable.getValue().getClass().getName();
			return name.equals(EObjectImpl.class.getName());
		}

		public Object[] getPipeContent() throws CoreException {
			return getPipeContent(TAKE_TIMEOUT, Integer.MAX_VALUE);
		}

		public Object[] getPipeContent(int timeout, int argCount)
				throws CoreException {
			if (process.isAlive()) {
				return new Object[0];
			}

			if (pipeContent == null) {
				List<Object> objects = new ArrayList<Object>();
				Object next;
				for (int index = 0; index < argCount; index++) {
					next = outPipe.take(timeout);
					if (next == null || next instanceof IStatus) {
						break;
					}
					objects.add(next);
				}
				pipeContent = objects.toArray(new Object[objects.size()]);
			}
			return pipeContent;
		}

		public static final int TAKE_TIMEOUT = 1000;
	}

}
