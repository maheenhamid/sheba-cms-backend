package com.shebadigital.cms.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.member.service.MemberInfoService;

@RestController
@RequestMapping(value = "/public/member/info")
public class PublicMemberController {

	@Autowired
	public MemberInfoService memberInfoService;
	

    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> memberList(@RequestParam String type, @RequestParam Long cmsId)  {
    
        return new ResponseEntity<>(memberInfoService.viewMemberInfoList(type, cmsId), HttpStatus.OK);
    }
}
