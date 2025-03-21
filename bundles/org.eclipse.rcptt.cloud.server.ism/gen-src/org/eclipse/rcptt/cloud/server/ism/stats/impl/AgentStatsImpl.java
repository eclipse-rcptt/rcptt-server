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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.rcptt.cloud.server.ism.stats.AgentEvent;
import org.eclipse.rcptt.cloud.server.ism.stats.AgentStats;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Agent Stats</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getTotalCount <em>Total Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getFailedCount <em>Failed Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getRevertCount <em>Revert Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getTakenTasks <em>Taken Tasks</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getLaunchID <em>Launch ID</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.ism.stats.impl.AgentStatsImpl#getSkipTags <em>Skip Tags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AgentStatsImpl extends EObjectImpl implements AgentStats {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected String uri = URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalCount() <em>Total Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalCount()
	 * @generated
	 * @ordered
	 */
	protected static final int TOTAL_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTotalCount() <em>Total Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalCount()
	 * @generated
	 * @ordered
	 */
	protected int totalCount = TOTAL_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFailedCount() <em>Failed Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailedCount()
	 * @generated
	 * @ordered
	 */
	protected static final int FAILED_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFailedCount() <em>Failed Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailedCount()
	 * @generated
	 * @ordered
	 */
	protected int failedCount = FAILED_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevertCount() <em>Revert Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevertCount()
	 * @generated
	 * @ordered
	 */
	protected static final int REVERT_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRevertCount() <em>Revert Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevertCount()
	 * @generated
	 * @ordered
	 */
	protected int revertCount = REVERT_COUNT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<AgentEvent> events;

	/**
	 * The default value of the '{@link #getTakenTasks() <em>Taken Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTakenTasks()
	 * @generated
	 * @ordered
	 */
	protected static final int TAKEN_TASKS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTakenTasks() <em>Taken Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTakenTasks()
	 * @generated
	 * @ordered
	 */
	protected int takenTasks = TAKEN_TASKS_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassifier() <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifier()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifier() <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifier()
	 * @generated
	 * @ordered
	 */
	protected String classifier = CLASSIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getLaunchID() <em>Launch ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaunchID()
	 * @generated
	 * @ordered
	 */
	protected static final String LAUNCH_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLaunchID() <em>Launch ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaunchID()
	 * @generated
	 * @ordered
	 */
	protected String launchID = LAUNCH_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSkipTags() <em>Skip Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipTags()
	 * @generated
	 * @ordered
	 */
	protected static final String SKIP_TAGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSkipTags() <em>Skip Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipTags()
	 * @generated
	 * @ordered
	 */
	protected String skipTags = SKIP_TAGS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgentStatsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return StatsPackage.Literals.AGENT_STATS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalCount(int newTotalCount) {
		int oldTotalCount = totalCount;
		totalCount = newTotalCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__TOTAL_COUNT, oldTotalCount, totalCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFailedCount() {
		return failedCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFailedCount(int newFailedCount) {
		int oldFailedCount = failedCount;
		failedCount = newFailedCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__FAILED_COUNT, oldFailedCount, failedCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRevertCount() {
		return revertCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevertCount(int newRevertCount) {
		int oldRevertCount = revertCount;
		revertCount = newRevertCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__REVERT_COUNT, oldRevertCount, revertCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AgentEvent> getEvents() {
		if (events == null) {
			events = new EObjectResolvingEList<AgentEvent>(AgentEvent.class, this, StatsPackage.AGENT_STATS__EVENTS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTakenTasks() {
		return takenTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTakenTasks(int newTakenTasks) {
		int oldTakenTasks = takenTasks;
		takenTasks = newTakenTasks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__TAKEN_TASKS, oldTakenTasks, takenTasks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassifier() {
		return classifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassifier(String newClassifier) {
		String oldClassifier = classifier;
		classifier = newClassifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__CLASSIFIER, oldClassifier, classifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLaunchID() {
		return launchID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLaunchID(String newLaunchID) {
		String oldLaunchID = launchID;
		launchID = newLaunchID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__LAUNCH_ID, oldLaunchID, launchID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSkipTags() {
		return skipTags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkipTags(String newSkipTags) {
		String oldSkipTags = skipTags;
		skipTags = newSkipTags;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatsPackage.AGENT_STATS__SKIP_TAGS, oldSkipTags, skipTags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatsPackage.AGENT_STATS__URI:
				return getUri();
			case StatsPackage.AGENT_STATS__TOTAL_COUNT:
				return getTotalCount();
			case StatsPackage.AGENT_STATS__FAILED_COUNT:
				return getFailedCount();
			case StatsPackage.AGENT_STATS__REVERT_COUNT:
				return getRevertCount();
			case StatsPackage.AGENT_STATS__EVENTS:
				return getEvents();
			case StatsPackage.AGENT_STATS__TAKEN_TASKS:
				return getTakenTasks();
			case StatsPackage.AGENT_STATS__CLASSIFIER:
				return getClassifier();
			case StatsPackage.AGENT_STATS__LAUNCH_ID:
				return getLaunchID();
			case StatsPackage.AGENT_STATS__SKIP_TAGS:
				return getSkipTags();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")

	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatsPackage.AGENT_STATS__URI:
				setUri((String)newValue);
				return;
			case StatsPackage.AGENT_STATS__TOTAL_COUNT:
				setTotalCount((Integer)newValue);
				return;
			case StatsPackage.AGENT_STATS__FAILED_COUNT:
				setFailedCount((Integer)newValue);
				return;
			case StatsPackage.AGENT_STATS__REVERT_COUNT:
				setRevertCount((Integer)newValue);
				return;
			case StatsPackage.AGENT_STATS__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends AgentEvent>)newValue);
				return;
			case StatsPackage.AGENT_STATS__TAKEN_TASKS:
				setTakenTasks((Integer)newValue);
				return;
			case StatsPackage.AGENT_STATS__CLASSIFIER:
				setClassifier((String)newValue);
				return;
			case StatsPackage.AGENT_STATS__LAUNCH_ID:
				setLaunchID((String)newValue);
				return;
			case StatsPackage.AGENT_STATS__SKIP_TAGS:
				setSkipTags((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StatsPackage.AGENT_STATS__URI:
				setUri(URI_EDEFAULT);
				return;
			case StatsPackage.AGENT_STATS__TOTAL_COUNT:
				setTotalCount(TOTAL_COUNT_EDEFAULT);
				return;
			case StatsPackage.AGENT_STATS__FAILED_COUNT:
				setFailedCount(FAILED_COUNT_EDEFAULT);
				return;
			case StatsPackage.AGENT_STATS__REVERT_COUNT:
				setRevertCount(REVERT_COUNT_EDEFAULT);
				return;
			case StatsPackage.AGENT_STATS__EVENTS:
				getEvents().clear();
				return;
			case StatsPackage.AGENT_STATS__TAKEN_TASKS:
				setTakenTasks(TAKEN_TASKS_EDEFAULT);
				return;
			case StatsPackage.AGENT_STATS__CLASSIFIER:
				setClassifier(CLASSIFIER_EDEFAULT);
				return;
			case StatsPackage.AGENT_STATS__LAUNCH_ID:
				setLaunchID(LAUNCH_ID_EDEFAULT);
				return;
			case StatsPackage.AGENT_STATS__SKIP_TAGS:
				setSkipTags(SKIP_TAGS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StatsPackage.AGENT_STATS__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case StatsPackage.AGENT_STATS__TOTAL_COUNT:
				return totalCount != TOTAL_COUNT_EDEFAULT;
			case StatsPackage.AGENT_STATS__FAILED_COUNT:
				return failedCount != FAILED_COUNT_EDEFAULT;
			case StatsPackage.AGENT_STATS__REVERT_COUNT:
				return revertCount != REVERT_COUNT_EDEFAULT;
			case StatsPackage.AGENT_STATS__EVENTS:
				return events != null && !events.isEmpty();
			case StatsPackage.AGENT_STATS__TAKEN_TASKS:
				return takenTasks != TAKEN_TASKS_EDEFAULT;
			case StatsPackage.AGENT_STATS__CLASSIFIER:
				return CLASSIFIER_EDEFAULT == null ? classifier != null : !CLASSIFIER_EDEFAULT.equals(classifier);
			case StatsPackage.AGENT_STATS__LAUNCH_ID:
				return LAUNCH_ID_EDEFAULT == null ? launchID != null : !LAUNCH_ID_EDEFAULT.equals(launchID);
			case StatsPackage.AGENT_STATS__SKIP_TAGS:
				return SKIP_TAGS_EDEFAULT == null ? skipTags != null : !SKIP_TAGS_EDEFAULT.equals(skipTags);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (uri: ");
		result.append(uri);
		result.append(", totalCount: ");
		result.append(totalCount);
		result.append(", failedCount: ");
		result.append(failedCount);
		result.append(", revertCount: ");
		result.append(revertCount);
		result.append(", takenTasks: ");
		result.append(takenTasks);
		result.append(", classifier: ");
		result.append(classifier);
		result.append(", launchID: ");
		result.append(launchID);
		result.append(", skipTags: ");
		result.append(skipTags);
		result.append(')');
		return result.toString();
	}

} //AgentStatsImpl
