package com.shebadigital.cms.event.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.event.model.entity.EventInfos;
import com.shebadigital.cms.event.model.request.EventInfosRequest;
import com.shebadigital.cms.event.model.request.EventInfosUpdateRequest;
import com.shebadigital.cms.event.model.response.EventInfoView;
import com.shebadigital.cms.event.repository.EventInfosRepository;

@Service
public class EventInfoService {
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Autowired
	public EventInfosRepository eventInfosRepository;

	@Transactional
	public BaseResponse saveEventInfo(EventInfosRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		EventInfos eventInfos = new EventInfos();
		
		eventInfos.setCmsInfo(cmsInfo);
		eventInfos.setCreatedAt(new Date());
		eventInfos.setDetails(request.getDetails());
		eventInfos.setEventStatus(request.getEventStatus());
		eventInfos.setExpiryDate(request.getExpiryDate());
		eventInfos.setIssueDate(request.getIssueDate());
		eventInfos.setSerial(request.getSerial());
		eventInfos.setSubTitle(request.getSubTitle());
		eventInfos.setTitle(request.getTitle());
		eventInfos.setType(request.getType());

		
		eventInfosRepository.save(eventInfos);

		
		baseResponse.setMessage("Event Info Successfully Saved.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	

	@Transactional
	public BaseResponse updateEventInfo(EventInfosUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		EventInfos eventInfos  =  eventInfosRepository.findByEventIdAndCmsInfo(request.getEventId(), cmsInfo);
		
		if(eventInfos==null) {
		
			baseResponse.setMessage("Event Infos Not Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		eventInfos.setDetails(request.getDetails());
		eventInfos.setEventStatus(request.getEventStatus());
		eventInfos.setExpiryDate(request.getExpiryDate());
		eventInfos.setIssueDate(request.getIssueDate());
		eventInfos.setSerial(request.getSerial());
		eventInfos.setSubTitle(request.getSubTitle());
		eventInfos.setTitle(request.getTitle());
		eventInfos.setType(request.getType());
		
		baseResponse.setMessage("Event Infos Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	
	@Transactional
	public BaseResponse deleteEventInfo(Long eventId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		EventInfos eventInfos  =  eventInfosRepository.findByEventIdAndCmsInfo(eventId, cmsInfo);
		
		if(eventInfos==null) {
			
			baseResponse.setMessage("Event Infos Not Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		eventInfosRepository.delete(eventInfos);
		
		baseResponse.setMessage("Event Info Successfully Deleted.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	

	public ItemResponse eventInfoList() {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		List<EventInfos> eventInfosList =  eventInfosRepository.findByCmsInfoOrderByExpiryDateDesc(cmsInfo);
		
		List<EventInfoView> views = new ArrayList<>();
		
		for(EventInfos info : eventInfosList) {
		
			EventInfoView infoResponse = new EventInfoView();
			
			infoResponse.setDetails(info.getDetails());
			
			infoResponse.setSerial(info.getSerial());
			infoResponse.setEventId(info.getEventId());
			infoResponse.setEventStatus(info.getEventStatus());
			infoResponse.setTitle(info.getTitle());
			infoResponse.setType(info.getType());
			infoResponse.setSubTitle(info.getSubTitle());
			
			if(info.getEventStatus()==1) {
			infoResponse.setEventStatusString("Enabled");
			}else {
			infoResponse.setEventStatusString("Disabled");	
			}
			
			if(info.getIssueDate()!=null) {
			infoResponse.setIssueDate(df.format(info.getIssueDate()));	
			}
			
			if(info.getExpiryDate()!=null) {
			infoResponse.setExpiryDate(df.format(info.getExpiryDate()));	
			}
						
			views.add(infoResponse);

		 }
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
	}
	

	
	public ItemResponse eventInfoList(Long cmsId) {
		
		ItemResponse itemResponse = new ItemResponse();
		
		Date today = new Date();
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		try {
			today = formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			
		}
		
		CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		List<EventInfos> eventInfosList =  eventInfosRepository.findByCmsInfoAndExpiryDateGreaterThanEqualOrderByExpiryDateDesc(cmsInfo, today);
		
		List<EventInfoView> views = new ArrayList<>();
		
		for(EventInfos info : eventInfosList) {
		
			EventInfoView infoResponse = new EventInfoView();
			
			infoResponse.setDetails(info.getDetails());
			
			infoResponse.setSerial(info.getSerial());
			infoResponse.setEventId(info.getEventId());
			infoResponse.setEventStatus(info.getEventStatus());
			infoResponse.setTitle(info.getTitle());
			infoResponse.setType(info.getType());
			infoResponse.setSubTitle(info.getSubTitle());
			
			if(info.getEventStatus()==1) {
			infoResponse.setEventStatusString("Enabled");
			}else {
			infoResponse.setEventStatusString("Disabled");	
			}
			
			if(info.getIssueDate()!=null) {
			infoResponse.setIssueDate(df.format(info.getIssueDate()));	
			}
			
			if(info.getExpiryDate()!=null) {
			infoResponse.setExpiryDate(df.format(info.getExpiryDate()));	
			}
						
			views.add(infoResponse);

		 }
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
	}

}
