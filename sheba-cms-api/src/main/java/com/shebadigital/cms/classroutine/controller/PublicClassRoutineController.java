package com.shebadigital.cms.classroutine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.classroutine.service.ClassRoutineService;
import com.shebadigital.cms.common.ItemResponse;

@RestController
@RequestMapping(value = "/public/class/routine")
public class PublicClassRoutineController {
	
	@Autowired
	public ClassRoutineService classRoutineService; 
	
	 @GetMapping(value = "/list")
	  public ResponseEntity<ItemResponse> classRoutineList(@RequestParam Long cmsId, @RequestParam Integer year)  {
	       return new ResponseEntity<>(classRoutineService.allClassRoutineInfos(cmsId,year), HttpStatus.OK);
	 }
	

}
