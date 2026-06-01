package com.shebadigital.cms.notice.controller;

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

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.gallery.model.request.GalleryInfoCreateRequest;
import com.shebadigital.cms.gallery.model.request.GalleryInfoUpdateRequest;
import com.shebadigital.cms.notice.model.request.NoticeSaveRequest;
import com.shebadigital.cms.notice.model.request.NoticeUpdateRequest;
import com.shebadigital.cms.notice.service.NoticeService;

@RestController
@RequestMapping(value = "/notice/info")
public class NoticeController {

	@Autowired
	public NoticeService noticeService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createNotice(@RequestBody @Valid NoticeSaveRequest request)  {
        
		return new ResponseEntity<>(noticeService.saveNoticeInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateNotice(@RequestBody @Valid NoticeUpdateRequest request)  {
        return new ResponseEntity<>(noticeService.updateNoticeInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteNotice(@RequestParam Long noticeId)  {
        return new ResponseEntity<>(noticeService.deleteNoticeInfo(noticeId), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> noticeList()  {
    
        return new ResponseEntity<>(noticeService.allNoticeInfos(), HttpStatus.OK);
    }
}
