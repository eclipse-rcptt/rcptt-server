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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.rcptt.logging.IQ7Monitor;
import org.eclipse.rcptt.logging.Q7LoggingManager;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;

/**
 * Manages Q7 cloud agents
 * 
 * @author ivaninozemtsev
 * 
 */
public class AgentRegistry {

	/**
	 * check each 30 seconds
	 */
	private static long monitorTimeout = 30 * 1000; // 30 seconds to monitor
	// hang

	/**
	 * 7 minutes timeout, to return task to queue, if agent is not responding.
	 */
	private static long timeout = 7 * 60 * 1000;

	private final Map<String, AgentInfo> agents = new HashMap<String, AgentInfo>();
	private final Map<String, AgentInfoDetails> agentPings = new HashMap<String, AgentInfoDetails>();
	private ListenerList listeners = new ListenerList();

	private IQ7Monitor agentLog;
	private Thread agentMonitorThread;

	public interface IAgentRegistryListener {
		void added(AgentInfo info);

		void removed(AgentInfo info);

		void agentTimeout(List<AgentInfo> timeouts);

		void timeoutCheck();

		boolean checkCancel(AgentInfo agent);
	}

	public AgentRegistry() {
		super();
		agentLog = Q7LoggingManager.get("agent.registry");
		agentLog.log("######################### initialize", null);
		agentMonitorThread = new Thread(createMonitorRunnable(), "");
		agentMonitorThread.start();

	}

	public static long getTimeout() {
		return timeout;
	}

	public static void setTimeout(long timeout) {
		AgentRegistry.timeout = timeout;
	}

	public static void setMonitorTimeout(long monitorTimeout) {
		synchronized (AgentRegistry.class) {
			AgentRegistry.monitorTimeout = monitorTimeout;
			AgentRegistry.class.notifyAll();
		}
	}

	private Runnable createMonitorRunnable() {
		return new Runnable() {

			public void run() {
				while (true) {
					List<AgentInfo> timeouts = new ArrayList<AgentInfo>();
					synchronized (agents) {
						for (String agentId : agents.keySet()) {
							AgentInfoDetails details = agentPings.get(agentId);
							if (details == null) { // No details yet. Create
								// one, and fill current
								// time.
								details = ServerCommandsFactory.eINSTANCE
										.createAgentInfoDetails();
								details.setTime(System.currentTimeMillis());
								agentPings.put(agentId, details);
							} else {
								// Check last ping is less then agent timeout.
								AgentInfo agentInfo = agents.get(agentId);
								if (isTimeout(agentInfo)) {
									timeouts.add(agentInfo);
									// Update to last one.
									details.setTime(System.currentTimeMillis());
								}
							}
						}
					}
					if (!timeouts.isEmpty()) {
						Object[] objects = listeners.getListeners();
						for (Object object : objects) {
							((IAgentRegistryListener) object)
									.agentTimeout(timeouts);
						}
					}

					Object[] objects = listeners.getListeners();
					for (Object object : objects) {
						((IAgentRegistryListener) object).timeoutCheck();
					}

					for (AgentInfo agentInfo : timeouts) {
						removeAgent(agentInfo);
					}

					synchronized (AgentRegistry.class) {
						try {
							AgentRegistry.class.wait(monitorTimeout);
						} catch (Throwable e) {
						}
					}
				}
			}

		};
	}

	public boolean isTimeout(AgentInfo info) {
		synchronized (agents) {
			AgentInfoDetails details = agentPings.get(getAgentID(info));
			if (details != null) {
				long time = details.getTime();
				long elapsed = System.currentTimeMillis() - time;
				if (elapsed > timeout) {
					return true;
				}
			}
		}
		return false;
	}

	public synchronized void addListener(IAgentRegistryListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeListener(IAgentRegistryListener listener) {
		listeners.remove(listener);
	}

	public AgentInfo getAgent(String uri) {
		synchronized (agents) {
			return agents.get(uri);
		}
	}

	public void addAgent(AgentInfo info) {
		agentLog.log(
				"add_agent: " + info.getUri() + " classifier: "
						+ info.getClassifier() + " launchID: "
						+ info.getLaunchId() + " skipTags: "
						+ Arrays.asList(info.getSkipTags().toArray()), null);
		synchronized (agents) {
			agents.put(getAgentID(info), info);
		}
		Object[] objects = listeners.getListeners();
		for (Object object : objects) {
			((IAgentRegistryListener) object).added(info);
		}
	}

	public static String getAgentID(AgentInfo info) {
		if (info == null) {
			return null;
		}
		return info.getUri();
	}

	public void removeAgent(AgentInfo info) {
		agentLog.log(
				"remove_agent: " + info.getUri() + " classifier: "
						+ info.getClassifier() + " launchID: "
						+ info.getLaunchId() + " skipTags: "
						+ Arrays.asList(info.getSkipTags().toArray()), null);
		synchronized (agents) {
			agents.remove(getAgentID(info));
			agentPings.remove(getAgentID(info));
		}
		Object[] objects = listeners.getListeners();
		for (Object object : objects) {
			((IAgentRegistryListener) object).removed(info);
		}
	}

	public List<AgentInfo> getAgents() {
		synchronized (agents) {
			return new ArrayList<AgentInfo>(this.agents.values());
		}
	}

	public void dispose() {
		agentLog.log("terminate", null);
	}

	/**
	 * Whether can <code>agent</code> execute specified <code>aut</code>
	 * 
	 * @param aut
	 * @param agent
	 * @return
	 */
	public static boolean canExecute(AutInfo aut, AgentInfo agent) {
		if (aut.getClassifier() == null) {
			return true;
		}
		return aut.getClassifier().equals(agent.getClassifier());
	}

	/**
	 * Whether exist agent who can execute specified <code>aut</code>
	 * 
	 * @param aut
	 * @return
	 */
	public boolean canExecute(AutInfo aut) {
		for (AgentInfo agent : getAgents()) {
			if (canExecute(aut, agent)) {
				return true;
			}
		}
		return false;
	}

	public boolean isRegistered(AgentInfo agent) {
		AgentInfo cached = getAgent(getAgentID(agent));
		return cached != null
				&& cached.getLaunchId().equals(agent.getLaunchId());
	}

	public void registerPing(AgentInfo agent, AgentInfoDetails details) {
		synchronized (agents) {
			String id = getAgentID(agent);
			details.setTime(System.currentTimeMillis());
			AgentInfoDetails previous = agentPings.get(id);
			// Calculate uptime shift
			if (previous == null) {
				details.setUptime(0);
			} else {
				details.setUptime(previous.getUptime()
						+ (details.getTime() - previous.getTime()));
			}
			agentPings.put(id, details);
		}
	}

	public AgentInfoDetails getLastPing(AgentInfo agent) {
		synchronized (agents) {
			return agentPings.get(getAgentID(agent));
		}
	}

	/**
	 * Check for cancel for agent.
	 */
	public boolean checkCancel(AgentInfo agent) {
		Object[] objects = listeners.getListeners();
		for (Object object : objects) {
			if (((IAgentRegistryListener) object).checkCancel(agent)) {
				return true;
			}
		}
		return false;
	}
}
