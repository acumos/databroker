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

import java.util.HashMap;
import java.util.Map;

/**
 * @author RK0057738
 *
 */

public class JsonRequestMapper {
	private Map<String, String> jsonRequestUrl = new HashMap<String, String>();
	private Map<String, String> jsonRequestScript = new HashMap<String, String>();
	private Map<String, String> jsonRequestMapping = new HashMap<String, String>();
	private Map<String, String> jsonRequestPosition = new HashMap<String, String>();
	
	public Map<String, String> getJsonRequestUrl() {
		return jsonRequestUrl;
	}
	public void setJsonRequestUrl(Map<String, String> jsonRequestUrl) {
		this.jsonRequestUrl = jsonRequestUrl;
	}
	public Map<String, String> getJsonRequestScript() {
		return jsonRequestScript;
	}
	public void setJsonRequestScript(Map<String, String> jsonRequestScript) {
		this.jsonRequestScript = jsonRequestScript;
	}
	public Map<String, String> getJsonRequestMapping() {
		return jsonRequestMapping;
	}
	public void setJsonRequestMapping(Map<String, String> jsonRequestMapping) {
		this.jsonRequestMapping = jsonRequestMapping;
	}
	public Map<String, String> getJsonRequestPosition() {
		return jsonRequestPosition;
	}
	public void setJsonRequestPosition(Map<String, String> jsonRequestPosition) {
		this.jsonRequestPosition = jsonRequestPosition;
	}
}
