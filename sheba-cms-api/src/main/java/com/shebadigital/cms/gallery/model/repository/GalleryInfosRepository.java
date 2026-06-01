package com.shebadigital.cms.gallery.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.gallery.model.entity.GalleryInfos;

public interface GalleryInfosRepository extends JpaRepository<GalleryInfos, Long>{

	public GalleryInfos findByGalleryIdAndCmsInfo(Long galleryId, CmsInfo cmsInfo);
	
	public List<GalleryInfos> findByCmsInfoOrderBySerial(CmsInfo cmsInfo);
	
	public List<GalleryInfos> findByCmsInfoAndTypeOrderBySerial(CmsInfo cmsInfo, String type);
}
