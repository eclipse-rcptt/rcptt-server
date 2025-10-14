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

import static org.eclipse.rcptt.cloud.util.internal.UtilPlugin.createException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl;

public class EmfResourceUtil {

	private static ResourceImpl createResource() {
		return new ECLBinaryResourceImpl();
	}

	public static void save(File file, EObject obj) throws CoreException {
		try {
			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}

			try (OutputStream out = new BufferedOutputStream(new FileOutputStream(
					file))) {
				save(out, obj);
			}
		} catch (IOException e) {
			throw createException("Can't save object", e);
		}
	}

	public static void save(OutputStream out, EObject obj) throws CoreException {
		Resource r = createResource();
		r.getContents().add(obj);
		try {
			r.save(out, null);
		} catch (IOException e) {
			throw createException("Can't save object", e);
		}
	}

	public static <T> T load(InputStream in, Class<T> clazz) throws IOException {
		Resource r = createResource();
		r.load(in, null);
		if (r.getContents().isEmpty()) {
			return null;
		}

		return clazz.cast(r.getContents().get(0));
	}

	public static <T> T load(File file, Class<T> clazz) throws IOException {
		try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
			T result = load(in, clazz);
			return result;
		}
	}

	public static String byteToStr(byte[] data) {
		StringBuilder result = new StringBuilder();
		for (byte b : data) {
			result.append(Integer.toHexString(b & 0xFF));
		}
		return result.toString();
	}

	@SuppressWarnings("resource")
	public static InputStream toInputStream(EObject obj) {
		CompletableFuture<Void> completionFlag = new CompletableFuture<Void>();
		PipedOutputStream sink = new PipedOutputStream();
		FutureTask<Void> task = new FutureTask<>(() -> {
			try {
				Resource r = createResource();
				r.getContents().add(EcoreUtil.copy(obj));
				r.save(sink, null);
			} catch (Throwable e) {
				completionFlag.completeExceptionally(e);
			} finally {
				completionFlag.complete(null); // If completed after close(),  PipedInputStream.close() can't distinguish normal input exhaustion and early close().
				sink.close();
			}
			return null;
		});
		completionFlag.whenComplete((e, r) -> {
			task.cancel(true);
		});
		PipedInputStream result;
		try {
			result = new PipedInputStream(sink) {
				@Override
				public void close() throws IOException {
					super.close();
					try {
						task.cancel(true); // on early PipedInputStream close, free up resources
						completionFlag.get(); // on normal completion check for writing errors
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						throw new IOException(e);
					} catch (ExecutionException e) {
						if (e.getCause() instanceof IOException checked) {
							throw checked;
						}
						throw new IOException(e.getCause());
					}
				}	
			};
		} catch (IOException e) {
			// Already connected error can not happen
			throw new AssertionError(e);
		}
		CompletableFuture.runAsync(task).whenComplete((result2, error) -> {
			if (error == null) {
				completionFlag.complete(result2);
			} else {
				completionFlag.completeExceptionally(error);
			}
		});
		return result;
	}
}
