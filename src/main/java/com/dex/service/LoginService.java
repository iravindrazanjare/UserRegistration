package com.dex.service;

import javax.servlet.http.HttpServletRequest;

import com.dex.models.LoginBean;
import com.dex.models.UserBean;

public interface LoginService {

	UserBean authenticateUser(LoginBean login, HttpServletRequest request);
}
