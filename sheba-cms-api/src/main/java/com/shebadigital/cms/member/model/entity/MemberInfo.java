package com.shebadigital.cms.member.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

@Entity
@Table(name = "member_info")
public class MemberInfo implements Serializable{
	
	private static final long serialVersionUID = 4927718574935116510L;

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	
	@Column(name = "serial")
	private Long serial;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "member_name")
	private String memberName;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "link_facebook")
	private String linkFacebook;
	
	@Column(name = "link_twitter")
	private String linkTwitter;
	
	@Column(name = "link_linkedin")
	private String linkLinkedin;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "joining_date")
	private Date joiningDate;
	
	@Column(name = "member_status")
	private Integer memberStatus; // 0 for inactive, 1 for active
	
	@ManyToOne
	@JoinColumn(name = "cms_id", nullable = false)
	private CmsInfo cmsInfo;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getLinkFacebook() {
		return linkFacebook;
	}

	public void setLinkFacebook(String linkFacebook) {
		this.linkFacebook = linkFacebook;
	}

	public String getLinkTwitter() {
		return linkTwitter;
	}

	public void setLinkTwitter(String linkTwitter) {
		this.linkTwitter = linkTwitter;
	}

	public String getLinkLinkedin() {
		return linkLinkedin;
	}

	public void setLinkLinkedin(String linkLinkedin) {
		this.linkLinkedin = linkLinkedin;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public CmsInfo getCmsInfo() {
		return cmsInfo;
	}

	public void setCmsInfo(CmsInfo cmsInfo) {
		this.cmsInfo = cmsInfo;
	}
	
	
	
	

}
