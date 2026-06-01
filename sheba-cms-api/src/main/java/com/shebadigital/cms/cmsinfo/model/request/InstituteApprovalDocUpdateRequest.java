package com.shebadigital.cms.cmsinfo.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituteApprovalDocUpdateRequest {

	
    private byte[] fileContent;
	
	private String fileType;
	
	private boolean fileSave;
}
