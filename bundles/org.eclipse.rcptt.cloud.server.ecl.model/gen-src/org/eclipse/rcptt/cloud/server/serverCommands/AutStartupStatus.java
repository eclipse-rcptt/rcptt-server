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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Aut Startup Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsPackage#getAutStartupStatus()
 * @model
 * @generated
 */
public enum AutStartupStatus implements Enumerator {
	/**
	 * The '<em><b>Started</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTED_VALUE
	 * @generated
	 * @ordered
	 */
	STARTED(0, "Started", "Started"),

	/**
	 * The '<em><b>Failed To Start</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAILED_TO_START_VALUE
	 * @generated
	 * @ordered
	 */
	FAILED_TO_START(1, "FailedToStart", "FailedToStart"),

	/**
	 * The '<em><b>Shutdown On Timeout</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHUTDOWN_ON_TIMEOUT_VALUE
	 * @generated
	 * @ordered
	 */
	SHUTDOWN_ON_TIMEOUT(2, "ShutdownOnTimeout", "ShutdownOnTimeout"),

	/**
	 * The '<em><b>Shutdown On Option</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHUTDOWN_ON_OPTION_VALUE
	 * @generated
	 * @ordered
	 */
	SHUTDOWN_ON_OPTION(3, "ShutdownOnOption", "ShutdownOnOption"), /**
	 * The '<em><b>Failed To Ping</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAILED_TO_PING_VALUE
	 * @generated
	 * @ordered
	 */
	FAILED_TO_PING(4, "FailedToPing", "FailedToPing");

	/**
	 * The '<em><b>Started</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Started</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTED
	 * @model name="Started"
	 * @generated
	 * @ordered
	 */
	public static final int STARTED_VALUE = 0;

	/**
	 * The '<em><b>Failed To Start</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Failed To Start</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAILED_TO_START
	 * @model name="FailedToStart"
	 * @generated
	 * @ordered
	 */
	public static final int FAILED_TO_START_VALUE = 1;

	/**
	 * The '<em><b>Shutdown On Timeout</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Shutdown On Timeout</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHUTDOWN_ON_TIMEOUT
	 * @model name="ShutdownOnTimeout"
	 * @generated
	 * @ordered
	 */
	public static final int SHUTDOWN_ON_TIMEOUT_VALUE = 2;

	/**
	 * The '<em><b>Shutdown On Option</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Shutdown On Option</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHUTDOWN_ON_OPTION
	 * @model name="ShutdownOnOption"
	 * @generated
	 * @ordered
	 */
	public static final int SHUTDOWN_ON_OPTION_VALUE = 3;

	/**
	 * The '<em><b>Failed To Ping</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Failed To Ping</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAILED_TO_PING
	 * @model name="FailedToPing"
	 * @generated
	 * @ordered
	 */
	public static final int FAILED_TO_PING_VALUE = 4;

	/**
	 * An array of all the '<em><b>Aut Startup Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AutStartupStatus[] VALUES_ARRAY =
		new AutStartupStatus[] {
			STARTED,
			FAILED_TO_START,
			SHUTDOWN_ON_TIMEOUT,
			SHUTDOWN_ON_OPTION,
			FAILED_TO_PING,
		};

	/**
	 * A public read-only list of all the '<em><b>Aut Startup Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AutStartupStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Aut Startup Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AutStartupStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AutStartupStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Aut Startup Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AutStartupStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AutStartupStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Aut Startup Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AutStartupStatus get(int value) {
		switch (value) {
			case STARTED_VALUE: return STARTED;
			case FAILED_TO_START_VALUE: return FAILED_TO_START;
			case SHUTDOWN_ON_TIMEOUT_VALUE: return SHUTDOWN_ON_TIMEOUT;
			case SHUTDOWN_ON_OPTION_VALUE: return SHUTDOWN_ON_OPTION;
			case FAILED_TO_PING_VALUE: return FAILED_TO_PING;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AutStartupStatus(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //AutStartupStatus
