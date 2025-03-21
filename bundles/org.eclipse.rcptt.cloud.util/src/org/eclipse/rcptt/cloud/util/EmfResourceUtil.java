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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl;

public class EmfResourceUtil {
	public static byte[] md5(EObject obj) throws CoreException {
		Resource r = createResource();
		r.getContents().add(EcoreUtil.copy(obj));
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			throw createException("Can't calc md5 hash", e);
		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try {
			r.save(bout, null);
		} catch (IOException e) {
			// shoudn't happen
			throw createException("Can't serialize EMF object", e);
		}

		md.reset();
		md.update(bout.toByteArray());
		return md.digest();
	}

	private static ResourceImpl createResource() {
		return new ECLBinaryResourceImpl();
	}

	public static void save(File file, EObject obj) throws CoreException {
		try {
			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}

			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					file));
			save(out, obj);
			out.flush();
			out.close();
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
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		T result = load(in, clazz);
		in.close();
		return result;
	}

	public static String byteToStr(byte[] data) {
		StringBuilder result = new StringBuilder();
		for (byte b : data) {
			result.append(Integer.toHexString(b & 0xFF));
		}
		return result.toString();
	}
}
