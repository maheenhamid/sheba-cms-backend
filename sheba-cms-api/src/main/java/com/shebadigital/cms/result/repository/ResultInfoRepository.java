package com.shebadigital.cms.result.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.result.model.entity.ResultInfo;

public interface ResultInfoRepository extends JpaRepository<ResultInfo, Long>{

	public ResultInfo findByResultIdAndCmsInfo(Long resultId, CmsInfo cmsInfo);
	
	public List<ResultInfo> findByCmsInfoAndResultStatusAndIssueDateLessThanEqualAndExpiryDateGreaterThanEqual(CmsInfo cmsInfo,Integer resultStatus, Date date1, Date date2 );
	
	public List<ResultInfo> findByCmsInfoOrderByIssueDateDescResultStatusDesc(CmsInfo cmsInfo);

	// native clause 
	
	@Query(value = "SELECT COUNT(result_id) FROM result_infos WHERE cms_id=?1", nativeQuery = true)
	public Long countResultId(Long cmsId);
}
