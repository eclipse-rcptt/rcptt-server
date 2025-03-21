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
package org.eclipse.rcptt.cloud.server.rest.resources;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.xored.emfjson.Emf2Json;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;
import org.eclipse.rcptt.cloud.server.rest.stub.InMemHandle;

public class TestResources {
	public static List<ISMHandle<Execution>> getExecutions() {
		List<ISMHandle<Execution>> result = new ArrayList<ISMHandle<Execution>>();
		for (int i = 1; i <= 8; i++) {
			result.add(getExecutionFromResource(String.format("%d.json", i)));
		}
		return result;
	}

	private static final JsonParser parser = new JsonParser();
	private static final Emf2Json emf2json = new Emf2Json(
			StatsPackage.eINSTANCE);

	private static ISMHandle<Execution> getExecutionFromResource(String name) {
		return new InMemHandle((Execution) emf2json.deserialize(
				getJsonFromResource(name).getAsJsonObject(),
				StatsPackage.Literals.EXECUTION));
	}

	public static JsonElement getJsonFromResource(String name) {
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(
					TestResources.class.getResourceAsStream(name),
					Charsets.UTF_8);
			return parser.parse(reader);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
