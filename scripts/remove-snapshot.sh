#/bin/sh 

set -ex

SNAPSHOT_VERSION=`${MVN_CMD=mvn} --quiet --non-recursive help:evaluate -Dtycho.mode=maven -DforceStdout -Dexpression='project.version'`
RELEASE_VERSION=${SNAPSHOT_VERSION%-SNAPSHOT}

`dirname $0`/set-version.sh "$RELEASE_VERSION"