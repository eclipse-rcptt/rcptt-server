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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent Ping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentPing#getDetails <em>Details</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentPing()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface AgentPing extends AgentCommand {

	/**
	 * Returns the value of the '<em><b>Details</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Details</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Details</em>' containment reference.
	 * @see #setDetails(AgentInfoDetails)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentPing_Details()
	 * @model containment="true"
	 * @generated
	 */
	AgentInfoDetails getDetails();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentPing#getDetails <em>Details</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Details</em>' containment reference.
	 * @see #getDetails()
	 * @generated
	 */
	void setDetails(AgentInfoDetails value);

} // AgentPing
