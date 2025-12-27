#/bin/sh 
set -ex
${MVN_CMD=mvn} -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin::set-version "-DnewVersion=$1"
${MVN_CMD=mvn} -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin::set-version "-DnewVersion=$1" --file rcpttTests
${MVN_CMD=mvn} -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin::set-property rcptt-maven-version "$1"