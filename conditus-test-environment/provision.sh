#!/bin/bash

apt-get update
# Subversion Installation
apt-get install apache2 subversion libapache2-svn unzip -y

mkdir -p /var/local/svn/conditus 
svnadmin create --fs-type fsfs /var/local/svn/conditus

chown -R www-data:www-data /var/local/svn

mv /etc/apache2/mods-available/dav_svn.conf /etc/apache2/mods-available/dav_svn.conf.bak
touch /etc/apache2/mods-available/dav_svn.conf

echo '<Location /svn>
       DAV svn
       SVNParentPath /var/local/svn
       SVNListParentPath on 
     </Location>' >> /etc/apache2/mods-available/dav_svn.conf
     
service apache2 restart

svnadmin load /var/local/svn/conditus < /vagrant/conditus-test-repo.dump

chmod -R a+rwx /var/local/svn

# Java Installation
apt-get install openjdk-7-jdk -y

# Sonatype Nexus Installation
cd /home/vagrant
mkdir -p nexus
wget http://www.sonatype.org/downloads/nexus-latest-bundle.zip
mv nexus-latest-bundle.zip nexus/.
cd nexus
unzip nexus-latest-bundle.zip
rm -f *.zip
cd ..
cp /vagrant/init.d/nexus /etc/init.d/.
chmod 755 /etc/init.d/nexus
cd /etc/init.d/
update-rc.d nexus defaults
cd /home/vagrant

# Update System Environment Variable
touch /etc/profile.d/sysenv.sh

echo 'export NEXUS_HOME=/home/vagrant/nexus/nexus-2.10.0-02' >> /etc/profile.d/sysenv.sh
echo 'export M2_HOME=/home/vagrant/apache-maven-3.2.1' >> /etc/profile.d/sysenv.sh
echo 'export SONAR_HOME=/home/vagrant/sonarqube/sonarqube-4.3' >>  /etc/profile.d/sysenv.sh
echo 'export RUN_AS_USER=vagrant' >> /etc/profile.d/sysenv.sh
echo 'export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/' >> /etc/profile.d/sysenv.sh

source /etc/profile.d/sysenv.sh

# Update Ownership
chown -R vagrant:vagrant *
chmod a+x -R *

# Start Applications
service nexus start

