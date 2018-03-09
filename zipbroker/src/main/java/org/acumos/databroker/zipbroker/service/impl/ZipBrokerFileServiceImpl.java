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

package org.acumos.databroker.zipbroker.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import org.acumos.databroker.commons.Column;
import org.acumos.databroker.zipbroker.model.JsonRequestMapper;
import org.acumos.databroker.zipbroker.model.JsonRequestMapping;
import org.acumos.databroker.zipbroker.model.JsonRequestPosition;
import org.acumos.databroker.zipbroker.model.ZipReaderResult;
import org.acumos.databroker.zipbroker.service.ZipBrokerFileService;
import org.acumos.databroker.zipbroker.util.EELFLoggerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.protobuf.DescriptorProtos;


@Service
public class ZipBrokerFileServiceImpl implements ZipBrokerFileService {
	@Autowired
	private Environment env;

	private static final EELFLoggerDelegate log = EELFLoggerDelegate.getLogger(ZipBrokerFileServiceImpl.class);

	@Override
	public List<ZipReaderResult> getZipFile(String newUrl, String pattern, String mimeType, String content) throws IOException {
		log.debug(EELFLoggerDelegate.debugLogger, "In getZipFile method");
		String filePath = env.getProperty("file.path");
		List<ZipReaderResult> lst = null;
		InputStream in = null;
		try {
			URL url = new URL(newUrl);
			in = url.openStream();
			lst = getUnzipFileFromInputStream(in, new File(filePath), false, pattern, mimeType, content);
		} catch (IOException e) {
			log.error(EELFLoggerDelegate.errorLogger, "getZipFile failed", e);
			throw e;
		} finally {
			if (in != null)
				in.close();
		}
		return lst;
	}

	public List<ZipReaderResult> getUnzipFileFromInputStream(InputStream inputStream, File destination,
			boolean overwrite, String filePattern, String mimeType, String content) {
		log.debug(EELFLoggerDelegate.debugLogger, "In getUnzipFileFromInputStream method");
		List<ZipReaderResult> lst = new ArrayList<ZipReaderResult>();
		try {
			byte[] buf = new byte[1024];
			ZipInputStream zipinputstream = null;
			ZipEntry zipentry = null;
			zipinputstream = new ZipInputStream(inputStream);
			zipentry = zipinputstream.getNextEntry();
			String mType = getMIMEType(zipentry.getName());
			while (zipentry != null) {
				int n;
				FileOutputStream fileoutputstream = null;
				File newFile = new File(destination, zipentry.getName());
				if (zipentry.isDirectory()) {
					newFile.mkdirs();
					zipentry = zipinputstream.getNextEntry();
					continue;
				}
				if (newFile.exists() && overwrite) {
					newFile.delete();
				}
				if (getMIMEType(zipentry.getName()).equals(mimeType)) {
					try {
						fileoutputstream = new FileOutputStream(newFile);
						while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
							fileoutputstream.write(buf, 0, n);
						}
					} catch (FileNotFoundException e) {
						log.error(EELFLoggerDelegate.errorLogger, "getUnzipFileFromInputStream failed:: FileNotFound", e);
						throw new IllegalStateException("Can't unzip input stream", e);
					} finally {
						fileoutputstream.close();
					}
				} else if (Pattern.compile(filePattern.split("'\\*")[0]).matcher(newFile.getName()).lookingAt()) {
					try {
						fileoutputstream = new FileOutputStream(newFile);
						while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
							fileoutputstream.write(buf, 0, n);
						}
						BufferedImage bufferedImage = ImageIO.read(newFile);
						WritableRaster raster = bufferedImage.getRaster();
						DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
						ZipReaderResult zipReaderResult = new ZipReaderResult();
						zipReaderResult.setContent(data.getData());
						zipReaderResult.setMime(mType);
						lst.add(zipReaderResult);
					} catch (FileNotFoundException e) {
						log.error(EELFLoggerDelegate.errorLogger, "getUnzipFileFromInputStream failed:: FileNotFound", e);
						throw new IllegalStateException("Can't unzip input stream", e);
					} finally {
						fileoutputstream.close();
					}
				}
				zipinputstream.closeEntry();
				zipentry = zipinputstream.getNextEntry();
			}
			zipinputstream.close();
		} catch (IOException e) {
			log.error(EELFLoggerDelegate.errorLogger, "getUnzipFileFromInputStream failed", e);
			throw new IllegalStateException("Can't unzip input stream", e);
		}
		return lst;
	}

	private List<ZipReaderResult> ZipReader(String mtype, byte[] buf) {
		log.debug(EELFLoggerDelegate.debugLogger, "In ZipReader method");
		List<ZipReaderResult> list = new ArrayList<ZipReaderResult>();
		ZipReaderResult zipReaderResult = new ZipReaderResult();
		zipReaderResult.setContent(buf);
		zipReaderResult.setMime(mtype);
		list.add(zipReaderResult);
		return list;
	}

	@Override
	public String getMIMEType(String fileName) {
		log.debug(EELFLoggerDelegate.debugLogger, "In getMIMEType method");
		MimetypesFileTypeMap mimetypesFileTypeMap = null;
		String mType = null;
		if (fileName != null) {
			mimetypesFileTypeMap = new MimetypesFileTypeMap();
			mType = mimetypesFileTypeMap.getContentType(fileName);
		}
		return mType;
	}

	@Override
	public void generateZipReaderResult(List<ZipReaderResult> zipReaderResultList, List<Map<String, Column>> rows,
			String mime_type_column, String content_column, Integer mime_type_position, Integer content_position) {
		log.debug(EELFLoggerDelegate.debugLogger, "In generateZipReaderResult metod");
		for (ZipReaderResult result : zipReaderResultList) {
			String mime_type = result.getMime();
			byte[] content = result.getContent();
			Map<String, Column> row = new HashMap<String, Column>();
			row.put(mime_type_column,
					new Column(mime_type, DescriptorProtos.FieldDescriptorProto.Type.TYPE_STRING, mime_type_position));
			row.put(content_column,
					new Column(content, DescriptorProtos.FieldDescriptorProto.Type.TYPE_BYTES, content_position));
			// resultRows.add(row);
			rows.add(row);
		}
	}

	@Override
	public JsonRequestMapping getMimeAndContentFromMapping(Map<String, String> jsonRequestMapping) {
		JsonRequestMapping jsonRequestMappingBean = new JsonRequestMapping();
		for (Map.Entry<String, String> entry : jsonRequestMapping.entrySet()) {
			if (entry.getValue() != null && entry.getValue().equals("mime_type")) {
				jsonRequestMappingBean.setMimeTypeColumn(entry.getKey());
			} else if (entry.getValue() != null && entry.getValue().equals("content")) {
				jsonRequestMappingBean.setContentColumn(entry.getKey());
			}
		}
		return jsonRequestMappingBean;
	}

	@Override
	public JsonRequestPosition getMimeAndContentFromPosition(Map<String, String> jsonRequestPosition) {
		JsonRequestPosition jsonRequestPositionBean = new JsonRequestPosition();
		for (Map.Entry<String, String> entry : jsonRequestPosition.entrySet()) {
			if (entry.getValue() != null && entry.getKey().equals("MIME_TYPE")) {
				jsonRequestPositionBean.setMimeTypePosition(Integer.valueOf(entry.getValue()));
			} else if (entry.getValue() != null && entry.getKey().equals("CONTENT")) {
				jsonRequestPositionBean.setContentPosition(Integer.valueOf(entry.getValue()));
			}
		}
		return jsonRequestPositionBean;
	}

	@Override
	public JsonRequestMapper getJsonRequestMapperObject(Map<String, String> jsonRequestUrl,
			Map<String, String> jsonRequestMapping,
			Map<String, String> jsonRequestPosition) {
		JsonRequestMapper jsonRequestMapper = new JsonRequestMapper();
		jsonRequestMapper.setJsonRequestUrl(jsonRequestUrl);
		jsonRequestMapper.setJsonRequestMapping(jsonRequestMapping);
		jsonRequestMapper.setJsonRequestPosition(jsonRequestPosition);
		return jsonRequestMapper;
	}
}
