package com.dex.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dex.dao.LoginDao;
import com.dex.models.UserBean;
import com.dex.queries.LoginQueries;
import com.dex.service.ConfigurationService;

@Repository
public class LoginDaoImpl implements LoginDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ConfigurationService configService;
    
    @Override
    public UserBean authenticateUser(String loginId, String password) {
        List<UserBean> users = new ArrayList<UserBean>();
        @SuppressWarnings("deprecation")
		List<UserBean> userFound = (List<UserBean>)jdbcTemplate.query(LoginQueries.FETCH_LOGGED_IN_USER_DETAILS, new Object[] { loginId, password }, rs -> {
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUser_fk(rs.getString("dex_user_pk"));
                user.setLoginId(rs.getString("dex_login_id"));
                user.setPassword(rs.getString("dex_login_password"));
                user.setFirst_name(rs.getString("dex_first_name"));
                user.setMiddle_name(rs.getString("dex_middle_name"));
                user.setLast_name(rs.getString("dex_last_name"));
                user.setGender(configService.getReferenceValue(Integer.parseInt(rs.getString("dex_gender"))));
                user.setMobileNo(rs.getString("dex_mobile_number"));
                user.setDateofbirth(rs.getString("dex_date_of_birth"));
                user.setEmail_id(rs.getString("dex_email_id"));
                if (rs.getString("dex_candidate_stage") != null && "".equalsIgnoreCase(rs.getString("dex_candidate_stage"))) {
                    user.setCandidateStage(Integer.valueOf(rs.getString("dex_candidate_stage")));
                }
                user.setStateMap(configService.getStateMap());
                users.add(user);
            }
            return users;
        });
        if (userFound.isEmpty() || userFound.size() > 1) {
            return null;
        }
        return users.get(0);
    }
}
