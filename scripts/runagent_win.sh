#!/bin/bash
export HOST=${1}
export ID=${2}
export PORT=1212
export CMD="./tragent/eclipse -consoleLog -serverHost ${HOST} -serverPort ${PORT} -command "
export PT=c:/
echo "Running agent on ${HOST} id: ${ID}"
${CMD} rmdir -path ${PT}/agent/agent${ID}
export AgentCmd="java -jar ${PT}/agent/agent/plugins/org.eclipse.equinox.launcher_1.2.0.v20110502.jar -application com.xored.q7.cloud.agent.app.headless -serverHost deb64c2q74 -serverPort 7504 -telnetPort 132${ID} -thisHost ${HOST} -port 750${ID} -data ${PT}/agent/agent${ID}"
echo ${AgentCmd}
${CMD} exec -path "${AgentCmd}" >${HOST}_out.txt &
