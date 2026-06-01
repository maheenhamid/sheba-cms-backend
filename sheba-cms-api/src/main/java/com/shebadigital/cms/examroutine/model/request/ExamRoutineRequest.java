package com.shebadigital.cms.examroutine.model.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExamRoutineRequest {


	private String routineHeader;
	
	private String examName;
	
	private String className;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;

	private Integer serial;

	private byte[] fileContent;
	
	private boolean fileSave;
	
	private String fileType;
	
	
}
