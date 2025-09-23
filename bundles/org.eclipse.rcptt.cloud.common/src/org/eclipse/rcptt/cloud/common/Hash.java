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
package org.eclipse.rcptt.cloud.common;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hash {
	public static final byte[] hash(Path file) throws IOException {
		try (ReadableByteChannel channel = Files.newByteChannel(file, StandardOpenOption.READ)) {
			ByteBuffer buffer = createBuffer();
			MessageDigest md = createDigest();
			while (channel.read(buffer) >= 0) {
				buffer.flip();
				md.update(buffer);
				buffer.compact();
			}
			return md.digest();
		}
	}

	private static ByteBuffer createBuffer() {
		return ByteBuffer.allocateDirect(64 * 1024); // L1 cache size for CPU Intel(R) Xeon(R) CPU E5-2680 v3
	}

	public static MessageDigest createDigest() {
		try {
			return MessageDigest.getInstance(digestType);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Invalid digest type: " + digestType, e);
		}
	}
	
	private static final String digestType = "SHA-256";
}
