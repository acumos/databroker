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

package org.acumos.databroker.zipbroker.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.acumos.databroker.commons.Column;
import org.acumos.databroker.zipbroker.model.JsonRequestMapper;
import org.acumos.databroker.zipbroker.model.JsonRequestMapping;
import org.acumos.databroker.zipbroker.model.JsonRequestPosition;
import org.acumos.databroker.zipbroker.model.ZipReaderResult;


public interface ZipBrokerFileService {
	public List<ZipReaderResult> getZipFile(String newUrl, String pattern, String mimeType, String content);

	public List<ZipReaderResult> getUnzipFileFromInputStream(InputStream inputStream, File destination,
			boolean overwrite, String filePattern, String mimeType, String content);

	public JsonRequestMapping getMimeAndContentFromMapping(Map<String, String> jsonRequestMapping);

	public JsonRequestPosition getMimeAndContentFromPosition(Map<String, String> jsonRequestPosition);

	public JsonRequestMapper getJsonRequestMapperObject(Map<String, String> jsonRequestUrl,
			Map<String, String> jsonRequestScript, Map<String, String> jsonRequestMapping,
			Map<String, String> jsonRequestPosition);

	public String getMIMEType(String fileName);

	public void generateZipReaderResult(List<ZipReaderResult> zipReaderResultList, List<Map<String, Column>> rows,
			String mime_type_column, String content_column, Integer mime_type_position, Integer content_position);
}
