package com.dex.queries;

public class LoginQueries {
	
    public static String FETCH_LOGGED_IN_USER_DETAILS;
    
    static {
        LoginQueries.FETCH_LOGGED_IN_USER_DETAILS = "Select * from dex_login_master where dex_login_id = ? and dex_login_password = ?";
    }

}
