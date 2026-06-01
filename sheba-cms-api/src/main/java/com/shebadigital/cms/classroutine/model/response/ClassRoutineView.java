package com.shebadigital.cms.classroutine.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRoutineView {

	
    private Long classRoutineId;

	private String routineHeader;
	
	private String className;
	
	private Integer year;
	
	private Integer serial;
	
	private Integer routineStatus;
	
	private String routineStatusString;
	
	private String fileName;
	
	private String filePath;
	
	private String folder;
	
	private String pdfFileViewer;
	

}
