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
 * A representation of the literals of the enumeration '<em><b>Return Task Cause</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.xored.q7.cloud.server.serverCommands.ServerCommandsPackage#getReturnTaskCause()
 * @model
 * @generated
 */
public enum ReturnTaskCause implements Enumerator {
	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN(0, "Unknown", "Unknown"),

	/**
	 * The '<em><b>No Space Left On Device</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_SPACE_LEFT_ON_DEVICE_VALUE
	 * @generated
	 * @ordered
	 */
	NO_SPACE_LEFT_ON_DEVICE(1, "NoSpaceLeftOnDevice", "NoSpaceLeftOnDevice"), /**
	 * The '<em><b>No More Handles</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_MORE_HANDLES_VALUE
	 * @generated
	 * @ordered
	 */
	NO_MORE_HANDLES(2, "NoMoreHandles", "NoMoreHandles");

	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @model name="Unknown"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN_VALUE = 0;

	/**
	 * The '<em><b>No Space Left On Device</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>No Space Left On Device</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_SPACE_LEFT_ON_DEVICE
	 * @model name="NoSpaceLeftOnDevice"
	 * @generated
	 * @ordered
	 */
	public static final int NO_SPACE_LEFT_ON_DEVICE_VALUE = 1;

	/**
	 * The '<em><b>No More Handles</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>No More Handles</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_MORE_HANDLES
	 * @model name="NoMoreHandles"
	 * @generated
	 * @ordered
	 */
	public static final int NO_MORE_HANDLES_VALUE = 2;

	/**
	 * An array of all the '<em><b>Return Task Cause</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReturnTaskCause[] VALUES_ARRAY =
			new ReturnTaskCause[] {
		UNKNOWN,
		NO_SPACE_LEFT_ON_DEVICE,
		NO_MORE_HANDLES,
	};

	/**
	 * A public read-only list of all the '<em><b>Return Task Cause</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ReturnTaskCause> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Return Task Cause</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReturnTaskCause get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReturnTaskCause result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Return Task Cause</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReturnTaskCause getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReturnTaskCause result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Return Task Cause</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReturnTaskCause get(int value) {
		switch (value) {
		case UNKNOWN_VALUE: return UNKNOWN;
		case NO_SPACE_LEFT_ON_DEVICE_VALUE: return NO_SPACE_LEFT_ON_DEVICE;
		case NO_MORE_HANDLES_VALUE: return NO_MORE_HANDLES;
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
	private ReturnTaskCause(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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

} //ReturnTaskCause
