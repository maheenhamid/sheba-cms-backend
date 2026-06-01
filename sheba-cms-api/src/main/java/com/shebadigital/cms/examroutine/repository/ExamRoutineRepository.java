package com.shebadigital.cms.examroutine.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.examroutine.model.entity.ExamRoutine;

public interface ExamRoutineRepository extends JpaRepository<ExamRoutine, Long>{
	
	public ExamRoutine findByExamRoutineIdAndCmsInfo(Long examRoutineId, CmsInfo cmsInfo);
	
	public List<ExamRoutine> findByCmsInfoAndEndDateGreaterThanEqualAndRoutineStatusOrderByEndDateDescSerialAsc(CmsInfo cmsInfo, Date date,Integer routineStatus);

	public List<ExamRoutine> findByCmsInfoOrderByEndDateDescSerialAsc(CmsInfo cmsInfo);

}
