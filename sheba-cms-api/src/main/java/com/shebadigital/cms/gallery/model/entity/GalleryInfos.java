package com.shebadigital.cms.gallery.model.entity;

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
@Table(name = "gallery_infos")
public class GalleryInfos implements Serializable{

	
	
	private static final long serialVersionUID = 2952013730777906838L;

	@Id
	@Column(name = "gallery_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long galleryId;
	
	@Column(name = "serial")
	private Long serial;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "sub_title1")
	private String subTitle1;   
	
	@Column(name = "sub_title2")
	private String subTitle2;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "type")
	private String type;       // HOME_SLIDER, GALLERY_PHOTO
	
	@Column(name = "file_name")
	private String fileName;   // cmsid+_+type+_+serial.+extension
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "cms_id")
	private CmsInfo cmsInfo;

	public Long getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(Long galleryId) {
		this.galleryId = galleryId;
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

	public String getSubTitle1() {
		return subTitle1;
	}

	public void setSubTitle1(String subTitle1) {
		this.subTitle1 = subTitle1;
	}

	public String getSubTitle2() {
		return subTitle2;
	}

	public void setSubTitle2(String subTitle2) {
		this.subTitle2 = subTitle2;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
