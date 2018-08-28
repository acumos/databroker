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

package org.acumos.databroker.zipbroker.service.impl;

import java.io.InputStream;
import java.util.List;

import org.acumos.databroker.zipbroker.model.DataSourceModel;
import org.acumos.databroker.zipbroker.service.DataSourceService;

public class DataSourceServiceImpl implements DataSourceService {

	@Override
	public List<DataSourceModel> getDataSourcesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSourceModel getDataSourceDetails(String dataSourceKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDataSourceDetail(String datasourceKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveDataSourceDetail(DataSourceModel dataSource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataSourceModel updateDataSourceDetail(String datasourceKey, DataSourceModel dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateDataSourceConnection(String dataSourceKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMetadataContents(String dataSourceKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getDataSourceSamples(String dataSourceKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
