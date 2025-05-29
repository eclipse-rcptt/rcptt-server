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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import com.google.common.base.Preconditions;
import org.eclipse.rcptt.cloud.agent.ITestExecutor;
import org.eclipse.rcptt.cloud.agent.app.internal.AgentThread.AgentTask;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;
import org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus;

final class ExecutionThread extends Thread {
	private final AgentThread agentThread;
	private int id;

	ExecutionThread(AgentThread agentThread, String name) {
		super(name);
		Preconditions.checkNotNull(agentThread);
		this.agentThread = agentThread;
		this.id = this.agentThread.id;
		
	}

	@Override
	public void run() {
		try {
			while (this.agentThread.agentApplication.isAlive()
					&& !this.agentThread.stopped) {
				// long start = System.currentTimeMillis();
				AgentTask agentTask = null;
				ITestExecutor executor = null;
				try {
					agentTask = this.agentThread.nextTask();
					if (agentTask == null) {
						continue;
					}
					Task task = agentTask.task;
					TestSuite suite = task.getSuite();
					this.agentThread.lastSuite = task.getSuiteId();
					AutInfo aut = task.getAut();
					TestOptions options = EcoreUtil.copy(task.getTestOptions());
					this.agentThread.agentApplication.replaceOptions(options);

					final Q7ArtifactRef suiteRef = ModelUtil
							.createSuiteRef(EcoreUtil.copy(suite));

					executor = this.agentThread.getExecutor(aut);

					executor.setTestOptions(options);

					ITestStore dir = agentTask.dir;
					executor.prepare(dir, suiteRef);

					AgentMonitor monitor = this.agentThread.getMonitor();
					monitor.setCanceled(false);
					monitor.setSuiteID(task.getSuiteId());
					// System.out.println("Begin AUT prepare:" + aut.getUri());
					executor.deployAut(monitor);

					// try to start aut for at least 2 times, before return
					// error message
					tryStartAUT(task.getSuiteId(), executor, monitor);

					final IProgressMonitor taskMonitor = monitor
							.createSubMonitor("Run tests", -1);
					Report report = executor.runTest(id, dir, suiteRef,
							taskMonitor);

					if (report != null) {
						if (!monitor.isCanceled()) {
							this.agentThread.completeTask(agentTask, report);
						}
					} else {
						agentThread
								.reportError(
										"Failed to execute test on AUT because of AUT problem.",
										null);
						this.agentThread.returnTask(agentTask, new Exception(
								"Failed to start AUT."),
								TaskStatus.FAILED_TO_START_AUT);
					}
					if (executor.needShutdownAUT()) {
						AutStartupStatus state = AutStartupStatus.SHUTDOWN_ON_OPTION;
						if (executor.isTestTimeout()) {
							state = AutStartupStatus.SHUTDOWN_ON_TIMEOUT;
						}
						if (report == null) {
							state = AutStartupStatus.FAILED_TO_START;
						}
						this.agentThread.shutdownAuts(task.getSuiteId(), true,
								false);
						this.agentThread
								.reportAUTStatus(
										task.getSuiteId(),
										executor.obtainConfigurationFiles(new NullProgressMonitor()),
										state);
					}
				} catch (OperationCanceledException canceled) {
					this.agentThread.err("Execution is canceled", null);
					// Do not return task since it is canceled.
					this.agentThread.shutdownAuts(agentTask.task.getSuiteId(),
							true, true);// Restart
					// AUT
					continue;
				} catch (Throwable e) {
					this.agentThread.err("Exception during test execution:", e);
					TaskStatus returnCause = (executor != null && !executor
							.isStarted()) ? TaskStatus.FAILED_TO_START_AUT
							: TaskStatus.UNKNOWN;
					this.agentThread.returnTask(agentTask, e, returnCause);
					this.agentThread.shutdownAuts(agentTask.task.getSuiteId(),
							true, true);// Restart
					// AUT
					continue;
				} finally {
					this.agentThread.complete();
				}
			}
		} catch (Throwable e) {
			AgentAppPlugin.error("Execution failure ", e);
			agentThread.reportError("Execution failure: " + e.getMessage(), e);
		}
	}

	private void tryStartAUT(String suiteID, final ITestExecutor executor,
			AgentMonitor monitor) throws CoreException, InterruptedException {
		boolean wasStarted = executor.isStarted();
		boolean needShutdown = false;
		try {
			executor.startAut(monitor);
		} catch (CoreException e) {
			agentThread
					.reportError("Failed to start AUT: " + e.getMessage(), e);
			needShutdown = true;
			throw e;
		} finally {
			if (!wasStarted) {
				// Obtain console and workspace logs and send to server
				this.agentThread.reportAUTStatus(suiteID, executor
						.obtainConfigurationFiles(new NullProgressMonitor()),
						executor.isStarted() ? AutStartupStatus.STARTED
								: AutStartupStatus.FAILED_TO_START);
			}
			if (needShutdown) {
				executor.exceptionShutdown();
			}
		}
		try {
			executor.ping();
		} catch (InterruptedException e) {
			throw e;
		} catch (CoreException e) {
			executor.shutdown();
			agentThread.reportError("Failed to ping AUT: " + e.getMessage(), e);
			this.agentThread.reportAUTStatus(suiteID, executor
					.obtainConfigurationFiles(new NullProgressMonitor()),
					AutStartupStatus.FAILED_TO_PING);
			throw e;
		}
	}
}
