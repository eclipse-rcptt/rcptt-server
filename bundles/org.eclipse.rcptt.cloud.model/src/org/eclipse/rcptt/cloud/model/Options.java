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
package org.eclipse.rcptt.cloud.model;

public class Options {

	public static final String KEY_EXEC_TIMEOUT = "execTimeout";
	public static final String TESTS_PER_AGENT = "execution.tests.per.agent";
	public static final String MAX_AGENTS_TO_USE = "execution.max.agents";

	public static final int DEFAULT_EXEC_TIMEOUT = 60 * 30;

	private TestOptions options;

	public Options(TestOptions options) {
		this.options = options;
	}

	public TestOptions getTestOptions() {
		return options;
	}

	public String getValue(String key, String def) {
		String result = options.getValues().get(key);
		return result == null ? def : result;
	}

	public int getValue(String key, int def) {
		try {
			String result = options.getValues().get(key);
			return result == null ? def : Integer.parseInt(result);
		} catch (NumberFormatException ex) {
			return def;
		}
	}

}
