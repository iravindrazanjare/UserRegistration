package com.dex.dao;

import com.dex.models.UserBean;

public interface LoginDao
{
    UserBean authenticateUser(String loginId, String password);
}