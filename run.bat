@echo off
setlocal enabledelayedexpansion
cd /d "%~dp0"
if not exist out mkdir out
set "SRC_FILES="
for /r src %%F in (*.java) do (
  set "SRC_FILES=!SRC_FILES! "%%~fF""
)
javac -cp "lib/mysql-connector-j-9.7.0.jar" -d out !SRC_FILES!
java -cp "out;lib/mysql-connector-j-9.7.0.jar" HospitalManagementApp
