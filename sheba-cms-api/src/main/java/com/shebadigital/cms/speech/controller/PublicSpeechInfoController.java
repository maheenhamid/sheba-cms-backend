package com.shebadigital.cms.speech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.speech.service.SpeechInfoService;

@RestController
@RequestMapping(value = "/public/speech/info")
public class PublicSpeechInfoController {
	
	@Autowired
	public SpeechInfoService speechInfoService;

	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> memberList(@RequestParam Long cmsId)  {
    
        return new ResponseEntity<>(speechInfoService.speechInfoList(cmsId), HttpStatus.OK);
    }
}
