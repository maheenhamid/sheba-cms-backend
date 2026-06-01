package com.shebadigital.cms.notice.model.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notice_infos")
@Getter
@Setter
public class NoticeInfo implements Serializable{
	
	private static final long serialVersionUID = -2486555245267199641L;

	@Id
	@Column(name = "notice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noticeId;
	
	@Column(name = "serial")
	private Long serial;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "sub_title")
	private String subTitle;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "issue_date")
	@Temporal(TemporalType.DATE)
	private Date issueDate;
	
	@Column(name = "expiry_date")
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	@Column(name = "notice_status")
	private Integer noticeStatus;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "cms_id")
	private CmsInfo cmsInfo;

}
