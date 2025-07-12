package com.dex.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationBean
{
    private String loginid;
    private String password;
    private String initials;
    private int user_pk;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String nationality;
    private String gender;
    private String contact_number;
    private String alternate_number;
    private String mobileNo;
    private String email_id;
    private String dateofbirth;
    private int otp;
}
