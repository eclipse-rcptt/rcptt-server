#!/bin/sh

ROOT=$(dirname $(dirname $0))
COMMON_ARGS=""
if [[ -d "$JAVA_HOME" ]] ; then
  COMMON_ARGS="$COMMON_ARGS -vm $JAVA_HOME/bin/java"
fi

$ROOT/products/products/target/products/org.eclipse.rcptt.cloud.server.product/macosx/cocoa/aarch64/Eclipse.app/Contents/MacOS/eclipse \
$COMMON_ARGS -consoleLog -port 5009  &
SERVER=$!
trap "kill $SERVER" EXIT 
$ROOT/products/products/target/products/org.eclipse.rcptt.cloud.agent.product/macosx/cocoa/aarch64/Eclipse.app/Contents/MacOS/eclipse \
$COMMON_ARGS -consoleLog -data /tmp/ws/agent -serverHost localhost -serverPort 5009
