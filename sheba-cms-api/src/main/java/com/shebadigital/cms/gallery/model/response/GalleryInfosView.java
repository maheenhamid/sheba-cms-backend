package com.shebadigital.cms.gallery.model.response;



public class GalleryInfosView {


	private Long galleryId;
	
	private Long serial;
	
	private String title;
	
	private String subTitle1;   

	private String subTitle2;
	
	private String details;

	private String type;       

	private String fileName;   
	
	private String filePath;

	private String createdAt="";
	
	private String updatedAt="";

	
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	
}
