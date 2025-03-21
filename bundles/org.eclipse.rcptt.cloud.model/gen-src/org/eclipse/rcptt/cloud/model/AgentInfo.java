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
package org.eclipse.rcptt.cloud.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AgentInfo#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AgentInfo#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AgentInfo#getLaunchId <em>Launch Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AgentInfo#getSkipTags <em>Skip Tags</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAgentInfo()
 * @model
 * @generated
 */
public interface AgentInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAgentInfo_Uri()
	 * @model
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AgentInfo#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

	/**
	 * Returns the value of the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classifier</em>' attribute.
	 * @see #setClassifier(String)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAgentInfo_Classifier()
	 * @model
	 * @generated
	 */
	String getClassifier();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AgentInfo#getClassifier <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier</em>' attribute.
	 * @see #getClassifier()
	 * @generated
	 */
	void setClassifier(String value);

	/**
	 * Returns the value of the '<em><b>Launch Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Launch Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Launch Id</em>' attribute.
	 * @see #setLaunchId(String)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAgentInfo_LaunchId()
	 * @model
	 * @generated
	 */
	String getLaunchId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AgentInfo#getLaunchId <em>Launch Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Launch Id</em>' attribute.
	 * @see #getLaunchId()
	 * @generated
	 */
	void setLaunchId(String value);

	/**
	 * Returns the value of the '<em><b>Skip Tags</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Tags</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Tags</em>' attribute list.
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAgentInfo_SkipTags()
	 * @model
	 * @generated
	 */
	EList<String> getSkipTags();

} // AgentInfo
