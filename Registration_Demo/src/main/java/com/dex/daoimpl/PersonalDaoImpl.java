package com.dex.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dex.dao.PersonalDao;
import com.dex.models.PersonalBean;
import com.dex.queries.PersonalQueries;
import com.dex.service.ConfigurationService;

@Repository
public class PersonalDaoImpl implements PersonalDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ConfigurationService configService;
    
    @Override
    public PersonalBean insertPersonalDetails(PersonalBean personal, HttpServletRequest request) {
        List<PersonalBean> personalDetails = new ArrayList<PersonalBean>();
        try {
        	//check if record exist in database
			int count = jdbcTemplate.queryForObject(PersonalQueries.CHECK_IF_RECORD_EXISTS, new Object[] {Integer.parseInt(personal.getUser_fk())},Integer.class);
            if (count <= 0) {
                jdbcTemplate.update(PersonalQueries.INSERT_PERSONAL_DETAILS, new Object[] { 
                		Integer.parseInt(personal.getUser_fk()), personal.getFirst_name(), personal.getMiddle_name(), personal.getLast_name(), personal.getMobileNo(), personal.getDateofbirth(), personal.getEmail_id(), personal.getAddressline1(), personal.getAddressline2(), personal.getCity(), personal.getState(), personal.getPincode()
                		});
                personalDetails.add(personal);
            }
            else {
                jdbcTemplate.update(PersonalQueries.UPDATE_PERSONAL_DETAILS, new Object[] { personal.getFirst_name(), personal.getMiddle_name(), personal.getLast_name(), personal.getMobileNo(), personal.getDateofbirth(), personal.getEmail_id(), personal.getAddressline1(), personal.getAddressline2(), personal.getCity(), personal.getState(), personal.getPincode(), Integer.parseInt(personal.getUser_fk()) });
                personalDetails.add(personal);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return personalDetails.get(0);
    }
    
    @Override
    public PersonalBean getPersonalDetails(String userFk, HttpServletRequest request) {
        List<PersonalBean> personalDetails = new ArrayList<PersonalBean>();
        @SuppressWarnings("deprecation")
		final List<PersonalBean> userDetails = (List<PersonalBean>)this.jdbcTemplate.query(PersonalQueries.GET_CANDIDATE_PERSONAL_RECORD, new Object[] { Integer.parseInt(userFk) }, rs -> {
            while (rs.next()) {
                PersonalBean personalBean = new PersonalBean();
                personalBean.setUser_fk(rs.getString("dex_user_fk"));
                personalBean.setFirst_name(rs.getString("dex_first_name"));
                personalBean.setMiddle_name(rs.getString("dex_middle_name"));
                personalBean.setLast_name(rs.getString("dex_last_name"));
                personalBean.setMobileNo(rs.getString("dex_mobile_number"));
                if(rs.getString("dex_gender") != null)
                personalBean.setGender(this.configService.getReferenceValue(Integer.parseInt(rs.getString("dex_gender"))));
                personalBean.setDateofbirth(rs.getString("dex_date_of_birth"));
                personalBean.setEmail_id(rs.getString("dex_email_id"));
                personalBean.setAddressline1(rs.getString("dex_address_line1"));
                personalBean.setAddressline2(rs.getString("dex_address_line2"));
                personalBean.setCity(rs.getString("dex_city"));
                if(rs.getString("dex_state") != null)
                personalBean.setState(Integer.parseInt(rs.getString("dex_state")));
                personalBean.setPincode(rs.getInt("dex_pincode"));
                personalBean.setStateMap(this.configService.getStateMap());
                personalBean.setReferenceValue(this.configService.getReferenceValueMap());
                personalDetails.add(personalBean);
            }
            return personalDetails;
        });
        if (userDetails.isEmpty() || userDetails.size() > 1) {
            return null;
        }
        return personalDetails.get(0);
    }
    
    @Override
    public PersonalBean getMenuStage(String user_fk, HttpServletRequest request) {
        List<PersonalBean> personalDetails = new ArrayList<PersonalBean>();
        @SuppressWarnings("deprecation")
		List<PersonalBean> userDetails = (List<PersonalBean>)jdbcTemplate.query(PersonalQueries.GET_CANDIDATE_PERSONAL_RECORD, new Object[] { Integer.parseInt(user_fk) }, rs -> {
            while (rs.next()) {
                PersonalBean personalBean = new PersonalBean();
                personalBean.setCandidateStage(Integer.valueOf(rs.getString("dex_candidate_stage")));
                personalDetails.add(personalBean);
            }
            return personalDetails;
        });
        if (userDetails.isEmpty() || userDetails.size() > 1) {
            return null;
        }
        return personalDetails.get(0);
    }
}