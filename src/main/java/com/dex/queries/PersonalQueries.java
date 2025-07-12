package com.dex.queries;

public class PersonalQueries {

	public static String GET_CANDIDATE_PERSONAL_RECORD;
    public static String INSERT_PERSONAL_DETAILS;
    public static String UPDATE_PERSONAL_DETAILS;
    public static String CHECK_IF_RECORD_EXISTS;
    
    static {
        PersonalQueries.GET_CANDIDATE_PERSONAL_RECORD = "select * from dex_personal_details where dex_user_fk = ?";
        PersonalQueries.INSERT_PERSONAL_DETAILS = "Insert into dex_personal_details (dex_cand_user_pk,dex_user_fk,dex_first_name,dex_middle_name,dex_last_name,dex_mobile_number,dex_date_of_birth,dex_email_id,dex_address_line1,dex_address_line2,dex_city,dex_state,dex_pincode) values (nextVal('dex_personal_details_dex_cand_user_pk_seq'),?,?,?,?,?,?,?,?,?,?,?,?)";
        PersonalQueries.UPDATE_PERSONAL_DETAILS = "Update dex_personal_details set dex_first_name = ?,dex_middle_name = ?,dex_last_name = ?,dex_mobile_number = ?,dex_date_of_birth = ?,dex_email_id = ?,dex_address_line1 = ?,dex_address_line2 = ?,dex_city = ?,dex_state = ?,dex_pincode = ? where dex_user_fk = ?";
        PersonalQueries.CHECK_IF_RECORD_EXISTS = "Select count(*) from dex_personal_details where dex_user_fk = ?";
    }
}
