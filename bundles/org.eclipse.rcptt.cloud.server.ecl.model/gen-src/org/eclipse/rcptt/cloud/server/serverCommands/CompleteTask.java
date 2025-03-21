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
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Complete Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReport <em>Report</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReturnCause <em>Return Cause</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getCompleteTask()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface CompleteTask extends AgentCommand {
	/**
	 * Returns the value of the '<em><b>Report</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Report</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Report</em>' containment reference.
	 * @see #setReport(Report)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getCompleteTask_Report()
	 * @model containment="true"
	 * @generated
	 */
	Report getReport();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReport <em>Report</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Report</em>' containment reference.
	 * @see #getReport()
	 * @generated
	 */
	void setReport(Report value);

	/**
	 * Returns the value of the '<em><b>Return Cause</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Cause</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Cause</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus
	 * @see #setReturnCause(TaskStatus)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getCompleteTask_ReturnCause()
	 * @model
	 * @generated
	 */
	TaskStatus getReturnCause();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReturnCause <em>Return Cause</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Cause</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus
	 * @see #getReturnCause()
	 * @generated
	 */
	void setReturnCause(TaskStatus value);

	/**
	 * Returns the value of the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suite Id</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suite Id</em>' attribute.
	 * @see #setSuiteId(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getCompleteTask_SuiteId()
	 * @model
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getCompleteTask_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // CompleteTask
