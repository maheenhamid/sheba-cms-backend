/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.cmsinfo.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author riad
 */


@Entity
@Table(name="cms_info")
public class CmsInfo implements  Serializable{
    
	
	private static final long serialVersionUID = 8918254557960452910L;

	@Id
    @Column(name="cms_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmsId;
    
    @Column(name="cms_name")
    private String cmsName;
    
    @Column(name="cms_status")
    private Integer cmsStatus;
    
    @Column(name="address")
    private String address;
    
    @Column(name="admin_name")
    private String adminName;
    
    @Column(name="contact_number")
    private String contactNumber;
    
    @Column(name="cms_email")
    private String cmsEmail;
    
    @Column(name="image_name")
    private String imageName;
    
    @Column(name="create_date")
    private Date createDate;
    
    @Column(name="details")
    private String details;
    
    @Column(name="file_name")
    private String fileName;
    
    @Column(name="file_path")
    private String filePath;
    
    @Column(name="web_site")
    private String webSite;
    
    @Column(name="link_facebook")
    private String linkFacebook;
    
    @Column(name="link_twitter")
    private String linkTwitter;
    
    @Column(name="link_linkedin")
    private String linkLinkedin;
    
    @Column(name="modified_date")
    private Date modifiedDate;
    
    @Column(name="modified_by")
    private String modifiedBy;
    
    
    @Column(name="sheba_institute_id")
    private String shebaInstituteId;
    
    
    @Column(name="sheba_institute_type")
    private String shebaInstituteType;
    
    
    @Column(name="banner_image")
    private String bannerImage;
    
    @Column(name="institute_approval_file_name")
    private String instituteApprovalFileName;
    
    
    @Column(name="school_college_admission_link")
    private String schoolCollegeAdmissionLink;
    
    
    @Column(name="university_admission_link")
    private String universityAdmissionLink;
    
    
    @Column(name="payment_link")
    private String paymentLink;
    
    
    @Column(name="primary_color")
    private String primaryColor;
    
    @Column(name="secondary_color")
    private String secondaryColor;
    
    @Column(name="third_color")
    private String thirdColor;
    
    @Column(name="forth_color")
    private String forthColor;
    
    @Column(name="fifth_color")
    private String fifthColor;
    
    

	public Long getCmsId() {
		return cmsId;
	}

	public void setCmsId(Long cmsId) {
		this.cmsId = cmsId;
	}

	public String getCmsName() {
		return cmsName;
	}

	public void setCmsName(String cmsName) {
		this.cmsName = cmsName;
	}

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCmsEmail() {
		return cmsEmail;
	}

	public void setCmsEmail(String cmsEmail) {
		this.cmsEmail = cmsEmail;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getCmsStatus() {
		return cmsStatus;
	}

	public void setCmsStatus(Integer cmsStatus) {
		this.cmsStatus = cmsStatus;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
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

	public String getShebaInstituteId() {
		return shebaInstituteId;
	}

	public void setShebaInstituteId(String shebaInstituteId) {
		this.shebaInstituteId = shebaInstituteId;
	}

	public String getShebaInstituteType() {
		return shebaInstituteType;
	}

	public void setShebaInstituteType(String shebaInstituteType) {
		this.shebaInstituteType = shebaInstituteType;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getSchoolCollegeAdmissionLink() {
		return schoolCollegeAdmissionLink;
	}

	public void setSchoolCollegeAdmissionLink(String schoolCollegeAdmissionLink) {
		this.schoolCollegeAdmissionLink = schoolCollegeAdmissionLink;
	}

	public String getUniversityAdmissionLink() {
		return universityAdmissionLink;
	}

	public void setUniversityAdmissionLink(String universityAdmissionLink) {
		this.universityAdmissionLink = universityAdmissionLink;
	}

	public String getPaymentLink() {
		return paymentLink;
	}

	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}

	public String getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}

	public String getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(String secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public String getThirdColor() {
		return thirdColor;
	}

	public void setThirdColor(String thirdColor) {
		this.thirdColor = thirdColor;
	}

	public String getForthColor() {
		return forthColor;
	}

	public void setForthColor(String forthColor) {
		this.forthColor = forthColor;
	}

	public String getFifthColor() {
		return fifthColor;
	}

	public void setFifthColor(String fifthColor) {
		this.fifthColor = fifthColor;
	}

	public String getInstituteApprovalFileName() {
		return instituteApprovalFileName;
	}

	public void setInstituteApprovalFileName(String instituteApprovalFileName) {
		this.instituteApprovalFileName = instituteApprovalFileName;
	}
    
    

   
    
    
    
}
