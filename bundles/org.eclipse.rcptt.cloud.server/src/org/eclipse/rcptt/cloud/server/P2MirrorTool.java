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

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.repository.IRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.artifact.IFileArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.rcptt.launching.injection.UpdateSite;
import org.eclipse.rcptt.launching.p2utils.P2Utils;
import org.eclipse.rcptt.launching.p2utils.P2Utils.ILogMonitor;
import org.eclipse.rcptt.launching.p2utils.Q7P2UtilsActivator;
import org.eclipse.rcptt.util.FileUtil;

public class P2MirrorTool {
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean mirror(IProgressMonitor monitor, UpdateSite site,
			File siteRoot, ILogMonitor logMonitor, IProvisioningAgent agent)
			throws CoreException {
		if (agent == null) {
			agent = P2Utils.getProvisioningAgent();
		}

		URI uri = URI.create(site.getUri());

		if (logMonitor != null) {
			logMonitor.log("Load metadata repository");
		}

		IMetadataRepository repository = P2Utils.safeLoadRepository(uri,
				monitor, agent);

		if (logMonitor != null) {
			logMonitor.log("Load artifact repository");
		}

		IArtifactRepository artifactRepository = P2Utils
				.safeLoadArtifactRepository(uri, monitor, agent);
		if (repository == null || artifactRepository == null) {
			if (monitor.isCanceled()) {
				return false;
			}
			Q7P2UtilsActivator.getDefault().log(
					"Failed to load repository from " + uri, null);
			errorMessage = "Failed to load update site:" + uri;
			return false;
		}

		IArtifactRepositoryManager repositoryManager = P2Utils
				.getArtifactRepositoryManager(agent);
		IMetadataRepositoryManager metadataRepositoryManager = P2Utils
				.getRepositoryManager(agent);

		List<IInstallableUnit> unitsToInstall = new ArrayList<IInstallableUnit>();

		// Query for all entries and then choose required.
		IQuery<IInstallableUnit> finalQuery = P2Utils.createQuery(site);

		if (logMonitor != null) {
			logMonitor.log("Query for required features");
		}
		IQueryResult<IInstallableUnit> result = repository.query(finalQuery,
				monitor);
		Set<IInstallableUnit> availableUnits = result.toSet();
		if (logMonitor != null) {
			logMonitor.log("Found: " + availableUnits.size()
					+ " units to download.");
		}

		// One more query for categories

		if (site.isAllUnits()) {
			unitsToInstall.addAll(availableUnits);
		} else {
			// Include dependencies
			if (logMonitor != null) {
				logMonitor.log("Expand features");
			}
			P2Utils.expandFeatures(monitor, repository, availableUnits);
			if (logMonitor != null) {
				logMonitor.log("Found: " + availableUnits.size()
						+ " units to downlaod after expansion.");
			}

			Set<String> unitIDs = P2Utils.mapUnitsToId(availableUnits);
			Set<String> missingUnits = new HashSet<String>(site.getUnits());
			missingUnits.removeAll(unitIDs);
			if (!missingUnits.isEmpty()) { // Some units are not available
				errorMessage = "Few units are not available:"
						+ Arrays.toString(missingUnits.toArray());
				return false;
			}
			unitsToInstall.addAll(availableUnits);
		}

		// Lets mirror all required artifacts into bundle pool, since we don't
		// really trust P2.
		siteRoot.mkdirs();
		if (logMonitor != null) {
			logMonitor.log("Mirroring repository to local folder: "
					+ siteRoot.getAbsolutePath());
		}
		try {

			metadataRepositoryManager.removeRepository(siteRoot.toURI());
			repositoryManager.removeRepository(siteRoot.toURI());

			String repoName = "q7.p2.update.site_" + System.currentTimeMillis();
			IFileArtifactRepository filesRepository = (IFileArtifactRepository) repositoryManager
					.createRepository(siteRoot.toURI(), repoName,
							IArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY,
							null);

			IMetadataRepository metadataRepository = metadataRepositoryManager
					.createRepository(siteRoot.toURI(), repoName,
							IMetadataRepositoryManager.TYPE_SIMPLE_REPOSITORY,
							null);

			metadataRepository.setProperty(IRepository.PROP_COMPRESSED, "true");
			filesRepository.setProperty(IRepository.PROP_COMPRESSED, "true");
			filesRepository.setProperty(IArtifactRepository.PROP_COMPRESSED,
					"true");

			List<IInstallableUnit> toInstall = new ArrayList<IInstallableUnit>();
			toInstall.addAll(unitsToInstall);

			if (logMonitor != null) {
				logMonitor.log("Downloading artifacts");
			}
			long start = System.currentTimeMillis();
			P2Utils.installUnits(monitor, artifactRepository, filesRepository,
					toInstall, 10, logMonitor, true, agent);
			long end = System.currentTimeMillis();

			if (logMonitor != null) {
				logMonitor.log("Save metadata");
			}
			// Mirror required metadata
			metadataRepository.addInstallableUnits(unitsToInstall);

			if (toInstall.size() > 0) {
				// Not all plugins are installed.
				errorMessage = "Failed to install next units from repository: "
						+ repository.getName();
				for (IInstallableUnit u : toInstall) {
					errorMessage += "\n" + u.getId();
				}

				return false;
			}
			final long totalSize[] = { 0 };

			FileUtil.traverse(siteRoot, siteRoot,
					new FileUtil.ITraverseRunnable() {
						@Override
						public boolean accept(File file, String name) {
							if (file.isFile()) {
								totalSize[0] += file.length();
							}
							return true;
						}
					});

			if (logMonitor != null) {
				logMonitor.log("Mirror statistics:");
				logMonitor.log("-- Downloaded size: " + totalSize[0]);
				logMonitor.log("-- Downloaded time: " + (end - start));
				logMonitor.log("-- Download rate: "
						+ calculateRate(start, end, totalSize[0]) + "kb/sec");

			}
			repositoryManager.removeRepository(siteRoot.toURI());
			metadataRepositoryManager.removeRepository(siteRoot.toURI());
		} catch (Exception e) {
			Q7P2UtilsActivator.getDefault().log(e);
			errorMessage = e.getMessage();
			return false;
		}

		return true;
	}

	private long calculateRate(long start, long end, long length) {
		try {
			long rate = -1;
			if (length > 0) {
				long time = (end - start);
				long t = (time / 1000);
				if (t == 0) {
					t = 1;
				}
				rate = (length / 1024) / t;
			}
			return rate;
		} catch (ArithmeticException e) {
			// ignore
		}
		return 1;
	}

	public boolean mirrorTry(final IProgressMonitor monitor,
			final UpdateSite donwloadSite, final File file,
			ILogMonitor logMonitor, IProvisioningAgent agent, int tryCount,
			int delay) throws CoreException {
		CoreException lastException = null;
		for (int i = 0; i < tryCount; i++) {
			try {
				if (mirror(monitor, donwloadSite, file, logMonitor, agent)) {
					return true;
				}
				if (errorMessage != null) {
					logMonitor.log("Failed to mirror repository: "
							+ donwloadSite.getUri() + ": "
							+ errorMessage);
					lastException = new CoreException(
							Q7P2UtilsActivator.status(errorMessage, null));
				}
			} catch (CoreException e) {
				lastException = e;
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				if (lastException != null) {
					throw lastException;
				}
			}
		}
		if (lastException != null) {
			throw lastException;
		}
		return false;
	}
}
