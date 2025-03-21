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
package org.eclipse.rcptt.cloud.server.serverCommands;

import org.eclipse.rcptt.ecl.core.Command;

import org.eclipse.rcptt.cloud.model.AgentInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand#getAgent <em>Agent</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentCommand()
 * @model abstract="true"
 * @generated
 */
public interface AgentCommand extends Command {
	/**
	 * Returns the value of the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agent</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agent</em>' containment reference.
	 * @see #setAgent(AgentInfo)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentCommand_Agent()
	 * @model containment="true"
	 * @generated
	 */
	AgentInfo getAgent();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand#getAgent <em>Agent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Agent</em>' containment reference.
	 * @see #getAgent()
	 * @generated
	 */
	void setAgent(AgentInfo value);

} // AgentCommand
