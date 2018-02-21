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

import java.io.Serializable;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;

/**
 * Model for Column
 * Contains columnValue,Position, ColumnType.
 */
public class Column implements Serializable {

	private static final long serialVersionUID = -1505595448809901709L;

	private Object columnValue;
	
	private Integer position;

	private DescriptorProtos.FieldDescriptorProto.Type columnType;

	public Object getColumnValue() {
		return columnValue;
	}
	/**
	 * @param columnValue
	 *            Value of Column
	 */
	public void setColumnValue(Object columnValue) {
		this.columnValue = columnValue;
	}

	/**
	 * @return DescriptorProtos.FieldDescriptorProto.Type
	 */
	public DescriptorProtos.FieldDescriptorProto.Type getColumnType() {
		return columnType;
	}

	/**
	 * @param columnType
	 *           Value of Column Type
	 */
	public void setColumnType(DescriptorProtos.FieldDescriptorProto.Type columnType) {
		this.columnType = columnType;
	}

	public Column(Object columnValue, Type columnType) {
		this.columnValue = columnValue;
		this.columnType = columnType;
	}
	
	public Column(Object columnValue, Type columnType,Integer position) {
		this.columnValue = columnValue;
		this.columnType = columnType;
		this.position = position;
	}

	/**
	 * @return Integer position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position
	 *         Position of Column
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}	
}