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
package org.eclipse.rcptt.cloud.agent.commands;

import org.eclipse.rcptt.ecl.core.Command;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Update Agent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.agent.commands.UpdateAgent#getRepo <em>Repo</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.agent.commands.CommandsPackage#getUpdateAgent()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface UpdateAgent extends Command {
	/**
	 * Returns the value of the '<em><b>Repo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repo</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repo</em>' attribute.
	 * @see #setRepo(String)
	 * @see org.eclipse.rcptt.cloud.agent.commands.CommandsPackage#getUpdateAgent_Repo()
	 * @model
	 * @generated
	 */
	String getRepo();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.agent.commands.UpdateAgent#getRepo <em>Repo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repo</em>' attribute.
	 * @see #getRepo()
	 * @generated
	 */
	void setRepo(String value);

} // UpdateAgent
