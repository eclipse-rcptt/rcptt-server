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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionAgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.ExecutionState;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Execution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getTotalCount <em>Total Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getFailedCount <em>Failed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getEndTime <em>End Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getFirstReportTime <em>First Report Time</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getReportFile <em>Report File</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getAgentStats <em>Agent Stats</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getExecutionChildName <em>Execution Child Name</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getAutArtifacts <em>Aut Artifacts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getMetadataArtifacts <em>Metadata Artifacts</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getCanceledCount <em>Canceled Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getPassedCount <em>Passed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getExecutedCount <em>Executed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getGlobalID <em>Global ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getState <em>State</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getSuiteId <em>Suite Id</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.ExecutionImpl#getAutNames <em>Aut Names</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionImpl extends EObjectImpl implements Execution {
	/**
	 * The default value of the '{@link #getTotalCount() <em>Total Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTotalCount()
	 * @generated
	 * @ordered
	 */
	protected static final int TOTAL_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTotalCount() <em>Total Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTotalCount()
	 * @generated
	 * @ordered
	 */
	protected int totalCount = TOTAL_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFailedCount() <em>Failed Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFailedCount()
	 * @generated
	 * @ordered
	 */
	protected static final int FAILED_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFailedCount() <em>Failed Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFailedCount()
	 * @generated
	 * @ordered
	 */
	protected int failedCount = FAILED_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final long START_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected long startTime = START_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEndTime()
	 * @generated
	 * @ordered
	 */
	protected static final long END_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getEndTime() <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEndTime()
	 * @generated
	 * @ordered
	 */
	protected long endTime = END_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFirstReportTime() <em>First Report Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getFirstReportTime()
	 * @generated
	 * @ordered
	 */
	protected static final long FIRST_REPORT_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getFirstReportTime() <em>First Report Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getFirstReportTime()
	 * @generated
	 * @ordered
	 */
	protected long firstReportTime = FIRST_REPORT_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getReportFile() <em>Report File</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReportFile()
	 * @generated
	 * @ordered
	 */
	protected static final String REPORT_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReportFile() <em>Report File</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReportFile()
	 * @generated
	 * @ordered
	 */
	protected String reportFile = REPORT_FILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAgentStats() <em>Agent Stats</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAgentStats()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionAgentStats> agentStats;

	/**
	 * The default value of the '{@link #getExecutionChildName() <em>Execution Child Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getExecutionChildName()
	 * @generated
	 * @ordered
	 */
	protected static final String EXECUTION_CHILD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExecutionChildName() <em>Execution Child Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getExecutionChildName()
	 * @generated
	 * @ordered
	 */
	protected String executionChildName = EXECUTION_CHILD_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAutArtifacts() <em>Aut Artifacts</em>}' attribute list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getAutArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<String> autArtifacts;

	/**
	 * The cached value of the '{@link #getMetadataArtifacts() <em>Metadata Artifacts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetadataArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<String> metadataArtifacts;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getCanceledCount() <em>Canceled Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getCanceledCount()
	 * @generated
	 * @ordered
	 */
	protected static final int CANCELED_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCanceledCount() <em>Canceled Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getCanceledCount()
	 * @generated
	 * @ordered
	 */
	protected int canceledCount = CANCELED_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassedCount() <em>Passed Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPassedCount()
	 * @generated
	 * @ordered
	 */
	protected static final int PASSED_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPassedCount() <em>Passed Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPassedCount()
	 * @generated
	 * @ordered
	 */
	protected int passedCount = PASSED_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getExecutedCount() <em>Executed Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getExecutedCount()
	 * @generated
	 * @ordered
	 */
	protected static final int EXECUTED_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExecutedCount() <em>Executed Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getExecutedCount()
	 * @generated
	 * @ordered
	 */
	protected int executedCount = EXECUTED_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getGlobalID() <em>Global ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGlobalID()
	 * @generated
	 * @ordered
	 */
	protected static final int GLOBAL_ID_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getGlobalID() <em>Global ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGlobalID()
	 * @generated
	 * @ordered
	 */
	protected int globalID = GLOBAL_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionState STATE_EDEFAULT = ExecutionState.FINISHED;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected ExecutionState state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSuiteId() <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSuiteId()
	 * @generated
	 * @ordered
	 */
	protected static final String SUITE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuiteId() <em>Suite Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSuiteId()
	 * @generated
	 * @ordered
	 */
	protected String suiteId = SUITE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMetadata() <em>Metadata</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMetadata()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> metadata;

	/**
	 * The cached value of the '{@link #getAutNames() <em>Aut Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutNames()
	 * @generated
	 * @ordered
	 */
	protected EList<String> autNames;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return StatsPackage.Literals.EXECUTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalCount(int newTotalCount) {
		int oldTotalCount = totalCount;
		totalCount = newTotalCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__TOTAL_COUNT, oldTotalCount, totalCount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getFailedCount() {
		return failedCount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFailedCount(int newFailedCount) {
		int oldFailedCount = failedCount;
		failedCount = newFailedCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__FAILED_COUNT, oldFailedCount, failedCount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartTime(long newStartTime) {
		long oldStartTime = startTime;
		startTime = newStartTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__START_TIME, oldStartTime, startTime));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndTime(long newEndTime) {
		long oldEndTime = endTime;
		endTime = newEndTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__END_TIME, oldEndTime, endTime));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getFirstReportTime() {
		return firstReportTime;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstReportTime(long newFirstReportTime) {
		long oldFirstReportTime = firstReportTime;
		firstReportTime = newFirstReportTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__FIRST_REPORT_TIME, oldFirstReportTime, firstReportTime));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getReportFile() {
		return reportFile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setReportFile(String newReportFile) {
		String oldReportFile = reportFile;
		reportFile = newReportFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__REPORT_FILE, oldReportFile, reportFile));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionAgentStats> getAgentStats() {
		if (agentStats == null) {
			agentStats = new EObjectContainmentEList<ExecutionAgentStats>(ExecutionAgentStats.class, this, StatsPackage.EXECUTION__AGENT_STATS);
		}
		return agentStats;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getExecutionChildName() {
		return executionChildName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionChildName(String newExecutionChildName) {
		String oldExecutionChildName = executionChildName;
		executionChildName = newExecutionChildName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__EXECUTION_CHILD_NAME, oldExecutionChildName, executionChildName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAutArtifacts() {
		if (autArtifacts == null) {
			autArtifacts = new EDataTypeUniqueEList<String>(String.class, this, StatsPackage.EXECUTION__AUT_ARTIFACTS);
		}
		return autArtifacts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMetadataArtifacts() {
		if (metadataArtifacts == null) {
			metadataArtifacts = new EDataTypeUniqueEList<String>(String.class, this, StatsPackage.EXECUTION__METADATA_ARTIFACTS);
		}
		return metadataArtifacts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getCanceledCount() {
		return canceledCount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanceledCount(int newCanceledCount) {
		int oldCanceledCount = canceledCount;
		canceledCount = newCanceledCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__CANCELED_COUNT, oldCanceledCount, canceledCount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getPassedCount() {
		return passedCount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassedCount(int newPassedCount) {
		int oldPassedCount = passedCount;
		passedCount = newPassedCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__PASSED_COUNT, oldPassedCount, passedCount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getExecutedCount() {
		return executedCount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutedCount(int newExecutedCount) {
		int oldExecutedCount = executedCount;
		executedCount = newExecutedCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__EXECUTED_COUNT, oldExecutedCount, executedCount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getGlobalID() {
		return globalID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalID(int newGlobalID) {
		int oldGlobalID = globalID;
		globalID = newGlobalID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__GLOBAL_ID, oldGlobalID, globalID));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(ExecutionState newState) {
		ExecutionState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getSuiteId() {
		return suiteId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuiteId(String newSuiteId) {
		String oldSuiteId = suiteId;
		suiteId = newSuiteId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.EXECUTION__SUITE_ID, oldSuiteId, suiteId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getMetadata() {
		if (metadata == null) {
			metadata = new EcoreEMap<String,String>(StatsPackage.Literals.METADATA_ENTRY, MetadataEntryImpl.class, this, StatsPackage.EXECUTION__METADATA);
		}
		return metadata;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAutNames() {
		if (autNames == null) {
			autNames = new EDataTypeUniqueEList<String>(String.class, this, StatsPackage.EXECUTION__AUT_NAMES);
		}
		return autNames;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatsPackage.EXECUTION__AGENT_STATS:
				return ((InternalEList<?>)getAgentStats()).basicRemove(otherEnd, msgs);
			case StatsPackage.EXECUTION__METADATA:
				return ((InternalEList<?>)getMetadata()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatsPackage.EXECUTION__TOTAL_COUNT:
				return getTotalCount();
			case StatsPackage.EXECUTION__FAILED_COUNT:
				return getFailedCount();
			case StatsPackage.EXECUTION__START_TIME:
				return getStartTime();
			case StatsPackage.EXECUTION__END_TIME:
				return getEndTime();
			case StatsPackage.EXECUTION__FIRST_REPORT_TIME:
				return getFirstReportTime();
			case StatsPackage.EXECUTION__REPORT_FILE:
				return getReportFile();
			case StatsPackage.EXECUTION__AGENT_STATS:
				return getAgentStats();
			case StatsPackage.EXECUTION__EXECUTION_CHILD_NAME:
				return getExecutionChildName();
			case StatsPackage.EXECUTION__AUT_ARTIFACTS:
				return getAutArtifacts();
			case StatsPackage.EXECUTION__METADATA_ARTIFACTS:
				return getMetadataArtifacts();
			case StatsPackage.EXECUTION__ID:
				return getId();
			case StatsPackage.EXECUTION__CANCELED_COUNT:
				return getCanceledCount();
			case StatsPackage.EXECUTION__PASSED_COUNT:
				return getPassedCount();
			case StatsPackage.EXECUTION__EXECUTED_COUNT:
				return getExecutedCount();
			case StatsPackage.EXECUTION__GLOBAL_ID:
				return getGlobalID();
			case StatsPackage.EXECUTION__STATE:
				return getState();
			case StatsPackage.EXECUTION__SUITE_ID:
				return getSuiteId();
			case StatsPackage.EXECUTION__METADATA:
				if (coreType) return getMetadata();
				else return getMetadata().map();
			case StatsPackage.EXECUTION__AUT_NAMES:
				return getAutNames();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")

	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatsPackage.EXECUTION__TOTAL_COUNT:
				setTotalCount((Integer)newValue);
				return;
			case StatsPackage.EXECUTION__FAILED_COUNT:
				setFailedCount((Integer)newValue);
				return;
			case StatsPackage.EXECUTION__START_TIME:
				setStartTime((Long)newValue);
				return;
			case StatsPackage.EXECUTION__END_TIME:
				setEndTime((Long)newValue);
				return;
			case StatsPackage.EXECUTION__FIRST_REPORT_TIME:
				setFirstReportTime((Long)newValue);
				return;
			case StatsPackage.EXECUTION__REPORT_FILE:
				setReportFile((String)newValue);
				return;
			case StatsPackage.EXECUTION__AGENT_STATS:
				getAgentStats().clear();
				getAgentStats().addAll((Collection<? extends ExecutionAgentStats>)newValue);
				return;
			case StatsPackage.EXECUTION__EXECUTION_CHILD_NAME:
				setExecutionChildName((String)newValue);
				return;
			case StatsPackage.EXECUTION__AUT_ARTIFACTS:
				getAutArtifacts().clear();
				getAutArtifacts().addAll((Collection<? extends String>)newValue);
				return;
			case StatsPackage.EXECUTION__METADATA_ARTIFACTS:
				getMetadataArtifacts().clear();
				getMetadataArtifacts().addAll((Collection<? extends String>)newValue);
				return;
			case StatsPackage.EXECUTION__ID:
				setId((String)newValue);
				return;
			case StatsPackage.EXECUTION__CANCELED_COUNT:
				setCanceledCount((Integer)newValue);
				return;
			case StatsPackage.EXECUTION__PASSED_COUNT:
				setPassedCount((Integer)newValue);
				return;
			case StatsPackage.EXECUTION__EXECUTED_COUNT:
				setExecutedCount((Integer)newValue);
				return;
			case StatsPackage.EXECUTION__GLOBAL_ID:
				setGlobalID((Integer)newValue);
				return;
			case StatsPackage.EXECUTION__STATE:
				setState((ExecutionState)newValue);
				return;
			case StatsPackage.EXECUTION__SUITE_ID:
				setSuiteId((String)newValue);
				return;
			case StatsPackage.EXECUTION__METADATA:
				((EStructuralFeature.Setting)getMetadata()).set(newValue);
				return;
			case StatsPackage.EXECUTION__AUT_NAMES:
				getAutNames().clear();
				getAutNames().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StatsPackage.EXECUTION__TOTAL_COUNT:
				setTotalCount(TOTAL_COUNT_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__FAILED_COUNT:
				setFailedCount(FAILED_COUNT_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__START_TIME:
				setStartTime(START_TIME_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__END_TIME:
				setEndTime(END_TIME_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__FIRST_REPORT_TIME:
				setFirstReportTime(FIRST_REPORT_TIME_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__REPORT_FILE:
				setReportFile(REPORT_FILE_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__AGENT_STATS:
				getAgentStats().clear();
				return;
			case StatsPackage.EXECUTION__EXECUTION_CHILD_NAME:
				setExecutionChildName(EXECUTION_CHILD_NAME_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__AUT_ARTIFACTS:
				getAutArtifacts().clear();
				return;
			case StatsPackage.EXECUTION__METADATA_ARTIFACTS:
				getMetadataArtifacts().clear();
				return;
			case StatsPackage.EXECUTION__ID:
				setId(ID_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__CANCELED_COUNT:
				setCanceledCount(CANCELED_COUNT_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__PASSED_COUNT:
				setPassedCount(PASSED_COUNT_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__EXECUTED_COUNT:
				setExecutedCount(EXECUTED_COUNT_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__GLOBAL_ID:
				setGlobalID(GLOBAL_ID_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__STATE:
				setState(STATE_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__SUITE_ID:
				setSuiteId(SUITE_ID_EDEFAULT);
				return;
			case StatsPackage.EXECUTION__METADATA:
				getMetadata().clear();
				return;
			case StatsPackage.EXECUTION__AUT_NAMES:
				getAutNames().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StatsPackage.EXECUTION__TOTAL_COUNT:
				return totalCount != TOTAL_COUNT_EDEFAULT;
			case StatsPackage.EXECUTION__FAILED_COUNT:
				return failedCount != FAILED_COUNT_EDEFAULT;
			case StatsPackage.EXECUTION__START_TIME:
				return startTime != START_TIME_EDEFAULT;
			case StatsPackage.EXECUTION__END_TIME:
				return endTime != END_TIME_EDEFAULT;
			case StatsPackage.EXECUTION__FIRST_REPORT_TIME:
				return firstReportTime != FIRST_REPORT_TIME_EDEFAULT;
			case StatsPackage.EXECUTION__REPORT_FILE:
				return REPORT_FILE_EDEFAULT == null ? reportFile != null : !REPORT_FILE_EDEFAULT.equals(reportFile);
			case StatsPackage.EXECUTION__AGENT_STATS:
				return agentStats != null && !agentStats.isEmpty();
			case StatsPackage.EXECUTION__EXECUTION_CHILD_NAME:
				return EXECUTION_CHILD_NAME_EDEFAULT == null ? executionChildName != null : !EXECUTION_CHILD_NAME_EDEFAULT.equals(executionChildName);
			case StatsPackage.EXECUTION__AUT_ARTIFACTS:
				return autArtifacts != null && !autArtifacts.isEmpty();
			case StatsPackage.EXECUTION__METADATA_ARTIFACTS:
				return metadataArtifacts != null && !metadataArtifacts.isEmpty();
			case StatsPackage.EXECUTION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case StatsPackage.EXECUTION__CANCELED_COUNT:
				return canceledCount != CANCELED_COUNT_EDEFAULT;
			case StatsPackage.EXECUTION__PASSED_COUNT:
				return passedCount != PASSED_COUNT_EDEFAULT;
			case StatsPackage.EXECUTION__EXECUTED_COUNT:
				return executedCount != EXECUTED_COUNT_EDEFAULT;
			case StatsPackage.EXECUTION__GLOBAL_ID:
				return globalID != GLOBAL_ID_EDEFAULT;
			case StatsPackage.EXECUTION__STATE:
				return state != STATE_EDEFAULT;
			case StatsPackage.EXECUTION__SUITE_ID:
				return SUITE_ID_EDEFAULT == null ? suiteId != null : !SUITE_ID_EDEFAULT.equals(suiteId);
			case StatsPackage.EXECUTION__METADATA:
				return metadata != null && !metadata.isEmpty();
			case StatsPackage.EXECUTION__AUT_NAMES:
				return autNames != null && !autNames.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (totalCount: ");
		result.append(totalCount);
		result.append(", failedCount: ");
		result.append(failedCount);
		result.append(", startTime: ");
		result.append(startTime);
		result.append(", endTime: ");
		result.append(endTime);
		result.append(", firstReportTime: ");
		result.append(firstReportTime);
		result.append(", reportFile: ");
		result.append(reportFile);
		result.append(", executionChildName: ");
		result.append(executionChildName);
		result.append(", autArtifacts: ");
		result.append(autArtifacts);
		result.append(", metadataArtifacts: ");
		result.append(metadataArtifacts);
		result.append(", id: ");
		result.append(id);
		result.append(", canceledCount: ");
		result.append(canceledCount);
		result.append(", passedCount: ");
		result.append(passedCount);
		result.append(", executedCount: ");
		result.append(executedCount);
		result.append(", globalID: ");
		result.append(globalID);
		result.append(", state: ");
		result.append(state);
		result.append(", suiteId: ");
		result.append(suiteId);
		result.append(", autNames: ");
		result.append(autNames);
		result.append(')');
		return result.toString();
	}

} // ExecutionImpl
