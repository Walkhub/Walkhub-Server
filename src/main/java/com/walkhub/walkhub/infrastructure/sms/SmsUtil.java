package com.walkhub.walkhub.infrastructure.sms;

public interface SmsUtil {
    String sendCode(String phoneNumber, String code);
}
