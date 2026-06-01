package com.shebadigital.cms.topstudent.controller;

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
import com.shebadigital.cms.topstudent.model.request.TopStudentRequest;
import com.shebadigital.cms.topstudent.model.request.TopStudentUpdateRequest;
import com.shebadigital.cms.topstudent.service.TopStudentService;

@RestController
@RequestMapping(value = "/top/student")
public class TopStudentController {
	
	
	@Autowired
	public TopStudentService topStudentService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createTopStudent(@RequestBody @Valid TopStudentRequest request)  {
		return new ResponseEntity<>(topStudentService.saveTopStudent(request), HttpStatus.CREATED);
    }

        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateTopStudent(@RequestBody @Valid TopStudentUpdateRequest request)  {
        return new ResponseEntity<>(topStudentService.updateTopStudent(request), HttpStatus.CREATED);
    }
    
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteTopStudent(@RequestParam Long studentId)  {
        return new ResponseEntity<>(topStudentService.deleteTopStudent(studentId), HttpStatus.CREATED);
    }
    
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> topStudentList()  {
        return new ResponseEntity<>(topStudentService.TopStudentInfos(), HttpStatus.OK);
    }

}
