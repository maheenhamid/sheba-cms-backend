package com.shebadigital.cms.gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.gallery.service.GalleryService;

@RestController
@RequestMapping(value = "/public/gallery/info")
public class PublicGalleryInfoController {
	
	@Autowired
	public GalleryService galleryService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;

	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> galleryList(@RequestParam Long cmsId, @RequestParam String type)  {
        return new ResponseEntity<>(galleryService.viewAllGalleryInfos(cmsId, type), HttpStatus.OK);
    }
	
	
	@GetMapping(value = "/list/all")
    public ResponseEntity<ItemResponse> galleryList(@RequestParam Long cmsId)  {
		
		ItemResponse itemResponse=new ItemResponse<>();
		
		CmsInfo cmsInfo =cmsInfoRepository.findByCmsId(cmsId);
		
        return new ResponseEntity<>(galleryService.viewAllGalleryInfos(cmsInfo), HttpStatus.OK);
    }
}
