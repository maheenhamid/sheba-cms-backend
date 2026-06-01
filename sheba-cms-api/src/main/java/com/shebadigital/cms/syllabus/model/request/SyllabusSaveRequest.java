package com.shebadigital.cms.syllabus.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyllabusSaveRequest {


	private String className;

	private String headerText;
	
	private Integer year;

	private Integer serial;
	
	private byte[] fileContent;
	
	private String fileType;
	
	private boolean fileSave;
	

}
