@echo off
rem Intentionally return WS_JDK to RUN.cmd in the same command session.
cd /d "%~dp0"
call "%~dp0tools\find-jdk.cmd"
if errorlevel 1 exit /b 1
echo JDK: %WS_JDK%
if not exist "out" mkdir "out"
if not exist "out" exit /b 1
> "out\sources.txt" (
    for %%F in (WS\*.java tools\EnvironmentCheck.java) do @echo "%%F"
)
"%WS_JDK%\bin\javac.exe" --release 21 -encoding UTF-8 -cp "lib\*" -d "out" @out\sources.txt
if errorlevel 1 (
    echo [ERROR] Bien dich that bai. Xem loi phia tren.
    exit /b 1
)
xcopy "wsdl\*" "out\wsdl\" /E /I /Y /Q >nul
if errorlevel 1 (
    echo [ERROR] Khong chep duoc WSDL vao out.
    exit /b 1
)
echo BUILD OK.
exit /b 0
