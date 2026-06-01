package com.shebadigital.cms.link.model.entity;

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

@Entity
@Table(name = "important_link_infos")
public class ImportantLinkInfos implements Serializable{
	
	private static final long serialVersionUID = -461218361815736585L;

	@Id
	@Column(name = "link_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long linkId;
	
	@Column(name = "serial")
	private Long serial;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "link_status")
	private Long linkStatus;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "cms_id")
	private CmsInfo cmsInfo;

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(Long linkStatus) {
		this.linkStatus = linkStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CmsInfo getCmsInfo() {
		return cmsInfo;
	}

	public void setCmsInfo(CmsInfo cmsInfo) {
		this.cmsInfo = cmsInfo;
	}
	
	
	

}
