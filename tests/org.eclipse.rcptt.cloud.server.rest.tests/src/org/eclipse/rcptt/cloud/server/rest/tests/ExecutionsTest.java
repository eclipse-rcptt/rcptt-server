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

import static java.util.Collections.singletonList;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.FINISHED;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.RUNNING;
import static org.eclipse.rcptt.cloud.server.rest.resources.TestResources.getJsonFromResource;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.eclipse.rcptt.cloud.server.ExecutionIndex;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState;
import org.eclipse.rcptt.cloud.server.rest.internal.ExecutionsHandler;
import org.eclipse.rcptt.cloud.server.rest.internal.JsonFilter;
import org.eclipse.rcptt.cloud.server.rest.resources.TestResources;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.gson.JsonArray;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionsTest {
	private static final String SUITE_FIELD = "testSuiteName";
	private static final String STATE_FIELD = "status";
	
	@Mock
	public ExecutionRegistry.Repository repository;

	
	private ExecutionIndex index;
	private ExecutionsHandler handler = new ExecutionsHandler();
	
	@Before
	public void before() {
		index = new ExecutionIndex(TestResources.getExecutions(), new ExecutionRegistry(repository));
	}

	@Test
	public void testLimit() {
		assertEquals(8, ((JsonArray) handler.handleExecutionList(9,
				noFilters(), null, index)).size());
		assertEquals(4, ((JsonArray) handler.handleExecutionList(4,
				noFilters(), null, index)).size());
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
				filterSuite("com.xored-e4.jdt.tests"), null, index)).size());
	}

	@Test
	public void testFilterState() {
		assertEquals(8, ((JsonArray) handler.handleExecutionList(9,
				filterState(FINISHED), null, index)).size());
		assertEquals(0, ((JsonArray) handler.handleExecutionList(9,
				filterState(RUNNING), null, index)).size());
	}

	@Test
	public void testById() {
		JsonArray array = (JsonArray) handler.handleSingleExecution(38, index);
		assertEquals(1, array.size());
		assertEquals(getJsonFromResource("response38.json"), array);
	}
}
