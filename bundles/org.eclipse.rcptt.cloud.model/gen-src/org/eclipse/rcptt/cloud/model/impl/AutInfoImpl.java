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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;

import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Aut Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getHash <em>Hash</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getInjection <em>Injection</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#isIgnoreOtherInjections <em>Ignore Other Injections</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getVmArgs <em>Vm Args</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl#getLicenseUrl <em>License Url</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AutInfoImpl extends EObjectImpl implements AutInfo {
	/**
	 * The default value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] HASH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	protected byte[] hash = HASH_EDEFAULT;

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
	 * The cached value of the '{@link #getInjection() <em>Injection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjection()
	 * @generated
	 * @ordered
	 */
	protected InjectionConfiguration injection;

	/**
	 * The default value of the '{@link #isIgnoreOtherInjections() <em>Ignore Other Injections</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreOtherInjections()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IGNORE_OTHER_INJECTIONS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIgnoreOtherInjections() <em>Ignore Other Injections</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreOtherInjections()
	 * @generated
	 * @ordered
	 */
	protected boolean ignoreOtherInjections = IGNORE_OTHER_INJECTIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArgs() <em>Args</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArgs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> args;

	/**
	 * The cached value of the '{@link #getVmArgs() <em>Vm Args</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVmArgs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> vmArgs;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AutInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.AUT_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public byte[] getHash() {
		return hash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHash(byte[] newHash) {
		byte[] oldHash = hash;
		hash = newHash;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__HASH, oldHash, hash));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__CLASSIFIER, oldClassifier, classifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InjectionConfiguration getInjection() {
		return injection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInjection(InjectionConfiguration newInjection, NotificationChain msgs) {
		InjectionConfiguration oldInjection = injection;
		injection = newInjection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__INJECTION, oldInjection, newInjection);
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
	public void setInjection(InjectionConfiguration newInjection) {
		if (newInjection != injection) {
			NotificationChain msgs = null;
			if (injection != null)
				msgs = ((InternalEObject)injection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.AUT_INFO__INJECTION, null, msgs);
			if (newInjection != null)
				msgs = ((InternalEObject)newInjection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.AUT_INFO__INJECTION, null, msgs);
			msgs = basicSetInjection(newInjection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__INJECTION, newInjection, newInjection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIgnoreOtherInjections() {
		return ignoreOtherInjections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIgnoreOtherInjections(boolean newIgnoreOtherInjections) {
		boolean oldIgnoreOtherInjections = ignoreOtherInjections;
		ignoreOtherInjections = newIgnoreOtherInjections;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__IGNORE_OTHER_INJECTIONS, oldIgnoreOtherInjections, ignoreOtherInjections));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getArgs() {
		if (args == null) {
			args = new EDataTypeUniqueEList<String>(String.class, this, ModelPackage.AUT_INFO__ARGS);
		}
		return args;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getVmArgs() {
		if (vmArgs == null) {
			vmArgs = new EDataTypeUniqueEList<String>(String.class, this, ModelPackage.AUT_INFO__VM_ARGS);
		}
		return vmArgs;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__URI, oldUri, uri));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.AUT_INFO__LICENSE_URL, oldLicenseUrl, licenseUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.AUT_INFO__INJECTION:
				return basicSetInjection(null, msgs);
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
			case ModelPackage.AUT_INFO__HASH:
				return getHash();
			case ModelPackage.AUT_INFO__CLASSIFIER:
				return getClassifier();
			case ModelPackage.AUT_INFO__INJECTION:
				return getInjection();
			case ModelPackage.AUT_INFO__IGNORE_OTHER_INJECTIONS:
				return isIgnoreOtherInjections();
			case ModelPackage.AUT_INFO__ID:
				return getId();
			case ModelPackage.AUT_INFO__ARGS:
				return getArgs();
			case ModelPackage.AUT_INFO__VM_ARGS:
				return getVmArgs();
			case ModelPackage.AUT_INFO__URI:
				return getUri();
			case ModelPackage.AUT_INFO__LICENSE_URL:
				return getLicenseUrl();
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
			case ModelPackage.AUT_INFO__HASH:
				setHash((byte[])newValue);
				return;
			case ModelPackage.AUT_INFO__CLASSIFIER:
				setClassifier((String)newValue);
				return;
			case ModelPackage.AUT_INFO__INJECTION:
				setInjection((InjectionConfiguration)newValue);
				return;
			case ModelPackage.AUT_INFO__IGNORE_OTHER_INJECTIONS:
				setIgnoreOtherInjections((Boolean)newValue);
				return;
			case ModelPackage.AUT_INFO__ID:
				setId((String)newValue);
				return;
			case ModelPackage.AUT_INFO__ARGS:
				getArgs().clear();
				getArgs().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.AUT_INFO__VM_ARGS:
				getVmArgs().clear();
				getVmArgs().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.AUT_INFO__URI:
				setUri((String)newValue);
				return;
			case ModelPackage.AUT_INFO__LICENSE_URL:
				setLicenseUrl((String)newValue);
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
			case ModelPackage.AUT_INFO__HASH:
				setHash(HASH_EDEFAULT);
				return;
			case ModelPackage.AUT_INFO__CLASSIFIER:
				setClassifier(CLASSIFIER_EDEFAULT);
				return;
			case ModelPackage.AUT_INFO__INJECTION:
				setInjection((InjectionConfiguration)null);
				return;
			case ModelPackage.AUT_INFO__IGNORE_OTHER_INJECTIONS:
				setIgnoreOtherInjections(IGNORE_OTHER_INJECTIONS_EDEFAULT);
				return;
			case ModelPackage.AUT_INFO__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.AUT_INFO__ARGS:
				getArgs().clear();
				return;
			case ModelPackage.AUT_INFO__VM_ARGS:
				getVmArgs().clear();
				return;
			case ModelPackage.AUT_INFO__URI:
				setUri(URI_EDEFAULT);
				return;
			case ModelPackage.AUT_INFO__LICENSE_URL:
				setLicenseUrl(LICENSE_URL_EDEFAULT);
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
			case ModelPackage.AUT_INFO__HASH:
				return HASH_EDEFAULT == null ? hash != null : !HASH_EDEFAULT.equals(hash);
			case ModelPackage.AUT_INFO__CLASSIFIER:
				return CLASSIFIER_EDEFAULT == null ? classifier != null : !CLASSIFIER_EDEFAULT.equals(classifier);
			case ModelPackage.AUT_INFO__INJECTION:
				return injection != null;
			case ModelPackage.AUT_INFO__IGNORE_OTHER_INJECTIONS:
				return ignoreOtherInjections != IGNORE_OTHER_INJECTIONS_EDEFAULT;
			case ModelPackage.AUT_INFO__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackage.AUT_INFO__ARGS:
				return args != null && !args.isEmpty();
			case ModelPackage.AUT_INFO__VM_ARGS:
				return vmArgs != null && !vmArgs.isEmpty();
			case ModelPackage.AUT_INFO__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ModelPackage.AUT_INFO__LICENSE_URL:
				return LICENSE_URL_EDEFAULT == null ? licenseUrl != null : !LICENSE_URL_EDEFAULT.equals(licenseUrl);
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
		result.append(" (hash: ");
		result.append(hash);
		result.append(", classifier: ");
		result.append(classifier);
		result.append(", ignoreOtherInjections: ");
		result.append(ignoreOtherInjections);
		result.append(", id: ");
		result.append(id);
		result.append(", args: ");
		result.append(args);
		result.append(", vmArgs: ");
		result.append(vmArgs);
		result.append(", uri: ");
		result.append(uri);
		result.append(", licenseUrl: ");
		result.append(licenseUrl);
		result.append(')');
		return result.toString();
	}

} // AutInfoImpl
