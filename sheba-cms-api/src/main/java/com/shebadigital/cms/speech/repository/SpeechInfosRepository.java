package com.shebadigital.cms.speech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.speech.model.entity.SpeechInfos;

public interface SpeechInfosRepository extends JpaRepository<SpeechInfos, Long>{
	
	public SpeechInfos findBySpeechIdAndCmsInfo(Long speechId, CmsInfo cmsInfo);
	
	public List<SpeechInfos> findByCmsInfoOrderBySerial(CmsInfo cmsInfo);
	
	public List<SpeechInfos> findByCmsInfoAndSpeechStatusOrderBySerial(CmsInfo cmsInfo,Integer speechStatus);

}
