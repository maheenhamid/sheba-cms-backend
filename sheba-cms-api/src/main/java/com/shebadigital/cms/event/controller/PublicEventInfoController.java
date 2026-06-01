package com.shebadigital.cms.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.event.service.EventInfoService;

@RestController
@RequestMapping(value = "/public/event/info")
public class PublicEventInfoController {

	@Autowired
	public EventInfoService eventInfoService;
	
	
	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> eventList(@RequestParam Long cmsId)  {
        
		return new ResponseEntity<>(eventInfoService.eventInfoList(cmsId), HttpStatus.OK);
    }
}
