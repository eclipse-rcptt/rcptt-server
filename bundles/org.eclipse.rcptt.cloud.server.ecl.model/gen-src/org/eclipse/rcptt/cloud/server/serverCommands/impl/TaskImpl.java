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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl#getSuite <em>Suite</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl#getAut <em>Aut</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl#getTestOptions <em>Test Options</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TaskImpl extends EObjectImpl implements Task {
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
	 * The cached value of the '{@link #getAut() <em>Aut</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAut()
	 * @generated
	 * @ordered
	 */
	protected AutInfo aut;

	/**
	 * The cached value of the '{@link #getTestOptions() <em>Test Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestOptions()
	 * @generated
	 * @ordered
	 */
	protected TestOptions testOptions;

	/**
	 * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<Q7Artifact> artifacts;

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
	 */
	protected TaskImpl() {
		super();
		testOptions = org.eclipse.rcptt.cloud.model.ModelFactory.eINSTANCE.createTestOptions();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.TASK;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__SUITE, oldSuite, newSuite);
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
				msgs = ((InternalEObject)suite).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.TASK__SUITE, null, msgs);
			if (newSuite != null)
				msgs = ((InternalEObject)newSuite).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.TASK__SUITE, null, msgs);
			msgs = basicSetSuite(newSuite, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__SUITE, newSuite, newSuite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AutInfo getAut() {
		return aut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAut(AutInfo newAut, NotificationChain msgs) {
		AutInfo oldAut = aut;
		aut = newAut;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__AUT, oldAut, newAut);
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
	public void setAut(AutInfo newAut) {
		if (newAut != aut) {
			NotificationChain msgs = null;
			if (aut != null)
				msgs = ((InternalEObject)aut).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.TASK__AUT, null, msgs);
			if (newAut != null)
				msgs = ((InternalEObject)newAut).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.TASK__AUT, null, msgs);
			msgs = basicSetAut(newAut, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__AUT, newAut, newAut));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestOptions getTestOptions() {
		return testOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTestOptions(TestOptions newTestOptions, NotificationChain msgs) {
		TestOptions oldTestOptions = testOptions;
		testOptions = newTestOptions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__TEST_OPTIONS, oldTestOptions, newTestOptions);
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
	public void setTestOptions(TestOptions newTestOptions) {
		if (newTestOptions != testOptions) {
			NotificationChain msgs = null;
			if (testOptions != null)
				msgs = ((InternalEObject)testOptions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.TASK__TEST_OPTIONS, null, msgs);
			if (newTestOptions != null)
				msgs = ((InternalEObject)newTestOptions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.TASK__TEST_OPTIONS, null, msgs);
			msgs = basicSetTestOptions(newTestOptions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__TEST_OPTIONS, newTestOptions, newTestOptions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Q7Artifact> getArtifacts() {
		if (artifacts == null) {
			artifacts = new EObjectContainmentEList<Q7Artifact>(Q7Artifact.class, this, ServerCommandsPackage.TASK__ARTIFACTS);
		}
		return artifacts;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.TASK__SUITE_ID, oldSuiteId, suiteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServerCommandsPackage.TASK__SUITE:
				return basicSetSuite(null, msgs);
			case ServerCommandsPackage.TASK__AUT:
				return basicSetAut(null, msgs);
			case ServerCommandsPackage.TASK__TEST_OPTIONS:
				return basicSetTestOptions(null, msgs);
			case ServerCommandsPackage.TASK__ARTIFACTS:
				return ((InternalEList<?>)getArtifacts()).basicRemove(otherEnd, msgs);
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
			case ServerCommandsPackage.TASK__SUITE:
				return getSuite();
			case ServerCommandsPackage.TASK__AUT:
				return getAut();
			case ServerCommandsPackage.TASK__TEST_OPTIONS:
				return getTestOptions();
			case ServerCommandsPackage.TASK__ARTIFACTS:
				return getArtifacts();
			case ServerCommandsPackage.TASK__ID:
				return getId();
			case ServerCommandsPackage.TASK__SUITE_ID:
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
			case ServerCommandsPackage.TASK__SUITE:
				setSuite((TestSuite)newValue);
				return;
			case ServerCommandsPackage.TASK__AUT:
				setAut((AutInfo)newValue);
				return;
			case ServerCommandsPackage.TASK__TEST_OPTIONS:
				setTestOptions((TestOptions)newValue);
				return;
			case ServerCommandsPackage.TASK__ARTIFACTS:
				getArtifacts().clear();
				getArtifacts().addAll((Collection<? extends Q7Artifact>)newValue);
				return;
			case ServerCommandsPackage.TASK__ID:
				setId((String)newValue);
				return;
			case ServerCommandsPackage.TASK__SUITE_ID:
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
			case ServerCommandsPackage.TASK__SUITE:
				setSuite((TestSuite)null);
				return;
			case ServerCommandsPackage.TASK__AUT:
				setAut((AutInfo)null);
				return;
			case ServerCommandsPackage.TASK__TEST_OPTIONS:
				setTestOptions((TestOptions)null);
				return;
			case ServerCommandsPackage.TASK__ARTIFACTS:
				getArtifacts().clear();
				return;
			case ServerCommandsPackage.TASK__ID:
				setId(ID_EDEFAULT);
				return;
			case ServerCommandsPackage.TASK__SUITE_ID:
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
			case ServerCommandsPackage.TASK__SUITE:
				return suite != null;
			case ServerCommandsPackage.TASK__AUT:
				return aut != null;
			case ServerCommandsPackage.TASK__TEST_OPTIONS:
				return testOptions != null;
			case ServerCommandsPackage.TASK__ARTIFACTS:
				return artifacts != null && !artifacts.isEmpty();
			case ServerCommandsPackage.TASK__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ServerCommandsPackage.TASK__SUITE_ID:
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
		result.append(" (id: ");
		result.append(id);
		result.append(", suiteId: ");
		result.append(suiteId);
		result.append(')');
		return result.toString();
	}

} //TaskImpl
