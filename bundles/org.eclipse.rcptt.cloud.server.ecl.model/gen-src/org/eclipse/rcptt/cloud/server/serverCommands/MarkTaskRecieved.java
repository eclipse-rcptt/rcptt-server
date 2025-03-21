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
 * A representation of the model object '<em><b>Mark Task Recieved</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved#isState <em>State</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getMarkTaskRecieved()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface MarkTaskRecieved extends AgentCommand {
	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see #setState(boolean)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getMarkTaskRecieved_State()
	 * @model
	 * @generated
	 */
	boolean isState();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved#isState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see #isState()
	 * @generated
	 */
	void setState(boolean value);

} // MarkTaskRecieved
