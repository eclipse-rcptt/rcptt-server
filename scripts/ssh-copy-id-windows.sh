#!/bin/bash

set -e

HOST="$1"
function ssh_command() {
    sshpass -f ssh_password.txt ssh -o StrictHostKeyChecking=no "Test@$HOST" "$@"
}

ssh -o StrictHostKeyChecking=no -o BatchMode=yes "Test@$HOST" echo Success && exit 0 || true
authorizedKey=`cat ~/.ssh/id_rsa.pub`
ssh_command powershell Add-Content -Force -Path \$env:ProgramData\\ssh\\administrators_authorized_keys -Value "'$authorizedKey'"
ssh_command icacls.exe '%ProgramData%\ssh\administrators_authorized_keys' /inheritance:r /grant Administrators:F /grant SYSTEM:F
ssh -o StrictHostKeyChecking=no -o BatchMode=yes "Test@$HOST" echo Success || exit 10
