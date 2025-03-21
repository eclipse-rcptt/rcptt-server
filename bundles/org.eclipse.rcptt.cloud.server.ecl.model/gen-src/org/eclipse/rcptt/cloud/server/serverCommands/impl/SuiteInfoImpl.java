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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;
import org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Suite Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.SuiteInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.SuiteInfoImpl#getPendingTasks <em>Pending Tasks</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SuiteInfoImpl extends EObjectImpl implements SuiteInfo {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPendingTasks() <em>Pending Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPendingTasks()
	 * @generated
	 * @ordered
	 */
	protected static final int PENDING_TASKS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPendingTasks() <em>Pending Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPendingTasks()
	 * @generated
	 * @ordered
	 */
	protected int pendingTasks = PENDING_TASKS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuiteInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.SUITE_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.SUITE_INFO__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getPendingTasks() {
		return pendingTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPendingTasks(int newPendingTasks) {
		int oldPendingTasks = pendingTasks;
		pendingTasks = newPendingTasks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.SUITE_INFO__PENDING_TASKS, oldPendingTasks, pendingTasks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServerCommandsPackage.SUITE_INFO__NAME:
				return getName();
			case ServerCommandsPackage.SUITE_INFO__PENDING_TASKS:
				return getPendingTasks();
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
			case ServerCommandsPackage.SUITE_INFO__NAME:
				setName((String)newValue);
				return;
			case ServerCommandsPackage.SUITE_INFO__PENDING_TASKS:
				setPendingTasks((Integer)newValue);
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
			case ServerCommandsPackage.SUITE_INFO__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ServerCommandsPackage.SUITE_INFO__PENDING_TASKS:
				setPendingTasks(PENDING_TASKS_EDEFAULT);
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
			case ServerCommandsPackage.SUITE_INFO__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ServerCommandsPackage.SUITE_INFO__PENDING_TASKS:
				return pendingTasks != PENDING_TASKS_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", pendingTasks: ");
		result.append(pendingTasks);
		result.append(')');
		return result.toString();
	}

} //SuiteInfoImpl
