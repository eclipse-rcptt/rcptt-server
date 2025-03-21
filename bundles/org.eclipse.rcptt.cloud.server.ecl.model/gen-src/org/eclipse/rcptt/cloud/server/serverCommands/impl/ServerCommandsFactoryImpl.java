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

import org.eclipse.rcptt.cloud.server.serverCommands.*;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ServerCommandsFactoryImpl extends EFactoryImpl implements ServerCommandsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ServerCommandsFactory init() {
		try {
			ServerCommandsFactory theServerCommandsFactory = (ServerCommandsFactory)EPackage.Registry.INSTANCE.getEFactory(ServerCommandsPackage.eNS_URI);
			if (theServerCommandsFactory != null) {
				return theServerCommandsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ServerCommandsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServerCommandsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ServerCommandsPackage.SUITE_INFO: return createSuiteInfo();
			case ServerCommandsPackage.AGENT_INFO_DETAILS: return createAgentInfoDetails();
			case ServerCommandsPackage.REGISTER_AGENT: return createRegisterAgent();
			case ServerCommandsPackage.UNREGISTER_AGENT: return createUnregisterAgent();
			case ServerCommandsPackage.AGENT_PING: return createAgentPing();
			case ServerCommandsPackage.LIST_AGENTS: return createListAgents();
			case ServerCommandsPackage.EXEC_TEST_SUITE: return createExecTestSuite();
			case ServerCommandsPackage.EXECUTION_PROGRESS: return createExecutionProgress();
			case ServerCommandsPackage.UPDATE_SYSTEM: return createUpdateSystem();
			case ServerCommandsPackage.EXECUTION_STATE: return createExecutionState();
			case ServerCommandsPackage.GET_TASK: return createGetTask();
			case ServerCommandsPackage.COMPLETE_TASK: return createCompleteTask();
			case ServerCommandsPackage.TASK: return createTask();
			case ServerCommandsPackage.LIST_EXECUTED_SUITES: return createListExecutedSuites();
			case ServerCommandsPackage.REPORT_PROBLEM: return createReportProblem();
			case ServerCommandsPackage.GET_HTTP_SERVER_INFO: return createGetHTTPServerInfo();
			case ServerCommandsPackage.HTTP_SERVER_INFO: return createHTTPServerInfo();
			case ServerCommandsPackage.AGENT_INFO_OBJECT: return createAgentInfoObject();
			case ServerCommandsPackage.REGISTER_AGENT_RESULT: return createRegisterAgentResult();
			case ServerCommandsPackage.REPORT_AUT_STARTUP: return createReportAUTStartup();
			case ServerCommandsPackage.REPORT_AGENT_LOG: return createReportAgentLog();
			case ServerCommandsPackage.AUT_FILE: return (EObject)createAutFile();
			case ServerCommandsPackage.MARK_TASK_RECIEVED: return createMarkTaskRecieved();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ServerCommandsPackage.TASK_STATUS:
				return createTaskStatusFromString(eDataType, initialValue);
			case ServerCommandsPackage.AUT_STARTUP_STATUS:
				return createAutStartupStatusFromString(eDataType, initialValue);
			case ServerCommandsPackage.AGENT_LOG_ENTRY_TYPE:
				return createAgentLogEntryTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ServerCommandsPackage.TASK_STATUS:
				return convertTaskStatusToString(eDataType, instanceValue);
			case ServerCommandsPackage.AUT_STARTUP_STATUS:
				return convertAutStartupStatusToString(eDataType, instanceValue);
			case ServerCommandsPackage.AGENT_LOG_ENTRY_TYPE:
				return convertAgentLogEntryTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SuiteInfo createSuiteInfo() {
		SuiteInfoImpl suiteInfo = new SuiteInfoImpl();
		return suiteInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgentInfoDetails createAgentInfoDetails() {
		AgentInfoDetailsImpl agentInfoDetails = new AgentInfoDetailsImpl();
		return agentInfoDetails;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RegisterAgent createRegisterAgent() {
		RegisterAgentImpl registerAgent = new RegisterAgentImpl();
		return registerAgent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnregisterAgent createUnregisterAgent() {
		UnregisterAgentImpl unregisterAgent = new UnregisterAgentImpl();
		return unregisterAgent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgentPing createAgentPing() {
		AgentPingImpl agentPing = new AgentPingImpl();
		return agentPing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ListAgents createListAgents() {
		ListAgentsImpl listAgents = new ListAgentsImpl();
		return listAgents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecTestSuite createExecTestSuite() {
		ExecTestSuiteImpl execTestSuite = new ExecTestSuiteImpl();
		return execTestSuite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecutionProgress createExecutionProgress() {
		ExecutionProgressImpl executionProgress = new ExecutionProgressImpl();
		return executionProgress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UpdateSystem createUpdateSystem() {
		UpdateSystemImpl updateSystem = new UpdateSystemImpl();
		return updateSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecutionState createExecutionState() {
		ExecutionStateImpl executionState = new ExecutionStateImpl();
		return executionState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GetTask createGetTask() {
		GetTaskImpl getTask = new GetTaskImpl();
		return getTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompleteTask createCompleteTask() {
		CompleteTaskImpl completeTask = new CompleteTaskImpl();
		return completeTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Task createTask() {
		TaskImpl task = new TaskImpl();
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ListExecutedSuites createListExecutedSuites() {
		ListExecutedSuitesImpl listExecutedSuites = new ListExecutedSuitesImpl();
		return listExecutedSuites;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReportProblem createReportProblem() {
		ReportProblemImpl reportProblem = new ReportProblemImpl();
		return reportProblem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GetHTTPServerInfo createGetHTTPServerInfo() {
		GetHTTPServerInfoImpl getHTTPServerInfo = new GetHTTPServerInfoImpl();
		return getHTTPServerInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HTTPServerInfo createHTTPServerInfo() {
		HTTPServerInfoImpl httpServerInfo = new HTTPServerInfoImpl();
		return httpServerInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgentInfoObject createAgentInfoObject() {
		AgentInfoObjectImpl agentInfoObject = new AgentInfoObjectImpl();
		return agentInfoObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RegisterAgentResult createRegisterAgentResult() {
		RegisterAgentResultImpl registerAgentResult = new RegisterAgentResultImpl();
		return registerAgentResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReportAUTStartup createReportAUTStartup() {
		ReportAUTStartupImpl reportAUTStartup = new ReportAUTStartupImpl();
		return reportAUTStartup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReportAgentLog createReportAgentLog() {
		ReportAgentLogImpl reportAgentLog = new ReportAgentLogImpl();
		return reportAgentLog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createAutFile() {
		AutFileImpl autFile = new AutFileImpl();
		return autFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MarkTaskRecieved createMarkTaskRecieved() {
		MarkTaskRecievedImpl markTaskRecieved = new MarkTaskRecievedImpl();
		return markTaskRecieved;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskStatus createTaskStatusFromString(EDataType eDataType, String initialValue) {
		TaskStatus result = TaskStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTaskStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AutStartupStatus createAutStartupStatusFromString(EDataType eDataType, String initialValue) {
		AutStartupStatus result = AutStartupStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAutStartupStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AgentLogEntryType createAgentLogEntryTypeFromString(EDataType eDataType, String initialValue) {
		AgentLogEntryType result = AgentLogEntryType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAgentLogEntryTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ServerCommandsPackage getServerCommandsPackage() {
		return (ServerCommandsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ServerCommandsPackage getPackage() {
		return ServerCommandsPackage.eINSTANCE;
	}

} //ServerCommandsFactoryImpl
