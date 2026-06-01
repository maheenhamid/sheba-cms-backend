package com.shebadigital.cms.link.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.repository.CmsInfoRepository;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.link.model.entity.ImportantLinkInfos;
import com.shebadigital.cms.link.model.request.ImportantLinkRequest;
import com.shebadigital.cms.link.model.request.ImportantLinkUpdateRequest;
import com.shebadigital.cms.link.model.response.ImportantLinkView;
import com.shebadigital.cms.link.repository.ImportantLinkInfosRepository;


@Service
public class ImportantLinkService {
	
	@Autowired
	public CmsInfoRepository cmsInfoRepository;
	
	@Autowired
	public ImportantLinkInfosRepository importantLinkInfosRepository;

	@Transactional
	public BaseResponse saveImportantLinkInfo(ImportantLinkRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ImportantLinkInfos importantLinkInfos = new ImportantLinkInfos();
		
		importantLinkInfos.setCmsInfo(cmsInfo);
		importantLinkInfos.setCreatedAt(new Date());

		importantLinkInfos.setSerial(request.getSerial());

		importantLinkInfos.setTitle(request.getTitle());
		importantLinkInfos.setLink(request.getLink());
		importantLinkInfos.setLinkStatus(1l);
		
		importantLinkInfosRepository.save(importantLinkInfos);

		
		baseResponse.setMessage("Important Link Info Successfully Saved.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	

	@Transactional
	public BaseResponse updateImportantLinkInfo(ImportantLinkUpdateRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ImportantLinkInfos importantLinkInfos  =  importantLinkInfosRepository.findByLinkIdAndCmsInfo(request.getImportantLinkId(), cmsInfo);
		
		if(importantLinkInfos==null) {
		
			baseResponse.setMessage("Important Link Infos Not Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}

		importantLinkInfos.setSerial(request.getSerial());

		importantLinkInfos.setTitle(request.getTitle());
		importantLinkInfos.setLink(request.getLink());
		importantLinkInfos.setLinkStatus(request.getLinkStatus());
		
		baseResponse.setMessage("Event Infos Successfully Updated.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	
	
	
	@Transactional
	public BaseResponse deleteImportantLink(Long linkId) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		ImportantLinkInfos importantLinkInfos  =  importantLinkInfosRepository.findByLinkIdAndCmsInfo(linkId, cmsInfo);
		
		if(importantLinkInfos==null) {
			
			baseResponse.setMessage("Important Link Infos Not Found");
			baseResponse.setMessageType(0);
			return baseResponse;
		}
		
		importantLinkInfosRepository.delete(importantLinkInfos);
		
		baseResponse.setMessage("Important Link Infos Successfully Deleted.");
		baseResponse.setMessageType(1);
		return baseResponse;
	}
	

	public ItemResponse importantLinkList() {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = UserInfoUtils.getLoggedInUserCms();
		
		List<ImportantLinkInfos> importantLinkInfosList =  importantLinkInfosRepository.findByCmsInfo(cmsInfo);
		
		List<ImportantLinkView> views = new ArrayList<>();
		
		for(ImportantLinkInfos info : importantLinkInfosList) {
		
			ImportantLinkView view = new ImportantLinkView();
			
			view.setSerial(info.getSerial());
			view.setTitle(info.getTitle());
			view.setLink(info.getLink());
			view.setLinkId(info.getLinkId());
			view.setLinkStatus(info.getLinkStatus());
			
			if(info.getLinkStatus()==1) {
				view.setLinkStatusString("Active");	
			}else {
				view.setLinkStatusString("Inactive");
			}
			
			views.add(view);

		 }
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
	}
	

	
	public ItemResponse importantLinkList(Long cmsId) {
		
		ItemResponse itemResponse = new ItemResponse();
		
		CmsInfo cmsInfo = cmsInfoRepository.findByCmsId(cmsId);
		
		List<ImportantLinkInfos> importantLinkInfosList =  importantLinkInfosRepository.findByCmsInfo(cmsInfo);
		
		List<ImportantLinkView> views = new ArrayList<>();
		
		for(ImportantLinkInfos info : importantLinkInfosList) {
		
			ImportantLinkView view = new ImportantLinkView();
			
			view.setSerial(info.getSerial());
			view.setTitle(info.getTitle());
			view.setLink(info.getLink());
			view.setLinkId(info.getLinkId());
			view.setLinkStatus(info.getLinkStatus());
			
			if(info.getLinkStatus()==1) {
				view.setLinkStatusString("Active");	
			}else {
				view.setLinkStatusString("Inactive");
			}
			
			views.add(view);

		 }
		
		itemResponse.setItem(views);
		itemResponse.setMessage("OK");
		itemResponse.setMessageType(1);
		return itemResponse;
	}

}
