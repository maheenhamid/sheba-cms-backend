package com.shebadigital.cms.event.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventInfoView {

	
	private Long eventId;
	
	private Long serial;

	private String title;
	
	private String subTitle;
	
	private String details;
	
	private String issueDate;

	private String expiryDate;
	
	private Long eventStatus;
	
	private String eventStatusString;
	
	private String type;
}
