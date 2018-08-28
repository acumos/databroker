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

package org.acumos.databroker.zipbroker.service;

import java.io.InputStream;
import java.util.List;

import org.acumos.databroker.zipbroker.model.DataSourceModel;

public interface DataSourceService {
	
	public List<DataSourceModel> getDataSourcesList();

	
	public DataSourceModel getDataSourceDetails(String dataSourceKey);

	
	public boolean deleteDataSourceDetail(String datasourceKey);

	
	public boolean saveDataSourceDetail(DataSourceModel dataSource);

	
	public DataSourceModel updateDataSourceDetail(String datasourceKey, DataSourceModel dataSource);

	

	public boolean validateDataSourceConnection(String dataSourceKey);

	
	public String getMetadataContents(String dataSourceKey);

	
	public InputStream getDataSourceSamples(String dataSourceKey);

}
