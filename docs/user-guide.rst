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

======================
Data Broker User Guide
======================

configDB: Input data url , mimeType and content in format 
  http://localhost:8080/configDB?jsonUrl={"url":"http://www.imageemotion.org/testImages_artphoto.zip"}&jsonMapping={"MIME_TYPE":"mime_type","CONTENT":"content"}&jsonPosition={"MIME_TYPE":"1", "CONTENT":"2"}
pullData: Input data jsonScript using POST method
  http://localhost:8080/pullData
  e.g., Body
  {"pattern":"exci*"}