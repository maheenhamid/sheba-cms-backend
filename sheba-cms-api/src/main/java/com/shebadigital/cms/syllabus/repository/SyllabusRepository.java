package com.shebadigital.cms.syllabus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.syllabus.model.entity.Syllabus;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long>{
	
	public Syllabus findBySyllabusIdAndCmsInfo(Long syllabusId, CmsInfo cmsInfo);
	
	public List<Syllabus> findByCmsInfoOrderByYearAscSerialAsc(CmsInfo cmsInfo);
	
	public List<Syllabus> findByCmsInfoAndYearAndShowStatusOrderBySerial(CmsInfo cmsInfo,Integer year, Integer showStatus);
	
	

}
