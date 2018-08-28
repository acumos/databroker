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

package org.acumos.databroker.zipbroker.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.acumos.databroker.zipbroker.model.DataSourceModel;
import org.acumos.databroker.zipbroker.service.DataSourceService;
import org.acumos.databroker.zipbroker.util.EELFLoggerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/datasources")
public class DataSourceController {
	private static final EELFLoggerDelegate log = EELFLoggerDelegate.getLogger(DataSourceController.class);

	@Autowired
	DataSourceService dataSourceService;

	/**
	 * Fetches a list of DataSources.
	 * 
	 * @param request
	 * @return ResponseEntity<Object>
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getDataSourcesList(HttpServletRequest request) {
		log.debug(EELFLoggerDelegate.debugLogger, "In getDataSourcesList method");
		List<DataSourceModel> datasourceList = dataSourceService.getDataSourcesList();
		return new ResponseEntity<>(datasourceList, HttpStatus.OK);
	}

	/**
	 * Fetches a DataSource based on given datasourceKey
	 * 
	 * @param request
	 * @param datasourcekey
	 * @return ResponseEntity<Object>
	 * @throws Exception
	 */
	@RequestMapping(path = "/{datasourcekey}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getDataSource(HttpServletRequest request,
			@PathVariable("datasourcekey") String datasourcekey) {
		log.debug(EELFLoggerDelegate.debugLogger, "In getDataSource method");
		DataSourceModel dataSourcemodel = dataSourceService.getDataSourceDetails(datasourcekey);
		return new ResponseEntity<>(dataSourcemodel, HttpStatus.OK);
	}

	/**
	 * Fetches a DataSource MetaData based on given datasourceKey
	 * 
	 * @param datasourcekey
	 * @param request
	 * @return ResponseEntity<Object>
	 * @throws Exception
	 */
	@RequestMapping(path = "/{datasourcekey}/metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getDataSourceMetaData(HttpServletRequest request,
			@PathVariable("datasourcekey") String datasourcekey) {
		log.debug(EELFLoggerDelegate.debugLogger, "In getDataSourceMetaData method");
		String metaData = dataSourceService.getMetadataContents(datasourcekey);
		return new ResponseEntity<>(metaData, HttpStatus.OK);
	}

	/**
	 * Save a DataSource detail
	 * 
	 * @param request
	 * @param dataSource
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> saveDataSource(HttpServletRequest request, @RequestBody DataSourceModel dataSource) {
		log.debug(EELFLoggerDelegate.debugLogger, "In saveDataSource method");
		dataSourceService.saveDataSourceDetail(dataSource);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update DataSource detail
	 * 
	 * @param request
	 * @param dataSource
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(path = "/{datasourcekey}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> updateDataSource(HttpServletRequest request, @RequestBody DataSourceModel dataSource,
			@PathVariable("datasourcekey") String datasourcekey) {
		log.debug(EELFLoggerDelegate.debugLogger, "In updateDataSource method");
		DataSourceModel dataSourcemodel = dataSourceService.updateDataSourceDetail(datasourcekey, dataSource);
		return new ResponseEntity<>(dataSourcemodel, HttpStatus.OK);
	}

	/**
	 * Fetches a DataSource based on given datasourceKey
	 * 
	 * @param datasourcekey
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(path = "/{datasourcekey}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> deleteDataSource(HttpServletRequest request,
			@PathVariable("datasourcekey") String datasourcekey) {
		log.debug(EELFLoggerDelegate.debugLogger, "In deleteDataSource method");
		dataSourceService.deleteDataSourceDetail(datasourcekey);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Validate datasource connection when datasourcekey passed as an input
	 * parameter
	 * 
	 * @param datasourcekey
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(path = "/{datasourcekey}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> validateDataSourceConnection(HttpServletRequest request,
			@PathVariable("datasourcekey") String datasourcekey) {
		log.debug(EELFLoggerDelegate.debugLogger, "In validateDataSourceConnection method");
		dataSourceService.validateDataSourceConnection(datasourcekey);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Fetches samples of data associated with datasourcekey passed as input
	 *
	 * @param datasourcekey
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(path = "/{datasourcekey}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getDataSourceSamples(HttpServletRequest request,
			@PathVariable("datasourcekey") String datasourcekey) {
		log.debug(EELFLoggerDelegate.debugLogger, "In getDataSourceSamples method");
		InputStream sample = dataSourceService.getDataSourceSamples(datasourcekey);
		return new ResponseEntity<>(sample, HttpStatus.OK);
	}
}
