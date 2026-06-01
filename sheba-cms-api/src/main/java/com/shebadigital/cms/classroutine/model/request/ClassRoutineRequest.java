package com.shebadigital.cms.classroutine.model.request;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClassRoutineRequest {


	private String routineHeader;
	
	private String className;
	
	private Integer year;

	private Integer serial;

	private byte[] fileContent;
	
	private boolean fileSave;
	
	private String fileType;
	
	
}
