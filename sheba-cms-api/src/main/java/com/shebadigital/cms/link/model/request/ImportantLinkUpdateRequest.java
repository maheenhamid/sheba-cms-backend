package com.shebadigital.cms.link.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportantLinkUpdateRequest {

	@NotNull
	private Long importantLinkId;
	
	private Long serial;
	
	private String title;

	private String link;

	private Long linkStatus;


	
	
	
}
