#!/bin/bash

apt-get install apache2 subversion libapache2-svn -y

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



