 .. ===============LICENSE_START=======================================================
 .. Acumos
 .. ===================================================================================
 .. Copyright (C) 2017 AT&T Intellectual Property & Tech Mahindra. All rights reserved.
 .. ===================================================================================
 .. This Acumos software file is distributed by AT&T and Tech Mahindra
 .. under the Apache License, Version 2.0 (the "License");
 .. you may not use this file except in compliance with the License.
 .. You may obtain a copy of the License at
 ..  
 ..      http://www.apache.org/licenses/LICENSE-2.0
 ..  
 .. This file is distributed on an "AS IS" BASIS,
 .. WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 .. See the License for the specific language governing permissions and
 .. limitations under the License.
 .. ===============LICENSE_END=========================================================

=============
Release Notes
=============

ZipBroker is deployed as a jar file to a Maven Nexus repository. The server is deployed as a 
Docker image to a Docker registry.

Version 0.0.1-SNAPSHOT, 09 March 2018
-------------------------------------

* Limit memory use in server JVM to max 512MB
* deliver configDB api - Deployer E6 will call /configDB to set all  env variables.
* deliver pullData api - Model connector will call POST operation /pullData to retrieve the results