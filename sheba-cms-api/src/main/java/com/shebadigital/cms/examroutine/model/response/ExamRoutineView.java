package com.shebadigital.cms.examroutine.model.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExamRoutineView {


	private Long examRoutineId;
	
	private String routineHeader;
	
	private String examName;
	
	private String className;
	
	private String startDate;
	
	private String endDate;
	
	private Integer serial;
	
	private Integer routineStatus;
	
	private String routineStatusString;
	
	private String fileName;
	
	private String filePath;
	
	private String folder;
	
	private String pdfFileViewer;
}
