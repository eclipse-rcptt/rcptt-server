#!/bin/bash

HOST=192.168.2.180

scp  /Users/vasiligulevich/git/q7server/products/products/target/products/com.xored.q7.cloud.agent.product-win32.win32.x86_64.zip "$HOST":Documents/
ssh "$HOST" powershell -Command "Remove-Item -LiteralPath Documents\\agent -Force -Recurse -ErrorAction SilentlyContinue"
ssh "$HOST" powershell -Command "Expand-Archive -LiteralPath Documents\\com.xored.q7.cloud.agent.product-win32.win32.x86_64.zip -DestinationPath Documents\\agent"

ssh "$HOST" Documents\\agent\\eclipsec.exe -serverHost 10.8.2.24 -serverPort 5009 -consoleLog