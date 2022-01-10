package com.walkhub.walkhub.infrastructure.sms;

public interface SmsUtil {
    void sendCode(String phoneNumber, String code);
}
