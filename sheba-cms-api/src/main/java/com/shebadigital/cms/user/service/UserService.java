/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shebadigital.cms.user.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.common.UserInfoUtils;
import com.shebadigital.cms.user.model.dto.UserDto;
import com.shebadigital.cms.user.model.entity.UserRoles;
import com.shebadigital.cms.user.model.entity.Users;
import com.shebadigital.cms.user.model.request.PasswordChangeRequest;
import com.shebadigital.cms.user.model.request.UserCreateRequest;
import com.shebadigital.cms.user.model.request.UserUpdateRequest;
import com.shebadigital.cms.user.repository.UserRolesRepository;
import com.shebadigital.cms.user.repository.UsersRepository;

/**
 *
 * @author riad
 */
@Service
public class UserService {
    
    @Autowired
    public UsersRepository usersRepository;
    
    @Autowired
    public UserRolesRepository userRolesRepository;
    
    @Transactional
    public BaseResponse createUser(UserCreateRequest request){
       
        BaseResponse baseResponse=new BaseResponse();
        CmsInfo cmsInfo= UserInfoUtils.getLoggedInUserCms();
        Users users=usersRepository.findByUsername(request.getUsername());
        if(users!=null) {
        	baseResponse.setMessage("User Name= "+request.getUsername()+" already exists.");
        	baseResponse.setMessageType(0);
        	return baseResponse;
        }
        
        users=new Users();
        String encryptedPassword=UserInfoUtils.getHashPassword(request.getPassword());
        users.setEnabled(true);
        users.setPassword(encryptedPassword);
        users.setUsername(request.getUsername());
        users.setNickName(request.getNickName());
        users.setCmsInfo(cmsInfo);
        List<UserRoles> userRoles=new ArrayList<>();
        UserRoles roles;
        
        for(String r:request.getUserRoles()){
            roles=new UserRoles();
            roles.setUsername(request.getUsername());
            roles.setRoleName(r);
            roles.setCmsInfo(cmsInfo);
            userRoles.add(roles);
        }
        
        usersRepository.save(users);
        userRolesRepository.saveAll(userRoles);
        
        baseResponse.setMessage("User has been created succesfully");
        baseResponse.setMessageType(1);
        return baseResponse;
        
    }
    
    
    
    @Transactional
    public BaseResponse updateUser(UserUpdateRequest request){
       
        BaseResponse baseResponse=new BaseResponse();
        CmsInfo cmsInfo= UserInfoUtils.getLoggedInUserCms();
        Users users = usersRepository.getOne(request.getUserId());
       
        if(users==null) {
        	baseResponse.setMessage("No User Found.");
        	baseResponse.setMessageType(0);
        	return baseResponse;
        }
        
        users.setEnabled(request.isEnabled());
        users.setNickName(request.getNickName());
        users.setMobileNo(request.getMobileNo());
        
        List<UserRoles> userRoles = userRolesRepository.findByUsername(users.getUsername());
        
        userRolesRepository.deleteAll(userRoles);
        userRolesRepository.flush();
        
        List<UserRoles> newuserRoles=new ArrayList<>();
        UserRoles roles;
        
        for(String r: request.getUserRoles()){
            roles=new UserRoles();
            roles.setUsername(users.getUsername());
            roles.setRoleName(r);
            roles.setCmsInfo(cmsInfo);
            newuserRoles.add(roles);
        }
        
        usersRepository.save(users);
        userRolesRepository.saveAll(newuserRoles);
        
        baseResponse.setMessage("User has been update succesfully");
        baseResponse.setMessageType(1);
        return baseResponse;
        
    }
    
    
    @Transactional
    public BaseResponse changePassword(PasswordChangeRequest request){
       
        BaseResponse baseResponse=new BaseResponse();
        String username = UserInfoUtils.getLoggedInUser().getUsername();
        
        Users asolUser=usersRepository.findByUsername(username);
       
        if(asolUser==null) {
        	baseResponse.setMessage("No User Found.");
        	baseResponse.setMessageType(0);
        	return baseResponse;
        }
                
    
        
        if(UserInfoUtils.isPreviousPasswordCorrect(request.getCurrentPassword(), asolUser.getPassword())) {
        	String newEncriptedPassword = UserInfoUtils.getHashPassword(request.getNewPassword());
        	asolUser.setPassword(newEncriptedPassword);
        	baseResponse.setMessage("Password Succesfully Updated.");
            baseResponse.setMessageType(1);
        }else {
        	baseResponse.setMessage("Password Mismatched");
            baseResponse.setMessageType(0);
        }
        
        
        return baseResponse;
        
    }
    
    @Transactional
    public BaseResponse deleteUser(Long id){
       
        BaseResponse baseResponse=new BaseResponse();
        Users users=usersRepository.getOne(id);
        List<UserRoles> roleses=userRolesRepository.findByUsername(users.getUsername());
        
        usersRepository.delete(users);
        userRolesRepository.deleteInBatch(roleses);
        
        baseResponse.setMessage("User has been deleted succesfully");
        return baseResponse;
        
    }
    
    
    
    public List<String> userroles(List<UserRoles> roles,String username){
       
        List<String> userroles=new ArrayList<>();
        for(UserRoles r:roles){
          if(r.getUsername().equals(username)){
           userroles.add(r.getRoleName());
          }  
        }
        
        return userroles; 
    }
    
    public ItemResponse userList(){
        ItemResponse itemResponse=new ItemResponse();
        List<Users> users=usersRepository.findAll();
        List<UserRoles> roles=userRolesRepository.findAll();
        
        List<UserDto> userDtos=new ArrayList<>();
        UserDto userDto;
        for(Users u:users){
         userDto=new UserDto();
         userDto.setUserId(u.getId());
         userDto.setUsername(u.getUsername());
         userDto.setUserRoles(userroles(roles, u.getUsername()));
         userDtos.add(userDto);
        }
        
        itemResponse.setItem(userDtos);
        itemResponse.setMessage("OK");
        return itemResponse;
        
        
    }
     
}
