/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shebadigital.cms.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.user.model.entity.UserRoles;
import com.shebadigital.cms.user.model.entity.Users;
import com.shebadigital.cms.user.repository.UserRolesRepository;
import com.shebadigital.cms.user.repository.UsersRepository;

/**
 *
 * @author riad
 */

@Service
public class AuthorizationUserDetailsService implements UserDetailsService{
    
    @Autowired
    public UsersRepository usersRepository;
    
    @Autowired
    public UserRolesRepository userRolesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
                List<String> roles = new ArrayList<>(); 
                List<UserRoles> roleList = userRolesRepository.findByUsername(user.getUsername());
                roleList.stream().forEach((r) -> {
                roles.add(r.getRoleName());
            });
                user.setRoles(roles);
                return user;

            } else {
                throw new RuntimeException("user not found");
            }
        } 
    }
    
    

