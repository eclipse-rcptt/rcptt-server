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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Register Agent Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpPort <em>Http Port</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpServer <em>Http Server</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getRegisterAgentResult()
 * @model
 * @generated
 */
public interface RegisterAgentResult extends EObject {
	/**
	 * Returns the value of the '<em><b>Http Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Port</em>' attribute.
	 * @see #setHttpPort(int)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getRegisterAgentResult_HttpPort()
	 * @model
	 * @generated
	 */
	int getHttpPort();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpPort <em>Http Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Port</em>' attribute.
	 * @see #getHttpPort()
	 * @generated
	 */
	void setHttpPort(int value);

	/**
	 * Returns the value of the '<em><b>Http Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Server</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Server</em>' attribute.
	 * @see #setHttpServer(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getRegisterAgentResult_HttpServer()
	 * @model
	 * @generated
	 */
	String getHttpServer();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpServer <em>Http Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Server</em>' attribute.
	 * @see #getHttpServer()
	 * @generated
	 */
	void setHttpServer(String value);

} // RegisterAgentResult
