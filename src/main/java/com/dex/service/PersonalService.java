package com.dex.service;

import javax.servlet.http.HttpServletRequest;

import com.dex.models.PersonalBean;

public interface PersonalService {

	 PersonalBean insertPersonalDetails(PersonalBean personal, HttpServletRequest request);
	    
	    PersonalBean getPersonalDetails(PersonalBean personal, HttpServletRequest request);
	    
	    PersonalBean getMenuStage(PersonalBean personal, HttpServletRequest request);
}
