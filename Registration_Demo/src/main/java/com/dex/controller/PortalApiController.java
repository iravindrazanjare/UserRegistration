package com.dex.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dex.models.LoginBean;
import com.dex.models.PersonalBean;
import com.dex.models.RegistrationBean;
import com.dex.models.UserBean;
import com.dex.service.LoginService;
import com.dex.service.PersonalService;
import com.dex.service.RegistrationService;

@RestController
@RequestMapping({ "/api" })
public class PortalApiController
{
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PersonalService personalService;
    
    @PostMapping({ "/login" })
    private UserBean login(@RequestBody LoginBean login, HttpServletRequest request) {
        UserBean users = loginService.authenticateUser(login, request);
        if (users == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }
        return users;
    }
    
    @PostMapping({ "/load_register" })
    private ResponseEntity<Map<String, Map<Integer, String>>> registerLoad() {
    	ResponseEntity<Map<String, Map<Integer, String>>> referenceValue = registrationService.registerLoad();
        return referenceValue;
    }
    
    @PostMapping({ "/register" })
    private UserBean register(@RequestBody RegistrationBean register, HttpServletRequest request) {
        UserBean users = registrationService.register(register, request);
        System.out.println("Generated user id : " + users.getLoginId() + " password : " + users.getPassword());
        return users;
    }
    
    @PostMapping({ "/genOtp" })
    private RegistrationBean generateOtp(@RequestBody RegistrationBean register, HttpServletRequest request) {
         RegistrationBean rb = registrationService.generateOtp(register, request);
        return rb;
    }
    
    @PostMapping({ "/savePersonal" })
    private PersonalBean savePersonal(@RequestBody final PersonalBean personal, final HttpServletRequest request) {
        PersonalBean saveDetails = personalService.insertPersonalDetails(personal, request);
        return saveDetails;
    }
    
    @PostMapping({ "/getPersonal" })
    private PersonalBean getPersonal(@RequestBody final PersonalBean personal, final HttpServletRequest request) {
        PersonalBean retrievedDetails = personalService.getPersonalDetails(personal, request);
        return retrievedDetails;
    }
    
    @PostMapping({ "/menuStage" })
    private PersonalBean getMenuStage(@RequestBody final PersonalBean personal, final HttpServletRequest request) {
        PersonalBean candidateStage = personalService.getMenuStage(personal, request);
        return candidateStage;
    }
}