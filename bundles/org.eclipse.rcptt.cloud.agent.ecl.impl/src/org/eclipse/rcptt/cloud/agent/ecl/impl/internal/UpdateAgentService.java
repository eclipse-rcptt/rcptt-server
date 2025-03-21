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
package org.eclipse.rcptt.cloud.agent.ecl.impl.internal;

import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.platform.objects.Repository;
import org.eclipse.rcptt.ecl.platform.objects.UpdateResult;
import org.eclipse.rcptt.ecl.platform.util.EclPlatformUtil;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

import org.eclipse.rcptt.cloud.agent.commands.UpdateAgent;
import org.eclipse.rcptt.cloud.util.StatusWriter;

public class UpdateAgentService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		try {
			String repo = ((UpdateAgent) command).getRepo();
			Repository[] repositories = EclPlatformUtil.listRepositories();
			for (Repository repository : repositories) {
				EclPlatformUtil.removeRepository(URI.create(repository
						.getLocation()));
			}
			EclPlatformUtil.replaceReposotory(URI.create(repo), REPO_NICK);
			EclPlatformUtil.cleanOldProfiles();
			for (UpdateResult update : EclPlatformUtil.update()) {
				context.getOutput().write(update);
			}
			context.getOutput().close(Status.OK_STATUS);
			return Status.OK_STATUS;
		} catch (CoreException cex) {
			IStatus internal = cex.getStatus();
			String msg = new StatusWriter(internal).toString();
			IStatus result = new Status(IStatus.ERROR,
					"com.xored.q7.cloud.agent.ecl.impl", msg);

			context.getOutput().close(result);
			return result;
		}
	}

	private static final String REPO_NICK = "agent-product-repository";

}
