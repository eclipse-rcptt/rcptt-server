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
package org.eclipse.rcptt.cloud.server.serverCommands.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Register Agent Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentResultImpl#getHttpPort <em>Http Port</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentResultImpl#getHttpServer <em>Http Server</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RegisterAgentResultImpl extends EObjectImpl implements RegisterAgentResult {
	/**
	 * The default value of the '{@link #getHttpPort() <em>Http Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpPort()
	 * @generated
	 * @ordered
	 */
	protected static final int HTTP_PORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHttpPort() <em>Http Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpPort()
	 * @generated
	 * @ordered
	 */
	protected int httpPort = HTTP_PORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpServer() <em>Http Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServer()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_SERVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpServer() <em>Http Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServer()
	 * @generated
	 * @ordered
	 */
	protected String httpServer = HTTP_SERVER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RegisterAgentResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.REGISTER_AGENT_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getHttpPort() {
		return httpPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHttpPort(int newHttpPort) {
		int oldHttpPort = httpPort;
		httpPort = newHttpPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_PORT, oldHttpPort, httpPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHttpServer() {
		return httpServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHttpServer(String newHttpServer) {
		String oldHttpServer = httpServer;
		httpServer = newHttpServer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_SERVER, oldHttpServer, httpServer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_PORT:
				return getHttpPort();
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_SERVER:
				return getHttpServer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_PORT:
				setHttpPort((Integer)newValue);
				return;
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_SERVER:
				setHttpServer((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_PORT:
				setHttpPort(HTTP_PORT_EDEFAULT);
				return;
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_SERVER:
				setHttpServer(HTTP_SERVER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_PORT:
				return httpPort != HTTP_PORT_EDEFAULT;
			case ServerCommandsPackage.REGISTER_AGENT_RESULT__HTTP_SERVER:
				return HTTP_SERVER_EDEFAULT == null ? httpServer != null : !HTTP_SERVER_EDEFAULT.equals(httpServer);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (httpPort: ");
		result.append(httpPort);
		result.append(", httpServer: ");
		result.append(httpServer);
		result.append(')');
		return result.toString();
	}

} //RegisterAgentResultImpl
