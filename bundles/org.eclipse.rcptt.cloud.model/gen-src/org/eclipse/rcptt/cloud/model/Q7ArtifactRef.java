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
 * A representation of the model object '<em><b>Q7 Artifact Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getHash <em>Hash</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getRefs <em>Refs</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getQ7ArtifactRef()
 * @model
 * @generated
 */
public interface Q7ArtifactRef extends EObject {
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
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getQ7ArtifactRef_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hash</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hash</em>' attribute.
	 * @see #setHash(byte[])
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getQ7ArtifactRef_Hash()
	 * @model
	 * @generated
	 */
	byte[] getHash();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getHash <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hash</em>' attribute.
	 * @see #getHash()
	 * @generated
	 */
	void setHash(byte[] value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.cloud.model.RefKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.model.RefKind
	 * @see #setKind(RefKind)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getQ7ArtifactRef_Kind()
	 * @model
	 * @generated
	 */
	RefKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.model.RefKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(RefKind value);

	/**
	 * Returns the value of the '<em><b>Refs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refs</em>' attribute list.
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getQ7ArtifactRef_Refs()
	 * @model
	 * @generated
	 */
	EList<String> getRefs();

} // Q7ArtifactRef
