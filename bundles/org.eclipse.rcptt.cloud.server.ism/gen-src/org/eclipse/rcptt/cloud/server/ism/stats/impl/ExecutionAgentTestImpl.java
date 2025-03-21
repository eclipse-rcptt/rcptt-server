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
package org.eclipse.rcptt.cloud.server.ism.stats.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Agent Test</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl#getTestID <em>Test ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl#getTestName <em>Test Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl#getEndTime <em>End Time</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionAgentTestImpl extends EObjectImpl implements ExecutionAgentTest {
	/**
	 * The default value of the '{@link #getTestID() <em>Test ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestID()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestID() <em>Test ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestID()
	 * @generated
	 * @ordered
	 */
	protected String testID = TEST_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionAgentTestStatus STATUS_EDEFAULT = ExecutionAgentTestStatus.PASS;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected ExecutionAgentTestStatus status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestName() <em>Test Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestName()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestName() <em>Test Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestName()
	 * @generated
	 * @ordered
	 */
	protected String testName = TEST_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final long START_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected long startTime = START_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndTime()
	 * @generated
	 * @ordered
	 */
	protected static final long END_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndTime()
	 * @generated
	 * @ordered
	 */
	protected long endTime = END_TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionAgentTestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return StatsPackage.Literals.EXECUTION_AGENT_TEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTestID() {
		return testID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestID(String newTestID) {
		String oldTestID = testID;
		testID = newTestID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION_AGENT_TEST__TEST_ID, oldTestID, testID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionAgentTestStatus getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(ExecutionAgentTestStatus newStatus) {
		ExecutionAgentTestStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION_AGENT_TEST__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestName(String newTestName) {
		String oldTestName = testName;
		testName = newTestName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION_AGENT_TEST__TEST_NAME, oldTestName, testName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartTime(long newStartTime) {
		long oldStartTime = startTime;
		startTime = newStartTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION_AGENT_TEST__START_TIME, oldStartTime, startTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndTime(long newEndTime) {
		long oldEndTime = endTime;
		endTime = newEndTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION_AGENT_TEST__END_TIME, oldEndTime, endTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_ID:
				return getTestID();
			case StatsPackage.EXECUTION_AGENT_TEST__STATUS:
				return getStatus();
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_NAME:
				return getTestName();
			case StatsPackage.EXECUTION_AGENT_TEST__START_TIME:
				return getStartTime();
			case StatsPackage.EXECUTION_AGENT_TEST__END_TIME:
				return getEndTime();
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
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_ID:
				setTestID((String)newValue);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__STATUS:
				setStatus((ExecutionAgentTestStatus)newValue);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_NAME:
				setTestName((String)newValue);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__START_TIME:
				setStartTime((Long)newValue);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__END_TIME:
				setEndTime((Long)newValue);
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
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_ID:
				setTestID(TEST_ID_EDEFAULT);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_NAME:
				setTestName(TEST_NAME_EDEFAULT);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__START_TIME:
				setStartTime(START_TIME_EDEFAULT);
				return;
			case StatsPackage.EXECUTION_AGENT_TEST__END_TIME:
				setEndTime(END_TIME_EDEFAULT);
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
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_ID:
				return TEST_ID_EDEFAULT == null ? testID != null : !TEST_ID_EDEFAULT.equals(testID);
			case StatsPackage.EXECUTION_AGENT_TEST__STATUS:
				return status != STATUS_EDEFAULT;
			case StatsPackage.EXECUTION_AGENT_TEST__TEST_NAME:
				return TEST_NAME_EDEFAULT == null ? testName != null : !TEST_NAME_EDEFAULT.equals(testName);
			case StatsPackage.EXECUTION_AGENT_TEST__START_TIME:
				return startTime != START_TIME_EDEFAULT;
			case StatsPackage.EXECUTION_AGENT_TEST__END_TIME:
				return endTime != END_TIME_EDEFAULT;
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
		result.append(" (testID: ");
		result.append(testID);
		result.append(", status: ");
		result.append(status);
		result.append(", testName: ");
		result.append(testName);
		result.append(", startTime: ");
		result.append(startTime);
		result.append(", endTime: ");
		result.append(endTime);
		result.append(')');
		return result.toString();
	}

} //ExecutionAgentTestImpl
