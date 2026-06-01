package com.shebadigital.cms.cmsinfo.controller;

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

import com.shebadigital.cms.cmsinfo.model.request.AboutUsInfosRequest;
import com.shebadigital.cms.cmsinfo.model.request.AboutUsInfosUpdateRequest;
import com.shebadigital.cms.cmsinfo.service.AboutUsInfoService;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;

@RestController
@RequestMapping(value = "/aboutus/info")
public class AboutUsInfoController {
	
	@Autowired
	public AboutUsInfoService aboutUsInfoService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createAboutUsInfo(@RequestBody @Valid AboutUsInfosRequest request)  {
        
		return new ResponseEntity<>(aboutUsInfoService.saveAboutUsInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateAboutUsInfo(@RequestBody @Valid AboutUsInfosUpdateRequest request)  {
        return new ResponseEntity<>(aboutUsInfoService.updateAboutUsInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteAboutUsInfo(@RequestParam Long aboutUsId)  {
        return new ResponseEntity<>(aboutUsInfoService.deleteAboutUsInfo(aboutUsId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> aboutUsInfoList()  {
    
        return new ResponseEntity<>(aboutUsInfoService.allAboutUsInfos(), HttpStatus.OK);
    }

}
