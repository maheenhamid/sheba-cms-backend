/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shebadigital.cms.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shebadigital.cms.user.model.entity.Users;

/**
 *
 * @author riad
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    public Users findByUsername(String username);
    
    
}
