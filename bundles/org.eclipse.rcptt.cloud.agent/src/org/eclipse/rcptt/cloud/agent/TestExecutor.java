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
package org.eclipse.rcptt.cloud.agent;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.singletonList;
import static org.eclipse.core.runtime.Status.error;
import static org.eclipse.debug.core.DebugPlugin.ATTR_CAPTURE_OUTPUT;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_JRE_CONTAINER_PATH;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS;
import static org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS;
import static org.eclipse.pde.internal.launching.IPDEConstants.APPEND_ARGS_EXPLICITLY;
import static org.eclipse.rcptt.internal.launching.ecl.ExecAdvancedInfoUtil.askForAdvancedInfo;
import static org.eclipse.rcptt.launching.IQ7Launch.ATTR_HEADLESS_LAUNCH;
import static org.eclipse.rcptt.launching.ext.Q7LaunchingUtil.createQ7LaunchConfiguration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IFlushableStreamMonitor;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.environments.IExecutionEnvironment;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.launching.IPDEConstants;
import org.eclipse.pde.internal.launching.launcher.LaunchConfigurationHelper;
import org.eclipse.pde.launching.IPDELauncherConstants;
import org.eclipse.rcptt.cloud.agent.IAgentMonitor.LogType;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.core.model.IQ7NamedElement;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.core.workspace.IWorkspaceFinder;
import org.eclipse.rcptt.internal.core.model.Q7InternalTestCase;
import org.eclipse.rcptt.internal.launching.ExecutionSession;
import org.eclipse.rcptt.internal.launching.ExecutionStatus;
import org.eclipse.rcptt.internal.launching.Q7TestLaunch;
import org.eclipse.rcptt.internal.launching.aut.BaseAutLaunch;
import org.eclipse.rcptt.internal.launching.ext.Q7TargetPlatformManager;
import org.eclipse.rcptt.launching.Aut;
import org.eclipse.rcptt.launching.AutLaunch;
import org.eclipse.rcptt.launching.AutLaunchState;
import org.eclipse.rcptt.launching.AutManager;
import org.eclipse.rcptt.launching.IExecutable;
import org.eclipse.rcptt.launching.Q7Launcher;
import org.eclipse.rcptt.launching.ext.JvmTargetCompatibility;
import org.eclipse.rcptt.launching.ext.Q7LaunchDelegateUtils;
import org.eclipse.rcptt.launching.ext.Q7LaunchingUtil;
import org.eclipse.rcptt.launching.ext.VmInstallMetaData;
import org.eclipse.rcptt.launching.target.ITargetPlatformHelper;
import org.eclipse.rcptt.launching.utils.TestSuiteUtils;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.Q7LoggingManager;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.io.Closer;

@SuppressWarnings("restriction")
public class TestExecutor implements ITestExecutor.Closeable {
	private final AutInfo aut;
	private AutRegistry auts = AgentPlugin.getDefault().getAutRegistry();
	private AutFileUtil autFiles = AgentPlugin.getDefault().getAutFiles();
	private IQ7Monitor logMonitor;
	private static final ILaunchManager LAUNCH_MANAGER = DebugPlugin.getDefault().getLaunchManager();


	public TestExecutor(AutInfo aut) throws CoreException {
		this.aut = EcoreUtil.copy(aut);
		this.logMonitor = Q7LoggingManager.get("test-executor");
		logMonitor.log("############## text-executor-session:" + aut.getId()
				+ " uri:" + aut.getUri(), null);
	}

	private ITargetPlatformHelper helper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.agent.ITestExecutor#deployAut(org.eclipse.core.runtime
	 * .IProgressMonitor)
	 */
	@Override
	public void deployAut(IProgressMonitor monitor) throws CoreException {
		if (helper == null) {
			long start = System.currentTimeMillis();

			logBeginAutDeploy(monitor);

			helper = auts.deployAut(aut, monitor);
			long end = System.currentTimeMillis();
			if (helper != null) {
				logAutIsDeployed(monitor, start, end);
			} else {
				logAutIsFailedToBeDeployed(monitor);
			}
		}
	}

	private void logAutIsFailedToBeDeployed(IProgressMonitor monitor) {
		log(monitor, "Failed to deploy AUT:" + aut.getUri(), LogType.Both);
	}

	private void logAutIsDeployed(IProgressMonitor monitor, long start, long end) {
		log(monitor,
				"AUT is deployed to: " + helper.getTargetPlatformProfilePath()
						+ " time: " + Long.toString(end - start),
				IAgentMonitor.LogType.Both);
	}

	private void log(IProgressMonitor monitor, String msg,
			IAgentMonitor.LogType type) {
		if (monitor instanceof IAgentMonitor) {
			((IAgentMonitor) monitor).logAgentMessage(msg, type);
		}
		logMonitor.log(msg, null);
	}

	private void logBeginAutDeploy(IProgressMonitor monitor) {
		log(monitor, "Begin AUT Deployment", LogType.Both);
	}

	private PrintStream sutOut;
	private PrintStream sutErr;

	private AutLaunch launch;

	private int timeout = 10 * 60;

	private int startCount = 0;

	private ILaunchListener listener = new ILaunchListener() {
		@Override
		public void launchRemoved(ILaunch launch) {
		}

		@Override
		public void launchChanged(ILaunch launch) {
			logProcesses(launch.getProcesses());
		}

		@Override
		public void launchAdded(ILaunch launch) {
			logProcesses(launch.getProcesses());
		}
	};

	private void addLaunchListener() {
		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(listener);
	}

	private void removeLaunchListener() {
		DebugPlugin.getDefault().getLaunchManager()
				.removeLaunchListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.agent.ITestExecutor#startAut(org.eclipse.core.runtime
	 * .IProgressMonitor)
	 */

	@Override
	public void startAut(IProgressMonitor monitor) throws CoreException {
		if (isStarted()) {
			try {
				ping();
				return;
			} catch (CoreException e) {
				shutdown();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new CoreException(Status.CANCEL_STATUS);
			}
		}
		log(monitor, "Launch AUT: " + aut.getUri(), LogType.Both);

		addLaunchListener();
		startCount++;

		workspaceFile = autFiles.getWorkspace(aut, startCount, true);

		log(monitor, "aut workspace path is: " + workspaceFile.toString(),
				LogType.Both);

		savedConfig = null;
		try {
			outStreamFile = autFiles.getOutStreamFile(aut, startCount);
			sutOut = autFiles.getFileStream(aut, outStreamFile);
			errStreamFile = autFiles.getErrStreamFile(aut, startCount);
			sutErr = autFiles.getFileStream(aut, errStreamFile);

			String sutArgs = Q7LaunchDelegateUtils.getAUTArgs(aut.getArgs());

			log(monitor, "AUT args: " + sutArgs, LogType.Both);

			ILaunchConfigurationWorkingCopy config = createQ7LaunchConfiguration(
					helper, sutArgs, aut.getId());

			config.setAttribute(IPDELauncherConstants.DOCLEAR, false);
			config.setAttribute(IPDELauncherConstants.ASKCLEAR, true);
			config.setAttribute(IPDEConstants.DOCLEARLOG, false);
			config.setAttribute(IPDELauncherConstants.LOCATION,
					workspaceFile.getAbsolutePath());
			// Delete launch configuration area before aut launch
			File area = LaunchConfigurationHelper.getConfigurationArea(config);
			if (area != null && area.exists()) {
				FileUtil.deleteFile(area);
			}

			config.setAttribute(ATTR_HEADLESS_LAUNCH, true);
			config.setAttribute(ATTR_CAPTURE_OUTPUT, true);
			config.setAttribute(ATTR_JRE_CONTAINER_PATH, findVm().formatVmContainerPath());
			String vmargs = Q7LaunchDelegateUtils.getJoinedVMArgs(helper,
					aut.getVmArgs());
			config.setAttribute(ATTR_VM_ARGUMENTS, vmargs);
			if (monitor instanceof IAgentMonitor) {
				((IAgentMonitor) monitor).logAgentMessage("AUT vmargs: "
						+ vmargs, IAgentMonitor.LogType.Both);
			}
			config.setAttribute(ATTR_PROGRAM_ARGUMENTS, sutArgs);
			config.setAttribute(APPEND_ARGS_EXPLICITLY, true);

			logMonitor.log("Save Launch Configuration", null);
			savedConfig = config.doSave();
			Aut aut = AutManager.INSTANCE.getByLaunch(savedConfig);

			Q7TargetPlatformManager.setHelper(config, helper);
			long start = System.currentTimeMillis();
			launch = aut.launch(new NullProgressMonitor());
			long end = System.currentTimeMillis();

			log(monitor, "AUT is launched time: " + Long.toString(end - start),
					LogType.Both);
		} catch (RuntimeException ex) {
			log(monitor, "exception during launch: " + ex.getMessage(),
					LogType.Both);
			logMonitor.log("exception during launching:" + ex.getMessage(), ex);
			String msg = null;
			try {
				msg = "----->>>>>>>Last 5kb of error log:\n"
						+ new String(FileUtil.getContents(errStreamFile,
								-1 * 5 * 1024)) + "\n------<<<<<<";
			} catch (IOException e) {
				msg = "";
			}
			try {
				msg += "\n----->>>>>>>Last 5kb of workspace log: "
						+ new String(FileUtil.getContents(outStreamFile,
								-1 * 5 * 1024)) + "\n------<<<<<<";
			} catch (IOException e) {
			}
			throw AgentPlugin.createException("Can't launch AUT. " + msg, ex);
		}
		if (monitor.isCanceled()) {
			logMonitor.log("canceled aut startup", null);
			shutdown();
			throw AgentPlugin.createException("Can't launch AUT");
		}

		BaseAutLaunch il = (BaseAutLaunch) launch;
		log(monitor, "aut started: " + aut.getId() + " host: " + il.getHost()
				+ " port: " + il.getEclPort(), LogType.Both);
	}

	@Override
	public Map<String, String> obtainConfigurationFiles(IProgressMonitor monitor) {
		Map<String, String> filesToFill = new HashMap<String, String>();
		putStreamIntoMap(filesToFill, "console.log",
				getOutStreamFile(1024 * 1024));

		putStreamIntoMap(filesToFill, "error.log",
				getErrStreamFile(1024 * 1024));
		putStreamIntoMap(filesToFill, "workspace.log",
				getWorkspaceLog(1024 * 1024));

		if (savedConfig == null) {
			return filesToFill;
		}
		File file = LaunchConfigurationHelper.getConfigurationArea(savedConfig);
		if (file == null) {
			return filesToFill;
		}
		putFileIntoMap(filesToFill, new File(file,
				"org.eclipse.equinox.simpleconfigurator"), "bundles.info", monitor);
		putFileIntoMap(filesToFill, file, "config.ini", monitor);

		// Obtain other created log files.
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile() && f.getName().endsWith(".log")) {
				putFileIntoMap(filesToFill, file, f.getName(), monitor);
			}
		}
		return filesToFill;
	}

	private void putStreamIntoMap(Map<String, String> filesToFill, String name,
			String content) {
		if (content != null && content.trim().length() > 0) {
			filesToFill.put(name, content);
		}
	}

	private void putFileIntoMap(Map<String, String> filesToFill,
			File parentFile, String fName, IProgressMonitor monitor) {
		File file = new File(parentFile, fName);
		if (file.exists()) {
			try (
				BufferedInputStream fileStream = new BufferedInputStream(
						new FileInputStream(file))) {
				byte[] streamContent = FileUtil.getStreamContent(fileStream);
				if (streamContent != null) {
					String content = new String(streamContent, "utf-8");
					filesToFill.put(fName, content);
				}
			} catch (IOException e) {
				String message = "Failed to read file " + file;
				AgentPlugin.logErr(message, e);
				log(monitor, message, IAgentMonitor.LogType.Both);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xored.q7.cloud.agent.ITestExecutor#isStarted()
	 */

	@Override
	public boolean isStarted() {
		if (helper == null) {
			return false;
		}
		return launch != null
				&& launch.getState().equals(AutLaunchState.ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xored.q7.cloud.agent.ITestExecutor#clearWorkspaces()
	 */

	@Override
	public void clearWorkspaces() {
		autFiles.clearWorkspaces(aut);
	}

	protected void clearPDEFiles() {
		File file = PDECore.getDefault().getStateLocation().toFile();
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			if (!f.getName().startsWith(".")
					|| f.getName().equalsIgnoreCase(".local_targets")
					|| f.getName().equalsIgnoreCase(".install_folders")) {
				FileUtil.deleteFile(f);
			}
		}
	}

	public static void cleanupPDE() {
		// delete all PDE core files
		File file = PDECore.getDefault().getStateLocation().toFile();
		FileUtil.deleteFile(file);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xored.q7.cloud.agent.ITestExecutor#clearConfigurations()
	 */

	@Override
	public void clearConfigurations() {
		clearLaunchConfigurations(Q7LaunchingUtil.EXTERNAL_LAUNCH_TYPE);
	}

	private void clearLaunchConfigurations(String id) {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager.getLaunchConfigurationType(id);
		if (type == null) {
			return;
		}

		try {
			for (ILaunchConfiguration config : manager
					.getLaunchConfigurations(type)) {
				config.delete();
			}
		} catch (CoreException e) {
			AgentPlugin.logWarn("Failed to remove launch configuration", e);
		}
	}

	private static final int WAIT_TIME = 10;

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// do nothing
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.agent.ITestExecutor#runTests(com.xored.q7.cloud.model
	 * .Q7ArtifactRef, org.eclipse.core.runtime.IProgressMonitor)
	 */

	@Override
	public Report runTest(int agentID, ITestStore dir, IProgressMonitor monitor) throws CoreException, TimeoutException {
		long start = System.currentTimeMillis();
		if (monitor instanceof IAgentMonitor) {
			((IAgentMonitor) monitor).logAgentMessage("Begin test",
					IAgentMonitor.LogType.LogOnly);
		}
		IWorkspaceFinder cxFinder = new TestDirContextFinder(dir);

		TestSuite testSuite = dir.getTestSuite();
		try {
			List<Q7ArtifactRef> scenarioList = ModelUtil.scenarioList(testSuite);
			assert scenarioList.size() == 1 : "Scenario size should be 1, but was " + scenarioList.size();
			if (monitor.isCanceled()) {
				throw new CoreException(Status.CANCEL_STATUS);
			}
			return runScenario(dir, scenarioList.get(0), cxFinder, monitor);
		} finally {
			long end = System.currentTimeMillis();
			if (monitor instanceof IAgentMonitor) {
				((IAgentMonitor) monitor).logAgentMessage(
						"Test complete: " + testSuite.getId() + " time: "
								+ Long.toString(end - start),
						IAgentMonitor.LogType.LogOnly);
			}
		}
	}

	protected Report runScenario(ITestStore dir, Q7ArtifactRef scenarioRef,
			IWorkspaceFinder cxFinder, IProgressMonitor monitor)
			throws CoreException, TimeoutException {
		Scenario scenario;
		try {
			scenario = extractScenario(dir, scenarioRef);
		} catch (Exception e) {
			String scenarioId = scenarioRef.getId();
			String msg = String.format(
					"Failed to load Q7 test case with id: %s. Cause: %s",
					scenarioId, e.getMessage());
			IStatus status = AgentPlugin.createStatus(msg, IStatus.WARNING, e);
			AgentPlugin.log(status);

			throw new CoreException(status);
		}

		if (monitor.isCanceled()) {
			throw new CoreException(Status.CANCEL_STATUS);
		}

		final Q7Launcher launcher = Q7Launcher.getInstance();
		String scenarioName = scenario.getName();

		ExecutionSession execSession = (ExecutionSession) launcher.execute(
				new IQ7NamedElement[] { getIQ7NamedElement(scenario) },
				launch.getAut(), scenarioName, cxFinder);
		Q7TestLaunch testLaunch = execSession.getLaunch();

		try {
			// Executables size must equals to 1 because launcher execute with
			// one scenario.
			IExecutable exec = execSession.getExecutables()[0];
			long startTime = currentTimeMillis();

			while (execSession.isRunning()) {
				final long elapsedSeconds = (currentTimeMillis() - startTime) / 1000;
				if (monitor.isCanceled()) {
					execSession.stop(askForAdvancedInfo(launch, error(format("Execution is cancelled after %d seconds", elapsedSeconds))));
					break;
				}
				if (execTimedOut(startTime)) {
					execSession.stop(askForAdvancedInfo(launch, error(format("Execution has timed out after %d seconds", elapsedSeconds))));
					break;
				}
				sleep(WAIT_TIME);
			}

			String state = "";
			boolean isFailed = false;
			boolean testTimeout = false;
			if (execTimedOut(startTime)) {
				testTimeout = true;
				isFailed = true;
				state = "Timeout during test execution";
			}
			if (!testTimeout) {
				IExecutable[] executables = execSession.getExecutables();
				for (IExecutable executable : executables) {
					IStatus resultStatus = executable.getResultStatus();
					if (!executable.getResultStatus().isOK()) {
						state = "";
						if (resultStatus != null) {
							if (resultStatus.getSeverity() == IStatus.CANCEL) {
								testTimeout = true;
							}
							state = resultStatus.getMessage();
							if (resultStatus instanceof ExecutionStatus
									&& ((ExecutionStatus) resultStatus)
											.getCause() != null) {
								state += "\nCaused by:\n\t"
										+ ((ExecutionStatus) resultStatus)
												.getCause().getMessage();
							}
						}
						isFailed = true;
					}
				}
			}
			if (monitor instanceof IAgentMonitor) {
				((IAgentMonitor) monitor).logAgentMessage(" Test execution is "
						+ (isFailed ? "Failed " : "Passed ") + " " + state
						+ " is timed out: " + testTimeout,
						IAgentMonitor.LogType.LogOnly);
			}
			String message = String.format(
					"%s has timed out, AUT will be shutdown", scenarioName);
			if (testTimeout) {
				// Restart AUT since timeout is detected, and return appropriate
				// report
				IStatus status = AgentPlugin.createStatus(message,
						IStatus.ERROR, null);
				AgentPlugin.log(status);

				shutdown();
			}

			if (!testTimeout && execTimedOut(startTime)) {
				testTimeout = true;
				IStatus status = AgentPlugin.createStatus(message,
						IStatus.INFO, null);
				AgentPlugin.log(status);
				shutdown();
			}
			if (isFailed && restartAUTOnFailures) {
				IStatus status = AgentPlugin
						.createStatus(
								String.format(
										"%s is failed and restartAUTOnFailure=true test option is specified, AUT will be restarted",
										scenarioName), IStatus.INFO, null);
				AgentPlugin.log(status);
				shutdown();
			}

			return createScenarioReport(exec, monitor);
		} finally {
			DebugPlugin.getDefault().getLaunchManager()
					.removeLaunch(testLaunch);
			launcher.removeExecutionSession(execSession);
		}
	}

	private boolean execTimedOut(long startTime) {
		// add 10 seconds overhead
		return (System.currentTimeMillis() - startTime) > ((timeout + 10) * 1000);
	}

	private Report createScenarioReport(IExecutable exec, IProgressMonitor monitor) {
		Report report = exec.getResultReport();
		if (report == null) {
			IStatus resultStatus = exec.getResultStatus();
			String failReason = "Unknown reason";
			if (resultStatus != null) {
				failReason = resultStatus.toString();
			}
			if (monitor instanceof IAgentMonitor) {
				((IAgentMonitor) monitor).logAgentMessage(
						"Generating fail report: " + failReason,
						IAgentMonitor.LogType.LogOnly);
			}
			report = TestSuiteUtils.generateFailedReport(exec.getActualElement(), failReason);
		}
		return report;
	}

	private Scenario extractScenario(ITestStore dir, Q7ArtifactRef ref)
			throws CoreException {
		try {
			return (Scenario) dir.getResource(ref).getContent();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xored.q7.cloud.agent.ITestExecutor#shutdown()
	 */

	@SuppressWarnings("resource")
	@Override
	public void shutdown() {
		try (Closer c = Closer.create()) {
			synchronized (this) {
				if (sutErr != null) {
					c.register(sutErr);
					sutErr = null;
				}
				if (sutOut != null) {
					c.register(sutOut);
					sutOut = null;
				}
				if (launch != null) {
					ILaunch launch2 = launch.getLaunch();
					c.register(() -> LAUNCH_MANAGER.removeLaunch(launch2));
					c.register(launch::shutdown);
					launch = null;
				}
				errStreamListener = null;
				outStreamListener = null;
				removeLaunchListener();
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public synchronized void close() {
		shutdown();
		if (helper != null) {
			helper.delete();
			helper = null;
		}
		clearConfigurations();
	}

	private void logProcesses(IProcess[] processes) {
		IStreamListener err = getErrStreamListener();
		IStreamListener out = getOutStreamListener();
		for (IProcess process : processes) {
			IStreamsProxy proxy = process.getStreamsProxy();
			if (proxy == null) {
				continue;
			}
			IStreamMonitor outMonitor = proxy.getOutputStreamMonitor();
			IStreamMonitor errMonitor = proxy.getErrorStreamMonitor();
			out.streamAppended(outMonitor.getContents(), outMonitor);
			err.streamAppended(errMonitor.getContents(), errMonitor);
			outMonitor.removeListener(out);
			outMonitor.addListener(out);
			errMonitor.removeListener(err);
			errMonitor.addListener(err);
		}
	}

	private PrintStreamListener errStreamListener;

	private PrintStreamListener getErrStreamListener() {
		if (errStreamListener == null) {
			errStreamListener = new PrintStreamListener(sutErr);
		}
		return errStreamListener;
	}

	private PrintStreamListener outStreamListener;
	private File outStreamFile;
	private File errStreamFile;
	private File workspaceFile;
	private ILaunchConfiguration savedConfig;
	private boolean restartAUTOnFailures;

	private PrintStreamListener getOutStreamListener() {
		if (outStreamListener == null) {
			outStreamListener = new PrintStreamListener(sutOut);
		}
		return outStreamListener;
	}

	private static class PrintStreamListener implements IStreamListener {
		private PrintStream stream;

		public PrintStreamListener(PrintStream stream) {
			this.stream = stream;
		}

		@Override
		public void streamAppended(String text, IStreamMonitor monitor) {
			if (stream == null) {
				return;
			}
			stream.append(text);
			if (monitor instanceof IFlushableStreamMonitor) {
				((IFlushableStreamMonitor) monitor).flushContents();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xored.q7.cloud.agent.ITestExecutor#setTestOptions(com.xored.q7.cloud
	 * .model.TestOptions)
	 */

	@Override
	public void setTestOptions(TestOptions options) {
		AgentOptionsHandler agentOptions = new AgentOptionsHandler();
		agentOptions.applyOptions(options.getValues());
		timeout = Q7Launcher.getLaunchTimeout() + 30;
		restartAUTOnFailures = agentOptions.isRestartAUTOnFailures();
	}

	private static IQ7NamedElement getIQ7NamedElement(Scenario scenario) {
		return new Q7InternalTestCase(null, scenario.getName(), scenario);
	}

	@Override
	public void prepare(ITestStore dir) {
	}

	@Override
	public String getOutStreamFile(long limit) {
		if (sutOut != null) {
			sutOut.flush();
		}
		if (outStreamFile.exists()) {
			try {
				return new String(FileUtil.getContents(outStreamFile, -1
						* limit));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getErrStreamFile(long limit) {
		if (sutErr != null) {
			sutErr.flush();
		}
		try {
			return new String(FileUtil.getContents(errStreamFile, -1 * limit));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getWorkspaceLog(long limit) {
		File logFile = new File(new File(workspaceFile, ".metadata"), ".log");
		if (logFile.exists()) {
			try {
				return new String(FileUtil.getContents(logFile, -1 * limit));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void cleanBundlePool() {
		Q7LaunchingUtil.cleanBundlePool(new NullProgressMonitor(), true, 60);
	}

	@Override
	public void ping() throws CoreException, InterruptedException {
		if (launch != null) {
			launch.ping();
		}
	}

	private VmInstallMetaData findVm() throws CoreException {
		String ee = aut.getExecutionEnvironment();
		var compatibility = new JvmTargetCompatibility(helper); 
		if (ee != null) {
			IExecutionEnvironment environment = JavaRuntime.getExecutionEnvironmentsManager().getEnvironment(ee);
			if (environment == null) {
				throw new CoreException(Status.error("Can't find execution environment " + ee));
			}
			return compatibility.selectCompatibile(suitableVm(environment).toList());
		}

		Optional<VmInstallMetaData> result = addJvmFromIniFile();
		if (result.isPresent()) {
			return compatibility.selectCompatibile(singletonList(result.get()));
		}
		
		result = compatibility.findVM().findFirst();
		if (result.isPresent()) {
			return result.get();
		}
		throw new CoreException(Status.error("Can't find compatible JVM:\n" + compatibility.explainJvmRequirements()));
	}

	private Stream<VmInstallMetaData> suitableVm(IExecutionEnvironment e) {
		IVMInstall result = e.getDefaultVM();
		if (result != null) {
			return VmInstallMetaData.adapt(result);
		}
		return Arrays.stream(e.getCompatibleVMs()).filter(e::isStrictlyCompatible).flatMap(VmInstallMetaData::adapt);
	}
	
	
	private Optional<VmInstallMetaData> addJvmFromIniFile() throws CoreException {
		Path vmFromIni = helper.getJavaHome().orElse(null);
		if (vmFromIni == null) {
			return Optional.empty();
		}
		System.out.println("Trying to use VM from application's ini file: "
				+ vmFromIni);
		return VmInstallMetaData.register(vmFromIni).findFirst();
	}

}
