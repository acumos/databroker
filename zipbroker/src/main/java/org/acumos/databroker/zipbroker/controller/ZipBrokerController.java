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

package org.acumos.databroker.zipbroker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acumos.databroker.commons.Column;
import org.acumos.databroker.commons.ProtoRecordGenerator;
import org.acumos.databroker.commons.ResultRows;
import org.acumos.databroker.zipbroker.model.JsonRequestMapper;
import org.acumos.databroker.zipbroker.model.JsonRequestMapping;
import org.acumos.databroker.zipbroker.model.JsonRequestPosition;
import org.acumos.databroker.zipbroker.model.JsonRequestScript;
import org.acumos.databroker.zipbroker.model.ZipBrokerConfigDB;
import org.acumos.databroker.zipbroker.model.ZipReaderResult;
import org.acumos.databroker.zipbroker.service.ZipBrokerFileService;
import org.acumos.databroker.zipbroker.util.EELFLoggerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping(path="/")
public class ZipBrokerController {
		
	@Autowired
	ZipBrokerFileService zipBrokerFileService;
	
	private static final EELFLoggerDelegate log = EELFLoggerDelegate.getLogger(ZipBrokerController.class);

	@RequestMapping(path = "/configDB", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> configureEnvironment(@RequestParam(value = "jsonUrl") String jsonUrl,
			@RequestParam(value = "jsonMapping") String jsonMapping,
			@RequestParam(value = "jsonPosition") String jsonPosition) {
		log.debug(EELFLoggerDelegate.debugLogger, "In configureEnvironment method");
		ObjectMapper mapper = new ObjectMapper();
		List<byte[]> byteList = new ArrayList<byte[]>();
		try {
			ZipBrokerConfigDB zipBrokerConfigDB = ZipBrokerConfigDB.getInstance();
			JsonRequestMapper jsonRequestMapper = zipBrokerFileService.getJsonRequestMapperObject(
					mapper.readValue(jsonUrl, HashMap.class), 
					mapper.readValue(jsonMapping, HashMap.class), 
					mapper.readValue(jsonPosition, HashMap.class));
			zipBrokerConfigDB.setJsonRequestMapper(jsonRequestMapper);
		} catch (Exception e) {
			log.error(EELFLoggerDelegate.errorLogger, "IO exception occoured while retrieving file from stream", e);
		}
		return new ResponseEntity<>(byteList, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(path = "/pullData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getData(@RequestBody JsonRequestScript jsonScript) {
		log.debug(EELFLoggerDelegate.debugLogger, "In getData method");
		List<byte[]> byteList = new ArrayList<byte[]>();
		List<ZipReaderResult> zipReaderResultList = new ArrayList<ZipReaderResult>();
		try {
			ZipBrokerConfigDB zipBrokerConfigDB = ZipBrokerConfigDB.getInstance();
			JsonRequestMapper jsonRequestMapper = zipBrokerConfigDB.getJsonRequestMapper();
			Map<String, String> jsonRequestUrl = jsonRequestMapper.getJsonRequestUrl();
			Map<String, String> jsonReqScript = new HashMap<String, String>();
			jsonReqScript.put("pattern", jsonScript.getPattern());
			Map<String, String> jsonRequestMapping = jsonRequestMapper.getJsonRequestMapping();
			Map<String, String> jsonRequestPosition = jsonRequestMapper.getJsonRequestPosition();

			if (jsonRequestUrl.get("url") != null || !jsonRequestUrl.get("url").isEmpty()) {
				zipReaderResultList = zipBrokerFileService.getZipFile(jsonRequestUrl.get("url"),
						jsonReqScript.get("pattern"), jsonRequestMapping.get("MIME_TYPE"),
						jsonRequestMapping.get("CONTENT"));
			} else {
				throw new IllegalArgumentException("The 'url' parameter must not be null or empty");
			}

			ResultRows resultRows = new ResultRows();
			List<Map<String, Column>> rows = new ArrayList<Map<String, Column>>();
			ProtoRecordGenerator protoRecordGenerator = new ProtoRecordGenerator();
			JsonRequestMapping jsonReqMapping = zipBrokerFileService.getMimeAndContentFromMapping(jsonRequestMapping);
			JsonRequestPosition jsonReqPosition = zipBrokerFileService
					.getMimeAndContentFromPosition(jsonRequestPosition);

			zipBrokerFileService.generateZipReaderResult(zipReaderResultList, rows, jsonReqMapping.getMimeTypeColumn(),
					jsonReqMapping.getContentColumn(), jsonReqPosition.getMimeTypePosition(),
					jsonReqPosition.getContentPosition());
			resultRows.setRows(rows);
			byteList = protoRecordGenerator.doConvert(resultRows, "zipbrokermsg");
		} catch (Exception e) {
			log.error(EELFLoggerDelegate.errorLogger, "IO exception occoured while retrieving file from stream", e);
		}
		if (byteList != null) {
			return new ResponseEntity<>(byteList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}