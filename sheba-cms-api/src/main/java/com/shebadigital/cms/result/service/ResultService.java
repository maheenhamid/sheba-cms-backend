package com.shebadigital.cms.result.service;

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
import com.shebadigital.cms.result.model.entity.ResultInfo;
import com.shebadigital.cms.result.model.request.ResultSaveRequest;
import com.shebadigital.cms.result.model.request.ResultUpdateRequest;
import com.shebadigital.cms.result.model.response.ResultInfoView;
import com.shebadigital.cms.result.repository.ResultInfoRepository;

@Service
public class ResultService {
	
	
	public Logger logger = LoggerFactory.getLogger(ResultService.class);

	@Autowired
	public ResultInfoRepository resultInfoRepository;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Transactional
    public BaseResponse saveResultInfo(ResultSaveRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ResultInfo resultInfos = new ResultInfo();
		resultInfos.setCmsInfo(cmsInfo);
		resultInfos.setCreatedAt(new Date());
		resultInfos.setDetails(request.getDetails());
		resultInfos.setSerial(request.getSerial());
		resultInfos.setSubTitle(request.getSubTitle());
		resultInfos.setTitle(request.getTitle());
		resultInfos.setResultStatus(1);
		resultInfos.setIssueDate(request.getIssueDate());
		resultInfos.setExpiryDate(request.getExpiryDate());

		if(request.isFileSave()) {
			
			 try {
				 
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				  
	    		  String fileName=cmsInfo.getCmsId()+"_RESULT_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  resultInfos.setFileName(fileName);
	    		  resultInfoRepository.save(resultInfos);
	    		  
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
    public BaseResponse updateResultInfo(ResultUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ResultInfo resultInfo = resultInfoRepository.findByResultIdAndCmsInfo(request.getResultId(), cmsInfo);
		
		if(resultInfo==null) {
			baseResponse.setMessage("No Notice Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		resultInfo.setDetails(request.getDetails());
		resultInfo.setSerial(request.getSerial());
		resultInfo.setSubTitle(request.getSubTitle());
		resultInfo.setTitle(request.getTitle());
		resultInfo.setResultStatus(request.getResultStatus());
		resultInfo.setIssueDate(request.getIssueDate());
		resultInfo.setExpiryDate(request.getExpiryDate());
		resultInfo.setUpdatedAt(new Date());

		if(request.isFileSave()) {
			
			 try {
				 
				  fileStorageService.deleteFile(Folder.NOTICE.name(), resultInfo.getFileName());
				  String filetime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	    		  String fileName=cmsInfo.getCmsId()+"_RESULT_"+filetime+"."+request.getFileType();
	    		  fileStorageService.writeFileToPath(Folder.NOTICE.name(), request.getFileContent(), fileName);
	    		  resultInfo.setFileName(fileName);
	    		  resultInfoRepository.save(resultInfo);
	    		  
	    		  baseResponse.setMessage("Result Successfully Updated.");
	    		  baseResponse.setMessageType(1);
	    		  return baseResponse;
	    	   }catch(Exception e) {
	    		   logger.error(""+e.getLocalizedMessage()); 
	    		   baseResponse.setMessage(" "+e.getLocalizedMessage());
	    		   baseResponse.setMessageType(0);
	    		   return baseResponse;
	    	   }
		}
		
		baseResponse.setMessage("Result Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    

    @Transactional
    public BaseResponse deleteResultInfo(Long resultId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ResultInfo resultInfos = resultInfoRepository.findByResultIdAndCmsInfo(resultId, cmsInfo);
		
		if(resultInfos==null) {
			baseResponse.setMessage("No Result Info Found.");
			baseResponse.setMessageType(0);
			return baseResponse;
		}

		fileStorageService.deleteFile(Folder.NOTICE.name(), resultInfos.getFileName());
		resultInfoRepository.delete(resultInfos);
				 
		
				
		baseResponse.setMessage("Notice Successfully Deleted..");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
    
    
 
    public ItemResponse allResultInfos() {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	 
    	List<ResultInfo> resultInfos = resultInfoRepository.findByCmsInfoOrderByIssueDateDescResultStatusDesc(cmsInfo);
		
    	List<ResultInfoView> views = new ArrayList<>();
    	
    	for(ResultInfo info : resultInfos) {
    	
    		ResultInfoView view = new ResultInfoView();
    		
    		copyResultInfoToResultInfoView(info, view, df);
    		
    		views.add(view);
    	
    	}
    	
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
		return itemResponse;
	}
    
    

    public ItemResponse publicResultInfos(Long cmsId) {
		
    	ItemResponse itemResponse = new ItemResponse();
		
    	CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date date=new Date();
    	 
    	List<ResultInfo> resultInfos = resultInfoRepository.findByCmsInfoAndResultStatusAndIssueDateLessThanEqualAndExpiryDateGreaterThanEqual(cmsInfo, 1, date, date);
		
    	List<ResultInfoView> views = new ArrayList<>();
    	
    	for(ResultInfo info : resultInfos) {
    	
    		ResultInfoView view = new ResultInfoView();
    		
    		copyResultInfoToResultInfoView(info, view, df);
    		
    		views.add(view);
    	
    	}
    	
    	itemResponse.setItem(views);
    	itemResponse.setMessage("OK");
    	itemResponse.setMessageType(1);
    	
    	return itemResponse;
	}
    
    public void copyResultInfoToResultInfoView(ResultInfo info,ResultInfoView view,DateFormat df) {
    
    	if(info.getCreatedAt()!=null) {
			view.setCreatedAt(df.format(info.getCreatedAt()));	
		}
		
		view.setDetails(info.getDetails());
		if(info.getExpiryDate()!=null) {
			view.setExpiryDate(df.format(info.getExpiryDate()));	
		}
		
		view.setFileName(info.getFileName());
		view.setFilePath(HostInfo.HOST_URL+""+FileUtils.FILE_VIEW_API+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
		view.setPdfFileViewer(HostInfo.HOST_URL+""+FileUtils.PDF_FILE_VIEWER+"/"+Folder.NOTICE.name()+"/"+info.getFileName());
		view.setFolder(Folder.NOTICE.name());
		
		
		if(info.getIssueDate()!=null) {
		view.setIssueDate(df.format(info.getIssueDate()));
		}
		view.setResultId(info.getResultId());
		
		if(info.getResultStatus()==1) {
		view.setResultStatus("Active");	
		}
		
		view.setTitle(info.getTitle());
		view.setSerial(info.getSerial());
		view.setSubTitle(info.getSubTitle());
		
		view.setResultStatusInt(info.getResultStatus());
		
		if(info.getUpdatedAt()!=null) {
			view.setUpdatedAt(df.format(info.getUpdatedAt()));	
		}
    }
}
