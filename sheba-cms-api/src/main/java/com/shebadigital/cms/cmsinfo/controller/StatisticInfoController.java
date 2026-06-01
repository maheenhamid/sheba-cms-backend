package com.shebadigital.cms.cmsinfo.controller;

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

import com.shebadigital.cms.cmsinfo.model.request.AboutUsInfosRequest;
import com.shebadigital.cms.cmsinfo.model.request.AboutUsInfosUpdateRequest;
import com.shebadigital.cms.cmsinfo.model.request.StatisticInfoRequest;
import com.shebadigital.cms.cmsinfo.model.request.StatisticInfoUpdateRequest;
import com.shebadigital.cms.cmsinfo.service.AboutUsInfoService;
import com.shebadigital.cms.cmsinfo.service.StatisticInfoService;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;

@RestController
@RequestMapping(value = "/statistic/info")
public class StatisticInfoController {

	@Autowired
	public StatisticInfoService statisticInfoService;
	
	@PostMapping(value = "/save")
    public ResponseEntity<BaseResponse> createStatisticInfo(@RequestBody @Valid StatisticInfoRequest request)  {
        
		return new ResponseEntity<>(statisticInfoService.saveStatisticInfo(request), HttpStatus.CREATED);
    }
        
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse> updateStatisticInfo(@RequestBody @Valid StatisticInfoUpdateRequest request)  {
        return new ResponseEntity<>(statisticInfoService.updateStatisticInfo(request), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> deleteStatisticInfo(@RequestParam Long id)  {
        return new ResponseEntity<>(statisticInfoService.deleteStatisticInfo(id), HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> statisticInfoList()  {
    
        return new ResponseEntity<>(statisticInfoService.viewAllStatisticInfo(), HttpStatus.OK);
    }
}
