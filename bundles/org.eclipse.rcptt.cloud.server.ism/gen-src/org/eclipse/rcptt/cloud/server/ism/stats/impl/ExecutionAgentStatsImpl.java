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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Agent Stats</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentStatsImpl#getAgentID <em>Agent ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentStatsImpl#getTests <em>Tests</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionAgentStatsImpl extends EObjectImpl implements ExecutionAgentStats {
	/**
	 * The default value of the '{@link #getAgentID() <em>Agent ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAgentID()
	 * @generated
	 * @ordered
	 */
	protected static final String AGENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAgentID() <em>Agent ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAgentID()
	 * @generated
	 * @ordered
	 */
	protected String agentID = AGENT_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTests() <em>Tests</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTests()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionAgentTest> tests;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionAgentStatsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return StatsPackage.Literals.EXECUTION_AGENT_STATS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAgentID() {
		return agentID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAgentID(String newAgentID) {
		String oldAgentID = agentID;
		agentID = newAgentID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION_AGENT_STATS__AGENT_ID, oldAgentID, agentID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionAgentTest> getTests() {
		if (tests == null) {
			tests = new EObjectContainmentEList<ExecutionAgentTest>(ExecutionAgentTest.class, this, StatsPackage.EXECUTION_AGENT_STATS__TESTS);
		}
		return tests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatsPackage.EXECUTION_AGENT_STATS__TESTS:
				return ((InternalEList<?>)getTests()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatsPackage.EXECUTION_AGENT_STATS__AGENT_ID:
				return getAgentID();
			case StatsPackage.EXECUTION_AGENT_STATS__TESTS:
				return getTests();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")

	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatsPackage.EXECUTION_AGENT_STATS__AGENT_ID:
				setAgentID((String)newValue);
				return;
			case StatsPackage.EXECUTION_AGENT_STATS__TESTS:
				getTests().clear();
				getTests().addAll((Collection<? extends ExecutionAgentTest>)newValue);
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
			case StatsPackage.EXECUTION_AGENT_STATS__AGENT_ID:
				setAgentID(AGENT_ID_EDEFAULT);
				return;
			case StatsPackage.EXECUTION_AGENT_STATS__TESTS:
				getTests().clear();
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
			case StatsPackage.EXECUTION_AGENT_STATS__AGENT_ID:
				return AGENT_ID_EDEFAULT == null ? agentID != null : !AGENT_ID_EDEFAULT.equals(agentID);
			case StatsPackage.EXECUTION_AGENT_STATS__TESTS:
				return tests != null && !tests.isEmpty();
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
		result.append(" (agentID: ");
		result.append(agentID);
		result.append(')');
		return result.toString();
	}

} //ExecutionAgentStatsImpl
