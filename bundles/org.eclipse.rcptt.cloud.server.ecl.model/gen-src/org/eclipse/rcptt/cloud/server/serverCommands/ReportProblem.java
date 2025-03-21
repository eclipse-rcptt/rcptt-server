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

import org.eclipse.rcptt.ecl.core.ProcessStatus;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Report Problem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem#getCause <em>Cause</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getReportProblem()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface ReportProblem extends AgentCommand {
	/**
	 * Returns the value of the '<em><b>Cause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cause</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cause</em>' containment reference.
	 * @see #setCause(ProcessStatus)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getReportProblem_Cause()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ProcessStatus getCause();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem#getCause <em>Cause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cause</em>' containment reference.
	 * @see #getCause()
	 * @generated
	 */
	void setCause(ProcessStatus value);

} // ReportProblem
