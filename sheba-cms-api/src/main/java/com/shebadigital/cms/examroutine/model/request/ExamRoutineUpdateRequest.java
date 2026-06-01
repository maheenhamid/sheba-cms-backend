package com.shebadigital.cms.examroutine.model.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExamRoutineUpdateRequest {

	@NotNull
	private Long examRoutineId;
	
	private String routineHeader;
	
	private String examName;
	
	private String className;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;

	private Integer serial;
	
	@NotNull
	private Integer routineStatus;

	private byte[] fileContent;
	
	private boolean fileSave;
	
	private String fileType;
	
	
}
