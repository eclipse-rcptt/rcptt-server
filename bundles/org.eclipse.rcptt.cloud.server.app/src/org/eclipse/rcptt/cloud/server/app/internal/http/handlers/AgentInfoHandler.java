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
package org.eclipse.rcptt.cloud.server.app.internal.http.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Function;
import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.server.AgentRegistry;
import org.eclipse.rcptt.cloud.server.ServerPlugin;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpUtils;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.EclServerImplPlugin;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskQueue;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.AgentStats;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails;

public class AgentInfoHandler extends Q7AbstractHandler {
	public static final String URI = "/agents";

	public boolean handle(Request request, Response response, Callback callback)
			throws IOException {
		AgentRegistry reg = ServerPlugin.getDefault().getAgentRegistry();
		final List<AgentInfo> agents = new ArrayList<AgentInfo>(reg.getAgents());
		Collections.sort(agents, SORTER);

		String agentsContent = Q7HttpUtils
				.getResource("/webroot/templates/agents.xml");

		final StringBuilder buffer = new StringBuilder();

		buffer.append("<div id=\"content\"><div id=\"tableContainer\"><table>");
		buffer.append("<tr class=\"th\">");
		buffer.append("<th>ID</th>");
		buffer.append("<th>OS</th>");
		buffer.append("<th>Started</th>");
		buffer.append("<th>Host</th>");
		buffer.append("<th>Disk Space</th>");
		buffer.append("<th>SkipTags</th>");
		buffer.append("<th>Tests count</th>");
		buffer.append("<th>Fail count</th>");
		buffer.append("<th>Status</th>");
		buffer.append("<th>Current suite</th>");
		buffer.append("</tr>");
		int id = 1;

		ISMHandleStore<AgentStats> agentStore = ISMCore.getInstance()
				.getAgentStore();
		final Set<String> existing = new HashSet<String>();
		for (AgentInfo agent : agents) {
			existing.add(agent.getUri());
		}
		// Add not alive agents
		List<ISMHandle<AgentStats>> handles = agentStore.getHandles();
		for (ISMHandle<AgentStats> ismHandle : handles) {
			ismHandle.apply(new Function<AgentStats, Void>() {
				public Void apply(AgentStats stats) {
					if (existing.add(stats.getUri())) {
						AgentInfo newAgent = ModelFactory.eINSTANCE
								.createAgentInfo();
						newAgent.setClassifier(stats.getClassifier());
						newAgent.setUri(stats.getUri());
						if (stats.getSkipTags() != null) {
							newAgent.getSkipTags().add(stats.getSkipTags());
						}
						newAgent.setLaunchId(stats.getLaunchID());
						agents.add(newAgent);
					}

					return null;
				}
			});
		}

		TaskQueue queue = EclServerImplPlugin.getDefault().getTaskQueue();

		for (final AgentInfo agent : agents) {
			buffer.append("<tr>");
			buffer.append("<td>" + Integer.toString(id) + "</td>");
			buffer.append("<td>" + getHumanOS(agent) + "</td>");
			buffer.append("<td>" + agent.getLaunchId() + "</td>");
			buffer.append("<td>" + agent.getUri() + "</td>");
			boolean alive = false;
			AgentInfoDetails lastPing = reg.getLastPing(agent);
			// PreScheduleResult preSchedule = reg.preSchedule(agent);
			if (lastPing != null) {
				buffer.append("<td>" + lastPing.getFreeDiskSpace() + "</td>");
				alive = true;
			} else {
				buffer.append("<td>" + "Unknown" + "</td>");
			}
			EList<String> skipTags = agent.getSkipTags();
			StringBuilder b = new StringBuilder();
			for (String tag : skipTags) {
				if (b.length() > 0) {
					b.append(", ");
				}
				b.append(tag);
			}
			if (b.length() == 0) {
				b.append("[empty]");
			}
			buffer.append("<td>" + b.toString() + "</td>");
			ISMHandle<AgentStats> handle = agentStore.getHandle(FileUtil
					.getID(agent.getUri()));
			handle.commit(new Function<AgentStats, Void>() {
				public Void apply(AgentStats stats) {
					buffer.append("<td>" + stats.getTotalCount() + "</td>");
					buffer.append("<td>" + stats.getFailedCount() + "</td>");
					if (stats.getUri() == null) {
						stats.setUri(agent.getUri());
					}
					if (stats.getClassifier() == null) {
						stats.setClassifier(agent.getClassifier());
					}
					if (stats.getSkipTags() == null) {
						stats.setSkipTags(Arrays
								.toString(agent.getSkipTags().toArray(
										new String[agent.getSkipTags().size()])));
					}
					if (stats.getLaunchID() == null) {
						stats.setLaunchID(agent.getLaunchId());
					}
					return null;
				}
			});
			buffer.append("<td>" + (alive ? "Running" : "Not available")
					+ "</td>");

			// Current suite
			buffer.append("<td>" + queue.getAgentSuite(agent) + "</td>");
			id++;
			buffer.append("</tr>");
		}
		buffer.append("</table></div></div>");

		writeMenuAndContent(response, agentsContent, buffer);
		callback.succeeded();
		return true;
	}

	private String getHumanOS(AgentInfo agent) {
		String cl = agent.getClassifier();
		if (cl == null) {
			return "Unknown";
		}
		Map<String, String> humanNames = new HashMap<String, String>();

		humanNames.put("win32.win32.x86", "Windows 32bit");
		humanNames.put("win32.win32.x86_64", "Windows 64bit");
		humanNames.put("linux.gtk.x86", "Linux  32bit");
		humanNames.put("linux.gtk.x86_64", "Linux 64bit");
		humanNames.put("macosx.cocoa.x86", "MacOS 32bit");
		humanNames.put("macosc.cocoa.x86_64", "MacOS 64bit");
		humanNames.put("macosc.cocoa.aarch64", "MacOS Apple Silicon");
		if (humanNames.containsKey(cl)) {
			return humanNames.get(cl);
		}
		return "OS with arch: " + cl;
	}

	private static Comparator<AgentInfo> SORTER = new Comparator<AgentInfo>() {

		public int compare(AgentInfo o1, AgentInfo o2) {
			return o1.getUri().compareTo(o2.getUri());
		}
	};
}
