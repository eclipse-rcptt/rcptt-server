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
package org.eclipse.rcptt.cloud.server.serverCommands;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage
 * @generated
 */
public interface ServerCommandsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ServerCommandsFactory eINSTANCE = org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Suite Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Suite Info</em>'.
	 * @generated
	 */
	SuiteInfo createSuiteInfo();

	/**
	 * Returns a new object of class '<em>Agent Info Details</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Agent Info Details</em>'.
	 * @generated
	 */
	AgentInfoDetails createAgentInfoDetails();

	/**
	 * Returns a new object of class '<em>Register Agent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Register Agent</em>'.
	 * @generated
	 */
	RegisterAgent createRegisterAgent();

	/**
	 * Returns a new object of class '<em>Unregister Agent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unregister Agent</em>'.
	 * @generated
	 */
	UnregisterAgent createUnregisterAgent();

	/**
	 * Returns a new object of class '<em>Agent Ping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Agent Ping</em>'.
	 * @generated
	 */
	AgentPing createAgentPing();

	/**
	 * Returns a new object of class '<em>List Agents</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Agents</em>'.
	 * @generated
	 */
	ListAgents createListAgents();

	/**
	 * Returns a new object of class '<em>Exec Test Suite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exec Test Suite</em>'.
	 * @generated
	 */
	ExecTestSuite createExecTestSuite();

	/**
	 * Returns a new object of class '<em>Execution Progress</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Progress</em>'.
	 * @generated
	 */
	ExecutionProgress createExecutionProgress();

	/**
	 * Returns a new object of class '<em>Update System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Update System</em>'.
	 * @generated
	 */
	UpdateSystem createUpdateSystem();

	/**
	 * Returns a new object of class '<em>Execution State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution State</em>'.
	 * @generated
	 */
	ExecutionState createExecutionState();

	/**
	 * Returns a new object of class '<em>Get Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Task</em>'.
	 * @generated
	 */
	GetTask createGetTask();

	/**
	 * Returns a new object of class '<em>Complete Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Complete Task</em>'.
	 * @generated
	 */
	CompleteTask createCompleteTask();

	/**
	 * Returns a new object of class '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task</em>'.
	 * @generated
	 */
	Task createTask();

	/**
	 * Returns a new object of class '<em>List Executed Suites</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Executed Suites</em>'.
	 * @generated
	 */
	ListExecutedSuites createListExecutedSuites();

	/**
	 * Returns a new object of class '<em>Report Problem</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report Problem</em>'.
	 * @generated
	 */
	ReportProblem createReportProblem();

	/**
	 * Returns a new object of class '<em>Get HTTP Server Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get HTTP Server Info</em>'.
	 * @generated
	 */
	GetHTTPServerInfo createGetHTTPServerInfo();

	/**
	 * Returns a new object of class '<em>HTTP Server Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>HTTP Server Info</em>'.
	 * @generated
	 */
	HTTPServerInfo createHTTPServerInfo();

	/**
	 * Returns a new object of class '<em>Agent Info Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Agent Info Object</em>'.
	 * @generated
	 */
	AgentInfoObject createAgentInfoObject();

	/**
	 * Returns a new object of class '<em>Register Agent Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Register Agent Result</em>'.
	 * @generated
	 */
	RegisterAgentResult createRegisterAgentResult();

	/**
	 * Returns a new object of class '<em>Report AUT Startup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report AUT Startup</em>'.
	 * @generated
	 */
	ReportAUTStartup createReportAUTStartup();

	/**
	 * Returns a new object of class '<em>Report Agent Log</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report Agent Log</em>'.
	 * @generated
	 */
	ReportAgentLog createReportAgentLog();

	/**
	 * Returns a new object of class '<em>Mark Task Recieved</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mark Task Recieved</em>'.
	 * @generated
	 */
	MarkTaskRecieved createMarkTaskRecieved();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ServerCommandsPackage getServerCommandsPackage();

} //ServerCommandsFactory
