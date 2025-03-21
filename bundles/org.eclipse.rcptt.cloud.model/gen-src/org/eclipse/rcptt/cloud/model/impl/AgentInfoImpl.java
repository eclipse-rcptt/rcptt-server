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
package org.eclipse.rcptt.cloud.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Agent Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl#getLaunchId <em>Launch Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl#getSkipTags <em>Skip Tags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AgentInfoImpl extends EObjectImpl implements AgentInfo {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected String uri = URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassifier() <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifier()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifier() <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifier()
	 * @generated
	 * @ordered
	 */
	protected String classifier = CLASSIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getLaunchId() <em>Launch Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaunchId()
	 * @generated
	 * @ordered
	 */
	protected static final String LAUNCH_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLaunchId() <em>Launch Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaunchId()
	 * @generated
	 * @ordered
	 */
	protected String launchId = LAUNCH_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSkipTags() <em>Skip Tags</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipTags()
	 * @generated
	 * @ordered
	 */
	protected EList<String> skipTags;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgentInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.AGENT_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AGENT_INFO__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getClassifier() {
		return classifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClassifier(String newClassifier) {
		String oldClassifier = classifier;
		classifier = newClassifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AGENT_INFO__CLASSIFIER, oldClassifier, classifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLaunchId() {
		return launchId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLaunchId(String newLaunchId) {
		String oldLaunchId = launchId;
		launchId = newLaunchId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AGENT_INFO__LAUNCH_ID, oldLaunchId, launchId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getSkipTags() {
		if (skipTags == null) {
			skipTags = new EDataTypeUniqueEList<String>(String.class, this, ModelPackage.AGENT_INFO__SKIP_TAGS);
		}
		return skipTags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.AGENT_INFO__URI:
				return getUri();
			case ModelPackage.AGENT_INFO__CLASSIFIER:
				return getClassifier();
			case ModelPackage.AGENT_INFO__LAUNCH_ID:
				return getLaunchId();
			case ModelPackage.AGENT_INFO__SKIP_TAGS:
				return getSkipTags();
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
			case ModelPackage.AGENT_INFO__URI:
				setUri((String)newValue);
				return;
			case ModelPackage.AGENT_INFO__CLASSIFIER:
				setClassifier((String)newValue);
				return;
			case ModelPackage.AGENT_INFO__LAUNCH_ID:
				setLaunchId((String)newValue);
				return;
			case ModelPackage.AGENT_INFO__SKIP_TAGS:
				getSkipTags().clear();
				getSkipTags().addAll((Collection<? extends String>)newValue);
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
			case ModelPackage.AGENT_INFO__URI:
				setUri(URI_EDEFAULT);
				return;
			case ModelPackage.AGENT_INFO__CLASSIFIER:
				setClassifier(CLASSIFIER_EDEFAULT);
				return;
			case ModelPackage.AGENT_INFO__LAUNCH_ID:
				setLaunchId(LAUNCH_ID_EDEFAULT);
				return;
			case ModelPackage.AGENT_INFO__SKIP_TAGS:
				getSkipTags().clear();
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
			case ModelPackage.AGENT_INFO__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ModelPackage.AGENT_INFO__CLASSIFIER:
				return CLASSIFIER_EDEFAULT == null ? classifier != null : !CLASSIFIER_EDEFAULT.equals(classifier);
			case ModelPackage.AGENT_INFO__LAUNCH_ID:
				return LAUNCH_ID_EDEFAULT == null ? launchId != null : !LAUNCH_ID_EDEFAULT.equals(launchId);
			case ModelPackage.AGENT_INFO__SKIP_TAGS:
				return skipTags != null && !skipTags.isEmpty();
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
		result.append(" (uri: ");
		result.append(uri);
		result.append(", classifier: ");
		result.append(classifier);
		result.append(", launchId: ");
		result.append(launchId);
		result.append(", skipTags: ");
		result.append(skipTags);
		result.append(')');
		return result.toString();
	}

} //AgentInfoImpl
