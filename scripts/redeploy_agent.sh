#!/bin/sh

set -ex

cd ~
jps -vm | grep org.eclipse.equinox.launcher | grep /agent/eclipse | cut -d ' ' -f 1 | xargs --no-run-if-empty kill
killall eclipse || true
sleep 1
rm -r agent 2>/dev/null|| true
unzip -d agent /tmp/org.eclipse.rcptt.cloud.agent.product-linux.gtk.x86_64.zip
cd agent
cat > xstartup.sh <<EOF
#!/bin/sh
./eclipse -consoleLog -serverHost 172.29.1.229  -serverPort 5009 -data ws -consoleLog -vm /usr/lib/jvm/jdk-21.0.7-oracle-x64/lib/server/ -vmargs '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8001'
EOF
chmod a+x xstartup.sh
vncserver -localhost false -autokill -xstartup ./xstartup.sh
sleep 10000
