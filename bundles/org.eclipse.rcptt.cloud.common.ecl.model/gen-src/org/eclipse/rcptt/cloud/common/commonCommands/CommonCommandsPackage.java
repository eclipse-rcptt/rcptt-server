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
package org.eclipse.rcptt.cloud.common.commonCommands;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.rcptt.ecl.core.CorePackage;

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
 * @see org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsFactory
 * @model kind="package"
 * @generated
 */
public interface CommonCommandsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "commonCommands";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///q7/cloud/ecl/common/commands.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "commonCommands";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommonCommandsPackage eINSTANCE = org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestSuiteImpl <em>Add Test Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestSuiteImpl
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getAddTestSuite()
	 * @generated
	 */
	int ADD_TEST_SUITE = 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_SUITE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_SUITE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Suite</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_SUITE__SUITE = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_SUITE__SUITE_ID = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Add Test Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_SUITE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestResourceImpl <em>Add Test Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestResourceImpl
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getAddTestResource()
	 * @generated
	 */
	int ADD_TEST_RESOURCE = 1;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_RESOURCE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_RESOURCE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_RESOURCE__RESOURCE = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Artifacts Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_RESOURCE__ARTIFACTS_PATH = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_RESOURCE__SUITE_ID = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Add Test Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_TEST_RESOURCE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.RequestShutdownImpl <em>Request Shutdown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.RequestShutdownImpl
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getRequestShutdown()
	 * @generated
	 */
	int REQUEST_SHUTDOWN = 2;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_SHUTDOWN__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_SHUTDOWN__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The number of structural features of the '<em>Request Shutdown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_SHUTDOWN_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.CancelSuiteImpl <em>Cancel Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CancelSuiteImpl
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getCancelSuite()
	 * @generated
	 */
	int CANCEL_SUITE = 3;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCEL_SUITE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCEL_SUITE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCEL_SUITE__SUITE_ID = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Cancel Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCEL_SUITE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl <em>Begin Test Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getBeginTestSuite()
	 * @generated
	 */
	int BEGIN_TEST_SUITE = 4;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE__SUITE_ID = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Client Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE__CLIENT_ID = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Client Secret</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE__CLIENT_SECRET = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>License Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE__LICENSE_URL = CorePackage.COMMAND_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Organization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE__ORGANIZATION = CorePackage.COMMAND_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Begin Test Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TEST_SUITE_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddAutImpl <em>Add Aut</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.AddAutImpl
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getAddAut()
	 * @generated
	 */
	int ADD_AUT = 5;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_AUT__HOST = CorePackage.COMMAND__HOST;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_AUT__BINDINGS = CorePackage.COMMAND__BINDINGS;

	/**
	 * The feature id for the '<em><b>Suite Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_AUT__SUITE_ID = CorePackage.COMMAND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aut</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_AUT__AUT = CorePackage.COMMAND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Add Aut</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_AUT_FEATURE_COUNT = CorePackage.COMMAND_FEATURE_COUNT + 2;

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite <em>Add Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Test Suite</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite
	 * @generated
	 */
	EClass getAddTestSuite();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuite <em>Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Suite</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuite()
	 * @see #getAddTestSuite()
	 * @generated
	 */
	EReference getAddTestSuite_Suite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite#getSuiteId()
	 * @see #getAddTestSuite()
	 * @generated
	 */
	EAttribute getAddTestSuite_SuiteId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource <em>Add Test Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Test Resource</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource
	 * @generated
	 */
	EClass getAddTestResource();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getResource()
	 * @see #getAddTestResource()
	 * @generated
	 */
	EReference getAddTestResource_Resource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getArtifactsPath <em>Artifacts Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifacts Path</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getArtifactsPath()
	 * @see #getAddTestResource()
	 * @generated
	 */
	EAttribute getAddTestResource_ArtifactsPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource#getSuiteId()
	 * @see #getAddTestResource()
	 * @generated
	 */
	EAttribute getAddTestResource_SuiteId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.common.commonCommands.RequestShutdown <em>Request Shutdown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Request Shutdown</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.RequestShutdown
	 * @generated
	 */
	EClass getRequestShutdown();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite <em>Cancel Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cancel Suite</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite
	 * @generated
	 */
	EClass getCancelSuite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite#getSuiteId()
	 * @see #getCancelSuite()
	 * @generated
	 */
	EAttribute getCancelSuite_SuiteId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite <em>Begin Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Begin Test Suite</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite
	 * @generated
	 */
	EClass getBeginTestSuite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getSuiteId()
	 * @see #getBeginTestSuite()
	 * @generated
	 */
	EAttribute getBeginTestSuite_SuiteId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getClientId <em>Client Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Client Id</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getClientId()
	 * @see #getBeginTestSuite()
	 * @generated
	 */
	EAttribute getBeginTestSuite_ClientId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getClientSecret <em>Client Secret</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Client Secret</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getClientSecret()
	 * @see #getBeginTestSuite()
	 * @generated
	 */
	EAttribute getBeginTestSuite_ClientSecret();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getLicenseUrl <em>License Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>License Url</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getLicenseUrl()
	 * @see #getBeginTestSuite()
	 * @generated
	 */
	EAttribute getBeginTestSuite_LicenseUrl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getOrganization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Organization</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite#getOrganization()
	 * @see #getBeginTestSuite()
	 * @generated
	 */
	EAttribute getBeginTestSuite_Organization();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddAut <em>Add Aut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Aut</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddAut
	 * @generated
	 */
	EClass getAddAut();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getSuiteId <em>Suite Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suite Id</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getSuiteId()
	 * @see #getAddAut()
	 * @generated
	 */
	EAttribute getAddAut_SuiteId();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getAut <em>Aut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Aut</em>'.
	 * @see org.eclipse.rcptt.cloud.common.commonCommands.AddAut#getAut()
	 * @see #getAddAut()
	 * @generated
	 */
	EReference getAddAut_Aut();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CommonCommandsFactory getCommonCommandsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestSuiteImpl <em>Add Test Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestSuiteImpl
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getAddTestSuite()
		 * @generated
		 */
		EClass ADD_TEST_SUITE = eINSTANCE.getAddTestSuite();

		/**
		 * The meta object literal for the '<em><b>Suite</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TEST_SUITE__SUITE = eINSTANCE.getAddTestSuite_Suite();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_TEST_SUITE__SUITE_ID = eINSTANCE.getAddTestSuite_SuiteId();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestResourceImpl <em>Add Test Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.AddTestResourceImpl
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getAddTestResource()
		 * @generated
		 */
		EClass ADD_TEST_RESOURCE = eINSTANCE.getAddTestResource();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_TEST_RESOURCE__RESOURCE = eINSTANCE.getAddTestResource_Resource();

		/**
		 * The meta object literal for the '<em><b>Artifacts Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_TEST_RESOURCE__ARTIFACTS_PATH = eINSTANCE.getAddTestResource_ArtifactsPath();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_TEST_RESOURCE__SUITE_ID = eINSTANCE.getAddTestResource_SuiteId();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.RequestShutdownImpl <em>Request Shutdown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.RequestShutdownImpl
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getRequestShutdown()
		 * @generated
		 */
		EClass REQUEST_SHUTDOWN = eINSTANCE.getRequestShutdown();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.CancelSuiteImpl <em>Cancel Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CancelSuiteImpl
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getCancelSuite()
		 * @generated
		 */
		EClass CANCEL_SUITE = eINSTANCE.getCancelSuite();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CANCEL_SUITE__SUITE_ID = eINSTANCE.getCancelSuite_SuiteId();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl <em>Begin Test Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.BeginTestSuiteImpl
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getBeginTestSuite()
		 * @generated
		 */
		EClass BEGIN_TEST_SUITE = eINSTANCE.getBeginTestSuite();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEGIN_TEST_SUITE__SUITE_ID = eINSTANCE.getBeginTestSuite_SuiteId();

		/**
		 * The meta object literal for the '<em><b>Client Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEGIN_TEST_SUITE__CLIENT_ID = eINSTANCE.getBeginTestSuite_ClientId();

		/**
		 * The meta object literal for the '<em><b>Client Secret</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEGIN_TEST_SUITE__CLIENT_SECRET = eINSTANCE.getBeginTestSuite_ClientSecret();

		/**
		 * The meta object literal for the '<em><b>License Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEGIN_TEST_SUITE__LICENSE_URL = eINSTANCE.getBeginTestSuite_LicenseUrl();

		/**
		 * The meta object literal for the '<em><b>Organization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEGIN_TEST_SUITE__ORGANIZATION = eINSTANCE.getBeginTestSuite_Organization();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.common.commonCommands.impl.AddAutImpl <em>Add Aut</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.AddAutImpl
		 * @see org.eclipse.rcptt.cloud.common.commonCommands.impl.CommonCommandsPackageImpl#getAddAut()
		 * @generated
		 */
		EClass ADD_AUT = eINSTANCE.getAddAut();

		/**
		 * The meta object literal for the '<em><b>Suite Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_AUT__SUITE_ID = eINSTANCE.getAddAut_SuiteId();

		/**
		 * The meta object literal for the '<em><b>Aut</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_AUT__AUT = eINSTANCE.getAddAut_Aut();

	}

} // CommonCommandsPackage
