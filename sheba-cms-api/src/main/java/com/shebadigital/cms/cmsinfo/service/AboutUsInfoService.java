package com.shebadigital.cms.cmsinfo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.cmsinfo.model.entity.AboutUsInfos;
import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.model.request.AboutUsInfosRequest;
import com.shebadigital.cms.cmsinfo.model.request.AboutUsInfosUpdateRequest;
import com.shebadigital.cms.cmsinfo.model.response.AboutUsInfosView;
import com.shebadigital.cms.cmsinfo.repository.AboutUsInfosRepository;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.Folder;
import com.shebadigital.cms.common.HostInfo;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.file.service.FileStorageService;
import com.shebadigital.cms.file.util.FileUtils;
import com.shebadigital.cms.gallery.service.GalleryService;
import com.shebadigital.cms.notice.model.entity.NoticeInfo;
import com.shebadigital.cms.notice.model.request.NoticeSaveRequest;
import com.shebadigital.cms.notice.model.request.NoticeUpdateRequest;
import com.shebadigital.cms.notice.model.response.NoticeInfoView;
import com.shebadigital.cms.notice.repository.NoticeInfosRepository;

@Service
public class AboutUsInfoService {

	
	public Logger logger = LoggerFactory.getLogger(AboutUsInfoService.class);

	@Autowired
	public NoticeInfosRepository noticeInfosRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Autowired
	public AboutUsInfosRepository aboutUsInfosRepository;
	
	@Transactional
    public BaseResponse saveAboutUsInfo(AboutUsInfosRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		AboutUsInfos aboutUsInfos = aboutUsInfosRepository.findByCmsInfoAndType(cmsInfo, request.getType());
		
		if(aboutUsInfos!=null) {
			
			baseResponse.setMessage("AboutUs Info Already Saved.");
			baseResponse.setMessageType(0);
			return baseResponse;
			
		}
		
		aboutUsInfos = new AboutUsInfos();
		aboutUsInfos.setCmsInfo(cmsInfo);
		aboutUsInfos.setCreatedAt(new Date());
		aboutUsInfos.setDetails(request.getDetails());
		aboutUsInfos.setSerial(request.getSerial());
		aboutUsInfos.setSubTitle(request.getSubTitle());
		aboutUsInfos.setTitle(request.getTitle());
		aboutUsInfos.setType(request.getType());
		
		
		if(request.isFileSave()) {
			
			 try {
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				  
	    		  String fileName=cmsInfo.getCmsId()+"_"+request.getType()+"_"+filetime+"."+request.getFileType();
	    		  
	    		  fileStorageService.writeFileToPath(Folder.ABOUT_US.name(), request.getFileContent(), fileName);
	    		  aboutUsInfos.setFileName(fileName);
	    		  
	    		  aboutUsInfosRepository.save(aboutUsInfos);	
	    		  
	    		  baseResponse.setMessage("AboutUs Info Successfully Saved.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		aboutUsInfosRepository.save(aboutUsInfos);
				
		return baseResponse;
	}
    
    
	@Transactional
    public BaseResponse updateAboutUsInfo(AboutUsInfosUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		AboutUsInfos aboutUsInfos = aboutUsInfosRepository.findByAboutUsIdAndCmsInfo(request.getAboutUsId(), cmsInfo);
		
		if(aboutUsInfos==null) {
			baseResponse.setMessage("No About Us Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		aboutUsInfos.setDetails(request.getDetails());
		aboutUsInfos.setSerial(request.getSerial());
		aboutUsInfos.setSubTitle(request.getSubTitle());
		aboutUsInfos.setTitle(request.getTitle());
		aboutUsInfos.setUpdatedAt(new Date());
		aboutUsInfos.setType(request.getType());

		if(request.isFileSave()) {
			
			 try {
				 
				  fileStorageService.deleteFile(Folder.ABOUT_US.name(), aboutUsInfos.getFileName());
				  
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				  
	    		  String fileName=cmsInfo.getCmsId()+"_"+request.getType()+"_"+filetime+"."+request.getFileType();
	    		  
	    		  fileStorageService.writeFileToPath(Folder.ABOUT_US.name(), request.getFileContent(), fileName);
	    		  aboutUsInfos.setFileName(fileName);
	    		  
	    		  
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		

		  
		  baseResponse.setMessage("AboutUs Info Successfully Updated.");
		  baseResponse.setMessageType(1);
		  return baseResponse;
	}
    

    @Transactional
    public BaseResponse deleteAboutUsInfo(Long aboutUsId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
         AboutUsInfos aboutUsInfos = aboutUsInfosRepository.findByAboutUsIdAndCmsInfo(aboutUsId, cmsInfo);
		
		if(aboutUsInfos==null) {
			baseResponse.setMessage("No About Us Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}

		fileStorageService.deleteFile(Folder.ABOUT_US.name(), aboutUsInfos.getFileName());
		aboutUsInfosRepository.delete(aboutUsInfos);	 
				
		baseResponse.setMessage("About Us Infos Successfully Deleted..");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    
    
 
    public ItemResponse allAboutUsInfos() {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	 
    	List<AboutUsInfos> aboutUsInfos = aboutUsInfosRepository.findByCmsInfo(cmsInfo);
		
    	List<AboutUsInfosView> views = new ArrayList<>();
    	
    	for(AboutUsInfos info : aboutUsInfos) {
    	
    		AboutUsInfosView view = new AboutUsInfosView();
    		
    		copyAboutUsInfosToAboutUsInfosView(info, view, df);
    		
    		views.add(view);
    	
    	}
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	return itemResponse;
    	
    }
    

    public ItemResponse viewAboutUsInfosByType(Long cmsId, String type) {
		
    	ItemResponse itemResponse = new ItemResponse();
	
    	CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	 
    	AboutUsInfos aboutUsInfos = aboutUsInfosRepository.findByCmsInfoAndType(cmsInfo,type);
    	
    	if(aboutUsInfos==null) {
        	itemResponse.setMessage("No Record Found.");
        	itemResponse.setMessageType(0);
        	return itemResponse;
    	}
    	
    	AboutUsInfosView view = new AboutUsInfosView();
    		
    	copyAboutUsInfosToAboutUsInfosView(aboutUsInfos, view, df);
    		    	
    	itemResponse.setItem(view);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	return itemResponse;
    	
    }
    
  
    public void copyAboutUsInfosToAboutUsInfosView(AboutUsInfos info, AboutUsInfosView view, DateFormat df) {
  
		view.setDetails(info.getDetails());

		view.setFileName(info.getFileName());
		view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.ABOUT_US.name()+"/"+info.getFileName());
		
		view.setTitle(info.getTitle());
		view.setSerial(info.getSerial());
		view.setSubTitle(info.getSubTitle());
		view.setDetails(info.getDetails());
		view.setAboutUsId(info.getAboutUsId());
		view.setType(info.getType());

				
  }
    	



    
    }
