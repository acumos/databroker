/*-
 * ===============LICENSE_START=======================================================
 * Acumos
 * ===================================================================================
 * Copyright (C) 2018 AT&T Intellectual Property. All rights reserved.
 * ===================================================================================
 * This Acumos software file is distributed by AT&T
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

import java.io.Serializable;

public class DataSourceModel implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String category;
	protected String namespace;
	protected String datasourceName;
	protected String datasourceDescription;
	protected String readWriteDescriptor;
	protected String predictorKey;
	protected boolean isDataReference;

	public DataSourceModel() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}

	public String getDatasourceDescription() {
		return datasourceDescription;
	}

	public void setDatasourceDescription(String datasourceDescription) {
		this.datasourceDescription = datasourceDescription;
	}

	public String getReadWriteDescriptor() {
		return readWriteDescriptor;
	}

	public void setReadWriteDescriptor(String readWriteDescriptor) {
		this.readWriteDescriptor = readWriteDescriptor;
	}

	public String getPredictorKey() {
		return predictorKey;
	}

	public void setPredictorKey(String predictorKey) {
		this.predictorKey = predictorKey;
	}

	public boolean isDataReference() {
		return isDataReference;
	}

	public void setDataReference(boolean isDataReference) {
		this.isDataReference = isDataReference;
	}
}
