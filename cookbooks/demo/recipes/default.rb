execute "apt-get update"
package "python-software-properties"
execute "sudo add-apt-repository ppa:webupd8team/java"
execute "apt-get update"
execute "sudo echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections"
package "oracle-java7-installer"
package "oracle-java7-set-default"
package "maven"
package "git"
cookbook_file "/etc/motd" do
  action :create
end