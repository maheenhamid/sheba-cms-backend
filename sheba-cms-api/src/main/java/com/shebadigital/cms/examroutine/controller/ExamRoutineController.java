package com.shebadigital.cms.examroutine.controller;

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
import com.shebadigital.cms.examroutine.model.request.ExamRoutineRequest;
import com.shebadigital.cms.examroutine.model.request.ExamRoutineUpdateRequest;
import com.shebadigital.cms.examroutine.service.ExamRoutineService;
import com.shebadigital.cms.notice.model.request.NoticeSaveRequest;
import com.shebadigital.cms.notice.model.request.NoticeUpdateRequest;

@RestController
@RequestMapping(value = "/exam/routine")
public class ExamRoutineController {
	
	
	@Autowired
	public ExamRoutineService examRoutine;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createExamRoutine(@RequestBody @Valid ExamRoutineRequest request)  {
        
		return new ResponseEntity<>(examRoutine.saveExamRoutine(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateExamRoutine(@RequestBody @Valid ExamRoutineUpdateRequest request)  {
        return new ResponseEntity<>(examRoutine.updateExamRoutine(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteExamRoutine(@RequestParam Long examRoutineId)  {
        return new ResponseEntity<>(examRoutine.deleteExamRoutine(examRoutineId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> examRoutineList()  {
    
        return new ResponseEntity<>(examRoutine.examRoutineList(), HttpStatus.OK);
    }

}
