#/bin/sh 

set -e

SNAPSHOT_VERSION=`${MVN_CMD} help:evaluate -q --non-recursive -Dtycho.mode=maven -DforceStdout=true -Dexpression='project.version'`
RELEASE_VERSION=${SNAPSHOT_VERSION%-SNAPSHOT}

`dirname $0`/set-version.sh "$RELEASE_VERSION"