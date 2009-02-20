@echo off
echo ==================================================
echo             Brick City Bank Launcher
echo ==================================================
echo -
echo You will need to enter your root MySQL password once during this process.
echo Starting rmiregistry...
start rmiregistry
echo Done!
echo -
echo 3) Starting the BrickCityBank Server...
appclient -client bin\Server.jar
echo Done!
echo -
pause