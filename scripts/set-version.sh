#/bin/sh 

mvn -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin:4.0.10:set-version "-DnewVersion=$1"