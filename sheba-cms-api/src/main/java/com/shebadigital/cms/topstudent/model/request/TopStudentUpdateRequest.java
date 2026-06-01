package com.shebadigital.cms.topstudent.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopStudentUpdateRequest {

	@NotNull
	private Long studentId;
	
	private String studentName;
	
	@NotNull
	private Long serial;
	
	@NotNull
	private Integer showStatus;

	private String fatherName;

	private String motherName;

	private String roll;

	private String examName;

	private Integer year;
	
	private String studentResult;	
	
	private String address;

	private byte[] fileContent;
	
	private String fileType;
	
	private boolean fileSave;
	
	
}
