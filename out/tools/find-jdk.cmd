@echo off
rem Prefer an explicit override, then the user's existing JDK 21.
set "WS_JDK="
if defined WS_JAVA_HOME call :try "%WS_JAVA_HOME%"
if not defined WS_JDK call :try "C:\Program Files\Java\jdk-21"
if not defined WS_JDK if defined JAVA_HOME call :try "%JAVA_HOME%"
for /d %%J in ("%ProgramFiles%\Java\jdk-21*" "%ProgramFiles%\Eclipse Adoptium\jdk-21*" "%ProgramFiles%\Microsoft\jdk-21*" "%ProgramFiles%\Zulu\zulu-21*" "%USERPROFILE%\.jdks\*21*") do if not defined WS_JDK call :try "%%~fJ"
for /f "delims=" %%J in ('where.exe javac.exe 2^>nul') do if not defined WS_JDK call :from_bin "%%~dpJ"
if defined WS_JDK exit /b 0
echo [ERROR] Khong tim thay JDK 21 hoac moi hon.
echo Dat WS_JAVA_HOME tro toi thu muc JDK da cai, khong gom bin.
echo Vi du PowerShell: $env:WS_JAVA_HOME = 'C:\Program Files\Java\jdk-21'
exit /b 1

:from_bin
call :try "%~1.."
exit /b 0

:try
if not exist "%~1\bin\java.exe" exit /b 0
if not exist "%~1\bin\javac.exe" exit /b 0
"%~1\bin\javac.exe" --release 21 -version >nul 2>nul
if errorlevel 1 exit /b 0
set "WS_JDK=%~f1"
exit /b 0
