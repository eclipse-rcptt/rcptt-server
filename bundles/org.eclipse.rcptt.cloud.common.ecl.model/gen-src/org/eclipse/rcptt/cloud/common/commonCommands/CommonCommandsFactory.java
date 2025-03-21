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
package org.eclipse.rcptt.cloud.common.commonCommands;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage
 * @generated
 */
public interface CommonCommandsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommonCommandsFactory eINSTANCE = org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Add Test Suite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Test Suite</em>'.
	 * @generated
	 */
	AddTestSuite createAddTestSuite();

	/**
	 * Returns a new object of class '<em>Add Test Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Test Resource</em>'.
	 * @generated
	 */
	AddTestResource createAddTestResource();

	/**
	 * Returns a new object of class '<em>Request Shutdown</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Request Shutdown</em>'.
	 * @generated
	 */
	RequestShutdown createRequestShutdown();

	/**
	 * Returns a new object of class '<em>Cancel Suite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cancel Suite</em>'.
	 * @generated
	 */
	CancelSuite createCancelSuite();

	/**
	 * Returns a new object of class '<em>Begin Test Suite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Begin Test Suite</em>'.
	 * @generated
	 */
	BeginTestSuite createBeginTestSuite();

	/**
	 * Returns a new object of class '<em>Add Aut</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Aut</em>'.
	 * @generated
	 */
	AddAut createAddAut();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CommonCommandsPackage getCommonCommandsPackage();

} //CommonCommandsFactory
