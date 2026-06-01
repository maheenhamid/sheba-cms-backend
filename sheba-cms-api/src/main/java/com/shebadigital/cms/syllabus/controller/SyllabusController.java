package com.shebadigital.cms.syllabus.controller;

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
import com.shebadigital.cms.notice.model.request.NoticeSaveRequest;
import com.shebadigital.cms.notice.model.request.NoticeUpdateRequest;
import com.shebadigital.cms.notice.service.NoticeService;
import com.shebadigital.cms.syllabus.model.request.SyllabusSaveRequest;
import com.shebadigital.cms.syllabus.model.request.SyllabusUpdateRequest;
import com.shebadigital.cms.syllabus.service.SyllabusService;

@RestController
@RequestMapping(value = "/syllabus")
public class SyllabusController {
	
	@Autowired
	public SyllabusService syllabusService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createSyllabus(@RequestBody @Valid SyllabusSaveRequest request)  {
        
		return new ResponseEntity<>(syllabusService.saveSyllabus(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateSyllabus(@RequestBody @Valid SyllabusUpdateRequest request)  {
        return new ResponseEntity<>(syllabusService.updateSyllabus(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteSyllabus(@RequestParam Long syllabusId)  {
        return new ResponseEntity<>(syllabusService.deleteSyllabus(syllabusId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> syllabusList()  {
    
        return new ResponseEntity<>(syllabusService.allSyllabusInfos(), HttpStatus.OK);
    }

}
