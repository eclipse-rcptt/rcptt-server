/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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


pipeline {
   agent {
      // Remove implicit -V Maven argument. See https://gitlab.eclipse.org/eclipsefdn/helpdesk/-/issues/6398
      kubernetes {
        inheritFrom 'basic'
        yaml '''
            spec:
                volumes:
                - emptyDir:
                    medium: ""
                  name: "m2-dir"
            '''
      }
   }

  options {
     timestamps()
     buildDiscarder(logRotator(numToKeepStr: '30', daysToKeepStr: '30', artifactNumToKeepStr: '1'))
     disableConcurrentBuilds()
     timeout(time: 1, unit: 'HOURS')
  }
  
	tools {
		jdk 'temurin-jdk21-latest'
		maven 'apache-maven-latest'
	}
  
  stages {
    stage('Maven') {
      steps {
		sh 'scripts/remove-snapshot.sh'
        sh 'mvn clean deploy'
      }
      post {
        always {
          junit "tests/*/target/*-reports/*.xml"
          archiveArtifacts allowEmptyArchive: false, artifacts: '**/*.hrpof, **/*.log'
        }
      }
    }
  }

}
