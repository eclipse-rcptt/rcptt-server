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
package org.eclipse.rcptt.cloud.server.tests;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.reporting.ItemKind;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.ReportingFactory;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.ReportHelper;
import org.eclipse.rcptt.sherlock.core.INodeBuilder;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.sherlock.core.reporting.ReportBuilder;

import org.eclipse.rcptt.cloud.agent.AgentPlugin;
import org.eclipse.rcptt.cloud.agent.ITestExecutor;
import org.eclipse.rcptt.cloud.agent.app.internal.AgentAppPlugin;
import org.eclipse.rcptt.cloud.agent.app.internal.AgentApplication;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper;
import org.eclipse.rcptt.cloud.model.Q7TestingHelper.TestCaseState;
import org.eclipse.rcptt.cloud.model.TestOptions;

public class FakeAgentApplication extends AgentApplication {

	public FakeAgentApplication() {
		fetchCount = 0;

	}

	@Override
	protected void addHook() {
		// skip hook addition
	}

	private ReportBuilder createReportBasedOn(Scenario scenario) {
		ReportBuilder builder = ReportBuilder.create(scenario.getName());
		INodeBuilder root = builder.getCurrent();
		Q7Info rinfo = ReportingFactory.eINSTANCE.createQ7Info();
		rinfo.setType(ItemKind.TESTCASE);

		Q7Info info = ReportHelper.createInfo();
		info.setType(ItemKind.SCRIPT);
		String tags = scenario.getTags();
		int passFail = scenario.getContexts().size() % 2;
		if (tags != null && tags.contains("pass")) {
			passFail = 1;
		}
		if (tags != null && tags.contains("fail")) {
			passFail = 0;
		}
		info.setResult(passFail == 1 ? RcpttPlugin.createProcessStatus(IStatus.OK, "Success") : RcpttPlugin.createProcessStatus(IStatus.ERROR, "Fail"));
		rinfo.setResult(passFail == 1 ? RcpttPlugin.createProcessStatus(IStatus.OK, "Success") : RcpttPlugin.createProcessStatus(IStatus.ERROR, "Fail"));
		rinfo.setId(scenario.getId());
		root.setProperty(IQ7ReportConstants.ROOT, rinfo);

		// int delay = r.nextInt(1000) + passFail;
		// if (!"2".equals(id)) {
		// delay += 10;
		// }
		// Thread.sleep(delay);
		info.setId(scenario.getId());
		INodeBuilder nde1 = root.beginTask(scenario.getName());
		nde1.setProperty(IQ7ReportConstants.ROOT, info);
		nde1.endTask();
		return builder;
	}

	private boolean doNotSendPing;
	private boolean stopOnRun;
	private boolean waitOnCounter = false;

	@Override
	public boolean isDoNotSendPing() {
		return doNotSendPing;
	}

	@Override
	protected ITestExecutor.Closeable createExecutor(AutInfo aut) throws CoreException {
		return new ITestExecutor.Closeable() {

			private Scenario scenario;
			private boolean isStarted = false;

			@Override
			public void startAut(IProgressMonitor monitor) throws CoreException {
				if (scenario != null) {
					if (Q7TestingHelper.containsTag(scenario, Q7TestingHelper.TestCaseState.autfailure_start)) {
						isStarted = false;
						throw AgentPlugin.createException(
								"Failure to start AUT", null);
					}
				}
				isStarted = true;
			}

			@Override
			public void shutdown() {
			}

			@Override
			public void setTestOptions(TestOptions options) {
			}

			@Override
			public Report runTest(int agentID, ITestStore dir,
					Q7ArtifactRef suite, IProgressMonitor monitor)
					throws CoreException {
				while (stopOnRun) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						throw new CoreException(Status.CANCEL_STATUS);
					}
				}
				// Wait from 0 to 2 seconds per test
				if (agentID % 2 == 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						return null;
					}
				}
				for (Q7ArtifactRef scenarioRef : ModelUtil.scenarioList(dir
						.getTestSuite())) {
					Scenario scenario;
					try {
						Q7Artifact ref = dir.getResource(scenarioRef);
						if (ref == null) {
							throw new CoreException(
									AgentAppPlugin.createErrorStatus(
											"Failed to retrive testcase "
													+ scenarioRef.getId(), null));
						}
						scenario = (Scenario) ref.getContent();
					} catch (Exception e) {
						String scenarioId = scenarioRef.getId();
						String msg = String
								.format("Failed to load Q7 test case with id: %s. Cause: %s",
										scenarioId, e.getMessage());
						IStatus status = AgentPlugin.createStatus(msg,
								IStatus.WARNING, e);
						// AgentPlugin.log(status);

						throw new CoreException(status);
					}
					if (Q7TestingHelper.containsTag(scenario,
							TestCaseState.wait5sec)) {
						doNotSendPing = true;
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							doNotSendPing = false;
						}
					}
					if (Q7TestingHelper.containsTag(scenario,
							TestCaseState.wait20sec)) {
						doNotSendPing = true;
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							doNotSendPing = false;
						}
					}
					if (Q7TestingHelper.containsTag(scenario,
							TestCaseState.waitonCounter)) {
						doNotSendPing = true;
						while (waitOnCounter) {
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						doNotSendPing = false;
					}
					if (Q7TestingHelper.containsTag(scenario,
							TestCaseState.timeout)) {
						IStatus status = AgentPlugin
								.createStatus(String.format(
										"%s timed out, AUT will be shutdown",
										scenario.getName()), IStatus.ERROR, null);
						AgentPlugin.log(status);
						shutdown();
						throw new CoreException(status);
					}

					ReportBuilder builder = createReportBasedOn(scenario);
					return builder.getReportCopy();
				}
				return null;
			}

			@Override
			public boolean isStarted() {
				return isStarted;
			}

			@Override
			public void deployAut(IProgressMonitor monitor)
					throws CoreException {
				if (scenario != null) {
					if (Q7TestingHelper.containsTag(scenario,
							Q7TestingHelper.TestCaseState.autfailure_deploy)) {
						throw AgentPlugin.createException(
								"Failure to deply AUT", null);
					}
				}
			}

			@Override
			public void clearWorkspaces() {
			}

			@Override
			public void clearConfigurations() {
			}

			@Override
			public void prepare(ITestStore dir, Q7ArtifactRef suiteRef) {
				for (Q7ArtifactRef scenarioRef : ModelUtil.scenarioList(dir
						.getTestSuite())) {
					try {
						Q7Artifact res = dir.getResource(scenarioRef);
						if (res != null) {
							scenario = (Scenario) res.getContent();
						}
						if (scenario != null) {
							break;
						}
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public String getOutStreamFile(long limit) {
				return null;
			}

			@Override
			public String getErrStreamFile(long limit) {
				return null;
			}

			@Override
			public String getWorkspaceLog(long limit) {
				return null;
			}

			@Override
			public Map<String, String> obtainConfigurationFiles(
					IProgressMonitor monitor) {
				return null;
			}

			@Override
			public void ping() throws CoreException {

			}

			@Override
			public void close() throws IOException {
				
			}
		};
	}

	public void setStopOnRun(boolean stopOnRun) {
		this.stopOnRun = stopOnRun;
	}

	public int getPreFetch() {
		return agentThreads[0].getFetchCount();
	}

	public void setWaitOnCounter(boolean b) {
		this.waitOnCounter = b;
	}
}
