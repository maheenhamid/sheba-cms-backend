package com.shebadigital.cms.gallery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.model.request.CmsCreateRequest;
import com.shebadigital.cms.cmsinfo.model.request.CmsUpdateRequest;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.gallery.model.request.GalleryInfoCreateRequest;
import com.shebadigital.cms.gallery.model.request.GalleryInfoUpdateRequest;
import com.shebadigital.cms.gallery.service.GalleryService;

@RestController
@RequestMapping(value = "/gallery/info")
public class GalleryInfoController {
	
	@Autowired
	public GalleryService galleryService;

	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createGallery(@RequestBody @Valid GalleryInfoCreateRequest request)  {
        
		return new ResponseEntity<>(galleryService.saveGalleryInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateGallery(@RequestBody @Valid GalleryInfoUpdateRequest request)  {
        return new ResponseEntity<>(galleryService.updateGalleryInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteGallery(@RequestParam Long galleryId)  {
        return new ResponseEntity<>(galleryService.deleteGalleryInfo(galleryId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> galleryList()  {
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
        return new ResponseEntity<>(galleryService.viewAllGalleryInfos(cmsInfo), HttpStatus.OK);
    }
}
