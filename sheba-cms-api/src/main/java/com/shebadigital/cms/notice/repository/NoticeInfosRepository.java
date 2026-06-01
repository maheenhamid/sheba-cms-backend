package com.shebadigital.cms.notice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.notice.model.entity.NoticeInfo;

public interface NoticeInfosRepository extends JpaRepository<NoticeInfo, Long>{
	
	public NoticeInfo findByNoticeIdAndCmsInfo(Long noticeId, CmsInfo cmsInfo);
	
	public List<NoticeInfo> findByCmsInfoAndNoticeStatusAndIssueDateLessThanEqualAndExpiryDateGreaterThanEqual(CmsInfo cmsInfo,Integer noticeStatus, Date date1, Date date2 );
	
	public List<NoticeInfo> findByCmsInfoAndNoticeStatusAndIssueDateLessThanEqualAndExpiryDateGreaterThanEqualOrderBySerial(CmsInfo cmsInfo,Integer noticeStatus, Date date1, Date date2 );
	
	public List<NoticeInfo> findByCmsInfoOrderByIssueDateDescNoticeStatusDesc(CmsInfo cmsInfo);

	// native clause 
	
	@Query(value = "SELECT COUNT(notice_id) FROM notice_infos WHERE cms_id=?1", nativeQuery = true)
	public Long countNoticeId(Long cmsId);

	public List<NoticeInfo> findByCmsInfoAndNoticeStatusAndIssueDateLessThanEqualAndExpiryDateGreaterThanEqualOrderByIssueDateDescSerialAsc(CmsInfo cmsInfo, Integer noticeStatus, Date date, Date date2);

}
