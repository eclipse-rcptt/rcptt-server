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
package org.eclipse.rcptt.cloud.server;

import static org.eclipse.rcptt.cloud.server.ServerPlugin.PLUGIN_ID;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.internal.p2.core.ProvisioningAgent;
import org.eclipse.equinox.internal.p2.repository.Transport;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.common.TestSuiteDirectory;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.server.PrepareTaskQueue.IPrepareRunnable;
import org.eclipse.rcptt.cloud.server.internal.providers.AutDownloadManager;
import org.eclipse.rcptt.cloud.server.internal.providers.IServerAutProvider;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.cloud.util.IOUtil.IDownloadMonitor;
import org.eclipse.rcptt.launching.injection.Entry;
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;
import org.eclipse.rcptt.launching.injection.UpdateSite;
import org.eclipse.rcptt.launching.p2utils.P2Utils;
import org.eclipse.rcptt.logging.FileQ7Monitor;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.IQ7Monitor.IQ7LogListener;
import org.eclipse.rcptt.util.Base64;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.io.Closeables;



public class ExecutionEntry {
	public static final String DOWNLOAD_MONITOR = "download";
	public static final String CLIENT_EXECUTION_MONITOR = "client_messages";
	public static final String EXECUTION_MONITOR = "execution";
	public static final String ERROR_MONITOR = "errors";
	private final TestSuiteDirectory suiteDir;
	private Map<ExecutionEntry.MonitorMetaInfo, IQ7Monitor> monitors = new HashMap<ExecutionEntry.MonitorMetaInfo, IQ7Monitor>();
	private final String suiteId;
	public final Instant created = Instant.now();
	private final ISMHandle<Execution> handle;

	private Object profiler;
	private final PrepareTaskQueue pendingQueue = new PrepareTaskQueue();

	// Map contain on server location -> file location mapping for auts and
	// update sites.
	private Map<URI, File> onServerPathToFile = new HashMap<URI, File>();

	public static ExecutionEntry create(String suiteId, ISMHandle<Execution> handle) throws CoreException {
		File testsFolder = getArtifactByName(handle, "tests");
		testsFolder.mkdirs();
		return new ExecutionEntry(suiteId, handle, new TestSuiteDirectory(testsFolder, true));
	}

	public ExecutionEntry(String suiteId, ISMHandle<Execution> handle, TestSuiteDirectory testSuiteDirectory)
			throws CoreException {
		this.suiteId = suiteId;
		this.handle = handle;
		this.suiteDir = testSuiteDirectory;
	}

	private PrepareTaskQueue getPrepareQueue() {
		return pendingQueue;
	}

	private static class MonitorMetaInfo {
		public MonitorMetaInfo(String prefix, String postfix) {
			this.prefix = prefix;
			this.postfix = postfix;
		}

		String prefix;
		String postfix;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((postfix == null) ? 0 : postfix.hashCode());
			result = prime * result
					+ ((prefix == null) ? 0 : prefix.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ExecutionEntry.MonitorMetaInfo other = (ExecutionEntry.MonitorMetaInfo) obj;
			if (postfix == null) {
				if (other.postfix != null) {
					return false;
				}
			} else if (!postfix.equals(other.postfix)) {
				return false;
			}
			if (prefix == null) {
				if (other.prefix != null) {
					return false;
				}
			} else if (!prefix.equals(other.prefix)) {
				return false;
			}
			return true;
		}

	}

	public String getSuiteId() {
		return suiteId + "-" + handle.getFileName();
	}

	public static File getArtifactByName(ISMHandle<Execution> handle,
			final String fileName) {
		handle.commit(new Function<Execution, Void>() {
			@Override
            public Void apply(Execution input) {
				input.getAutArtifacts().add(fileName);
				return null;
			}
		});
		return new File(handle.getFileRoot(), fileName);
	}

	public File getArtifactName(final String fileName) {
		return getArtifactByName(handle, fileName);
	}

	public File getMetadataName(final String fileName) {
		handle.commit(new Function<Execution, Void>() {
			@Override
            public Void apply(Execution input) {
				input.getMetadataArtifacts().add(fileName);
				return null;
			}
		});
		return new File(handle.getFileRoot(), fileName);
	}

	public IQ7Monitor getMonitor(String monitorName) {
		ExecutionEntry.MonitorMetaInfo mon = new MonitorMetaInfo(monitorName
				+ "_", ".log");
		IQ7Monitor monitor = monitors.get(mon);
		if (monitor != null) {
			return monitor;
		}
		monitor = new FileQ7Monitor(new File(handle.getFileRoot(), monitorName));
		monitors.put(mon, monitor);

		return monitor;
	}

	public void applyMonitorFiles() {
		handle.commit(new Function<Execution, Void>() {
			@Override
            public Void apply(Execution input) {
				File file = handle.getFileRoot();
				File[] files = file.listFiles();
				for (File f : files) {
					for (ExecutionEntry.MonitorMetaInfo info : monitors
							.keySet()) {
						String name = f.getName();
						if (name.startsWith(info.prefix)
								&& (name.endsWith(info.postfix) || name
										.endsWith(info.postfix + ".lck"))) {
							if (!input.getAutArtifacts().contains(name)) {
								input.getAutArtifacts().add(name);
							}
						}
					}
				}
				return null;
			}
		});

		for (IQ7Monitor monitor : monitors.values()) {
			monitor.close();
		}
	}

	public ISMHandle<SuiteStats> getSuite() {
		return ISMCore.getInstance().getSuiteStore().getHandle(suiteId);
	}

	public ITestStore getTestStore() {
		return suiteDir;
	}

	private int updateSiteIndex = 0;
	private List<String> messages = Collections.synchronizedList(new ArrayList<>());

	private static IStatus createError(String message) {
		return new Status(IStatus.ERROR, PLUGIN_ID, message);
	}

	private static void throwError(String message) throws CoreException {
		throw new CoreException(createError(message));
	}

	public AutInfo addAutForDownload(final AutInfo info) throws CoreException {
		final IQ7Monitor downloadMonitor = getMonitor(DOWNLOAD_MONITOR);
		try {

			if (!info.getUri().startsWith("server://")) {
				// Only Download if not already on server.
				final IServerAutProvider provider = AutDownloadManager
						.getInstance().getProvider(info);

				if (provider == null) {
					String message = "No AUT download provider is registered for AUT URI: "
							+ info.getUri();
					throwError(message);
				}

				// Update classifier to most used Agent, if not specified.
				if (info.getClassifier() == null) {
					/*
					 * TODO: Discuss how it should work in such situations.
					 *
					 * Right now will take classifiers from most agents, and
					 * assign it to aut.
					 *
					 * In Theory it should check for available auts by url, if
					 * classifier is used. Probable some execution options are
					 * required here.
					 */
					String classifier = AgentUtils
							.getMostUsedClassifier(ServerPlugin.getDefault()
									.getAgentRegistry().getAgents());
					if (classifier == null) {
						String message = "Failed to specify AUT classifier, seems there is no agents registered.";
						throwError(message);
					}
					info.setClassifier(classifier);
				}

				// Begin AUT download action.
				final File autFile = provider.getAutFile(this, info,
						info.getClassifier());

				handle.commit(new Function<Execution, Void>() {
					@Override
                    public Void apply(Execution input) {
						input.getAutNames().add(autFile.getName());
						return null;
					}
				});

				final AutInfo originalInfo = EcoreUtil.copy(info);

				getPrepareQueue().addTask(new IPrepareRunnable() {

					@Override
                    public void run() throws Exception {
						String msg = "Begin AUT download to " + autFile;
						downloadMonitor.log(msg);
						addMessage(
								Thread.currentThread().getName(), msg);

						final IQ7Monitor log = getMonitor(ExecutionEntry.DOWNLOAD_MONITOR
								+ "_" + FileUtil.getID(autFile.getName()));

						log.addListener(new IQ7LogListener() {

							@Override
                            public void added(String message) {
								addMessage(
										Thread.currentThread().getName(),
										message);
							}
						});
						provider.download(ExecutionEntry.this, originalInfo,
								originalInfo.getClassifier(), autFile,
								new NullProgressMonitor(), log);
						downloadMonitor.log("AUT download is complete.");
					}
				}, "Download AUT");

				// Add update site mirroring process.

				info.setUri(getOnServerPath(autFile));

			}

			InjectionConfiguration injection = info.getInjection();
			// Process update sites
			if (injection == null) {
				return info;
			}
			EList<Entry> list = injection
					.getEntries();
			Iterator<Entry> iterator = list
					.iterator();
			while (iterator.hasNext()) {
				final Entry next = iterator
						.next();
				if (next instanceof UpdateSite) {
					UpdateSite site = (UpdateSite) next;
					if (site.getUri().startsWith("server://")) {
						// continue, already on server
						continue;
					}
					if (site.getUri().equals("null"))
                    {
                        throw new IllegalArgumentException(
								"Invalid injection update site");
                    }
					final P2MirrorTool tool = new P2MirrorTool();
					final URI uri = URI.create(site.getUri());
					IPath path = new Path(uri.getPath());

					if (path.segmentCount() > 2) { // Reduce to 2 last segments
						path = path
								.removeFirstSegments(path.segmentCount() - 2);
					}

					String siteName = FileUtil.getID("updatesite_"
							+ updateSiteIndex + "_" + path.toString());// +
																		// ".zip";
					updateSiteIndex++;

					final File file = getArtifactName(siteName);
					if (file.exists()) {
						file.delete();
					}

					final UpdateSite donwloadSite = EcoreUtil.copy(site);
					site.setUri(getOnServerPath(file));

					getPrepareQueue().addTask(new IPrepareRunnable() {

						@Override
                        public void run() throws Exception {
							doUpdateSiteMirroring(downloadMonitor, tool, file,
									donwloadSite);
						}
					}, "Mirror update site:" + donwloadSite.getUri());
				}
			}

			return info;
		} catch (Exception e) {
			downloadMonitor.log(e.getMessage(), e);
			// terminate all pretend tasks
			getPrepareQueue().close();
			Throwables.throwIfInstanceOf(e, CoreException.class);
			throw new CoreException(Status.error(e.getMessage(), e));
		}
	}

	private String getOnServerPath(final File file) {
		return "server://" + ServerPlugin.getDefault().getServerName() + ":"
				+ ServerPlugin.getDefault().getServerHttpPort() + "/artifacts/"
				+ ExecutionRegistry.getInstance().makeRelativePath(file);
	}

	public Object getProfiler() {
		return profiler;
	}

	public void setProfiler(Object profiler) {
		this.profiler = profiler;
	}

	private static Set<String> mirroringQueue = new HashSet<String>();

	private void doUpdateSiteMirroring(final IQ7Monitor downloadMonitor,
			final P2MirrorTool tool, final File file,
			final UpdateSite downloadSite) throws CoreException {
		synchronized (mirroringQueue) {
			while (mirroringQueue.contains(downloadSite.getUri())) {
				try {
					mirroringQueue.wait(100);
				} catch (InterruptedException e) {
					return;
				}
			}
			mirroringQueue.add(downloadSite.getUri());
		}
		try {
			String msg = "Begin Mirroring: " + downloadSite.getUri();
			downloadMonitor.log(msg);
			addMessage(Thread.currentThread().getName(), msg);

			final IQ7Monitor log = getMonitor(ExecutionEntry.DOWNLOAD_MONITOR
					+ "_" + FileUtil.getID(file.getName()));
			final P2Utils.ILogMonitor logMonitor = new P2Utils.ILogMonitor() {
				@Override
                public void log(String message) {
					log.log("\t--" + message);
					addMessage(
							Thread.currentThread().getName(), message);
				}
			};

			logMonitor.log("Mirroring repository: " + downloadSite.getUri());

			// File tempFolder = new File(file.getAbsolutePath()
			// + "_temporaty");
			final Transport transport = new Q7Transport(new IDownloadMonitor() {

				@Override
                public void logDownloaded(String msg) {
					log.log("\t--" + msg);
					addMessage(
							Thread.currentThread().getName(), msg);
				}
			});

			final IProvisioningAgent agent = P2Utils.getProvisioningAgent();
			Object oldService = null;
			synchronized (agent) {
				try {
					oldService = agent.getService(Transport.SERVICE_NAME);
					((ProvisioningAgent) agent).registerService(
							Transport.SERVICE_NAME, transport);
					if (!tool.mirrorTry(new NullProgressMonitor() {
						@Override
						public boolean isCanceled() {
							return Thread.currentThread().isInterrupted();
						}
					}, downloadSite, file, logMonitor, agent, 10, 5000)) {
						log.log("\t--" + "Failed to mirror update site: "
								+ tool.getErrorMessage());
						addMessage(
								Thread.currentThread().getName(),
								"Failed to mirror update site: "
										+ downloadSite.getUri() + ": "
										+ tool.getErrorMessage());
						throw new CoreException(ServerPlugin.createErr(tool
								.getErrorMessage()));
					}
				} finally {
					((ProvisioningAgent) agent).registerService(
							Transport.SERVICE_NAME, oldService);
				}
			}
		} finally {
			synchronized (mirroringQueue) {
				mirroringQueue.remove(downloadSite.getUri());
				mirroringQueue.notifyAll();
			}
		}
	}

	public URI recieveAUT(InputStream stream, String fileName, String unZip)
			throws IOException {
		if (stream != null) {
			File outputFile = getArtifactName(fileName);
			File outputMD5File = getArtifactName(fileName + ".md5");
            boolean threw = true;
			if (outputFile.exists()) {
				FileUtil.deleteFile(outputFile);
				// outputFile.delete();
			}
			if ("true".equals(unZip)) {
				FileUtil.unzipToFolder(stream, outputFile);
			} else {
				BufferedOutputStream outStream = new BufferedOutputStream(
						new FileOutputStream(outputFile));
                MessageDigest md = null;
				try {

                    try
                    {
                        md = MessageDigest.getInstance("md5");
                    }
                    catch (NoSuchAlgorithmException e)
                    {
                        // ignore.
                    }
                    if (md != null)
                    {
                        md.reset();
                        FileUtil.copyNoClose(stream, outStream, md);
                    }
                    else
                    {
                        FileUtil.copyNoClose(stream, outStream);
                    }
                    threw = false;
				}
                finally
                {
                    Closeables.close(outStream, false);
				}
                threw = true;
				// Write also a md5 file
				BufferedOutputStream md5File = new BufferedOutputStream(
						new FileOutputStream(outputMD5File));
                try
                {
                    if (md == null)
                    {
                        md5File.write(("md5:" + System.currentTimeMillis()).getBytes());
                    }
                    else
                    {
                        md5File.write(Base64.encode(md.digest()).getBytes());
                    }
                    threw = false;
                }
                finally
                {
                    Closeables.close(md5File, false);
                }

                threw = true;
			}

			URI onServerPath = ExecutionRegistry.getInstance()
					.makeRelativePath(outputFile);
			onServerPathToFile.put(onServerPath, outputFile);
			return onServerPath;
		}
		return null;
	}

	private File findOnServerFile(URI uri) {
		return onServerPathToFile.get(uri);
	}

	public static String getAutNameFromUri(String uri, String defaultValue) {
		try {
			if (uri != null) {
				int index = uri.lastIndexOf("/");
				if (index != -1) {
					return uri.substring(index + 1);
				}
			}
		} catch (Exception e) {
			// ignore
		}
		return defaultValue;
	}

	public boolean waitForTasks(BooleanSupplier isCancelled, Duration timeout) throws Exception {
		return getPrepareQueue().waitForTasks(isCancelled, timeout);
	}

	private void addMessage(String name, String message) {
		messages.add(message + ": " + message);
	}

	public List<String> drainMessages() {
		synchronized (messages) {
			return List.copyOf(messages);
		}
	}

	public void updateStatistics(Consumer<Execution> update) {
		handle.commit(execution -> {
			update.accept(execution);
			return null;
		});
		
	}
}
