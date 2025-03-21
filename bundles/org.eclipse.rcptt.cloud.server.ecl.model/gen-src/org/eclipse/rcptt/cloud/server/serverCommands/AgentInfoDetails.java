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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent Info Details</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTakenTasks <em>Taken Tasks</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeMemory <em>Free Memory</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalMemory <em>Total Memory</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalDiskSpace <em>Total Disk Space</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeDiskSpace <em>Free Disk Space</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuUsage <em>Cpu Usage</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getScreenFeatures <em>Screen Features</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuCount <em>Cpu Count</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getUptime <em>Uptime</em>}</li>
 *   <li>{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTime <em>Time</em>}</li>
 * </ul>
 *
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails()
 * @model
 * @generated
 */
public interface AgentInfoDetails extends EObject {
	/**
	 * Returns the value of the '<em><b>Taken Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Taken Tasks</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Taken Tasks</em>' attribute.
	 * @see #setTakenTasks(int)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_TakenTasks()
	 * @model
	 * @generated
	 */
	int getTakenTasks();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTakenTasks <em>Taken Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Taken Tasks</em>' attribute.
	 * @see #getTakenTasks()
	 * @generated
	 */
	void setTakenTasks(int value);

	/**
	 * Returns the value of the '<em><b>Free Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Free Memory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Free Memory</em>' attribute.
	 * @see #setFreeMemory(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_FreeMemory()
	 * @model
	 * @generated
	 */
	long getFreeMemory();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeMemory <em>Free Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Free Memory</em>' attribute.
	 * @see #getFreeMemory()
	 * @generated
	 */
	void setFreeMemory(long value);

	/**
	 * Returns the value of the '<em><b>Total Memory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Memory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Memory</em>' attribute.
	 * @see #setTotalMemory(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_TotalMemory()
	 * @model
	 * @generated
	 */
	long getTotalMemory();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalMemory <em>Total Memory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Memory</em>' attribute.
	 * @see #getTotalMemory()
	 * @generated
	 */
	void setTotalMemory(long value);

	/**
	 * Returns the value of the '<em><b>Total Disk Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Disk Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Disk Space</em>' attribute.
	 * @see #setTotalDiskSpace(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_TotalDiskSpace()
	 * @model
	 * @generated
	 */
	long getTotalDiskSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTotalDiskSpace <em>Total Disk Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Disk Space</em>' attribute.
	 * @see #getTotalDiskSpace()
	 * @generated
	 */
	void setTotalDiskSpace(long value);

	/**
	 * Returns the value of the '<em><b>Free Disk Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Free Disk Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Free Disk Space</em>' attribute.
	 * @see #setFreeDiskSpace(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_FreeDiskSpace()
	 * @model
	 * @generated
	 */
	long getFreeDiskSpace();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getFreeDiskSpace <em>Free Disk Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Free Disk Space</em>' attribute.
	 * @see #getFreeDiskSpace()
	 * @generated
	 */
	void setFreeDiskSpace(long value);

	/**
	 * Returns the value of the '<em><b>Cpu Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cpu Usage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cpu Usage</em>' attribute.
	 * @see #setCpuUsage(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_CpuUsage()
	 * @model
	 * @generated
	 */
	long getCpuUsage();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuUsage <em>Cpu Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cpu Usage</em>' attribute.
	 * @see #getCpuUsage()
	 * @generated
	 */
	void setCpuUsage(long value);

	/**
	 * Returns the value of the '<em><b>Screen Features</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Screen Features</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Screen Features</em>' attribute.
	 * @see #setScreenFeatures(String)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_ScreenFeatures()
	 * @model
	 * @generated
	 */
	String getScreenFeatures();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getScreenFeatures <em>Screen Features</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Screen Features</em>' attribute.
	 * @see #getScreenFeatures()
	 * @generated
	 */
	void setScreenFeatures(String value);

	/**
	 * Returns the value of the '<em><b>Cpu Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cpu Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cpu Count</em>' attribute.
	 * @see #setCpuCount(int)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_CpuCount()
	 * @model
	 * @generated
	 */
	int getCpuCount();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getCpuCount <em>Cpu Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cpu Count</em>' attribute.
	 * @see #getCpuCount()
	 * @generated
	 */
	void setCpuCount(int value);

	/**
	 * Returns the value of the '<em><b>Uptime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uptime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uptime</em>' attribute.
	 * @see #setUptime(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_Uptime()
	 * @model
	 * @generated
	 */
	long getUptime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getUptime <em>Uptime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uptime</em>' attribute.
	 * @see #getUptime()
	 * @generated
	 */
	void setUptime(long value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(long)
	 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAgentInfoDetails_Time()
	 * @model
	 * @generated
	 */
	long getTime();

	/**
	 * Sets the value of the '{@link org.eclipse.rcptt.cloud.server.serverCommands.AgentInfoDetails#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(long value);

} // AgentInfoDetails
