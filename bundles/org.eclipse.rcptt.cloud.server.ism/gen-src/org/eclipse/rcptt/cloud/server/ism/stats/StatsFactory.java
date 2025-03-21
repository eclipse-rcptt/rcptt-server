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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage
 * @generated
 */
public interface StatsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatsFactory eINSTANCE = org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Suite Stats</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Suite Stats</em>'.
	 * @generated
	 */
	SuiteStats createSuiteStats();

	/**
	 * Returns a new object of class '<em>Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution</em>'.
	 * @generated
	 */
	Execution createExecution();

	/**
	 * Returns a new object of class '<em>Execution Agent Stats</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Agent Stats</em>'.
	 * @generated
	 */
	ExecutionAgentStats createExecutionAgentStats();

	/**
	 * Returns a new object of class '<em>Execution Agent Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Agent Test</em>'.
	 * @generated
	 */
	ExecutionAgentTest createExecutionAgentTest();

	/**
	 * Returns a new object of class '<em>Agent Stats</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Agent Stats</em>'.
	 * @generated
	 */
	AgentStats createAgentStats();

	/**
	 * Returns a new object of class '<em>Agent Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Agent Event</em>'.
	 * @generated
	 */
	AgentEvent createAgentEvent();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StatsPackage getStatsPackage();

} //StatsFactory
