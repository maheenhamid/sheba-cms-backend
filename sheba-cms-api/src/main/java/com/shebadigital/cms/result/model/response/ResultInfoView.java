package com.shebadigital.cms.result.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultInfoView {

    private Long resultId;

	private Long serial;
	
	private String title;
	
	private String subTitle;
	
	private String details;
	
	private String issueDate="";
	
	private String expiryDate="";

	private String resultStatus="Inactive";
	
	private Integer resultStatusInt;

	private String fileName;
	
	private String filePath;
	
	private String folder;
	
	private String pdfFileViewer;
	
	private String createdAt="";
	
	private String updatedAt="";
}
