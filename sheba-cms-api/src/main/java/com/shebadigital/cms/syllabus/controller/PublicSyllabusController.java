package com.shebadigital.cms.syllabus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.syllabus.service.SyllabusService;

@RestController
@RequestMapping(value = "/public/syllabus")
public class PublicSyllabusController {
	
	@Autowired
	public SyllabusService syllabusService;
	
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> syllabusList(@RequestParam Long cmsId)  {
    
        return new ResponseEntity<>(syllabusService.allSyllabusInfos(cmsId), HttpStatus.OK);
    }

}
