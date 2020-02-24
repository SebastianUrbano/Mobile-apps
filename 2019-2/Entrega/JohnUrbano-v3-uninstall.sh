#!/bin/sh
echo Elsebas
echo desinstalando jdk y jre
sudo apt-get remove -y default-jdk default-jre
echo desinstalando eclipse
sudo apt-get autoremove -y eclipse
echo desinstalando chrome
sudo dpkg -r -y gooogle-chrome-stable
sudo apt-get remove -y putty wireshark vagrant docker
sudo apt-get remove -y mongodb-org

