package com.shebadigital.cms.speech.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.Folder;
import com.shebadigital.cms.common.HostInfo;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.file.util.FileUtils;
import com.shebadigital.cms.member.model.entity.MemberInfo;
import com.shebadigital.cms.member.repository.MemberInfoRepository;
import com.shebadigital.cms.speech.model.entity.SpeechInfos;
import com.shebadigital.cms.speech.model.request.SpeechInfoSaveRequest;
import com.shebadigital.cms.speech.model.request.SpeechInfoUpdateRequest;
import com.shebadigital.cms.speech.model.response.SpeechInfoResponse;
import com.shebadigital.cms.speech.repository.SpeechInfosRepository;



@Service
public class SpeechInfoService {
	
	@Autowired
	public MemberInfoRepository memberInfoRepository;
	
	@Autowired
	public SpeechInfosRepository speechInfosRepository;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;

	@Transactional
	public BaseResponse saveSpeechInfo(SpeechInfoSaveRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		MemberInfo memberInfo =  memberInfoRepository.findByMemberIdAndCmsInfo(request.getMemberId(), cmsInfo);
		
		if(memberInfo==null) {
		
			baseResponse.setMessage("Member Info Not Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		SpeechInfos speechInfos = new SpeechInfos();
		
		speechInfos.setCmsInfo(cmsInfo);
		speechInfos.setCreatedAt(new Date());
		speechInfos.setDetails(request.getDetails());
		speechInfos.setMemberInfo(memberInfo);
		speechInfos.setSerial(request.getSerial());
		speechInfos.setSpeechStatus(request.getSpeechStatus());
		speechInfos.setTitle(request.getTitle());
		speechInfos.setUpdatedAt(null);
		
		speechInfosRepository.save(speechInfos);

		
		baseResponse.setMessage("Speech Info Successfully Saved.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	

	@Transactional
	public BaseResponse updateSpeechInfo(SpeechInfoUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		SpeechInfos speechInfos =  speechInfosRepository.findBySpeechIdAndCmsInfo(request.getSpeechId(), cmsInfo);
		
		if(speechInfos==null) {
		
			baseResponse.setMessage("Speech Infos Not Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		

		speechInfos.setDetails(request.getDetails());
		speechInfos.setSerial(request.getSerial());
		speechInfos.setSpeechStatus(request.getSpeechStatus());
		speechInfos.setTitle(request.getTitle());
		speechInfos.setUpdatedAt(new Date());
		

		
		baseResponse.setMessage("Speech Info Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	
	@Transactional
	public BaseResponse deleteSpeechInfo(Long speechId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		SpeechInfos speechInfos =  speechInfosRepository.findBySpeechIdAndCmsInfo(speechId, cmsInfo);
		
		if(speechInfos==null) {
		
			baseResponse.setMessage("Speech Infos Not Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		speechInfosRepository.delete(speechInfos);
		
		baseResponse.setMessage("Speech Info Successfully Deleted.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	
	

	public ItemResponse speechInfoList() {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		List<SpeechInfos> speechInfos =  speechInfosRepository.findByCmsInfoOrderBySerial(cmsInfo);
		
		List<SpeechInfoResponse> views = new ArrayList<>();
		
		for(SpeechInfos info : speechInfos) {
		
			SpeechInfoResponse infoResponse = new SpeechInfoResponse();
			
			infoResponse.setDetails(info.getDetails());
			
			infoResponse.setSerial(info.getSerial());
			infoResponse.setSpeechId(info.getSpeechId());
			infoResponse.setSpeechStatus(info.getSpeechStatus());
			infoResponse.setTitle(info.getTitle());
			
			if(info.getSpeechStatus()==1) {
			infoResponse.setSpeechStatusString("Enabled");
			}else {
			infoResponse.setSpeechStatusString("Disabled");	
			}
			
			infoResponse.setMemberDesignation(info.getMemberInfo().getDesignation());
			infoResponse.setMemberName(info.getMemberInfo().getMemberName());
			
			views.add(infoResponse);

		}
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
	}
	
	

	public ItemResponse speechInfoList(Long cmsId) {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
		
		List<SpeechInfos> speechInfos =  speechInfosRepository.findByCmsInfoAndSpeechStatusOrderBySerial(cmsInfo,1);
		
		List<SpeechInfoResponse> views = new ArrayList<>();
		
		for(SpeechInfos info : speechInfos) {
		
			SpeechInfoResponse infoResponse = new SpeechInfoResponse();
			
			infoResponse.setDetails(info.getDetails());
			
			infoResponse.setSerial(info.getSerial());
			infoResponse.setSpeechId(info.getSpeechId());
			infoResponse.setSpeechStatus(info.getSpeechStatus());
			infoResponse.setTitle(info.getTitle());
			
			if(info.getSpeechStatus()==1) {
			infoResponse.setSpeechStatusString("Enabled");
			}else {
			infoResponse.setSpeechStatusString("Disabled");	
			}
			
			infoResponse.setMemberDesignation(info.getMemberInfo().getDesignation());
			infoResponse.setMemberName(info.getMemberInfo().getMemberName());
			infoResponse.setMemberPhotoPath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.MEMBER.name()+"/"+info.getMemberInfo().getFileName());
			
			views.add(infoResponse);

		}
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
	}
}
