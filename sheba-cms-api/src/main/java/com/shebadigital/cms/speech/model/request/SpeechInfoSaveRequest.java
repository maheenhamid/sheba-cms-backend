package com.shebadigital.cms.speech.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechInfoSaveRequest {

	@NotNull
    private Long serial;
	
	private String title;
	
	private String details;
	
	private Integer speechStatus;

	@NotNull
	private Long memberId;
	

	
	
}
