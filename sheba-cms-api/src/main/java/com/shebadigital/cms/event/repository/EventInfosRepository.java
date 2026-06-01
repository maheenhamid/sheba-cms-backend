package com.shebadigital.cms.event.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.event.model.entity.EventInfos;

public interface EventInfosRepository extends JpaRepository<EventInfos, Long>{

	EventInfos findByEventIdAndCmsInfo(Long eventId, CmsInfo cmsInfo);
	
	List<EventInfos> findByCmsInfoOrderByExpiryDateDesc(CmsInfo cmsInfo);
	

	List<EventInfos> findByCmsInfoAndExpiryDateGreaterThanEqualOrderByExpiryDateDesc(CmsInfo cmsInfo,Date today);

}
