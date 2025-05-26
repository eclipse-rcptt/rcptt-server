#!/bin/sh

set -ex

cd ~
jps -v | grep org.eclipse.equinox.launcher | cut -d ' ' -f 1 | xargs --no-run-if-empty kill
sleep 1
rm -r server
unzip -d server /tmp/org.eclipse.rcptt.cloud.server.product-linux.gtk.x86_64.zip
cd server
nohup ./eclipse -port 5009 -data ws -consoleLog -vmargs '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000' &
sleep 5
tail -n 100 nohup.out