/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shebadigital.cms.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shebadigital.cms.common.BaseResponse;
import com.shebadigital.cms.common.ItemResponse;
import com.shebadigital.cms.user.model.dto.UserDto;
import com.shebadigital.cms.user.model.request.PasswordChangeRequest;
import com.shebadigital.cms.user.model.request.UserCreateRequest;
import com.shebadigital.cms.user.model.request.UserUpdateRequest;
import com.shebadigital.cms.user.service.UserService;

/**
 *
 * @author riad
 */

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    public UserService userService;
    
    @Autowired
    private TokenStore tokenStore;
    
    
    
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Valid UserCreateRequest request){
        BaseResponse baseResponse=userService.createUser(request);
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody @Valid UserUpdateRequest request){
        BaseResponse baseResponse=userService.updateUser(request);
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponse> deleteUser(@RequestParam Long id ){
        BaseResponse baseResponse=userService.deleteUser(id);
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity<ItemResponse> userList(){
        ItemResponse itemResponse=userService.userList();
        return new ResponseEntity<>(itemResponse, HttpStatus.OK);
        
    }
    
    
    @PostMapping(value = "/password/change")
    public ResponseEntity<BaseResponse> changeUserPassword(@RequestBody @Valid PasswordChangeRequest request){
    	BaseResponse itemResponse=userService.changePassword(request);
        return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> logout(HttpServletRequest request) {
        BaseResponse baseResponse=new BaseResponse();
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
            tokenStore.removeRefreshToken(refreshToken);
            tokenStore.removeAccessToken(accessToken);
            baseResponse.setMessage("Success");
            baseResponse.setMessageType(1);
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }
        baseResponse.setMessage("Failed");
        baseResponse.setMessageType(0);
        return new ResponseEntity<>(baseResponse,HttpStatus.UNAUTHORIZED);
    }
    
}
