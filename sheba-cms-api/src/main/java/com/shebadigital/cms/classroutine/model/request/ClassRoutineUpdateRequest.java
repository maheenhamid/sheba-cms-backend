package com.shebadigital.cms.classroutine.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClassRoutineUpdateRequest {

	@NotNull
	private Long classRoutineId;
	
	private String routineHeader;
	
	private String className;
	
	@NotNull
	private Integer year;

	private Integer serial;
	
	@NotNull
	private Integer routineStatus;

	private byte[] fileContent;
	
	private boolean fileSave;
	
	private String fileType;
	
	
}
