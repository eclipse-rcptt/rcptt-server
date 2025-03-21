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
import org.eclipse.emf.ecore.EObject;

import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.TestSuite;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuite <em>Suite</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getAut <em>Aut</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getTestOptions <em>Test Options</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getTask()
 * @model
 * @generated
 */
public interface Task extends EObject {
	/**
	 * Returns the value of the '<em><b>Suite</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suite</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suite</em>' containment reference.
	 * @see #setSuite(TestSuite)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getTask_Suite()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TestSuite getSuite();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuite <em>Suite</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite</em>' containment reference.
	 * @see #getSuite()
	 * @generated
	 */
	void setSuite(TestSuite value);

	/**
	 * Returns the value of the '<em><b>Aut</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aut</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aut</em>' containment reference.
	 * @see #setAut(AutInfo)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getTask_Aut()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AutInfo getAut();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getAut <em>Aut</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aut</em>' containment reference.
	 * @see #getAut()
	 * @generated
	 */
	void setAut(AutInfo value);

	/**
	 * Returns the value of the '<em><b>Test Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Options</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Options</em>' containment reference.
	 * @see #setTestOptions(TestOptions)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getTask_TestOptions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TestOptions getTestOptions();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getTestOptions <em>Test Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Options</em>' containment reference.
	 * @see #getTestOptions()
	 * @generated
	 */
	void setTestOptions(TestOptions value);

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.cloud.model.Q7Artifact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifacts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts</em>' containment reference list.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getTask_Artifacts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Q7Artifact> getArtifacts();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getTask_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suite Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suite Id</em>' attribute.
	 * @see #setSuiteId(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getTask_SuiteId()
	 * @model required="true"
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

} // Task
