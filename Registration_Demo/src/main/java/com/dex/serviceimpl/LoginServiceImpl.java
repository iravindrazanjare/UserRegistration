package com.dex.serviceimpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dex.dao.LoginDao;
import com.dex.models.LoginBean;
import com.dex.models.UserBean;
import com.dex.service.LoginService;

@Transactional
@Service
public class LoginServiceImpl implements LoginService
{
    public UserBean loggedInUser;
    @Autowired
    private LoginDao loginDao;
    
    @Override
    public UserBean authenticateUser(LoginBean login, HttpServletRequest request) {
        UserBean users = loginDao.authenticateUser(login.getLoginId(), login.getPassword());
        setLoggedInUser(users);
        return users;
    }
    
    @Bean({ "loggedInUser" })
    @Scope("request")
    public void setLoggedInUser(UserBean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}