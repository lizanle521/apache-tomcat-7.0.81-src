@echo off
:: echo off是 关闭命令回显，只打印输出结果
:: rem 是注释
rem Licensed to the Apache Software Foundation (ASF) under one or more
rem contributor license agreements.  See the NOTICE file distributed with
rem this work for additional information regarding copyright ownership.
rem The ASF licenses this file to You under the Apache License, Version 2.0
rem (the "License"); you may not use this file except in compliance with
rem the License.  You may obtain a copy of the License at
rem
rem     http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS,
rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
rem See the License for the specific language governing permissions and
rem limitations under the License.

rem ---------------------------------------------------------------------------
rem Start script for the CATALINA Server
rem ---------------------------------------------------------------------------
::执行 Setlocal 之后所做的环境改动只限于批处理文件
setlocal

rem Guess CATALINA_HOME if not defined
:: %cd% 表示当前路径，和pwd作用差不多
set "CURRENT_DIR=%cd%"
:: 如果 CATALINA_HOME环境变量有设置，那么跳转到 gotHome
if not "%CATALINA_HOME%" == "" goto gotHome
::如果没有设置，那么将当前目录设置为 CATALINA_HOME
set "CATALINA_HOME=%CURRENT_DIR%"
:: CATALINA_HOME中存在 catalina.bat这个文件的话，那么跳转到 okHome
if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
cd ..
::如果不存在CATALINA_HOME/bin/catalina.bat,那么就跳转到当前目录的上一层，继续看是否存在这个脚本
set "CATALINA_HOME=%cd%"
cd "%CURRENT_DIR%"
:: 遇到跳转标签并不会终止或者跳过，而是接着执行
:gotHome
if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
echo The CATALINA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
:: 如果还是没有这个脚本，那么就报错并且终止
goto end

:okHome
:: 设置EXECUTABLE变量为 catalina.bat脚本的路径
set "EXECUTABLE=%CATALINA_HOME%\bin\catalina.bat"

rem Check that target executable exists
:: 检查EXECUTABLE变量路径文件存在的话，那么跳转到 okExec
if exist "%EXECUTABLE%" goto okExec
:: 否则报错 并终止运行
echo Cannot find "%EXECUTABLE%"
echo This file is needed to run this program
goto end
:okExec
:: 剩下的参数放到  CMD_LINE_ARGS变量中去
rem Get remaining unshifted command line arguments and save them in the
set CMD_LINE_ARGS=
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs
:: 执行catalina.bat start CMD_LINE_ARGS .正常情况下 CMD_LINE_ARGS都是空的
call "%EXECUTABLE%" start %CMD_LINE_ARGS%

:end
