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
 * A representation of the model object '<em><b>Return Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.xored.q7.cloud.server.serverCommands.ReturnTask#getCause <em>Cause</em>}</li>
 *   <li>{@link com.xored.q7.cloud.server.serverCommands.ReturnTask#getReturnCause <em>Return Cause</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.xored.q7.cloud.server.serverCommands.ServerCommandsPackage#getReturnTask()
 * @model
 * @generated
 */
public interface ReturnTask extends AgentCommand {

	/**
	 * Returns the value of the '<em><b>Cause</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cause</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cause</em>' attribute.
	 * @see #setCause(String)
	 * @see com.xored.q7.cloud.server.serverCommands.ServerCommandsPackage#getReturnTask_Cause()
	 * @model
	 * @generated
	 */
	String getCause();

	/**
	 * Sets the value of the '{@link com.xored.q7.cloud.server.serverCommands.ReturnTask#getCause <em>Cause</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cause</em>' attribute.
	 * @see #getCause()
	 * @generated
	 */
	void setCause(String value);

	/**
	 * Returns the value of the '<em><b>Return Cause</b></em>' attribute.
	 * The literals are from the enumeration {@link com.xored.q7.cloud.server.serverCommands.ReturnTaskCause}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Cause</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Cause</em>' attribute.
	 * @see com.xored.q7.cloud.server.serverCommands.ReturnTaskCause
	 * @see #setReturnCause(ReturnTaskCause)
	 * @see com.xored.q7.cloud.server.serverCommands.ServerCommandsPackage#getReturnTask_ReturnCause()
	 * @model
	 * @generated
	 */
	ReturnTaskCause getReturnCause();

	/**
	 * Sets the value of the '{@link com.xored.q7.cloud.server.serverCommands.ReturnTask#getReturnCause <em>Return Cause</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Cause</em>' attribute.
	 * @see com.xored.q7.cloud.server.serverCommands.ReturnTaskCause
	 * @see #getReturnCause()
	 * @generated
	 */
	void setReturnCause(ReturnTaskCause value);

} // ReturnTask
