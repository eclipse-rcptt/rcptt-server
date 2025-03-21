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
 * A representation of the model object '<em><b>Execution State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getTotalTestCount <em>Total Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutedTestCount <em>Executed Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getFailedTestCount <em>Failed Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getSkippedTestCount <em>Skipped Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutionTime <em>Execution Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getEstimationTimeLeft <em>Estimation Time Left</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecutionState()
 * @model
 * @generated
 */
public interface ExecutionState extends EObject {
	/**
	 * Returns the value of the '<em><b>Total Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Test Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Test Count</em>' attribute.
	 * @see #setTotalTestCount(int)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecutionState_TotalTestCount()
	 * @model
	 * @generated
	 */
	int getTotalTestCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getTotalTestCount <em>Total Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Test Count</em>' attribute.
	 * @see #getTotalTestCount()
	 * @generated
	 */
	void setTotalTestCount(int value);

	/**
	 * Returns the value of the '<em><b>Executed Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executed Test Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executed Test Count</em>' attribute.
	 * @see #setExecutedTestCount(int)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecutionState_ExecutedTestCount()
	 * @model
	 * @generated
	 */
	int getExecutedTestCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutedTestCount <em>Executed Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executed Test Count</em>' attribute.
	 * @see #getExecutedTestCount()
	 * @generated
	 */
	void setExecutedTestCount(int value);

	/**
	 * Returns the value of the '<em><b>Failed Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Failed Test Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failed Test Count</em>' attribute.
	 * @see #setFailedTestCount(int)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecutionState_FailedTestCount()
	 * @model
	 * @generated
	 */
	int getFailedTestCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getFailedTestCount <em>Failed Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failed Test Count</em>' attribute.
	 * @see #getFailedTestCount()
	 * @generated
	 */
	void setFailedTestCount(int value);

	/**
	 * Returns the value of the '<em><b>Skipped Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skipped Test Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skipped Test Count</em>' attribute.
	 * @see #setSkippedTestCount(int)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecutionState_SkippedTestCount()
	 * @model
	 * @generated
	 */
	int getSkippedTestCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getSkippedTestCount <em>Skipped Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skipped Test Count</em>' attribute.
	 * @see #getSkippedTestCount()
	 * @generated
	 */
	void setSkippedTestCount(int value);

	/**
	 * Returns the value of the '<em><b>Execution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Time</em>' attribute.
	 * @see #setExecutionTime(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecutionState_ExecutionTime()
	 * @model
	 * @generated
	 */
	long getExecutionTime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutionTime <em>Execution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Time</em>' attribute.
	 * @see #getExecutionTime()
	 * @generated
	 */
	void setExecutionTime(long value);

	/**
	 * Returns the value of the '<em><b>Estimation Time Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimation Time Left</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimation Time Left</em>' attribute.
	 * @see #setEstimationTimeLeft(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecutionState_EstimationTimeLeft()
	 * @model
	 * @generated
	 */
	long getEstimationTimeLeft();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getEstimationTimeLeft <em>Estimation Time Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Estimation Time Left</em>' attribute.
	 * @see #getEstimationTimeLeft()
	 * @generated
	 */
	void setEstimationTimeLeft(long value);

} // ExecutionState
