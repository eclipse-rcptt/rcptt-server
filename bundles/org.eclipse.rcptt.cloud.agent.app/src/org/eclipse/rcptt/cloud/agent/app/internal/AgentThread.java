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
package org.eclipse.rcptt.cloud.agent.app.internal;

import static org.eclipse.core.runtime.Status.error;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.ecl.client.tcp.EclTcpSocketStatus;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;
import org.eclipse.rcptt.ecl.server.tcp.EclTcpServerManager;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.internal.launching.aut.BaseAutLaunch;
import org.eclipse.rcptt.internal.launching.aut.BaseAutManager;
import org.eclipse.rcptt.launching.injection.Entry;
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;
import org.eclipse.rcptt.launching.injection.UpdateSite;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.Q7LoggingManager;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.util.Base64;

import org.eclipse.rcptt.cloud.agent.AgentPlugin;
import org.eclipse.rcptt.cloud.agent.ITestExecutor;
import org.eclipse.rcptt.cloud.agent.autManager.impl.http.ServerUriAutProvider;
import org.eclipse.rcptt.cloud.common.AutUtil;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.common.TestSuiteRegistry;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentPing;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask;
import org.eclipse.rcptt.cloud.server.serverCommands.GetTask;
import org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved;
import org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgent;
import org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;
import org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus;
import org.eclipse.rcptt.cloud.util.EObjectKey;
import org.eclipse.rcptt.cloud.util.RemoteEclClient.ExecResult;

public class AgentThread implements Runnable {

	private static final int AGENT_ECL_TIMEOUT = 2 * 60000; // 2 minutes
	private static final int GET_TASK_TIMEOUT = AGENT_ECL_TIMEOUT;
	private static final int PING_TIMEOUT = 10000;
	AgentApplication agentApplication;
	

	// Currently executed task
	static class AgentTask {
		Task task;
		ITestStore dir; // Suite directory for current task
	}

	LinkedBlockingQueue<AgentTask> tasks = new LinkedBlockingQueue<AgentThread.AgentTask>();
	AtomicInteger tasksCount = new AtomicInteger(0);

	private AgentInfo agentInfo;
	private TestSuiteRegistry registry;
	int id;
	private int agentPort = 0;
	private ITestExecutor.Closeable started = null;

	EObjectKey<AutInfo> startedAutInfo = null;

	IQ7Monitor agentMonitor;
	volatile boolean stopped = false;
	String lastSuite;
	private long executeIndex = 0;
	private ExecutionThread executionThread = null;

	private List<Runnable> runnables = new ArrayList<Runnable>();
	private AgentMonitor monitor;

	public AgentThread(AgentApplication agentApplication, int id, int port) {
		this.agentApplication = agentApplication;
		agentMonitor = Q7LoggingManager.newMonitor("agents", "agent-" + id
				+ "-");
		this.id = id;
		this.agentPort = port;
		agentMonitor.log("############ start agent:" + getAgentID(), null);
		try {
			EclTcpServerManager.Instance.startServer(agentPort, false, false);
		} catch (IOException e) {
			err("Failed to start ecl serverf at port:" + agentPort, e);
		}
		executionThread = new ExecutionThread(this, "agent-" + id + " port:"
				+ port + " execution thread");
	}

	public void stop() {
		agentMonitor.close();
		stopped = true;
	}

	void err(String message, Throwable e) {
		agentMonitor.log("error: " + message, e);

		// Try to send error into server
		reportError(message, e);
	}

	@Override
	public void run() {
		try {
			executionThread.start();
			while (agentApplication.isAlive() && !stopped) {
				try {
					if (getAgentInfo() == null) {
						register();
					} else {
						if (requestTask()) {
							// Try to take one more task
							continue;
						}
					}
					ping();
					for (;;) {
						Runnable r = null;
						synchronized (this.runnables) {
							if (!this.runnables.isEmpty()) {
								r = this.runnables.remove(0);
							}
						}
						if (r == null) {
							break;
						}
						r.run();
					}
					waitTicks(agentApplication.getWaitTicks());
				} catch (CoreException e) {

					if (e.getStatus() instanceof RegisterStatus) {
						// Just logging
						reportError(
								"Failed to register agent:" + e.getMessage(), e);
						safeSleep(10000); // Wait for 10 seconds
					} else {
						reportError("With unregister" + e.getMessage(), e);
						unregister();
					}
				} catch (InterruptedException e) {
					break;
				}
			}
		} finally {
			unregister();
			agentApplication.doCleanupPDE();
		}
	}

	private void safeSleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception eee) {
			// ignore
		}
	}

	private void waitTicks(int mul) throws InterruptedException {
		if (mul == 0) {
			return;
		}
		synchronized (runnables) {
			this.runnables.wait(AgentApplication.TICK_MILLIS * mul);
		}
	}

	private AgentInfo getAgentInfo() {
		if (this.agentInfo == null) {
			return null;
		}
		return EcoreUtil.copy(this.agentInfo);
	}

	protected void unregister() {
		agentMonitor.log("unregister agent from server", null);
		setAgentInfo(null);
	}

	private void setAgentInfo(AgentInfo object) {
		this.agentInfo = object;
	}

	protected void register() throws CoreException {
		try {
			RegisterAgent cmd = ServerCommandsFactory.eINSTANCE
					.createRegisterAgent();
			AgentInfo info = ModelFactory.eINSTANCE.createAgentInfo();
			info.setUri(getAgentID());
			info.setClassifier(agentApplication.getClassifier());
			info.setLaunchId(agentApplication.generateAgentId());
			info.getSkipTags().addAll(agentApplication.getSkipTags());
			cmd.setAgent(info);
			agentMonitor.log("register agent:" + info.getLaunchId(), null);

			ExecResult status = agentApplication.getQ7Server().execCommand(cmd,
					60000, new NullProgressMonitor());
			if (status != null && status.getStatus().isOK()) {
				RegisterAgentResult result = status
						.getPipeContent(RegisterAgentResult.class);
				if (result != null) {
					String serverUri = agentApplication.getServerHost();
					if (result.getHttpServer() != null) {
						// Use dedicated http server
						serverUri = result.getHttpServer();
					}
					ServerUriAutProvider.setServerInfo(serverUri,
							result.getHttpPort());
				}
				setAgentInfo(info);
				agentMonitor.log("register successful", null);
			} else {
				agentMonitor.log("failed to register to server, cause: "
						+ status.getStatus().toString(), status.getStatus()
						.getException());
			}
			agentMonitor.log("register agent:" + info.getLaunchId(), null);
			agentApplication.onRegistered(getAgentID());
		} catch (Exception e) {
			throw new CoreException(new RegisterStatus(IStatus.ERROR,
					AgentAppPlugin.PLUGIN_ID, RegisterStatus.FAILED,
					"Failed to register agent on server", e));
		}
	}

	protected void ping() throws CoreException, InterruptedException {
		if (agentApplication.isDoNotSendPing()) {
			return;
		}
		AgentPing cmd = ServerCommandsFactory.eINSTANCE.createAgentPing();
		// AgentInfo info = ModelFactory.eINSTANCE.createAgentInfo();
		// info.setUri(getAgentID());
		// info.setClassifier(agentApplication.getClassifier());
		// info.setLaunchId(agentApplication.generateAgentId());
		// info.getSkipTags().addAll(agentApplication.getSkipTags());

		cmd.setAgent(EcoreUtil.copy(getAgentInfo()));

		cmd.setDetails(StatUtils.getDetails(getSuiteRegistry().getBaseDir()));

		ExecResult execCommand = agentApplication.getQ7Server().execCommand(
				cmd, PING_TIMEOUT, new NullProgressMonitor());
		if (execCommand != null) {
			Object[] content = execCommand.getPipeContent();
			if (content != null && content.length == 1
					&& content[0] instanceof Integer) {
				Integer state = (Integer) content[0];
				if (state.intValue() == -1) {
					unregister();
				}
				if (state.intValue() == 1 || state.intValue() == -1) {
					if (tasks.size() > 0) {
						agentMonitor.log("Execution cancel is recieved", null);
						tasks.clear();
						tasksCount.set(0);
						lastSuite = null;
						// Do a really execution cancel
						getMonitor().setCanceled(true);
					}
				}
			}
		}
	}

	private String getAgentID() {
		return String.format("tcp://%s:%d", agentApplication.getThisHost(),
				agentPort);
	}

	protected boolean requestTask() throws CoreException, InterruptedException {
		AgentTask ftask = null;
		if (tasksCount.get() >= agentApplication.fetchCount + 1) {
			// No more tasks required
			return false;
		}
		// Need to request task
		// Check for a free space for at least 200mb before asking for a
		// testcase agentMonitor.log("request task", null);
		long freeSpace = StatUtils
				.getFreeSpace(getSuiteRegistry().getBaseDir());
		if (freeSpace < 200) {
			// returnTask(new Exceptio("Not enought space to execute test on: "
			// + getAgentID()), TaskStatus.NO_SPACE_LEFT_ON_DEVICE);
			return false;
		}

		GetTask cmd = ServerCommandsFactory.eINSTANCE.createGetTask();
		cmd.setAgent(getAgentInfo());
		cmd.setSuiteId(lastSuite);
		ExecResult result = agentApplication.getQ7Server().execCommand(cmd,
				GET_TASK_TIMEOUT, new NullProgressMonitor());
		if (result.getStatus() instanceof EclTcpSocketStatus) {
			String message = String.format("Can't get task. Status: "
					+ result.getStatus().getMessage());
			reportTaskRecieve(false);
			agentMonitor.log(message, new Exception());
			return false;
		}

		Task task = null;
		try {
			task = result.getPipeContent(Task.class);
		} catch (Exception e) {
			reportError("Failed to get task content: " + e.getMessage(), e);
			reportTaskRecieve(false);
			return false;
		}
		if (task == null) {
			reportTaskRecieve(false);
			return false;
		}
		ftask = new AgentTask();
		ftask.task = task;
		// Check for available space and return task if no space, with
		// appropriate error.

		agentMonitor.log("task received: " + ftask.task.getSuiteId(), null);

		if (!copyArtifacts(ftask)) {
			reportTaskRecieve(false);
			return false;
		}
		updateServerURI(ftask);

		reportMessage(ftask.task.getSuiteId(), Plugin.UTILS.createInfo("Task recieved succesfully: "
				+ ftask.task.getId()), AgentLogEntryType.LOGONLY);
		tasks.add(ftask);
		tasksCount.incrementAndGet();
		reportTaskRecieve(true);
		return true;
	}

	private void updateServerURI(AgentTask ftask) {
		AutInfo aut = ftask.task.getAut();
		// Process injections
		InjectionConfiguration injection = aut.getInjection();
		if (injection == null) {
			return; // no need since no injections
		}
		EList<Entry> list = injection
				.getEntries();
		Iterator<Entry> iterator = list
				.iterator();
		while (iterator.hasNext()) {
			Entry next = iterator.next();
			if (next instanceof UpdateSite) {
				UpdateSite site = (UpdateSite) next;
				if (ServerUriAutProvider.isSupported(site.getUri())) {
					site.setUri(ServerUriAutProvider.getUri(site.getUri()));
				}
			}
		}

	}

	protected void returnTask(final AgentTask task, final Throwable ex, final TaskStatus returnCause) {
		synchronized (runnables) {
			runnables.add(new Runnable() {

				@Override
				public void run() {
					if (task != null) {
						try {
							agentMonitor.log(
									"return task because of:" + ex.getMessage(),
									ex);
							CompleteTask cmd = ServerCommandsFactory.eINSTANCE
									.createCompleteTask();
							
							String cause = ex.getMessage();
							if ((cause == null || cause.isEmpty())
									|| Q7TestingHelper.TESTING) {
								Writer writer = new StringWriter();
								PrintWriter prtintWriter = new PrintWriter(writer, true);
								ex.printStackTrace(prtintWriter);
								cause = writer.toString();
							}
							cmd.setAgent(getAgentInfo());
							cmd.setId(task.task.getId());
							cmd.setSuiteId(task.task.getSuiteId());
							cmd.setReturnCause(returnCause);
							cmd.setReport(ReportUtil.generateFailedReport(task.task.getId(), "", ex));
							lastSuite = null; // Since failed

							agentApplication.getQ7Server().execCommand(cmd,
									AGENT_ECL_TIMEOUT,
									new NullProgressMonitor());
						} catch (Throwable exIgnore) {
							String message = String
									.format("Can't return task. Disconnect Agent.");
							agentMonitor.log(message, exIgnore);
						} finally {
							task.dir.remove();
						}
					}
				}
			});
			// Wake up main thread
			runnables.notifyAll();
		}
	}

	protected void reportError(final String msg, final Throwable ex) {
		synchronized (runnables) {
			runnables.add(new Runnable() {

				@Override
				public void run() {
					agentMonitor.log("report error to server msg: " + msg
							+ (ex != null ? ex.getMessage() : ""), ex);
					MultiStatus failure = new MultiStatus(getClass(), 0, "Agent error", null);
					File dir = getSuiteRegistry().getBaseDir();
					long freeSpace = dir.getFreeSpace();

					if (ex instanceof CoreException coreEx) {
						failure.merge(coreEx.getStatus());
					}
					failure.add(RcpttPlugin.createStatus(ex));
					failure.add(Status.info("Free disk space at: " + dir.getAbsolutePath()
							+ " is " + (freeSpace / 1024 * 1024) + " mb"));
					ReportProblem cmd = ServerCommandsFactory.eINSTANCE.createReportProblem();
					cmd.setCause(ProcessStatusConverter.toProcessStatus(failure));
					cmd.setAgent(getAgentInfo());

					try {
						agentApplication.getQ7Server().execCommand(cmd,
								AGENT_ECL_TIMEOUT, new NullProgressMonitor());
					} catch (Throwable exIgnore) {
						String message = String
								.format("Can't report problem to server.");
						agentMonitor.log(message, exIgnore);
					}
				}
			});
			runnables.notifyAll();
		}
	}

	protected void reportTaskRecieve(final boolean state) {
		MarkTaskRecieved cmd = ServerCommandsFactory.eINSTANCE
				.createMarkTaskRecieved();
		cmd.setState(state);
		cmd.setAgent(getAgentInfo());
		try {
			agentApplication.getQ7Server().execCommand(cmd, AGENT_ECL_TIMEOUT,
					new NullProgressMonitor());
		} catch (Throwable exIgnore) {
			String message = String.format("Can't report problem to server.");
			agentMonitor.log(message, exIgnore);
		}
	}

	protected void reportMessage(final String suiteID, final IStatus status,
			final AgentLogEntryType type) {
		final ProcessStatus ps = ProcessStatusConverter.toProcessStatus(status);
		synchronized (runnables) {
			runnables.add(new Runnable() {

				@Override
				public void run() {
					ReportAgentLog cmd = ServerCommandsFactory.eINSTANCE
							.createReportAgentLog();
					cmd.setAgent(getAgentInfo());
					cmd.setSuiteId(suiteID);
					cmd.setType(type);
					cmd.setStatus(ps);

					try {
						agentApplication.getQ7Server().execCommand(cmd,
								AGENT_ECL_TIMEOUT, new NullProgressMonitor());
					} catch (Throwable exIgnore) {
						String message = String
								.format("Can't report log entry to server.");
						agentMonitor.log(message, exIgnore);
					}
				}
			});
			runnables.notifyAll();
		}
	}

	protected void reportAUTStatus(final String suiteID,
			final Map<String, String> files, final AutStartupStatus status) {
		synchronized (runnables) {
			runnables.add(new Runnable() {

				@Override
				public void run() {
					ReportAUTStartup cmd = ServerCommandsFactory.eINSTANCE
							.createReportAUTStartup();
					cmd.setAgent(getAgentInfo());

					if (files != null) {
						for (Map.Entry<String, String> file : files.entrySet()) {
							cmd.getFiles().put(file.getKey(),
									toBase64(file.getValue()));
						}
					}
					cmd.setState(status);
					cmd.setSuiteId(suiteID);
					try {
						agentApplication.getQ7Server().execCommand(cmd,
								AGENT_ECL_TIMEOUT, new NullProgressMonitor());
					} catch (Throwable exIgnore) {
						String message = String
								.format("Can't report problem to server.");
						agentMonitor.log(message, exIgnore);
					}

				}
			});
			runnables.notifyAll();
		}
	}

	private String toBase64(String log) {
		if (log == null) {
			return null;
		}
		byte[] bytes = log.getBytes();
		if (bytes != null) {
			return Base64.encode(bytes);
		}
		return null;
	}

	protected void completeTask(final AgentTask task, final Report report)
			throws CoreException {
		synchronized (runnables) {
			runnables.add(new Runnable() {

				@Override
				public void run() {
					try {
						String details = org.eclipse.rcptt.reporting.util.ReportUtils.getDetails(report
								.getRoot());
						if (details
								.contains("org.eclipse.swt.SWTError:No more handles")) {
							Exception e = new Exception(details);
							agentMonitor.log("No more handles", e);

							shutdownAuts(task.task.getSuiteId(), true, true);
							returnTask(task, e, TaskStatus.NO_MORE_HANDLES);
							reportError(
									"AUT is in state: org.eclipse.swt.SWTError:No more handles. Shutdown AUT, and disable agent until next reshedule",
									e);
							return;
						}

						CompleteTask cmd = ServerCommandsFactory.eINSTANCE
								.createCompleteTask();
						cmd.setReport(report);
						cmd.setAgent(getAgentInfo());
						cmd.setSuiteId(task.task.getSuiteId());
						cmd.setId(task.task.getId());
						cmd.setReturnCause(TaskStatus.OK);
						lastSuite = task.task.getSuiteId();
						agentApplication.getQ7Server().execCommand(cmd,
								AGENT_ECL_TIMEOUT, new NullProgressMonitor());
					} catch (Throwable ex) {
						String message = String
								.format("Can't complete task. Disconnect Agent.");
						agentMonitor.log(message, ex);
					} finally {
						task.dir.remove();
					}
				}
			});
			// Wake up main thread
			runnables.notifyAll();
		}
	}

	public synchronized TestSuiteRegistry getSuiteRegistry() {
		if (registry == null) {
			registry = new TestSuiteRegistry(new File(new File(AgentPlugin
					.getDefault().getStateLocation().toOSString()), "suites-"
					+ agentApplication.getClassifier() + "-agent-" + id));
			// Ensure it is fully clean
			registry.removeAll();
		}
		return registry;
	}

	protected boolean copyArtifacts(AgentTask ftask) throws CoreException {
		// Copy artifacts from common folder
		try {
			TestSuite suite = ftask.task.getSuite();
			ftask.dir = getSuiteRegistry().getDirectory(
					suite.getId() + executeIndex, true, true);
			ftask.dir.clearOutDated(suite, true);
			for (Q7Artifact artifact : ftask.task.getArtifacts().toArray(
					new Q7Artifact[0])) {
				ftask.dir.putResource(artifact);
			}
			return true;
		} catch (Exception e) {
			reportError("Failed to store test on agent: " + e.getMessage(), e);
			return false;
		} finally {
			executeIndex++;
		}
	}

	protected ITestExecutor getExecutor(AutInfo aut) throws CoreException, InterruptedException {
		EObjectKey<AutInfo> key = AutUtil.getAutKey(aut);
		if (started != null && key.equals(startedAutInfo)) {
			return started;
		}
		shutdownAuts(null, false, true);
		try (Closeable c = started) {

		} catch (IOException e) {
			throw new CoreException(error("Failed to dispose previous AUT", e));
		}
		started = null;
		try {
			started = agentApplication.createExecutor(aut);
			started.clearConfigurations();
			startedAutInfo = key;
		} catch (RuntimeException ex) {
			throw AgentAppPlugin.createException("Creating TestExecutor error:"
					+ ex.getMessage(), ex);
		}

		return started;
	}

	protected void shutdownAuts(String suiteID, boolean cancel,
			boolean removeLaunches) throws InterruptedException, CoreException {
		if (suiteID != null) {
			reportMessage(suiteID, Plugin.UTILS.createInfo("Shut down auts"), AgentLogEntryType.LOGONLY);
		}
		AgentMonitor mon = getMonitor();
		if (cancel && mon != null) {
			mon.setCanceled(true);
		}

		if (started != null) {
			agentMonitor.log("Shutdown AUTs", null);
			try {
				started.close();
			} catch (IOException e) {
				throw new CoreException(error("Can't dispose previous AUT", e));
			}
		}
		List<ILaunch> oldLaunches = new ArrayList<ILaunch>();
		if (DebugPlugin.getDefault() != null) {
			for (ILaunch launch : DebugPlugin.getDefault().getLaunchManager()
					.getLaunches()) {
				BaseAutLaunch aut = BaseAutManager.INSTANCE.getByLaunch(launch);
				if (aut != null) {
					try {
						aut.gracefulShutdown(300);
					} catch (TimeoutException e) {
						throw new CoreException(Status.error("Can't terminate AUT + " + aut.getAut().getName(), e));
					}
				}
				launch.terminate();
				if (launch.isTerminated()) {
					oldLaunches.add(launch);
				}

				if (DebugPlugin.getDefault().getLaunchManager().getLaunches().length == 0) {
					break;
				}
			}
		}
		if (removeLaunches) {
			if (DebugPlugin.getDefault() != null) {
				DebugPlugin
						.getDefault()
						.getLaunchManager()
						.removeLaunches(
								oldLaunches.toArray(new ILaunch[oldLaunches
										.size()]));
			}
		}

		started = null;
		startedAutInfo = null;
	}

	public synchronized AgentMonitor getMonitor() {
		if (monitor == null) {
			monitor = new AgentMonitor(null, this);
		}
		return monitor;
	}

	public int getFetchCount() {
		return tasks.size();
	}

	public AgentTask nextTask() throws InterruptedException {
		return tasks.poll(60, TimeUnit.SECONDS);
	}

	public void complete() {
		if (tasksCount.get() > 0) {
			tasksCount.decrementAndGet();
		}
	}
}
