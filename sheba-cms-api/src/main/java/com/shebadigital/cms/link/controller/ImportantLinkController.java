package com.shebadigital.cms.link.controller;

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
import com.shebadigital.cms.link.model.request.ImportantLinkRequest;
import com.shebadigital.cms.link.model.request.ImportantLinkUpdateRequest;
import com.shebadigital.cms.link.service.ImportantLinkService;

@RestController
@RequestMapping(value = "/important/link")
public class ImportantLinkController {
	
	@Autowired
	public ImportantLinkService importantLinkService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> saveImportantLink(@RequestBody @Valid ImportantLinkRequest request)  {
        
		return new ResponseEntity<>(importantLinkService.saveImportantLinkInfo(request), HttpStatus.CREATED);
    }
	
	
	@PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateImportantLink(@RequestBody @Valid ImportantLinkUpdateRequest request)  {
        
		return new ResponseEntity<>(importantLinkService.updateImportantLinkInfo(request), HttpStatus.CREATED);
    }
	
	
	@DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteImportantLink(@RequestParam Long linkId)  {
        
		return new ResponseEntity<>(importantLinkService.deleteImportantLink(linkId), HttpStatus.CREATED);
    }
	
	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> importantLinkList()  {
        
		return new ResponseEntity<>(importantLinkService.importantLinkList(), HttpStatus.OK);
    }

}
