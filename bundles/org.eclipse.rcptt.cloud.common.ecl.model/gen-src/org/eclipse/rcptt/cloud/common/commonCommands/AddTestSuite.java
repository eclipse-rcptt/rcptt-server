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

import org.eclipse.rcptt.ecl.core.Command;

import org.eclipse.rcptt.cloud.model.TestSuite;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Test Suite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuite <em>Suite</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddTestSuite()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface AddTestSuite extends Command {
	/**
	 * Returns the value of the '<em><b>Suite</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suite</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suite</em>' containment reference.
	 * @see #setSuite(TestSuite)
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddTestSuite_Suite()
	 * @model containment="true"
	 * @generated
	 */
	TestSuite getSuite();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuite <em>Suite</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite</em>' containment reference.
	 * @see #getSuite()
	 * @generated
	 */
	void setSuite(TestSuite value);

	/**
	 * Returns the value of the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suite Id</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suite Id</em>' attribute.
	 * @see #setSuiteId(String)
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddTestSuite_SuiteId()
	 * @model
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

} // AddTestSuite
