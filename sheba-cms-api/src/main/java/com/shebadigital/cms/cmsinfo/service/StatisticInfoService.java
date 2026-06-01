package com.shebadigital.cms.cmsinfo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.model.entity.StatisticInfo;
import com.shebadigital.cms.cmsinfo.model.request.StatisticInfoRequest;
import com.shebadigital.cms.cmsinfo.model.request.StatisticInfoUpdateRequest;
import com.shebadigital.cms.cmsinfo.model.response.StatisticInfoView;
import com.shebadigital.cms.cmsinfo.repository.AboutUsInfosRepository;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.cmsinfo.repository.StatisticInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.file.service.FileStorageService;
import com.shebadigital.cms.notice.repository.NoticeInfosRepository;

@Service
public class StatisticInfoService {

	public Logger logger = LoggerFactory.getLogger(AboutUsInfoService.class);

	@Autowired
	public NoticeInfosRepository noticeInfosRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Autowired
	public AboutUsInfosRepository aboutUsInfosRepository;
	
	@Autowired
	public StatisticInfoRepository statisticInfoRepository;
	
	@Transactional
    public BaseResponse saveStatisticInfo(StatisticInfoRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		StatisticInfo statisticInfo = statisticInfoRepository.findByCmsInfoAndName(cmsInfo, request.getName());
		
		if(statisticInfo!=null) {
			
			baseResponse.setMessage("Statistic Info Already Saved.");
			baseResponse.setMessageType(0);
			return baseResponse;
			
		}
		
		statisticInfo = new StatisticInfo();
		statisticInfo.setCmsInfo(cmsInfo);
		statisticInfo.setCreatedAt(new Date());
		statisticInfo.setName(request.getName());
		statisticInfo.setQuantity(request.getQuantity());
		statisticInfo.setSerial(request.getSerial());

		statisticInfoRepository.save(statisticInfo);
				
		baseResponse.setMessage("Statistic Info Saved.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    
    
	@Transactional
    public BaseResponse updateStatisticInfo(StatisticInfoUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		StatisticInfo statisticInfo = statisticInfoRepository.findByIdAndCmsInfo(request.getId(), cmsInfo);
		
		if(statisticInfo==null) {
			baseResponse.setMessage("No Statistic Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		statisticInfo.setName(request.getName());
		statisticInfo.setSerial(request.getSerial());
		statisticInfo.setQuantity(request.getQuantity());
		statisticInfo.setUpdatedAt(new Date());

		baseResponse.setMessage("Statistic Info Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    

    @Transactional
    public BaseResponse deleteStatisticInfo(Long id) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		StatisticInfo statisticInfo = statisticInfoRepository.findByIdAndCmsInfo(id, cmsInfo);
		
		if(statisticInfo==null) {
			baseResponse.setMessage("No Statistic Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}

		statisticInfoRepository.delete(statisticInfo);	 
				
		baseResponse.setMessage("Statistic Info Successfully Deleted..");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    
    
 
    public ItemResponse viewAllStatisticInfo() {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
    	    	 
    	List<StatisticInfo> statisticInfos = statisticInfoRepository.findByCmsInfoOrderBySerial(cmsInfo);
		
    	List<StatisticInfoView> views = new ArrayList<>();
    	
    	for(StatisticInfo info : statisticInfos) {
    	
    		StatisticInfoView view = new StatisticInfoView();
    		
    		view.setId(info.getId());
    		view.setName(info.getName());
    		view.setQuantity(info.getQuantity());
    		view.setSerial(info.getSerial());
    		
    		views.add(view);
    	
    	}
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	return itemResponse;
    	
    }
    

    public ItemResponse viewStatisticInfo(Long cmsId) {
		
    	ItemResponse itemResponse = new ItemResponse();
	
    	CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
    	   	 
    	List<StatisticInfo> statisticInfos = statisticInfoRepository.findByCmsInfoOrderBySerial(cmsInfo);
		
    	List<StatisticInfoView> views = new ArrayList<>();
    	
    	for(StatisticInfo info : statisticInfos) {
    	
    		StatisticInfoView view = new StatisticInfoView();
    		
    		view.setId(info.getId());
    		view.setName(info.getName());
    		view.setQuantity(info.getQuantity());
    		view.setSerial(info.getSerial());
    		
    		views.add(view);
    	
    	}
    		    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	return itemResponse;
    	
    }
    
  
   
}
