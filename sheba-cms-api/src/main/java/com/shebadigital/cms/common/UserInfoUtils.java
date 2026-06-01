package com.shebadigital.cms.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shebadigital.cms.cmsinfo.model.entity.CmsInfo;
import com.shebadigital.cms.user.model.entity.Users;



@Component
public class UserInfoUtils {
		
	
	 private static ApplicationContext context;

	    @Autowired
	    public UserInfoUtils(ApplicationContext ac) {
	        context = ac;
	    }

	    public static ApplicationContext getContext() {
	        return context;
	    }
	    
	    
	     public static String getHashPassword(String password) {
	        PasswordEncoder userPasswordEncoder = context.getBean("userPasswordEncoder", PasswordEncoder.class);
	        return userPasswordEncoder.encode(password);
	    }
	    
	    public static boolean isPreviousPasswordCorrect(String previousPlainPassword,String previousEncriptedPassword) {
	        PasswordEncoder userPasswordEncoder = context.getBean("userPasswordEncoder", PasswordEncoder.class);
	       return userPasswordEncoder.matches(previousPlainPassword, previousEncriptedPassword);
	    }
	    
	    
	    public static String getLoggedInUserName() {
	       
	    	try {
	    		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    		return user.getUsername();
	    	}
	        
	    	catch(Exception e) {
	    		System.out.println(e.getLocalizedMessage());
	    		return "Unauthorized";
	    	}
	    }
	    
	    public static Users getLoggedInUser() {
	        
	        try {
	        	Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	        return user;
	    	}
	    	catch(Exception e) {
	    		System.out.println(e.getLocalizedMessage());
	    		return new Users();
	    	}

	    }
	    
	    
	    public static CmsInfo getLoggedInUserCms() {
	       
	        try {
	        	Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	        return user.getCmsInfo();
	    	}
	    	catch(Exception e) {
	    		System.out.println(e.getLocalizedMessage());
	    		return new CmsInfo();
	    	}

	    }
	    
  


}
