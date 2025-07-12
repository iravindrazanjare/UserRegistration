package com.dex.serviceimpl;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dex.service.PaymentService;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService
{
    @Override
    public String generatePaymentUrl() {
        String strMsg = "";
        String msg = "";
        final String merchantId = "1000003";
        final String transactionId = "ABCD123422";
        final int paymentAmount = 5;
        final Long userFk = 14L;
        final String userId = "ABCD1234";
        final String sbiOtherDetails = "";
        final String currencyType = "INR";
        final String typeField1 = "R";
        final String securityId = "";
        final String typeField2 = "F";
        final String returnUrl = "http://localhost:8080/Registration/pay/recordResponse";
        final String checkSumKey = "fBc5628ybRQf88f/aqDUOQ==";
        try {
            final String requestUrl = this.createRequestSBIEPay(merchantId, transactionId, paymentAmount, currencyType, typeField1, securityId, typeField2, returnUrl, userFk, userId, sbiOtherDetails);
            final StringBuilder builder = new StringBuilder();
            builder.append(requestUrl);
            strMsg = builder.toString();
            final Key aesKey = new SecretKeySpec(checkSumKey.getBytes(), "AES");
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, aesKey);
            final byte[] encrypted = cipher.doFinal(strMsg.getBytes());
            msg = new String(Base64.getEncoder().encodeToString(encrypted));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Encrypted Url : " + msg);
        return msg;
    }
    
    private String createRequestSBIEPay(final String merchantId, final String transactionId, final int paymentAmount, final String currencyType, final String typeField1, final String securityId, final String typeField2, final String returnUrl, final Long userFk, final String userId, final String sbiOtherDetails) {
        String requestUrl = "";
        final StringBuilder sbuilder = new StringBuilder();
        try {
            sbuilder.append(merchantId);
            sbuilder.append("|");
            sbuilder.append("DOM");
            sbuilder.append("|");
            sbuilder.append("IN");
            sbuilder.append("|");
            sbuilder.append("INR");
            sbuilder.append("|");
            sbuilder.append(paymentAmount);
            sbuilder.append("|");
            sbuilder.append(sbiOtherDetails);
            sbuilder.append("|");
            sbuilder.append(returnUrl);
            sbuilder.append("|");
            sbuilder.append(returnUrl);
            sbuilder.append("|");
            sbuilder.append("SBIEPAY");
            sbuilder.append("|");
            sbuilder.append(transactionId);
            sbuilder.append("|");
            sbuilder.append(userId);
            sbuilder.append("|");
            sbuilder.append("NB");
            sbuilder.append("|");
            sbuilder.append("ONLINE");
            sbuilder.append("|");
            sbuilder.append("ONLINE");
            requestUrl = sbuilder.toString();
            System.out.println(requestUrl);
        }
        catch (Exception ex) {}
        return requestUrl;
    }
}