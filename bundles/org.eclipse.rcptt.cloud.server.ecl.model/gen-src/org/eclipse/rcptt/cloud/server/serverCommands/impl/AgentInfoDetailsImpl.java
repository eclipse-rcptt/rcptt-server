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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Agent Info Details</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getTakenTasks <em>Taken Tasks</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getFreeMemory <em>Free Memory</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getTotalMemory <em>Total Memory</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getTotalDiskSpace <em>Total Disk Space</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getFreeDiskSpace <em>Free Disk Space</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getCpuUsage <em>Cpu Usage</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getScreenFeatures <em>Screen Features</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getCpuCount <em>Cpu Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getUptime <em>Uptime</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.impl.AgentInfoDetailsImpl#getTime <em>Time</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AgentInfoDetailsImpl extends EObjectImpl implements AgentInfoDetails {
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
	 * The default value of the '{@link #getFreeMemory() <em>Free Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFreeMemory()
	 * @generated
	 * @ordered
	 */
	protected static final long FREE_MEMORY_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getFreeMemory() <em>Free Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFreeMemory()
	 * @generated
	 * @ordered
	 */
	protected long freeMemory = FREE_MEMORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalMemory() <em>Total Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalMemory()
	 * @generated
	 * @ordered
	 */
	protected static final long TOTAL_MEMORY_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTotalMemory() <em>Total Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalMemory()
	 * @generated
	 * @ordered
	 */
	protected long totalMemory = TOTAL_MEMORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalDiskSpace() <em>Total Disk Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalDiskSpace()
	 * @generated
	 * @ordered
	 */
	protected static final long TOTAL_DISK_SPACE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTotalDiskSpace() <em>Total Disk Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalDiskSpace()
	 * @generated
	 * @ordered
	 */
	protected long totalDiskSpace = TOTAL_DISK_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFreeDiskSpace() <em>Free Disk Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFreeDiskSpace()
	 * @generated
	 * @ordered
	 */
	protected static final long FREE_DISK_SPACE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getFreeDiskSpace() <em>Free Disk Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFreeDiskSpace()
	 * @generated
	 * @ordered
	 */
	protected long freeDiskSpace = FREE_DISK_SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCpuUsage() <em>Cpu Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuUsage()
	 * @generated
	 * @ordered
	 */
	protected static final long CPU_USAGE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCpuUsage() <em>Cpu Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuUsage()
	 * @generated
	 * @ordered
	 */
	protected long cpuUsage = CPU_USAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getScreenFeatures() <em>Screen Features</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScreenFeatures()
	 * @generated
	 * @ordered
	 */
	protected static final String SCREEN_FEATURES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScreenFeatures() <em>Screen Features</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScreenFeatures()
	 * @generated
	 * @ordered
	 */
	protected String screenFeatures = SCREEN_FEATURES_EDEFAULT;

	/**
	 * The default value of the '{@link #getCpuCount() <em>Cpu Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuCount()
	 * @generated
	 * @ordered
	 */
	protected static final int CPU_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCpuCount() <em>Cpu Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuCount()
	 * @generated
	 * @ordered
	 */
	protected int cpuCount = CPU_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUptime() <em>Uptime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUptime()
	 * @generated
	 * @ordered
	 */
	protected static final long UPTIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getUptime() <em>Uptime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUptime()
	 * @generated
	 * @ordered
	 */
	protected long uptime = UPTIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final long TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected long time = TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgentInfoDetailsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return ServerCommandsPackage.Literals.AGENT_INFO_DETAILS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTakenTasks() {
		return takenTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTakenTasks(int newTakenTasks) {
		int oldTakenTasks = takenTasks;
		takenTasks = newTakenTasks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__TAKEN_TASKS, oldTakenTasks, takenTasks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getFreeMemory() {
		return freeMemory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFreeMemory(long newFreeMemory) {
		long oldFreeMemory = freeMemory;
		freeMemory = newFreeMemory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_MEMORY, oldFreeMemory, freeMemory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getTotalMemory() {
		return totalMemory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTotalMemory(long newTotalMemory) {
		long oldTotalMemory = totalMemory;
		totalMemory = newTotalMemory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_MEMORY, oldTotalMemory, totalMemory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getTotalDiskSpace() {
		return totalDiskSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTotalDiskSpace(long newTotalDiskSpace) {
		long oldTotalDiskSpace = totalDiskSpace;
		totalDiskSpace = newTotalDiskSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_DISK_SPACE, oldTotalDiskSpace, totalDiskSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getFreeDiskSpace() {
		return freeDiskSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFreeDiskSpace(long newFreeDiskSpace) {
		long oldFreeDiskSpace = freeDiskSpace;
		freeDiskSpace = newFreeDiskSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_DISK_SPACE, oldFreeDiskSpace, freeDiskSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getCpuUsage() {
		return cpuUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCpuUsage(long newCpuUsage) {
		long oldCpuUsage = cpuUsage;
		cpuUsage = newCpuUsage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_USAGE, oldCpuUsage, cpuUsage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getScreenFeatures() {
		return screenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScreenFeatures(String newScreenFeatures) {
		String oldScreenFeatures = screenFeatures;
		screenFeatures = newScreenFeatures;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__SCREEN_FEATURES, oldScreenFeatures, screenFeatures));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getCpuCount() {
		return cpuCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCpuCount(int newCpuCount) {
		int oldCpuCount = cpuCount;
		cpuCount = newCpuCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_COUNT, oldCpuCount, cpuCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getUptime() {
		return uptime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUptime(long newUptime) {
		long oldUptime = uptime;
		uptime = newUptime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__UPTIME, oldUptime, uptime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTime(long newTime) {
		long oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServerCommandsPackage.AGENT_INFO_DETAILS__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TAKEN_TASKS:
				return getTakenTasks();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_MEMORY:
				return getFreeMemory();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_MEMORY:
				return getTotalMemory();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_DISK_SPACE:
				return getTotalDiskSpace();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_DISK_SPACE:
				return getFreeDiskSpace();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_USAGE:
				return getCpuUsage();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__SCREEN_FEATURES:
				return getScreenFeatures();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_COUNT:
				return getCpuCount();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__UPTIME:
				return getUptime();
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TIME:
				return getTime();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TAKEN_TASKS:
				setTakenTasks((Integer)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_MEMORY:
				setFreeMemory((Long)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_MEMORY:
				setTotalMemory((Long)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_DISK_SPACE:
				setTotalDiskSpace((Long)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_DISK_SPACE:
				setFreeDiskSpace((Long)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_USAGE:
				setCpuUsage((Long)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__SCREEN_FEATURES:
				setScreenFeatures((String)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_COUNT:
				setCpuCount((Integer)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__UPTIME:
				setUptime((Long)newValue);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TIME:
				setTime((Long)newValue);
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
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TAKEN_TASKS:
				setTakenTasks(TAKEN_TASKS_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_MEMORY:
				setFreeMemory(FREE_MEMORY_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_MEMORY:
				setTotalMemory(TOTAL_MEMORY_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_DISK_SPACE:
				setTotalDiskSpace(TOTAL_DISK_SPACE_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_DISK_SPACE:
				setFreeDiskSpace(FREE_DISK_SPACE_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_USAGE:
				setCpuUsage(CPU_USAGE_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__SCREEN_FEATURES:
				setScreenFeatures(SCREEN_FEATURES_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_COUNT:
				setCpuCount(CPU_COUNT_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__UPTIME:
				setUptime(UPTIME_EDEFAULT);
				return;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TIME:
				setTime(TIME_EDEFAULT);
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
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TAKEN_TASKS:
				return takenTasks != TAKEN_TASKS_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_MEMORY:
				return freeMemory != FREE_MEMORY_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_MEMORY:
				return totalMemory != TOTAL_MEMORY_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TOTAL_DISK_SPACE:
				return totalDiskSpace != TOTAL_DISK_SPACE_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__FREE_DISK_SPACE:
				return freeDiskSpace != FREE_DISK_SPACE_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_USAGE:
				return cpuUsage != CPU_USAGE_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__SCREEN_FEATURES:
				return SCREEN_FEATURES_EDEFAULT == null ? screenFeatures != null : !SCREEN_FEATURES_EDEFAULT.equals(screenFeatures);
			case ServerCommandsPackage.AGENT_INFO_DETAILS__CPU_COUNT:
				return cpuCount != CPU_COUNT_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__UPTIME:
				return uptime != UPTIME_EDEFAULT;
			case ServerCommandsPackage.AGENT_INFO_DETAILS__TIME:
				return time != TIME_EDEFAULT;
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
		result.append(" (takenTasks: ");
		result.append(takenTasks);
		result.append(", freeMemory: ");
		result.append(freeMemory);
		result.append(", totalMemory: ");
		result.append(totalMemory);
		result.append(", totalDiskSpace: ");
		result.append(totalDiskSpace);
		result.append(", freeDiskSpace: ");
		result.append(freeDiskSpace);
		result.append(", cpuUsage: ");
		result.append(cpuUsage);
		result.append(", screenFeatures: ");
		result.append(screenFeatures);
		result.append(", cpuCount: ");
		result.append(cpuCount);
		result.append(", uptime: ");
		result.append(uptime);
		result.append(", time: ");
		result.append(time);
		result.append(')');
		return result.toString();
	}

} //AgentInfoDetailsImpl
