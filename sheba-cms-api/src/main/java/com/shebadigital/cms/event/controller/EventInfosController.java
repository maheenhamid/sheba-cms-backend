package com.shebadigital.cms.event.controller;

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
import com.shebadigital.cms.event.model.request.EventInfosRequest;
import com.shebadigital.cms.event.model.request.EventInfosUpdateRequest;
import com.shebadigital.cms.event.service.EventInfoService;

@RestController
@RequestMapping(value = "/event/info")
public class EventInfosController {

	@Autowired
	public EventInfoService eventInfoService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> saveEvent(@RequestBody @Valid EventInfosRequest request)  {
        
		return new ResponseEntity<>(eventInfoService.saveEventInfo(request), HttpStatus.CREATED);
    }
	
	
	@PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateEvent(@RequestBody @Valid EventInfosUpdateRequest request)  {
        
		return new ResponseEntity<>(eventInfoService.updateEventInfo(request), HttpStatus.CREATED);
    }
	
	
	@DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteEvent(@RequestParam Long eventId)  {
        
		return new ResponseEntity<>(eventInfoService.deleteEventInfo(eventId), HttpStatus.CREATED);
    }
	
	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> eventList()  {
        
		return new ResponseEntity<>(eventInfoService.eventInfoList(), HttpStatus.OK);
    }
}
