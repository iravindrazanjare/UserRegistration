package com.dex.dao;

import javax.servlet.http.HttpServletRequest;

import com.dex.models.PersonalBean;

public interface PersonalDao
{
    PersonalBean insertPersonalDetails(PersonalBean personal, HttpServletRequest request);
    
    PersonalBean getPersonalDetails(String userFk, HttpServletRequest request);
    
    PersonalBean getMenuStage(String user_fk, HttpServletRequest request);
}
