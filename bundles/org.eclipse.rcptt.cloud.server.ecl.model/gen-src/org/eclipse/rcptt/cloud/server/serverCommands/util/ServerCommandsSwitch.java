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
package org.eclipse.rcptt.cloud.server.serverCommands.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.rcptt.ecl.core.Command;

import org.eclipse.rcptt.cloud.model.AgentInfo;
import org.eclipse.rcptt.cloud.server.serverCommands.*;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject;
import org.eclipse.rcptt.cloud.server.serverCommands.AgentPing;
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
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;
import org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo;
import org.eclipse.rcptt.cloud.server.serverCommands.Task;
import org.eclipse.rcptt.cloud.server.serverCommands.UnregisterAgent;
import org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each
 * class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage
 * @generated
 */
public class ServerCommandsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ServerCommandsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServerCommandsSwitch() {
		if (modelPackage == null) {
			modelPackage = ServerCommandsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */

	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */

	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ServerCommandsPackage.SUITE_INFO: {
				SuiteInfo suiteInfo = (SuiteInfo)theEObject;
				T result = caseSuiteInfo(suiteInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.AGENT_COMMAND: {
				AgentCommand agentCommand = (AgentCommand)theEObject;
				T result = caseAgentCommand(agentCommand);
				if (result == null) result = caseCommand(agentCommand);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.AGENT_INFO_DETAILS: {
				AgentInfoDetails agentInfoDetails = (AgentInfoDetails)theEObject;
				T result = caseAgentInfoDetails(agentInfoDetails);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.REGISTER_AGENT: {
				RegisterAgent registerAgent = (RegisterAgent)theEObject;
				T result = caseRegisterAgent(registerAgent);
				if (result == null) result = caseAgentCommand(registerAgent);
				if (result == null) result = caseCommand(registerAgent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.UNREGISTER_AGENT: {
				UnregisterAgent unregisterAgent = (UnregisterAgent)theEObject;
				T result = caseUnregisterAgent(unregisterAgent);
				if (result == null) result = caseAgentCommand(unregisterAgent);
				if (result == null) result = caseCommand(unregisterAgent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.AGENT_PING: {
				AgentPing agentPing = (AgentPing)theEObject;
				T result = caseAgentPing(agentPing);
				if (result == null) result = caseAgentCommand(agentPing);
				if (result == null) result = caseCommand(agentPing);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.LIST_AGENTS: {
				ListAgents listAgents = (ListAgents)theEObject;
				T result = caseListAgents(listAgents);
				if (result == null) result = caseCommand(listAgents);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.EXEC_TEST_SUITE: {
				ExecTestSuite execTestSuite = (ExecTestSuite)theEObject;
				T result = caseExecTestSuite(execTestSuite);
				if (result == null) result = caseCommand(execTestSuite);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.EXECUTION_PROGRESS: {
				ExecutionProgress executionProgress = (ExecutionProgress)theEObject;
				T result = caseExecutionProgress(executionProgress);
				if (result == null) result = caseCommand(executionProgress);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.UPDATE_SYSTEM: {
				UpdateSystem updateSystem = (UpdateSystem)theEObject;
				T result = caseUpdateSystem(updateSystem);
				if (result == null) result = caseCommand(updateSystem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.EXECUTION_STATE: {
				ExecutionState executionState = (ExecutionState)theEObject;
				T result = caseExecutionState(executionState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.GET_TASK: {
				GetTask getTask = (GetTask)theEObject;
				T result = caseGetTask(getTask);
				if (result == null) result = caseAgentCommand(getTask);
				if (result == null) result = caseCommand(getTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.COMPLETE_TASK: {
				CompleteTask completeTask = (CompleteTask)theEObject;
				T result = caseCompleteTask(completeTask);
				if (result == null) result = caseAgentCommand(completeTask);
				if (result == null) result = caseCommand(completeTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.TASK: {
				Task task = (Task)theEObject;
				T result = caseTask(task);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.LIST_EXECUTED_SUITES: {
				ListExecutedSuites listExecutedSuites = (ListExecutedSuites)theEObject;
				T result = caseListExecutedSuites(listExecutedSuites);
				if (result == null) result = caseCommand(listExecutedSuites);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.REPORT_PROBLEM: {
				ReportProblem reportProblem = (ReportProblem)theEObject;
				T result = caseReportProblem(reportProblem);
				if (result == null) result = caseAgentCommand(reportProblem);
				if (result == null) result = caseCommand(reportProblem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.GET_HTTP_SERVER_INFO: {
				GetHTTPServerInfo getHTTPServerInfo = (GetHTTPServerInfo)theEObject;
				T result = caseGetHTTPServerInfo(getHTTPServerInfo);
				if (result == null) result = caseCommand(getHTTPServerInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.HTTP_SERVER_INFO: {
				HTTPServerInfo httpServerInfo = (HTTPServerInfo)theEObject;
				T result = caseHTTPServerInfo(httpServerInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.AGENT_INFO_OBJECT: {
				AgentInfoObject agentInfoObject = (AgentInfoObject)theEObject;
				T result = caseAgentInfoObject(agentInfoObject);
				if (result == null) result = caseAgentInfo(agentInfoObject);
				if (result == null) result = caseAgentInfoDetails(agentInfoObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.REGISTER_AGENT_RESULT: {
				RegisterAgentResult registerAgentResult = (RegisterAgentResult)theEObject;
				T result = caseRegisterAgentResult(registerAgentResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.REPORT_AUT_STARTUP: {
				ReportAUTStartup reportAUTStartup = (ReportAUTStartup)theEObject;
				T result = caseReportAUTStartup(reportAUTStartup);
				if (result == null) result = caseAgentCommand(reportAUTStartup);
				if (result == null) result = caseCommand(reportAUTStartup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.REPORT_AGENT_LOG: {
				ReportAgentLog reportAgentLog = (ReportAgentLog)theEObject;
				T result = caseReportAgentLog(reportAgentLog);
				if (result == null) result = caseAgentCommand(reportAgentLog);
				if (result == null) result = caseCommand(reportAgentLog);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.AUT_FILE: {
				@SuppressWarnings("unchecked") Map.Entry<String, String> autFile = (Map.Entry<String, String>)theEObject;
				T result = caseAutFile(autFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServerCommandsPackage.MARK_TASK_RECIEVED: {
				MarkTaskRecieved markTaskRecieved = (MarkTaskRecieved)theEObject;
				T result = caseMarkTaskRecieved(markTaskRecieved);
				if (result == null) result = caseAgentCommand(markTaskRecieved);
				if (result == null) result = caseCommand(markTaskRecieved);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Suite Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Suite Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSuiteInfo(SuiteInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent Command</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent Command</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgentCommand(AgentCommand object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent Info Details</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent Info Details</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgentInfoDetails(AgentInfoDetails object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Register Agent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Register Agent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRegisterAgent(RegisterAgent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unregister Agent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unregister Agent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnregisterAgent(UnregisterAgent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent Ping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent Ping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgentPing(AgentPing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Agents</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Agents</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListAgents(ListAgents object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exec Test Suite</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exec Test Suite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecTestSuite(ExecTestSuite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Progress</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Progress</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionProgress(ExecutionProgress object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Update System</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Update System</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUpdateSystem(UpdateSystem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionState(ExecutionState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Get Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Get Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGetTask(GetTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Complete Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Complete Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompleteTask(CompleteTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTask(Task object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Executed Suites</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Executed Suites</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListExecutedSuites(ListExecutedSuites object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report Problem</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report Problem</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportProblem(ReportProblem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Get HTTP Server Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Get HTTP Server Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGetHTTPServerInfo(GetHTTPServerInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>HTTP Server Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>HTTP Server Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHTTPServerInfo(HTTPServerInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent Info Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent Info Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgentInfoObject(AgentInfoObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Register Agent Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Register Agent Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRegisterAgentResult(RegisterAgentResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report AUT Startup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report AUT Startup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportAUTStartup(ReportAUTStartup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report Agent Log</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report Agent Log</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReportAgentLog(ReportAgentLog object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Aut File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aut File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAutFile(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mark Task Recieved</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mark Task Recieved</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMarkTaskRecieved(MarkTaskRecieved object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Command</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Command</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommand(Command object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgentInfo(AgentInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */

	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // ServerCommandsSwitch
