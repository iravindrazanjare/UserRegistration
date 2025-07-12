package com.dex.daoimpl;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dex.dao.RegistrationDao;
import com.dex.models.RegistrationBean;
import com.dex.models.UserBean;
import com.dex.queries.RegistrationQueries;
import com.dex.service.ConfigurationService;

@Repository
public class RegistrationDaoImpl implements RegistrationDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ConfigurationService configService;
    
    @Override
    public UserBean register(RegistrationBean register, HttpServletRequest request) {
        UserBean users = new UserBean();
        String userId = null;
        try {
        	userId = configService.getConfiguration("user_id_prefix");
            int min = 50;
            int max = 100;
            int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
            userId = "USER" + random_int;
            int password = ThreadLocalRandom.current().nextInt(100000, 1000000);
            users.setLoginId(userId);
            users.setPassword(String.valueOf(password));
            jdbcTemplate.update(RegistrationQueries.INSERT_NEW_USER, new Object[] {
            		userId, password, register.getFirst_name(), register.getMiddle_name(), register.getLast_name(), 
            		register.getMobileNo(), register.getGender(), register.getDateofbirth(), register.getEmail_id()
            		});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    @Override
    public int insertGenerateOtp(String mobileNo, int otp) {
        jdbcTemplate.update(RegistrationQueries.DELETE_EXISTING_OTP, new Object[] { mobileNo });
        int result = jdbcTemplate.update(RegistrationQueries.INSERT_OTP_RECORD, new Object[] { mobileNo, otp, "Sent" });
        return result;
    }
    
	@Override
    public ResponseEntity<Map<String, Map<Integer, String>>> registerLoad() {
        return ResponseEntity.ok(configService.getReferenceValueMap());
    }
}