#/bin/sh 
set -ex
${MVN_CMD:=mvn} -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin::set-version "-DnewVersion=$1"
${MVN_CMD} -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin::set-version "-DnewVersion=$1" --file rcpttTests
${MVN_CMD} clean install --file maven # to allow Maven plugins to be used in a subsequent build

