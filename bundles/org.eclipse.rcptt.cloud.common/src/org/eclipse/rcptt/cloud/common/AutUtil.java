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
package org.eclipse.rcptt.cloud.common;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import org.eclipse.rcptt.cloud.model.AutInfo;

public class AutUtil {

	public static String getName(AutInfo aut) {
		return aut.getId();
	}

	private static final class Key {
		private final Object[] fields;
		private int hash = 0;
		
		public Key(String id, String uri, byte[] hash) {
			fields = new Object[] {requireNonNull(id), requireNonNull(uri), Arrays.copyOf(hash, hash.length)};
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Key that) {
				return Arrays.deepEquals(fields, that.fields);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			if (hash == 0) {
				hash = Arrays.deepHashCode(fields);
			}
			return hash;
		}
		
	}
	
	public static Object getAutKey(AutInfo info) {
		return new Key(info.getId(), info.getUri(), info.getHash());
	}

}
