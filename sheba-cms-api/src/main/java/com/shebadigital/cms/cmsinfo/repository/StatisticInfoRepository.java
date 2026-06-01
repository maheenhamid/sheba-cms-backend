package com.shebadigital.cms.cmsinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.AboutUsInfos;
import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.cmsinfo.model.entity.StatisticInfo;

public interface StatisticInfoRepository extends JpaRepository<StatisticInfo, Long>{

	public List<StatisticInfo> findByCmsInfoOrderBySerial(CmsInfo cmsInfo);
	
	public StatisticInfo findByCmsInfoAndName(CmsInfo cmsInfo, String name);
	
	public StatisticInfo findByIdAndCmsInfo(Long id,CmsInfo cmsInfo);
}
