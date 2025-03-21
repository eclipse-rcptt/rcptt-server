#!/bin/bash
export HOST=${1}
export PORT=1212
export CMD="./tragent/eclipse -consoleLog -serverHost ${HOST} -serverPort ${PORT} -command "
export PT=/tmp/ws
export client=${2}

${CMD} kill -path asd >${HOST}_out.txt &
