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

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.TestOptions;

public interface ITestExecutor {

	public void deployAut(IProgressMonitor monitor) throws CoreException;

	public void startAut(IProgressMonitor monitor) throws CoreException;

	public boolean isStarted();

	public void clearWorkspaces();

	public void clearConfigurations();

	public Report runTest(int agentID, ITestStore dir, Q7ArtifactRef suite,
			IProgressMonitor monitor) throws CoreException;

	public void shutdown();

	public void setTestOptions(TestOptions options);

	public void prepare(ITestStore dir, Q7ArtifactRef suiteRef);

	public String getOutStreamFile(long limit);

	public String getErrStreamFile(long limit);

	public String getWorkspaceLog(long limit);

	public boolean isTestTimeout();

	public Map<String, String> obtainConfigurationFiles(IProgressMonitor monitor);

	public void ping() throws CoreException, InterruptedException;

	public boolean needShutdownAUT();

	public void exceptionShutdown();
}
