package com.dex.models;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class UserBean
{
    private String loginId;
    private String password;
    private String user_fk;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String mobileNo;
    private String gender;
    private String dateofbirth;
    private String email_id;
    private int candidateStage;
    private Map<Integer, String> stateMap;
    private Map<String, Map<Integer, String>> referenceValue;
    
    public UserBean() {
        this.stateMap = new LinkedHashMap<Integer, String>();
        this.referenceValue = new LinkedHashMap<String, Map<Integer, String>>();
    }
    
    public UserBean(final String loginId, final String password) {
        this.stateMap = new LinkedHashMap<Integer, String>();
        this.referenceValue = new LinkedHashMap<String, Map<Integer, String>>();
        this.setLoginId(loginId);
        this.setPassword(password);
    }
}
