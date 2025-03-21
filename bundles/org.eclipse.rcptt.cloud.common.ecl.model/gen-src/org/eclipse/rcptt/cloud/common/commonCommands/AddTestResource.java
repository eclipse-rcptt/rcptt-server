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

import org.eclipse.rcptt.cloud.model.Q7Artifact;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Test Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getArtifactsPath <em>Artifacts Path</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getSuiteId <em>Suite Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddTestResource()
 * @model annotation="http://www.eclipse.org/ecl/internal"
 * @generated
 */
public interface AddTestResource extends Command {
	/**
	 * Returns the value of the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' containment reference.
	 * @see #setResource(Q7Artifact)
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddTestResource_Resource()
	 * @model containment="true"
	 * @generated
	 */
	Q7Artifact getResource();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getResource <em>Resource</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' containment reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Q7Artifact value);

	/**
	 * Returns the value of the '<em><b>Artifacts Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifacts Path</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts Path</em>' attribute.
	 * @see #setArtifactsPath(String)
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddTestResource_ArtifactsPath()
	 * @model
	 * @generated
	 */
	String getArtifactsPath();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getArtifactsPath <em>Artifacts Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifacts Path</em>' attribute.
	 * @see #getArtifactsPath()
	 * @generated
	 */
	void setArtifactsPath(String value);

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
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage#getAddTestResource_SuiteId()
	 * @model
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

} // AddTestResource
