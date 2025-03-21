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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage
 * @generated
 */
public class ServerCommandsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ServerCommandsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServerCommandsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ServerCommandsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance
	 * object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */

	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServerCommandsSwitch<Adapter> modelSwitch =
			new ServerCommandsSwitch<Adapter>() {
			@Override
			public Adapter caseSuiteInfo(SuiteInfo object) {
				return createSuiteInfoAdapter();
			}
			@Override
			public Adapter caseAgentCommand(AgentCommand object) {
				return createAgentCommandAdapter();
			}
			@Override
			public Adapter caseAgentInfoDetails(AgentInfoDetails object) {
				return createAgentInfoDetailsAdapter();
			}
			@Override
			public Adapter caseRegisterAgent(RegisterAgent object) {
				return createRegisterAgentAdapter();
			}
			@Override
			public Adapter caseUnregisterAgent(UnregisterAgent object) {
				return createUnregisterAgentAdapter();
			}
			@Override
			public Adapter caseAgentPing(AgentPing object) {
				return createAgentPingAdapter();
			}
			@Override
			public Adapter caseListAgents(ListAgents object) {
				return createListAgentsAdapter();
			}
			@Override
			public Adapter caseExecTestSuite(ExecTestSuite object) {
				return createExecTestSuiteAdapter();
			}
			@Override
			public Adapter caseExecutionProgress(ExecutionProgress object) {
				return createExecutionProgressAdapter();
			}
			@Override
			public Adapter caseUpdateSystem(UpdateSystem object) {
				return createUpdateSystemAdapter();
			}
			@Override
			public Adapter caseExecutionState(ExecutionState object) {
				return createExecutionStateAdapter();
			}
			@Override
			public Adapter caseGetTask(GetTask object) {
				return createGetTaskAdapter();
			}
			@Override
			public Adapter caseCompleteTask(CompleteTask object) {
				return createCompleteTaskAdapter();
			}
			@Override
			public Adapter caseTask(Task object) {
				return createTaskAdapter();
			}
			@Override
			public Adapter caseListExecutedSuites(ListExecutedSuites object) {
				return createListExecutedSuitesAdapter();
			}
			@Override
			public Adapter caseReportProblem(ReportProblem object) {
				return createReportProblemAdapter();
			}
			@Override
			public Adapter caseGetHTTPServerInfo(GetHTTPServerInfo object) {
				return createGetHTTPServerInfoAdapter();
			}
			@Override
			public Adapter caseHTTPServerInfo(HTTPServerInfo object) {
				return createHTTPServerInfoAdapter();
			}
			@Override
			public Adapter caseAgentInfoObject(AgentInfoObject object) {
				return createAgentInfoObjectAdapter();
			}
			@Override
			public Adapter caseRegisterAgentResult(RegisterAgentResult object) {
				return createRegisterAgentResultAdapter();
			}
			@Override
			public Adapter caseReportAUTStartup(ReportAUTStartup object) {
				return createReportAUTStartupAdapter();
			}
			@Override
			public Adapter caseReportAgentLog(ReportAgentLog object) {
				return createReportAgentLogAdapter();
			}
			@Override
			public Adapter caseAutFile(Map.Entry<String, String> object) {
				return createAutFileAdapter();
			}
			@Override
			public Adapter caseMarkTaskRecieved(MarkTaskRecieved object) {
				return createMarkTaskRecievedAdapter();
			}
			@Override
			public Adapter caseCommand(Command object) {
				return createCommandAdapter();
			}
			@Override
			public Adapter caseAgentInfo(AgentInfo object) {
				return createAgentInfoAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */

	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo <em>Suite Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo
	 * @generated
	 */
	public Adapter createSuiteInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand <em>Agent Command</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand
	 * @generated
	 */
	public Adapter createAgentCommandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails <em>Agent Info Details</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails
	 * @generated
	 */
	public Adapter createAgentInfoDetailsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgent <em>Register Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgent
	 * @generated
	 */
	public Adapter createRegisterAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.UnregisterAgent <em>Unregister Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.UnregisterAgent
	 * @generated
	 */
	public Adapter createUnregisterAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentPing <em>Agent Ping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentPing
	 * @generated
	 */
	public Adapter createAgentPingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ListAgents <em>List Agents</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ListAgents
	 * @generated
	 */
	public Adapter createListAgentsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite <em>Exec Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite
	 * @generated
	 */
	public Adapter createExecTestSuiteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress <em>Execution Progress</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress
	 * @generated
	 */
	public Adapter createExecutionProgressAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem <em>Update System</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem
	 * @generated
	 */
	public Adapter createUpdateSystemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState <em>Execution State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState
	 * @generated
	 */
	public Adapter createExecutionStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.GetTask <em>Get Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.GetTask
	 * @generated
	 */
	public Adapter createGetTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask <em>Complete Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask
	 * @generated
	 */
	public Adapter createCompleteTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ListExecutedSuites <em>List Executed Suites</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ListExecutedSuites
	 * @generated
	 */
	public Adapter createListExecutedSuitesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem <em>Report Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem
	 * @generated
	 */
	public Adapter createReportProblemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.GetHTTPServerInfo <em>Get HTTP Server Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.GetHTTPServerInfo
	 * @generated
	 */
	public Adapter createGetHTTPServerInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.HTTPServerInfo <em>HTTP Server Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.HTTPServerInfo
	 * @generated
	 */
	public Adapter createHTTPServerInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject <em>Agent Info Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject
	 * @generated
	 */
	public Adapter createAgentInfoObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult <em>Register Agent Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult
	 * @generated
	 */
	public Adapter createRegisterAgentResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup <em>Report AUT Startup</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup
	 * @generated
	 */
	public Adapter createReportAUTStartupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog <em>Report Agent Log</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog
	 * @generated
	 */
	public Adapter createReportAgentLogAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Aut File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createAutFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved <em>Mark Task Recieved</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved
	 * @generated
	 */
	public Adapter createMarkTaskRecievedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.ecl.core.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.ecl.core.Command
	 * @generated
	 */
	public Adapter createCommandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.rcptt.cloud.model.AgentInfo <em>Agent Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.rcptt.cloud.model.AgentInfo
	 * @generated
	 */
	public Adapter createAgentInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ServerCommandsAdapterFactory
