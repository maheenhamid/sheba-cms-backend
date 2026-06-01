package com.shebadigital.cms.cmsinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.cmsinfo.service.AboutUsInfoService;
import com.shebadigital.cms.common.ItemResponse;

@RestController
@RequestMapping(value = "/public/aboutus/info")
public class PublicAboutUsInfoController {
	
	@Autowired
	public AboutUsInfoService aboutUsInfoService;

	  @GetMapping(value = "/view")
	    public ResponseEntity<ItemResponse> aboutUsInfoView(@RequestParam Long cmsId, @RequestParam String type)  {
	    
	        return new ResponseEntity<>(aboutUsInfoService.viewAboutUsInfosByType(cmsId, type), HttpStatus.OK);
	    }

}
