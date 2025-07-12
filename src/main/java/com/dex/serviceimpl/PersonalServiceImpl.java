package com.dex.serviceimpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dex.dao.PersonalDao;
import com.dex.models.PersonalBean;
import com.dex.service.PersonalService;

@Transactional
@Service
public class PersonalServiceImpl implements PersonalService
{
    @Autowired
    private PersonalDao personalDao;
    
    @Override
    public PersonalBean insertPersonalDetails(PersonalBean personal, HttpServletRequest request) {
        return personalDao.insertPersonalDetails(personal, request);
    }
    
    @Override
    public PersonalBean getPersonalDetails(PersonalBean personal, HttpServletRequest request) {
        return personalDao.getPersonalDetails(personal.getUser_fk(), request);
    }
    
    @Override
    public PersonalBean getMenuStage(PersonalBean personal, HttpServletRequest request) {
        return personalDao.getMenuStage(personal.getUser_fk(), request);
    }
}