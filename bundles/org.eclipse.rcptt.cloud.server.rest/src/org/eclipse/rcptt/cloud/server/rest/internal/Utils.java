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

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.transform;
import static org.eclipse.rcptt.cloud.server.ExecutionIndex.isDone;
import static org.eclipse.rcptt.cloud.server.ExecutionIndex.isPending;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content.Sink;
import org.eclipse.jetty.server.Response;
import org.eclipse.rcptt.cloud.server.app.ReportHelper;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7AbstractHandler;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;

public class Utils {
	public static void writeJsonResponse(JsonElement json, Response response) throws IOException {
		StringWriter sw = new StringWriter();
		JsonWriter jw = new JsonWriter(sw);
		jw.setIndent("   ");
		gson.toJson(json, jw);
		byte[] responseData = sw.toString().getBytes(Charsets.UTF_8);
		response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json;charset=utf-8");
		Sink.write(response, true, ByteBuffer.wrap(responseData));
	}
	
	public static void sendPng(Response response, byte[] data) throws IOException {
		Q7AbstractHandler.sendPng(response, data);
	}


	private static final Gson gson = new Gson();

	public static <R, E> R foldLeft(R init, Iterable<E> iterable,
			FoldFunction<E, R> func) {
		R result = init;
		Iterator<E> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			result = func.apply(result, iterator.next());
		}
		return result;
	}

	public static interface FoldFunction<F, T> {
		T apply(T t, F f);
	}

	public static long getCloudTime(Execution exec) {
		return org.eclipse.rcptt.cloud.server.app.ReportHelper.getCloudTime(exec);
	}

	public static long getCpuTime(Execution exec) {
		ExecutionAgentTest firstTest = foldLeft(null,
				concat(transform(exec.getAgentStats(), getTests)),
				mostEarlyTest);
		long firstReportLen = (firstTest != null) ? (firstTest.getEndTime() - firstTest
				.getStartTime()) : 0;
		long firstReportTime = (exec.getFirstReportTime() - exec.getStartTime());
		long autStartTime = (exec.getFirstReportTime() != 0) ? (firstReportTime - firstReportLen)
				: 0;
		return foldLeft(0L, concat(transform(exec.getAgentStats(), getTests)),
				addTime) + autStartTime;
	}

	private static final FoldFunction<ExecutionAgentTest, Long> addTime = new FoldFunction<ExecutionAgentTest, Long>() {
		public Long apply(Long result, ExecutionAgentTest test) {
			return result
					+ Math.max(test.getEndTime() - test.getStartTime(), 0);
		}
	};
	private static final FoldFunction<ExecutionAgentTest, ExecutionAgentTest> mostEarlyTest = new FoldFunction<ExecutionAgentTest, ExecutionAgentTest>() {
		public ExecutionAgentTest apply(ExecutionAgentTest result,
				ExecutionAgentTest test) {
			if (test.getEndTime() == 0 || test.getStartTime() == 0) {
				return result;
			}
			return (result == null || (result.getEndTime() > test.getEndTime())) ? test
					: result;
		}
	};

	private static final Function<ExecutionAgentStats, List<ExecutionAgentTest>> getTests = new Function<ExecutionAgentStats, List<ExecutionAgentTest>>() {
		public List<ExecutionAgentTest> apply(ExecutionAgentStats input) {
			return input.getTests();
		}
	};

	public static Function<Execution, Integer> getTotalCount = new Function<Execution, Integer>() {
		public Integer apply(Execution input) {
			return input.getTotalCount();
		}
	};
}
