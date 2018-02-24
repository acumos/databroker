/*-
 * ===============LICENSE_START=======================================================
 * Acumos
 * ===================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property & Tech Mahindra. All rights reserved.
 * ===================================================================================
 * This Acumos software file is distributed by AT&T and Tech Mahindra
 * under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * This file is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===============LICENSE_END=========================================================
 */

package org.acumos.databroker.zipbroker.model;

public class ZipBrokerConfigDB {
	private JsonRequestMapper jsonRequestMapper;
	private static volatile ZipBrokerConfigDB instance;

	public static ZipBrokerConfigDB getInstance() {
		if (instance == null) { 
			synchronized (ZipBrokerConfigDB.class) {
				if (instance == null) { 
					instance = new ZipBrokerConfigDB();
				}
			}
		}
		return instance;
	}

	public JsonRequestMapper getJsonRequestMapper() {
		return jsonRequestMapper;
	}

	public void setJsonRequestMapper(JsonRequestMapper jsonRequestMapper) {
		this.jsonRequestMapper = jsonRequestMapper;
	}
}
