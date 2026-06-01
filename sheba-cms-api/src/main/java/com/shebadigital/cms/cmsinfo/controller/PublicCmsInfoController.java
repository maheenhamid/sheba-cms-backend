package com.shebadigital.cms.cmsinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shebadigital.cms.cmsinfo.service.CmsInfoService;
import com.shebadigital.cms.common.ItemResponse;

@RestController
@RequestMapping(value = "/public/cms")
public class PublicCmsInfoController {
	
	@Autowired
	public CmsInfoService cmsInfoService;
	
	
	@GetMapping(value = "/info/{cmsId}")
    public ResponseEntity<ItemResponse> cmsInfo(@PathVariable Long cmsId)  {
        return new ResponseEntity<>(cmsInfoService.cmsInfoByCmsId(cmsId), HttpStatus.OK);
    }

}
