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

package org.acumos.databroker.zipbroker.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Converts a JSON String to  (Key,Value) pair.
 */
public class JsonProcessor<K, V> {

	private static final EELFLoggerDelegate log = EELFLoggerDelegate.getLogger(JsonProcessor.class);


	/**
	 * @param jsonString value of String
	 * @return map
	 * @throws IOException ioexception
	 */
	 
	public Map<K, V> deserialize(String jsonString) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<K, V> map = new HashMap<K, V>();
		try {
			TypeReference<HashMap<K, V>> typeRef = new TypeReference<HashMap<K, V>>() {
			};
			map = mapper.readValue(jsonString, typeRef);

		} catch (IOException e) {
			log.error(EELFLoggerDelegate.errorLogger,e.getMessage(), e);
			throw e;
		}
		return map;
	}
	
}
