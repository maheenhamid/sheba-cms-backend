package com.shebadigital.cms.link.model.request;



public class ImportantLinkRequest {

	
	private Long serial;
	

	private String title;
	

	private String link;
	

	private Long linkStatus;


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
	
	
	
	
}
