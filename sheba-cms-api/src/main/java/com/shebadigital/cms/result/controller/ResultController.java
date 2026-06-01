package com.shebadigital.cms.result.controller;

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
import com.shebadigital.cms.result.model.request.ResultSaveRequest;
import com.shebadigital.cms.result.model.request.ResultUpdateRequest;
import com.shebadigital.cms.result.service.ResultService;

@RestController
@RequestMapping(value = "/result/info")
public class ResultController {

	@Autowired
	public ResultService resultService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createResult(@RequestBody @Valid ResultSaveRequest request)  {
        
		return new ResponseEntity<>(resultService.saveResultInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateResult(@RequestBody @Valid ResultUpdateRequest request)  {
        return new ResponseEntity<>(resultService.updateResultInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteResult(@RequestParam Long resultId)  {
        return new ResponseEntity<>(resultService.deleteResultInfo(resultId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> resultList()  {
    
        return new ResponseEntity<>(resultService.allResultInfos(), HttpStatus.OK);
    }
}
