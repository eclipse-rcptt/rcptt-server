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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal.execution;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.flow.TaskDescriptor;

public interface IExecutionProfilerMonitor {
	void onExecute(TaskDescriptor descr, AgentInfo agent);

	void onCancel(TaskDescriptor taskDescriptor, AgentInfo agent);

	void onComplete(TaskDescriptor taskDescriptor, AgentInfo agent);
}
