package com.shebadigital.cms.cmsinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.AboutUsInfos;
import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

public interface AboutUsInfosRepository extends JpaRepository<AboutUsInfos, Long>{
	
	public List<AboutUsInfos> findByCmsInfo(CmsInfo cmsInfo);
	
	public AboutUsInfos findByCmsInfoAndType(CmsInfo cmsInfo, String type);
	
	public AboutUsInfos findByAboutUsIdAndCmsInfo(Long aboutUsId,CmsInfo cmsInfo);

}
