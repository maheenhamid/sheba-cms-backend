/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shebadigital.cms.user.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;

/**
 *
 * @author riad
 */
@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "rolename"}))
public class UserRoles implements Serializable {

	private static final long serialVersionUID = 4521710211442387698L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "rolename")
    private String roleName;
    
    @ManyToOne
    @JoinColumn(name = "cms_id")
    private CmsInfo cmsInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public CmsInfo getCmsInfo() {
		return cmsInfo;
	}

	public void setCmsInfo(CmsInfo cmsInfo) {
		this.cmsInfo = cmsInfo;
	}

	
    
    
    
    
}
