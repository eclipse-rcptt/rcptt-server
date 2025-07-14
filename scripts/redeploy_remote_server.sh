#!/bin/bash

set -xe

HOST=172.29.1.229
cd $(dirname $0)
scp redeploy_server.sh "$HOST":/tmp
ssh "$HOST" 'chmod a+x /tmp/redeploy_server.sh'
DIST=../products/products/target/products/org.eclipse.rcptt.cloud.server.product-linux.gtk.x86_64.zip
remote_sum=`ssh "$HOST" 'shasum -b -a 512 /tmp/org.eclipse.rcptt.cloud.server.product-linux.gtk.x86_64.zip' | cut -d ' ' -f 1` || exit 1
local_sum=`shasum -b -a 512  "$DIST" | cut -d ' ' -f 1` 
if [[ $remote_sum != $local_sum ]] ; then
	scp "$DIST" "$HOST":/tmp
fi  
ssh "$HOST" "/tmp/redeploy_server.sh"
