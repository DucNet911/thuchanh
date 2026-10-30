@echo off
setlocal EnableExtensions DisableDelayedExpansion
cd /d "%~dp0"
call "%~dp0BUILD.cmd"
if errorlevel 1 goto build_failed

:menu
echo.
echo WS - B23DCVT095
echo 1. LargestNumber              - Ghep so lon nhat
echo 2. SumArray                   - Tinh tong
echo 3. RotateCharacters           - Xoay ky tu
echo 4. GroupByVowelCount           - Nhom theo nguyen am
echo 5. SortEmployeesBySeniority    - Sap xep nhan vien
echo 6. FilterPotentialCustomers   - Loc khach hang
echo 7. Kiem tra thu vien          - Khong ket noi server
echo 0. Thoat
echo Lua chon 1-6 se nhan du lieu va gui ket qua len server.
choice /C 12345670 /N /M "Chon 1-7 hoac 0: "
set "WS_CHOICE=%errorlevel%"
set "WS_MAIN="
if "%WS_CHOICE%"=="1" set "WS_MAIN=WS.LargestNumber"
if "%WS_CHOICE%"=="2" set "WS_MAIN=WS.SumArray"
if "%WS_CHOICE%"=="3" set "WS_MAIN=WS.RotateCharacters"
if "%WS_CHOICE%"=="4" set "WS_MAIN=WS.GroupByVowelCount"
if "%WS_CHOICE%"=="5" set "WS_MAIN=WS.SortEmployeesBySeniority"
if "%WS_CHOICE%"=="6" set "WS_MAIN=WS.FilterPotentialCustomers"
if "%WS_CHOICE%"=="7" set "WS_MAIN=tools.EnvironmentCheck"
if not defined WS_MAIN exit /b 0
echo.
"%WS_JDK%\bin\java.exe" -Dfile.encoding=UTF-8 -cp "out;lib\*" %WS_MAIN%
if errorlevel 1 (
    echo.
    echo [ERROR] Chuong trinh gap loi. Xem thong bao phia tren.
    echo Neu Connection refused/timed out: kiem tra mang va server cua de.
)
echo.
pause
goto menu

:build_failed
echo.
pause
exit /b 1
