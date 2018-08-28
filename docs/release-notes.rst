.. ===============LICENSE_START=======================================================
.. Acumos CC-BY-4.0
.. ===================================================================================
.. Copyright (C) 2017-2018 AT&T Intellectual Property & Tech Mahindra. All rights reserved.
.. ===================================================================================
.. This Acumos documentation file is distributed by AT&T and Tech Mahindra
.. under the Creative Commons Attribution 4.0 International License (the "License");
.. you may not use this file except in compliance with the License.
.. You may obtain a copy of the License at
..
.. http://creativecommons.org/licenses/by/4.0
..
.. This file is distributed on an "AS IS" BASIS,
.. WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
.. See the License for the specific language governing permissions and
.. limitations under the License.
.. ===============LICENSE_END=========================================================

=========================
Data Broker Release Notes
=========================

Version 0.0.2-SNAPSHOT, 04 September 2018
=====================================

* Added new API endpoint to get Datasource info, right now it is just prototype (ACUMOS-1182)


Version 0.0.1-SNAPSHOT, 09 March 2018
=====================================

* Limit memory use in server JVM to max 512MB
* deliver configDB api - Deployer E6 will call /configDB to set all  env variables.
* deliver pullData api - Model connector will call POST operation /pullData to retrieve the results
