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

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Iterables.getFirst;
import static com.google.common.collect.Iterables.limit;
import static com.google.common.collect.Iterables.transform;
import static org.eclipse.rcptt.cloud.server.ExecutionIndex.isDone;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.foldLeft;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.getCpuTime;
import static org.eclipse.rcptt.cloud.server.rest.internal.Utils.writeJsonResponse;
import static java.lang.Integer.parseInt;
import static java.util.Collections.singletonList;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.Fields.Field;
import org.eclipse.rcptt.core.utils.TagsUtil;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.reporting.util.Q7ReportIterator;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Node;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Screenshot;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionIndex;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.app.ContextEscape;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.rest.internal.Utils.FoldFunction;

public class ExecutionsHandler extends Handler.Abstract {

	@Override
	public boolean handle(Request request, Response response, Callback callback) throws IOException {
		String[] targetPath = getTargetPath(Request.getPathInContext(request));
		ExecutionIndex index = ContextEscape.getExecutionIndex(getServer());

		if (isExecList(targetPath)) {
			writeJsonResponse(handleExecutionList(getLimit(request), getFilters(request), getSuiteNameFilter(request), index),
					response);
		} else if (isSingleExec(targetPath)) {
			writeJsonResponse(handleSingleExecution(getExecutionId(targetPath), index), response);
		} else if (isReport(targetPath)) {
			writeJsonResponse(handleReport(getExecutionId(targetPath), index), response);
		} else if (isArtifactList(targetPath)) {
			writeJsonResponse(handleArtifactList(getExecutionId(targetPath), index), response);
		} else if (isArtifactFile(targetPath)) {
			Response.sendRedirect(request, response, callback,
					handleArtifactFile(getExecutionId(targetPath), getArtifactFile(targetPath), index).toString());
		} else if (ScreenshotParams.isScreenshot(targetPath)) {
			handleScreenshot(ScreenshotParams.fromRequest(targetPath, request), request, response, callback);
		}
		callback.succeeded();
		return true;
	}

	private String getArtifactFile(String[] targetPath) {
		return targetPath[2];
	}

	private static String[] getTargetPath(String target) {
		return Iterables.toArray(Splitter.on("/").omitEmptyStrings().split(target), String.class);
	}

	private static boolean isSingleExec(String[] target) {
		return target.length == 1;
	}

	private static boolean isExecList(String[] target) {
		return target.length == 0;
	}

	private static boolean isReport(String[] target) {
		return target.length == 2 && target[1].equals("report");
	}

	private static boolean isArtifactList(String[] target) {
		return target.length == 2 && target[1].equals("artifacts");
	}

	private static boolean isArtifactFile(String[] target) {
		return target.length == 3 && target[1].equals("artifacts");
	}

	private static boolean isScreenshot(String[] target) {
		return target.length >= 4 && target[1].equals(REPORT) && target[3].equals(SCREENSHOT);
	}

	private static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static final String SCREENSHOT = "screenshot";
	private static final String REPORT = "report";
	private static final String THUMBNAIL = "thumbnail";

	private static final class ScreenshotParams {
		public ScreenshotParams(int execId, int testId, int screenshotIndex, boolean thumbnail, int thumbnailWidth,
				int thumbnailHeight) {
			this.execId = execId;
			this.testId = testId;
			this.screenshotIndex = screenshotIndex;
			this.thumbnail = thumbnail;
			this.thumbnailWidth = thumbnailWidth;
			this.thumbnailHeight = thumbnailHeight;
		}

		public static boolean isScreenshot(String[] target) {
			if (target.length < 4) {
				return false;
			}
			if (!isNumber(target[0]) || !REPORT.equals(target[1]) || !isNumber(target[2])
					|| !SCREENSHOT.equals(target[3])) {
				return false;
			}

			if (target.length > 4 && !isNumber(target[4])) {
				return false;
			}

			if (target.length > 5 && !THUMBNAIL.equals(target[5])) {
				return false;
			}

			return target.length <= 6;
		}

		public static ScreenshotParams fromRequest(String[] target, Request req) {
			int execId = parseInt(target[0]);
			int testId = parseInt(target[2]);
			int screenshotIndex = target.length > 4 ? parseInt(target[4]) : -1;
			boolean thumbnail = target.length > 5;
			int thumbnailWidth = thumbnail ? getParam(req, WIDTH, DEFAULT_WIDTH, Integer.class) : -1;
			int thumbnailHeight = thumbnail ? getParam(req, HEIGHT, DEFAULT_HEIGHT, Integer.class) : -1;
			return new ScreenshotParams(execId, testId, screenshotIndex, thumbnail, thumbnailWidth, thumbnailHeight);
		}

		public final int execId;
		public final int testId;
		public final int screenshotIndex;
		public final boolean thumbnail;
		public final int thumbnailWidth;
		public final int thumbnailHeight;
	}

	private void handleScreenshot(ScreenshotParams params, Request request, Response response, Callback callback)
			throws IOException {

		ISMHandle<Execution> exec = ContextEscape.getExecutionIndex(getServer()).getExecution(params.execId);
		Iterable<Report> iterable = new Q7ReportIterator(new File(exec.getFileRoot(), "q7.report"));
		Report report = get(iterable, params.testId);

		List<Screenshot> screenshots = ReportUtils.findScreenshots(report.getRoot());
		int screenshotIndex = params.screenshotIndex >= 0 ? params.screenshotIndex
				: params.screenshotIndex + screenshots.size();
		Screenshot screenshot = get(screenshots, screenshotIndex, null);
		if (screenshot == null) {
			send404(request, response, callback);
			return;
		}
		byte[] data = params.thumbnail ? resizePng(screenshot.getData(), params.thumbnailWidth, params.thumbnailHeight)
				: screenshot.getData();
		Utils.sendPng(response, data);
	}

	private static byte[] resizePng(byte[] original, int width, int height) {
		try {
			// Read the input PNG into a BufferedImage
			ByteArrayInputStream inputStream = new ByteArrayInputStream(original);
			BufferedImage originalImage = ImageIO.read(inputStream);

			if (originalImage == null) {
				throw new IOException("The input byte array is not a valid PNG image.");
			}

			// Create a new BufferedImage with the desired dimensions
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			// Draw the original image scaled to the new dimensions
			Graphics2D g2d = resizedImage.createGraphics();
			g2d.drawImage(originalImage, 0, 0, width, height, null);
			g2d.dispose();

			// Write the resized image to a byte array
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, "png", outputStream);

			return outputStream.toByteArray();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static void send404(Request request, Response response, Callback callback) throws IOException {
		Response.writeError(request, response, callback, HttpStatus.NOT_FOUND_404);
	}

	public URI handleArtifactFile(int id, String name, ExecutionIndex index) {
		ISMHandle<Execution> exec = index.getExecution(id);
		return URI.create(String.format("/artifacts/%s/%s/%s", exec.apply(getSuiteId), exec.apply(getBuildId), name));
	}

	public JsonElement handleArtifactList(int id, ExecutionIndex index) {
		return toArray(filter(transform(getArtifactMap(index.getExecution(id)).entrySet(), artifactToJson), notNull()));

	}

	private static final Function<Entry<File, String>, JsonElement> artifactToJson = new Function<Entry<File, String>, JsonElement>() {
		@Override
		public JsonElement apply(Entry<File, String> input) {
			File file = input.getKey();
			if (!file.exists()) {
				return null;
			}
			JsonObject result = new JsonObject();
			result.addProperty("name", file.getName());
			result.addProperty("type", input.getValue());
			result.addProperty("size", file.length());
			result.addProperty("created", file.lastModified());
			return result;
		}
	};

	private static final Map<File, String> getArtifactMap(final ISMHandle<Execution> exec) {
		return exec.apply(new Function<Execution, Map<File, String>>() {
			@Override
			public Map<File, String> apply(Execution input) {
				Map<File, String> result = new LinkedHashMap<File, String>();
				result.put(new File(exec.getFileRoot(), "q7.report"), "report");
				String aut = getAutName(input, exec);
				if (aut != null) {
					result.put(new File(exec.getFileRoot(), aut), "aut");
				}
				return result;
			}

		});
	}

	private static String getAutName(Execution input, ISMHandle<Execution> entry) {
		if (!input.getAutNames().isEmpty()) {
			return input.getAutNames().get(0);
		}
		String autMetaName = getFirst(filter(input.getMetadataArtifacts(), new Predicate<String>() {
			@Override
			public boolean apply(String input) {
				return input.endsWith(".properties") && input.startsWith("aut");
			}
		}), null);
		if (autMetaName == null) {
			return null;
		}
		File autMetaFile = new File(entry.getFileRoot(), autMetaName);
		if (autMetaFile.exists()) {
			Properties p = new Properties();
			try (InputStream inputStream = new BufferedInputStream(new FileInputStream(autMetaFile))) {
				p.load(inputStream);
				String uri = p.getProperty("uri");
				return (uri != null) ? ExecutionEntry.getAutNameFromUri(uri, null) : null;
			} catch (Exception e) {
				ServerPlugin.logErr(e, "Failed");
				return null;
			}
		}
		return null;
	}

	public JsonElement handleReport(int id, ExecutionIndex index) {
		ISMHandle<Execution> exec = index.getExecution(id);
		try (Q7ReportIterator iterable = new Q7ReportIterator(new File(exec.getFileRoot(), "q7.report"))) {
			return toArray(transformi(iterable, reportToJson));
		}
	}

	private static final IndexFunction<Report, JsonElement> reportToJson = new IndexFunction<Report, JsonElement>() {
		@Override
		public JsonElement apply(Report input, int index) {
			JsonObject test = new JsonObject();
			Q7Info info = getInfo(input.getRoot());
			test.addProperty("id", index);
			test.addProperty("name", input.getRoot().getName());
			test.addProperty("description", info.getDescription());
			test.addProperty("screenshotCount", ReportUtils.findScreenshots(input.getRoot()).size());
			test.add("tags", getTags(info.getTags()));
			test.addProperty("time", input.getRoot().getEndTime() - input.getRoot().getStartTime());
			boolean fail = SimpleSeverity.create(info) != SimpleSeverity.OK;

			test.addProperty("result", fail ? "FAILURE" : "SUCCESS");
			if (fail) {
				test.addProperty("failureReason", ReportUtils.getFailMessage(input.getRoot()));
			}
			return test;
		}

		private Q7Info getInfo(Node node) {
			return (Q7Info) node.getProperties().get(IQ7ReportConstants.ROOT);
		}

		private JsonArray getTags(String tags) {
			return toArray(transform(TagsUtil.extractTags(tags), new Function<String, JsonElement>() {
				@Override
				public JsonElement apply(String input) {
					return new JsonPrimitive(input);
				}
			}));
		}

	};

	public JsonElement handleSingleExecution(int id, ExecutionIndex index) {
		return toArray(transform(singletonList(index.getExecution(id)), entryToJson));
	}

	public JsonElement handleExecutionList(int limit, final Iterable<JsonFilter> filters, String suiteNameFilter, ExecutionIndex index) {
		return toArray(
				limit(filter(transform(index.getExecutions(suiteNameFilter), entryToJson), and(filters)), limit));
	}

	private static int getExecutionId(String[] target) {
		return Integer.parseInt(target[0]);
	}

	private static int getLimit(Request request) {
		return getParam(request, LIMIT, DEFAULT_LIMIT, Integer.class);
	}

	private static Iterable<JsonFilter> getFilters(Request request) {
		List<JsonFilter> result = new ArrayList<JsonFilter>();
		Fields parameters;
		try {
			parameters = Request.getParameters(request);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		for (Field entry : parameters) {
			String key = entry.getName();
			if (key.equals(LIMIT)) {
				continue;
			}

			List<String> values = entry.getValues();
			if (values.isEmpty()) {
				continue;
			}

			String value = values.iterator().next();
			result.add(new JsonFilter(key, value));
		}
		return result;
	}

	private static String getSuiteNameFilter(Request request) {
		try {
			return Request.getParameters(request).getValues(SUITE_FIELD).stream().filter(s -> s != null && !s.isEmpty())
					.findFirst().orElse(null);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private static final Function<Execution, String> getSuiteId = new Function<Execution, String>() {
		@Override
		public String apply(Execution input) {
			return input.getSuiteId();
		}
	};

	private static final Function<Execution, String> getBuildId = new Function<Execution, String>() {
		@Override
		public String apply(Execution input) {
			return input.getId();
		}
	};

	private static final FoldFunction<JsonElement, JsonArray> addElement = new FoldFunction<JsonElement, JsonArray>() {
		@Override
		public JsonArray apply(JsonArray t, JsonElement f) {
			t.add(f);
			return t;
		}
	};

	private static final Function<ISMHandle<Execution>, JsonElement> entryToJson = new Function<ISMHandle<Execution>, JsonElement>() {
		@Override
		public JsonElement apply(final ISMHandle<Execution> entry) {
			return entry.apply(new Function<Execution, JsonElement>() {
				@Override
				public JsonElement apply(Execution exec) {
					JsonObject result = new JsonObject();
					result.addProperty("id", exec.getGlobalID());
					result.addProperty(SUITE_FIELD, exec.getSuiteId());
					result.addProperty("aut", getAutName(exec, entry));
					result.add("tests", fillTestInfo(exec));
					result.addProperty(STATE_FIELD, exec.getState().getLiteral());
					result.add("metadata", fillMetadata(exec));
					result.add("time", fillTimeInfo(exec));
					return result;
				}

			});
		}

		private JsonObject fillMetadata(Execution exec) {
			JsonObject metadata = new JsonObject();
			for (Entry<String, String> entry : exec.getMetadata().entrySet()) {
				// TODO: find out where this null is coming from
				if (entry == null)
					continue;
				metadata.addProperty(entry.getKey(), entry.getValue());
			}
			return metadata;
		}

		private JsonObject fillTestInfo(Execution exec) {
			JsonObject tests = new JsonObject();
			tests.addProperty("failed", exec.getFailedCount());
			tests.addProperty("succeeded", exec.getPassedCount());
			tests.addProperty("canceled", exec.getCanceledCount());
			tests.addProperty("total", exec.getTotalCount());
			tests.addProperty("executed", exec.getExecutedCount());
			return tests;
		}

		private JsonObject fillTimeInfo(Execution exec) {
			long endTime = isDone(exec) ? exec.getEndTime() : System.currentTimeMillis();
			long elapsedCloud = endTime - exec.getStartTime();
			long elapsedCpu = getCpuTime(exec);

			int total = exec.getTotalCount();
			int done = exec.getExecutedCount() + exec.getCanceledCount();

			long estimatedCloud = -1;
			long estimatedCpu = -1;
			if (done > 0) {
				float fraction = total / (float) done;
				estimatedCloud = (long) (elapsedCloud * fraction);
				estimatedCpu = (long) (elapsedCpu * fraction);
			}
			JsonObject time = new JsonObject();
			time.addProperty("started", exec.getStartTime());
			time.addProperty("elapsedCpu", elapsedCpu);
			time.addProperty("elapsedCloud", elapsedCloud);
			time.addProperty("estimatedCpu", estimatedCpu);
			time.addProperty("estimatedCloud", estimatedCloud);
			time.addProperty("agents", exec.getAgentStats().size());
			return time;
		}
	};

	private static final String LIMIT = "limit";
	private static final String SUITE_FIELD = "testSuiteName";
	private static final String STATE_FIELD = "status";
	private static final int DEFAULT_LIMIT = 10;
	private static final String WIDTH = "width";
	private static final String HEIGHT = "height";
	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_HEIGHT = 100;

	private static interface IndexFunction<F, T> {
		T apply(F f, int index);
	}

	private static <F, T> Iterable<T> transformi(Iterable<F> elements, IndexFunction<F, T> map) {
		List<T> result = new ArrayList<T>();
		int index = 0;
		for (F f : elements) {
			result.add(map.apply(f, index));
			index++;
		}
		return result;
	}

	private static JsonArray toArray(Iterable<JsonElement> elements) {
		return foldLeft(new JsonArray(), elements, addElement);
	}

	private static <T> T getParam(Request request, String name, T defVal, Class<T> clazz) {
		Field field;
		try {
			field = Request.getParameters(request).get(name);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		if (field == null) {
			return defVal;
		}
		String strVal = field.getValue();
		if (strVal == null) {
			return defVal;
		}

		if (clazz.equals(String.class)) {
			return clazz.cast(strVal);
		}
		if (defVal instanceof Integer) {
			try {
				return clazz.cast(Integer.parseInt(strVal));
			} catch (NumberFormatException e) {
				return defVal;
			}
		}

		if (Enum.class.isAssignableFrom(clazz)) {
			try {
				return clazz.cast(Enum.valueOf((Class<? extends Enum>) clazz, strVal));
			} catch (IllegalArgumentException ex) {
				return defVal;
			}
		}

		if (defVal instanceof String) {
			return clazz.cast(strVal);
		}

		if (defVal == null) {
			return null;
		}

		throw new IllegalArgumentException("Don't know how to convert to " + clazz);
	}

}
