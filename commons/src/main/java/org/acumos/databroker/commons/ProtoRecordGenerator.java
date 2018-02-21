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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Message;

/**
 * ProtoRecordGenerator is entry point for Generating Generic Protobuf Message.
 */
public class ProtoRecordGenerator  {
	
	private static Logger logger = LoggerFactory.getLogger(ProtoRecordGenerator.class);
	
	/**
	 * @param resultRows
	 *         ResultRows
	 * @param msg
	 *         Message Name
	 * @return
	 *         List of Byte[]
	 * @throws Exception exception
	 */
	public List<byte[]> doConvert(ResultRows resultRows,String msg) throws Exception {	
	  logger.info("doConvert");
	  List<byte[]> byteArrayList = new ArrayList<byte[]>();
	  List<Map<String, Column>> rows = resultRows.getRows();
	  for(Map<String, Column> row: rows) {
		DynamicProtobufMessage dynamicProtobufMessage = new DynamicProtobufMessage();
		for (Entry<String, Column> entry : row.entrySet()) {
			 String columnName = entry.getKey();
			 Column column = entry.getValue();
			 DescriptorProtos.FieldDescriptorProto.Type type = column.getColumnType();
			 dynamicProtobufMessage.addField(columnName , column.getColumnValue(),type,column.getPosition());						
		}
		Message m = dynamicProtobufMessage.constructMessage(msg);
		logger.debug("message=" + m.toString());
		byteArrayList.add(m.toByteArray());
	   }
	   return null;
	}	
}
