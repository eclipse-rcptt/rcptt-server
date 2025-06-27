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

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.System.currentTimeMillis;
import static org.eclipse.rcptt.internal.launching.ext.Q7TargetPlatformInitializer.createInjectionConfiguration;
import static org.eclipse.rcptt.internal.launching.ext.Q7TargetPlatformInitializer.getInfo;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.EList;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.rcptt.cloud.agent.autManager.IAutProvider;
import org.eclipse.rcptt.cloud.common.monitor.Q7LogProgressMonitor;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.util.IOUtil;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpsSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.IDownloadMonitor;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;
import org.eclipse.rcptt.internal.launching.aut.BaseAutManager;
import org.eclipse.rcptt.internal.launching.ext.Q7TargetPlatformInitializer;
import org.eclipse.rcptt.internal.launching.ext.Q7TargetPlatformInitializer.Q7Info;
import org.eclipse.rcptt.launching.Aut;
import org.eclipse.rcptt.launching.AutLaunch;
import org.eclipse.rcptt.launching.IQ7Launch;
import org.eclipse.rcptt.launching.Q7LaunchUtils;
import org.eclipse.rcptt.launching.injection.Directory;
import org.eclipse.rcptt.launching.injection.Entry;
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;
import org.eclipse.rcptt.launching.injection.InjectionFactory;
import org.eclipse.rcptt.launching.injection.UpdateSite;
import org.eclipse.rcptt.launching.injection.util.InjectionUtil;
import org.eclipse.rcptt.launching.target.ITargetPlatformHelper;
import org.eclipse.rcptt.launching.target.TargetPlatformManager;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.Q7LoggingManager;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Joiner;

/**
 * Manages AUTs.
 * 
 * @author ivaninozemtsev
 * 
 */
public class AutRegistry {
	private final File baseDir;
	private static IQ7Monitor logMonitor;
	private final ITargetPlatformManager tpManager;
	private final IAutProvider autProvider;
	public interface ITargetPlatformManager {
		ITargetPlatformHelper create(String location, IProgressMonitor monitor) throws CoreException;

		void clear();

	}

	public AutRegistry(File baseDir, ITargetPlatformManager tpManager, IAutProvider autProvider) {
		checkNotNull(baseDir);
		checkNotNull(tpManager);
		checkNotNull(autProvider);
		this.tpManager = tpManager;
		this.autProvider = autProvider;
		getLog();
		logMonitor.log("################ aut-registry initialization...", null);
		this.baseDir = baseDir;
		if (!baseDir.exists() && !baseDir.mkdirs()) {
			throw new IllegalArgumentException("Invalid base dir");
		}
	}

	public static IQ7Monitor getLog() {
		if (logMonitor == null) {
			logMonitor = Q7LoggingManager.get("aut_registry");
		}
		return logMonitor;
	}

	public synchronized ITargetPlatformHelper deployAut(final AutInfo aut,
			final IProgressMonitor monitor) throws CoreException {
		logMonitor.log("deploy aut: " + aut.getId() + " uri: " + aut.getUri(),
				null);
		final ITargetPlatformHelper[] target = new ITargetPlatformHelper[] { null };
		final CoreException[] ex = new CoreException[] { null };
		final boolean complete[] = { false };
		Thread deployAUTThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String msg = "download aut to " + baseDir.toString()
							+ " aut: " + aut.getId() + " uri: " + aut.getUri()
							+ " classifier: " + AgentPlugin.getClassifier();
					logMonitor.log(msg, null);
					long start = System.currentTimeMillis();
					if (monitor instanceof IAgentMonitor) {
						((IAgentMonitor) monitor).logAgentMessage(msg,
								IAgentMonitor.LogType.LogOnly);
					}
					final File autFile = autProvider.download(aut, AgentPlugin.getClassifier(),
							monitor);
					long end = System.currentTimeMillis();
					if (monitor instanceof IAgentMonitor) {
						((IAgentMonitor) monitor).logAgentMessage(
								"download aut done. time: "
										+ Long.toString(end - start),
								IAgentMonitor.LogType.Both);
					}

					IProgressMonitor progressMonitor = new Q7LogProgressMonitor(
							logMonitor);

					long stop = currentTimeMillis() + 60000;
					while (!monitor.isCanceled() && stop > currentTimeMillis()) {
						List<AutLaunch> launches = BaseAutManager.INSTANCE.getLaunches();
						if (launches.isEmpty()) {
							break;
						}
						for (AutLaunch launch: launches) {
							launch.shutdown();
						}
						
					}
					List<String> auts = BaseAutManager.INSTANCE.getLaunches().stream().map(AutLaunch::getAut).map(Aut::getName).toList();
					if (!auts.isEmpty()) {
						throw new IllegalStateException("Unable to terminate auts: " + Joiner.on(", ").join(auts));
					}

					removeTargetPlatforms();
					tpManager.clear();

					start = System.currentTimeMillis();
					target[0] = tpManager.create(autFile.getAbsolutePath(), progressMonitor);
					end = System.currentTimeMillis();
					if (monitor instanceof IAgentMonitor) {
						((IAgentMonitor) monitor).logAgentMessage(
								"create target platform done time:"
										+ Long.toString(end - start),
								IAgentMonitor.LogType.LogOnly);
					}

					InjectionConfiguration injectionConfiguration = InjectionFactory.eINSTANCE.createInjectionConfiguration();
					Map<String, Version> versions = target[0].getVersions();
					Q7Info q7Info = getInfo(target[0], versions);
					if (!aut.isIgnoreOtherInjections()) {
						injectionConfiguration = InjectionUtil.merge(injectionConfiguration, createInjectionConfiguration(new NullProgressMonitor(), q7Info, versions));
					} else {
						injectionConfiguration = InjectionUtil.merge(injectionConfiguration, Q7TargetPlatformInitializer.getAspectJInjection(q7Info, progressMonitor));
					}
					
					if (aut.getInjection() != null) {
						start = System.currentTimeMillis();
						logMonitor.log("apply aut injections", null);
						injectionConfiguration = InjectionUtil.merge(injectionConfiguration, aut.getInjection());
					}

					StringBuilder injs = new StringBuilder();
					EList<Entry> entries = injectionConfiguration.getEntries();
					Set<File> temporatyFolders = new HashSet<File>();
					for (Entry e : entries) {
						if (e instanceof UpdateSite) {
							injs.append("\nupdate site: "
									+ ((UpdateSite) e).getUri());
							EList<String> units = ((UpdateSite) e)
									.getUnits();
							for (String s : units) {
								injs.append("\nunit -> " + s);
							}

						} else if (e instanceof Directory) {
							injs.append("\n directory: "
									+ ((Directory) e).getPath());
						}
					}
					logMonitor
							.log("apply aut injections:" + injs.toString(),
									null);
					if (monitor instanceof IAgentMonitor) {
						((IAgentMonitor) monitor).logAgentMessage(
								"apply injections: " + injs.toString(),
								IAgentMonitor.LogType.LogOnly);
					}
					try {
						IStatus status = target[0].applyInjection(injectionConfiguration, new NullProgressMonitor());
						if (!status.isOK()) {
							AgentPlugin.log(status);
							throw new CoreException(status);
						}
					} finally {
						for (File file : temporatyFolders) {
							FileUtil.deleteFile(file);
						}
						end = System.currentTimeMillis();
						if (monitor instanceof IAgentMonitor) {
							((IAgentMonitor) monitor).logAgentMessage(
									"apply injections done. time: "
											+ Long.toString(end - start),
									IAgentMonitor.LogType.LogOnly);
						}
					}
					target[0].save();
				} catch (CoreException e) {
					ex[0] = e;
					return;
				} catch (Throwable e) {
					ex[0] = AgentPlugin.createException(e.getMessage(), e);
				} finally {
					complete[0] = true;
				}

			}
		}, "Deploy AUT");
		deployAUTThread.start();
		try {
			deployAUTThread.join();
			// startup
		} catch (InterruptedException e) {
			throw AgentPlugin.createException("Timeout during AUT download", e);
		} finally {
			deployAUTThread.interrupt();
		}
		if (ex[0] != null) {
			throw ex[0];
		} else if (!complete[0]) {
			// This is timeout
			throw AgentPlugin
					.createException("Internal error.");
		}
		return target[0];
	}

	protected File downloadUpdateSite(UpdateSite e,
			final IProgressMonitor monitor) throws CoreException {
		String name = FileUtil.limitSize(FileUtil.getID(e.getUri()));
		File zipFile = genNewFile(name);
		// Download
		logMsg(monitor, "Downloading updatesite: " + e.getUri() + " to: "
				+ zipFile, IAgentMonitor.LogType.LogOnly);
		ISrcFactory source = null;
		String uri = e.getUri();
		if (HttpSrc.isSupported(uri)) {
			source = new HttpSrc(uri);
		} else if (HttpsSrc.isSupported(uri)) {
			source = new HttpsSrc(uri);
		}
		if (source == null) {
			return null;
		}

		try {
			IOUtil.download(source, zipFile, monitor, null,
					new IDownloadMonitor() {
						@Override
						public void logDownloaded(String msg) {
							logMsg(monitor, msg, IAgentMonitor.LogType.LogOnly);
						}
					});
		} catch (IOException e1) {
			String msg = "Failed to download update site: " + e.getUri();
			logMsg(monitor, msg, IAgentMonitor.LogType.Both);
			throw AgentPlugin.createException(msg, e1);
		}
		File unzippedFolder = genNewFile(name + "unzipped");
		try {
			FileUtil.unzip(zipFile, unzippedFolder);
			FileUtil.deleteFile(zipFile);
		} catch (IOException e1) {
			String msg = "Failed to unzip update site: " + e.getUri();
			logMsg(monitor, msg, IAgentMonitor.LogType.Both);
			throw AgentPlugin.createException(msg, e1);
		}
		e.setUri(unzippedFolder.toURI().toString());

		return unzippedFolder;
	}

	private File genNewFile(String name) {
		File dir = new File(baseDir, name);
		int index = 0;
		while (dir.exists()) {
			dir = new File(baseDir, name + index);
			index++;
		}
		return dir;
	}

	private void logMsg(IProgressMonitor monitor, String msg,
			IAgentMonitor.LogType type) {
		logMonitor.log(msg);
		if (monitor instanceof IAgentMonitor) {
			((IAgentMonitor) monitor).logAgentMessage(msg, type);
		}
	}

	public static void removeTargetPlatforms() throws CoreException {
		ILaunchConfiguration[] configurations = DebugPlugin.getDefault()
				.getLaunchManager().getLaunchConfigurations();
		for (ILaunchConfiguration cfg : configurations) {
			String platform = cfg.getAttribute(IQ7Launch.TARGET_PLATFORM, "");
			if (platform.length() > 0) {
				TargetPlatformManager.deleteTargetPlatform(platform);
				String configLocation = Q7LaunchUtils
						.getConfigFilesLocation(cfg);
				final File configFolder = new File(configLocation);
				if (configFolder.exists()) {
					FileUtil.deleteFile(configFolder, true);
				}
			}
		}
	}

}
