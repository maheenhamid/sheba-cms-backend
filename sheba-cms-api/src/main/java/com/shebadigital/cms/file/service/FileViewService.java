package com.shebadigital.cms.file.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import com.shebadigital.cms.common.ApplicationUtils;

@Service
public class FileViewService {
	
	public Logger logger = LoggerFactory.getLogger(FileViewService.class);
	
	public Resource showFileResource(String folder, String filename) {

		Resource resource = null;

		String filepath = ApplicationUtils.filePath(folder)+""+filename;
		//logger.info("filepath "+filepath);

		try {
			
			Path path = new File(filepath).toPath();
			resource = new UrlResource(path.toUri());

		}

		catch (MalformedURLException e) {
			logger.error(e.getLocalizedMessage());
		}

		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return resource;
	}

}
