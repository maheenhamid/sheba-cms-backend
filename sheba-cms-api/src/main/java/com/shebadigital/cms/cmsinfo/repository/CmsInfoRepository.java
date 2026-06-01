/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.cmsinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

/**
 *
 * @author riad
 */
public interface CmsInfoRepository extends JpaRepository<CmsInfo, Long>{
    
    public CmsInfo findByCmsName(String cmsName);
    
    public CmsInfo findByCmsId(Long cmsId);
    
    
    @Query(value = "select (case when max(cms_id) is not null then max(cms_id) else 0 end) from cms_info", nativeQuery=true)
    public Long findMaxCmsId();
}
