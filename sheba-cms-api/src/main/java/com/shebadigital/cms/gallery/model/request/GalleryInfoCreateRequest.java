package com.shebadigital.cms.gallery.model.request;

import javax.validation.constraints.NotNull;

public class GalleryInfoCreateRequest {

	@NotNull
	private Long serial;
	
	private String title;
	
	private String subTitle;
	
	private String details;
	
	private String type; // Home Slider,Gallery Photo 
	
	private byte[] imageContent;
    
    private String imageType;
	
	private boolean imageSave;
	

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

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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

	public byte[] getImageContent() {
		return imageContent;
	}

	public void setImageContent(byte[] imageContent) {
		this.imageContent = imageContent;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public boolean isImageSave() {
		return imageSave;
	}

	public void setImageSave(boolean imageSave) {
		this.imageSave = imageSave;
	}
	
	
	
	
}
