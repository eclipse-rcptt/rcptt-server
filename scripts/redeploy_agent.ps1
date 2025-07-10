 # Monitors a zip distribution file and restarts the agent whenever distribbution is updated
 # Has to be started in graphical context
 
$DebugPreference = "Continue"
$global:ProgressPreference = "SilentlyContinue"


function Restart-Agent {
	param (
		[System.IO.FileInfo]$zipPath 
	)
	cd C:\Users\Test\Documents
	& taskkill.exe /F /IM eclipsec.exe
	& jps -l | Select-String "org.eclipse.equinox.launcher" | ForEach-Object {
		& taskkill.exe /F /PID ($_ -Split " ")[0]
	}
	& jps -l | Select-String "Worker" | ForEach-Object {
		& taskkill.exe /F /PID ($_ -Split " ")[0]
	}
	Start-Sleep -Seconds 10
	if (Test-Path agent) {
		# \\?\ allows referencing a very long path
		# It is needed even when the original path is short if -Recurse is given
		Get-Item agent | ForEach-Object { "\\?\" + $_.FullName } | Remove-Item -Recurse -Force  -ErrorAction Stop
	}
	Expand-Archive -Path $zipPath -DestinationPath "agent"
	Start-Process -PassThru agent\eclipsec.exe -ArgumentList "-consoleLog -serverHost 172.29.1.229  -serverPort 5009 -data agent\ws -vm `"C:\Users\Test\Documents\jdk-21.0.6.7-hotspot\bin\java.exe`" -vmargs -XX:+HeapDumpOnOutOfMemoryError -Xlog:gc"
}


function Wait-Modified {
	param (
		[System.IO.FileInfo]$path,
        [System.DateTime]$since
	)
	while ($true) {
		[System.DateTime]$modifiedDate = (Get-Item $path).LastWriteTime
		Write-Debug "File $path is last modified $modifiedDate"

		# Ensure that file is not being written
		[System.DateTime]$newerThan = (Get-Date).AddMinutes(-1)
		if ($modifiedDate -gt $newerThan) {
            $seconds = (New-TimeSpan -Start $newerThan -End $modifiedDate).TotalSeconds
            Write-Debug "Waiting for modifications to stop. Sleeping for $seconds seconds"
			Start-Sleep -Seconds $seconds
			continue
		}
		
		if ($modifiedDate -gt $since) { 
			return $modifiedDate
		}
		
		Start-Sleep 10
	}
}

[System.IO.FileInfo]$zipPath = "C:\Users\Test\Documents\org.eclipse.rcptt.cloud.agent.product-win32.win32.x86_64.zip"

$lastModified = (Get-Date -Year 1)
while ($true) {	
	$lastModified = Wait-Modified $zipPath $lastModified
	
	Restart-Agent $zipPath
	
	Start-Sleep 10
}  
 
