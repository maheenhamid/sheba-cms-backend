package com.shebadigital.cms.speech.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechInfoUpdateRequest {

	@NotNull
	private Long speechId;
	
	@NotNull
    private Long serial;
	
	@NotNull
	private String title;
	
	@NotNull
	private String details;
	
	@NotNull
	private Integer speechStatus;

	

	
	
}
