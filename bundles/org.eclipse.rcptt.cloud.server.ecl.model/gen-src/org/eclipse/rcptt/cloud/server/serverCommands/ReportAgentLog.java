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

import org.eclipse.rcptt.ecl.core.ProcessStatus;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Report Agent Log</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getReportAgentLog()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface ReportAgentLog extends AgentCommand {
	/**
	 * Returns the value of the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suite Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suite Id</em>' attribute.
	 * @see #setSuiteId(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getReportAgentLog_SuiteId()
	 * @model
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType
	 * @see #setType(AgentLogEntryType)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getReportAgentLog_Type()
	 * @model
	 * @generated
	 */
	AgentLogEntryType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType
	 * @see #getType()
	 * @generated
	 */
	void setType(AgentLogEntryType value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' containment reference.
	 * @see #setStatus(ProcessStatus)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getReportAgentLog_Status()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ProcessStatus getStatus();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getStatus <em>Status</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' containment reference.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(ProcessStatus value);

} // ReportAgentLog
