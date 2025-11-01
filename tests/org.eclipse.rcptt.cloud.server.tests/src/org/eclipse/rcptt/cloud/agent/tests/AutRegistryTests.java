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
package org.eclipse.rcptt.cloud.agent.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.rcptt.cloud.agent.AutRegistry;
import org.eclipse.rcptt.cloud.agent.autManager.IAutProvider;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.server.tests.Activator;
import org.eclipse.rcptt.internal.launching.ext.OSArchitecture;
import org.eclipse.rcptt.launching.ext.AUTInformation;
import org.eclipse.rcptt.launching.ext.OriginalOrderProperties;
import org.eclipse.rcptt.launching.injection.Directory;
import org.eclipse.rcptt.launching.injection.Entry;
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;
import org.eclipse.rcptt.launching.injection.InjectionFactory;
import org.eclipse.rcptt.launching.injection.UpdateSite;
import org.eclipse.rcptt.launching.internal.target.Q7Target;
import org.eclipse.rcptt.launching.target.ITargetPlatformHelper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.collect.ImmutableMap;

public class AutRegistryTests {
	
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private class DummyTargetPlatformHelper implements ITargetPlatformHelper {
		private String name; 

		@Override
		public void save() {
		}

		@Override
		public IStatus resolve(IProgressMonitor monitor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isResolved() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Optional<Path> getJavaHome() {
			return Optional.empty();
		}

		@Override
		public String getTemplateConfigLocation() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getTargetPlatformProfilePath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IStatus getStatus() {
			return Status.OK_STATUS;
		}

		@Override
		public String getRuntimeVersion() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Q7Target getQ7Target() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getProducts() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getIniVMArgs() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getEquinoxStartupPath(String bundleEquinoxLauncher) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getDefaultProduct() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getDefaultApplication() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public OriginalOrderProperties getConfigIniProperties() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String[] getApplications() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public OSArchitecture detectArchitecture(StringBuilder detectMsg) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void delete() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IStatus applyInjection(org.eclipse.rcptt.launching.injection.InjectionConfiguration configuration, IProgressMonitor monitor) {
			return Status.OK_STATUS;
		}

		@Override
		public org.eclipse.pde.core.plugin.IPluginModelBase getWeavingHook() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, Version> getVersions() {
			return ImmutableMap.of(AUTInformation.OSGI, Version.create("4.5"), AUTInformation.VERSION, Version.create("4.5"));
		}

		@Override
		public Map<String, String> getIniEnvironment() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getUserArea() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Stream<Model> getModels() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String findCompatibilityProblems(Set<String> providedExecutionEnvironemnts) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String explainJvmRequirements() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private static final File baseLocation = new File(new File(Activator.getContext().getStateLocation().toOSString()), "auts");
	private final IAutProvider autProvider = new IAutProvider(){

		@Override
		public boolean isSupport(AutInfo aut) {
			try {
				return new URI(aut.getUri()).getScheme().equals("test");
			} catch (URISyntaxException e) {
				return false;
			}
		}

		@Override
		public File download(AutInfo aut, String classifier, IProgressMonitor monitor)
				throws CoreException {
			return new File(temporaryFolder.getRoot(), "1.zip");
		}};

	@Test
	public void testTargetPlatfromFailure() throws CoreException {
		AutRegistry registry = new AutRegistry(baseLocation, new AutRegistry.ITargetPlatformManager() {
			
			@Override
			public void clear() {				
			}

			@Override
			public org.eclipse.rcptt.launching.target.ITargetPlatformHelper create(String location, IProgressMonitor monitor) throws CoreException {
				throw new IllegalArgumentException("Test success");
			}

		}, autProvider);
		AutInfo info = ModelFactory.eINSTANCE.createAutInfo();
		info.setUri("test://toDownload.zip");
		try {
			registry.deployAut(info, new NullProgressMonitor());
			Assert.fail("Should throw");
		} catch (CoreException e) {
			Assert.assertEquals("Test success", e.getMessage());
		}
	}
	
	@Test
	public void testInitializationFailure() throws CoreException {
		AutRegistry registry = new AutRegistry(baseLocation, new AutRegistry.ITargetPlatformManager() {
			
			@Override
			public void clear() {				
			}

			@Override
			public org.eclipse.rcptt.launching.target.ITargetPlatformHelper create(String location, IProgressMonitor monitor) throws CoreException {
				return new DummyTargetPlatformHelper() {

					@Override
					public Map<String, Version> getVersions() {
						throw new IllegalArgumentException("Test success2");
					}
					
				};
			}

		}, autProvider);
		AutInfo info = ModelFactory.eINSTANCE.createAutInfo();
		info.setUri("test://toDownload.zip");
		try {
			registry.deployAut(info, new NullProgressMonitor());
			Assert.fail("Should throw");
		} catch (CoreException e) {
			Assert.assertEquals("Test success2", e.getMessage());
		}
	}


	@Test
	public void testDownloadAndInjection() throws CoreException {
		AutRegistry registry = new AutRegistry(baseLocation, new AutRegistry.ITargetPlatformManager() {
			
			@Override
			public void clear() {				
			}

			@Override
			public org.eclipse.rcptt.launching.target.ITargetPlatformHelper create(String location, IProgressMonitor monitor) throws CoreException {
				return new DummyTargetPlatformHelper() {

					@Override
					public IStatus applyInjection(InjectionConfiguration configuration, IProgressMonitor monitor) {
						Assert.assertTrue(containsUri(configuration.getEntries(), "test://updatesitex"));
						return super.applyInjection(configuration, monitor);
					}
					
				};
			}

		}, autProvider);
		AutInfo info = ModelFactory.eINSTANCE.createAutInfo();
		info.setUri("test://toDownload.zip");
		info.setInjection(InjectionFactory.eINSTANCE.createInjectionConfiguration());
		UpdateSite entry = InjectionFactory.eINSTANCE.createUpdateSite();
		entry.setUri("test://updatesitex");
		info.getInjection().getEntries().add(entry);
		registry.deployAut(info, new NullProgressMonitor());
	}
	
	@Test(timeout = 10000)
	public void cancelDownload() throws CoreException {
		NullProgressMonitor deployMonitor = new NullProgressMonitor();
		IAutProvider autProvider2 = new IAutProvider() {

			@Override
			public boolean isSupport(AutInfo aut) {
				return true;
			}

			@Override
			public File download(AutInfo aut, String classifier, IProgressMonitor monitor) throws CoreException {
				deployMonitor.setCanceled(true);
				for(;;) {		
					if (monitor.isCanceled()) {
						throw new CoreException(Status.CANCEL_STATUS);
					}
					try {
						Thread.sleep(1000);					
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						throw new CoreException(Status.CANCEL_STATUS);
					}
				}
			}
			
		};
		AutRegistry registry = new AutRegistry(baseLocation, new AutRegistry.ITargetPlatformManager() {
			
			@Override
			public void clear() {				
			}

			@Override
			public org.eclipse.rcptt.launching.target.ITargetPlatformHelper create(String location, IProgressMonitor monitor) throws CoreException {
				throw new AssertionError("Should not reach 2");
			}

		}, autProvider2);
		AutInfo info = ModelFactory.eINSTANCE.createAutInfo();
		info.setUri("test://toDownload.zip");
		info.setInjection(InjectionFactory.eINSTANCE.createInjectionConfiguration());
		UpdateSite entry = InjectionFactory.eINSTANCE.createUpdateSite();
		entry.setUri("test://updatesitex");
		info.getInjection().getEntries().add(entry);
		try {
			registry.deployAut(info, deployMonitor);
			fail("Should not reach");
		} catch (CoreException e) {
			assertEquals(e.getMessage(), IStatus.CANCEL, e.getStatus().getSeverity());
		}
	}
	
	private static boolean containsUri(List<Entry> injections, String uri) {
		for (Entry entry: injections) {
			if (entry instanceof UpdateSite) {
				if (uri.equals(((UpdateSite) entry).getUri()))
					return true;
			} else if (entry instanceof Directory) {
				if (uri.equals(((Directory) entry).getPath()))
					return true;
			} else {
				Assert.fail("Unknown injection type: " + entry.getClass().getName());
			}
		}
		return false;
	}
	@Test
	public void testInjectionFailure() throws CoreException {
		AutRegistry registry = new AutRegistry(baseLocation, new AutRegistry.ITargetPlatformManager() {
			
			@Override
			public void clear() {				
			}

			@Override
			public org.eclipse.rcptt.launching.target.ITargetPlatformHelper create(String location, IProgressMonitor monitor) throws CoreException {
				return new DummyTargetPlatformHelper() {

					@Override
					public IStatus applyInjection(InjectionConfiguration configuration, IProgressMonitor monitor) {
						Assert.assertTrue(containsUri(configuration.getEntries(), "test://updatesite"));
						throw new IllegalStateException("Test success3");
					}
					
				};
			}

		}, autProvider);
		AutInfo info = ModelFactory.eINSTANCE.createAutInfo();
		info.setInjection(InjectionFactory.eINSTANCE.createInjectionConfiguration());
		info.setUri("test://toDownload.zip");
		UpdateSite entry = InjectionFactory.eINSTANCE.createUpdateSite();
		entry.setUri("test://updatesite");
		info.getInjection().getEntries().add(entry);
		try {
			registry.deployAut(info, new NullProgressMonitor());
			Assert.fail("Should throw");
		} catch (CoreException e) {
			Assert.assertEquals("Test success3", e.getMessage());
		}
	}
}
