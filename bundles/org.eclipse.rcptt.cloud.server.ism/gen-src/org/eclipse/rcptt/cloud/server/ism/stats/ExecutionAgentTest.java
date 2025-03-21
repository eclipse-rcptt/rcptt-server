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
package org.eclipse.rcptt.cloud.server.ism.stats;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Agent Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestID <em>Test ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStatus <em>Status</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestName <em>Test Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getEndTime <em>End Time</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentTest()
 * @model
 * @generated
 */
public interface ExecutionAgentTest extends EObject {
	/**
	 * Returns the value of the '<em><b>Test ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test ID</em>' attribute.
	 * @see #setTestID(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentTest_TestID()
	 * @model
	 * @generated
	 */
	String getTestID();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestID <em>Test ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test ID</em>' attribute.
	 * @see #getTestID()
	 * @generated
	 */
	void setTestID(String value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus
	 * @see #setStatus(ExecutionAgentTestStatus)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentTest_Status()
	 * @model
	 * @generated
	 */
	ExecutionAgentTestStatus getStatus();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(ExecutionAgentTestStatus value);

	/**
	 * Returns the value of the '<em><b>Test Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Name</em>' attribute.
	 * @see #setTestName(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentTest_TestName()
	 * @model
	 * @generated
	 */
	String getTestName();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestName <em>Test Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Name</em>' attribute.
	 * @see #getTestName()
	 * @generated
	 */
	void setTestName(String value);

	/**
	 * Returns the value of the '<em><b>Start Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Time</em>' attribute.
	 * @see #setStartTime(long)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentTest_StartTime()
	 * @model default="0"
	 * @generated
	 */
	long getStartTime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStartTime <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Time</em>' attribute.
	 * @see #getStartTime()
	 * @generated
	 */
	void setStartTime(long value);

	/**
	 * Returns the value of the '<em><b>End Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Time</em>' attribute.
	 * @see #setEndTime(long)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentTest_EndTime()
	 * @model default="0"
	 * @generated
	 */
	long getEndTime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getEndTime <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Time</em>' attribute.
	 * @see #getEndTime()
	 * @generated
	 */
	void setEndTime(long value);

} // ExecutionAgentTest
