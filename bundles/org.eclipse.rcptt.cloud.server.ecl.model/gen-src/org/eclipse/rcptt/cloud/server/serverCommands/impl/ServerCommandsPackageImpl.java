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
package org.eclipse.rcptt.cloud.server.serverCommands.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.rcptt.ecl.core.CorePackage;
import org.eclipse.rcptt.launching.injection.InjectionPackage;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.ReportPackage;

import org.eclipse.rcptt.cloud.model.ModelPackage;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentPing;
import org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.cloud.server.serverCommands.GetHTTPServerInfo;
import org.eclipse.rcptt.cloud.server.serverCommands.GetTask;
import org.eclipse.rcptt.cloud.server.serverCommands.HTTPServerInfo;
import org.eclipse.rcptt.cloud.server.serverCommands.ListAgents;
import org.eclipse.rcptt.cloud.server.serverCommands.ListExecutedSuites;
import org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved;
import org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgent;
import org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog;
import org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;
import org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;
import org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus;
import org.eclipse.rcptt.cloud.server.serverCommands.UnregisterAgent;
import org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ServerCommandsPackageImpl extends EPackageImpl implements ServerCommandsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass suiteInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentCommandEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentInfoDetailsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass registerAgentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unregisterAgentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentPingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listAgentsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass execTestSuiteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionProgressEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass updateSystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completeTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listExecutedSuitesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reportProblemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getHTTPServerInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass httpServerInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentInfoObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass registerAgentResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reportAUTStartupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reportAgentLogEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass autFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markTaskRecievedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum taskStatusEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum autStartupStatusEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum agentLogEntryTypeEEnum = null;

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
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ServerCommandsPackageImpl() {
		super(eNS_URI, ServerCommandsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ServerCommandsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ServerCommandsPackage init() {
		if (isInited) return (ServerCommandsPackage)EPackage.Registry.INSTANCE.getEPackage(ServerCommandsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredServerCommandsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ServerCommandsPackageImpl theServerCommandsPackage = registeredServerCommandsPackage instanceof ServerCommandsPackageImpl ? (ServerCommandsPackageImpl)registeredServerCommandsPackage : new ServerCommandsPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		ModelPackage.eINSTANCE.eClass();
		InjectionPackage.eINSTANCE.eClass();
		ReportPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theServerCommandsPackage.createPackageContents();

		// Initialize created meta-data
		theServerCommandsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theServerCommandsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ServerCommandsPackage.eNS_URI, theServerCommandsPackage);
		return theServerCommandsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSuiteInfo() {
		return suiteInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSuiteInfo_Name() {
		return (EAttribute)suiteInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSuiteInfo_PendingTasks() {
		return (EAttribute)suiteInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgentCommand() {
		return agentCommandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAgentCommand_Agent() {
		return (EReference)agentCommandEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgentInfoDetails() {
		return agentInfoDetailsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_TakenTasks() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_FreeMemory() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_TotalMemory() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_TotalDiskSpace() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_FreeDiskSpace() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_CpuUsage() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_ScreenFeatures() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_CpuCount() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_Uptime() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoDetails_Time() {
		return (EAttribute)agentInfoDetailsEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRegisterAgent() {
		return registerAgentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUnregisterAgent() {
		return unregisterAgentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgentPing() {
		return agentPingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAgentPing_Details() {
		return (EReference)agentPingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getListAgents() {
		return listAgentsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExecTestSuite() {
		return execTestSuiteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExecTestSuite_Auts() {
		return (EReference)execTestSuiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExecTestSuite_Options() {
		return (EReference)execTestSuiteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExecTestSuite_Metadata() {
		return (EReference)execTestSuiteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecTestSuite_SuiteId() {
		return (EAttribute)execTestSuiteEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExecutionProgress() {
		return executionProgressEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecutionProgress_SuiteId() {
		return (EAttribute)executionProgressEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUpdateSystem() {
		return updateSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUpdateSystem_Repo() {
		return (EAttribute)updateSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExecutionState() {
		return executionStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecutionState_TotalTestCount() {
		return (EAttribute)executionStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecutionState_ExecutedTestCount() {
		return (EAttribute)executionStateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecutionState_FailedTestCount() {
		return (EAttribute)executionStateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecutionState_SkippedTestCount() {
		return (EAttribute)executionStateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecutionState_ExecutionTime() {
		return (EAttribute)executionStateEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExecutionState_EstimationTimeLeft() {
		return (EAttribute)executionStateEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGetTask() {
		return getTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGetTask_SuiteId() {
		return (EAttribute)getTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompleteTask() {
		return completeTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompleteTask_Report() {
		return (EReference)completeTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompleteTask_ReturnCause() {
		return (EAttribute)completeTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompleteTask_SuiteId() {
		return (EAttribute)completeTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompleteTask_Id() {
		return (EAttribute)completeTaskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTask() {
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTask_Suite() {
		return (EReference)taskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTask_Aut() {
		return (EReference)taskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTask_TestOptions() {
		return (EReference)taskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTask_Artifacts() {
		return (EReference)taskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTask_Id() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTask_SuiteId() {
		return (EAttribute)taskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getListExecutedSuites() {
		return listExecutedSuitesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReportProblem() {
		return reportProblemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReportProblem_Cause() {
		return (EReference)reportProblemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGetHTTPServerInfo() {
		return getHTTPServerInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHTTPServerInfo() {
		return httpServerInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHTTPServerInfo_Port() {
		return (EAttribute)httpServerInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgentInfoObject() {
		return agentInfoObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAgentInfoObject_Id() {
		return (EAttribute)agentInfoObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRegisterAgentResult() {
		return registerAgentResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRegisterAgentResult_HttpPort() {
		return (EAttribute)registerAgentResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRegisterAgentResult_HttpServer() {
		return (EAttribute)registerAgentResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReportAUTStartup() {
		return reportAUTStartupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReportAUTStartup_SuiteId() {
		return (EAttribute)reportAUTStartupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReportAUTStartup_Files() {
		return (EReference)reportAUTStartupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReportAUTStartup_State() {
		return (EAttribute)reportAUTStartupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReportAgentLog() {
		return reportAgentLogEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReportAgentLog_SuiteId() {
		return (EAttribute)reportAgentLogEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReportAgentLog_Type() {
		return (EAttribute)reportAgentLogEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReportAgentLog_Status() {
		return (EReference)reportAgentLogEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAutFile() {
		return autFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAutFile_Key() {
		return (EAttribute)autFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAutFile_Value() {
		return (EAttribute)autFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMarkTaskRecieved() {
		return markTaskRecievedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarkTaskRecieved_State() {
		return (EAttribute)markTaskRecievedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getTaskStatus() {
		return taskStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getAutStartupStatus() {
		return autStartupStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getAgentLogEntryType() {
		return agentLogEntryTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ServerCommandsFactory getServerCommandsFactory() {
		return (ServerCommandsFactory)getEFactoryInstance();
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
		suiteInfoEClass = createEClass(SUITE_INFO);
		createEAttribute(suiteInfoEClass, SUITE_INFO__NAME);
		createEAttribute(suiteInfoEClass, SUITE_INFO__PENDING_TASKS);

		agentCommandEClass = createEClass(AGENT_COMMAND);
		createEReference(agentCommandEClass, AGENT_COMMAND__AGENT);

		agentInfoDetailsEClass = createEClass(AGENT_INFO_DETAILS);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__TAKEN_TASKS);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__FREE_MEMORY);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__TOTAL_MEMORY);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__TOTAL_DISK_SPACE);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__FREE_DISK_SPACE);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__CPU_USAGE);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__SCREEN_FEATURES);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__CPU_COUNT);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__UPTIME);
		createEAttribute(agentInfoDetailsEClass, AGENT_INFO_DETAILS__TIME);

		registerAgentEClass = createEClass(REGISTER_AGENT);

		unregisterAgentEClass = createEClass(UNREGISTER_AGENT);

		agentPingEClass = createEClass(AGENT_PING);
		createEReference(agentPingEClass, AGENT_PING__DETAILS);

		listAgentsEClass = createEClass(LIST_AGENTS);

		execTestSuiteEClass = createEClass(EXEC_TEST_SUITE);
		createEReference(execTestSuiteEClass, EXEC_TEST_SUITE__AUTS);
		createEReference(execTestSuiteEClass, EXEC_TEST_SUITE__OPTIONS);
		createEReference(execTestSuiteEClass, EXEC_TEST_SUITE__METADATA);
		createEAttribute(execTestSuiteEClass, EXEC_TEST_SUITE__SUITE_ID);

		executionProgressEClass = createEClass(EXECUTION_PROGRESS);
		createEAttribute(executionProgressEClass, EXECUTION_PROGRESS__SUITE_ID);

		updateSystemEClass = createEClass(UPDATE_SYSTEM);
		createEAttribute(updateSystemEClass, UPDATE_SYSTEM__REPO);

		executionStateEClass = createEClass(EXECUTION_STATE);
		createEAttribute(executionStateEClass, EXECUTION_STATE__TOTAL_TEST_COUNT);
		createEAttribute(executionStateEClass, EXECUTION_STATE__EXECUTED_TEST_COUNT);
		createEAttribute(executionStateEClass, EXECUTION_STATE__FAILED_TEST_COUNT);
		createEAttribute(executionStateEClass, EXECUTION_STATE__SKIPPED_TEST_COUNT);
		createEAttribute(executionStateEClass, EXECUTION_STATE__EXECUTION_TIME);
		createEAttribute(executionStateEClass, EXECUTION_STATE__ESTIMATION_TIME_LEFT);

		getTaskEClass = createEClass(GET_TASK);
		createEAttribute(getTaskEClass, GET_TASK__SUITE_ID);

		completeTaskEClass = createEClass(COMPLETE_TASK);
		createEReference(completeTaskEClass, COMPLETE_TASK__REPORT);
		createEAttribute(completeTaskEClass, COMPLETE_TASK__RETURN_CAUSE);
		createEAttribute(completeTaskEClass, COMPLETE_TASK__SUITE_ID);
		createEAttribute(completeTaskEClass, COMPLETE_TASK__ID);

		taskEClass = createEClass(TASK);
		createEReference(taskEClass, TASK__SUITE);
		createEReference(taskEClass, TASK__AUT);
		createEReference(taskEClass, TASK__TEST_OPTIONS);
		createEReference(taskEClass, TASK__ARTIFACTS);
		createEAttribute(taskEClass, TASK__ID);
		createEAttribute(taskEClass, TASK__SUITE_ID);

		listExecutedSuitesEClass = createEClass(LIST_EXECUTED_SUITES);

		reportProblemEClass = createEClass(REPORT_PROBLEM);
		createEReference(reportProblemEClass, REPORT_PROBLEM__CAUSE);

		getHTTPServerInfoEClass = createEClass(GET_HTTP_SERVER_INFO);

		httpServerInfoEClass = createEClass(HTTP_SERVER_INFO);
		createEAttribute(httpServerInfoEClass, HTTP_SERVER_INFO__PORT);

		agentInfoObjectEClass = createEClass(AGENT_INFO_OBJECT);
		createEAttribute(agentInfoObjectEClass, AGENT_INFO_OBJECT__ID);

		registerAgentResultEClass = createEClass(REGISTER_AGENT_RESULT);
		createEAttribute(registerAgentResultEClass, REGISTER_AGENT_RESULT__HTTP_PORT);
		createEAttribute(registerAgentResultEClass, REGISTER_AGENT_RESULT__HTTP_SERVER);

		reportAUTStartupEClass = createEClass(REPORT_AUT_STARTUP);
		createEAttribute(reportAUTStartupEClass, REPORT_AUT_STARTUP__SUITE_ID);
		createEReference(reportAUTStartupEClass, REPORT_AUT_STARTUP__FILES);
		createEAttribute(reportAUTStartupEClass, REPORT_AUT_STARTUP__STATE);

		reportAgentLogEClass = createEClass(REPORT_AGENT_LOG);
		createEAttribute(reportAgentLogEClass, REPORT_AGENT_LOG__SUITE_ID);
		createEAttribute(reportAgentLogEClass, REPORT_AGENT_LOG__TYPE);
		createEReference(reportAgentLogEClass, REPORT_AGENT_LOG__STATUS);

		autFileEClass = createEClass(AUT_FILE);
		createEAttribute(autFileEClass, AUT_FILE__KEY);
		createEAttribute(autFileEClass, AUT_FILE__VALUE);

		markTaskRecievedEClass = createEClass(MARK_TASK_RECIEVED);
		createEAttribute(markTaskRecievedEClass, MARK_TASK_RECIEVED__STATE);

		// Create enums
		taskStatusEEnum = createEEnum(TASK_STATUS);
		autStartupStatusEEnum = createEEnum(AUT_STARTUP_STATUS);
		agentLogEntryTypeEEnum = createEEnum(AGENT_LOG_ENTRY_TYPE);
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

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		ModelPackage theModelPackage = (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);
		ReportPackage theReportPackage = (ReportPackage)EPackage.Registry.INSTANCE.getEPackage(ReportPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		agentCommandEClass.getESuperTypes().add(theCorePackage.getCommand());
		registerAgentEClass.getESuperTypes().add(this.getAgentCommand());
		unregisterAgentEClass.getESuperTypes().add(this.getAgentCommand());
		agentPingEClass.getESuperTypes().add(this.getAgentCommand());
		listAgentsEClass.getESuperTypes().add(theCorePackage.getCommand());
		execTestSuiteEClass.getESuperTypes().add(theCorePackage.getCommand());
		executionProgressEClass.getESuperTypes().add(theCorePackage.getCommand());
		updateSystemEClass.getESuperTypes().add(theCorePackage.getCommand());
		getTaskEClass.getESuperTypes().add(this.getAgentCommand());
		completeTaskEClass.getESuperTypes().add(this.getAgentCommand());
		listExecutedSuitesEClass.getESuperTypes().add(theCorePackage.getCommand());
		reportProblemEClass.getESuperTypes().add(this.getAgentCommand());
		getHTTPServerInfoEClass.getESuperTypes().add(theCorePackage.getCommand());
		agentInfoObjectEClass.getESuperTypes().add(theModelPackage.getAgentInfo());
		agentInfoObjectEClass.getESuperTypes().add(this.getAgentInfoDetails());
		reportAUTStartupEClass.getESuperTypes().add(this.getAgentCommand());
		reportAgentLogEClass.getESuperTypes().add(this.getAgentCommand());
		markTaskRecievedEClass.getESuperTypes().add(this.getAgentCommand());

		// Initialize classes and features; add operations and parameters
		initEClass(suiteInfoEClass, SuiteInfo.class, "SuiteInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSuiteInfo_Name(), theEcorePackage.getEString(), "name", null, 0, 1, SuiteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSuiteInfo_PendingTasks(), theEcorePackage.getEInt(), "pendingTasks", null, 0, 1, SuiteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agentCommandEClass, AgentCommand.class, "AgentCommand", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAgentCommand_Agent(), theModelPackage.getAgentInfo(), null, "agent", null, 0, 1, AgentCommand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agentInfoDetailsEClass, AgentInfoDetails.class, "AgentInfoDetails", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAgentInfoDetails_TakenTasks(), theEcorePackage.getEInt(), "takenTasks", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_FreeMemory(), theEcorePackage.getELong(), "freeMemory", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_TotalMemory(), theEcorePackage.getELong(), "totalMemory", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_TotalDiskSpace(), theEcorePackage.getELong(), "totalDiskSpace", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_FreeDiskSpace(), theEcorePackage.getELong(), "freeDiskSpace", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_CpuUsage(), theEcorePackage.getELong(), "cpuUsage", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_ScreenFeatures(), theEcorePackage.getEString(), "screenFeatures", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_CpuCount(), theEcorePackage.getEInt(), "cpuCount", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_Uptime(), theEcorePackage.getELong(), "uptime", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgentInfoDetails_Time(), theEcorePackage.getELong(), "time", null, 0, 1, AgentInfoDetails.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(registerAgentEClass, RegisterAgent.class, "RegisterAgent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unregisterAgentEClass, UnregisterAgent.class, "UnregisterAgent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(agentPingEClass, AgentPing.class, "AgentPing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAgentPing_Details(), this.getAgentInfoDetails(), null, "details", null, 0, 1, AgentPing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listAgentsEClass, ListAgents.class, "ListAgents", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(execTestSuiteEClass, ExecTestSuite.class, "ExecTestSuite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecTestSuite_Auts(), theModelPackage.getAutInfo(), null, "auts", null, 0, -1, ExecTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecTestSuite_Options(), theModelPackage.getTestOptions(), null, "options", null, 0, 1, ExecTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecTestSuite_Metadata(), theModelPackage.getSuiteMetadata(), null, "metadata", null, 0, -1, ExecTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecTestSuite_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, ExecTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionProgressEClass, ExecutionProgress.class, "ExecutionProgress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionProgress_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, ExecutionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(updateSystemEClass, UpdateSystem.class, "UpdateSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUpdateSystem_Repo(), theEcorePackage.getEString(), "repo", null, 0, 1, UpdateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionStateEClass, ExecutionState.class, "ExecutionState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionState_TotalTestCount(), theEcorePackage.getEInt(), "totalTestCount", null, 0, 1, ExecutionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionState_ExecutedTestCount(), theEcorePackage.getEInt(), "executedTestCount", null, 0, 1, ExecutionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionState_FailedTestCount(), theEcorePackage.getEInt(), "failedTestCount", null, 0, 1, ExecutionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionState_SkippedTestCount(), theEcorePackage.getEInt(), "skippedTestCount", null, 0, 1, ExecutionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionState_ExecutionTime(), theEcorePackage.getELong(), "executionTime", null, 0, 1, ExecutionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionState_EstimationTimeLeft(), theEcorePackage.getELong(), "estimationTimeLeft", null, 0, 1, ExecutionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(getTaskEClass, GetTask.class, "GetTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGetTask_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, GetTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(completeTaskEClass, CompleteTask.class, "CompleteTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompleteTask_Report(), theReportPackage.getReport(), null, "report", null, 0, 1, CompleteTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompleteTask_ReturnCause(), this.getTaskStatus(), "returnCause", null, 0, 1, CompleteTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompleteTask_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, CompleteTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompleteTask_Id(), theEcorePackage.getEString(), "id", null, 0, 1, CompleteTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTask_Suite(), theModelPackage.getTestSuite(), null, "suite", null, 1, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTask_Aut(), theModelPackage.getAutInfo(), null, "aut", null, 1, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTask_TestOptions(), theModelPackage.getTestOptions(), null, "testOptions", null, 1, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTask_Artifacts(), theModelPackage.getQ7Artifact(), null, "artifacts", null, 0, -1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_Id(), theEcorePackage.getEString(), "id", null, 1, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTask_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 1, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listExecutedSuitesEClass, ListExecutedSuites.class, "ListExecutedSuites", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(reportProblemEClass, ReportProblem.class, "ReportProblem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReportProblem_Cause(), theCorePackage.getProcessStatus(), null, "cause", null, 1, 1, ReportProblem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(getHTTPServerInfoEClass, GetHTTPServerInfo.class, "GetHTTPServerInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(httpServerInfoEClass, HTTPServerInfo.class, "HTTPServerInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHTTPServerInfo_Port(), theEcorePackage.getEInt(), "port", null, 0, 1, HTTPServerInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agentInfoObjectEClass, AgentInfoObject.class, "AgentInfoObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAgentInfoObject_Id(), theEcorePackage.getEString(), "id", null, 0, 1, AgentInfoObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(registerAgentResultEClass, RegisterAgentResult.class, "RegisterAgentResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRegisterAgentResult_HttpPort(), theEcorePackage.getEInt(), "httpPort", null, 0, 1, RegisterAgentResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegisterAgentResult_HttpServer(), theEcorePackage.getEString(), "httpServer", null, 0, 1, RegisterAgentResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportAUTStartupEClass, ReportAUTStartup.class, "ReportAUTStartup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReportAUTStartup_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, ReportAUTStartup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReportAUTStartup_Files(), theModelPackage.getTestOption(), null, "files", null, 0, -1, ReportAUTStartup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReportAUTStartup_State(), this.getAutStartupStatus(), "state", null, 0, 1, ReportAUTStartup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportAgentLogEClass, ReportAgentLog.class, "ReportAgentLog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReportAgentLog_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, ReportAgentLog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReportAgentLog_Type(), this.getAgentLogEntryType(), "type", null, 0, 1, ReportAgentLog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReportAgentLog_Status(), theCorePackage.getProcessStatus(), null, "status", null, 1, 1, ReportAgentLog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(autFileEClass, Map.Entry.class, "AutFile", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAutFile_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAutFile_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(markTaskRecievedEClass, MarkTaskRecieved.class, "MarkTaskRecieved", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMarkTaskRecieved_State(), theEcorePackage.getEBoolean(), "state", null, 0, 1, MarkTaskRecieved.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(taskStatusEEnum, TaskStatus.class, "TaskStatus");
		addEEnumLiteral(taskStatusEEnum, TaskStatus.UNKNOWN);
		addEEnumLiteral(taskStatusEEnum, TaskStatus.NO_SPACE_LEFT_ON_DEVICE);
		addEEnumLiteral(taskStatusEEnum, TaskStatus.NO_MORE_HANDLES);
		addEEnumLiteral(taskStatusEEnum, TaskStatus.OK);
		addEEnumLiteral(taskStatusEEnum, TaskStatus.TIMEOUT);
		addEEnumLiteral(taskStatusEEnum, TaskStatus.FAILED_TO_START_AUT);
		addEEnumLiteral(taskStatusEEnum, TaskStatus.LICENSING_NOT_AVAILABLE);

		initEEnum(autStartupStatusEEnum, AutStartupStatus.class, "AutStartupStatus");
		addEEnumLiteral(autStartupStatusEEnum, AutStartupStatus.STARTED);
		addEEnumLiteral(autStartupStatusEEnum, AutStartupStatus.FAILED_TO_START);
		addEEnumLiteral(autStartupStatusEEnum, AutStartupStatus.SHUTDOWN_ON_TIMEOUT);
		addEEnumLiteral(autStartupStatusEEnum, AutStartupStatus.SHUTDOWN_ON_OPTION);
		addEEnumLiteral(autStartupStatusEEnum, AutStartupStatus.FAILED_TO_PING);

		initEEnum(agentLogEntryTypeEEnum, AgentLogEntryType.class, "AgentLogEntryType");
		addEEnumLiteral(agentLogEntryTypeEEnum, AgentLogEntryType.CLIENT);
		addEEnumLiteral(agentLogEntryTypeEEnum, AgentLogEntryType.BOTH);
		addEEnumLiteral(agentLogEntryTypeEEnum, AgentLogEntryType.LOGONLY);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/ecl/internal
		createInternalAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/ecl/internal</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createInternalAnnotations() {
		String source = "http://www.eclipse.org/ecl/internal";
		addAnnotation
		  (registerAgentEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (unregisterAgentEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (agentPingEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (listAgentsEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (execTestSuiteEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (executionProgressEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (updateSystemEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (getTaskEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (completeTaskEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (reportProblemEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (getHTTPServerInfoEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (reportAUTStartupEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (reportAgentLogEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (markTaskRecievedEClass,
		   source,
		   new String[] {
		   });
	}

} //ServerCommandsPackageImpl
