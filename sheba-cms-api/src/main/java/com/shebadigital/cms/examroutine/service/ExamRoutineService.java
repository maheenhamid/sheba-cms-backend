package com.shebadigital.cms.examroutine.service;

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
import com.shebadigital.cms.common.CacheEvictService;
import com.shebadigital.cms.common.Folder;
import com.shebadigital.cms.common.HostInfo;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.examroutine.model.entity.ExamRoutine;
import com.shebadigital.cms.examroutine.model.request.ExamRoutineRequest;
import com.shebadigital.cms.examroutine.model.request.ExamRoutineUpdateRequest;
import com.shebadigital.cms.examroutine.model.response.ExamRoutineView;
import com.shebadigital.cms.examroutine.repository.ExamRoutineRepository;
import com.shebadigital.cms.file.service.FileStorageService;
import com.shebadigital.cms.file.util.FileUtils;
import com.shebadigital.cms.notice.model.entity.NoticeInfo;
import com.shebadigital.cms.notice.model.response.NoticeInfoView;
import com.shebadigital.cms.notice.service.NoticeService;

@Service
public class ExamRoutineService {
	
	
	public Logger logger = LoggerFactory.getLogger(ExamRoutineService.class);
	
	@Autowired
	public ExamRoutineRepository examRoutineRepository;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	
	@Transactional
	public BaseResponse saveExamRoutine(ExamRoutineRequest request) {
	
		BaseResponse baseResponse = new BaseResponse();
		
		Date date = new Date();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ExamRoutine examRoutine = new ExamRoutine();
		
		examRoutine.setClassName(request.getClassName());
		examRoutine.setCmsInfo(cmsInfo);
		examRoutine.setEndDate(request.getEndDate());
		examRoutine.setEntryDate(date);
		examRoutine.setExamName(request.getExamName());
		examRoutine.setRoutineHeader(request.getRoutineHeader());
		examRoutine.setRoutineStatus(1);
		examRoutine.setSerial(request.getSerial());
		examRoutine.setStartDate(request.getStartDate());

		if(request.isFileSave()) {
			
			 try {
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				  
	    		  String fileName=cmsInfo.getCmsId()+"_EXAMROUTINE_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  examRoutine.setFileName(fileName);
	    		  examRoutineRepository.save(examRoutine);
	    		  
	    		  baseResponse.setMessage("Exam Routine Successfully Saved.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		
		baseResponse.setMessage("Exam Routine Not Saved.");
		baseResponse.setMessageType(0);
		return baseResponse;
		
	}
	
	
	
	@Transactional
	public BaseResponse updateExamRoutine(ExamRoutineUpdateRequest request) {
	
		BaseResponse baseResponse = new BaseResponse();
		
		Date date = new Date();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ExamRoutine examRoutine = examRoutineRepository.findByExamRoutineIdAndCmsInfo(request.getExamRoutineId(), cmsInfo);
		
		if(examRoutine==null) {
		
		  baseResponse.setMessage("Exam Routine Not Found");
  		  baseResponse.setMessageType(0);
  		  return baseResponse;
		}
		
		examRoutine.setClassName(request.getClassName());
		examRoutine.setCmsInfo(cmsInfo);
		examRoutine.setEndDate(request.getEndDate());
		examRoutine.setModifyDate(date);
		examRoutine.setExamName(request.getExamName());
		examRoutine.setRoutineHeader(request.getRoutineHeader());
		examRoutine.setRoutineStatus(request.getRoutineStatus());
		examRoutine.setSerial(request.getSerial());
		examRoutine.setStartDate(request.getStartDate());

		if(request.isFileSave()) {
			
			 try {
				 
				  fileStorageService.deleteFile(Folder.NOTICE.name(), examRoutine.getFileName());
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(date);
				  
	    		  String fileName=cmsInfo.getCmsId()+"_EXAMROUTINE_"+filetime+"."+request.getFileType();
	    		  
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  
	    		  examRoutine.setFileName(fileName);
	    		  
	    	   }catch(Exception e) {
	    		   
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		
		baseResponse.setMessage("Exam Routine Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
		
	}
	
	
	
	@Transactional
	public BaseResponse deleteExamRoutine(Long examRoutineId) {
	
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ExamRoutine examRoutine = examRoutineRepository.findByExamRoutineIdAndCmsInfo(examRoutineId, cmsInfo);
		
		if(examRoutine==null) {
		
		  baseResponse.setMessage("No Exam Routine Found");
  		  baseResponse.setMessageType(0);
  		  return baseResponse;
		}
		
		fileStorageService.deleteFile(Folder.NOTICE.name(), examRoutine.getFileName());
		
		examRoutineRepository.delete(examRoutine);
				 		
		baseResponse.setMessage("Exam Routine Successfully Deleted.");
		baseResponse.setMessageType(1);
		return baseResponse;
		
	}
	
	
	

	public ItemResponse examRoutineList() {
	
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		
		List<ExamRoutine> examRoutines = examRoutineRepository.findByCmsInfoOrderByEndDateDescSerialAsc(cmsInfo);
		
		List<ExamRoutineView> views = new ArrayList<>();
		
		for(ExamRoutine info : examRoutines) {
			
			ExamRoutineView view = new ExamRoutineView();
			
			copyExamRoutineToExamRoutineView(info, view, df);
			
			views.add(view);

		}
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
		
	}

	public ItemResponse examRoutineList(Long cmsId) {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId); 
		
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		
		List<ExamRoutine> examRoutines = examRoutineRepository.findByCmsInfoOrderByEndDateDescSerialAsc(cmsInfo);
		
		List<ExamRoutineView> views = new ArrayList<>();
		
		for(ExamRoutine info : examRoutines) {
			
			ExamRoutineView view = new ExamRoutineView();
			
			copyExamRoutineToExamRoutineView(info, view, df);
			
			views.add(view);

		}
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
		
	}
	
	public void copyExamRoutineToExamRoutineView(ExamRoutine info,ExamRoutineView view,DateFormat df) {
		
		
		view.setExamRoutineId(info.getExamRoutineId());
		view.setClassName(info.getClassName());
		view.setExamName(info.getExamName());
		view.setFileName(info.getFileName());
		view.setRoutineHeader(info.getRoutineHeader());
		view.setRoutineStatus(info.getRoutineStatus());
		view.setRoutineStatusString(info.getRoutineStatus()==1 ? "Active" : "Inactive");
		view.setSerial(info.getSerial());
		
		if(info.getStartDate()!=null) {
		view.setStartDate(df.format(info.getStartDate()));	
		}
		
		if(info.getEndDate()!=null) {
			view.setEndDate(df.format(info.getEndDate()));	
		}
		
		view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
		view.setPdfFileViewer(HostInfo.HOST_URL+""+FileUtils.PDF_FILE_VIEWER+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
		view.setFolder(Folder.NOTICE.name());
	}
}
