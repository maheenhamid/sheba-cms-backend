package com.shebadigital.cms.link.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.link.service.ImportantLinkService;

@RestController
@RequestMapping(value = "/public/important/link")
public class PublicImportantLinkController {
	
	@Autowired
	public ImportantLinkService importantLinkService;
	
	
	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> importantLinkList(@RequestParam Long cmsId)  {
        
		return new ResponseEntity<>(importantLinkService.importantLinkList(cmsId), HttpStatus.OK);
    }

}
