#!/bin/bash
echo "Processing for ${1}"
export HOST=${1}
export PORT=1212
export CMD="./tragent/eclipse -consoleLog -serverHost ${HOST} -serverPort ${PORT} -command "
export PT=c:/
export client=${2}

${CMD} kill -path asd
${CMD} rmdir -path ${PT}/agent
${CMD} mkdir -path ${PT}/agent
${CMD} upload -path ${client} -extraPath ${PT}/agent/agent.zip
${CMD} unzip -path ${PT}/agent/agent.zip -extraPath ${PT}/agent/agent
