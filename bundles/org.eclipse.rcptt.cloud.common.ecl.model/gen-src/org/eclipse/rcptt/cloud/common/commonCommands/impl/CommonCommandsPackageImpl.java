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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.rcptt.core.scenario.ScenarioPackage;
import org.eclipse.rcptt.ecl.core.CorePackage;

import org.eclipse.rcptt.launching.injection.InjectionPackage;
import org.eclipse.rcptt.cloud.common.commonCommands.AddAut;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsFactory;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsPackage;
import org.eclipse.rcptt.cloud.common.commonCommands.RequestShutdown;
import org.eclipse.rcptt.cloud.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommonCommandsPackageImpl extends EPackageImpl implements CommonCommandsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addTestSuiteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addTestResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requestShutdownEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cancelSuiteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beginTestSuiteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addAutEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package
	 * package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.xored.q7.cloud.common.commonCommands.CommonCommandsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CommonCommandsPackageImpl() {
		super(eNS_URI, CommonCommandsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link CommonCommandsPackage#eINSTANCE} when that field is accessed. Clients
	 * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CommonCommandsPackage init() {
		if (isInited) return (CommonCommandsPackage)EPackage.Registry.INSTANCE.getEPackage(CommonCommandsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCommonCommandsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CommonCommandsPackageImpl theCommonCommandsPackage = registeredCommonCommandsPackage instanceof CommonCommandsPackageImpl ? (CommonCommandsPackageImpl)registeredCommonCommandsPackage : new CommonCommandsPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		ModelPackage.eINSTANCE.eClass();
		ScenarioPackage.eINSTANCE.eClass();
		InjectionPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCommonCommandsPackage.createPackageContents();

		// Initialize created meta-data
		theCommonCommandsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCommonCommandsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CommonCommandsPackage.eNS_URI, theCommonCommandsPackage);
		return theCommonCommandsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAddTestSuite() {
		return addTestSuiteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAddTestSuite_Suite() {
		return (EReference)addTestSuiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddTestSuite_SuiteId() {
		return (EAttribute)addTestSuiteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAddTestResource() {
		return addTestResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAddTestResource_Resource() {
		return (EReference)addTestResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddTestResource_ArtifactsPath() {
		return (EAttribute)addTestResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddTestResource_SuiteId() {
		return (EAttribute)addTestResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRequestShutdown() {
		return requestShutdownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCancelSuite() {
		return cancelSuiteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCancelSuite_SuiteId() {
		return (EAttribute)cancelSuiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBeginTestSuite() {
		return beginTestSuiteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBeginTestSuite_SuiteId() {
		return (EAttribute)beginTestSuiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBeginTestSuite_ClientId() {
		return (EAttribute)beginTestSuiteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBeginTestSuite_ClientSecret() {
		return (EAttribute)beginTestSuiteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBeginTestSuite_LicenseUrl() {
		return (EAttribute)beginTestSuiteEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBeginTestSuite_Organization() {
		return (EAttribute)beginTestSuiteEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAddAut() {
		return addAutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddAut_SuiteId() {
		return (EAttribute)addAutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAddAut_Aut() {
		return (EReference)addAutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CommonCommandsFactory getCommonCommandsFactory() {
		return (CommonCommandsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		addTestSuiteEClass = createEClass(ADD_TEST_SUITE);
		createEReference(addTestSuiteEClass, ADD_TEST_SUITE__SUITE);
		createEAttribute(addTestSuiteEClass, ADD_TEST_SUITE__SUITE_ID);

		addTestResourceEClass = createEClass(ADD_TEST_RESOURCE);
		createEReference(addTestResourceEClass, ADD_TEST_RESOURCE__RESOURCE);
		createEAttribute(addTestResourceEClass, ADD_TEST_RESOURCE__ARTIFACTS_PATH);
		createEAttribute(addTestResourceEClass, ADD_TEST_RESOURCE__SUITE_ID);

		requestShutdownEClass = createEClass(REQUEST_SHUTDOWN);

		cancelSuiteEClass = createEClass(CANCEL_SUITE);
		createEAttribute(cancelSuiteEClass, CANCEL_SUITE__SUITE_ID);

		beginTestSuiteEClass = createEClass(BEGIN_TEST_SUITE);
		createEAttribute(beginTestSuiteEClass, BEGIN_TEST_SUITE__SUITE_ID);
		createEAttribute(beginTestSuiteEClass, BEGIN_TEST_SUITE__CLIENT_ID);
		createEAttribute(beginTestSuiteEClass, BEGIN_TEST_SUITE__CLIENT_SECRET);
		createEAttribute(beginTestSuiteEClass, BEGIN_TEST_SUITE__LICENSE_URL);
		createEAttribute(beginTestSuiteEClass, BEGIN_TEST_SUITE__ORGANIZATION);

		addAutEClass = createEClass(ADD_AUT);
		createEAttribute(addAutEClass, ADD_AUT__SUITE_ID);
		createEReference(addAutEClass, ADD_AUT__AUT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		ModelPackage theModelPackage = (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		addTestSuiteEClass.getESuperTypes().add(theCorePackage.getCommand());
		addTestResourceEClass.getESuperTypes().add(theCorePackage.getCommand());
		requestShutdownEClass.getESuperTypes().add(theCorePackage.getCommand());
		cancelSuiteEClass.getESuperTypes().add(theCorePackage.getCommand());
		beginTestSuiteEClass.getESuperTypes().add(theCorePackage.getCommand());
		addAutEClass.getESuperTypes().add(theCorePackage.getCommand());

		// Initialize classes and features; add operations and parameters
		initEClass(addTestSuiteEClass, AddTestSuite.class, "AddTestSuite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddTestSuite_Suite(), theModelPackage.getTestSuite(), null, "suite", null, 0, 1, AddTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddTestSuite_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, AddTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addTestResourceEClass, AddTestResource.class, "AddTestResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddTestResource_Resource(), theModelPackage.getQ7Artifact(), null, "resource", null, 0, 1, AddTestResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddTestResource_ArtifactsPath(), theEcorePackage.getEString(), "artifactsPath", null, 0, 1, AddTestResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddTestResource_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, AddTestResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requestShutdownEClass, RequestShutdown.class, "RequestShutdown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cancelSuiteEClass, CancelSuite.class, "CancelSuite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCancelSuite_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, CancelSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beginTestSuiteEClass, BeginTestSuite.class, "BeginTestSuite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBeginTestSuite_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, BeginTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeginTestSuite_ClientId(), theEcorePackage.getEString(), "clientId", null, 0, 1, BeginTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeginTestSuite_ClientSecret(), theEcorePackage.getEString(), "clientSecret", null, 0, 1, BeginTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeginTestSuite_LicenseUrl(), theEcorePackage.getEString(), "licenseUrl", null, 0, 1, BeginTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeginTestSuite_Organization(), theEcorePackage.getEString(), "organization", null, 0, 1, BeginTestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addAutEClass, AddAut.class, "AddAut", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAddAut_SuiteId(), theEcorePackage.getEString(), "suiteId", null, 0, 1, AddAut.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAddAut_Aut(), theModelPackage.getAutInfo(), null, "aut", null, 0, 1, AddAut.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/ecl/internal
		createInternalAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/ecl/internal</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createInternalAnnotations() {
		String source = "http://www.eclipse.org/ecl/internal";
		addAnnotation
		  (addTestSuiteEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (addTestResourceEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (requestShutdownEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (cancelSuiteEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (beginTestSuiteEClass,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (addAutEClass,
		   source,
		   new String[] {
		   });
	}

} // CommonCommandsPackageImpl
