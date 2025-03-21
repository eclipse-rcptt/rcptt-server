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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.rcptt.cloud.model.ModelPackage;
import org.eclipse.rcptt.cloud.model.impl.TestOptionImpl;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report AUT Startup</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAUTStartupImpl#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAUTStartupImpl#getFiles <em>Files</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAUTStartupImpl#getState <em>State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReportAUTStartupImpl extends AgentCommandImpl implements ReportAUTStartup {
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
	 * The cached value of the '{@link #getFiles() <em>Files</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiles()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> files;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final AutStartupStatus STATE_EDEFAULT = AutStartupStatus.STARTED;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected AutStartupStatus state = STATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReportAUTStartupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.REPORT_AUT_STARTUP;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.REPORT_AUT_STARTUP__SUITE_ID, oldSuiteId, suiteId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getFiles() {
		if (files == null) {
			files = new EcoreEMap<String,String>(ModelPackage.Literals.TEST_OPTION, TestOptionImpl.class, this, ServerCommandsPackage.REPORT_AUT_STARTUP__FILES);
		}
		return files;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AutStartupStatus getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setState(AutStartupStatus newState) {
		AutStartupStatus oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.REPORT_AUT_STARTUP__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServerCommandsPackage.REPORT_AUT_STARTUP__FILES:
				return ((InternalEList<?>)getFiles()).basicRemove(otherEnd, msgs);
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
			case ServerCommandsPackage.REPORT_AUT_STARTUP__SUITE_ID:
				return getSuiteId();
			case ServerCommandsPackage.REPORT_AUT_STARTUP__FILES:
				if (coreType) return getFiles();
				else return getFiles().map();
			case ServerCommandsPackage.REPORT_AUT_STARTUP__STATE:
				return getState();
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
			case ServerCommandsPackage.REPORT_AUT_STARTUP__SUITE_ID:
				setSuiteId((String)newValue);
				return;
			case ServerCommandsPackage.REPORT_AUT_STARTUP__FILES:
				((EStructuralFeature.Setting)getFiles()).set(newValue);
				return;
			case ServerCommandsPackage.REPORT_AUT_STARTUP__STATE:
				setState((AutStartupStatus)newValue);
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
			case ServerCommandsPackage.REPORT_AUT_STARTUP__SUITE_ID:
				setSuiteId(SUITE_ID_EDEFAULT);
				return;
			case ServerCommandsPackage.REPORT_AUT_STARTUP__FILES:
				getFiles().clear();
				return;
			case ServerCommandsPackage.REPORT_AUT_STARTUP__STATE:
				setState(STATE_EDEFAULT);
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
			case ServerCommandsPackage.REPORT_AUT_STARTUP__SUITE_ID:
				return SUITE_ID_EDEFAULT == null ? suiteId != null : !SUITE_ID_EDEFAULT.equals(suiteId);
			case ServerCommandsPackage.REPORT_AUT_STARTUP__FILES:
				return files != null && !files.isEmpty();
			case ServerCommandsPackage.REPORT_AUT_STARTUP__STATE:
				return state != STATE_EDEFAULT;
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
		result.append(", state: ");
		result.append(state);
		result.append(')');
		return result.toString();
	}

} //ReportAUTStartupImpl
