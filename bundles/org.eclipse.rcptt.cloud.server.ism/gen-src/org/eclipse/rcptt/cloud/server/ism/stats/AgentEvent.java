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
package org.eclipse.rcptt.cloud.server.ism.stats;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getDate <em>Date</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getMsg <em>Msg</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentEvent()
 * @model
 * @generated
 */
public interface AgentEvent extends EObject {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentEvent_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

	/**
	 * Returns the value of the '<em><b>Msg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Msg</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Msg</em>' attribute.
	 * @see #setMsg(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentEvent_Msg()
	 * @model
	 * @generated
	 */
	String getMsg();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getMsg <em>Msg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Msg</em>' attribute.
	 * @see #getMsg()
	 * @generated
	 */
	void setMsg(String value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind
	 * @see #setKind(AgentEventKind)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentEvent_Kind()
	 * @model
	 * @generated
	 */
	AgentEventKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(AgentEventKind value);

} // AgentEvent
