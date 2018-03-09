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

package org.acumos.databroker.zipbroker.test.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.acumos.databroker.zipbroker.ZipBrokerStartUp;
import org.acumos.databroker.zipbroker.model.ZipReaderResult;
import org.acumos.databroker.zipbroker.service.ZipBrokerFileService;
import org.acumos.databroker.zipbroker.util.EELFLoggerDelegate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZipBrokerStartUp.class)
public class ZipBrokerServiceImplTest {
	
private static final EELFLoggerDelegate log = EELFLoggerDelegate.getLogger(ZipBrokerServiceImplTest.class);
	
    @Autowired
    ZipBrokerFileService service;
	
	@Test
	public void getZipFileFromURL() throws Exception {
		log.debug(log.debugLogger,"In getZipFileFromURL");
		byte[] content=null;
		List<ZipReaderResult> zipReaderResultList=service.getZipFile("http://www.imageemotion.org/testImages_artphoto.zip", "amu*", "MIME_TYPE", "CONTENT");
		
		//test number of file return for requested pattern
		assertEquals(zipReaderResultList.size(), 101);
		
		// test size of file 
		for(ZipReaderResult result :zipReaderResultList) {            	
			String mime_type= result.getMime();
			content = result.getContent();            	
 		}
		//test last file size
		assertEquals(content.length, 599508);
	}
}
