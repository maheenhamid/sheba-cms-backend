package com.shebadigital.cms.notice.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeInfoView {

    private Long noticeId;

	private Long serial;
	
	private String title;
	
	private String subTitle;
	
	private String details;
	
	private String issueDate="";
	
	private String expiryDate="";

	private String noticeStatus="Inactive";
	
	private Integer noticeStatusInt;

	private String fileName;
	
	private String folder;
	
	private String pdfFileViewer;
	
	private String filePath;
	
	
	
	private String createdAt="";
	
	private String updatedAt="";
}
