package com.shebadigital.cms.notice.service;

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

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.Folder;
import com.shebadigital.cms.common.HostInfo;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.file.service.FileStorageService;
import com.shebadigital.cms.file.util.FileUtils;
import com.shebadigital.cms.notice.model.entity.NoticeInfo;
import com.shebadigital.cms.notice.model.request.NoticeSaveRequest;
import com.shebadigital.cms.notice.model.request.NoticeUpdateRequest;
import com.shebadigital.cms.notice.model.response.NoticeInfoView;
import com.shebadigital.cms.notice.repository.NoticeInfosRepository;

@Service
public class NoticeService {
	
	
	public Logger logger = LoggerFactory.getLogger(NoticeService.class);

	@Autowired
	public NoticeInfosRepository noticeInfosRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Transactional
    public BaseResponse saveNoticeInfo(NoticeSaveRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		NoticeInfo noticeInfos = new NoticeInfo();
		noticeInfos.setCmsInfo(cmsInfo);
		noticeInfos.setCreatedAt(new Date());
		noticeInfos.setDetails(request.getDetails());
		noticeInfos.setSerial(request.getSerial());
		noticeInfos.setSubTitle(request.getSubTitle());
		noticeInfos.setTitle(request.getTitle());
		noticeInfos.setNoticeStatus(1);
		noticeInfos.setIssueDate(request.getIssueDate());
		noticeInfos.setExpiryDate(request.getExpiryDate());

		if(request.isFileSave()) {
			
			 try {
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				  
	    		  String fileName=cmsInfo.getCmsId()+"_NOTICE_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  noticeInfos.setFileName(fileName);
	    		  noticeInfosRepository.save(noticeInfos);
	    		  
	    		  baseResponse.setMessage("Notice Successfully Saved.");
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
    public BaseResponse updateNoticeInfo(NoticeUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		NoticeInfo noticeInfo = noticeInfosRepository.findByNoticeIdAndCmsInfo(request.getNoticeId(), cmsInfo);
		
		if(noticeInfo==null) {
			baseResponse.setMessage("No Notice Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		noticeInfo.setDetails(request.getDetails());
		noticeInfo.setSerial(request.getSerial());
		noticeInfo.setSubTitle(request.getSubTitle());
		noticeInfo.setTitle(request.getTitle());
		noticeInfo.setNoticeStatus(request.getNoticeStatus());
		noticeInfo.setIssueDate(request.getIssueDate());
		noticeInfo.setExpiryDate(request.getExpiryDate());
		noticeInfo.setUpdatedAt(new Date());

		if(request.isFileSave()) {
			
			 try {
				 
				  fileStorageService.deleteFile(Folder.NOTICE.name(), noticeInfo.getFileName());
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	    		  String fileName=cmsInfo.getCmsId()+"_NOTICE_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  noticeInfo.setFileName(fileName);
	    		  noticeInfosRepository.save(noticeInfo);
	    		  
	    		  baseResponse.setMessage("Notice Successfully Updated.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		baseResponse.setMessage("Notice Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    

    @Transactional
    public BaseResponse deleteNoticeInfo(Long noticeId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		NoticeInfo noticeInfos = noticeInfosRepository.findByNoticeIdAndCmsInfo(noticeId, cmsInfo);
		
		if(noticeInfos==null) {
			baseResponse.setMessage("No Notice Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}

		fileStorageService.deleteFile(Folder.NOTICE.name(), noticeInfos.getFileName());
		noticeInfosRepository.delete(noticeInfos);
				 
		
				
		baseResponse.setMessage("Notice Successfully Deleted..");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    
    
 
    public ItemResponse allNoticeInfos() {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	 
    	List<NoticeInfo> noticeInfos = noticeInfosRepository.findByCmsInfoOrderByIssueDateDescNoticeStatusDesc(cmsInfo);
		
    	List<NoticeInfoView> views = new ArrayList<>();
    	
    	for(NoticeInfo info : noticeInfos) {
    	
    		NoticeInfoView view = new NoticeInfoView();
    		
    		copyNoticeInfoToNoticeInfoView(info, view, df);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}
    
    

    public ItemResponse publicNoticeInfos(Long cmsId) {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date date=new Date();
    	 
    	List<NoticeInfo> noticeInfos = noticeInfosRepository.findByCmsInfoAndNoticeStatusAndIssueDateLessThanEqualAndExpiryDateGreaterThanEqualOrderByIssueDateDescSerialAsc(cmsInfo, 1, date, date);
		
    	List<NoticeInfoView> views = new ArrayList<>();
    	
    	for(NoticeInfo info : noticeInfos) {
    	
    		NoticeInfoView view = new NoticeInfoView();
    		
    		copyNoticeInfoToNoticeInfoView(info, view, df);
    		
    		views.add(view);
    	
    	}
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
    	return itemResponse;
	}
    
    public void copyNoticeInfoToNoticeInfoView(NoticeInfo info,NoticeInfoView view,DateFormat df) {
    
    	if(info.getCreatedAt()!=null) {
			view.setCreatedAt(df.format(info.getCreatedAt()));	
		}
		
		view.setDetails(info.getDetails());
		if(info.getExpiryDate()!=null) {
			view.setExpiryDate(df.format(info.getExpiryDate()));	
		}
		
		view.setFileName(info.getFileName());
		view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
		
		view.setFolder(Folder.NOTICE.name());
		
		if(info.getIssueDate()!=null) {
		view.setIssueDate(df.format(info.getIssueDate()));
		}
		view.setNoticeId(info.getNoticeId());
		
		if(info.getNoticeStatus()==1) {
		view.setNoticeStatus("Active");	
		}
		
		view.setTitle(info.getTitle());
		view.setSerial(info.getSerial());
		view.setSubTitle(info.getSubTitle());
		
		
		view.setNoticeStatusInt(info.getNoticeStatus());
		
		if(info.getUpdatedAt()!=null) {
			view.setUpdatedAt(df.format(info.getUpdatedAt()));	
		}
		
		view.setPdfFileViewer("");
		if(info.getFileName()!=null) {
			
			if(info.getFileName().contains(".pdf")) {
				view.setPdfFileViewer(HostInfo.HOST_URL+""+FileUtils.PDF_FILE_VIEWER+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
			}else {
				view.setPdfFileViewer(HostInfo.HOST_URL+""+FileUtils.IMAGE_FILE_VIEWER+"/"+Folder.NOTICE.name()+"/"+info.getFileName());

			}
		}
		
    }
}
