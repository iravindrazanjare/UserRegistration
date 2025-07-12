package com.dex.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.dex.models.RegistrationBean;
import com.dex.models.UserBean;

public interface RegistrationService {
	 UserBean register(RegistrationBean register, HttpServletRequest request);
	    
	    RegistrationBean generateOtp(RegistrationBean register, HttpServletRequest request);
	    
	    ResponseEntity<Map<String, Map<Integer, String>>> registerLoad();
}
