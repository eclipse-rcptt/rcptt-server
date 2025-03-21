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
package org.eclipse.rcptt.cloud.server.rest.internal;

import java.util.Iterator;

import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.gson.JsonElement;

public class JsonFilter implements Predicate<JsonElement> {
	public JsonFilter(Iterable<String> path, String value) {
		this.path = path;
		this.value = value;
	}

	public JsonFilter(String path, String value) {
		this(splitter.split(path), value);
	}

	private static final Splitter splitter = Splitter.on('.')
			.omitEmptyStrings();

	public final Iterable<String> path;
	public final String value;

	public boolean apply(JsonElement element) {
		return value.equals(getValue(path.iterator(), element));
	}

	private static String getValue(Iterator<String> path, JsonElement value) {
		if (!path.hasNext()) {
			return value == null ? null : !value.isJsonPrimitive() ? null
					: value.getAsString();
		}

		String field = path.next();
		if (!value.isJsonObject()) {
			return null;
		}
		return getValue(path, value.getAsJsonObject().get(field));
	}
}
