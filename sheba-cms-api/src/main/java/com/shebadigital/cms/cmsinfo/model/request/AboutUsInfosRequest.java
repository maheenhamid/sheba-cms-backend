package com.shebadigital.cms.cmsinfo.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutUsInfosRequest {


	private Long serial;
	private String title;
	private String subTitle;
	private String details;
	private String type;
	
	private byte[] fileContent;
	private String fileType;
	private boolean fileSave;


}
