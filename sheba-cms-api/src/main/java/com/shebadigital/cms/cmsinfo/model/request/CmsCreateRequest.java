/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shebadigital.cms.cmsinfo.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author riad
 */
@Getter
@Setter
public class CmsCreateRequest {
    

    @NotNull
    private String cmsName;
    
    @NotNull
    private String address;
    
    
    private String cmsEmail;
    
    
    private String cmsContactNumber;

    @NotNull
    private String adminName;
    
    
    @NotNull
    private String religion;
    
    @NotNull
    private String mobileNumber;
    
    @NotNull
    private String userName;
    
    @NotNull
    private String password;

    private String webSite;

    
}
