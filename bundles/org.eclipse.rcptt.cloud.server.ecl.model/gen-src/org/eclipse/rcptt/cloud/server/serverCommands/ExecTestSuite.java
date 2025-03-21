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
package org.eclipse.rcptt.cloud.server.serverCommands;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.rcptt.ecl.core.Command;

import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.TestOptions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exec Test Suite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getAuts <em>Auts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecTestSuite()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface ExecTestSuite extends Command {
	/**
	 * Returns the value of the '<em><b>Auts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.cloud.model.AutInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auts</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auts</em>' containment reference list.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecTestSuite_Auts()
	 * @model containment="true"
	 * @generated
	 */
	EList<AutInfo> getAuts();

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference.
	 * @see #setOptions(TestOptions)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecTestSuite_Options()
	 * @model containment="true"
	 * @generated
	 */
	TestOptions getOptions();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getOptions <em>Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Options</em>' containment reference.
	 * @see #getOptions()
	 * @generated
	 */
	void setOptions(TestOptions value);

	/**
	 * Returns the value of the '<em><b>Metadata</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata</em>' map.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecTestSuite_Metadata()
	 * @model mapType="org.eclipse.rcptt.cloud.model.SuiteMetadata&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getMetadata();

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
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getExecTestSuite_SuiteId()
	 * @model
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

} // ExecTestSuite
