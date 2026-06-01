package com.shebadigital.cms.link.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.link.model.entity.ImportantLinkInfos;

public interface ImportantLinkInfosRepository extends JpaRepository<ImportantLinkInfos, Long>{

	ImportantLinkInfos findByLinkIdAndCmsInfo(Long importantLinkId, CmsInfo cmsInfo);

	List<ImportantLinkInfos> findByCmsInfo(CmsInfo cmsInfo);

}
