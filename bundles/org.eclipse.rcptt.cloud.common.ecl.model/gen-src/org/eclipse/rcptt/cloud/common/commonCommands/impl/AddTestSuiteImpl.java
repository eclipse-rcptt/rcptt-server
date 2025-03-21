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
package org.eclipse.rcptt.cloud.common.commonCommands.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.rcptt.ecl.core.impl.CommandImpl;

import org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage;
import org.eclipse.rcptt.cloud.model.TestSuite;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Add Test Suite</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestSuiteImpl#getSuite <em>Suite</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestSuiteImpl#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AddTestSuiteImpl extends CommandImpl implements AddTestSuite {
	/**
	 * The cached value of the '{@link #getSuite() <em>Suite</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuite()
	 * @generated
	 * @ordered
	 */
	protected TestSuite suite;

	/**
	 * The default value of the '{@link #getSuiteId() <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuiteId()
	 * @generated
	 * @ordered
	 */
	protected static final String SUITE_ID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getSuiteId() <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuiteId()
	 * @generated
	 * @ordered
	 */
	protected String suiteId = SUITE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AddTestSuiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonCommandsPackage.Literals.ADD_TEST_SUITE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestSuite getSuite() {
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuite(TestSuite newSuite, NotificationChain msgs) {
		TestSuite oldSuite = suite;
		suite = newSuite;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.ADD_TEST_SUITE__SUITE, oldSuite, newSuite);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuite(TestSuite newSuite) {
		if (newSuite != suite) {
			NotificationChain msgs = null;
			if (suite != null)
				msgs = ((InternalEObject)suite).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CommonCommandsPackage.ADD_TEST_SUITE__SUITE, null, msgs);
			if (newSuite != null)
				msgs = ((InternalEObject)newSuite).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CommonCommandsPackage.ADD_TEST_SUITE__SUITE, null, msgs);
			msgs = basicSetSuite(newSuite, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.ADD_TEST_SUITE__SUITE, newSuite, newSuite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSuiteId() {
		return suiteId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuiteId(String newSuiteId) {
		String oldSuiteId = suiteId;
		suiteId = newSuiteId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.ADD_TEST_SUITE__SUITE_ID, oldSuiteId, suiteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE:
				return basicSetSuite(null, msgs);
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
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE:
				return getSuite();
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE_ID:
				return getSuiteId();
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
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE:
				setSuite((TestSuite)newValue);
				return;
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE_ID:
				setSuiteId((String)newValue);
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
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE:
				setSuite((TestSuite)null);
				return;
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE_ID:
				setSuiteId(SUITE_ID_EDEFAULT);
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
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE:
				return suite != null;
			case CommonCommandsPackage.ADD_TEST_SUITE__SUITE_ID:
				return SUITE_ID_EDEFAULT == null ? suiteId != null : !SUITE_ID_EDEFAULT.equals(suiteId);
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
		result.append(" (suiteId: ");
		result.append(suiteId);
		result.append(')');
		return result.toString();
	}

} // AddTestSuiteImpl
