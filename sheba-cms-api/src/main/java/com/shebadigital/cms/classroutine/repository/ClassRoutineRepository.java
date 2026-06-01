package com.shebadigital.cms.classroutine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.classroutine.model.entity.ClassRoutine;
import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

public interface ClassRoutineRepository extends JpaRepository<ClassRoutine, Long>{
	
	public ClassRoutine findByClassRoutineIdAndCmsInfo(Long classRoutineId, CmsInfo cmsInfo);
	
	
	public List<ClassRoutine> findByCmsInfoOrderByYearAscSerialAsc(CmsInfo cmsInfo);
	
	public List<ClassRoutine> findByCmsInfoAndYearAndRoutineStatusOrderBySerialAsc(CmsInfo cmsInfo, Integer year, Integer routineStatus);

}
