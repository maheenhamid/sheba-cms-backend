package com.shebadigital.cms.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ApplicationUtils;
import com.shebadigital.cms.common.CacheEvictService;
import com.shebadigital.cms.file.service.FileViewService;




@RestController
@RequestMapping(value = "/public/file")
public class PublicFileViewerController {
	

	@Autowired
	public FileViewService fileViewService;
	
	@Autowired
	public CacheEvictService cacheEvictService;
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private static final Logger logger = LoggerFactory.getLogger(PublicFileViewerController.class);
	
	
	
	@Cacheable(value="show-file", key = "#folder+#filename")
	@GetMapping("/view/{folder}/{filename}")
    public ResponseEntity<Resource> showFile(@PathVariable String folder,@PathVariable String filename) {
		
	 logger.info("/public/file/view/"+folder+"/"+filename);
     Resource resource = fileViewService.showFileResource(folder, filename);
     
     scheduler.schedule(() -> {cacheEvictService.cacheEvictShowFile(folder, filename);}, 10, TimeUnit.MINUTES);
      
      return ResponseEntity.ok()
             .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"")
             .body(resource);
  }
	
	
	//@Cacheable(value="show-pdf-file", key = "#folder+#filename")
	@GetMapping(value = "/pdf/view/{folder}/{filename}")
    public ResponseEntity<InputStreamResource> pdfFileViewer(@PathVariable String folder,@PathVariable String filename) throws FileNotFoundException {

		
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.add("content-disposition", "inline;filename=" +filename);
        
        String filepath = ApplicationUtils.filePath(folder)+""+filename;
        
        logger.info("/public/file/pdf/view/"+folder+"/"+filename);
        
        File file = new File(filepath);
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
     //   scheduler.schedule(() -> {cacheEvictService.cacheEvictShowPdfFile(folder, filename);}, 10, TimeUnit.MINUTES);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
	
	
	//@Cacheable(value="show-image-file", key = "#folder+#filename")
	@GetMapping(value = "/image/view/{folder}/{filename}")
    public ResponseEntity<InputStreamResource> imageFileViewer(@PathVariable String folder,@PathVariable String filename) throws FileNotFoundException {

        HttpHeaders headers = new HttpHeaders(); 
        
        headers.add("content-disposition", "inline;filename=" +filename);
        
        String filepath = ApplicationUtils.filePath(folder)+""+filename;
        
        logger.info("/public/file/image/view/"+folder+"/"+filename);
        
        File file = new File(filepath);
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
      //  scheduler.schedule(() -> {cacheEvictService.cacheEvictShowImageFile(folder, filename);}, 10, TimeUnit.MINUTES);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("image/png"))
                .body(resource);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("/view/{folder}/{filename}")
//    public ResponseEntity<Resource> showFile(@PathVariable String filename,@PathVariable String folder) {
//
//        Resource resource = null;
//        System.out.println("filename   "+filename);
//        System.out.println("folderName "+folder);
//        String link="D:\\images\\openspace\\institute-image\\";
//
//        try {
//            Path file = new File(link+""+filename).toPath();
//            resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                
//            }
//            
//        }
//        
//        catch (MalformedURLException e) {
//            
//        }
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }

}
