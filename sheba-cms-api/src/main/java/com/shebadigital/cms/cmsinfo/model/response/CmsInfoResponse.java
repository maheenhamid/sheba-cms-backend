/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.cmsinfo.model.response;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

/**
 *
 * @author riad
 */
public class CmsInfoResponse {
    


    private Long cmsId;
    
    private String cmsName;
   
    private Integer cmsStatus;

    private String address;
   
    private String adminName;

    private String contactNumber;

    private String cmsEmail;
    
    private String imageName;
    
    private String imagelink;
    
    private String webSite;
    
    private String linkFacebook;
    
    private String linkTwitter;
    
    private String linkLinkedin;
    
    private String shebaInstituteId;
    
    private String shebaInstituteType;
    
    private String bannerName;
    
    private String bannerLink;
    
    private String schoolCollegeAdmissionLink;
    
    private String universityAdmissionLink;
    
    private String paymentLink;
    
    private String primaryColor;
    
    private String secondaryColor;
    
    private String thirdColor;
    
    private String forthColor;
    
    private String fifhColor;
    
    private String instituteApprovalFileLink;
    
    private String instituteApprovalFileName;
    

    
    
    public String getInstituteApprovalFileName() {
		return instituteApprovalFileName;
	}

	public void setInstituteApprovalFileName(String instituteApprovalFileName) {
		this.instituteApprovalFileName = instituteApprovalFileName;
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

	public String getImagelink() {
		return imagelink;
	}

	public void setImagelink(String imagelink) {
		this.imagelink = imagelink;
	}

	private String createDate;
    
    private String staffFullName;
    
    private String userName;
    
    private List<String> roleList;
    

   

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

	public Integer getCmsStatus() {
		return cmsStatus;
	}

	public void setCmsStatus(Integer cmsStatus) {
		this.cmsStatus = cmsStatus;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStaffFullName() {
        return staffFullName;
    }

    public void setStaffFullName(String staffFullName) {
        this.staffFullName = staffFullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
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

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public String getBannerLink() {
		return bannerLink;
	}

	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
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

	public String getFifhColor() {
		return fifhColor;
	}

	public void setFifhColor(String fifhColor) {
		this.fifhColor = fifhColor;
	}

	public String getInstituteApprovalFileLink() {
		return instituteApprovalFileLink;
	}

	public void setInstituteApprovalFileLink(String instituteApprovalFileLink) {
		this.instituteApprovalFileLink = instituteApprovalFileLink;
	}
    

    
}
