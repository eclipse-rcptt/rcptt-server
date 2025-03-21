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
package org.eclipse.rcptt.cloud.server.ism.stats.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent;
import org.eclipse.rcptt.cloud.server.ism.stats.AgentEventKind;
import org.eclipse.rcptt.cloud.server.ism.stats.AgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTest;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentTestStatus;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsFactory;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatsPackageImpl extends EPackageImpl implements StatsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass suiteStatsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionAgentStatsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionAgentTestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentStatsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metadataEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum executionAgentTestStatusEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum agentEventKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum executionStateEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StatsPackageImpl() {
		super(eNS_URI, StatsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link StatsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StatsPackage init() {
		if (isInited) return (StatsPackage)EPackage.Registry.INSTANCE.getEPackage(StatsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredStatsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		StatsPackageImpl theStatsPackage = registeredStatsPackage instanceof StatsPackageImpl ? (StatsPackageImpl)registeredStatsPackage : new StatsPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theStatsPackage.createPackageContents();

		// Initialize created meta-data
		theStatsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStatsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StatsPackage.eNS_URI, theStatsPackage);
		return theStatsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuiteStats() {
		return suiteStatsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSuiteStats_SuiteName() {
		return (EAttribute)suiteStatsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSuiteStats_LastSuiteID() {
		return (EAttribute)suiteStatsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecution() {
		return executionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_TotalCount() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_FailedCount() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_StartTime() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_EndTime() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_FirstReportTime() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_ReportFile() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecution_AgentStats() {
		return (EReference)executionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_ExecutionChildName() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_AutArtifacts() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_MetadataArtifacts() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_Id() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_CanceledCount() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_PassedCount() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_ExecutedCount() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_GlobalID() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_State() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_SuiteId() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecution_Metadata() {
		return (EReference)executionEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecution_AutNames() {
		return (EAttribute)executionEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionAgentStats() {
		return executionAgentStatsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionAgentStats_AgentID() {
		return (EAttribute)executionAgentStatsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionAgentStats_Tests() {
		return (EReference)executionAgentStatsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionAgentTest() {
		return executionAgentTestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionAgentTest_TestID() {
		return (EAttribute)executionAgentTestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionAgentTest_Status() {
		return (EAttribute)executionAgentTestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionAgentTest_TestName() {
		return (EAttribute)executionAgentTestEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionAgentTest_StartTime() {
		return (EAttribute)executionAgentTestEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionAgentTest_EndTime() {
		return (EAttribute)executionAgentTestEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAgentStats() {
		return agentStatsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_Uri() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_TotalCount() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_FailedCount() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_RevertCount() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAgentStats_Events() {
		return (EReference)agentStatsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_TakenTasks() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_Classifier() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_LaunchID() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentStats_SkipTags() {
		return (EAttribute)agentStatsEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAgentEvent() {
		return agentEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentEvent_Date() {
		return (EAttribute)agentEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentEvent_Msg() {
		return (EAttribute)agentEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgentEvent_Kind() {
		return (EAttribute)agentEventEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetadataEntry() {
		return metadataEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetadataEntry_Key() {
		return (EAttribute)metadataEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetadataEntry_Value() {
		return (EAttribute)metadataEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getExecutionAgentTestStatus() {
		return executionAgentTestStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAgentEventKind() {
		return agentEventKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getExecutionState() {
		return executionStateEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatsFactory getStatsFactory() {
		return (StatsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		suiteStatsEClass = createEClass(SUITE_STATS);
		createEAttribute(suiteStatsEClass, SUITE_STATS__SUITE_NAME);
		createEAttribute(suiteStatsEClass, SUITE_STATS__LAST_SUITE_ID);

		executionEClass = createEClass(EXECUTION);
		createEAttribute(executionEClass, EXECUTION__TOTAL_COUNT);
		createEAttribute(executionEClass, EXECUTION__FAILED_COUNT);
		createEAttribute(executionEClass, EXECUTION__START_TIME);
		createEAttribute(executionEClass, EXECUTION__END_TIME);
		createEAttribute(executionEClass, EXECUTION__FIRST_REPORT_TIME);
		createEAttribute(executionEClass, EXECUTION__REPORT_FILE);
		createEReference(executionEClass, EXECUTION__AGENT_STATS);
		createEAttribute(executionEClass, EXECUTION__EXECUTION_CHILD_NAME);
		createEAttribute(executionEClass, EXECUTION__AUT_ARTIFACTS);
		createEAttribute(executionEClass, EXECUTION__METADATA_ARTIFACTS);
		createEAttribute(executionEClass, EXECUTION__ID);
		createEAttribute(executionEClass, EXECUTION__CANCELED_COUNT);
		createEAttribute(executionEClass, EXECUTION__PASSED_COUNT);
		createEAttribute(executionEClass, EXECUTION__EXECUTED_COUNT);
		createEAttribute(executionEClass, EXECUTION__GLOBAL_ID);
		createEAttribute(executionEClass, EXECUTION__STATE);
		createEAttribute(executionEClass, EXECUTION__SUITE_ID);
		createEReference(executionEClass, EXECUTION__METADATA);
		createEAttribute(executionEClass, EXECUTION__AUT_NAMES);

		executionAgentStatsEClass = createEClass(EXECUTION_AGENT_STATS);
		createEAttribute(executionAgentStatsEClass, EXECUTION_AGENT_STATS__AGENT_ID);
		createEReference(executionAgentStatsEClass, EXECUTION_AGENT_STATS__TESTS);

		executionAgentTestEClass = createEClass(EXECUTION_AGENT_TEST);
		createEAttribute(executionAgentTestEClass, EXECUTION_AGENT_TEST__TEST_ID);
		createEAttribute(executionAgentTestEClass, EXECUTION_AGENT_TEST__STATUS);
		createEAttribute(executionAgentTestEClass, EXECUTION_AGENT_TEST__TEST_NAME);
		createEAttribute(executionAgentTestEClass, EXECUTION_AGENT_TEST__START_TIME);
		createEAttribute(executionAgentTestEClass, EXECUTION_AGENT_TEST__END_TIME);

		agentStatsEClass = createEClass(AGENT_STATS);
		createEAttribute(agentStatsEClass, AGENT_STATS__URI);
		createEAttribute(agentStatsEClass, AGENT_STATS__TOTAL_COUNT);
		createEAttribute(agentStatsEClass, AGENT_STATS__FAILED_COUNT);
		createEAttribute(agentStatsEClass, AGENT_STATS__REVERT_COUNT);
		createEReference(agentStatsEClass, AGENT_STATS__EVENTS);
		createEAttribute(agentStatsEClass, AGENT_STATS__TAKEN_TASKS);
		createEAttribute(agentStatsEClass, AGENT_STATS__CLASSIFIER);
		createEAttribute(agentStatsEClass, AGENT_STATS__LAUNCH_ID);
		createEAttribute(agentStatsEClass, AGENT_STATS__SKIP_TAGS);

		agentEventEClass = createEClass(AGENT_EVENT);
		createEAttribute(agentEventEClass, AGENT_EVENT__DATE);
		createEAttribute(agentEventEClass, AGENT_EVENT__MSG);
		createEAttribute(agentEventEClass, AGENT_EVENT__KIND);

		metadataEntryEClass = createEClass(METADATA_ENTRY);
		createEAttribute(metadataEntryEClass, METADATA_ENTRY__KEY);
		createEAttribute(metadataEntryEClass, METADATA_ENTRY__VALUE);

		// Create enums
		executionAgentTestStatusEEnum = createEEnum(EXECUTION_AGENT_TEST_STATUS);
		agentEventKindEEnum = createEEnum(AGENT_EVENT_KIND);
		executionStateEEnum = createEEnum(EXECUTION_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(suiteStatsEClass, SuiteStats.class, "SuiteStats", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSuiteStats_SuiteName(), ecorePackage.getEString(), "suiteName", null, 0, 1, SuiteStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSuiteStats_LastSuiteID(), ecorePackage.getEInt(), "lastSuiteID", null, 0, 1, SuiteStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionEClass, Execution.class, "Execution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecution_TotalCount(), ecorePackage.getEInt(), "totalCount", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_FailedCount(), ecorePackage.getEInt(), "failedCount", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_StartTime(), ecorePackage.getELong(), "startTime", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_EndTime(), ecorePackage.getELong(), "endTime", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_FirstReportTime(), ecorePackage.getELong(), "firstReportTime", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_ReportFile(), ecorePackage.getEString(), "reportFile", null, 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecution_AgentStats(), this.getExecutionAgentStats(), null, "agentStats", null, 0, -1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_ExecutionChildName(), ecorePackage.getEString(), "executionChildName", null, 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_AutArtifacts(), ecorePackage.getEString(), "autArtifacts", null, 0, -1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_MetadataArtifacts(), ecorePackage.getEString(), "metadataArtifacts", null, 0, -1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_Id(), ecorePackage.getEString(), "id", null, 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_CanceledCount(), ecorePackage.getEInt(), "canceledCount", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_PassedCount(), ecorePackage.getEInt(), "passedCount", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_ExecutedCount(), ecorePackage.getEInt(), "executedCount", "0", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_GlobalID(), ecorePackage.getEInt(), "globalID", "-1", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_State(), this.getExecutionState(), "state", "COMPLETED", 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_SuiteId(), ecorePackage.getEString(), "suiteId", null, 0, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecution_Metadata(), this.getMetadataEntry(), null, "metadata", null, 0, -1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecution_AutNames(), ecorePackage.getEString(), "autNames", null, 0, -1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionAgentStatsEClass, ExecutionAgentStats.class, "ExecutionAgentStats", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionAgentStats_AgentID(), ecorePackage.getEString(), "agentID", null, 0, 1, ExecutionAgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionAgentStats_Tests(), this.getExecutionAgentTest(), null, "tests", null, 0, -1, ExecutionAgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionAgentTestEClass, ExecutionAgentTest.class, "ExecutionAgentTest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionAgentTest_TestID(), ecorePackage.getEString(), "testID", null, 0, 1, ExecutionAgentTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionAgentTest_Status(), this.getExecutionAgentTestStatus(), "status", null, 0, 1, ExecutionAgentTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionAgentTest_TestName(), ecorePackage.getEString(), "testName", null, 0, 1, ExecutionAgentTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionAgentTest_StartTime(), ecorePackage.getELong(), "startTime", "0", 0, 1, ExecutionAgentTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionAgentTest_EndTime(), ecorePackage.getELong(), "endTime", "0", 0, 1, ExecutionAgentTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agentStatsEClass, AgentStats.class, "AgentStats", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAgentStats_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentStats_TotalCount(), ecorePackage.getEInt(), "totalCount", "0", 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentStats_FailedCount(), ecorePackage.getEInt(), "failedCount", "0", 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentStats_RevertCount(), ecorePackage.getEInt(), "revertCount", "0", 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAgentStats_Events(), this.getAgentEvent(), null, "events", null, 0, -1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentStats_TakenTasks(), ecorePackage.getEInt(), "takenTasks", "0", 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentStats_Classifier(), ecorePackage.getEString(), "classifier", null, 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentStats_LaunchID(), ecorePackage.getEString(), "launchID", null, 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentStats_SkipTags(), ecorePackage.getEString(), "skipTags", null, 0, 1, AgentStats.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agentEventEClass, AgentEvent.class, "AgentEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAgentEvent_Date(), ecorePackage.getEDate(), "date", null, 0, 1, AgentEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentEvent_Msg(), ecorePackage.getEString(), "msg", null, 0, 1, AgentEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentEvent_Kind(), this.getAgentEventKind(), "kind", null, 0, 1, AgentEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metadataEntryEClass, Map.Entry.class, "MetadataEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetadataEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetadataEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(executionAgentTestStatusEEnum, ExecutionAgentTestStatus.class, "ExecutionAgentTestStatus");
		addEEnumLiteral(executionAgentTestStatusEEnum, ExecutionAgentTestStatus.PASS);
		addEEnumLiteral(executionAgentTestStatusEEnum, ExecutionAgentTestStatus.FAIL);
		addEEnumLiteral(executionAgentTestStatusEEnum, ExecutionAgentTestStatus.TIMEOUT);
		addEEnumLiteral(executionAgentTestStatusEEnum, ExecutionAgentTestStatus.NO_HANDLES);
		addEEnumLiteral(executionAgentTestStatusEEnum, ExecutionAgentTestStatus.SKIPPED);

		initEEnum(agentEventKindEEnum, AgentEventKind.class, "AgentEventKind");
		addEEnumLiteral(agentEventKindEEnum, AgentEventKind.REGISTER);
		addEEnumLiteral(agentEventKindEEnum, AgentEventKind.UNREGISTER);
		addEEnumLiteral(agentEventKindEEnum, AgentEventKind.ERROR);
		addEEnumLiteral(agentEventKindEEnum, AgentEventKind.NO_MORE_HANDLES);
		addEEnumLiteral(agentEventKindEEnum, AgentEventKind.NO_SPACE_ERROR);

		initEEnum(executionStateEEnum, ExecutionState.class, "ExecutionState");
		addEEnumLiteral(executionStateEEnum, ExecutionState.FINISHED);
		addEEnumLiteral(executionStateEEnum, ExecutionState.CANCELED);
		addEEnumLiteral(executionStateEEnum, ExecutionState.RUNNING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.PENDING);

		// Create resource
		createResource(eNS_URI);
	}

} //StatsPackageImpl
