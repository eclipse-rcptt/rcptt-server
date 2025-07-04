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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.CANCELED;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.FINISHED;
import static org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState.RUNNING;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;
import org.eclipse.rcptt.ecl.runtime.BoxedValues;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.launching.injection.Directory;
import org.eclipse.rcptt.launching.injection.Entry;
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;
import org.eclipse.rcptt.launching.injection.UpdateSite;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.StatusListener;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Node;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.sherlock.core.streams.SherlockReportOutputStream;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.Envelope;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Options;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.EclServerImplPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor.Listener;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskSuiteDescriptor;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsFactory;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;

public class ExecutionProfiler implements IExecutionProfiler, TaskSuiteDescriptor.Listener {

	/**
	 * Use 7 agent failure, or specified amount of agents
	 */
	private static final int CANCEL_AUT_FAILURES_LIMIT = 7;

	private final ITestStore suiteDir;
	private final Options options;
	private int totalTestCount = -1;
	private final Reporter reporter;

	private long started;
	private ProfilerThread thread;
	private int failedTestCount;
	private int executedTestCount;
	private int canceledTestCount = 0;
	private int passedTestCount = 0;

	private final IQ7Monitor monitor;
	private final IQ7Monitor errorMonitor;
	private volatile AtomicReference<IStatus> result = new AtomicReference<IStatus>();
	private volatile boolean completed = false;
	private boolean clientComplete = false;
	private final Collection<AutInfo> infos;

	private List<TaskSuiteDescriptor> suites;
	
	private Listener executionMonitor = new Listener() {

		@Override
		public void onExecute(TaskDescriptor descr) {
			monitor.log("execute task: " + descr + " on:"
					+ descr.getAgent().getUri(), null);
		}

		@Override
		public void onCancel(TaskDescriptor descr, AgentInfo agent, IStatus reason) {
			if (agent != null) {
				monitor.log(
						"# cancel task: " + descr + " from:" + agent.getUri(),
						null);
			} else {
				monitor.log("# cancel task: " + descr, null);
			}
		}

		@Override
		public void onComplete(TaskDescriptor taskDescriptor, Report report) {
			AgentInfo agent = taskDescriptor.getAgent();
			if (agent != null) {
				monitor.log("#-> complete task: " + taskDescriptor + " from:"
						+ agent.getUri(),
						null);
			} else {
				monitor.log("#-> complete task: " + taskDescriptor
						+ " no AGENT INFO",
						null);
			}
			processReport(taskDescriptor, report);
		}

		@Override
		public void onError(TaskDescriptor taskDescriptor, Throwable e, boolean fatal) {
			errorMonitor.log(ReportUtil.toString(e));
			if (fatal) {
				monitor.log("Cancelling execution due to fatal error");
				cancel(RcpttPlugin.createStatus(e));
			}
		}
	};

	public ExecutionProfiler(String suiteID, ITestStore suiteDir,
			AutInfo[] auts, TestOptions options, EMap<String, String> metadata,
			ExecutionEntry executionHandle) throws CoreException, IOException {
		this.suiteDir = suiteDir;

		// this.auts = auts;
		this.options = new Options(options);
		infos = EcoreUtil.copyAll(Arrays.asList(auts));
		handle = ExecutionRegistry.getInstance().getSuiteHandle(suiteID);
		reportFile = handle.getMetadataName("q7.report");
		reportOut = new SherlockReportOutputStream(
				new BufferedOutputStream(new FileOutputStream(reportFile)));

		Properties testOptions = new Properties();
		EMap<String, String> values = options.getValues();
		for (java.util.Map.Entry<String, String> entry : values.entrySet()) {
			testOptions.put(entry.getKey(), entry.getValue());
		}

		storePropertiesTo(handle.getMetadataName("test.options.properties"),
				testOptions);

		// Store metadata
		storeExecMetadata(metadata, handle.getHandle());

		int index = 0;
		for (AutInfo autInfo : auts) {
			storeAUTInfo(autInfo,
					handle.getMetadataName("aut" + index + ".properties"));
			index++;
		}

		reporter = new Reporter();
		executionsMonitor = handle
				.getMonitor(ExecutionEntry.CLIENT_EXECUTION_MONITOR);
		monitor = handle.getMonitor(ExecutionEntry.EXECUTION_MONITOR);
		monitor.log("#################### Initialize execution session", null);
		errorMonitor = handle.getMonitor(ExecutionEntry.ERROR_MONITOR);
		errorMonitor.log("#################### Initialize execution session",
				null);
		ismHandle = handle.getSuite();
		this.executionHandle = handle.getHandle();

	}

	// Populates suites with uninitialized objects.
	private void createSuites() throws IOException {
		int taskCount = 0;
		if (totalTestCount >= 0)
			throw new IllegalStateException("Repeated initialization");
		List<Q7ArtifactRef> scenarios = ModelUtil.scenarioList(suiteDir
				.getTestSuite());

		ArrayList<TaskSuiteDescriptor> temp = new ArrayList<TaskSuiteDescriptor>();
		for (AutInfo aut : infos) {
			// Here I need to obtain all AUT plugins.
			TaskSuiteDescriptor descr = createSuite(scenarios, aut);
			temp.add(descr);
			taskCount += descr.getTaskCount();
		}
		suites = ImmutableList.copyOf(temp);
		totalTestCount = taskCount;
		executionHandle.commit(new Function<Execution, Void>() {
			@Override
			public Void apply(Execution execStat) {
				execStat.setTotalCount(totalTestCount);
				return null;
			}
		});
	}

	private TaskSuiteDescriptor createSuite(List<Q7ArtifactRef> scenarios, AutInfo aut) throws IOException {
		int tasksPerAgent = options.getValue(Options.TESTS_PER_AGENT, 50);
		int maxAgents = options.getValue(Options.MAX_AGENTS_TO_USE, -1);
		List<TaskDescriptor> tasks = Lists.newArrayList();
		for (Q7ArtifactRef scenario : scenarios) {
			TaskDescriptor task = new TaskDescriptor(suiteDir, aut,
					options.getTestOptions(), scenario, getTestCaseName(suiteDir, scenario));
			tasks.add(task);
		}
		TaskSuiteDescriptor descr = new TaskSuiteDescriptor(getSuiteID(), aut, errorMonitor, tasksPerAgent, maxAgents,
				tasks);
		descr.addTaskListener(getMonitor());
		descr.addSuiteListener(this);
		return descr;
	}

	private String getTestCaseName(ITestStore suiteDir, Q7ArtifactRef scenario)
			throws IOException {
		if (suiteDir == null)
			throw new NullPointerException();
		if (scenario == null)
			throw new NullPointerException();
		if (scenario.getId() == null)
			throw new NullPointerException();
		Q7Artifact resource = suiteDir.getResource(scenario);
		if (resource == null)
			throw new NullPointerException("Resource " + scenario.getId()
					+ " not found " + " in " + suiteDir);
		Scenario content = (Scenario) resource.getContent();
		if (content == null)
			throw new NullPointerException("No content found for "
					+ scenario.getId() + " in " + suiteDir);
		return content.getName();
	}

	private void storeExecMetadata(final EMap<String, String> metadata,
			final ISMHandle<Execution> handle) {
		handle.commit(new Function<Execution, Void>() {
			@Override
			public Void apply(Execution input) {
				EMap<String, String> map = input.getMetadata();
				for (java.util.Map.Entry<String, String> entry : metadata
						.entrySet()) {
					map.put(entry.getKey(), entry.getValue());
				}

				if (!map.containsKey("suiteName")) {
					map.put("suiteName", getSuiteID());
				}
				if (!map.containsKey("buildID")) {
					map.put("buildID", handle.getFileName());
				}
				return null;
			}
		});
	}

	private void storeAUTInfo(AutInfo autInfo, File metadataName) {
		Properties props = new Properties();
		props.setProperty("uri", autInfo.getUri());
		props.setProperty("id", autInfo.getId());
		props.setProperty("classifier", autInfo.getClassifier());
		props.setProperty("args", toString(autInfo.getArgs()));
		props.setProperty("vmArgs", toString(autInfo.getVmArgs()));
		InjectionConfiguration injection = autInfo.getInjection();
		if (injection != null) {
			EList<Entry> list = injection.getEntries();
			int index = 0;
			for (Entry entry : list) {
				if (entry instanceof UpdateSite) {
					props.setProperty("injection." + index + ".uri",
							((UpdateSite) entry).getUri());
					props.setProperty("injection." + index + ".units",
							toString(((UpdateSite) entry).getUnits()));
				} else if (entry instanceof Directory) {
					props.setProperty("injection." + index + ".uri",
							((Directory) entry).getPath());
				}
				index++;
			}
		}
		storePropertiesTo(metadataName, props);
	}

	private void storePropertiesTo(File metadataName, Properties props) {
		BufferedOutputStream out;
		try {
			out = new BufferedOutputStream(new FileOutputStream(metadataName));
			props.store(out, "");
			out.close();
		} catch (FileNotFoundException e) {
			EclServerImplPlugin.err(e.getMessage(), e);
		} catch (IOException e) {
			EclServerImplPlugin.err(e.getMessage(), e);
		}
	}

	private String toString(List<String> args) {
		if (args != null) {
			return Arrays.toString(args.toArray());
		}
		return "";
	}

	@Override
	public String getSuiteID() {
		return handle.getSuiteId();
	}

	@Override
	public void start() {
		ismHandle.commit(new Function<SuiteStats, Void>() {
			@Override
			public Void apply(SuiteStats suiteStats) {
				if (suiteStats.getSuiteName() == null) {
					suiteStats.setSuiteName(suiteDir.getTestSuite().getId());
				}
				return null;
			}
		});

		executionHandle.commit(new Function<Execution, Void>() {
			@Override
			public Void apply(Execution execStat) {
				execStat.setStartTime(System.currentTimeMillis());
				execStat.setId(executionHandle.getFileName());
				execStat.setState(RUNNING);
				execStat.setTotalCount(totalTestCount);
				return null;
			}
		});
		executionHandle.commit(new Function<Execution, Void>() {
			@Override
			public Void apply(Execution execStat) {
				execStat.setReportFile(reportFile.getName());
				return null;
			}
		});

		started = System.currentTimeMillis();
		thread = new ProfilerThread();
		thread.start();
	}

	@Override
	public File getReportFile() {
		return reportFile;
	}

	public void finish(final IStatus result) {
		try {
			// Perform finalization
			monitor.log("Wait for finalization", null);
			String suiteId = getSuiteID();
			if (!result.isOK()) {
				errorMonitor.log("Server error ", new CoreException(result));
				sendClientMessage("Server reports error: " + result.getMessage()
						+ " See additional information in test artifacts.");
				getTaskQueue().cancel(suiteId, new CoreException(result));
			} else {
				getTaskQueue().cancel(suiteId, new RuntimeException("Test suite " + suiteId + " is finished."));
			}

			synchronized (this) {
				reportOut.close();
				monitor.log("done with suite execution:" + suiteId, null);
				if (clientComplete) {
					monitor.log("Client is sucessfully recieved all reports", null);
				} else {
					monitor.log(
							"Some reports are not recieved by client, because of client timeout.",
							null);
				}
				monitor.log("finish waiting for completion", null);
				monitor.close();
				errorMonitor.close();
			}
		} finally {

			executionHandle.commit(new Function<Execution, Void>() {
				@Override
				public Void apply(Execution execStat) {
					execStat.setFailedCount(failedTestCount);
					execStat.setExecutedCount(executedTestCount);
					execStat.setCanceledCount(canceledTestCount);
					execStat.setPassedCount(passedTestCount);
					execStat.setEndTime(System.currentTimeMillis());
					execStat.setState(result.isOK() ? FINISHED : CANCELED);
					execStat.setTotalCount(totalTestCount);
					return null;
				}
			});

			handle.applyMonitorFiles();
		}
	}

	@Override
	public synchronized boolean isComplete() {
		return completed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.server.ecl.impl.internal.execution.IExecutionProfiler
	 * #generateSkippedReport(com.xored.q7.cloud.model.Q7ArtifactRef,
	 * java.lang.String)
	 */

	@Override
	public Report generateSkippedReport(TaskDescriptor task, String cause) {
		return ReportUtil.generateFailedReport(task.getId(),
				getTestcaseName(task), cause);
	}

	@Override
	public String getTestcaseName(TaskDescriptor task) {
		return task.getTaskName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.server.ecl.impl.internal.execution.IExecutionProfiler
	 * #processReport(com.xored.q7.cloud.model.AgentInfo,
	 * org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report)
	 */

	@Override
	public void processReport(TaskDescriptor descr, Report report) {
		if (report == null) {
			report = generateSkippedReport(descr, "Agent error. No report.");
		}
		SimpleSeverity result = SimpleSeverity.ERROR;

		{
			Node reportRoot = report.getRoot();
			EObject root = reportRoot.getProperties().get(IQ7ReportConstants.ROOT);
			if (root instanceof Q7Info) {
				result = SimpleSeverity.create((Q7Info) root);
			} else {
				EclServerImplPlugin.warn("No Q7 information in report");
			}

		}

		processTaskReport(descr, report);

		AgentInfo agent = descr.getAgent();
		sendReport(getSuite(descr), report,
				agent != null ? agent.getUri() : "no_agent", descr
						.getAut().getClassifier());

		synchronized (this) {
			switch (result) {
			case OK:
				passedTestCount++;
				break;
			case ERROR:
				failedTestCount++;
				break;
			case CANCEL:
				canceledTestCount++;
				break;
			}
			executedTestCount++;
			notifyAll();
		}
	}

	private void processTaskReport(TaskDescriptor descr,
			Report report) {
		final ExecutionAgentTest test = StatsFactory.eINSTANCE
				.createExecutionAgentTest();
		test.setTestID(descr.getId());
		test.setTestName(descr.getTaskName());
		EObject root = report.getRoot().getProperties()
				.get(IQ7ReportConstants.ROOT);
		test.setStartTime(report.getRoot().getStartTime());
		test.setEndTime(report.getRoot().getEndTime());

		if (root instanceof Q7Info) {
			Q7Info info = (Q7Info) root;
			switch (SimpleSeverity.create(info)) {
			case OK:
				test.setStatus(ExecutionAgentTestStatus.PASS);
				break;
			case ERROR:
				test.setStatus(ExecutionAgentTestStatus.FAIL);
				break;
			case CANCEL:
				test.setStatus(ExecutionAgentTestStatus.SKIPPED);
				break;
			}
		}
		AgentInfo agent = descr.getAgent();
		final String agentId = agent == null ? "server" : agent.getUri();
		synchronized (this) {
			if (reportOut != null && agentId != null && !completed) {
				report.getRoot().getProperties()
						.put(IQ7ReportConstants.AGENTID,
								BoxedValues.box(agent == null ? "" : agent.getUri()));
				reportOut.write(report);
			}
		}
		// Save report into ism folder for further usage
		executionHandle.commit(new Function<Execution, Void>() {
			@Override
			public Void apply(Execution execStat) {
				if (execStat.getFirstReportTime() == 0) {
					execStat.setFirstReportTime(System.currentTimeMillis());
				}
				EList<ExecutionAgentStats> agentStats = execStat
						.getAgentStats();
				ExecutionAgentStats agentStat = null;
				for (ExecutionAgentStats executionAgentStats : agentStats) {
					if (executionAgentStats.getAgentID().equals(agentId)) {
						agentStat = executionAgentStats;
						break;
					}
				}
				if (agentStat == null) {
					agentStat = StatsFactory.eINSTANCE
							.createExecutionAgentStats();
					agentStats.add(agentStat);
					agentStat.setAgentID(agentId);
				}
				agentStat.getTests().add(test);
				execStat.setFailedCount(failedTestCount);
				execStat.setEndTime(System.currentTimeMillis());

				execStat.setTotalCount(totalTestCount);
				execStat.setCanceledCount(canceledTestCount);
				execStat.setPassedCount(passedTestCount);
				execStat.setExecutedCount(executedTestCount);
				return null;
			}
		});
		ServerPlugin.getDefault().getExecListener().updated(executionHandle);

	}

	private final ISMHandle<Execution> executionHandle;

	private final ISMHandle<SuiteStats> ismHandle;

	private final SherlockReportOutputStream reportOut;

	private ExecutionEntry handle;

	protected void sendReport(TaskSuiteDescriptor suite, Report report,
			String agentUri, String classifier) {
		IStatus result = getResult();
		if (result != null && result.matches(IStatus.CANCEL)) {
			return;
		}
		
		int pos = agentUri.indexOf("://");
		String from = pos == -1 ? agentUri : agentUri.substring(pos + 3);
		from = from.replace(':', '_');

		Envelope envelope = ModelFactory.eINSTANCE.createEnvelope();
		envelope.setTo(getSuiteID());
		envelope.setFrom(from);
		envelope.setArch(classifier);

		EObject root = report.getRoot().getProperties()
				.get(IQ7ReportConstants.ROOT);

		if (root instanceof Q7Info) {
			Q7Info info = (Q7Info) root;
			envelope.setQ7Info(EcoreUtil.copy(info));

			String message = org.eclipse.rcptt.reporting.util.ReportUtils.getFailMessage(report.getRoot());
			envelope.setMessage(String
					.format("%s %s. %d (%d) processed, %d failed.%s %s, running tasks: %d, agents: %d, cause: %s",
							envelope.getFrom(),
							SimpleSeverity.create(info).name(),
							getExecutedTestCount(),
							getTotalTestCount(),
							getFailedTestCount(),
							ReportUtils.calculateRemaining(this),
							report.getRoot().getName(),
							suite != null ? suite.getRunningAgents() : 0,
							suite != null ? suite.getAgentCount() : 0,
							((message != null && message.trim().length() > 0) ? message.trim() : ""))
					+ " arch: " + envelope.getArch());
			executionsMonitor.log(envelope.getMessage(), null);
		}

		getReporter().sendReport(envelope, this);
	}

	@Override
	public Reporter getReporter() {
		return reporter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.server.ecl.impl.internal.execution.IExecutionProfiler
	 * #getExecutionState()
	 */

	@Override
	public ExecutionState getExecutionState() {
		ExecutionState state = ServerCommandsFactory.eINSTANCE
				.createExecutionState();

		state.setTotalTestCount(getTotalTestCount());
		state.setExecutedTestCount(getExecutedTestCount());
		state.setFailedTestCount(getFailedTestCount());
		state.setSkippedTestCount(getSkippedTestCount());
		state.setExecutionTime(System.currentTimeMillis() - getStarted());

		return state;
	}

	@Override
	public int getTotalTestCount() {
		if (totalTestCount < 0) {
			throw new IllegalStateException("Uninitialized");
		}
		return totalTestCount;
	}

	@Override
	public int getFailedTestCount() {
		return failedTestCount;
	}

	@Override
	public int getExecutedTestCount() {
		return executedTestCount;
	}

	@Override
	public int getSkippedTestCount() {
		return canceledTestCount;
	}

	protected long getStarted() {
		return started;
	}

	private static TaskQueue getTaskQueue() {
		return EclServerImplPlugin.getDefault().getTaskQueue();
	}

	@Override
	public void sendClientMessage(String msg) {
		Envelope envelope = ModelFactory.eINSTANCE.createEnvelope();
		envelope.setTo(getSuiteID());
		envelope.setFrom("no_agent");
		envelope.setArch("");
		envelope.setMessage(msg);
		reporter.sendReport(envelope, ExecutionProfiler.this);
	}

	private int getTestsLeftCount() {
		Preconditions.checkNotNull(suites);
		int rv = 0;
		for (TaskSuiteDescriptor suite : suites) {
			rv += suite.getTaskCount();
		}
		return rv;
	}

	private final Function<TaskSuiteDescriptor, Collection<TaskDescriptor>> getSuiteRunningTasks = new Function<TaskSuiteDescriptor, Collection<TaskDescriptor>>() {
		@Override
		public Collection<TaskDescriptor> apply(TaskSuiteDescriptor input) {
			return input.getRunningTasks();
		}
	};

	private final Function<TaskSuiteDescriptor, Collection<TaskDescriptor>> getSuitePendingTasks = new Function<TaskSuiteDescriptor, Collection<TaskDescriptor>>() {
		@Override
		public Collection<TaskDescriptor> apply(TaskSuiteDescriptor input) {
			return input.getPendingTasks();
		}
	};

	private final Function<TaskDescriptor, String> taskToString = new Function<TaskDescriptor, String>() {
		@Override
		public String apply(TaskDescriptor input) {
			AgentInfo agent = input.getAgent();
			return input.toString() + " on: " + (agent != null ? agent.getUri() : " no agent");
		}
	};

	private Collection<TaskDescriptor> getTasks(Function<TaskSuiteDescriptor, Collection<TaskDescriptor>> visitor) {
		return newArrayList(concat(Collections2.transform(suites, visitor)));
	}

	private class ProfilerThread extends Thread {
		{
			setName(getSuiteID() + " profiler thread");
		}

		// private boolean isCanceled = false;

		@Override
		public void run() {
			// Wait for aut and update site downloads.
			BooleanSupplier isCancelled = () -> {
				sendPrepareNotifications();
				return result.get() != null;
			};
			monitor.log("Prepare tasks...", null);
			try {
				createSuites();
				Preconditions.checkNotNull(suites);
				if (totalTestCount < 0)
					throw new IllegalStateException();
				monitor.log("Schedule per aut suites: " + suites.size(), null);
				ServerPlugin.getDefault().getExecListener().started(executionHandle);

				Map<String, Q7ArtifactRef> contexts = ModelUtil
						.dependenciesMap(suiteDir.getTestSuite());

				IProgressMonitor pm = new NullProgressMonitor() {

					@Override
					public boolean isCanceled() {
						return result.get() != null;
					}

				};

				if (!handle.waitForTasks(isCancelled, Duration.ofMinutes(30))) {
					throw new TimeoutException("Failed to prepare execution " + handle.getSuiteId());
				}

				// Register suites in queue
				// They have no tasks that are ready to execute yet
				// All tasks are UNINITIALIZED
				getTaskQueue().schedule(suites.toArray(new TaskSuiteDescriptor[suites.size()]));

				// Verify and give out tasks to agents
				for (TaskSuiteDescriptor descr : suites) {
					if (Thread.interrupted())
						throw new InterruptedException();
					descr.initialize(contexts, pm);
					IStatus tempRes = result.get();
					if (tempRes != null)
						throw new CoreException(tempRes); 
				}

				// String suiteId = suiteDir.testSuite.getId();
				long timeout = options.getValue(Options.KEY_EXEC_TIMEOUT,
						Options.DEFAULT_EXEC_TIMEOUT);
				monitor.log("waiting for completion, timeout:" + timeout, null);
				long startTime = System.currentTimeMillis();
				int emptyFor = 0;
				int previousCount = 0;
				long lastExecutionTime = System.currentTimeMillis();
				while (true) {
					if (result.get() != null)
						break;
					synchronized (ExecutionProfiler.this) {
						ExecutionProfiler.this.wait(1000);
						assert executedTestCount <= totalTestCount;
						if (executedTestCount == totalTestCount) {
							// No tasks left for execution
							break;
						}
						if (executedTestCount != previousCount) {
							lastExecutionTime = System.currentTimeMillis();
							previousCount = executedTestCount;
						}
					}
					if (System.currentTimeMillis() > lastExecutionTime + 60000) {
						lastExecutionTime = System.currentTimeMillis();
						dumpRunning();
					}
					if (getTestsLeftCount() <= 0) {
						emptyFor++;
						if (emptyFor > 100)
							throw new IllegalStateException(
									"No tests left, but not all tests are reported. Executed: "
											+ executedTestCount + ", total: "
											+ totalTestCount);
					}
					if (!timeout(startTime, timeout))
						throw new RuntimeException("Timeout during waiting for execution complete.");
				}
				monitor.log("stop waiting for results", null);
				result.compareAndSet(null, Status.OK_STATUS);
			} catch (Throwable e) {
				result.compareAndSet(null, RcpttPlugin.createStatus(e));
				if (e instanceof InterruptedException) {
					errorMonitor
							.log("Client timeout happen. Terminating suite execution.",
									e);
				} else {
					errorMonitor.log("Exception during wait for completion:"
							+ e.getMessage(), e);
				}
			} finally {
				IStatus tempRes = result.get();
				if (tempRes == null)
					throw new NullPointerException();
				finish(tempRes);
				waitReporterAndCleanup();
			}
		}

		private void waitReporterAndCleanup() {
			long start = System.currentTimeMillis();

			// While is not terminated, wait for client to do a termination.
			// No more 5 minutes to wait for client to get all report files.
			try {
				while (!clientComplete && reporter.getQueueLength() > 0
						&& timeout(start, 5 * 60)) {
					IStatus result = ExecutionProfiler.this.result.get();
					if (result != null && result.matches(IStatus.CANCEL))
						return;
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
			} finally {
				ExecutionRegistry.getInstance().removeSuiteHandle(getSuiteID());
				ServerPlugin.getDefault().getExecListener()
						.completed(executionHandle);
				handle.setProfiler(null);
			}
		}
	}

	private boolean timeout(long starttime, long time) {
		return (System.currentTimeMillis() - starttime) < time * 1000;
	}

	private void dumpRunning() {
		Collection<TaskDescriptor> data = getTasks(getSuiteRunningTasks);
		Joiner j = Joiner.on("; ");
		if (data.size() > 0) {
			monitor.log("Running tasks: " + j.join(transform(data, taskToString)));
			return;
		}
		data = getTasks(getSuitePendingTasks);
		if (data.size() > 0) {
			monitor.log("Pending tasks: " + j.join(transform(data, taskToString)));
			return;
		}
		monitor.log("No pending tasks");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.server.ecl.impl.internal.execution.IExecutionProfiler
	 * #getMonitor()
	 */

	@Override
	public Listener getMonitor() {
		return executionMonitor;
	}

	@Override
	public synchronized void cancel(IStatus status) {
		sendClientMessage("Cancelling suite " + getSuiteID() + ":  " + status.getMessage());
		errorMonitor.log("Cancelled", new CoreException(status));
		result.compareAndSet(null, status);
		notifyAll();
	}

	@Override
	public void reportProblem(AgentInfo agent, String cause) {
		errorMonitor.log("error from agent: " + agent.getUri() + " msg: "
				+ cause, null);
	}

	@Override
	public synchronized void markComplete() {
		clientComplete = true;
	}

	private Map<String, Integer> autStartLogIndex = new HashMap<String, Integer>();
	private Set<String> autNotStarted = new HashSet<String>();

	private IQ7Monitor executionsMonitor;

	private final File reportFile;

	Function<TaskSuiteDescriptor, String> getSuiteId = new Function<TaskSuiteDescriptor, String>() {

		@Override
		public String apply(TaskSuiteDescriptor input) {
			return input.getSuiteId();
		}
	};

	private TaskSuiteDescriptor getSuite(TaskDescriptor task) {
		return suites.stream()
		.filter(suite -> Objects.equals(suite.getSuiteId(), task.getSuiteId()))
		.filter(suite -> Objects.equals(suite.getClassifier(), task.getAut().getClassifier()))
		.reduce((a, b) -> {
            throw new IllegalStateException("Multiple elements: " + a + ", " + b);
        })
		.get();
	}

	@Override
	public synchronized void reportAUTLogs(AgentInfo agent, Map<String, String> files,
			AutStartupStatus status) {
		synchronized (this) {
			String agentId = FileUtil.getID(AgentRegistry.getAgentID(agent));
			Integer index = autStartLogIndex.get(agentId);
			if (index == null) {
				index = Integer.valueOf(0);
			} else {
				index = Integer.valueOf(index.intValue() + 1);
			}
			autStartLogIndex.put(agentId, index);

			if (AutStartupStatus.SHUTDOWN_ON_TIMEOUT.equals(status)) {
				saveAutFiles(files, agentId, index, "timeout_");
			} else if (AutStartupStatus.SHUTDOWN_ON_OPTION.equals(status)) {
				saveAutFiles(files, agentId, index, "shutdown_");
			} else if (AutStartupStatus.FAILED_TO_PING.equals(status)) {
				saveAutFiles(files, agentId, index, "ping_");
			}
			// Else this is startup, not a timeout issue
			else if (AutStartupStatus.STARTED.equals(status)) {
				sendClientMessage("AUT is started on: " + agentId);
				saveAutFiles(files, agentId, index, "");
				autNotStarted.clear();
			} else {
				saveAutFiles(files, agentId, index, "failed_");
				autNotStarted.add(agentId);

				int totalAgents = 0;
				for (TaskSuiteDescriptor d : suites) {
					totalAgents += d.getAgentCount();
				}
				int max = CANCEL_AUT_FAILURES_LIMIT;
				if (totalAgents > max) {
					max = totalAgents;
				}
				if (autNotStarted.size() >= max) {
					String message = "Cancelling execution, since AUT could not be started: "
							+ autNotStarted.size()
							+ " times one by one.";
					reportProblem(agent,message);
					cancel(RcpttPlugin.createStatus(message));
					notifyAll();
				}
			}
		}
	}

	private void saveAutFiles(Map<String, String> files, String agentId,
			Integer index, String prefix) {
		if (files != null) {
			for (Map.Entry<String, String> file : files.entrySet()) {
				writeFile(
						handle.getArtifactName(prefix + agentId + "_"
								+ index.intValue() + "_" + file.getKey()),
						file.getValue());
			}
		}
	}

	@Override
	public void reportAgentLog(AgentInfo agent, ProcessStatus status,
			AgentLogEntryType type) {
		Preconditions.checkNotNull(status);
		IStatus iStatus = ProcessStatusConverter.toIStatus(status);
		synchronized (this) {
			String agentId = FileUtil.getID(AgentRegistry.getAgentID(agent));
			StatusListener monitor = handle.getMonitor(agentId + ".log");
			switch (type) {
			case BOTH:
				monitor.log(iStatus);
				sendClientMessage(AgentRegistry.getAgentID(agent) + ": " + status.getMessage());
				break;
			case CLIENT:
				sendClientMessage(AgentRegistry.getAgentID(agent) + ": " + status.getMessage());
				break;
			case LOGONLY:
				monitor.log(iStatus);
				break;
			}
		}

	}

	private void writeFile(File file, String content) {
		try {
			Files.write(content, file, Charset.forName("utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendPrepareNotifications() {
		List<String> list = handle.drainMessages();
		if (list != null) {
			for (String msg : list) {
				sendClientMessage(msg);
			}
		}
	}

	@Override
	public void onCancel(TaskSuiteDescriptor suite, Throwable reason, int tasksLeft) {
		if (tasksLeft > 0)
			cancel(RcpttPlugin.createStatus(reason));
	}

	@Override
	public void onClientMessage(String string) {
		sendClientMessage(string);
	}
	
	private IStatus getResult() {
		return result.get();
	}
}
