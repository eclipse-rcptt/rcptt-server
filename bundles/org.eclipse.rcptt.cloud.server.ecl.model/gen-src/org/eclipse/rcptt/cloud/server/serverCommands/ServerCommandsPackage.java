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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.rcptt.ecl.core.CorePackage;

import org.eclipse.rcptt.cloud.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory
 * @model kind="package"
 * @generated
 */
public interface ServerCommandsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "serverCommands";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///q7/cloud/ecl/server/commands.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "serverCommands";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ServerCommandsPackage eINSTANCE = org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.SuiteInfoImpl <em>Suite Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.SuiteInfoImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getSuiteInfo()
	 * @generated
	 */
	int SUITE_INFO = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_INFO__NAME = 0;

	/**
	 * The feature id for the '<em><b>Pending Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_INFO__PENDING_TASKS = 1;

	/**
	 * The number of structural features of the '<em>Suite Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_INFO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentCommandImpl <em>Agent Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentCommandImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentCommand()
	 * @generated
	 */
	int AGENT_COMMAND = 1;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_COMMAND__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_COMMAND__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_COMMAND__AGENT = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Agent Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_COMMAND_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl <em>Agent Info Details</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentInfoDetails()
	 * @generated
	 */
	int AGENT_INFO_DETAILS = 2;

	/**
	 * The feature id for the '<em><b>Taken Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__TAKEN_TASKS = 0;

	/**
	 * The feature id for the '<em><b>Free Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__FREE_MEMORY = 1;

	/**
	 * The feature id for the '<em><b>Total Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__TOTAL_MEMORY = 2;

	/**
	 * The feature id for the '<em><b>Total Disk Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__TOTAL_DISK_SPACE = 3;

	/**
	 * The feature id for the '<em><b>Free Disk Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__FREE_DISK_SPACE = 4;

	/**
	 * The feature id for the '<em><b>Cpu Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__CPU_USAGE = 5;

	/**
	 * The feature id for the '<em><b>Screen Features</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__SCREEN_FEATURES = 6;

	/**
	 * The feature id for the '<em><b>Cpu Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__CPU_COUNT = 7;

	/**
	 * The feature id for the '<em><b>Uptime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__UPTIME = 8;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS__TIME = 9;

	/**
	 * The number of structural features of the '<em>Agent Info Details</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_DETAILS_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentImpl <em>Register Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getRegisterAgent()
	 * @generated
	 */
	int REGISTER_AGENT = 3;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTER_AGENT__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTER_AGENT__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTER_AGENT__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The number of structural features of the '<em>Register Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTER_AGENT_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.UnregisterAgentImpl <em>Unregister Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.UnregisterAgentImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getUnregisterAgent()
	 * @generated
	 */
	int UNREGISTER_AGENT = 4;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNREGISTER_AGENT__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNREGISTER_AGENT__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNREGISTER_AGENT__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The number of structural features of the '<em>Unregister Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNREGISTER_AGENT_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentPingImpl <em>Agent Ping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentPingImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentPing()
	 * @generated
	 */
	int AGENT_PING = 5;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_PING__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_PING__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_PING__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The feature id for the '<em><b>Details</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_PING__DETAILS = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Agent Ping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_PING_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ListAgentsImpl <em>List Agents</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ListAgentsImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getListAgents()
	 * @generated
	 */
	int LIST_AGENTS = 6;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_AGENTS__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_AGENTS__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The number of structural features of the '<em>List Agents</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_AGENTS_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl <em>Exec Test Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getExecTestSuite()
	 * @generated
	 */
	int EXEC_TEST_SUITE = 7;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_TEST_SUITE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_TEST_SUITE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Auts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_TEST_SUITE__AUTS = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_TEST_SUITE__OPTIONS = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_TEST_SUITE__METADATA = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_TEST_SUITE__SUITE_ID = CorePackage.COMMAND_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Exec Test Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_TEST_SUITE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionProgressImpl <em>Execution Progress</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionProgressImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getExecutionProgress()
	 * @generated
	 */
	int EXECUTION_PROGRESS = 8;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROGRESS__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROGRESS__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROGRESS__SUITE_ID = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Execution Progress</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROGRESS_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.UpdateSystemImpl <em>Update System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.UpdateSystemImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getUpdateSystem()
	 * @generated
	 */
	int UPDATE_SYSTEM = 9;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_SYSTEM__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_SYSTEM__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Repo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_SYSTEM__REPO = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Update System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_SYSTEM_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl <em>Execution State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getExecutionState()
	 * @generated
	 */
	int EXECUTION_STATE = 10;

	/**
	 * The feature id for the '<em><b>Total Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__TOTAL_TEST_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Executed Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__EXECUTED_TEST_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Failed Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__FAILED_TEST_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Skipped Test Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__SKIPPED_TEST_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Execution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__EXECUTION_TIME = 4;

	/**
	 * The feature id for the '<em><b>Estimation Time Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__ESTIMATION_TIME_LEFT = 5;

	/**
	 * The number of structural features of the '<em>Execution State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.GetTaskImpl <em>Get Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.GetTaskImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getGetTask()
	 * @generated
	 */
	int GET_TASK = 11;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_TASK__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_TASK__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_TASK__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_TASK__SUITE_ID = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Get Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_TASK_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl <em>Complete Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getCompleteTask()
	 * @generated
	 */
	int COMPLETE_TASK = 12;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The feature id for the '<em><b>Report</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK__REPORT = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Return Cause</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK__RETURN_CAUSE = AGENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK__SUITE_ID = AGENT_COMMAND_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK__ID = AGENT_COMMAND_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Complete Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETE_TASK_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 13;

	/**
	 * The feature id for the '<em><b>Suite</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUITE = 0;

	/**
	 * The feature id for the '<em><b>Aut</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__AUT = 1;

	/**
	 * The feature id for the '<em><b>Test Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TEST_OPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ARTIFACTS = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ID = 4;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUITE_ID = 5;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ListExecutedSuitesImpl <em>List Executed Suites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ListExecutedSuitesImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getListExecutedSuites()
	 * @generated
	 */
	int LIST_EXECUTED_SUITES = 14;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXECUTED_SUITES__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXECUTED_SUITES__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The number of structural features of the '<em>List Executed Suites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXECUTED_SUITES_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportProblemImpl <em>Report Problem</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportProblemImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getReportProblem()
	 * @generated
	 */
	int REPORT_PROBLEM = 15;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_PROBLEM__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_PROBLEM__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_PROBLEM__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The feature id for the '<em><b>Cause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_PROBLEM__CAUSE = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Report Problem</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_PROBLEM_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.GetHTTPServerInfoImpl <em>Get HTTP Server Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.GetHTTPServerInfoImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getGetHTTPServerInfo()
	 * @generated
	 */
	int GET_HTTP_SERVER_INFO = 16;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_HTTP_SERVER_INFO__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_HTTP_SERVER_INFO__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The number of structural features of the '<em>Get HTTP Server Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_HTTP_SERVER_INFO_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.HTTPServerInfoImpl <em>HTTP Server Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.HTTPServerInfoImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getHTTPServerInfo()
	 * @generated
	 */
	int HTTP_SERVER_INFO = 17;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_SERVER_INFO__PORT = 0;

	/**
	 * The number of structural features of the '<em>HTTP Server Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_SERVER_INFO_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoObjectImpl <em>Agent Info Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoObjectImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentInfoObject()
	 * @generated
	 */
	int AGENT_INFO_OBJECT = 18;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__URI = ModelPackage.AGENT_INFO__URI;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__CLASSIFIER = ModelPackage.AGENT_INFO__CLASSIFIER;

	/**
	 * The feature id for the '<em><b>Launch Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__LAUNCH_ID = ModelPackage.AGENT_INFO__LAUNCH_ID;

	/**
	 * The feature id for the '<em><b>Skip Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__SKIP_TAGS = ModelPackage.AGENT_INFO__SKIP_TAGS;

	/**
	 * The feature id for the '<em><b>Taken Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__TAKEN_TASKS = ModelPackage.AGENT_INFO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Free Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__FREE_MEMORY = ModelPackage.AGENT_INFO_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Total Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__TOTAL_MEMORY = ModelPackage.AGENT_INFO_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Total Disk Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__TOTAL_DISK_SPACE = ModelPackage.AGENT_INFO_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Free Disk Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__FREE_DISK_SPACE = ModelPackage.AGENT_INFO_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Cpu Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__CPU_USAGE = ModelPackage.AGENT_INFO_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Screen Features</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__SCREEN_FEATURES = ModelPackage.AGENT_INFO_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Cpu Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__CPU_COUNT = ModelPackage.AGENT_INFO_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Uptime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__UPTIME = ModelPackage.AGENT_INFO_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__TIME = ModelPackage.AGENT_INFO_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT__ID = ModelPackage.AGENT_INFO_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Agent Info Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_OBJECT_FEATURE_COUNT = ModelPackage.AGENT_INFO_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentResultImpl <em>Register Agent Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentResultImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getRegisterAgentResult()
	 * @generated
	 */
	int REGISTER_AGENT_RESULT = 19;

	/**
	 * The feature id for the '<em><b>Http Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTER_AGENT_RESULT__HTTP_PORT = 0;

	/**
	 * The feature id for the '<em><b>Http Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTER_AGENT_RESULT__HTTP_SERVER = 1;

	/**
	 * The number of structural features of the '<em>Register Agent Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGISTER_AGENT_RESULT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAUTStartupImpl <em>Report AUT Startup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAUTStartupImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getReportAUTStartup()
	 * @generated
	 */
	int REPORT_AUT_STARTUP = 20;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AUT_STARTUP__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AUT_STARTUP__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AUT_STARTUP__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AUT_STARTUP__SUITE_ID = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Files</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AUT_STARTUP__FILES = AGENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AUT_STARTUP__STATE = AGENT_COMMAND_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Report AUT Startup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AUT_STARTUP_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAgentLogImpl <em>Report Agent Log</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAgentLogImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getReportAgentLog()
	 * @generated
	 */
	int REPORT_AGENT_LOG = 21;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AGENT_LOG__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AGENT_LOG__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AGENT_LOG__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AGENT_LOG__SUITE_ID = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AGENT_LOG__TYPE = AGENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AGENT_LOG__STATUS = AGENT_COMMAND_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Report Agent Log</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_AGENT_LOG_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AutFileImpl <em>Aut File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AutFileImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAutFile()
	 * @generated
	 */
	int AUT_FILE = 22;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_FILE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_FILE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Aut File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_FILE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.MarkTaskRecievedImpl <em>Mark Task Recieved</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.MarkTaskRecievedImpl
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getMarkTaskRecieved()
	 * @generated
	 */
	int MARK_TASK_RECIEVED = 23;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARK_TASK_RECIEVED__HOST = AGENT_COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARK_TASK_RECIEVED__BINDINGS = AGENT_COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARK_TASK_RECIEVED__AGENT = AGENT_COMMAND__AGENT;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARK_TASK_RECIEVED__STATE = AGENT_COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mark Task Recieved</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARK_TASK_RECIEVED_FEATURE_COUNT = AGENT_COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus <em>Task Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getTaskStatus()
	 * @generated
	 */
	int TASK_STATUS = 24;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus <em>Aut Startup Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAutStartupStatus()
	 * @generated
	 */
	int AUT_STARTUP_STATUS = 25;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType <em>Agent Log Entry Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentLogEntryType()
	 * @generated
	 */
	int AGENT_LOG_ENTRY_TYPE = 26;

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo <em>Suite Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Suite Info</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo
	 * @generated
	 */
	EClass getSuiteInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo#getName()
	 * @see #getSuiteInfo()
	 * @generated
	 */
	EAttribute getSuiteInfo_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo#getPendingTasks <em>Pending Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pending Tasks</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.SuiteInfo#getPendingTasks()
	 * @see #getSuiteInfo()
	 * @generated
	 */
	EAttribute getSuiteInfo_PendingTasks();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand <em>Agent Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent Command</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand
	 * @generated
	 */
	EClass getAgentCommand();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand#getAgent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Agent</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentCommand#getAgent()
	 * @see #getAgentCommand()
	 * @generated
	 */
	EReference getAgentCommand_Agent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails <em>Agent Info Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent Info Details</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails
	 * @generated
	 */
	EClass getAgentInfoDetails();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTakenTasks <em>Taken Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Taken Tasks</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTakenTasks()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_TakenTasks();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeMemory <em>Free Memory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Free Memory</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeMemory()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_FreeMemory();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalMemory <em>Total Memory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Memory</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalMemory()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_TotalMemory();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalDiskSpace <em>Total Disk Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Disk Space</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalDiskSpace()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_TotalDiskSpace();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeDiskSpace <em>Free Disk Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Free Disk Space</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeDiskSpace()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_FreeDiskSpace();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuUsage <em>Cpu Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cpu Usage</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuUsage()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_CpuUsage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getScreenFeatures <em>Screen Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Screen Features</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getScreenFeatures()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_ScreenFeatures();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuCount <em>Cpu Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cpu Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuCount()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_CpuCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getUptime <em>Uptime</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uptime</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getUptime()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_Uptime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTime()
	 * @see #getAgentInfoDetails()
	 * @generated
	 */
	EAttribute getAgentInfoDetails_Time();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgent <em>Register Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Register Agent</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgent
	 * @generated
	 */
	EClass getRegisterAgent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.UnregisterAgent <em>Unregister Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unregister Agent</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.UnregisterAgent
	 * @generated
	 */
	EClass getUnregisterAgent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentPing <em>Agent Ping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent Ping</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentPing
	 * @generated
	 */
	EClass getAgentPing();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentPing#getDetails <em>Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Details</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentPing#getDetails()
	 * @see #getAgentPing()
	 * @generated
	 */
	EReference getAgentPing_Details();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ListAgents <em>List Agents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Agents</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ListAgents
	 * @generated
	 */
	EClass getListAgents();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite <em>Exec Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exec Test Suite</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite
	 * @generated
	 */
	EClass getExecTestSuite();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getAuts <em>Auts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Auts</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getAuts()
	 * @see #getExecTestSuite()
	 * @generated
	 */
	EReference getExecTestSuite_Auts();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Options</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getOptions()
	 * @see #getExecTestSuite()
	 * @generated
	 */
	EReference getExecTestSuite_Options();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getMetadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Metadata</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getMetadata()
	 * @see #getExecTestSuite()
	 * @generated
	 */
	EReference getExecTestSuite_Metadata();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite#getSuiteId()
	 * @see #getExecTestSuite()
	 * @generated
	 */
	EAttribute getExecTestSuite_SuiteId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress <em>Execution Progress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Progress</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress
	 * @generated
	 */
	EClass getExecutionProgress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress#getSuiteId()
	 * @see #getExecutionProgress()
	 * @generated
	 */
	EAttribute getExecutionProgress_SuiteId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem <em>Update System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Update System</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem
	 * @generated
	 */
	EClass getUpdateSystem();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem#getRepo <em>Repo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repo</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.UpdateSystem#getRepo()
	 * @see #getUpdateSystem()
	 * @generated
	 */
	EAttribute getUpdateSystem_Repo();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState <em>Execution State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution State</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState
	 * @generated
	 */
	EClass getExecutionState();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getTotalTestCount <em>Total Test Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Test Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getTotalTestCount()
	 * @see #getExecutionState()
	 * @generated
	 */
	EAttribute getExecutionState_TotalTestCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutedTestCount <em>Executed Test Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Executed Test Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutedTestCount()
	 * @see #getExecutionState()
	 * @generated
	 */
	EAttribute getExecutionState_ExecutedTestCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getFailedTestCount <em>Failed Test Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failed Test Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getFailedTestCount()
	 * @see #getExecutionState()
	 * @generated
	 */
	EAttribute getExecutionState_FailedTestCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getSkippedTestCount <em>Skipped Test Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Skipped Test Count</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getSkippedTestCount()
	 * @see #getExecutionState()
	 * @generated
	 */
	EAttribute getExecutionState_SkippedTestCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutionTime <em>Execution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution Time</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getExecutionTime()
	 * @see #getExecutionState()
	 * @generated
	 */
	EAttribute getExecutionState_ExecutionTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getEstimationTimeLeft <em>Estimation Time Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Estimation Time Left</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState#getEstimationTimeLeft()
	 * @see #getExecutionState()
	 * @generated
	 */
	EAttribute getExecutionState_EstimationTimeLeft();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.GetTask <em>Get Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get Task</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.GetTask
	 * @generated
	 */
	EClass getGetTask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.GetTask#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.GetTask#getSuiteId()
	 * @see #getGetTask()
	 * @generated
	 */
	EAttribute getGetTask_SuiteId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask <em>Complete Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Complete Task</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask
	 * @generated
	 */
	EClass getCompleteTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReport <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Report</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReport()
	 * @see #getCompleteTask()
	 * @generated
	 */
	EReference getCompleteTask_Report();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReturnCause <em>Return Cause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Cause</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getReturnCause()
	 * @see #getCompleteTask()
	 * @generated
	 */
	EAttribute getCompleteTask_ReturnCause();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getSuiteId()
	 * @see #getCompleteTask()
	 * @generated
	 */
	EAttribute getCompleteTask_SuiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.CompleteTask#getId()
	 * @see #getCompleteTask()
	 * @generated
	 */
	EAttribute getCompleteTask_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuite <em>Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Suite</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuite()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Suite();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getAut <em>Aut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Aut</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task#getAut()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Aut();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getTestOptions <em>Test Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Test Options</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task#getTestOptions()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_TestOptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifacts</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task#getArtifacts()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Artifacts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task#getId()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.Task#getSuiteId()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_SuiteId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ListExecutedSuites <em>List Executed Suites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Executed Suites</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ListExecutedSuites
	 * @generated
	 */
	EClass getListExecutedSuites();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem <em>Report Problem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report Problem</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem
	 * @generated
	 */
	EClass getReportProblem();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem#getCause <em>Cause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cause</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportProblem#getCause()
	 * @see #getReportProblem()
	 * @generated
	 */
	EReference getReportProblem_Cause();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.GetHTTPServerInfo <em>Get HTTP Server Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get HTTP Server Info</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.GetHTTPServerInfo
	 * @generated
	 */
	EClass getGetHTTPServerInfo();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.HTTPServerInfo <em>HTTP Server Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HTTP Server Info</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.HTTPServerInfo
	 * @generated
	 */
	EClass getHTTPServerInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.HTTPServerInfo#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.HTTPServerInfo#getPort()
	 * @see #getHTTPServerInfo()
	 * @generated
	 */
	EAttribute getHTTPServerInfo_Port();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject <em>Agent Info Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent Info Object</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject
	 * @generated
	 */
	EClass getAgentInfoObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoObject#getId()
	 * @see #getAgentInfoObject()
	 * @generated
	 */
	EAttribute getAgentInfoObject_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult <em>Register Agent Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Register Agent Result</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult
	 * @generated
	 */
	EClass getRegisterAgentResult();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpPort <em>Http Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Port</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpPort()
	 * @see #getRegisterAgentResult()
	 * @generated
	 */
	EAttribute getRegisterAgentResult_HttpPort();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpServer <em>Http Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Server</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.RegisterAgentResult#getHttpServer()
	 * @see #getRegisterAgentResult()
	 * @generated
	 */
	EAttribute getRegisterAgentResult_HttpServer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup <em>Report AUT Startup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report AUT Startup</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup
	 * @generated
	 */
	EClass getReportAUTStartup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup#getSuiteId()
	 * @see #getReportAUTStartup()
	 * @generated
	 */
	EAttribute getReportAUTStartup_SuiteId();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup#getFiles <em>Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Files</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup#getFiles()
	 * @see #getReportAUTStartup()
	 * @generated
	 */
	EReference getReportAUTStartup_Files();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAUTStartup#getState()
	 * @see #getReportAUTStartup()
	 * @generated
	 */
	EAttribute getReportAUTStartup_State();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog <em>Report Agent Log</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report Agent Log</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog
	 * @generated
	 */
	EClass getReportAgentLog();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getSuiteId()
	 * @see #getReportAgentLog()
	 * @generated
	 */
	EAttribute getReportAgentLog_SuiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getType()
	 * @see #getReportAgentLog()
	 * @generated
	 */
	EAttribute getReportAgentLog_Type();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Status</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ReportAgentLog#getStatus()
	 * @see #getReportAgentLog()
	 * @generated
	 */
	EReference getReportAgentLog_Status();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Aut File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aut File</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getAutFile();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAutFile()
	 * @generated
	 */
	EAttribute getAutFile_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAutFile()
	 * @generated
	 */
	EAttribute getAutFile_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved <em>Mark Task Recieved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mark Task Recieved</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved
	 * @generated
	 */
	EClass getMarkTaskRecieved();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved#isState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.MarkTaskRecieved#isState()
	 * @see #getMarkTaskRecieved()
	 * @generated
	 */
	EAttribute getMarkTaskRecieved_State();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus <em>Task Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Task Status</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus
	 * @generated
	 */
	EEnum getTaskStatus();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus <em>Aut Startup Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Aut Startup Status</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus
	 * @generated
	 */
	EEnum getAutStartupStatus();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType <em>Agent Log Entry Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Agent Log Entry Type</em>'.
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType
	 * @generated
	 */
	EEnum getAgentLogEntryType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ServerCommandsFactory getServerCommandsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.SuiteInfoImpl <em>Suite Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.SuiteInfoImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getSuiteInfo()
		 * @generated
		 */
		EClass SUITE_INFO = eINSTANCE.getSuiteInfo();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUITE_INFO__NAME = eINSTANCE.getSuiteInfo_Name();

		/**
		 * The meta object literal for the '<em><b>Pending Tasks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUITE_INFO__PENDING_TASKS = eINSTANCE.getSuiteInfo_PendingTasks();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentCommandImpl <em>Agent Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentCommandImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentCommand()
		 * @generated
		 */
		EClass AGENT_COMMAND = eINSTANCE.getAgentCommand();

		/**
		 * The meta object literal for the '<em><b>Agent</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT_COMMAND__AGENT = eINSTANCE.getAgentCommand_Agent();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl <em>Agent Info Details</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentInfoDetails()
		 * @generated
		 */
		EClass AGENT_INFO_DETAILS = eINSTANCE.getAgentInfoDetails();

		/**
		 * The meta object literal for the '<em><b>Taken Tasks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__TAKEN_TASKS = eINSTANCE.getAgentInfoDetails_TakenTasks();

		/**
		 * The meta object literal for the '<em><b>Free Memory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__FREE_MEMORY = eINSTANCE.getAgentInfoDetails_FreeMemory();

		/**
		 * The meta object literal for the '<em><b>Total Memory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__TOTAL_MEMORY = eINSTANCE.getAgentInfoDetails_TotalMemory();

		/**
		 * The meta object literal for the '<em><b>Total Disk Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__TOTAL_DISK_SPACE = eINSTANCE.getAgentInfoDetails_TotalDiskSpace();

		/**
		 * The meta object literal for the '<em><b>Free Disk Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__FREE_DISK_SPACE = eINSTANCE.getAgentInfoDetails_FreeDiskSpace();

		/**
		 * The meta object literal for the '<em><b>Cpu Usage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__CPU_USAGE = eINSTANCE.getAgentInfoDetails_CpuUsage();

		/**
		 * The meta object literal for the '<em><b>Screen Features</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__SCREEN_FEATURES = eINSTANCE.getAgentInfoDetails_ScreenFeatures();

		/**
		 * The meta object literal for the '<em><b>Cpu Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__CPU_COUNT = eINSTANCE.getAgentInfoDetails_CpuCount();

		/**
		 * The meta object literal for the '<em><b>Uptime</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__UPTIME = eINSTANCE.getAgentInfoDetails_Uptime();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_DETAILS__TIME = eINSTANCE.getAgentInfoDetails_Time();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentImpl <em>Register Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getRegisterAgent()
		 * @generated
		 */
		EClass REGISTER_AGENT = eINSTANCE.getRegisterAgent();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.UnregisterAgentImpl <em>Unregister Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.UnregisterAgentImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getUnregisterAgent()
		 * @generated
		 */
		EClass UNREGISTER_AGENT = eINSTANCE.getUnregisterAgent();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentPingImpl <em>Agent Ping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentPingImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentPing()
		 * @generated
		 */
		EClass AGENT_PING = eINSTANCE.getAgentPing();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT_PING__DETAILS = eINSTANCE.getAgentPing_Details();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ListAgentsImpl <em>List Agents</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ListAgentsImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getListAgents()
		 * @generated
		 */
		EClass LIST_AGENTS = eINSTANCE.getListAgents();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl <em>Exec Test Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecTestSuiteImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getExecTestSuite()
		 * @generated
		 */
		EClass EXEC_TEST_SUITE = eINSTANCE.getExecTestSuite();

		/**
		 * The meta object literal for the '<em><b>Auts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_TEST_SUITE__AUTS = eINSTANCE.getExecTestSuite_Auts();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_TEST_SUITE__OPTIONS = eINSTANCE.getExecTestSuite_Options();

		/**
		 * The meta object literal for the '<em><b>Metadata</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_TEST_SUITE__METADATA = eINSTANCE.getExecTestSuite_Metadata();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_TEST_SUITE__SUITE_ID = eINSTANCE.getExecTestSuite_SuiteId();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionProgressImpl <em>Execution Progress</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionProgressImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getExecutionProgress()
		 * @generated
		 */
		EClass EXECUTION_PROGRESS = eINSTANCE.getExecutionProgress();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_PROGRESS__SUITE_ID = eINSTANCE.getExecutionProgress_SuiteId();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.UpdateSystemImpl <em>Update System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.UpdateSystemImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getUpdateSystem()
		 * @generated
		 */
		EClass UPDATE_SYSTEM = eINSTANCE.getUpdateSystem();

		/**
		 * The meta object literal for the '<em><b>Repo</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UPDATE_SYSTEM__REPO = eINSTANCE.getUpdateSystem_Repo();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl <em>Execution State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ExecutionStateImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getExecutionState()
		 * @generated
		 */
		EClass EXECUTION_STATE = eINSTANCE.getExecutionState();

		/**
		 * The meta object literal for the '<em><b>Total Test Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_STATE__TOTAL_TEST_COUNT = eINSTANCE.getExecutionState_TotalTestCount();

		/**
		 * The meta object literal for the '<em><b>Executed Test Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_STATE__EXECUTED_TEST_COUNT = eINSTANCE.getExecutionState_ExecutedTestCount();

		/**
		 * The meta object literal for the '<em><b>Failed Test Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_STATE__FAILED_TEST_COUNT = eINSTANCE.getExecutionState_FailedTestCount();

		/**
		 * The meta object literal for the '<em><b>Skipped Test Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_STATE__SKIPPED_TEST_COUNT = eINSTANCE.getExecutionState_SkippedTestCount();

		/**
		 * The meta object literal for the '<em><b>Execution Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_STATE__EXECUTION_TIME = eINSTANCE.getExecutionState_ExecutionTime();

		/**
		 * The meta object literal for the '<em><b>Estimation Time Left</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_STATE__ESTIMATION_TIME_LEFT = eINSTANCE.getExecutionState_EstimationTimeLeft();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.GetTaskImpl <em>Get Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.GetTaskImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getGetTask()
		 * @generated
		 */
		EClass GET_TASK = eINSTANCE.getGetTask();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GET_TASK__SUITE_ID = eINSTANCE.getGetTask_SuiteId();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl <em>Complete Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.CompleteTaskImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getCompleteTask()
		 * @generated
		 */
		EClass COMPLETE_TASK = eINSTANCE.getCompleteTask();

		/**
		 * The meta object literal for the '<em><b>Report</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_TASK__REPORT = eINSTANCE.getCompleteTask_Report();

		/**
		 * The meta object literal for the '<em><b>Return Cause</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPLETE_TASK__RETURN_CAUSE = eINSTANCE.getCompleteTask_ReturnCause();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPLETE_TASK__SUITE_ID = eINSTANCE.getCompleteTask_SuiteId();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPLETE_TASK__ID = eINSTANCE.getCompleteTask_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.TaskImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Suite</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__SUITE = eINSTANCE.getTask_Suite();

		/**
		 * The meta object literal for the '<em><b>Aut</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__AUT = eINSTANCE.getTask_Aut();

		/**
		 * The meta object literal for the '<em><b>Test Options</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__TEST_OPTIONS = eINSTANCE.getTask_TestOptions();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__ARTIFACTS = eINSTANCE.getTask_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__ID = eINSTANCE.getTask_Id();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__SUITE_ID = eINSTANCE.getTask_SuiteId();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ListExecutedSuitesImpl <em>List Executed Suites</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ListExecutedSuitesImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getListExecutedSuites()
		 * @generated
		 */
		EClass LIST_EXECUTED_SUITES = eINSTANCE.getListExecutedSuites();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportProblemImpl <em>Report Problem</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportProblemImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getReportProblem()
		 * @generated
		 */
		EClass REPORT_PROBLEM = eINSTANCE.getReportProblem();

		/**
		 * The meta object literal for the '<em><b>Cause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT_PROBLEM__CAUSE = eINSTANCE.getReportProblem_Cause();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.GetHTTPServerInfoImpl <em>Get HTTP Server Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.GetHTTPServerInfoImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getGetHTTPServerInfo()
		 * @generated
		 */
		EClass GET_HTTP_SERVER_INFO = eINSTANCE.getGetHTTPServerInfo();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.HTTPServerInfoImpl <em>HTTP Server Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.HTTPServerInfoImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getHTTPServerInfo()
		 * @generated
		 */
		EClass HTTP_SERVER_INFO = eINSTANCE.getHTTPServerInfo();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTTP_SERVER_INFO__PORT = eINSTANCE.getHTTPServerInfo_Port();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoObjectImpl <em>Agent Info Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoObjectImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentInfoObject()
		 * @generated
		 */
		EClass AGENT_INFO_OBJECT = eINSTANCE.getAgentInfoObject();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO_OBJECT__ID = eINSTANCE.getAgentInfoObject_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentResultImpl <em>Register Agent Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.RegisterAgentResultImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getRegisterAgentResult()
		 * @generated
		 */
		EClass REGISTER_AGENT_RESULT = eINSTANCE.getRegisterAgentResult();

		/**
		 * The meta object literal for the '<em><b>Http Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGISTER_AGENT_RESULT__HTTP_PORT = eINSTANCE.getRegisterAgentResult_HttpPort();

		/**
		 * The meta object literal for the '<em><b>Http Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGISTER_AGENT_RESULT__HTTP_SERVER = eINSTANCE.getRegisterAgentResult_HttpServer();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAUTStartupImpl <em>Report AUT Startup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAUTStartupImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getReportAUTStartup()
		 * @generated
		 */
		EClass REPORT_AUT_STARTUP = eINSTANCE.getReportAUTStartup();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT_AUT_STARTUP__SUITE_ID = eINSTANCE.getReportAUTStartup_SuiteId();

		/**
		 * The meta object literal for the '<em><b>Files</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT_AUT_STARTUP__FILES = eINSTANCE.getReportAUTStartup_Files();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT_AUT_STARTUP__STATE = eINSTANCE.getReportAUTStartup_State();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAgentLogImpl <em>Report Agent Log</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ReportAgentLogImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getReportAgentLog()
		 * @generated
		 */
		EClass REPORT_AGENT_LOG = eINSTANCE.getReportAgentLog();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT_AGENT_LOG__SUITE_ID = eINSTANCE.getReportAgentLog_SuiteId();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT_AGENT_LOG__TYPE = eINSTANCE.getReportAgentLog_Type();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT_AGENT_LOG__STATUS = eINSTANCE.getReportAgentLog_Status();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AutFileImpl <em>Aut File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.AutFileImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAutFile()
		 * @generated
		 */
		EClass AUT_FILE = eINSTANCE.getAutFile();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_FILE__KEY = eINSTANCE.getAutFile_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_FILE__VALUE = eINSTANCE.getAutFile_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.MarkTaskRecievedImpl <em>Mark Task Recieved</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.MarkTaskRecievedImpl
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getMarkTaskRecieved()
		 * @generated
		 */
		EClass MARK_TASK_RECIEVED = eINSTANCE.getMarkTaskRecieved();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARK_TASK_RECIEVED__STATE = eINSTANCE.getMarkTaskRecieved_State();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus <em>Task Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.TaskStatus
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getTaskStatus()
		 * @generated
		 */
		EEnum TASK_STATUS = eINSTANCE.getTaskStatus();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus <em>Aut Startup Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.AutStartupStatus
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAutStartupStatus()
		 * @generated
		 */
		EEnum AUT_STARTUP_STATUS = eINSTANCE.getAutStartupStatus();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType <em>Agent Log Entry Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.AgentLogEntryType
		 * @see org.eclipse.rcptt.cloud.server.serverCommands.impl.ServerCommandsPackageImpl#getAgentLogEntryType()
		 * @generated
		 */
		EEnum AGENT_LOG_ENTRY_TYPE = eINSTANCE.getAgentLogEntryType();

	}

} // ServerCommandsPackage
