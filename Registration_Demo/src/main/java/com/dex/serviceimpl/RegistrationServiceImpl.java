package com.dex.serviceimpl;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dex.dao.RegistrationDao;
import com.dex.models.RegistrationBean;
import com.dex.models.UserBean;
import com.dex.service.ConfigurationService;
import com.dex.service.RegistrationService;

@Transactional
@Service
public class RegistrationServiceImpl implements RegistrationService
{
    @Autowired
    private RegistrationDao registrationDao;
    @Autowired
    private ConfigurationService configurations;
    
    @Override
    public UserBean register(RegistrationBean register, HttpServletRequest request) {
        return registrationDao.register(register, request);
    }
    
    @Override
    public RegistrationBean generateOtp(RegistrationBean register, HttpServletRequest request) {
        RegistrationBean rb = new RegistrationBean();
        int generatedOtp = ThreadLocalRandom.current().nextInt(100000, 1000000);
        int result = registrationDao.insertGenerateOtp(register.getMobileNo(), generatedOtp);
        if (result > 0) {
            rb.setMobileNo(register.getMobileNo());
            rb.setOtp(generatedOtp);
            sendOtp(register.getMobileNo(), generatedOtp);
        }
        return rb;
    }
    
    @SuppressWarnings("deprecation")
	private void sendOtp(String mobileNo, int otp) {
        StringBuffer strSmsUrl = new StringBuffer("");
        String finalURL = null;
        String msg = String.format(configurations.getConfiguration("sms_otp_content"), otp);
        String smsApiUrl = configurations.getConfiguration("sms_url");
        String userName = configurations.getConfiguration("sms_api_username");
        String password = configurations.getConfiguration("sms_api_password");
        String smsFeedId = configurations.getConfiguration("sms_feed_id");
        String keyword = configurations.getConfiguration("sms_keyword");
        String senderId = configurations.getConfiguration("sms_sender_id");
        strSmsUrl.append(smsApiUrl);
        strSmsUrl.append("feedid=" + smsFeedId);
        strSmsUrl.append("&username=" + userName);
        strSmsUrl.append("&password=" + password);
        strSmsUrl.append("&senderid=" + senderId);
        strSmsUrl.append("&keyword=" + keyword);
        finalURL = strSmsUrl.toString() + "&to=" + mobileNo + "&text=" + URLEncoder.encode(msg);
        System.out.println("Sending SMS == > " + finalURL);
        try {
            URL url = new URL(finalURL);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(60000);
            urlConnection.setReadTimeout(60000);
            urlConnection.setDoOutput(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Map<Integer, String>>> registerLoad() {
        return registrationDao.registerLoad();
    }
}
