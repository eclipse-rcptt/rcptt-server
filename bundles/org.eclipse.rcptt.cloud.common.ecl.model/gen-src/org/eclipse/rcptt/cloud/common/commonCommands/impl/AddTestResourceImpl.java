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

import org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage;
import org.eclipse.rcptt.cloud.model.Q7Artifact;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Add Test Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestResourceImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestResourceImpl#getArtifactsPath <em>Artifacts Path</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestResourceImpl#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AddTestResourceImpl extends CommandImpl implements AddTestResource {
	/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected Q7Artifact resource;

	/**
	 * The default value of the '{@link #getArtifactsPath() <em>Artifacts Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactsPath()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACTS_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactsPath() <em>Artifacts Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactsPath()
	 * @generated
	 * @ordered
	 */
	protected String artifactsPath = ARTIFACTS_PATH_EDEFAULT;

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
	protected AddTestResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonCommandsPackage.Literals.ADD_TEST_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Q7Artifact getResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResource(Q7Artifact newResource, NotificationChain msgs) {
		Q7Artifact oldResource = resource;
		resource = newResource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE, oldResource, newResource);
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
	public void setResource(Q7Artifact newResource) {
		if (newResource != resource) {
			NotificationChain msgs = null;
			if (resource != null)
				msgs = ((InternalEObject)resource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE, null, msgs);
			if (newResource != null)
				msgs = ((InternalEObject)newResource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE, null, msgs);
			msgs = basicSetResource(newResource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE, newResource, newResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getArtifactsPath() {
		return artifactsPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifactsPath(String newArtifactsPath) {
		String oldArtifactsPath = artifactsPath;
		artifactsPath = newArtifactsPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.ADD_TEST_RESOURCE__ARTIFACTS_PATH, oldArtifactsPath, artifactsPath));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonCommandsPackage.ADD_TEST_RESOURCE__SUITE_ID, oldSuiteId, suiteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE:
				return basicSetResource(null, msgs);
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
			case CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE:
				return getResource();
			case CommonCommandsPackage.ADD_TEST_RESOURCE__ARTIFACTS_PATH:
				return getArtifactsPath();
			case CommonCommandsPackage.ADD_TEST_RESOURCE__SUITE_ID:
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
			case CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE:
				setResource((Q7Artifact)newValue);
				return;
			case CommonCommandsPackage.ADD_TEST_RESOURCE__ARTIFACTS_PATH:
				setArtifactsPath((String)newValue);
				return;
			case CommonCommandsPackage.ADD_TEST_RESOURCE__SUITE_ID:
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
			case CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE:
				setResource((Q7Artifact)null);
				return;
			case CommonCommandsPackage.ADD_TEST_RESOURCE__ARTIFACTS_PATH:
				setArtifactsPath(ARTIFACTS_PATH_EDEFAULT);
				return;
			case CommonCommandsPackage.ADD_TEST_RESOURCE__SUITE_ID:
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
			case CommonCommandsPackage.ADD_TEST_RESOURCE__RESOURCE:
				return resource != null;
			case CommonCommandsPackage.ADD_TEST_RESOURCE__ARTIFACTS_PATH:
				return ARTIFACTS_PATH_EDEFAULT == null ? artifactsPath != null : !ARTIFACTS_PATH_EDEFAULT.equals(artifactsPath);
			case CommonCommandsPackage.ADD_TEST_RESOURCE__SUITE_ID:
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
		result.append(" (artifactsPath: ");
		result.append(artifactsPath);
		result.append(", suiteId: ");
		result.append(suiteId);
		result.append(')');
		return result.toString();
	}

} // AddTestResourceImpl
