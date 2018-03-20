Release Notes
-------------

ZipBroker is deployed as a jar file to a Maven Nexus repository. The server is deployed as a 
Docker image to a Docker registry.

Version 0.0.1-SNAPSHOT, 09 March 2018
-------------------------------------

* Limit memory use in server JVM to max 512MB
* deliver configDB api - Deployer E6 will call /configDB to set all  env variables.
* deliver pullData api - Model connector will call POST operation /pullData to retrieve the results