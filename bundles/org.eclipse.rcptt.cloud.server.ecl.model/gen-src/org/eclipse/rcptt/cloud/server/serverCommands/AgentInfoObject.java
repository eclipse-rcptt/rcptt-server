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

import org.eclipse.rcptt.cloud.model.AgentInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent Info Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoObject()
 * @model
 * @generated
 */
public interface AgentInfoObject extends AgentInfo, AgentInfoDetails {

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoObject_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);
} // AgentInfoObject
