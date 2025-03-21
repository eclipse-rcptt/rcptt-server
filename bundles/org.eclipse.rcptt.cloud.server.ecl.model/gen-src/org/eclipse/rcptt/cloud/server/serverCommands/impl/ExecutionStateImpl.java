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

import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl#getTotalTestCount <em>Total Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl#getExecutedTestCount <em>Executed Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl#getFailedTestCount <em>Failed Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl#getSkippedTestCount <em>Skipped Test Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl#getExecutionTime <em>Execution Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl#getEstimationTimeLeft <em>Estimation Time Left</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionStateImpl extends EObjectImpl implements ExecutionState {
	/**
	 * The default value of the '{@link #getTotalTestCount() <em>Total Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalTestCount()
	 * @generated
	 * @ordered
	 */
	protected static final int TOTAL_TEST_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTotalTestCount() <em>Total Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalTestCount()
	 * @generated
	 * @ordered
	 */
	protected int totalTestCount = TOTAL_TEST_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getExecutedTestCount() <em>Executed Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutedTestCount()
	 * @generated
	 * @ordered
	 */
	protected static final int EXECUTED_TEST_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExecutedTestCount() <em>Executed Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutedTestCount()
	 * @generated
	 * @ordered
	 */
	protected int executedTestCount = EXECUTED_TEST_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFailedTestCount() <em>Failed Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailedTestCount()
	 * @generated
	 * @ordered
	 */
	protected static final int FAILED_TEST_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFailedTestCount() <em>Failed Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailedTestCount()
	 * @generated
	 * @ordered
	 */
	protected int failedTestCount = FAILED_TEST_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSkippedTestCount() <em>Skipped Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkippedTestCount()
	 * @generated
	 * @ordered
	 */
	protected static final int SKIPPED_TEST_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSkippedTestCount() <em>Skipped Test Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkippedTestCount()
	 * @generated
	 * @ordered
	 */
	protected int skippedTestCount = SKIPPED_TEST_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getExecutionTime() <em>Execution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionTime()
	 * @generated
	 * @ordered
	 */
	protected static final long EXECUTION_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getExecutionTime() <em>Execution Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionTime()
	 * @generated
	 * @ordered
	 */
	protected long executionTime = EXECUTION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEstimationTimeLeft() <em>Estimation Time Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEstimationTimeLeft()
	 * @generated
	 * @ordered
	 */
	protected static final long ESTIMATION_TIME_LEFT_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getEstimationTimeLeft() <em>Estimation Time Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEstimationTimeLeft()
	 * @generated
	 * @ordered
	 */
	protected long estimationTimeLeft = ESTIMATION_TIME_LEFT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.EXECUTION_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTotalTestCount() {
		return totalTestCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTotalTestCount(int newTotalTestCount) {
		int oldTotalTestCount = totalTestCount;
		totalTestCount = newTotalTestCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXECUTION_STATE__TOTAL_TEST_COUNT, oldTotalTestCount, totalTestCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getExecutedTestCount() {
		return executedTestCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExecutedTestCount(int newExecutedTestCount) {
		int oldExecutedTestCount = executedTestCount;
		executedTestCount = newExecutedTestCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXECUTION_STATE__EXECUTED_TEST_COUNT, oldExecutedTestCount, executedTestCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getFailedTestCount() {
		return failedTestCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFailedTestCount(int newFailedTestCount) {
		int oldFailedTestCount = failedTestCount;
		failedTestCount = newFailedTestCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXECUTION_STATE__FAILED_TEST_COUNT, oldFailedTestCount, failedTestCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getSkippedTestCount() {
		return skippedTestCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSkippedTestCount(int newSkippedTestCount) {
		int oldSkippedTestCount = skippedTestCount;
		skippedTestCount = newSkippedTestCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXECUTION_STATE__SKIPPED_TEST_COUNT, oldSkippedTestCount, skippedTestCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getExecutionTime() {
		return executionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExecutionTime(long newExecutionTime) {
		long oldExecutionTime = executionTime;
		executionTime = newExecutionTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXECUTION_STATE__EXECUTION_TIME, oldExecutionTime, executionTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getEstimationTimeLeft() {
		return estimationTimeLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEstimationTimeLeft(long newEstimationTimeLeft) {
		long oldEstimationTimeLeft = estimationTimeLeft;
		estimationTimeLeft = newEstimationTimeLeft;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXECUTION_STATE__ESTIMATION_TIME_LEFT, oldEstimationTimeLeft, estimationTimeLeft));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServerCommandsPackage.EXECUTION_STATE__TOTAL_TEST_COUNT:
				return getTotalTestCount();
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTED_TEST_COUNT:
				return getExecutedTestCount();
			case ServerCommandsPackage.EXECUTION_STATE__FAILED_TEST_COUNT:
				return getFailedTestCount();
			case ServerCommandsPackage.EXECUTION_STATE__SKIPPED_TEST_COUNT:
				return getSkippedTestCount();
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTION_TIME:
				return getExecutionTime();
			case ServerCommandsPackage.EXECUTION_STATE__ESTIMATION_TIME_LEFT:
				return getEstimationTimeLeft();
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
			case ServerCommandsPackage.EXECUTION_STATE__TOTAL_TEST_COUNT:
				setTotalTestCount((Integer)newValue);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTED_TEST_COUNT:
				setExecutedTestCount((Integer)newValue);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__FAILED_TEST_COUNT:
				setFailedTestCount((Integer)newValue);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__SKIPPED_TEST_COUNT:
				setSkippedTestCount((Integer)newValue);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTION_TIME:
				setExecutionTime((Long)newValue);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__ESTIMATION_TIME_LEFT:
				setEstimationTimeLeft((Long)newValue);
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
			case ServerCommandsPackage.EXECUTION_STATE__TOTAL_TEST_COUNT:
				setTotalTestCount(TOTAL_TEST_COUNT_EDEFAULT);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTED_TEST_COUNT:
				setExecutedTestCount(EXECUTED_TEST_COUNT_EDEFAULT);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__FAILED_TEST_COUNT:
				setFailedTestCount(FAILED_TEST_COUNT_EDEFAULT);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__SKIPPED_TEST_COUNT:
				setSkippedTestCount(SKIPPED_TEST_COUNT_EDEFAULT);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTION_TIME:
				setExecutionTime(EXECUTION_TIME_EDEFAULT);
				return;
			case ServerCommandsPackage.EXECUTION_STATE__ESTIMATION_TIME_LEFT:
				setEstimationTimeLeft(ESTIMATION_TIME_LEFT_EDEFAULT);
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
			case ServerCommandsPackage.EXECUTION_STATE__TOTAL_TEST_COUNT:
				return totalTestCount != TOTAL_TEST_COUNT_EDEFAULT;
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTED_TEST_COUNT:
				return executedTestCount != EXECUTED_TEST_COUNT_EDEFAULT;
			case ServerCommandsPackage.EXECUTION_STATE__FAILED_TEST_COUNT:
				return failedTestCount != FAILED_TEST_COUNT_EDEFAULT;
			case ServerCommandsPackage.EXECUTION_STATE__SKIPPED_TEST_COUNT:
				return skippedTestCount != SKIPPED_TEST_COUNT_EDEFAULT;
			case ServerCommandsPackage.EXECUTION_STATE__EXECUTION_TIME:
				return executionTime != EXECUTION_TIME_EDEFAULT;
			case ServerCommandsPackage.EXECUTION_STATE__ESTIMATION_TIME_LEFT:
				return estimationTimeLeft != ESTIMATION_TIME_LEFT_EDEFAULT;
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
		result.append(" (totalTestCount: ");
		result.append(totalTestCount);
		result.append(", executedTestCount: ");
		result.append(executedTestCount);
		result.append(", failedTestCount: ");
		result.append(failedTestCount);
		result.append(", skippedTestCount: ");
		result.append(skippedTestCount);
		result.append(", executionTime: ");
		result.append(executionTime);
		result.append(", estimationTimeLeft: ");
		result.append(estimationTimeLeft);
		result.append(')');
		return result.toString();
	}

} //ExecutionStateImpl
