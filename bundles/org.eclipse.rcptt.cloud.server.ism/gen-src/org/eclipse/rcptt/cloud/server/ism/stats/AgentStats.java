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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent Stats</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTotalCount <em>Total Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getFailedCount <em>Failed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getRevertCount <em>Revert Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getEvents <em>Events</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTakenTasks <em>Taken Tasks</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getLaunchID <em>Launch ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getSkipTags <em>Skip Tags</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats()
 * @model
 * @generated
 */
public interface AgentStats extends EObject {
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
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_Uri()
	 * @model
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

	/**
	 * Returns the value of the '<em><b>Total Count</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Count</em>' attribute.
	 * @see #setTotalCount(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_TotalCount()
	 * @model default="0"
	 * @generated
	 */
	int getTotalCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTotalCount <em>Total Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Count</em>' attribute.
	 * @see #getTotalCount()
	 * @generated
	 */
	void setTotalCount(int value);

	/**
	 * Returns the value of the '<em><b>Failed Count</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Failed Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failed Count</em>' attribute.
	 * @see #setFailedCount(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_FailedCount()
	 * @model default="0"
	 * @generated
	 */
	int getFailedCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getFailedCount <em>Failed Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failed Count</em>' attribute.
	 * @see #getFailedCount()
	 * @generated
	 */
	void setFailedCount(int value);

	/**
	 * Returns the value of the '<em><b>Revert Count</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Revert Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Revert Count</em>' attribute.
	 * @see #setRevertCount(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_RevertCount()
	 * @model default="0"
	 * @generated
	 */
	int getRevertCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getRevertCount <em>Revert Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Revert Count</em>' attribute.
	 * @see #getRevertCount()
	 * @generated
	 */
	void setRevertCount(int value);

	/**
	 * Returns the value of the '<em><b>Events</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' reference list.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_Events()
	 * @model
	 * @generated
	 */
	EList<AgentEvent> getEvents();

	/**
	 * Returns the value of the '<em><b>Taken Tasks</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Taken Tasks</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Taken Tasks</em>' attribute.
	 * @see #setTakenTasks(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_TakenTasks()
	 * @model default="0"
	 * @generated
	 */
	int getTakenTasks();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTakenTasks <em>Taken Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Taken Tasks</em>' attribute.
	 * @see #getTakenTasks()
	 * @generated
	 */
	void setTakenTasks(int value);

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
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_Classifier()
	 * @model
	 * @generated
	 */
	String getClassifier();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getClassifier <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier</em>' attribute.
	 * @see #getClassifier()
	 * @generated
	 */
	void setClassifier(String value);

	/**
	 * Returns the value of the '<em><b>Launch ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Launch ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Launch ID</em>' attribute.
	 * @see #setLaunchID(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_LaunchID()
	 * @model
	 * @generated
	 */
	String getLaunchID();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getLaunchID <em>Launch ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Launch ID</em>' attribute.
	 * @see #getLaunchID()
	 * @generated
	 */
	void setLaunchID(String value);

	/**
	 * Returns the value of the '<em><b>Skip Tags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Tags</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Tags</em>' attribute.
	 * @see #setSkipTags(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getAgentStats_SkipTags()
	 * @model
	 * @generated
	 */
	String getSkipTags();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getSkipTags <em>Skip Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip Tags</em>' attribute.
	 * @see #getSkipTags()
	 * @generated
	 */
	void setSkipTags(String value);

} // AgentStats
