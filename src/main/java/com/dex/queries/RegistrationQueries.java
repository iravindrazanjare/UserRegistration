package com.dex.queries;

public class RegistrationQueries {

	public static String INSERT_NEW_USER;
    public static String INSERT_OTP_RECORD;
    public static String DELETE_EXISTING_OTP;
    
    static {
        RegistrationQueries.INSERT_NEW_USER = "Insert into dex_login_master (dex_login_id,dex_login_password,dex_first_name,dex_middle_name,dex_last_name,dex_created_date,dex_mobile_number,dex_gender,dex_date_of_birth,dex_email_id,dex_first_login) values (?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?,'Y')";
        RegistrationQueries.INSERT_OTP_RECORD = "Insert into dex_otp_master (dex_mob_no,dex_mob_otp,dex_verification_status,dex_created_date) values (?,?,?,CURRENT_TIMESTAMP)";
        RegistrationQueries.DELETE_EXISTING_OTP = "DELETE from dex_otp_master where dex_mob_no = ?";
    }
}
