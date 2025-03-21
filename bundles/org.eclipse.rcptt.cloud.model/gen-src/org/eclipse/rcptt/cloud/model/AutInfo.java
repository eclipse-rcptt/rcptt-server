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
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aut Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getHash <em>Hash</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getInjection <em>Injection</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#isIgnoreOtherInjections <em>Ignore Other Injections</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getArgs <em>Args</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getVmArgs <em>Vm Args</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.model.AutInfo#getLicenseUrl <em>License Url</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo()
 * @model
 * @generated
 */
public interface AutInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hash</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hash</em>' attribute.
	 * @see #setHash(byte[])
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_Hash()
	 * @model
	 * @generated
	 */
	byte[] getHash();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AutInfo#getHash <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hash</em>' attribute.
	 * @see #getHash()
	 * @generated
	 */
	void setHash(byte[] value);

	/**
	 * Returns the value of the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classifier</em>' attribute.
	 * @see #setClassifier(String)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_Classifier()
	 * @model
	 * @generated
	 */
	String getClassifier();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AutInfo#getClassifier <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier</em>' attribute.
	 * @see #getClassifier()
	 * @generated
	 */
	void setClassifier(String value);

	/**
	 * Returns the value of the '<em><b>Injection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Injection</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Injection</em>' containment reference.
	 * @see #setInjection(InjectionConfiguration)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_Injection()
	 * @model containment="true"
	 * @generated
	 */
	InjectionConfiguration getInjection();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AutInfo#getInjection <em>Injection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Injection</em>' containment reference.
	 * @see #getInjection()
	 * @generated
	 */
	void setInjection(InjectionConfiguration value);

	/**
	 * Returns the value of the '<em><b>Ignore Other Injections</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignore Other Injections</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignore Other Injections</em>' attribute.
	 * @see #setIgnoreOtherInjections(boolean)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_IgnoreOtherInjections()
	 * @model default="false"
	 * @generated
	 */
	boolean isIgnoreOtherInjections();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AutInfo#isIgnoreOtherInjections <em>Ignore Other Injections</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignore Other Injections</em>' attribute.
	 * @see #isIgnoreOtherInjections()
	 * @generated
	 */
	void setIgnoreOtherInjections(boolean value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AutInfo#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Args</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Args</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Args</em>' attribute list.
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_Args()
	 * @model
	 * @generated
	 */
	EList<String> getArgs();

	/**
	 * Returns the value of the '<em><b>Vm Args</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vm Args</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vm Args</em>' attribute list.
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_VmArgs()
	 * @model
	 * @generated
	 */
	EList<String> getVmArgs();

	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_Uri()
	 * @model
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AutInfo#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

	/**
	 * Returns the value of the '<em><b>License Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>License Url</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License Url</em>' attribute.
	 * @see #setLicenseUrl(String)
	 * @see org.eclipse.rcptt.cloud.model.ModelPackage#getAutInfo_LicenseUrl()
	 * @model
	 * @generated
	 */
	String getLicenseUrl();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.model.AutInfo#getLicenseUrl <em>License Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License Url</em>' attribute.
	 * @see #getLicenseUrl()
	 * @generated
	 */
	void setLicenseUrl(String value);

} // AutInfo
