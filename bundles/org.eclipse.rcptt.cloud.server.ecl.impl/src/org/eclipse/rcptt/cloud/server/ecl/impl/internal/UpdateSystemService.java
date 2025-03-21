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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal;

import static org.eclipse.rcptt.cloud.server.ecl.impl.internal.EclServerImplPlugin.PLUGIN_ID;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.cloud.agent.commands.UpdateAgent;
import org.eclipse.rcptt.cloud.common.CommonPlugin;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsFactory;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem;
import org.eclipse.rcptt.cloud.util.RemoteEclClient;
import org.eclipse.rcptt.cloud.util.RemoteEclClient.ExecResult;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.platform.objects.UpdateResult;
import org.eclipse.rcptt.ecl.platform.util.EclPlatformUtil;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public class UpdateSystemService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		UpdateSystem update = (UpdateSystem) command;
		String repoUrl = update.getRepo();
		List<AgentInfo> agents = ServerPlugin.getDefault().getAgentRegistry()
				.getAgents();
		List<UpdateAgentThread> agentThreads = new ArrayList<UpdateAgentThread>();

		for (AgentInfo agent : agents) {
			agentThreads.add(new UpdateAgentThread(agent, repoUrl));
		}

		for (Thread thread : agentThreads) {
			thread.start();
		}

		boolean allDone = false;
		while (!allDone) {
			allDone = true;
			for (UpdateAgentThread thread : agentThreads) {
				if (thread.isAlive()) {
					allDone = false;
					thread.join(100);
				}
			}
		}

		UpdateResult serverUpdated = updateSelf(repoUrl);

		for (UpdateAgentThread thread : agentThreads) {
			if (thread.result != null) {
				context.getOutput().write(
						String.format("%s updated to %s",
								thread.agent.getUri(), thread.result.getTo()));
				restartAgent(thread.client);
				thread.client.close();
			} else if (thread.status != null && !thread.status.isOK()) {
				context.getOutput().write(
						String.format("%s failed: %s", thread.agent.getUri(),
								thread.status.getMessage()));
			} else {
				context.getOutput()
						.write(String.format("%s is up to date",
								thread.agent.getUri()));
			}

		}

		if (serverUpdated != null) {
			context.getOutput()
					.write(String.format("Server updated to %s",
							serverUpdated.getTo()));
			restartSelf();
		} else {
			context.getOutput().write(String.format("Server is up to date"));
		}
		return Status.OK_STATUS;
	}

	private void restartSelf() {
		CommonPlugin.requestShutdown();
	}

	private void restartAgent(RemoteEclClient agent) throws CoreException, InterruptedException {
		agent.execCommand(
				CommonCommandsFactory.eINSTANCE.createRequestShutdown(), 60000,
				new NullProgressMonitor());
	}

	private UpdateResult updateSelf(String repoUrl) throws CoreException {
		EclPlatformUtil.replaceReposotory(URI.create(repoUrl), REPO_NICK);
		UpdateResult[] updates = EclPlatformUtil.update();
		return updates.length == 0 ? null : updates[0];
	}

	private static final String REPO_NICK = "server-product-repository";

	public static RemoteEclClient getClient(AgentInfo agent) {
		URI uri = URI.create(agent.getUri());
		try {
			return new RemoteEclClient(uri.getHost(), uri.getPort());
		} catch (CoreException e) {
			// agentLog.log(
			// "Failed to create agent remote ecl session: "
			// + agent.getUri() + " classifier: "
			// + agent.getClassifier() + " launchID: "
			// + agent.getLaunchId() + " skipTags: "
			// + Arrays.asList(agent.getSkipTags().toArray()), e);
		}
		return null;
	}

	private static final class UpdateAgentThread extends Thread {
		AgentInfo agent;
		RemoteEclClient client;
		String repoUrl;
		UpdateResult result = null;
		IStatus status = null;

		public UpdateAgentThread(AgentInfo agent, String repoUrl)
				throws CoreException {
			this.agent = agent;
			this.client = getClient(agent);
			this.repoUrl = repoUrl;

		}

		@Override
		public void run() {
			try {
				UpdateAgent updateAgent = org.eclipse.rcptt.cloud.agent.commands.CommandsFactory.eINSTANCE
						.createUpdateAgent();
				updateAgent.setRepo(repoUrl);
				ExecResult out = client.execCommand(updateAgent, 5 * 60000, // 5
						// minutes
						// to
						// update
						new NullProgressMonitor());
				if (!out.getStatus().isOK()) {
					this.status = out.getStatus();
					return;
				}

				for (Object obj : out.getPipeContent()) {
					if (obj instanceof UpdateResult) {
						this.result = (UpdateResult) obj;
						break;
					}
				}
			} catch (CoreException e) {
				status = e.getStatus();
			} catch (InterruptedException e) {
				status = new Status(IStatus.ERROR, PLUGIN_ID, "Interrupted", e);
			}
		}
	}
}
