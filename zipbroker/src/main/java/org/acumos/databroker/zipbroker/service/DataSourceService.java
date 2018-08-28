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
	/**
	 * fetches a list of datasources
	 * 
	 * @return List<DataSourceModel>
	 */
	public List<DataSourceModel> getDataSourcesList();

	/**
	 * fetches a list of datasources
	 * 
	 * @param datasourceKey
	 * @return
	 */
	public DataSourceModel getDataSourceDetails(String dataSourceKey);

	/**
	 * deletes a registered datasource based on key
	 * 
	 * @param datasourceKey
	 * @return
	 */
	public boolean deleteDataSourceDetail(String datasourceKey);

	/**
	 * registers a new datasource
	 * 
	 * @param dataSource
	 * @return
	 */
	public boolean saveDataSourceDetail(DataSourceModel dataSource);

	/**
	 * updates a datasources for given datasource key
	 * 
	 * @param datasourceKey
	 * @param dataSource
	 * @return
	 */
	public DataSourceModel updateDataSourceDetail(String datasourceKey, DataSourceModel dataSource);

	/**
	 * Validate datasource connection when datasourcekey passed as an input
	 * parameter
	 * 
	 * @param dataSourceKey
	 * @return
	 */

	public boolean validateDataSourceConnection(String dataSourceKey);

	/**
	 * returns the metadata from the database associated with the datasource key
	 * 
	 * @param dataSourceKey
	 * @return
	 */
	public String getMetadataContents(String dataSourceKey);

	/**
	 * returns a sample inputstream for given datasource key
	 * 
	 * @param dataSourceKey
	 * @return
	 */
	public InputStream getDataSourceSamples(String dataSourceKey);

}
