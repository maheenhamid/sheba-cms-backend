package com.shebadigital.cms.speech.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechInfoResponse {


	private Long speechId;
	
	private Long serial;

	private String title;

	private String details;

	private Integer speechStatus;
	
	private String speechStatusString;

	private String memberName;
	
	private String memberDesignation;
	
	private String memberPhotoPath;
	
}
