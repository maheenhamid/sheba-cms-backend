package com.shebadigital.cms.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.notice.service.NoticeService;


@RestController
@RequestMapping(value = "/public/notice/info")
public class PublicNoticeController {

	@Autowired
	public NoticeService noticeService;
	
	@GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> noticeList(@RequestParam Long cmsId)  {
    
        return new ResponseEntity<>(noticeService.publicNoticeInfos(cmsId), HttpStatus.OK);
    }
}
