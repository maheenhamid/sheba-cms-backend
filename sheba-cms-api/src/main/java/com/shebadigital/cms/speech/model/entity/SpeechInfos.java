package com.shebadigital.cms.speech.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.member.model.entity.MemberInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "speech_infos")
public class SpeechInfos implements Serializable{
	
	

	private static final long serialVersionUID = 8016210325651836177L;

	@Id
	@Column(name = "speech_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long speechId;
	
	@Column(name = "serial")
	private Long serial;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "speech_status")
	private Integer speechStatus;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "cms_id")
	private CmsInfo cmsInfo;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private MemberInfo memberInfo;
	
}
