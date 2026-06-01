package com.shebadigital.cms.speech.controller;

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
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.speech.model.request.SpeechInfoSaveRequest;
import com.shebadigital.cms.speech.model.request.SpeechInfoUpdateRequest;
import com.shebadigital.cms.speech.service.SpeechInfoService;

@RestController
@RequestMapping(value = "/speech/info")
public class SpeechInfoController {

	@Autowired
	public SpeechInfoService speechInfoService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> saveSpeech(@RequestBody @Valid SpeechInfoSaveRequest request)  {
        
		return new ResponseEntity<>(speechInfoService.saveSpeechInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateSpeech(@RequestBody @Valid SpeechInfoUpdateRequest request)  {
        return new ResponseEntity<>(speechInfoService.updateSpeechInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteSpeech(@RequestParam Long speechId)  {
        return new ResponseEntity<>(speechInfoService.deleteSpeechInfo(speechId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> speechList()  {
    
        return new ResponseEntity<>(speechInfoService.speechInfoList(), HttpStatus.OK);
    }
}
