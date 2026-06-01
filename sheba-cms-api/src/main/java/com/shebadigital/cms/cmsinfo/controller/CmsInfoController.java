/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.cmsinfo.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.cmsinfo.model.request.CmsCreateRequest;
import com.shebadigital.cms.cmsinfo.model.request.CmsUpdateRequest;
import com.shebadigital.cms.cmsinfo.model.request.InstituteApprovalDocUpdateRequest;
import com.shebadigital.cms.cmsinfo.service.CmsInfoService;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;

/**
 *
 * @author riad
 */
@RestController
@RequestMapping("/cms")
public class CmsInfoController {
    
    @Autowired
    private CmsInfoService cmsInfoService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<BaseResponse> createCms(@RequestBody @Valid CmsCreateRequest cmsCreateRequest)  {
        return new ResponseEntity<>(cmsInfoService.saveCmsInfo(cmsCreateRequest), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/info")
    public ResponseEntity<ItemResponse> cmsInfo()  {
        return new ResponseEntity<>(cmsInfoService.cmsInfo(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateCms(@RequestBody @Valid CmsUpdateRequest request)  {
        return new ResponseEntity<>(cmsInfoService.updateCmsInfo(request), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> cmsList()  {
        return new ResponseEntity<>(cmsInfoService.cmsList(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/jump")
    public ResponseEntity<BaseResponse> jumpToCms(@RequestParam Long cmsId)  {
    	BaseResponse baseResponse = cmsInfoService.goToCms(cmsId);
    	return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/institute/approval/file/update")
    public ResponseEntity<BaseResponse> updateCmsApprovalFile(@RequestBody @Valid InstituteApprovalDocUpdateRequest request)  {
        return new ResponseEntity<>(cmsInfoService.updateInstituteApprovalDocumentation(request), HttpStatus.CREATED);
    }
    
}
