package com.shebadigital.cms.classroutine.controller;

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

import com.shebadigital.cms.classroutine.model.request.ClassRoutineRequest;
import com.shebadigital.cms.classroutine.model.request.ClassRoutineUpdateRequest;
import com.shebadigital.cms.classroutine.service.ClassRoutineService;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;

@RestController
@RequestMapping(value = "/class/routine")
public class ClassRoutineController {

	@Autowired
	public ClassRoutineService classRoutineService; 
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createClassRoutine(@RequestBody @Valid ClassRoutineRequest request)  {
        
		return new ResponseEntity<>(classRoutineService.saveRoutineInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateClassRoutine(@RequestBody @Valid ClassRoutineUpdateRequest request)  {
        return new ResponseEntity<>(classRoutineService.updateRoutineInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteClassRoutine(@RequestParam Long classRoutineId)  {
        return new ResponseEntity<>(classRoutineService.deleteRoutineInfo(classRoutineId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> classRoutineList()  {
        return new ResponseEntity<>(classRoutineService.allClassRoutineInfos(), HttpStatus.OK);
    }
}
