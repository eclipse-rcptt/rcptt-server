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
package org.eclipse.rcptt.cloud.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.cloud.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///q7/cloud/model.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cloudModel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.Q7ArtifactImpl <em>Q7 Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.Q7ArtifactImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getQ7Artifact()
	 * @generated
	 */
	int Q7_ARTIFACT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT__ID = 0;

	/**
	 * The feature id for the '<em><b>Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT__REFS = 1;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT__CONTENT = 2;

	/**
	 * The number of structural features of the '<em>Q7 Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.Q7ArtifactRefImpl <em>Q7 Artifact Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.Q7ArtifactRefImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getQ7ArtifactRef()
	 * @generated
	 */
	int Q7_ARTIFACT_REF = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT_REF__ID = 0;

	/**
	 * The feature id for the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT_REF__HASH = 1;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT_REF__KIND = 2;

	/**
	 * The feature id for the '<em><b>Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT_REF__REFS = 3;

	/**
	 * The number of structural features of the '<em>Q7 Artifact Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int Q7_ARTIFACT_REF_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.TestSuiteImpl <em>Test Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.TestSuiteImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getTestSuite()
	 * @generated
	 */
	int TEST_SUITE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE__ID = Q7_ARTIFACT__ID;

	/**
	 * The feature id for the '<em><b>Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE__REFS = Q7_ARTIFACT__REFS;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE__CONTENT = Q7_ARTIFACT__CONTENT;

	/**
	 * The number of structural features of the '<em>Test Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE_FEATURE_COUNT = Q7_ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl <em>Aut Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.AutInfoImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getAutInfo()
	 * @generated
	 */
	int AUT_INFO = 3;

	/**
	 * The feature id for the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__HASH = 0;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__CLASSIFIER = 1;

	/**
	 * The feature id for the '<em><b>Injection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__INJECTION = 2;

	/**
	 * The feature id for the '<em><b>Ignore Other Injections</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__IGNORE_OTHER_INJECTIONS = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__ID = 4;

	/**
	 * The feature id for the '<em><b>Args</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__ARGS = 5;

	/**
	 * The feature id for the '<em><b>Vm Args</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__VM_ARGS = 6;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__URI = 7;

	/**
	 * The feature id for the '<em><b>License Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__LICENSE_URL = 8;

	/**
	 * The feature id for the '<em><b>Execution Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO__EXECUTION_ENVIRONMENT = 9;

	/**
	 * The number of structural features of the '<em>Aut Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUT_INFO_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl <em>Agent Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getAgentInfo()
	 * @generated
	 */
	int AGENT_INFO = 4;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO__URI = 0;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO__CLASSIFIER = 1;

	/**
	 * The feature id for the '<em><b>Launch Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO__LAUNCH_ID = 2;

	/**
	 * The feature id for the '<em><b>Skip Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO__SKIP_TAGS = 3;

	/**
	 * The number of structural features of the '<em>Agent Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_INFO_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.EnvelopeImpl <em>Envelope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.EnvelopeImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getEnvelope()
	 * @generated
	 */
	int ENVELOPE = 5;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVELOPE__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVELOPE__TO = 1;

	/**
	 * The feature id for the '<em><b>Arch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVELOPE__ARCH = 2;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVELOPE__MESSAGE = 3;

	/**
	 * The feature id for the '<em><b>Q7 Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVELOPE__Q7_INFO = 4;

	/**
	 * The number of structural features of the '<em>Envelope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVELOPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.TestOptionImpl <em>Test Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.TestOptionImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getTestOption()
	 * @generated
	 */
	int TEST_OPTION = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OPTION__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OPTION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Test Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OPTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.TestOptionsImpl <em>Test Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.TestOptionsImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getTestOptions()
	 * @generated
	 */
	int TEST_OPTIONS = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.impl.SuiteMetadataImpl <em>Suite Metadata</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.impl.SuiteMetadataImpl
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getSuiteMetadata()
	 * @generated
	 */
	int SUITE_METADATA = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_METADATA__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_METADATA__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Suite Metadata</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUITE_METADATA_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Values</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OPTIONS__VALUES = 0;

	/**
	 * The number of structural features of the '<em>Test Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OPTIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rcptt.cloud.model.RefKind <em>Ref Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rcptt.cloud.model.RefKind
	 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getRefKind()
	 * @generated
	 */
	int REF_KIND = 9;


	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.model.Q7Artifact <em>Q7 Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Q7 Artifact</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7Artifact
	 * @generated
	 */
	EClass getQ7Artifact();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Q7Artifact#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7Artifact#getId()
	 * @see #getQ7Artifact()
	 * @generated
	 */
	EAttribute getQ7Artifact_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.rcptt.cloud.model.Q7Artifact#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Refs</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7Artifact#getRefs()
	 * @see #getQ7Artifact()
	 * @generated
	 */
	EReference getQ7Artifact_Refs();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.model.Q7Artifact#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Content</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7Artifact#getContent()
	 * @see #getQ7Artifact()
	 * @generated
	 */
	EReference getQ7Artifact_Content();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef <em>Q7 Artifact Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Q7 Artifact Ref</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7ArtifactRef
	 * @generated
	 */
	EClass getQ7ArtifactRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getId()
	 * @see #getQ7ArtifactRef()
	 * @generated
	 */
	EAttribute getQ7ArtifactRef_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getHash <em>Hash</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hash</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getHash()
	 * @see #getQ7ArtifactRef()
	 * @generated
	 */
	EAttribute getQ7ArtifactRef_Hash();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getKind()
	 * @see #getQ7ArtifactRef()
	 * @generated
	 */
	EAttribute getQ7ArtifactRef_Kind();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Refs</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Q7ArtifactRef#getRefs()
	 * @see #getQ7ArtifactRef()
	 * @generated
	 */
	EAttribute getQ7ArtifactRef_Refs();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.model.TestSuite <em>Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Suite</em>'.
	 * @see org.eclipse.rcptt.cloud.model.TestSuite
	 * @generated
	 */
	EClass getTestSuite();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.model.AutInfo <em>Aut Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aut Info</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo
	 * @generated
	 */
	EClass getAutInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AutInfo#getHash <em>Hash</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hash</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getHash()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_Hash();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AutInfo#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classifier</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getClassifier()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_Classifier();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.model.AutInfo#getInjection <em>Injection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Injection</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getInjection()
	 * @see #getAutInfo()
	 * @generated
	 */
	EReference getAutInfo_Injection();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AutInfo#isIgnoreOtherInjections <em>Ignore Other Injections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Other Injections</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#isIgnoreOtherInjections()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_IgnoreOtherInjections();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AutInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getId()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_Id();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.cloud.model.AutInfo#getArgs <em>Args</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Args</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getArgs()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_Args();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.cloud.model.AutInfo#getVmArgs <em>Vm Args</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Vm Args</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getVmArgs()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_VmArgs();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AutInfo#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getUri()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AutInfo#getLicenseUrl <em>License Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>License Url</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getLicenseUrl()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_LicenseUrl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AutInfo#getExecutionEnvironment <em>Execution Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution Environment</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AutInfo#getExecutionEnvironment()
	 * @see #getAutInfo()
	 * @generated
	 */
	EAttribute getAutInfo_ExecutionEnvironment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.model.AgentInfo <em>Agent Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent Info</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AgentInfo
	 * @generated
	 */
	EClass getAgentInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AgentInfo#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AgentInfo#getUri()
	 * @see #getAgentInfo()
	 * @generated
	 */
	EAttribute getAgentInfo_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AgentInfo#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classifier</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AgentInfo#getClassifier()
	 * @see #getAgentInfo()
	 * @generated
	 */
	EAttribute getAgentInfo_Classifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.AgentInfo#getLaunchId <em>Launch Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Launch Id</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AgentInfo#getLaunchId()
	 * @see #getAgentInfo()
	 * @generated
	 */
	EAttribute getAgentInfo_LaunchId();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.rcptt.cloud.model.AgentInfo#getSkipTags <em>Skip Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Skip Tags</em>'.
	 * @see org.eclipse.rcptt.cloud.model.AgentInfo#getSkipTags()
	 * @see #getAgentInfo()
	 * @generated
	 */
	EAttribute getAgentInfo_SkipTags();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.model.Envelope <em>Envelope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Envelope</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Envelope
	 * @generated
	 */
	EClass getEnvelope();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Envelope#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Envelope#getFrom()
	 * @see #getEnvelope()
	 * @generated
	 */
	EAttribute getEnvelope_From();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Envelope#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Envelope#getTo()
	 * @see #getEnvelope()
	 * @generated
	 */
	EAttribute getEnvelope_To();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Envelope#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Envelope#getMessage()
	 * @see #getEnvelope()
	 * @generated
	 */
	EAttribute getEnvelope_Message();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rcptt.cloud.model.Envelope#getQ7Info <em>Q7 Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Q7 Info</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Envelope#getQ7Info()
	 * @see #getEnvelope()
	 * @generated
	 */
	EReference getEnvelope_Q7Info();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rcptt.cloud.model.Envelope#getArch <em>Arch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Arch</em>'.
	 * @see org.eclipse.rcptt.cloud.model.Envelope#getArch()
	 * @see #getEnvelope()
	 * @generated
	 */
	EAttribute getEnvelope_Arch();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Test Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Option</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getTestOption();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTestOption()
	 * @generated
	 */
	EAttribute getTestOption_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTestOption()
	 * @generated
	 */
	EAttribute getTestOption_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rcptt.cloud.model.TestOptions <em>Test Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Options</em>'.
	 * @see org.eclipse.rcptt.cloud.model.TestOptions
	 * @generated
	 */
	EClass getTestOptions();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.rcptt.cloud.model.TestOptions#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Values</em>'.
	 * @see org.eclipse.rcptt.cloud.model.TestOptions#getValues()
	 * @see #getTestOptions()
	 * @generated
	 */
	EReference getTestOptions_Values();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Suite Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Suite Metadata</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getSuiteMetadata();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getSuiteMetadata()
	 * @generated
	 */
	EAttribute getSuiteMetadata_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getSuiteMetadata()
	 * @generated
	 */
	EAttribute getSuiteMetadata_Value();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.rcptt.cloud.model.RefKind <em>Ref Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Ref Kind</em>'.
	 * @see org.eclipse.rcptt.cloud.model.RefKind
	 * @generated
	 */
	EEnum getRefKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.Q7ArtifactImpl <em>Q7 Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.Q7ArtifactImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getQ7Artifact()
		 * @generated
		 */
		EClass Q7_ARTIFACT = eINSTANCE.getQ7Artifact();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute Q7_ARTIFACT__ID = eINSTANCE.getQ7Artifact_Id();

		/**
		 * The meta object literal for the '<em><b>Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference Q7_ARTIFACT__REFS = eINSTANCE.getQ7Artifact_Refs();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference Q7_ARTIFACT__CONTENT = eINSTANCE.getQ7Artifact_Content();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.Q7ArtifactRefImpl <em>Q7 Artifact Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.Q7ArtifactRefImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getQ7ArtifactRef()
		 * @generated
		 */
		EClass Q7_ARTIFACT_REF = eINSTANCE.getQ7ArtifactRef();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute Q7_ARTIFACT_REF__ID = eINSTANCE.getQ7ArtifactRef_Id();

		/**
		 * The meta object literal for the '<em><b>Hash</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute Q7_ARTIFACT_REF__HASH = eINSTANCE.getQ7ArtifactRef_Hash();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute Q7_ARTIFACT_REF__KIND = eINSTANCE.getQ7ArtifactRef_Kind();

		/**
		 * The meta object literal for the '<em><b>Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute Q7_ARTIFACT_REF__REFS = eINSTANCE.getQ7ArtifactRef_Refs();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.TestSuiteImpl <em>Test Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.TestSuiteImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getTestSuite()
		 * @generated
		 */
		EClass TEST_SUITE = eINSTANCE.getTestSuite();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.AutInfoImpl <em>Aut Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.AutInfoImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getAutInfo()
		 * @generated
		 */
		EClass AUT_INFO = eINSTANCE.getAutInfo();

		/**
		 * The meta object literal for the '<em><b>Hash</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__HASH = eINSTANCE.getAutInfo_Hash();

		/**
		 * The meta object literal for the '<em><b>Classifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__CLASSIFIER = eINSTANCE.getAutInfo_Classifier();

		/**
		 * The meta object literal for the '<em><b>Injection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUT_INFO__INJECTION = eINSTANCE.getAutInfo_Injection();

		/**
		 * The meta object literal for the '<em><b>Ignore Other Injections</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__IGNORE_OTHER_INJECTIONS = eINSTANCE.getAutInfo_IgnoreOtherInjections();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__ID = eINSTANCE.getAutInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Args</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__ARGS = eINSTANCE.getAutInfo_Args();

		/**
		 * The meta object literal for the '<em><b>Vm Args</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__VM_ARGS = eINSTANCE.getAutInfo_VmArgs();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__URI = eINSTANCE.getAutInfo_Uri();

		/**
		 * The meta object literal for the '<em><b>License Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__LICENSE_URL = eINSTANCE.getAutInfo_LicenseUrl();

		/**
		 * The meta object literal for the '<em><b>Execution Environment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUT_INFO__EXECUTION_ENVIRONMENT = eINSTANCE.getAutInfo_ExecutionEnvironment();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl <em>Agent Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.AgentInfoImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getAgentInfo()
		 * @generated
		 */
		EClass AGENT_INFO = eINSTANCE.getAgentInfo();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO__URI = eINSTANCE.getAgentInfo_Uri();

		/**
		 * The meta object literal for the '<em><b>Classifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO__CLASSIFIER = eINSTANCE.getAgentInfo_Classifier();

		/**
		 * The meta object literal for the '<em><b>Launch Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO__LAUNCH_ID = eINSTANCE.getAgentInfo_LaunchId();

		/**
		 * The meta object literal for the '<em><b>Skip Tags</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT_INFO__SKIP_TAGS = eINSTANCE.getAgentInfo_SkipTags();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.EnvelopeImpl <em>Envelope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.EnvelopeImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getEnvelope()
		 * @generated
		 */
		EClass ENVELOPE = eINSTANCE.getEnvelope();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVELOPE__FROM = eINSTANCE.getEnvelope_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVELOPE__TO = eINSTANCE.getEnvelope_To();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVELOPE__MESSAGE = eINSTANCE.getEnvelope_Message();

		/**
		 * The meta object literal for the '<em><b>Q7 Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVELOPE__Q7_INFO = eINSTANCE.getEnvelope_Q7Info();

		/**
		 * The meta object literal for the '<em><b>Arch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVELOPE__ARCH = eINSTANCE.getEnvelope_Arch();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.TestOptionImpl <em>Test Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.TestOptionImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getTestOption()
		 * @generated
		 */
		EClass TEST_OPTION = eINSTANCE.getTestOption();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_OPTION__KEY = eINSTANCE.getTestOption_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_OPTION__VALUE = eINSTANCE.getTestOption_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.TestOptionsImpl <em>Test Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.TestOptionsImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getTestOptions()
		 * @generated
		 */
		EClass TEST_OPTIONS = eINSTANCE.getTestOptions();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_OPTIONS__VALUES = eINSTANCE.getTestOptions_Values();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.impl.SuiteMetadataImpl <em>Suite Metadata</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.impl.SuiteMetadataImpl
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getSuiteMetadata()
		 * @generated
		 */
		EClass SUITE_METADATA = eINSTANCE.getSuiteMetadata();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUITE_METADATA__KEY = eINSTANCE.getSuiteMetadata_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUITE_METADATA__VALUE = eINSTANCE.getSuiteMetadata_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.rcptt.cloud.model.RefKind <em>Ref Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rcptt.cloud.model.RefKind
		 * @see org.eclipse.rcptt.cloud.model.impl.ModelPackageImpl#getRefKind()
		 * @generated
		 */
		EEnum REF_KIND = eINSTANCE.getRefKind();

	}

} //ModelPackage
