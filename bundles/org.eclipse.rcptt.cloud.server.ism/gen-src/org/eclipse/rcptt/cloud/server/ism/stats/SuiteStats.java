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
package org.eclipse.rcptt.cloud.server.ism.stats;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Suite Stats</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getSuiteName <em>Suite Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getLastSuiteID <em>Last Suite ID</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getSuiteStats()
 * @model
 * @generated
 */
public interface SuiteStats extends EObject {
	/**
	 * Returns the value of the '<em><b>Suite Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suite Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suite Name</em>' attribute.
	 * @see #setSuiteName(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getSuiteStats_SuiteName()
	 * @model
	 * @generated
	 */
	String getSuiteName();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getSuiteName <em>Suite Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Name</em>' attribute.
	 * @see #getSuiteName()
	 * @generated
	 */
	void setSuiteName(String value);

	/**
	 * Returns the value of the '<em><b>Last Suite ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Suite ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Suite ID</em>' attribute.
	 * @see #setLastSuiteID(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getSuiteStats_LastSuiteID()
	 * @model
	 * @generated
	 */
	int getLastSuiteID();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getLastSuiteID <em>Last Suite ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Suite ID</em>' attribute.
	 * @see #getLastSuiteID()
	 * @generated
	 */
	void setLastSuiteID(int value);

} // SuiteStats
