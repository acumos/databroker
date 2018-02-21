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

import static org.junit.Assert.assertEquals;

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
import org.acumos.databroker.zipbroker.model.ZipReaderResult;
import org.acumos.databroker.zipbroker.service.ZipBrokerFileService;
import org.acumos.databroker.zipbroker.util.EELFLoggerDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author RK0057738
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZipBrokerStartUp.class)
public class ZipBrokerFileController {

	ObjectMapper mapper = new ObjectMapper();
	List<byte[]> byteList = new ArrayList<byte[]>();
	Map<String, String> jsonRequestUrl = null;
	Map<String, String> jsonRequestScript = null;
	Map<String, String> jsonRequestMapping = null;
	Map<String, String> jsonRequestPosition = null;
	List<ZipReaderResult> zipReaderResultList = new ArrayList<ZipReaderResult>();
	private static final EELFLoggerDelegate log = EELFLoggerDelegate.getLogger(ZipBrokerFileController.class);
	@Autowired
	ZipBrokerFileService service;

	@Test
	public void getZipBrokerProcessor() throws Exception {
		log.debug(log.debugLogger, "In getZipBrokerProcessor method");
		String jsonUrl = "{\"url\":\"http://www.imageemotion.org/testImages_artphoto.zip\"}";
		String jsonScript = "{\"pattern\":\"amu*\"}";
		String jsonMapping = "{\"MIME_TYPE\":\"mime_type\",\"CONTENT\":\"content\"}";
		String jsonPosition = "{\"MIME_TYPE\":\"1\", \"CONTENT\":\"2\"}";
		JsonRequestMapper jsonRequestMapper = service.getJsonRequestMapperObject(
				mapper.readValue(jsonUrl, HashMap.class), mapper.readValue(jsonScript, HashMap.class),
				mapper.readValue(jsonMapping, HashMap.class), mapper.readValue(jsonPosition, HashMap.class));
		Map<String, String> jsonRequestUrl = jsonRequestMapper.getJsonRequestUrl();
		Map<String, String> jsonRequestScript = jsonRequestMapper.getJsonRequestScript();
		Map<String, String> jsonRequestMapping = jsonRequestMapper.getJsonRequestMapping();
		Map<String, String> jsonRequestPosition = jsonRequestMapper.getJsonRequestPosition();

		zipReaderResultList = service.getZipFile(jsonRequestUrl.get("url"), jsonRequestScript.get("pattern"),
				jsonRequestMapping.get("MIME_TYPE"), jsonRequestMapping.get("CONTENT"));

		ResultRows resultRows = new ResultRows();
		List<Map<String, Column>> rows = new ArrayList<Map<String, Column>>();
		ProtoRecordGenerator protoRecordGenerator = new ProtoRecordGenerator();
		JsonRequestMapping jsonReqMapping = service.getMimeAndContentFromMapping(jsonRequestMapping);
		JsonRequestPosition jsonReqPosition = service.getMimeAndContentFromPosition(jsonRequestPosition);

		service.generateZipReaderResult(zipReaderResultList, rows, jsonReqMapping.getMimeTypeColumn(),
				jsonReqMapping.getContentColumn(), jsonReqPosition.getMimeTypePosition(),
				jsonReqPosition.getContentPosition());
		resultRows.setRows(rows);
		byteList = protoRecordGenerator.doConvert(resultRows, "zipbrokermsg");
		assertEquals(byteList.get(0).length, 599106);
	}
}
