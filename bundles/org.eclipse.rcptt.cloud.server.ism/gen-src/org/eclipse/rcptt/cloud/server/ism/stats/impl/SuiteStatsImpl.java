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

import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Suite Stats</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.SuiteStatsImpl#getSuiteName <em>Suite Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.SuiteStatsImpl#getLastSuiteID <em>Last Suite ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SuiteStatsImpl extends EObjectImpl implements SuiteStats {
	/**
	 * The default value of the '{@link #getSuiteName() <em>Suite Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSuiteName()
	 * @generated
	 * @ordered
	 */
	protected static final String SUITE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuiteName() <em>Suite Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSuiteName()
	 * @generated
	 * @ordered
	 */
	protected String suiteName = SUITE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastSuiteID() <em>Last Suite ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLastSuiteID()
	 * @generated
	 * @ordered
	 */
	protected static final int LAST_SUITE_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLastSuiteID() <em>Last Suite ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLastSuiteID()
	 * @generated
	 * @ordered
	 */
	protected int lastSuiteID = LAST_SUITE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SuiteStatsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return StatsPackage.Literals.SUITE_STATS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getSuiteName() {
		return suiteName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuiteName(String newSuiteName) {
		String oldSuiteName = suiteName;
		suiteName = newSuiteName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.SUITE_STATS__SUITE_NAME, oldSuiteName, suiteName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getLastSuiteID() {
		return lastSuiteID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastSuiteID(int newLastSuiteID) {
		int oldLastSuiteID = lastSuiteID;
		lastSuiteID = newLastSuiteID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.SUITE_STATS__LAST_SUITE_ID, oldLastSuiteID, lastSuiteID));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatsPackage.SUITE_STATS__SUITE_NAME:
				return getSuiteName();
			case StatsPackage.SUITE_STATS__LAST_SUITE_ID:
				return getLastSuiteID();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")

	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatsPackage.SUITE_STATS__SUITE_NAME:
				setSuiteName((String)newValue);
				return;
			case StatsPackage.SUITE_STATS__LAST_SUITE_ID:
				setLastSuiteID((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StatsPackage.SUITE_STATS__SUITE_NAME:
				setSuiteName(SUITE_NAME_EDEFAULT);
				return;
			case StatsPackage.SUITE_STATS__LAST_SUITE_ID:
				setLastSuiteID(LAST_SUITE_ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StatsPackage.SUITE_STATS__SUITE_NAME:
				return SUITE_NAME_EDEFAULT == null ? suiteName != null : !SUITE_NAME_EDEFAULT.equals(suiteName);
			case StatsPackage.SUITE_STATS__LAST_SUITE_ID:
				return lastSuiteID != LAST_SUITE_ID_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (suiteName: ");
		result.append(suiteName);
		result.append(", lastSuiteID: ");
		result.append(lastSuiteID);
		result.append(')');
		return result.toString();
	}

} // SuiteStatsImpl
