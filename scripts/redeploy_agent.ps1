cd C:\Users\Test\Documents
$DebugPreference = "Continue"
$global:ProgressPreference = "SilentlyContinue"
$pids = & jps -v | Where {! ( $_ -match 'Jps' ) } | ForEach-Object {
        [int]($_ -Split " ")[0]
} | Where { $_ }
if ($pids -ne $null) {
	$pids | Out-String -stream | Write-Debug
    $pids | ForEach-Object { & taskkill.exe /F /PID $_ }
}

& taskkill.exe /F /IM eclipsec.exe 
Start-Sleep -Seconds 2.5
if (Test-Path agent) {
	Remove-Item -Recurse -Force agent -ErrorAction Stop
}
Expand-Archive -Path "org.eclipse.rcptt.cloud.agent.product-win32.win32.x86_64.zip" -DestinationPath "agent"
Start-Process -Wait agent\eclipsec.exe -ArgumentList "-consoleLog -serverHost 172.29.1.229  -serverPort 5009 -data agent\ws -vm `"C:\Users\Test\Documents\jdk-21.0.6.7-hotspot\bin\java.exe`" -vmargs -XX:+HeapDumpOnOutOfMemoryError -Xlog:gc"