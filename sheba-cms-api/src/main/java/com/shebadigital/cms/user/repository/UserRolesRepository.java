/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shebadigital.cms.user.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.user.model.entity.UserRoles;

/**
 *
 * @author riad
 */
public interface UserRolesRepository extends JpaRepository<UserRoles, Long>{

    public List<UserRoles> findByUsername(String username);
}
