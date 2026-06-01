package com.shebadigital.cms.topstudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.topstudent.service.TopStudentService;

@RestController
@RequestMapping(value = "/public/top/student")
public class PublicTopStudentController {

	@Autowired
	public TopStudentService topStudentService;
	
	
	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> topStudentList(@RequestParam Long cmsId)  {
        return new ResponseEntity<>(topStudentService.TopStudentInfos(cmsId), HttpStatus.OK);
    }
}
