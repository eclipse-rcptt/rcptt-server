#!/bin/bash

set -xeuo pipefail

OPTIONS="$@"

for i in /`pwd`/../rcptt/repository/full/target/repository/  /`pwd`/../org.eclipse.rcptt/repository/full/target/repository/ ; do
	[[ -d "$i" ]] && break
done

mvn clean install -DplatformRepository=file:/"$i" $OPTIONS
