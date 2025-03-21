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

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.getLast;
import static org.eclipse.rcptt.cloud.server.ExecutionIndex.getMetadata;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.foldLeft;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.getCloudTime;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.getCpuTime;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.getTotalCount;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.writeJsonResponse;
import static java.lang.Boolean.parseBoolean;

import java.io.IOException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.util.Fields;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.gson.JsonObject;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.cloud.server.rest.internal.Utils.FoldFunction;

public class StatsHandler extends Handler.Abstract {

	@Override
	public boolean handle(Request request, Response response, Callback callback)
			throws IOException {
		writeJsonResponse(
				toJson(foldLeft(emptyStats(), getSuites(isPublic(request)),
						foldSuites)), response);
		callback.succeeded();
		return true;
	}

	private static Stats emptyStats() {
		Stats result = new Stats();
		result.agents = ServerPlugin.getDefault().getAgentRegistry()
				.getAgents().size();
		return result;
	}

	private static boolean isPublic(Request request) {
		Fields parameters;
		try {
			parameters = Request.getParameters(request);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return parseBoolean(parameters.getValue(PUBLIC));
	}

	private static FoldFunction<ISMHandle<SuiteStats>, Stats> foldSuites = new FoldFunction<ISMHandle<SuiteStats>, StatsHandler.Stats>() {
		public Stats apply(Stats stats, ISMHandle<SuiteStats> suite) {
			ISMHandle<Execution> lastExec = lastExec(suite);
			if (lastExec == null) {
				return stats;
			}

			stats.projects++;
			stats.tests += lastExec.apply(getTotalCount);
			return foldLeft(stats, allExecs(suite), foldExecs);
		}

		private FoldFunction<ISMHandle<Execution>, Stats> foldExecs = new FoldFunction<ISMHandle<Execution>, StatsHandler.Stats>() {
			public Stats apply(final Stats stats, ISMHandle<Execution> handle) {
				return handle.apply(new Function<Execution, Stats>() {
					public Stats apply(Execution input) {
						stats.executions += input.getTotalCount();
						stats.cloudTime += getCloudTime(input);
						stats.cpuTime += getCpuTime(input);
						return stats;
					}
				});
			}
		};
	};

	private static Iterable<ISMHandle<SuiteStats>> getSuites(boolean isPublic) {
		return filter(ISMCore.getInstance().getSuiteStore().getHandles(),
				byPublic(isPublic));
	}

	private static Predicate<ISMHandle<SuiteStats>> byPublic(boolean isPublic) {
		return !isPublic ? Predicates.<ISMHandle<SuiteStats>> alwaysTrue()
				: new Predicate<ISMHandle<SuiteStats>>() {
					public boolean apply(ISMHandle<SuiteStats> input) {
						return isPublic(lastExec(input));
					}
				};
	}

	private static ISMHandle<Execution> lastExec(ISMHandle<SuiteStats> suite) {
		return getLast(ExecutionRegistry.getInstance().getExecutions(suite)
				.getHandles(), null);
	}

	private static Iterable<ISMHandle<Execution>> allExecs(
			ISMHandle<SuiteStats> suite) {
		return ExecutionRegistry.getInstance().getExecutions(suite)
				.getHandles();
	}

	private static boolean isPublic(ISMHandle<Execution> exec) {
		return exec != null
				&& parseBoolean(exec.apply(getMetadata).get(PUBLIC));
	}

	private static final String PUBLIC = "public";

	private static class Stats {
		public int projects;
		public int tests;
		public int executions;
		public long cpuTime;
		public long cloudTime;
		public int agents;
	}

	private static JsonObject toJson(Stats stats) {
		JsonObject result = new JsonObject();
		result.addProperty("projects", stats.projects);
		result.addProperty("tests", stats.tests);
		result.addProperty("testsExecuted", stats.executions);
		result.addProperty("agents", stats.agents);
		result.addProperty("cpuTime", stats.cpuTime);
		result.addProperty("cloudTime", stats.cloudTime);
		return result;
	}

}
