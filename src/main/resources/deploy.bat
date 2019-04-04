@echo off

set host=${app.local.host}
set port=${app.local.port}
set user=${app.user.name}
set password=${app.user.password}
set baseDir=${app.local.basedir}
set war=${project.build.directory}\\${project.build.finalName}.war
set deltaJar=${project.build.directory}\\${project.build.finalName}\\WEB-INF\\lib\\${project.build.finalName}.jar
set startCli=${app.cli.start}
set tailLogCli=${app.cli.taillog}


echo.
echo ***************************************************************************************
echo UPLOAD...
echo ***************************************************************************************

if "%~1"=="-delta" (
    goto uploadDeltaJar
) else (
    goto uploadWar
)

:uploadWar
@echo on
PSCP -l %user% -pw %password% -P %port% "%war%" "%host%:%baseDir%/${project.artifactId}.war"
@echo off
goto startup

:uploadDeltaJar
@echo on
PSCP -l %user% -pw %password% -P %port% "%deltaJar%" "%host%:%baseDir%/${project.artifactId}/WEB-INF/lib/"
@echo off
goto startup

:startup
echo.
echo ***************************************************************************************
echo STARTUP...
echo ***************************************************************************************

@echo on
PLINK -l %user% -pw %password% -P %port% %host% "%startCli%"
PLINK -l %user% -pw %password% -P %port% %host% -t "%tailLogCli%"
@echo off
