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
package org.eclipse.rcptt.cloud.common.commonCommands.impl;

import org.eclipse.rcptt.cloud.common.commonCommands.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.rcptt.cloud.common.commonCommands.AddAut;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsFactory;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage;
import org.eclipse.rcptt.cloud.common.commonCommands.RequestShutdown;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommonCommandsFactoryImpl extends EFactoryImpl implements CommonCommandsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CommonCommandsFactory init() {
		try {
			CommonCommandsFactory theCommonCommandsFactory = (CommonCommandsFactory)EPackage.Registry.INSTANCE.getEFactory(CommonCommandsPackage.eNS_URI);
			if (theCommonCommandsFactory != null) {
				return theCommonCommandsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CommonCommandsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommonCommandsFactoryImpl() {
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
			case CommonCommandsPackage.ADD_TEST_SUITE: return createAddTestSuite();
			case CommonCommandsPackage.ADD_TEST_RESOURCE: return createAddTestResource();
			case CommonCommandsPackage.REQUEST_SHUTDOWN: return createRequestShutdown();
			case CommonCommandsPackage.CANCEL_SUITE: return createCancelSuite();
			case CommonCommandsPackage.BEGIN_TEST_SUITE: return createBeginTestSuite();
			case CommonCommandsPackage.ADD_AUT: return createAddAut();
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
	public AddTestSuite createAddTestSuite() {
		AddTestSuiteImpl addTestSuite = new AddTestSuiteImpl();
		return addTestSuite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AddTestResource createAddTestResource() {
		AddTestResourceImpl addTestResource = new AddTestResourceImpl();
		return addTestResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RequestShutdown createRequestShutdown() {
		RequestShutdownImpl requestShutdown = new RequestShutdownImpl();
		return requestShutdown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CancelSuite createCancelSuite() {
		CancelSuiteImpl cancelSuite = new CancelSuiteImpl();
		return cancelSuite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BeginTestSuite createBeginTestSuite() {
		BeginTestSuiteImpl beginTestSuite = new BeginTestSuiteImpl();
		return beginTestSuite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AddAut createAddAut() {
		AddAutImpl addAut = new AddAutImpl();
		return addAut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CommonCommandsPackage getCommonCommandsPackage() {
		return (CommonCommandsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CommonCommandsPackage getPackage() {
		return CommonCommandsPackage.eINSTANCE;
	}

} //CommonCommandsFactoryImpl
