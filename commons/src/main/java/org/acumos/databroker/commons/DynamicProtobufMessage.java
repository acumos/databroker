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

import java.util.HashMap;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;

/**
 * Creates a protobuf message without the .proto file dynamically.
 */
public class DynamicProtobufMessage {
	
	private HashMap<String, Object> values = new HashMap<String, Object>();
	private DescriptorProtos.DescriptorProto.Builder desBuilder;

	public DynamicProtobufMessage() {
		desBuilder = DescriptorProtos.DescriptorProto.newBuilder();
	}

	
	public <T> void addField(String fieldName, T fieldValue, DescriptorProtos.FieldDescriptorProto.Type type,
			Integer i) {
		
		//System.out.println("fieldName=" + fieldName + " fieldValue=" + fieldValue + " type="+ type);
		/*DescriptorProtos.FieldDescriptorProto.Builder fd1Builder = DescriptorProtos.FieldDescriptorProto.newBuilder()
				.setName(fieldName).setNumber(i++).setType(type); */
		DescriptorProtos.FieldDescriptorProto.Builder fd1Builder = DescriptorProtos.FieldDescriptorProto.newBuilder()
				.setName(fieldName).setNumber(i).setType(type);
		desBuilder.addField(fd1Builder.build());
		values.put(fieldName, fieldValue);
	}

	/**
	 * @param messageName
	 *          The Name of the Message
	 * @return  Message
	 * @throws Descriptors.DescriptorValidationException description exception
	 */
	public Message constructMessage(String messageName) throws Descriptors.DescriptorValidationException {
		desBuilder.setName(messageName);
		DescriptorProtos.DescriptorProto dsc = desBuilder.build();

		DescriptorProtos.FileDescriptorProto fileDescP = DescriptorProtos.FileDescriptorProto.newBuilder()
				.addMessageType(dsc).build();

		Descriptors.FileDescriptor[] fileDescs = new Descriptors.FileDescriptor[0];
		Descriptors.FileDescriptor dynamicDescriptor = Descriptors.FileDescriptor.buildFrom(fileDescP, fileDescs);
		Descriptors.Descriptor msgDescriptor = dynamicDescriptor.findMessageTypeByName(messageName);
		DynamicMessage.Builder dmBuilder = DynamicMessage.newBuilder(msgDescriptor);
		try {
		for (String name : values.keySet()) {
			//System.out.println("name=" + name + " msgDescriptor.findFieldByName(name)=" + msgDescriptor.findFieldByName(name) + " values.get(name)="+ values.get(name));
			dmBuilder.setField(msgDescriptor.findFieldByName(name), values.get(name));
		}
		} catch(Exception exc){
			//exc.printStackTrace();
			throw exc;
		}
		return dmBuilder.build();
	}

	public void clear() {
		desBuilder = DescriptorProtos.DescriptorProto.newBuilder();
		values.clear();
	}	
}