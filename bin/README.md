Bus Spider SJCampos [![Stories in Ready](https://badge.waffle.io/waffleio/waffle.io.png)](https://waffle.io/transparenciasjc/spider_bus_java)
===============

# Motivations

The goal of the project is to provide an REST api of schedules and itineraries of buses from São José dos Campos.

The online version of the service is hosted in [OpenShift](https://www.openshift.com/), and can accessed by the following link: ['http://spiderbus-pedrohos.rhcloud.com/api/buscar/{numero}'](http://spiderbus-pedrohos.rhcloud.com/api/buscar/200), where {number} is the line number that you search.

# Platforms and Frameworks

The application was made using these platforms:

* [Java](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html);
* [RestEasy](https://www.jboss.org/resteasy);
* [JSoup](http://jsoup.org/)

# How to Run

Install [Maven](http://maven.apache.org/) and [Eclipse](http://www.eclipse.org/downloads/)

1 - Clone this repository:

	git clone https://github.com/transparenciasjc/spider_bus_java.git

cd to project folder: 
	
	cd spider_bus_java

run maven to download the dependencies:

	mvn clean install

finally, Import Project into eclipse and select "Run on server" or "Debug on server" to run the server on Web server, like [Tomcat](http://tomcat.apache.org) and [Jboss](http://www.jboss.org/).
