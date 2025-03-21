#!/bin/bash

set -exuo pipefail

HEADER_FILE="/tmp/copyright.txt"
TEMP_FILE="/tmp/temp.java"
MISSING_COPYRIGHT_DETECTION=" "

for i in Copyright '(c)' Author @author ; do
	MISSING_COPYRIGHT_DETECTION="$MISSING_COPYRIGHT_DETECTION -not -exec grep -q $i {} ; ";
done

function prepend() {
    	cat "$HEADER_FILE" "$1"
}

function insert_xml() {
	grep '<?' "$1"
	cat "$HEADER_FILE"
	grep -v '<?' "$1"
}

function process_all() {
	while read -r file; do
    	"$1" "$file" > "$TEMP_FILE" &&  mv "$TEMP_FILE" "$file"
	done
}

cat > "$HEADER_FILE" <<EOF
/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Xored Software Inc - initial API and implementation
 ********************************************************************************/
EOF

find . -type f \( -name \*.java -or -name \*.js -or -name \*.css -or -name Jenkinsfile\* \) -not -path \*/gen-src/\* $MISSING_COPYRIGHT_DETECTION -print | process_all prepend

cat > "$HEADER_FILE" <<EOF
###############################################################################
# Copyright (c) 2025 Xored Software Inc.
# This program and the accompanying materials are made available
# under the terms of the Eclipse Public License 2.0 which is available
# at http://www.eclipse.org/legal/epl-2.0.
# 
# SPDX-License-Identifier: EPL-2.0
# 
# Contributors: 
#    Xored Software Inc. - initial API and implementation
###############################################################################
EOF

find . -type f -name "build.properties" $MISSING_COPYRIGHT_DETECTION -print | process_all prepend

cat > "$HEADER_FILE" <<EOF
<!--
Copyright (c) 2025 Xored Software Inc and others

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

SPDX-License-Identifier: EPL-2.0

Contributors:
    Xored Software Inc - initial API and implementation
-->
EOF

find . -type f \( -name "plugin.xml" -or -name pom.xml \) $MISSING_COPYRIGHT_DETECTION -print | process_all insert_xml



