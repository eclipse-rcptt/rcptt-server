#!/bin/sh

set -ex

cd ~
jps -vm | grep org.eclipse.equinox.launcher | grep /agent/eclipse | cut -d ' ' -f 1 | xargs --no-run-if-empty kill
sleep 1
rm -r agent
unzip -d agent /tmp/org.eclipse.rcptt.cloud.agent.product-linux.gtk.x86_64.zip
cd agent
vncserver -noxstartup -autokill -- ./eclipse -consoleLog -serverHost 172.29.1.229  -serverPort 5009 -data ../ws/agent -consoleLog -vmargs '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8001'
sleep 5
