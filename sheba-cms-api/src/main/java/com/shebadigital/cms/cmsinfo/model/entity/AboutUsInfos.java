package com.shebadigital.cms.cmsinfo.model.entity;

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
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "about_us_infos", uniqueConstraints = @UniqueConstraint(columnNames = { "cms_id", "type" }))
public class AboutUsInfos implements Serializable{
	
	private static final long serialVersionUID = -4092832750950563372L;

	@Id
	@Column(name = "about_us_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aboutUsId;
	
	@Column(name = "serial")
	private Long serial;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "sub_title")
	private String subTitle;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@Column(name = "updated_at")
	@Temporal(TemporalType.DATE)
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "cms_id")
	private CmsInfo cmsInfo;

	
	
	
	
	
}
