#!/bin/sh
echo mi primer script jeje
sudo apt-get update
sudo apt-get upgrade
sudo apt update
sudo apt-get update && time sudo apt-get dist-upgrade
sudo apt-get install snap
sudo apt-get install wget
echo Instalando java versión estable
sudo apt install -y default-jre default-jdk
echo Instalando eclipse
sudo snap install --classic eclipse
echo Instalado Git
sudo apt-get install git
echo Instalando Chrome
wget c https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo apt-get install -y libappindicator1
sudo dpkg -i google-chrome-stable_current_amd64.deb
cd -
echo Instalando software generico putty, wireshark, vagrant y docker
export DEBIAN_FRONTEND=noninteractive
sudo apt-get install -y putty wireshark vagrant docker
echo Instalando Mongobd
sudo apt install -y mongodb-org
echo ya pasoooooooooooooooooooo
echo Se queda debiendo packet tracer
echo Creación de usuario
sudo useradd -m -d /home/sebas sebas -s /bin/bash
sudo adduser sebas sudo
echo Todo lo que pedían fue totalmente instalado :D

