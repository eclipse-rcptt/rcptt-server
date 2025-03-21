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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.rcptt.ecl.core.impl.CommandImpl;

import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelPackage;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.impl.SuiteMetadataImpl;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exec Test Suite</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl#getAuts <em>Auts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecTestSuiteImpl extends CommandImpl implements ExecTestSuite {
	/**
	 * The cached value of the '{@link #getAuts() <em>Auts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuts()
	 * @generated
	 * @ordered
	 */
	protected EList<AutInfo> auts;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected TestOptions options;

	/**
	 * The cached value of the '{@link #getMetadata() <em>Metadata</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetadata()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> metadata;

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
	protected ExecTestSuiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.EXEC_TEST_SUITE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AutInfo> getAuts() {
		if (auts == null) {
			auts = new EObjectContainmentEList<AutInfo>(AutInfo.class, this, ServerCommandsPackage.EXEC_TEST_SUITE__AUTS);
		}
		return auts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestOptions getOptions() {
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOptions(TestOptions newOptions, NotificationChain msgs) {
		TestOptions oldOptions = options;
		options = newOptions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS, oldOptions, newOptions);
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
	public void setOptions(TestOptions newOptions) {
		if (newOptions != options) {
			NotificationChain msgs = null;
			if (options != null)
				msgs = ((InternalEObject)options).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS, null, msgs);
			if (newOptions != null)
				msgs = ((InternalEObject)newOptions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS, null, msgs);
			msgs = basicSetOptions(newOptions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS, newOptions, newOptions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getMetadata() {
		if (metadata == null) {
			metadata = new EcoreEMap<String,String>(ModelPackage.Literals.SUITE_METADATA, SuiteMetadataImpl.class, this, ServerCommandsPackage.EXEC_TEST_SUITE__METADATA);
		}
		return metadata;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.EXEC_TEST_SUITE__SUITE_ID, oldSuiteId, suiteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServerCommandsPackage.EXEC_TEST_SUITE__AUTS:
				return ((InternalEList<?>)getAuts()).basicRemove(otherEnd, msgs);
			case ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS:
				return basicSetOptions(null, msgs);
			case ServerCommandsPackage.EXEC_TEST_SUITE__METADATA:
				return ((InternalEList<?>)getMetadata()).basicRemove(otherEnd, msgs);
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
			case ServerCommandsPackage.EXEC_TEST_SUITE__AUTS:
				return getAuts();
			case ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS:
				return getOptions();
			case ServerCommandsPackage.EXEC_TEST_SUITE__METADATA:
				if (coreType) return getMetadata();
				else return getMetadata().map();
			case ServerCommandsPackage.EXEC_TEST_SUITE__SUITE_ID:
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
	@SuppressWarnings("unchecked")

	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ServerCommandsPackage.EXEC_TEST_SUITE__AUTS:
				getAuts().clear();
				getAuts().addAll((Collection<? extends AutInfo>)newValue);
				return;
			case ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS:
				setOptions((TestOptions)newValue);
				return;
			case ServerCommandsPackage.EXEC_TEST_SUITE__METADATA:
				((EStructuralFeature.Setting)getMetadata()).set(newValue);
				return;
			case ServerCommandsPackage.EXEC_TEST_SUITE__SUITE_ID:
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
			case ServerCommandsPackage.EXEC_TEST_SUITE__AUTS:
				getAuts().clear();
				return;
			case ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS:
				setOptions((TestOptions)null);
				return;
			case ServerCommandsPackage.EXEC_TEST_SUITE__METADATA:
				getMetadata().clear();
				return;
			case ServerCommandsPackage.EXEC_TEST_SUITE__SUITE_ID:
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
			case ServerCommandsPackage.EXEC_TEST_SUITE__AUTS:
				return auts != null && !auts.isEmpty();
			case ServerCommandsPackage.EXEC_TEST_SUITE__OPTIONS:
				return options != null;
			case ServerCommandsPackage.EXEC_TEST_SUITE__METADATA:
				return metadata != null && !metadata.isEmpty();
			case ServerCommandsPackage.EXEC_TEST_SUITE__SUITE_ID:
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

} //ExecTestSuiteImpl
