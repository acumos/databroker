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

import org.acumos.databroker.commons.DynamicProtobufMessage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Message;

/**
 * Tests ProtoRecordGenerator
 */
public class DynamicProtobufMessageTest {

	private static Logger logger = LoggerFactory.getLogger(DynamicProtobufMessageTest.class);

	@Test
	public void testProtoRecordGenerator() throws Exception {
		try {
			
			DynamicProtobufMessage pe = new DynamicProtobufMessage();
		    int i = 1;
		    for (; i < 5; i++) {
		      pe.<String>addField("Field" + i, i * 1000 + "FOO", DescriptorProtos.FieldDescriptorProto.Type.TYPE_STRING,i);
		    }
		    for (; i < 10; i++) {
		      pe.<Integer>addField("Field" + i, i * 1000, DescriptorProtos.FieldDescriptorProto.Type.TYPE_INT32,i);
		    }
		    Message m = pe.constructMessage("TestMessage");
		    logger.debug("Message=" + m);
			
		} catch (Exception ex) {
			logger.error("testProtoRecordGenerator failed: ", ex);
			throw ex;
		}
	}
}
