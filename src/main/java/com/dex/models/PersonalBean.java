package com.dex.models;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PersonalBean
{
    private String user_fk;
    private String loginId;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String gender;
    private String mobileNo;
    private String email_id;
    private String dateofbirth;
    private String addressline1;
    private String addressline2;
    private String city;
    private int state;
    private int candidateStage;
    private int pincode;
    private Map<Integer, String> stateMap;
    private Map<String, Map<Integer, String>> referenceValue;
    
    public PersonalBean() {
        this.stateMap = new LinkedHashMap<Integer, String>();
        this.referenceValue = new LinkedHashMap<String, Map<Integer, String>>();
    }
 }
