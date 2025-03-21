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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Agent Stats</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats#getAgentID <em>Agent ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats#getTests <em>Tests</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentStats()
 * @model
 * @generated
 */
public interface ExecutionAgentStats extends EObject {
	/**
	 * Returns the value of the '<em><b>Agent ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agent ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agent ID</em>' attribute.
	 * @see #setAgentID(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentStats_AgentID()
	 * @model
	 * @generated
	 */
	String getAgentID();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats#getAgentID <em>Agent ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Agent ID</em>' attribute.
	 * @see #getAgentID()
	 * @generated
	 */
	void setAgentID(String value);

	/**
	 * Returns the value of the '<em><b>Tests</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tests</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tests</em>' containment reference list.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecutionAgentStats_Tests()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExecutionAgentTest> getTests();

} // ExecutionAgentStats
