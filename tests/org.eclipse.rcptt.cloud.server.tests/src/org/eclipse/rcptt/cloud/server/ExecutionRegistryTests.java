/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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
package org.eclipse.rcptt.cloud.server;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry.Repository;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionRegistryTests {
	
	@Rule
	public final TemporaryFolder temporaryFolder = new TemporaryFolder();
	private ISMHandleStore<SuiteStats> suiteStore;
	@Mock
	public Repository repository;
	private ExecutionRegistry subject;

	
	@Before
	public void before() {
		subject = new ExecutionRegistry(repository);
		suiteStore = new ISMHandleStore<SuiteStats>(new File(temporaryFolder.getRoot(), "suites"),
				StatsPackage.eINSTANCE.getSuiteStats());
	}
	
	@Test
	public void deleteTrueOldest() throws CoreException {
		ISMHandle<SuiteStats> a = suiteStore.getHandle("a");
		ISMHandle<SuiteStats> b = suiteStore.getHandle("b");
		ExecutionEntry a1 = subject.beginNewSuite(a);
		subject.removeSuiteHandle(a1.getSuiteId());
		a1.updateStatistics(e -> {
			e.setStartTime(currentTimeMillis() - 10000);
		});
		ExecutionEntry b1 = subject.beginNewSuite(b);
		subject.removeSuiteHandle(b1.getSuiteId());
		ExecutionEntry b2 = subject.beginNewSuite(b);
		subject.removeSuiteHandle(b2.getSuiteId());
	
		assertEquals(1, subject.getExecutions(a).getHandles().size());
		assertEquals(2, subject.getExecutions(b).getHandles().size());
		
		List<ISMHandle<SuiteStats>> allSuites = List.of(a, b);
		subject.removeOldExecutions(allSuites, 100, 100, (ignored) -> {});
		assertEquals(1, subject.getExecutions(a).getHandles().size());
		assertEquals(2, subject.getExecutions(b).getHandles().size());
		
		subject.removeOldExecutions(allSuites, 1, 100, (ignored) -> {});
		assertEquals(1, subject.getExecutions(a).getHandles().size());
		assertEquals(2, subject.getExecutions(b).getHandles().size());
		
		subject.removeOldExecutions(allSuites, 1, 2, (ignored) -> {});
		
		assertEquals(0, subject.getExecutions(a).getHandles().size());
		assertEquals(2, subject.getExecutions(b).getHandles().size());
		
		subject.removeOldExecutions(allSuites, 1, 2, (ignored) -> {});
		assertEquals(0, subject.getExecutions(a).getHandles().size());
		assertEquals(2, subject.getExecutions(b).getHandles().size());
		
		subject.removeOldExecutions(allSuites, 1, 1, (ignored) -> {});
		assertEquals(0, subject.getExecutions(a).getHandles().size());
		assertEquals(1, subject.getExecutions(b).getHandles().size());
	}

}
