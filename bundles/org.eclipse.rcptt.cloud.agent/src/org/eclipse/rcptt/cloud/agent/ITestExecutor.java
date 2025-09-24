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
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.TestOptions;

public interface ITestExecutor {
	
	interface Closeable extends ITestExecutor, java.io.Closeable {
		
	}

	public void deployAut(IProgressMonitor monitor) throws CoreException;

	public void startAut(IProgressMonitor monitor) throws CoreException;

	public boolean isStarted();

	public void clearWorkspaces();

	public void clearConfigurations();

	public Report runTest(int agentID, ITestStore dir, IProgressMonitor monitor) throws CoreException, TimeoutException;

	public void shutdown();

	public void setTestOptions(TestOptions options);

	public void prepare(ITestStore dir);

	public String getOutStreamFile(long limit);

	public String getErrStreamFile(long limit);

	public String getWorkspaceLog(long limit);

	public Map<String, String> obtainConfigurationFiles(IProgressMonitor monitor);

	public void ping() throws CoreException, InterruptedException;

}
