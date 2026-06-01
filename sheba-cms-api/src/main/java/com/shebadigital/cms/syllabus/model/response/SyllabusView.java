package com.shebadigital.cms.syllabus.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyllabusView {

	private Long syllabusId;
	
	private String className;

	private String headerText;
	
	private Integer year;

	private String fileName;
	
	private String filePath;
	
	private Integer showStatus;
	
	private String showStatusString;
	
	private Integer serial;
	
	private String folder;
	
	private String pdfFileViewer;
	

}
