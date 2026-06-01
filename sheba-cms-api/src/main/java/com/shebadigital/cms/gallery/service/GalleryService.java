package com.shebadigital.cms.gallery.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.CacheEvictService;
import com.shebadigital.cms.common.Folder;
import com.shebadigital.cms.common.HostInfo;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.file.service.FileStorageService;
import com.shebadigital.cms.file.util.FileUtils;
import com.shebadigital.cms.gallery.model.entity.GalleryInfos;
import com.shebadigital.cms.gallery.model.repository.GalleryInfosRepository;
import com.shebadigital.cms.gallery.model.request.GalleryInfoCreateRequest;
import com.shebadigital.cms.gallery.model.request.GalleryInfoUpdateRequest;
import com.shebadigital.cms.gallery.model.response.GalleryInfosView;

@Service
public class GalleryService {
	
	public Logger logger = LoggerFactory.getLogger(GalleryService.class);
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public GalleryInfosRepository galleryInfosRepository;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Autowired
	public CacheEvictService cacheEvictService;
	
	public BaseResponse saveGalleryInfo(GalleryInfoCreateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		GalleryInfos galleryInfos = new GalleryInfos();
		galleryInfos.setCmsInfo(cmsInfo);
		galleryInfos.setCreatedAt(new Date());
		galleryInfos.setDetails(request.getDetails());
		
		galleryInfos.setSerial(request.getSerial());
		galleryInfos.setSubTitle1(request.getSubTitle());
		galleryInfos.setSubTitle2(request.getSubTitle());
		galleryInfos.setTitle(request.getTitle());
		galleryInfos.setType(request.getType());

		if(request.isImageSave()) {
			
			 try {
				 
				  fileStorageService.deleteFile(Folder.GALLERY.name(), galleryInfos.getFileName());
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	    		  String imageName=cmsInfo.getCmsId()+"_"+galleryInfos.getType()+"_"+filetime+"."+request.getImageType();
	    		  fileStorageService.writeFileToPath(Folder.GALLERY.name(), request.getImageContent(), imageName);
	    		  galleryInfos.setFileName(imageName);
	    		  galleryInfosRepository.save(galleryInfos);
	    		  baseResponse.setMessage("Gallery Photo Successfully Saved.");
	    		  baseResponse.setMessageType(1);
	    		  
	    		  
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
				
		return baseResponse;
	}
	
	

	@Transactional
	public BaseResponse updateGalleryInfo(GalleryInfoUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		GalleryInfos galleryInfos = galleryInfosRepository.findByGalleryIdAndCmsInfo(request.getGalleryId(), cmsInfo);
		
		if(galleryInfos==null) {
		  baseResponse.setMessage("Gallery Info Not Found.");
   		  baseResponse.setMessageType(0);
   		  return baseResponse;
		}
		

		galleryInfos.setDetails(request.getDetails());
		galleryInfos.setSerial(request.getSerial());
		galleryInfos.setSubTitle1(request.getSubTitle());
		galleryInfos.setSubTitle2(request.getSubTitle());
		galleryInfos.setTitle(request.getTitle());
		galleryInfos.setType(request.getType());

		if(request.isImageSave()) {
			
			 try {
	    		  
				  fileStorageService.deleteFile(Folder.GALLERY.name(), galleryInfos.getFileName());
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				  String imageName=cmsInfo.getCmsId()+"_"+galleryInfos.getType()+"_"+filetime+"."+request.getImageType();
	    		  fileStorageService.writeFileToPath(Folder.GALLERY.name(), request.getImageContent(), imageName);
	    		  galleryInfos.setFileName(imageName);
	    		  
	    		 
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
				
		 baseResponse.setMessage("Gallery Photo Successfully Updated.");
		 baseResponse.setMessageType(1);
		 return baseResponse;
	}
	
	
	@Transactional
	public BaseResponse deleteGalleryInfo(Long galleryId) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		GalleryInfos galleryInfos = galleryInfosRepository.findByGalleryIdAndCmsInfo(galleryId, cmsInfo);
		
		if (galleryInfos == null) {
			baseResponse.setMessage("Gallery Info Not Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}

		try {
		
			fileStorageService.deleteFile(Folder.GALLERY.name(), galleryInfos.getFileName());
			galleryInfosRepository.delete(galleryInfos);

		} catch (Exception e) {
			logger.error(""+e.getLocalizedMessage());
		}
	
				
		 baseResponse.setMessage("Gallery Photo Successfully Deleted.");
		 baseResponse.setMessageType(1);
		 return baseResponse;
	}

	
	public ItemResponse viewAllGalleryInfos(CmsInfo cmsInfo) {
		
		ItemResponse itemResponse = new ItemResponse();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		List<GalleryInfos> galleryInfos = galleryInfosRepository.findByCmsInfoOrderBySerial(cmsInfo);
		
		List<GalleryInfosView> views = new ArrayList<>();
		
		for(GalleryInfos info : galleryInfos) {
			GalleryInfosView view = new GalleryInfosView();
			setGalleryInfosToGalleryInfosView(info, view, df);
			views.add(view);
		}
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		
		return itemResponse;
	}
	

	public ItemResponse viewAllGalleryInfos(Long cmsId,String type) {
		
		ItemResponse itemResponse = new ItemResponse();
		CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		List<GalleryInfos> galleryInfos = galleryInfosRepository.findByCmsInfoAndTypeOrderBySerial(cmsInfo, type);
		
		List<GalleryInfosView> views = new ArrayList<>();
		
		for(GalleryInfos info : galleryInfos) {
			GalleryInfosView view = new GalleryInfosView();
			setGalleryInfosToGalleryInfosView(info, view, df);
			views.add(view);
		}
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		
		return itemResponse;
	}
	
	public void setGalleryInfosToGalleryInfosView(GalleryInfos info,GalleryInfosView view, DateFormat df) {
		
		if(info.getCreatedAt()!=null) {
			view.setCreatedAt(df.format(info.getCreatedAt()));
		}
		
		if(info.getUpdatedAt()!=null) {
			view.setUpdatedAt(df.format(info.getUpdatedAt()));
		}
		
		view.setDetails(info.getDetails());
		view.setFileName(info.getFileName());
		view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.GALLERY.name()+"/"+info.getFileName());
		view.setGalleryId(info.getGalleryId());
		view.setSerial(info.getSerial());
		view.setSubTitle1(info.getSubTitle1());
		view.setSubTitle2(info.getSubTitle2());
		view.setTitle(info.getTitle());
		view.setType(info.getType());
	}
}
