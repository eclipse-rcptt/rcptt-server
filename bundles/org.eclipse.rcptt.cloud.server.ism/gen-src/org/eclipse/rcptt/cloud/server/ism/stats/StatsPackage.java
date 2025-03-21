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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsFactory
 * @model kind="package"
 * @generated
 */
public interface StatsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "stats";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///com/xored/q7/cloud/server/ism/stats.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "com.xored.q7.cloud.server.ism.stats";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatsPackage eINSTANCE = org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.SuiteStatsImpl <em>Suite Stats</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.SuiteStatsImpl
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getSuiteStats()
	 * @generated
	 */
	int SUITE_STATS = 0;

	/**
	 * The feature id for the '<em><b>Suite Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_STATS__SUITE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Last Suite ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_STATS__LAST_SUITE_ID = 1;

	/**
	 * The number of structural features of the '<em>Suite Stats</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_STATS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl <em>Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecution()
	 * @generated
	 */
	int EXECUTION = 1;

	/**
	 * The feature id for the '<em><b>Total Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__TOTAL_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Failed Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__FAILED_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__START_TIME = 2;

	/**
	 * The feature id for the '<em><b>End Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__END_TIME = 3;

	/**
	 * The feature id for the '<em><b>First Report Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__FIRST_REPORT_TIME = 4;

	/**
	 * The feature id for the '<em><b>Report File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__REPORT_FILE = 5;

	/**
	 * The feature id for the '<em><b>Agent Stats</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__AGENT_STATS = 6;

	/**
	 * The feature id for the '<em><b>Execution Child Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__EXECUTION_CHILD_NAME = 7;

	/**
	 * The feature id for the '<em><b>Aut Artifacts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__AUT_ARTIFACTS = 8;

	/**
	 * The feature id for the '<em><b>Metadata Artifacts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__METADATA_ARTIFACTS = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__ID = 10;

	/**
	 * The feature id for the '<em><b>Canceled Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__CANCELED_COUNT = 11;

	/**
	 * The feature id for the '<em><b>Passed Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__PASSED_COUNT = 12;

	/**
	 * The feature id for the '<em><b>Executed Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__EXECUTED_COUNT = 13;

	/**
	 * The feature id for the '<em><b>Global ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__GLOBAL_ID = 14;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__STATE = 15;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__SUITE_ID = 16;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__METADATA = 17;

	/**
	 * The feature id for the '<em><b>Aut Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION__AUT_NAMES = 18;

	/**
	 * The number of structural features of the '<em>Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_FEATURE_COUNT = 19;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentStatsImpl <em>Execution Agent Stats</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentStatsImpl
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionAgentStats()
	 * @generated
	 */
	int EXECUTION_AGENT_STATS = 2;

	/**
	 * The feature id for the '<em><b>Agent ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_STATS__AGENT_ID = 0;

	/**
	 * The feature id for the '<em><b>Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_STATS__TESTS = 1;

	/**
	 * The number of structural features of the '<em>Execution Agent Stats</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_STATS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl <em>Execution Agent Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionAgentTest()
	 * @generated
	 */
	int EXECUTION_AGENT_TEST = 3;

	/**
	 * The feature id for the '<em><b>Test ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_TEST__TEST_ID = 0;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_TEST__STATUS = 1;

	/**
	 * The feature id for the '<em><b>Test Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_TEST__TEST_NAME = 2;

	/**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_TEST__START_TIME = 3;

	/**
	 * The feature id for the '<em><b>End Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_TEST__END_TIME = 4;

	/**
	 * The number of structural features of the '<em>Execution Agent Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_AGENT_TEST_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl <em>Agent Stats</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getAgentStats()
	 * @generated
	 */
	int AGENT_STATS = 4;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__URI = 0;

	/**
	 * The feature id for the '<em><b>Total Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__TOTAL_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Failed Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__FAILED_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Revert Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__REVERT_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__EVENTS = 4;

	/**
	 * The feature id for the '<em><b>Taken Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__TAKEN_TASKS = 5;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__CLASSIFIER = 6;

	/**
	 * The feature id for the '<em><b>Launch ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__LAUNCH_ID = 7;

	/**
	 * The feature id for the '<em><b>Skip Tags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS__SKIP_TAGS = 8;

	/**
	 * The number of structural features of the '<em>Agent Stats</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_STATS_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentEventImpl <em>Agent Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentEventImpl
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getAgentEvent()
	 * @generated
	 */
	int AGENT_EVENT = 5;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_EVENT__DATE = 0;

	/**
	 * The feature id for the '<em><b>Msg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_EVENT__MSG = 1;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_EVENT__KIND = 2;

	/**
	 * The number of structural features of the '<em>Agent Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_EVENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.MetadataEntryImpl <em>Metadata Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.MetadataEntryImpl
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getMetadataEntry()
	 * @generated
	 */
	int METADATA_ENTRY = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Metadata Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus <em>Execution Agent Test Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionAgentTestStatus()
	 * @generated
	 */
	int EXECUTION_AGENT_TEST_STATUS = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind <em>Agent Event Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getAgentEventKind()
	 * @generated
	 */
	int AGENT_EVENT_KIND = 8;


	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState <em>Execution State</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionState()
	 * @generated
	 */
	int EXECUTION_STATE = 9;


	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats <em>Suite Stats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Suite Stats</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats
	 * @generated
	 */
	EClass getSuiteStats();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getSuiteName <em>Suite Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Name</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getSuiteName()
	 * @see #getSuiteStats()
	 * @generated
	 */
	EAttribute getSuiteStats_SuiteName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getLastSuiteID <em>Last Suite ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Suite ID</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats#getLastSuiteID()
	 * @see #getSuiteStats()
	 * @generated
	 */
	EAttribute getSuiteStats_LastSuiteID();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution <em>Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution
	 * @generated
	 */
	EClass getExecution();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getTotalCount <em>Total Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getTotalCount()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_TotalCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFailedCount <em>Failed Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failed Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFailedCount()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_FailedCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getStartTime()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_StartTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getEndTime <em>End Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Time</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getEndTime()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_EndTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFirstReportTime <em>First Report Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Report Time</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getFirstReportTime()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_FirstReportTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getReportFile <em>Report File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Report File</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getReportFile()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_ReportFile();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAgentStats <em>Agent Stats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Agent Stats</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAgentStats()
	 * @see #getExecution()
	 * @generated
	 */
	EReference getExecution_AgentStats();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutionChildName <em>Execution Child Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution Child Name</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutionChildName()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_ExecutionChildName();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAutArtifacts <em>Aut Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Aut Artifacts</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAutArtifacts()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_AutArtifacts();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getMetadataArtifacts <em>Metadata Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Metadata Artifacts</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getMetadataArtifacts()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_MetadataArtifacts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getId()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getCanceledCount <em>Canceled Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Canceled Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getCanceledCount()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_CanceledCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getPassedCount <em>Passed Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Passed Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getPassedCount()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_PassedCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutedCount <em>Executed Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Executed Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getExecutedCount()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_ExecutedCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getGlobalID <em>Global ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Global ID</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getGlobalID()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_GlobalID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getState()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_State();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getSuiteId()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_SuiteId();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getMetadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Metadata</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getMetadata()
	 * @see #getExecution()
	 * @generated
	 */
	EReference getExecution_Metadata();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAutNames <em>Aut Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Aut Names</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.Execution#getAutNames()
	 * @see #getExecution()
	 * @generated
	 */
	EAttribute getExecution_AutNames();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats <em>Execution Agent Stats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Agent Stats</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats
	 * @generated
	 */
	EClass getExecutionAgentStats();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats#getAgentID <em>Agent ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Agent ID</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats#getAgentID()
	 * @see #getExecutionAgentStats()
	 * @generated
	 */
	EAttribute getExecutionAgentStats_AgentID();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats#getTests <em>Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tests</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats#getTests()
	 * @see #getExecutionAgentStats()
	 * @generated
	 */
	EReference getExecutionAgentStats_Tests();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest <em>Execution Agent Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Agent Test</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest
	 * @generated
	 */
	EClass getExecutionAgentTest();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestID <em>Test ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test ID</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestID()
	 * @see #getExecutionAgentTest()
	 * @generated
	 */
	EAttribute getExecutionAgentTest_TestID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStatus()
	 * @see #getExecutionAgentTest()
	 * @generated
	 */
	EAttribute getExecutionAgentTest_Status();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestName <em>Test Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Name</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getTestName()
	 * @see #getExecutionAgentTest()
	 * @generated
	 */
	EAttribute getExecutionAgentTest_TestName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getStartTime()
	 * @see #getExecutionAgentTest()
	 * @generated
	 */
	EAttribute getExecutionAgentTest_StartTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getEndTime <em>End Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Time</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest#getEndTime()
	 * @see #getExecutionAgentTest()
	 * @generated
	 */
	EAttribute getExecutionAgentTest_EndTime();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats <em>Agent Stats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent Stats</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats
	 * @generated
	 */
	EClass getAgentStats();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getUri()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTotalCount <em>Total Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTotalCount()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_TotalCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getFailedCount <em>Failed Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failed Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getFailedCount()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_FailedCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getRevertCount <em>Revert Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Revert Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getRevertCount()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_RevertCount();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Events</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getEvents()
	 * @see #getAgentStats()
	 * @generated
	 */
	EReference getAgentStats_Events();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTakenTasks <em>Taken Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Taken Tasks</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getTakenTasks()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_TakenTasks();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classifier</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getClassifier()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_Classifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getLaunchID <em>Launch ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Launch ID</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getLaunchID()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_LaunchID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getSkipTags <em>Skip Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Skip Tags</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentStats#getSkipTags()
	 * @see #getAgentStats()
	 * @generated
	 */
	EAttribute getAgentStats_SkipTags();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent <em>Agent Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent Event</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent
	 * @generated
	 */
	EClass getAgentEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getDate()
	 * @see #getAgentEvent()
	 * @generated
	 */
	EAttribute getAgentEvent_Date();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getMsg <em>Msg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Msg</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getMsg()
	 * @see #getAgentEvent()
	 * @generated
	 */
	EAttribute getAgentEvent_Msg();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent#getKind()
	 * @see #getAgentEvent()
	 * @generated
	 */
	EAttribute getAgentEvent_Kind();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Metadata Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metadata Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getMetadataEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMetadataEntry()
	 * @generated
	 */
	EAttribute getMetadataEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMetadataEntry()
	 * @generated
	 */
	EAttribute getMetadataEntry_Value();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus <em>Execution Agent Test Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Execution Agent Test Status</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus
	 * @generated
	 */
	EEnum getExecutionAgentTestStatus();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind <em>Agent Event Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Agent Event Kind</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind
	 * @generated
	 */
	EEnum getAgentEventKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState <em>Execution State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Execution State</em>'.
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState
	 * @generated
	 */
	EEnum getExecutionState();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StatsFactory getStatsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.SuiteStatsImpl <em>Suite Stats</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.SuiteStatsImpl
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getSuiteStats()
		 * @generated
		 */
		EClass SUITE_STATS = eINSTANCE.getSuiteStats();

		/**
		 * The meta object literal for the '<em><b>Suite Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUITE_STATS__SUITE_NAME = eINSTANCE.getSuiteStats_SuiteName();

		/**
		 * The meta object literal for the '<em><b>Last Suite ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUITE_STATS__LAST_SUITE_ID = eINSTANCE.getSuiteStats_LastSuiteID();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl <em>Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecution()
		 * @generated
		 */
		EClass EXECUTION = eINSTANCE.getExecution();

		/**
		 * The meta object literal for the '<em><b>Total Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__TOTAL_COUNT = eINSTANCE.getExecution_TotalCount();

		/**
		 * The meta object literal for the '<em><b>Failed Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__FAILED_COUNT = eINSTANCE.getExecution_FailedCount();

		/**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__START_TIME = eINSTANCE.getExecution_StartTime();

		/**
		 * The meta object literal for the '<em><b>End Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__END_TIME = eINSTANCE.getExecution_EndTime();

		/**
		 * The meta object literal for the '<em><b>First Report Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__FIRST_REPORT_TIME = eINSTANCE.getExecution_FirstReportTime();

		/**
		 * The meta object literal for the '<em><b>Report File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__REPORT_FILE = eINSTANCE.getExecution_ReportFile();

		/**
		 * The meta object literal for the '<em><b>Agent Stats</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION__AGENT_STATS = eINSTANCE.getExecution_AgentStats();

		/**
		 * The meta object literal for the '<em><b>Execution Child Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__EXECUTION_CHILD_NAME = eINSTANCE.getExecution_ExecutionChildName();

		/**
		 * The meta object literal for the '<em><b>Aut Artifacts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__AUT_ARTIFACTS = eINSTANCE.getExecution_AutArtifacts();

		/**
		 * The meta object literal for the '<em><b>Metadata Artifacts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__METADATA_ARTIFACTS = eINSTANCE.getExecution_MetadataArtifacts();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__ID = eINSTANCE.getExecution_Id();

		/**
		 * The meta object literal for the '<em><b>Canceled Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__CANCELED_COUNT = eINSTANCE.getExecution_CanceledCount();

		/**
		 * The meta object literal for the '<em><b>Passed Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__PASSED_COUNT = eINSTANCE.getExecution_PassedCount();

		/**
		 * The meta object literal for the '<em><b>Executed Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__EXECUTED_COUNT = eINSTANCE.getExecution_ExecutedCount();

		/**
		 * The meta object literal for the '<em><b>Global ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__GLOBAL_ID = eINSTANCE.getExecution_GlobalID();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__STATE = eINSTANCE.getExecution_State();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__SUITE_ID = eINSTANCE.getExecution_SuiteId();

		/**
		 * The meta object literal for the '<em><b>Metadata</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION__METADATA = eINSTANCE.getExecution_Metadata();

		/**
		 * The meta object literal for the '<em><b>Aut Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION__AUT_NAMES = eINSTANCE.getExecution_AutNames();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentStatsImpl <em>Execution Agent Stats</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentStatsImpl
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionAgentStats()
		 * @generated
		 */
		EClass EXECUTION_AGENT_STATS = eINSTANCE.getExecutionAgentStats();

		/**
		 * The meta object literal for the '<em><b>Agent ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_AGENT_STATS__AGENT_ID = eINSTANCE.getExecutionAgentStats_AgentID();

		/**
		 * The meta object literal for the '<em><b>Tests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_AGENT_STATS__TESTS = eINSTANCE.getExecutionAgentStats_Tests();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl <em>Execution Agent Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionAgentTestImpl
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionAgentTest()
		 * @generated
		 */
		EClass EXECUTION_AGENT_TEST = eINSTANCE.getExecutionAgentTest();

		/**
		 * The meta object literal for the '<em><b>Test ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_AGENT_TEST__TEST_ID = eINSTANCE.getExecutionAgentTest_TestID();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_AGENT_TEST__STATUS = eINSTANCE.getExecutionAgentTest_Status();

		/**
		 * The meta object literal for the '<em><b>Test Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_AGENT_TEST__TEST_NAME = eINSTANCE.getExecutionAgentTest_TestName();

		/**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_AGENT_TEST__START_TIME = eINSTANCE.getExecutionAgentTest_StartTime();

		/**
		 * The meta object literal for the '<em><b>End Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_AGENT_TEST__END_TIME = eINSTANCE.getExecutionAgentTest_EndTime();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl <em>Agent Stats</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getAgentStats()
		 * @generated
		 */
		EClass AGENT_STATS = eINSTANCE.getAgentStats();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__URI = eINSTANCE.getAgentStats_Uri();

		/**
		 * The meta object literal for the '<em><b>Total Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__TOTAL_COUNT = eINSTANCE.getAgentStats_TotalCount();

		/**
		 * The meta object literal for the '<em><b>Failed Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__FAILED_COUNT = eINSTANCE.getAgentStats_FailedCount();

		/**
		 * The meta object literal for the '<em><b>Revert Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__REVERT_COUNT = eINSTANCE.getAgentStats_RevertCount();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT_STATS__EVENTS = eINSTANCE.getAgentStats_Events();

		/**
		 * The meta object literal for the '<em><b>Taken Tasks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__TAKEN_TASKS = eINSTANCE.getAgentStats_TakenTasks();

		/**
		 * The meta object literal for the '<em><b>Classifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__CLASSIFIER = eINSTANCE.getAgentStats_Classifier();

		/**
		 * The meta object literal for the '<em><b>Launch ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__LAUNCH_ID = eINSTANCE.getAgentStats_LaunchID();

		/**
		 * The meta object literal for the '<em><b>Skip Tags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_STATS__SKIP_TAGS = eINSTANCE.getAgentStats_SkipTags();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentEventImpl <em>Agent Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentEventImpl
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getAgentEvent()
		 * @generated
		 */
		EClass AGENT_EVENT = eINSTANCE.getAgentEvent();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_EVENT__DATE = eINSTANCE.getAgentEvent_Date();

		/**
		 * The meta object literal for the '<em><b>Msg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_EVENT__MSG = eINSTANCE.getAgentEvent_Msg();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_EVENT__KIND = eINSTANCE.getAgentEvent_Kind();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.MetadataEntryImpl <em>Metadata Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.MetadataEntryImpl
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getMetadataEntry()
		 * @generated
		 */
		EClass METADATA_ENTRY = eINSTANCE.getMetadataEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA_ENTRY__KEY = eINSTANCE.getMetadataEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA_ENTRY__VALUE = eINSTANCE.getMetadataEntry_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus <em>Execution Agent Test Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionAgentTestStatus()
		 * @generated
		 */
		EEnum EXECUTION_AGENT_TEST_STATUS = eINSTANCE.getExecutionAgentTestStatus();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind <em>Agent Event Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getAgentEventKind()
		 * @generated
		 */
		EEnum AGENT_EVENT_KIND = eINSTANCE.getAgentEventKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState <em>Execution State</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState
		 * @see org.eclipse.rcptt.cloud.server.ism.stats.impl.StatsPackageImpl#getExecutionState()
		 * @generated
		 */
		EEnum EXECUTION_STATE = eINSTANCE.getExecutionState();

	}

} //StatsPackage
