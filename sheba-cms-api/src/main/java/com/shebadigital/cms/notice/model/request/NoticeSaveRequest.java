package com.shebadigital.cms.notice.model.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeSaveRequest {

	@NotNull
	private Long serial;
	
	private String title;
	
	private String subTitle;
	
	private String details;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date issueDate;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date expiryDate;
	
	private byte[] fileContent;
	
	private String fileType;
	
	private boolean fileSave;
	
	
}
