echo off
mysql -bu root -p -e"source start.sql"
cd bin
java rmi.BrickCityBankClient
echo on