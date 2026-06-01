package com.shebadigital.cms.link.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportantLinkView {

    private Long linkId;
	
	private Long serial;
	
	private String title;
	
	private String link;
	
	private Long linkStatus;
	
	private String linkStatusString;
	

}
