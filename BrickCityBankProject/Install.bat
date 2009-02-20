@echo off
echo ==================================================
echo Welcome to the BrickCityBank Installation process!
echo ==================================================
echo -
echo You will need to enter your root MySQL password 2 times during this installation.
echo - 
echo 1) Creating BrickCityBank MySQL database...
mysql -bu root -p -e"source start.sql"
echo Done!
echo - 
echo 2) Populating BrickCityBank MySQL database...
mysql -bu root -p -e"source brickcitybank.sql"
echo Done!
echo -
echo If you don't see any ERRORs above, then
echo The BrickCityBank server has been installed successfully!
echo -
pause