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
package org.eclipse.rcptt.cloud.server.rest.tests;

import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.FINISHED;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.RUNNING;
import static org.eclipse.rcptt.cloud.server.rest.resources.TestResources.getJsonFromResource;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.JsonArray;
import org.eclipse.rcptt.cloud.server.ExecutionIndex;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState;
import org.eclipse.rcptt.cloud.server.rest.internal.ExecutionsHandler;
import org.eclipse.rcptt.cloud.server.rest.internal.JsonFilter;
import org.eclipse.rcptt.cloud.server.rest.resources.TestResources;

public class ExecutionsTest {
	private static final String SUITE_FIELD = "testSuiteName";
	private static final String STATE_FIELD = "status";

	private ExecutionsHandler handler = new ExecutionsHandler(
			new ExecutionIndex(TestResources.getExecutions()));

	@Test
	public void testLimit() {
		assertEquals(8, ((JsonArray) handler.handleExecutionList(9,
				noFilters(), null)).size());
		assertEquals(4, ((JsonArray) handler.handleExecutionList(4,
				noFilters(), null)).size());
	}

	private static Iterable<JsonFilter> noFilters() {
		return new ArrayList<JsonFilter>();
	}

	private static Iterable<JsonFilter> filterState(ExecutionState state) {
		return singletonList(new JsonFilter(STATE_FIELD, state.getLiteral()));
	}

	private static Iterable<JsonFilter> filterSuite(String suiteId) {
		return singletonList(new JsonFilter(SUITE_FIELD, suiteId));
	}

	@Test
	public void testFilterTestSuite() {
		assertEquals(4, ((JsonArray) handler.handleExecutionList(9,
				filterSuite("com.xored-e4.jdt.tests"), null)).size());
	}

	@Test
	public void testFilterState() {
		assertEquals(8, ((JsonArray) handler.handleExecutionList(9,
				filterState(FINISHED), null)).size());
		assertEquals(0, ((JsonArray) handler.handleExecutionList(9,
				filterState(RUNNING), null)).size());
	}

	@Test
	public void testById() {
		JsonArray array = (JsonArray) handler.handleSingleExecution(38);
		assertEquals(1, array.size());
		assertEquals(getJsonFromResource("response38.json"), array);
	}
}
