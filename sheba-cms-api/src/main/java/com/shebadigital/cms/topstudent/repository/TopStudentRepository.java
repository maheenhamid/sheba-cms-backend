package com.shebadigital.cms.topstudent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.topstudent.model.entity.TopStudent;

public interface TopStudentRepository extends JpaRepository<TopStudent, Long>{
	
	public TopStudent findByStudentIdAndCmsInfo(Long studentId, CmsInfo cmsInfo);
	
	public List<TopStudent> findByCmsInfoOrderBySerial(CmsInfo cmsInfo);
	
	public List<TopStudent> findByCmsInfoAndShowStatusOrderBySerial(CmsInfo cmsInfo, Integer showStatus);

}
