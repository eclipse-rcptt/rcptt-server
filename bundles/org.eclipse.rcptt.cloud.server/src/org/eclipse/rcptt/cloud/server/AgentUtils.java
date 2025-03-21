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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.rcptt.logging.StatusListener;
import org.eclipse.rcptt.util.FileUtil;

import org.eclipse.rcptt.cloud.model.AgentInfo;

public class AgentUtils {
	public static Map<String, Integer> getAgentByClassifier(
			List<AgentInfo> agents) {
		Map<String, Integer> byClassifier = new HashMap<String, Integer>();
		for (AgentInfo agentInfo : agents) {
			Integer value = byClassifier.get(agentInfo.getClassifier());
			if (value == null) {
				value = Integer.valueOf(1);
			} else {
				value = Integer.valueOf(1 + value.intValue());
			}
			byClassifier.put(agentInfo.getClassifier(), value);
		}
		return byClassifier;
	}

	public static String getMostUsedClassifier(List<AgentInfo> agents) {
		Map<String, Integer> agentByClassifier = getAgentByClassifier(agents);
		int maxValue = -1;
		String classifier = null;
		for (Map.Entry<String, Integer> e : agentByClassifier.entrySet()) {
			if (maxValue < e.getValue().intValue()) {
				classifier = e.getKey();
				maxValue = e.getValue().intValue();
			}
		}
		return classifier;
	}

	static StatusListener getAgentLog(AgentInfo agent, String suiteId) {
		ExecutionEntry suiteHandle = ExecutionRegistry.getInstance()
				.getSuiteHandle(suiteId);
		String agentId = FileUtil.getID(AgentRegistry.getAgentID(agent));
		StatusListener monitor = suiteHandle.getMonitor(agentId + ".log");
		if (monitor == null)
			throw new NullPointerException();
		return monitor;
	}
}
