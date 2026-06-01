package com.shebadigital.cms.member.model.request;

import java.util.Date;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoSaveRequest {


	@NotNull
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
	
	private byte[] fileContent;
	
	private String fileType;
	
	private boolean fileSave;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date joiningDate;
	
	@NotNull
	private Integer memberStatus;
	
}
