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

===========================
Data Broker Developer Guide
===========================

Data broker exposed two API configDB and pullData.

configDB: call by Deployer E6
 * Zipbroker received url ,mimeType and content (JSON format) and set environment variable.

pullData: call by Model connector with script(JSON string) to retrieve data.
 * Retrieved input stream and unzip it
 * Iterate on stream as per pattern(script)
 * Used protobuf api and convert unzipped filtered input stream to byte array
 * Send response(byte array) to pullData