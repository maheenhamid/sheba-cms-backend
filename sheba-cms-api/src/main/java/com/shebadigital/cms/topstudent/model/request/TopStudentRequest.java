package com.shebadigital.cms.topstudent.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopStudentRequest {

	
	private String studentName;
	
	private Long serial;

	private String fatherName;

	private String motherName;

	private String roll;

	private String examName;
	
	private String address;

	private Integer year;
	
	private String studentResult;	

	private byte[] fileContent;
	
	private String fileType;
	
	private boolean fileSave;
	
	
}
