package com.shebadigital.cms.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


@Service
public class CacheEvictService {

	private static final Logger logger = LoggerFactory.getLogger(CacheEvictService.class);
	
	@CacheEvict(value="show-file", key = "#folder+#filename")
	public void cacheEvictShowFile(String folder,String filename){
		logger.info(" Cache Evicted of ShowFile= "+folder+" "+filename);
		
	}
	
	@CacheEvict(value="show-pdf-file", key = "#folder+#filename")
	public void cacheEvictShowPdfFile(String folder,String filename){
		logger.info(" Cache Evicted of ShowPdfFile= "+folder+" "+filename);
		
	}
	
	@CacheEvict(value="show-image-file", key = "#folder+#filename")
	public void cacheEvictShowImageFile(String folder,String filename){
		logger.info(" Cache Evicted of ShowImageFile= "+folder+" "+filename);
		
	}
}
