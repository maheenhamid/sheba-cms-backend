/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.cmsinfo.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.model.request.CmsCreateRequest;
import com.shebadigital.cms.cmsinfo.model.request.CmsUpdateRequest;
import com.shebadigital.cms.cmsinfo.model.request.InstituteApprovalDocUpdateRequest;
import com.shebadigital.cms.cmsinfo.model.response.CmsInfoResponse;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.ApplicationUtils;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.Folder;
import com.shebadigital.cms.common.HostInfo;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.file.service.FileStorageService;
import com.shebadigital.cms.file.util.FileUtils;
import com.shebadigital.cms.user.model.entity.UserRoles;
import com.shebadigital.cms.user.model.entity.Users;
import com.shebadigital.cms.user.repository.UserRolesRepository;
import com.shebadigital.cms.user.repository.UsersRepository;


/**
 *
 * @author riad
 */

@Service
public class CmsInfoService {
    

    
   @Autowired 
   private CmsInfoRepository cmsInfoRepository; 
   
   @Autowired 
   private UsersRepository usersRepository;
   
   @Autowired 
   private UserRolesRepository userRolesRepository;
   
   @Autowired
   private FileStorageService fileStorageService;
   
   
   @Autowired
   private TokenStore tokenStore;
   
   
   
  public Long findMaxCmsId() {
	   
	   Long maxCmsId=cmsInfoRepository.findMaxCmsId();
	   if(maxCmsId==0) {
		   maxCmsId=10000l; 
	   }
	   return maxCmsId;
   }
   
   
   @Transactional
   public BaseResponse saveCmsInfo(CmsCreateRequest createRequest){
       
	   BaseResponse baseResponse=new BaseResponse();
       
       if(usersRepository.findByUsername(createRequest.getMobileNumber())!=null){
           baseResponse.setMessage("This Mobile No. Already exists");
           baseResponse.setMessageType(0);
           return baseResponse;
           
       }
       
       if(cmsInfoRepository.findByCmsName(createRequest.getCmsName())!=null){
           baseResponse.setMessage("This CmsInfo Name Already exists");
           baseResponse.setMessageType(0);
           return baseResponse;
           
       }
       
       CmsInfo cmsInfo=new CmsInfo();
       Long cmsId=findMaxCmsId()+1;
       
       cmsInfo.setCmsId(cmsId);
       cmsInfo.setCmsName(createRequest.getCmsName());
       cmsInfo.setAdminName(createRequest.getAdminName());
       cmsInfo.setAddress(createRequest.getAddress());
       cmsInfo.setContactNumber(createRequest.getCmsContactNumber());
       cmsInfo.setCreateDate(new Date());
       cmsInfo.setCmsEmail(createRequest.getCmsEmail());
       cmsInfo.setCmsStatus(1);
       
       
       
       Users users=usersRepository.findByUsername(createRequest.getUserName());
       if(users!=null) {
       	baseResponse.setMessage("User Name= "+createRequest.getUserName()+" already exists.");
       	baseResponse.setMessageType(0);
       	return baseResponse;
       }
      
       
        cmsInfo = cmsInfoRepository.save(cmsInfo);
        
        users = new Users();
        
        String encryptedPassword=UserInfoUtils.getHashPassword(createRequest.getPassword());
        users.setEnabled(true);
        users.setPassword(encryptedPassword);
        users.setUsername(createRequest.getUserName());
        users.setCmsInfo(cmsInfo);
        users.setNickName(createRequest.getAdminName());
        users.setMobileNo(createRequest.getMobileNumber());
        users=usersRepository.save(users);
        
        UserRoles roles=new UserRoles();
        roles.setUsername(createRequest.getUserName());
        roles.setRoleName("ROLE_ADMIN");
        roles.setCmsInfo(cmsInfo);

        userRolesRepository.save(roles);
        
        baseResponse.setMessage("CmsInfo Created Successfully");
        baseResponse.setMessageType(1);
       
       return baseResponse;
       
   }
   
   
   public ItemResponse cmsInfo(){
       
       ItemResponse  itemResponse=new ItemResponse();
       CmsInfoResponse cmsInfoResponse=new CmsInfoResponse();
       Long cmsId = UserInfoUtils.getLoggedInUserCms().getCmsId();
       Users user= UserInfoUtils.getLoggedInUser();
       
       
       CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
       
       if(cmsInfo==null) {
           itemResponse.setMessageType(0);
           itemResponse.setMessage("No CmsInfo Found.");
           return itemResponse;
       }
       
       copyCmsInfoToCmsInfoResponse(cmsInfo, cmsInfoResponse);
       
       List<String> roleList=new ArrayList<>();
       
       for(String ur: user.getRoles()){
          roleList.add(ur);
       }
       
       cmsInfoResponse.setUserName(user.getUsername());
       cmsInfoResponse.setRoleList(roleList);
       
       itemResponse.setMessage("OK");
       itemResponse.setItem(cmsInfoResponse);
       itemResponse.setMessageType(1);
       return itemResponse;
   }  
   
 
   public ItemResponse cmsList(){
       
       ItemResponse  itemResponse=new ItemResponse();
       
       List<CmsInfo> cmsInfos = cmsInfoRepository.findAll();
      
       List<CmsInfoResponse> infos = new ArrayList<>();
       
       for(CmsInfo cms : cmsInfos) {
    	
    	  CmsInfoResponse cmsInfoResponse=new CmsInfoResponse();
    	   
    	  copyCmsInfoToCmsInfoResponse(cms, cmsInfoResponse);
       
    	  infos.add(cmsInfoResponse);
       }
       
       
       itemResponse.setMessage("OK");
       itemResponse.setItem(infos);
       itemResponse.setMessageType(1);
       return itemResponse;
   }
   
   
//   public BaseResponse goToCms(Long cmsId) {
//	   
//	   BaseResponse baseResponse = new BaseResponse();
//	   
//	   CmsInfo realCmsInfo = cmsInfoRepository.findByCmsId(cmsId);
//	   
//	   if(realCmsInfo==null) {
//		   baseResponse.setMessage("No Cms Found");
//		   baseResponse.setMessageType(0);
//		   return baseResponse;   
//	   }
//	   
//	   Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	   
//	   user.setCmsInfo(realCmsInfo);
//	   
//	   baseResponse.setMessage("OK");
//	   baseResponse.setMessageType(1);
//	   
//	   return baseResponse;
//   }
   
   
 
   public BaseResponse goToCms(Long cmsId) {
       
	   BaseResponse baseResponse = new BaseResponse();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        OAuth2Authentication requestingUser = (OAuth2Authentication) securityContext.getAuthentication();
        Users user = (Users) requestingUser.getUserAuthentication().getPrincipal();
        
        CmsInfo realCmsInfo = cmsInfoRepository.findByCmsId(cmsId);
 	   
 	   if(realCmsInfo==null) {
 		   baseResponse.setMessage("No Cms Found");
 		   baseResponse.setMessageType(0);
 		   return baseResponse;   
 	   }
        
       
 	   user.setCmsInfo(realCmsInfo);

       OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails) requestingUser.getDetails();
       OAuth2AccessToken tokenStored = tokenStore.readAccessToken(authDetails.getTokenValue());
       tokenStore.storeAccessToken(tokenStored, requestingUser);
       
        baseResponse.setMessageType(1);
        baseResponse.setMessage("OK");
        return baseResponse;
    }
   

   public ItemResponse cmsInfoByCmsId(Long cmsId){
       
       ItemResponse  itemResponse=new ItemResponse();
       
       CmsInfoResponse cmsInfoResponse=new CmsInfoResponse();       
       
       CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
       
       if(cmsInfo == null) {
           itemResponse.setMessageType(0);
           itemResponse.setMessage("No CmsInfo Found.");
           return itemResponse;
       }
       
       copyCmsInfoToCmsInfoResponse(cmsInfo, cmsInfoResponse);
       
       itemResponse.setMessage("OK");
       itemResponse.setItem(cmsInfoResponse);
       itemResponse.setMessageType(1);
       return itemResponse;
   }
   
   
   
   public void copyCmsInfoToCmsInfoResponse(CmsInfo cmsInfo, CmsInfoResponse cmsInfoResponse) {
	 
	   cmsInfoResponse.setCmsId(cmsInfo.getCmsId());
       cmsInfoResponse.setCmsName(cmsInfo.getCmsName());
       cmsInfoResponse.setAddress(cmsInfo.getAddress());
       cmsInfoResponse.setCmsEmail(cmsInfo.getCmsEmail());
       cmsInfoResponse.setAdminName(cmsInfo.getAdminName());
       cmsInfoResponse.setCmsEmail(cmsInfo.getCmsEmail());
       cmsInfoResponse.setContactNumber(cmsInfo.getContactNumber());
       cmsInfoResponse.setCreateDate(new SimpleDateFormat("dd-MM-yyyy").format(cmsInfo.getCreateDate()));
       cmsInfoResponse.setImageName(cmsInfo.getImageName());
       cmsInfoResponse.setCmsStatus(cmsInfo.getCmsStatus());
       cmsInfoResponse.setStaffFullName("");
       
       String imageLink = HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.CMS.name()+"/"+cmsInfo.getImageName();
       cmsInfoResponse.setImagelink(imageLink);
       
       String bannerLink = HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.CMS.name()+"/"+cmsInfo.getBannerImage();
       cmsInfoResponse.setBannerLink(bannerLink);
       cmsInfoResponse.setBannerName(cmsInfo.getBannerImage());
       
       cmsInfoResponse.setWebSite(cmsInfo.getWebSite());
       cmsInfoResponse.setLinkFacebook(cmsInfo.getLinkFacebook());
       cmsInfoResponse.setLinkTwitter(cmsInfo.getLinkTwitter());
       cmsInfoResponse.setLinkLinkedin(cmsInfo.getLinkLinkedin());
       cmsInfoResponse.setShebaInstituteId(cmsInfo.getShebaInstituteId());
       cmsInfoResponse.setShebaInstituteType(cmsInfo.getShebaInstituteType());
       cmsInfoResponse.setSchoolCollegeAdmissionLink(cmsInfo.getSchoolCollegeAdmissionLink());
       cmsInfoResponse.setUniversityAdmissionLink(cmsInfo.getUniversityAdmissionLink());
       cmsInfoResponse.setPaymentLink(cmsInfo.getPaymentLink());
       
       cmsInfoResponse.setPrimaryColor(cmsInfo.getPrimaryColor());
       cmsInfoResponse.setSecondaryColor(cmsInfo.getSecondaryColor());
       cmsInfoResponse.setThirdColor(cmsInfo.getThirdColor());
       cmsInfoResponse.setForthColor(cmsInfo.getForthColor());
       cmsInfoResponse.setFifhColor(cmsInfo.getFifthColor());
       
       String InstituteApprovalFileLink="";
       
       if(cmsInfo.getInstituteApprovalFileName()!=null) {
    	   
    	   if(cmsInfo.getInstituteApprovalFileName().contains(".pdf")) {
    		   InstituteApprovalFileLink = HostInfo.HOST_URL+""+FileUtils.PDF_FILE_VIEWER+"/"+Folder.CMS.name()+"/"+cmsInfo.getInstituteApprovalFileName();   
    	   }else {
    		   InstituteApprovalFileLink = HostInfo.HOST_URL+""+FileUtils.IMAGE_FILE_VIEWER+"/"+Folder.CMS.name()+"/"+cmsInfo.getInstituteApprovalFileName();    
    	   }
       }
       
       cmsInfoResponse.setInstituteApprovalFileLink(InstituteApprovalFileLink);
       
       cmsInfoResponse.setInstituteApprovalFileName(cmsInfo.getInstituteApprovalFileName());
   }
   

   @Transactional
   public BaseResponse updateCmsInfo(CmsUpdateRequest request){
       
	   BaseResponse  baseResponse=new BaseResponse();
   
       Long cmsId = UserInfoUtils.getLoggedInUserCms().getCmsId();

       CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
       
       if(cmsInfo==null) {
           baseResponse.setMessageType(0);
           baseResponse.setMessage("No CmsInfo Found.");
           return baseResponse;
       }
       
       cmsInfo.setCmsName(request.getCmsName());
       cmsInfo.setAddress(request.getAddress());
       cmsInfo.setCmsEmail(request.getCmsEmail());
       cmsInfo.setAdminName(request.getAdminName());
       cmsInfo.setCmsEmail(request.getCmsEmail());
       cmsInfo.setContactNumber(request.getContactNumber());
       
       cmsInfo.setWebSite(request.getWebSite());
       cmsInfo.setLinkFacebook(request.getLinkFacebook());
       cmsInfo.setLinkTwitter(request.getLinkTwitter());
       cmsInfo.setLinkLinkedin(request.getLinkLinkedin());
       
       cmsInfo.setShebaInstituteId(request.getShebaInstituteId());
       cmsInfo.setShebaInstituteType(request.getShebaInstituteType());
       
       cmsInfo.setSchoolCollegeAdmissionLink(request.getSchoolCollegeAdmissionLink());
       cmsInfo.setUniversityAdmissionLink(request.getUniversityAdmissionLink());
       cmsInfo.setPaymentLink(request.getPaymentLink());
       
       if(request.getPrimaryColor()!=null) {
    	   cmsInfo.setPrimaryColor(request.getPrimaryColor());   
       }
       
		if(request.getSecondaryColor()!=null) {
			cmsInfo.setSecondaryColor(request.getSecondaryColor());   	   
		}
		
		if(request.getThirdColor()!=null) {
			cmsInfo.setThirdColor(request.getThirdColor());	   
		}
		
		if(request.getForthColor()!=null) {
			cmsInfo.setForthColor(request.getForthColor());	   
		}
		
		if(request.getFifthColor()!=null) {
			cmsInfo.setFifthColor(request.getFifthColor());	   
		}
		
              
       if(request.isImageSave()) {
    	   
    	   try {
    		   fileStorageService.deleteFile(Folder.CMS.name(), cmsInfo.getImageName());
    		   String imageName = cmsInfo.getCmsId()+"_logo."+request.getImageType();
    		   fileStorageService.writeFileToPath(Folder.CMS.name(), request.getImageContent(), imageName); 
    		   cmsInfo.setImageName(imageName);
    	   }catch(Exception e) {
    		   
    	   }
       }
       
       if(request.isBannerSave()) {
    	   
    	   try {
    		   fileStorageService.deleteFile(Folder.CMS.name(), cmsInfo.getBannerImage());
    		   String bannerImageName = cmsInfo.getCmsId()+"_banner."+request.getBannerType();
    		   fileStorageService.writeFileToPath(Folder.CMS.name(), request.getBannerContent(), bannerImageName); 
    		   cmsInfo.setBannerImage(bannerImageName);
    	   }catch(Exception e) {
    		   
    	   }
       }
       
       baseResponse.setMessage("Cms Info Successfully Updated.");
       baseResponse.setMessageType(1);
       return baseResponse;
   }
   
   
   
   @Transactional
   public BaseResponse updateInstituteApprovalDocumentation(InstituteApprovalDocUpdateRequest request) {
	   
	   BaseResponse  baseResponse=new BaseResponse();
	   
       Long cmsId = UserInfoUtils.getLoggedInUserCms().getCmsId();

       CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
       
           if(request.isFileSave()) {
    	   
    	   try {
    		   fileStorageService.deleteFile(Folder.CMS.name(), cmsInfo.getInstituteApprovalFileName());
    		   String fileName = cmsInfo.getCmsId()+"_institute_approval_doc."+request.getFileType();
    		   fileStorageService.writeFileToPath(Folder.CMS.name(), request.getFileContent(), fileName); 
    		   cmsInfo.setInstituteApprovalFileName(fileName);
    	   }catch(Exception e) {
    		   baseResponse.setMessage(""+e.getLocalizedMessage());
    	       baseResponse.setMessageType(0);
    	       return baseResponse;
    		   
    	   }
       }
       
       baseResponse.setMessage("Institute Approval Documentation Successfully Updated.");
       baseResponse.setMessageType(1);
       return baseResponse;
   }

    
}
