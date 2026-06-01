package com.shebadigital.cms.examroutine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.examroutine.service.ExamRoutineService;

@RestController
@RequestMapping(value = "/public/exam/routine")
public class PublicExamRoutineController {
	
	
	@Autowired
	public ExamRoutineService examRoutine;

    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> examRoutineList(@RequestParam Long cmsId)  {
    
        return new ResponseEntity<>(examRoutine.examRoutineList(cmsId), HttpStatus.OK);
    }

}
