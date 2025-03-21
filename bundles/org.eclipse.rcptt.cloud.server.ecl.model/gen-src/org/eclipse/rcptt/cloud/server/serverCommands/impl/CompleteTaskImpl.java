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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;
import org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complete Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl#getReport <em>Report</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl#getReturnCause <em>Return Cause</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl#getId <em>Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompleteTaskImpl extends AgentCommandImpl implements CompleteTask {
	/**
	 * The cached value of the '{@link #getReport() <em>Report</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReport()
	 * @generated
	 * @ordered
	 */
	protected Report report;
	/**
	 * The default value of the '{@link #getReturnCause() <em>Return Cause</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnCause()
	 * @generated
	 * @ordered
	 */
	protected static final TaskStatus RETURN_CAUSE_EDEFAULT = TaskStatus.UNKNOWN;
	/**
	 * The cached value of the '{@link #getReturnCause() <em>Return Cause</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnCause()
	 * @generated
	 * @ordered
	 */
	protected TaskStatus returnCause = RETURN_CAUSE_EDEFAULT;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompleteTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.COMPLETE_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Report getReport() {
		return report;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReport(Report newReport, NotificationChain msgs) {
		Report oldReport = report;
		report = newReport;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.COMPLETE_TASK__REPORT, oldReport, newReport);
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
	public void setReport(Report newReport) {
		if (newReport != report) {
			NotificationChain msgs = null;
			if (report != null)
				msgs = ((InternalEObject)report).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.COMPLETE_TASK__REPORT, null, msgs);
			if (newReport != null)
				msgs = ((InternalEObject)newReport).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServerCommandsPackage.COMPLETE_TASK__REPORT, null, msgs);
			msgs = basicSetReport(newReport, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.COMPLETE_TASK__REPORT, newReport, newReport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TaskStatus getReturnCause() {
		return returnCause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReturnCause(TaskStatus newReturnCause) {
		TaskStatus oldReturnCause = returnCause;
		returnCause = newReturnCause == null ? RETURN_CAUSE_EDEFAULT : newReturnCause;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.COMPLETE_TASK__RETURN_CAUSE, oldReturnCause, returnCause));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.COMPLETE_TASK__SUITE_ID, oldSuiteId, suiteId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.COMPLETE_TASK__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServerCommandsPackage.COMPLETE_TASK__REPORT:
				return basicSetReport(null, msgs);
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
			case ServerCommandsPackage.COMPLETE_TASK__REPORT:
				return getReport();
			case ServerCommandsPackage.COMPLETE_TASK__RETURN_CAUSE:
				return getReturnCause();
			case ServerCommandsPackage.COMPLETE_TASK__SUITE_ID:
				return getSuiteId();
			case ServerCommandsPackage.COMPLETE_TASK__ID:
				return getId();
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
			case ServerCommandsPackage.COMPLETE_TASK__REPORT:
				setReport((Report)newValue);
				return;
			case ServerCommandsPackage.COMPLETE_TASK__RETURN_CAUSE:
				setReturnCause((TaskStatus)newValue);
				return;
			case ServerCommandsPackage.COMPLETE_TASK__SUITE_ID:
				setSuiteId((String)newValue);
				return;
			case ServerCommandsPackage.COMPLETE_TASK__ID:
				setId((String)newValue);
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
			case ServerCommandsPackage.COMPLETE_TASK__REPORT:
				setReport((Report)null);
				return;
			case ServerCommandsPackage.COMPLETE_TASK__RETURN_CAUSE:
				setReturnCause(RETURN_CAUSE_EDEFAULT);
				return;
			case ServerCommandsPackage.COMPLETE_TASK__SUITE_ID:
				setSuiteId(SUITE_ID_EDEFAULT);
				return;
			case ServerCommandsPackage.COMPLETE_TASK__ID:
				setId(ID_EDEFAULT);
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
			case ServerCommandsPackage.COMPLETE_TASK__REPORT:
				return report != null;
			case ServerCommandsPackage.COMPLETE_TASK__RETURN_CAUSE:
				return returnCause != RETURN_CAUSE_EDEFAULT;
			case ServerCommandsPackage.COMPLETE_TASK__SUITE_ID:
				return SUITE_ID_EDEFAULT == null ? suiteId != null : !SUITE_ID_EDEFAULT.equals(suiteId);
			case ServerCommandsPackage.COMPLETE_TASK__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (returnCause: ");
		result.append(returnCause);
		result.append(", suiteId: ");
		result.append(suiteId);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //CompleteTaskImpl
