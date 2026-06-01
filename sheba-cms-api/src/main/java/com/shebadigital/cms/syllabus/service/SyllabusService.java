package com.shebadigital.cms.syllabus.service;

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
import com.shebadigital.cms.notice.service.NoticeService;
import com.shebadigital.cms.syllabus.model.entity.Syllabus;
import com.shebadigital.cms.syllabus.model.request.SyllabusSaveRequest;
import com.shebadigital.cms.syllabus.model.request.SyllabusUpdateRequest;
import com.shebadigital.cms.syllabus.model.response.SyllabusView;
import com.shebadigital.cms.syllabus.repository.SyllabusRepository;

@Service
public class SyllabusService {
	
	
	public Logger logger = LoggerFactory.getLogger(SyllabusService.class);

	@Autowired
	public NoticeInfosRepository noticeInfosRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Autowired
	public SyllabusRepository syllabusRepository; 
	
	@Transactional
    public BaseResponse saveSyllabus(SyllabusSaveRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		Syllabus syllabus = new Syllabus();
		syllabus.setCmsInfo(cmsInfo);
		syllabus.setEntryDate(new Date());
		syllabus.setSerial(request.getSerial());
		syllabus.setHeaderText(request.getHeaderText());
		syllabus.setShowStatus(1);
		syllabus.setYear(request.getYear());
		syllabus.setClassName(request.getClassName());

		if(request.isFileSave()) {
			
			 try {
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	    		  String fileName=cmsInfo.getCmsId()+"_SYLLABUS_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.SYLLABUS.name(), request.getFileContent(), fileName);
	    		  syllabus.setFileName(fileName);
	    		  syllabusRepository.save(syllabus);
	    		  baseResponse.setMessage("Syllabus Successfully Saved.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		
				
		 baseResponse.setMessage("Syllabus is not saved ");
		 baseResponse.setMessageType(0);
		 return baseResponse;
	}
    
    
		@Transactional
	    public BaseResponse updateSyllabus(SyllabusUpdateRequest request) {
			
			BaseResponse baseResponse = new BaseResponse();
			CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
			
			Syllabus syllabus = syllabusRepository.findBySyllabusIdAndCmsInfo(request.getSyllabusId(), cmsInfo);
		
			if(syllabus==null) {
				baseResponse.setMessage("No Syllabus Found ");
				baseResponse.setMessageType(0);
				return baseResponse;
			}
			
			syllabus.setSerial(request.getSerial());
			syllabus.setHeaderText(request.getHeaderText());
			syllabus.setShowStatus(request.getShowStatus());
			syllabus.setYear(request.getYear());
			syllabus.setClassName(request.getClassName());
	
			if(request.isFileSave()) {
				
				 try {
					 
					 fileStorageService.deleteFile(Folder.SYLLABUS.name(), syllabus.getFileName());
					 String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		    		 String fileName=cmsInfo.getCmsId()+"_SYLLABUS_"+filetime+"."+request.getFileType();
		    		 fileStorageService.writeFileToPath(Folder.SYLLABUS.name(), request.getFileContent(), fileName);
		    		 syllabus.setFileName(fileName);
		    
		    	   }catch(Exception e) {
		    		   logger.error(""+e.getLocalizedMessage()); 
		    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
		    		   baseResponse.setMessageType(0);
		    		   return baseResponse;
		    	   }
			}
			
					
			 baseResponse.setMessage("Syllabus successfully updated.");
			 baseResponse.setMessageType(1);
			 return baseResponse;
		}
    

   
		@Transactional
	    public BaseResponse deleteSyllabus(Long syllabusId) {
			
			BaseResponse baseResponse = new BaseResponse();
			
			CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
			
			Syllabus syllabus = syllabusRepository.findBySyllabusIdAndCmsInfo(syllabusId, cmsInfo);
		
			if(syllabus==null) {
				baseResponse.setMessage("No Syllabus Found ");
				baseResponse.setMessageType(0);
				return baseResponse;
			}
			
			fileStorageService.deleteFile(Folder.SYLLABUS.name(), syllabus.getFileName());
			syllabusRepository.delete(syllabus);
			
			 baseResponse.setMessage("Syllabus successfully deleted. ");
			 baseResponse.setMessageType(1);
			 return baseResponse;
		}
    
    
 
    public ItemResponse allSyllabusInfos() {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
    	 
    	List<Syllabus> syllabusInfos = syllabusRepository.findByCmsInfoOrderByYearAscSerialAsc(cmsInfo);
		
    	List<SyllabusView> views = new ArrayList<>();
    	
    	for(Syllabus info : syllabusInfos) {
    	
    		SyllabusView view = new SyllabusView();
    		
    		copySyllabusInfoToSyllabusInfoView(info, view);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}
    
    


    
    public ItemResponse allSyllabusInfos(Long cmsId) {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
    	
    	 
    	List<Syllabus> syllabusInfos = syllabusRepository.findByCmsInfoOrderByYearAscSerialAsc(cmsInfo);
		
    	List<SyllabusView> views = new ArrayList<>();
    	
    	for(Syllabus info : syllabusInfos) {
    	
    		SyllabusView view = new SyllabusView();
    		
    		copySyllabusInfoToSyllabusInfoView(info, view);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}
    
   
    public void copySyllabusInfoToSyllabusInfoView(Syllabus info,SyllabusView view) {
    
    	view.setSyllabusId(info.getSyllabusId());
    	view.setFileName(info.getFileName());
    	view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.SYLLABUS.name()+"/"+info.getFileName());
    	view.setPdfFileViewer(HostInfo.HOST_URL+""+FileUtils.PDF_FILE_VIEWER+"/"+Folder.SYLLABUS.name()+"/"+info.getFileName());
		view.setFolder(Folder.SYLLABUS.name());
    	
    	view.setHeaderText(info.getHeaderText());
    	view.setSerial(info.getSerial());
    	view.setShowStatus(info.getShowStatus());
    	view.setClassName(info.getClassName());
    	
    	if(info.getShowStatus()==1) {
    	view.setShowStatusString("Yes");	
    	}else {
    	view.setShowStatusString("No");	
    	}
    	
    	view.setYear(info.getYear());


    }

}
