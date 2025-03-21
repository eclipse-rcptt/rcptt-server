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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.Q7LoggingManager;

import org.eclipse.rcptt.cloud.common.CommonPlugin;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.server.ExecutionEntry;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;

public class AddTestResourceService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		AddTestResource cmd = (AddTestResource) command;

		IQ7Monitor monitor = Q7LoggingManager.get("resources");
		if (cmd.getResource() != null) {
			monitor.log("add resource to suite:" + cmd.getSuiteId()
					+ " resource: " + cmd.getResource().getId(), null);
		}

		ExecutionEntry handle = ExecutionRegistry.getInstance().getSuiteHandle(
				cmd.getSuiteId());
		if (handle == null) {
			return new Status(IStatus.ERROR,
					"com.xored.q7.cloud.common.ecl.impl", "Suite "
					+ cmd.getSuiteId() + " not found");
		}

		ITestStore dir = handle.getTestStore();
		if (cmd.getResource() != null) {
			dir.putResource(EcoreUtil.copy(cmd.getResource()));
		} else if (cmd.getArtifactsPath() != null) {
			File artifactName = new File(ExecutionRegistry.getInstance()
					.getRoot().toURI().resolve(URI.create(cmd.getArtifactsPath())));
			try {
				ZipInputStream zin = new ZipInputStream(
						new BufferedInputStream(new FileInputStream(
								artifactName)));
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
					dir.putResource(artifact);
				}
				zin.close();
				// Delete zip artifact
				artifactName.delete();
				// Also remove .md5 file
				File md5File = new File(artifactName.getAbsolutePath() + ".md5");
				if (md5File.exists()) {
					md5File.delete();
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Status.OK_STATUS;
	}
}
