package com.shebadigital.cms.classroutine.service;

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

import com.shebadigital.cms.classroutine.model.entity.ClassRoutine;
import com.shebadigital.cms.classroutine.model.request.ClassRoutineRequest;
import com.shebadigital.cms.classroutine.model.request.ClassRoutineUpdateRequest;
import com.shebadigital.cms.classroutine.model.response.ClassRoutineView;
import com.shebadigital.cms.classroutine.repository.ClassRoutineRepository;
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
public class ClassRoutineService {
	
	
	public Logger logger = LoggerFactory.getLogger(ClassRoutineService.class);

	@Autowired
	public NoticeInfosRepository noticeInfosRepository;
	
	@Autowired
	public ClassRoutineRepository classRoutineRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Transactional
    public BaseResponse saveRoutineInfo(ClassRoutineRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		Date date=new Date();
		
		ClassRoutine routineInfo = new ClassRoutine();
		routineInfo.setCmsInfo(cmsInfo);
		routineInfo.setSerial(request.getSerial());
		routineInfo.setRoutineHeader(request.getRoutineHeader());
		routineInfo.setRoutineStatus(1);
		routineInfo.setClassName(request.getClassName());
		
		routineInfo.setEntryDate(date);
		routineInfo.setYear(request.getYear());


		if(request.isFileSave()) {
			
			 try {
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(date);
				  
	    		  String fileName=cmsInfo.getCmsId()+"_CLASS_ROUTINE_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  routineInfo.setFileName(fileName);
	    		  classRoutineRepository.save(routineInfo);
	    		  
	    		  baseResponse.setMessage("Class Routine Successfully Saved.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}else {
		
		   baseResponse.setMessage("File can not be saved. ");
 		   baseResponse.setMessageType(0);
 		   return baseResponse;
		}
				
	
	}
    
    
	@Transactional
    public BaseResponse updateRoutineInfo(ClassRoutineUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ClassRoutine routineInfo = classRoutineRepository.findByClassRoutineIdAndCmsInfo(request.getClassRoutineId(), cmsInfo);
		
		if(routineInfo==null) {
			baseResponse.setMessage("No Routine Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		routineInfo.setSerial(request.getSerial());

		routineInfo.setSerial(request.getSerial());
		routineInfo.setRoutineHeader(request.getRoutineHeader());
		routineInfo.setRoutineStatus(request.getRoutineStatus());
		routineInfo.setClassName(request.getClassName());
		routineInfo.setYear(request.getYear());

		if(request.isFileSave()) {
			
			 try {
				 
				  fileStorageService.deleteFile(Folder.NOTICE.name(), routineInfo.getFileName());
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	    		  String fileName=cmsInfo.getCmsId()+"_CLASS_ROUTINE_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  routineInfo.setFileName(fileName);
	    		  classRoutineRepository.save(routineInfo);
	    		  
	    		  baseResponse.setMessage("Class Routine Successfully Updated.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		baseResponse.setMessage("Class Routine Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    

    @Transactional
    public BaseResponse deleteRoutineInfo(Long classRoutineId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ClassRoutine classRoutine = classRoutineRepository.findByClassRoutineIdAndCmsInfo(classRoutineId, cmsInfo);
		
		if(classRoutine==null) {
			baseResponse.setMessage("No Routine Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}

		fileStorageService.deleteFile(Folder.NOTICE.name(), classRoutine.getFileName());
		classRoutineRepository.delete(classRoutine);
				 				
		baseResponse.setMessage("Class Routine Successfully Deleted..");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    
    
 
    public ItemResponse allClassRoutineInfos() {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	 
    	List<ClassRoutine> classRoutineInfos = classRoutineRepository.findByCmsInfoOrderByYearAscSerialAsc(cmsInfo);
		
    	List<ClassRoutineView> views = new ArrayList<>();
    	
    	for(ClassRoutine info : classRoutineInfos) {
    	
    		ClassRoutineView view = new ClassRoutineView();
    		
    		copyClassRoutineInfoToClassRoutineInfoView(info, view, df);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}
    
    


    public ItemResponse allClassRoutineInfos(Long cmsId, Integer year) {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	 
    	List<ClassRoutine> classRoutineInfos = classRoutineRepository.findByCmsInfoAndYearAndRoutineStatusOrderBySerialAsc(cmsInfo, year, 1);
		
    	List<ClassRoutineView> views = new ArrayList<>();
    	
    	for(ClassRoutine info : classRoutineInfos) {
    	
    		ClassRoutineView view = new ClassRoutineView();
    		
    		copyClassRoutineInfoToClassRoutineInfoView(info, view, df);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}    
    
    
    
    public void copyClassRoutineInfoToClassRoutineInfoView(ClassRoutine info,ClassRoutineView view,DateFormat df) {
		
		view.setFileName(info.getFileName());
		view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
		view.setPdfFileViewer(HostInfo.HOST_URL+""+FileUtils.PDF_FILE_VIEWER+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
		view.setFolder(Folder.NOTICE.name());
	
		view.setClassRoutineId(info.getClassRoutineId());
		
		if(info.getRoutineStatus()==1) {
		view.setRoutineStatus(info.getRoutineStatus());
		view.setRoutineStatusString("Active");
		}else {
			view.setRoutineStatus(info.getRoutineStatus());
			view.setRoutineStatusString("Inactive");	
		}
		
		view.setRoutineHeader(info.getRoutineHeader());
		view.setSerial(info.getSerial());
		view.setYear(info.getYear());
		view.setClassName(info.getClassName());
		
	
    }
	
	
	

}
