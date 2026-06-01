package com.shebadigital.cms.syllabus.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyllabusUpdateRequest {

	@NotNull
	private Long syllabusId;

	private String className;

	private String headerText;
	
	@NotNull
	private Integer year;
	
	private Integer showStatus;

	@NotNull
	private Integer serial;
	
	private byte[] fileContent;
	
	private String fileType;
	
	private boolean fileSave;
	

}
