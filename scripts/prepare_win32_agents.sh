#!/bin/bash
export File=com.xored.q7.cloud.agent.product-win32.win32.x86.zip
./prepare_agent_win.sh 192.168.4.203 ${File} > 192.168.4.203_out.txt & 
./prepare_agent_win.sh 192.168.4.211 ${File} > 192.168.4.211_out.txt &
./prepare_agent_win.sh 192.168.4.221 ${File} > 192.168.4.221_out.txt &
./prepare_agent_win.sh 192.168.4.201 ${File} > 192.168.4.201_out.txt &
./prepare_agent_win.sh 192.168.4.223 ${File} > 192.168.4.223_out.txt &
./prepare_agent_win.sh 192.168.2.69 ${File} > 192.168.2.69_out.txt &
./prepare_agent_win.sh 192.168.4.202  ${File} > 192.168.4.202_out.txt &
./prepare_agent_win.sh 192.168.2.70 ${File} > 192.168.2.70_out.txt &
./prepare_agent_win.sh 192.168.4.212 ${File} > 192.168.4.212_out.txt &
./prepare_agent_win.sh 192.168.4.213  ${File} > 192.168.4.213_out.txt &
./prepare_agent_win.sh 192.168.4.222  ${File} > 192.168.4.222_out.txt &
./prepare_agent_win.sh 192.168.2.68 ${File} > 192.168.2.68_out.txt &
./prepare_agent_win.sh 192.168.2.67 ${File} > 192.168.2.67_out.txt &
./prepare_agent_win.sh 192.168.2.66 ${File} > 192.168.2.66_out.txt &

