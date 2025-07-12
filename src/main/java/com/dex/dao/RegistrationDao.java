package com.dex.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.dex.models.RegistrationBean;
import com.dex.models.UserBean;

public interface RegistrationDao
{
    UserBean register(RegistrationBean register, HttpServletRequest request);
    
    int insertGenerateOtp(String mobileNo, int otp);
    
    ResponseEntity<Map<String, Map<Integer, String>>> registerLoad();
}