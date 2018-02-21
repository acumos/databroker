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

package org.acumos.databroker.commons.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acumos.databroker.commons.Column;
import org.acumos.databroker.commons.ProtoRecordGenerator;
import org.acumos.databroker.commons.ResultRows;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.DescriptorProtos;

/**
 * Tests ProtoRecordGenerator
 */
public class ProtoRecordGeneratorTest {

	private static Logger logger = LoggerFactory.getLogger(ProtoRecordGeneratorTest.class);

	@Test
	public void testProtoRecordGenerator() throws Exception {
		try {
			
			ResultRows resultRows = new ResultRows();
			
			List<Map<String, Column>> rows = new ArrayList<Map<String, Column>>();
			ProtoRecordGenerator protoRecordGenerator = new ProtoRecordGenerator();
			Map<String, Column> row = new HashMap<String, Column>(3);
			row.put("file_name", new Column("image_mood_class",DescriptorProtos.FieldDescriptorProto.Type.TYPE_STRING,2));
			row.put("content", new Column("Image Content",DescriptorProtos.FieldDescriptorProto.Type.TYPE_STRING,1));
			row.put("mime_type", new Column("jpeg",DescriptorProtos.FieldDescriptorProto.Type.TYPE_STRING,3));
			rows.add(row);
			resultRows.setRows(rows);
			protoRecordGenerator.doConvert(resultRows, "test");
			
		} catch (Exception ex) {
			logger.error("testProtoRecordGenerator failed: ", ex);
			throw ex;
		}
	}
}
