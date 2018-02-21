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

package org.acumos.databroker.commons;

/**
 * Publishes constants for the Data Broker.
 */
public abstract class DataBrokerConstants {

	// Seems like this should available somewhere
	public static final String APPLICATION_JSON = "application/json";
	// Entities
	public static final String DATABROKER_PATH = "databroker";
	
	public static final String RETRIEVE_PATH = "retrieve";

	// Sonar wants a private constructor, placed after all fields
	private DataBrokerConstants() {
	}

}
