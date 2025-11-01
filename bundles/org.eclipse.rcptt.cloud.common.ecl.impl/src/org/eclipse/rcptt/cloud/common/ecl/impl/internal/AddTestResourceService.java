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
package org.eclipse.rcptt.cloud.common.ecl.impl.internal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.cloud.common.CommonPlugin;
import org.eclipse.rcptt.cloud.common.Hash;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.util.EmfResourceUtil;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.Q7LoggingManager;

public class AddTestResourceService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		AddTestResource cmd = (AddTestResource) command;

		IQ7Monitor monitor = Q7LoggingManager.get("resources");
		if (cmd.getResource() != null) {
			monitor.log("add resource to suite:" + cmd.getSuiteId()
					+ " resource: " + cmd.getResource().getId(), null);
		}

		ExecutionRegistry executions = ExecutionRegistry.getInstance(context);
		ExecutionEntry handle = executions.getSuiteHandle(
				cmd.getSuiteId());
		if (handle == null) {
			return new Status(IStatus.ERROR,
					"com.xored.q7.cloud.common.ecl.impl", "Suite "
					+ cmd.getSuiteId() + " not found");
		}

		try {
			if (cmd.getResource() != null) {
				try (InputStream is = EmfResourceUtil.toInputStream(cmd.getResource().getContent())) {
					handle.addArtifact(is);
				}
			} else if (cmd.getArtifactsPath() != null) {
				File artifactName = new File(executions
					.getRoot().toURI().resolve(URI.create(cmd.getArtifactsPath())));
				try (ZipInputStream zin = new ZipInputStream(
						new BufferedInputStream(new FileInputStream(
								artifactName)))) {
					while (true) {
						ZipEntry entry = zin.getNextEntry();
						if (entry == null) {
							break;
						}
						ECLBinaryResourceImpl res = new ECLBinaryResourceImpl();
						try {
							res.load(zin, null);
						} catch (Exception e) {
							CommonPlugin.error(
									"Cannot load resource from " + entry.getName(),
									e);
							continue;
						}
						Q7Artifact artifact = (Q7Artifact) res.getContents().get(0);
						try (InputStream inputStream = EmfResourceUtil.toInputStream(artifact.getContent())) {
							handle.addArtifact(inputStream);
						} catch (Exception e) {
							StringBuilder message = new StringBuilder();
							message.append("Failed to store artifact ").append(artifact.getId())
							.append(", hash: ").append(Hash.hash(artifact.getContent()))
							.append(", from entry ").append(entry.getName())
							.append("\n");
							final String dump = artifact.getContent().toString();
							message.append(dump.substring(0, Math.min(10000, dump.length())));
							throw new IOException(message.toString(), e);
						}
					}
				}
				// Delete zip artifact
				artifactName.delete();
				// Also remove .md5 file
				File md5File = new File(artifactName.getAbsolutePath() + ".md5");
				if (md5File.exists()) {
					md5File.delete();
				}

		}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return Status.OK_STATUS;
	}
}
