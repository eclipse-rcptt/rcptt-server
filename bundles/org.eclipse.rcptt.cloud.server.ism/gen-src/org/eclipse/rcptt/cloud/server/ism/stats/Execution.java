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
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getTotalCount <em>Total Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFailedCount <em>Failed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getEndTime <em>End Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFirstReportTime <em>First Report Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getReportFile <em>Report File</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAgentStats <em>Agent Stats</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutionChildName <em>Execution Child Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAutArtifacts <em>Aut Artifacts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getMetadataArtifacts <em>Metadata Artifacts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getCanceledCount <em>Canceled Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getPassedCount <em>Passed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutedCount <em>Executed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getGlobalID <em>Global ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getState <em>State</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAutNames <em>Aut Names</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution()
 * @model
 * @generated
 */
public interface Execution extends EObject {
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
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_TotalCount()
	 * @model default="0"
	 * @generated
	 */
	int getTotalCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getTotalCount <em>Total Count</em>}' attribute.
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
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_FailedCount()
	 * @model default="0"
	 * @generated
	 */
	int getFailedCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFailedCount <em>Failed Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failed Count</em>' attribute.
	 * @see #getFailedCount()
	 * @generated
	 */
	void setFailedCount(int value);

	/**
	 * Returns the value of the '<em><b>Start Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Time</em>' attribute.
	 * @see #setStartTime(long)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_StartTime()
	 * @model default="0"
	 * @generated
	 */
	long getStartTime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getStartTime <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Time</em>' attribute.
	 * @see #getStartTime()
	 * @generated
	 */
	void setStartTime(long value);

	/**
	 * Returns the value of the '<em><b>End Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Time</em>' attribute.
	 * @see #setEndTime(long)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_EndTime()
	 * @model default="0"
	 * @generated
	 */
	long getEndTime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getEndTime <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Time</em>' attribute.
	 * @see #getEndTime()
	 * @generated
	 */
	void setEndTime(long value);

	/**
	 * Returns the value of the '<em><b>First Report Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Report Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Report Time</em>' attribute.
	 * @see #setFirstReportTime(long)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_FirstReportTime()
	 * @model default="0"
	 * @generated
	 */
	long getFirstReportTime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFirstReportTime <em>First Report Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Report Time</em>' attribute.
	 * @see #getFirstReportTime()
	 * @generated
	 */
	void setFirstReportTime(long value);

	/**
	 * Returns the value of the '<em><b>Report File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Report File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Report File</em>' attribute.
	 * @see #setReportFile(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_ReportFile()
	 * @model
	 * @generated
	 */
	String getReportFile();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getReportFile <em>Report File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Report File</em>' attribute.
	 * @see #getReportFile()
	 * @generated
	 */
	void setReportFile(String value);

	/**
	 * Returns the value of the '<em><b>Agent Stats</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Agent Stats</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agent Stats</em>' containment reference list.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_AgentStats()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExecutionAgentStats> getAgentStats();

	/**
	 * Returns the value of the '<em><b>Execution Child Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Child Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Child Name</em>' attribute.
	 * @see #setExecutionChildName(String)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_ExecutionChildName()
	 * @model
	 * @generated
	 */
	String getExecutionChildName();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutionChildName <em>Execution Child Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Child Name</em>' attribute.
	 * @see #getExecutionChildName()
	 * @generated
	 */
	void setExecutionChildName(String value);

	/**
	 * Returns the value of the '<em><b>Aut Artifacts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aut Artifacts</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aut Artifacts</em>' attribute list.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_AutArtifacts()
	 * @model
	 * @generated
	 */
	EList<String> getAutArtifacts();

	/**
	 * Returns the value of the '<em><b>Metadata Artifacts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata Artifacts</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata Artifacts</em>' attribute list.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_MetadataArtifacts()
	 * @model
	 * @generated
	 */
	EList<String> getMetadataArtifacts();

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
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Canceled Count</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Canceled Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Canceled Count</em>' attribute.
	 * @see #setCanceledCount(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_CanceledCount()
	 * @model default="0"
	 * @generated
	 */
	int getCanceledCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getCanceledCount <em>Canceled Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Canceled Count</em>' attribute.
	 * @see #getCanceledCount()
	 * @generated
	 */
	void setCanceledCount(int value);

	/**
	 * Returns the value of the '<em><b>Passed Count</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Passed Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Passed Count</em>' attribute.
	 * @see #setPassedCount(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_PassedCount()
	 * @model default="0"
	 * @generated
	 */
	int getPassedCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getPassedCount <em>Passed Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Passed Count</em>' attribute.
	 * @see #getPassedCount()
	 * @generated
	 */
	void setPassedCount(int value);

	/**
	 * Returns the value of the '<em><b>Executed Count</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executed Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executed Count</em>' attribute.
	 * @see #setExecutedCount(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_ExecutedCount()
	 * @model default="0"
	 * @generated
	 */
	int getExecutedCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutedCount <em>Executed Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executed Count</em>' attribute.
	 * @see #getExecutedCount()
	 * @generated
	 */
	void setExecutedCount(int value);

	/**
	 * Returns the value of the '<em><b>Global ID</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global ID</em>' attribute.
	 * @see #setGlobalID(int)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_GlobalID()
	 * @model default="-1"
	 * @generated
	 */
	int getGlobalID();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getGlobalID <em>Global ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global ID</em>' attribute.
	 * @see #getGlobalID()
	 * @generated
	 */
	void setGlobalID(int value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The default value is <code>"COMPLETED"</code>.
	 * The literals are from the enumeration {@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState
	 * @see #setState(ExecutionState)
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_State()
	 * @model default="COMPLETED"
	 * @generated
	 */
	ExecutionState getState();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState
	 * @see #getState()
	 * @generated
	 */
	void setState(ExecutionState value);

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
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_SuiteId()
	 * @model
	 * @generated
	 */
	String getSuiteId();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getSuiteId <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suite Id</em>' attribute.
	 * @see #getSuiteId()
	 * @generated
	 */
	void setSuiteId(String value);

	/**
	 * Returns the value of the '<em><b>Metadata</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata</em>' map.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_Metadata()
	 * @model mapType="org.eclipse.rcptt.cloud.server.ism.stats.MetadataEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getMetadata();

	/**
	 * Returns the value of the '<em><b>Aut Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aut Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aut Names</em>' attribute list.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#getExecution_AutNames()
	 * @model
	 * @generated
	 */
	EList<String> getAutNames();

} // Execution
