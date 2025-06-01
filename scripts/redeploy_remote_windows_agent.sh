#!/bin/bash

set -xe

AGENT_HOST=172.29.1.228
cd $(dirname $0)
scp  redeploy_agent.ps1 "$AGENT_HOST":Documents/
DIST=../products/products/target/products/org.eclipse.rcptt.cloud.agent.product-win32.win32.x86_64.zip
remote_sum=`ssh "$AGENT_HOST" 'certUtil -hashfile %HOME%/Documents/org.eclipse.rcptt.cloud.agent.product-win32.win32.x86_64.zip SHA512 | findstr /v "hash"' | tr -d '\r'` || exit 1
local_sum=`shasum -b -a 512  "$DIST" | cut -d ' ' -f 1` 
if [[ $remote_sum != $local_sum ]] ; then
	scp "$DIST" "$AGENT_HOST":Documents/
fi

# Start redeploy_agent.ps1 script in a graphical context, if will monitor zip file for changes
