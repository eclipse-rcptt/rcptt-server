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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.rcptt.cloud.server.ism.stats.*;
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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatsFactoryImpl extends EFactoryImpl implements StatsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatsFactory init() {
		try {
			StatsFactory theStatsFactory = (StatsFactory)EPackage.Registry.INSTANCE.getEFactory(StatsPackage.eNS_URI);
			if (theStatsFactory != null) {
				return theStatsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StatsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatsFactoryImpl() {
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
			case StatsPackage.SUITE_STATS: return createSuiteStats();
			case StatsPackage.EXECUTION: return createExecution();
			case StatsPackage.EXECUTION_AGENT_STATS: return createExecutionAgentStats();
			case StatsPackage.EXECUTION_AGENT_TEST: return createExecutionAgentTest();
			case StatsPackage.AGENT_STATS: return createAgentStats();
			case StatsPackage.AGENT_EVENT: return createAgentEvent();
			case StatsPackage.METADATA_ENTRY: return (EObject)createMetadataEntry();
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
			case StatsPackage.EXECUTION_AGENT_TEST_STATUS:
				return createExecutionAgentTestStatusFromString(eDataType, initialValue);
			case StatsPackage.AGENT_EVENT_KIND:
				return createAgentEventKindFromString(eDataType, initialValue);
			case StatsPackage.EXECUTION_STATE:
				return createExecutionStateFromString(eDataType, initialValue);
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
			case StatsPackage.EXECUTION_AGENT_TEST_STATUS:
				return convertExecutionAgentTestStatusToString(eDataType, instanceValue);
			case StatsPackage.AGENT_EVENT_KIND:
				return convertAgentEventKindToString(eDataType, instanceValue);
			case StatsPackage.EXECUTION_STATE:
				return convertExecutionStateToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuiteStats createSuiteStats() {
		SuiteStatsImpl suiteStats = new SuiteStatsImpl();
		return suiteStats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Execution createExecution() {
		ExecutionImpl execution = new ExecutionImpl();
		return execution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionAgentStats createExecutionAgentStats() {
		ExecutionAgentStatsImpl executionAgentStats = new ExecutionAgentStatsImpl();
		return executionAgentStats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionAgentTest createExecutionAgentTest() {
		ExecutionAgentTestImpl executionAgentTest = new ExecutionAgentTestImpl();
		return executionAgentTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AgentStats createAgentStats() {
		AgentStatsImpl agentStats = new AgentStatsImpl();
		return agentStats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AgentEvent createAgentEvent() {
		AgentEventImpl agentEvent = new AgentEventImpl();
		return agentEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createMetadataEntry() {
		MetadataEntryImpl metadataEntry = new MetadataEntryImpl();
		return metadataEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionAgentTestStatus createExecutionAgentTestStatusFromString(EDataType eDataType, String initialValue) {
		ExecutionAgentTestStatus result = ExecutionAgentTestStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionAgentTestStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AgentEventKind createAgentEventKindFromString(EDataType eDataType, String initialValue) {
		AgentEventKind result = AgentEventKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAgentEventKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState createExecutionStateFromString(EDataType eDataType, String initialValue) {
		ExecutionState result = ExecutionState.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionStateToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatsPackage getStatsPackage() {
		return (StatsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StatsPackage getPackage() {
		return StatsPackage.eINSTANCE;
	}

} //StatsFactoryImpl
