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

import org.eclipse.rcptt.cloud.model.AutInfo;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Aut</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getAut <em>Aut</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddAut()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface AddAut extends Command {
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
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddAut_SuiteId()
	 * @model
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

	/**
	 * Returns the value of the '<em><b>Aut</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aut</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aut</em>' reference.
	 * @see #setAut(AutInfo)
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddAut_Aut()
	 * @model
	 * @generated
	 */
	AutInfo getAut();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getAut <em>Aut</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aut</em>' reference.
	 * @see #getAut()
	 * @generated
	 */
	void setAut(AutInfo value);

} // AddAut
