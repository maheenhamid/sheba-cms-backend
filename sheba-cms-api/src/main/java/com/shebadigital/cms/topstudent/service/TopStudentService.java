package com.shebadigital.cms.topstudent.service;

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
import com.shebadigital.cms.notice.model.response.NoticeInfoView;
import com.shebadigital.cms.notice.service.NoticeService;
import com.shebadigital.cms.topstudent.model.entity.TopStudent;
import com.shebadigital.cms.topstudent.model.request.TopStudentRequest;
import com.shebadigital.cms.topstudent.model.request.TopStudentUpdateRequest;
import com.shebadigital.cms.topstudent.model.response.TopStudentView;
import com.shebadigital.cms.topstudent.repository.TopStudentRepository;

@Service
public class TopStudentService {
	
	public Logger logger = LoggerFactory.getLogger(TopStudentService.class);

	@Autowired
	public TopStudentRepository topStudentRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	
	@Transactional
	public BaseResponse saveTopStudent(TopStudentRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		Date date =new Date();
		
		TopStudent topStudent = new TopStudent();
		topStudent.setCmsInfo(cmsInfo);
		topStudent.setExamName(request.getExamName());
		topStudent.setFatherName(request.getFatherName());
		topStudent.setMotherName(request.getMotherName());
		topStudent.setInputDate(date);
		topStudent.setRoll(request.getRoll());
		topStudent.setSerial(request.getSerial());
		topStudent.setShowStatus(1);
		topStudent.setStudentName(request.getStudentName());
		topStudent.setStudentResult(request.getStudentResult());
		topStudent.setYear(request.getYear());
		topStudent.setAddress(request.getAddress());
	
		
		if(request.isFileSave()) {
			
			 try {
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(date);
				  
	    		  String fileName=cmsInfo.getCmsId()+"_STUDENT_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.STUDENT.name(), request.getFileContent(), fileName);
	    		  topStudent.setFileName(fileName);
	    		  topStudentRepository.save(topStudent);
	    		  
	    		  baseResponse.setMessage("Top Student Successfully Saved.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		
		
		baseResponse.setMessage("Top Student Not Saved.");
		baseResponse.setMessageType(0);
		return baseResponse;
	}
	
	
	

	@Transactional
	public BaseResponse updateTopStudent(TopStudentUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		Date date =new Date();
		
		TopStudent topStudent = topStudentRepository.findByStudentIdAndCmsInfo(request.getStudentId(), cmsInfo);
		
		if(topStudent==null) {
		
			baseResponse.setMessage("No Student Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		
		topStudent.setExamName(request.getExamName());
		topStudent.setFatherName(request.getFatherName());
		topStudent.setMotherName(request.getMotherName());
		topStudent.setInputDate(date);
		topStudent.setRoll(request.getRoll());
		topStudent.setSerial(request.getSerial());
		topStudent.setShowStatus(1);
		topStudent.setStudentName(request.getStudentName());
		topStudent.setStudentResult(request.getStudentResult());
		topStudent.setYear(request.getYear());
		topStudent.setAddress(request.getAddress());
		topStudent.setModifyDate(date);
	
		
		if(request.isFileSave()) {
			
			 try {				 
				  
				  fileStorageService.deleteFile(Folder.STUDENT.name(), topStudent.getFileName());
				  
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(date);
				  
	    		  String fileName=cmsInfo.getCmsId()+"_STUDENT_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.STUDENT.name(), request.getFileContent(), fileName);
	    		  topStudent.setFileName(fileName);

	    		  
	    		  
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		
		
		baseResponse.setMessage("Top Student Successfully Saved.");
		  baseResponse.setMessageType(1);
		  return baseResponse;
	}
	
	
	@Transactional
	public BaseResponse deleteTopStudent(Long studentId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();

		TopStudent topStudent = topStudentRepository.findByStudentIdAndCmsInfo(studentId, cmsInfo);
		
		if(topStudent==null) {
		
			baseResponse.setMessage("No Student Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
			 
		fileStorageService.deleteFile(Folder.STUDENT.name(), topStudent.getFileName());
				  
		topStudentRepository.delete(topStudent);
	    		  
		baseResponse.setMessage("Top Student Successfully Deleted.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	
	

	public ItemResponse TopStudentInfos() {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
    	
     	List<TopStudent> topStudents = topStudentRepository.findByCmsInfoOrderBySerial(cmsInfo);
		
    	List<TopStudentView> views = new ArrayList<>();
    	
    	for(TopStudent info : topStudents) {
    	
    		TopStudentView view = new TopStudentView();
    		
    		copyTopStudentInfoToTopStudentInfoView(info, view);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}
	

	public ItemResponse TopStudentInfos(Long cmsId) {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
    	
     	List<TopStudent> topStudents = topStudentRepository.findByCmsInfoOrderBySerial(cmsInfo);
		
    	List<TopStudentView> views = new ArrayList<>();
    	
    	for(TopStudent info : topStudents) {
    	
    		TopStudentView view = new TopStudentView();
    		
    		copyTopStudentInfoToTopStudentInfoView(info, view);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}
	
	
	
	 public void copyTopStudentInfoToTopStudentInfoView(TopStudent info,TopStudentView view) {
	    
		view.setFileName(info.getFileName());
		view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.STUDENT.name()+"/"+info.getFileName());

		view.setStudentId(info.getStudentId());
		
		if(info.getShowStatus()==1) {
		view.setShowStatus(info.getShowStatus());	
		view.setShowStatusString("yes");
		}else {
		view.setShowStatus(info.getShowStatus());	
		view.setShowStatusString("no");	
		}
		
		view.setAddress(info.getAddress());
		view.setExamName(info.getExamName());
		view.setFatherName(info.getFatherName());
		view.setFileName(info.getFileName());

		view.setMotherName(info.getMotherName());
		view.setRoll(info.getRoll());
		view.setSerial(info.getSerial());
		view.setStudentId(info.getStudentId());
		view.setStudentName(info.getStudentName());
		view.setStudentResult(info.getStudentResult());
		view.setYear(info.getYear());
		
    }
	
}
