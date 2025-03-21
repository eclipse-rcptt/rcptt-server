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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.rcptt.ecl.core.impl.CommandImpl;

import org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Begin Test Suite</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl#getClientId <em>Client Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl#getClientSecret <em>Client Secret</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl#getLicenseUrl <em>License Url</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl#getOrganization <em>Organization</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BeginTestSuiteImpl extends CommandImpl implements BeginTestSuite {
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
	 * The default value of the '{@link #getClientId() <em>Client Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientId()
	 * @generated
	 * @ordered
	 */
	protected static final String CLIENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClientId() <em>Client Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientId()
	 * @generated
	 * @ordered
	 */
	protected String clientId = CLIENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getClientSecret() <em>Client Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientSecret()
	 * @generated
	 * @ordered
	 */
	protected static final String CLIENT_SECRET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClientSecret() <em>Client Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientSecret()
	 * @generated
	 * @ordered
	 */
	protected String clientSecret = CLIENT_SECRET_EDEFAULT;

	/**
	 * The default value of the '{@link #getLicenseUrl() <em>License Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicenseUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String LICENSE_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLicenseUrl() <em>License Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLicenseUrl()
	 * @generated
	 * @ordered
	 */
	protected String licenseUrl = LICENSE_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrganization() <em>Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganization()
	 * @generated
	 * @ordered
	 */
	protected static final String ORGANIZATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrganization() <em>Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganization()
	 * @generated
	 * @ordered
	 */
	protected String organization = ORGANIZATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BeginTestSuiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonCommandsPackage.Literals.BEGIN_TEST_SUITE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.BEGIN_TEST_SUITE__SUITE_ID, oldSuiteId, suiteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getClientId() {
		return clientId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClientId(String newClientId) {
		String oldClientId = clientId;
		clientId = newClientId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_ID, oldClientId, clientId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClientSecret(String newClientSecret) {
		String oldClientSecret = clientSecret;
		clientSecret = newClientSecret;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_SECRET, oldClientSecret, clientSecret));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLicenseUrl() {
		return licenseUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLicenseUrl(String newLicenseUrl) {
		String oldLicenseUrl = licenseUrl;
		licenseUrl = newLicenseUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.BEGIN_TEST_SUITE__LICENSE_URL, oldLicenseUrl, licenseUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOrganization() {
		return organization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrganization(String newOrganization) {
		String oldOrganization = organization;
		organization = newOrganization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.BEGIN_TEST_SUITE__ORGANIZATION, oldOrganization, organization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonCommandsPackage.BEGIN_TEST_SUITE__SUITE_ID:
				return getSuiteId();
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_ID:
				return getClientId();
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_SECRET:
				return getClientSecret();
			case CommonCommandsPackage.BEGIN_TEST_SUITE__LICENSE_URL:
				return getLicenseUrl();
			case CommonCommandsPackage.BEGIN_TEST_SUITE__ORGANIZATION:
				return getOrganization();
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
			case CommonCommandsPackage.BEGIN_TEST_SUITE__SUITE_ID:
				setSuiteId((String)newValue);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_ID:
				setClientId((String)newValue);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_SECRET:
				setClientSecret((String)newValue);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__LICENSE_URL:
				setLicenseUrl((String)newValue);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__ORGANIZATION:
				setOrganization((String)newValue);
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
			case CommonCommandsPackage.BEGIN_TEST_SUITE__SUITE_ID:
				setSuiteId(SUITE_ID_EDEFAULT);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_ID:
				setClientId(CLIENT_ID_EDEFAULT);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_SECRET:
				setClientSecret(CLIENT_SECRET_EDEFAULT);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__LICENSE_URL:
				setLicenseUrl(LICENSE_URL_EDEFAULT);
				return;
			case CommonCommandsPackage.BEGIN_TEST_SUITE__ORGANIZATION:
				setOrganization(ORGANIZATION_EDEFAULT);
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
			case CommonCommandsPackage.BEGIN_TEST_SUITE__SUITE_ID:
				return SUITE_ID_EDEFAULT == null ? suiteId != null : !SUITE_ID_EDEFAULT.equals(suiteId);
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_ID:
				return CLIENT_ID_EDEFAULT == null ? clientId != null : !CLIENT_ID_EDEFAULT.equals(clientId);
			case CommonCommandsPackage.BEGIN_TEST_SUITE__CLIENT_SECRET:
				return CLIENT_SECRET_EDEFAULT == null ? clientSecret != null : !CLIENT_SECRET_EDEFAULT.equals(clientSecret);
			case CommonCommandsPackage.BEGIN_TEST_SUITE__LICENSE_URL:
				return LICENSE_URL_EDEFAULT == null ? licenseUrl != null : !LICENSE_URL_EDEFAULT.equals(licenseUrl);
			case CommonCommandsPackage.BEGIN_TEST_SUITE__ORGANIZATION:
				return ORGANIZATION_EDEFAULT == null ? organization != null : !ORGANIZATION_EDEFAULT.equals(organization);
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
		result.append(", clientId: ");
		result.append(clientId);
		result.append(", clientSecret: ");
		result.append(clientSecret);
		result.append(", licenseUrl: ");
		result.append(licenseUrl);
		result.append(", organization: ");
		result.append(organization);
		result.append(')');
		return result.toString();
	}

} // BeginTestSuiteImpl
