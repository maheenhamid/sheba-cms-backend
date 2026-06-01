package com.shebadigital.cms.member.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.Folder;
import com.shebadigital.cms.common.HostInfo;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.file.service.FileStorageService;
import com.shebadigital.cms.file.util.FileUtils;
import com.shebadigital.cms.gallery.service.GalleryService;
import com.shebadigital.cms.member.model.entity.MemberInfo;
import com.shebadigital.cms.member.model.request.MemberInfoSaveRequest;
import com.shebadigital.cms.member.model.request.MemberInfoUpdateRequest;
import com.shebadigital.cms.member.model.response.MemberInfoView;
import com.shebadigital.cms.member.repository.MemberInfoRepository;

@Service
public class MemberInfoService {
	
	public Logger logger = LoggerFactory.getLogger(MemberInfoService.class);

	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public MemberInfoRepository memberInfoRepository;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository; 
	
	
	
	@Transactional
	public BaseResponse saveMemberInfo(MemberInfoSaveRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo=UserInfoUtils.getLoggedInUserCms();
		
		MemberInfo memberInfo = new MemberInfo();
		
		memberInfo.setAddress(request.getAddress());
		memberInfo.setAge(request.getAge());
		memberInfo.setCmsInfo(cmsInfo);
		memberInfo.setDesignation(request.getDesignation());
		memberInfo.setDetails(request.getDetails());
		memberInfo.setEmail(request.getEmail());
		
		memberInfo.setGender(request.getGender());
		memberInfo.setHobby(request.getHobby());
		memberInfo.setJoiningDate(request.getJoiningDate());
		memberInfo.setLinkFacebook(request.getLinkFacebook());
		memberInfo.setLinkLinkedin(request.getLinkLinkedin());
		memberInfo.setLinkTwitter(request.getLinkTwitter());
		memberInfo.setMemberName(request.getMemberName());
		memberInfo.setMemberStatus(request.getMemberStatus());
		memberInfo.setPhone(request.getPhone());
		memberInfo.setSerial(request.getSerial());
		memberInfo.setType(request.getType());

		if (request.isFileSave()) {


				try {

				String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());

				String fileName = cmsInfo.getCmsId()+"_MEMBER_"+filetime+"."+request.getFileType();
				fileStorageService.writeFileToPath(Folder.MEMBER.name(), request.getFileContent(), fileName);
				memberInfo.setFileName(fileName);
				memberInfoRepository.save(memberInfo);

				baseResponse.setMessage("Member Successfully Saved.");
				baseResponse.setMessageType(1);
				return baseResponse;
				} 
				
				catch (Exception e) {
					logger.error("" + e.getLocalizedMessage());
					baseResponse.setMessage(" " + e.getLocalizedMessage());
					baseResponse.setMessageType(0);
					return baseResponse;
				}
			}
		
		     else {

		    	 baseResponse.setMessage(" File is not uploaded ");
				 baseResponse.setMessageType(0);
				 return baseResponse;
		   }
		
	  }
	
	

	@Transactional
	public BaseResponse updateMemberInfo(MemberInfoUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo=UserInfoUtils.getLoggedInUserCms();
		
		MemberInfo memberInfo = memberInfoRepository.findByMemberIdAndCmsInfo(request.getMemberId(), cmsInfo);
		
		if(memberInfo==null) {
			baseResponse.setMessage("Member Info Not Found.");
			baseResponse.setMessageType(0);
			return baseResponse;	
		}
		
		memberInfo.setAddress(request.getAddress());
		memberInfo.setAge(request.getAge());
	
		memberInfo.setDesignation(request.getDesignation());
		memberInfo.setDetails(request.getDetails());
		memberInfo.setEmail(request.getEmail());
		
		memberInfo.setGender(request.getGender());
		memberInfo.setHobby(request.getHobby());
		memberInfo.setJoiningDate(request.getJoiningDate());
		memberInfo.setLinkFacebook(request.getLinkFacebook());
		memberInfo.setLinkLinkedin(request.getLinkLinkedin());
		memberInfo.setLinkTwitter(request.getLinkTwitter());
		memberInfo.setMemberName(request.getMemberName());
		memberInfo.setMemberStatus(request.getMemberStatus());
		memberInfo.setPhone(request.getPhone());
		memberInfo.setSerial(request.getSerial());
		memberInfo.setType(request.getType());

		if (request.isFileSave()) {
				
				try {
					
				fileStorageService.deleteFile(Folder.MEMBER.name(), memberInfo.getFileName());	

				String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());

				String fileName = cmsInfo.getCmsId()+"_MEMBER_"+filetime+"."+request.getFileType();
				fileStorageService.writeFileToPath(Folder.MEMBER.name(), request.getFileContent(), fileName);
				memberInfo.setFileName(fileName);
				
				} 
				
				catch (Exception e) {
					logger.error("" + e.getLocalizedMessage());
					baseResponse.setMessage(" " + e.getLocalizedMessage());
					baseResponse.setMessageType(0);
					return baseResponse;
				}
			}
		
		
		baseResponse.setMessage("Member Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
		  
		
	  }
	
	
	@Transactional
	public BaseResponse deleteMemberInfo(Long memberId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo=UserInfoUtils.getLoggedInUserCms();
		
		MemberInfo memberInfo = memberInfoRepository.findByMemberIdAndCmsInfo(memberId, cmsInfo);
		
		if(memberInfo==null) {
			baseResponse.setMessage("Member Info Not Found.");
			baseResponse.setMessageType(0);
			return baseResponse;	
		}
		
	    fileStorageService.deleteFile(Folder.MEMBER.name(), memberInfo.getFileName());	

	    memberInfoRepository.delete(memberInfo);		
		
		baseResponse.setMessage("Member Successfully Deleted.");
		baseResponse.setMessageType(1);
		return baseResponse;
		  
		
	  }
	
	
	
	public ItemResponse viewMemberInfoList() {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		List<MemberInfo> memberInfos = memberInfoRepository.findByCmsInfo(cmsInfo);
		
		List<MemberInfoView> views = new ArrayList<>();
		
		for(MemberInfo info : memberInfos) {
			
			MemberInfoView view = new MemberInfoView();
			BeanUtils.copyProperties(info, view);
			view.setJoiningDate(new SimpleDateFormat("yyyy-MM-dd").format(info.getJoiningDate()));
			
			view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.MEMBER.name()+"/"+info.getFileName());
		
			if(info.getMemberStatus()==1) {
			
				view.setMemberStatusStr("Active");
			}
			
			views.add(view);
		
		 }
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		
		return itemResponse;
		
	}
	

	public ItemResponse viewMemberInfoList(String type, Long cmsId) {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
		
		List<MemberInfo> memberInfos = memberInfoRepository.findByCmsInfoAndTypeOrderBySerial(cmsInfo,type);
		
		List<MemberInfoView> views = new ArrayList<>();
		
		for(MemberInfo info : memberInfos) {
			
			MemberInfoView view = new MemberInfoView();
			BeanUtils.copyProperties(info, view);
			
			view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.MEMBER.name()+"/"+info.getFileName());
		
			if(info.getMemberStatus()==1) {
			
				view.setMemberStatusStr("Active");
			}
			
			views.add(view);
		
		 }
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		
		return itemResponse;
		
	}
}
