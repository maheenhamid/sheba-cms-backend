package com.shebadigital.cms.member.controller;

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
import com.shebadigital.cms.member.model.request.MemberInfoSaveRequest;
import com.shebadigital.cms.member.model.request.MemberInfoUpdateRequest;
import com.shebadigital.cms.member.service.MemberInfoService;

@RestController
@RequestMapping(value = "/member/info")
public class MemberController {

	@Autowired
	public MemberInfoService memberInfoService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createMember(@RequestBody @Valid MemberInfoSaveRequest request)  {
        
		return new ResponseEntity<>(memberInfoService.saveMemberInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateMember(@RequestBody @Valid MemberInfoUpdateRequest request)  {
        return new ResponseEntity<>(memberInfoService.updateMemberInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteMember(@RequestParam Long memberId)  {
        return new ResponseEntity<>(memberInfoService.deleteMemberInfo(memberId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> memberList()  {
    
        return new ResponseEntity<>(memberInfoService.viewMemberInfoList(), HttpStatus.OK);
    }
}
