package com.shebadigital.cms.cmsinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.cmsinfo.service.StatisticInfoService;
import com.shebadigital.cms.common.ItemResponse;

@RestController
@RequestMapping(value = "/public/statistic/info")
public class PublicStatisticInfoController {

	@Autowired
	public StatisticInfoService statisticInfoService;
	
	
    
    @GetMapping(value = "/list")
    public ResponseEntity<ItemResponse> statisticInfoList(@RequestParam Long cmsId)  {
    
        return new ResponseEntity<>(statisticInfoService.viewStatisticInfo(cmsId), HttpStatus.OK);
    }
}
