package com.shebadigital.cms.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.member.model.entity.MemberInfo;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long>{

	public MemberInfo findByMemberIdAndCmsInfo(Long memberId, CmsInfo cmsInfo);

	public List<MemberInfo> findByCmsInfo(CmsInfo cmsInfo);

	public List<MemberInfo> findByCmsInfoAndType(CmsInfo cmsInfo, String type);
	
	public List<MemberInfo> findByCmsInfoAndTypeOrderBySerial(CmsInfo cmsInfo, String type);
}
