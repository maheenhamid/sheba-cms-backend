package com.shebadigital.cms.member.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoView {

	
	private Long memberId;
	
	private Long serial;
	
	private String type;
	
	private String gender;
	
	private String memberName;
	
	private String designation;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String details;
	
	private String linkFacebook;
	
	private String linkTwitter;
	
	private String linkLinkedin;
	
	private String age;
	
	private String hobby;
	
	private String fileName;
	
	private String filePath;
	
	private String joiningDate;
	
	private Integer memberStatus;
	
	private String memberStatusStr="Inactive";
}
