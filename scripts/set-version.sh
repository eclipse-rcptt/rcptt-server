#/bin/sh 
set -ex
${MVN_CMD=mvn} -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin::set-version "-DnewVersion=$1"