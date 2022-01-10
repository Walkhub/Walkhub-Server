package com.walkhub.walkhub.infrastructure.sms.coolsms;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "coolsms")
public class CoolSmsProperties {

    private final String key;
    private final String secret;
    private final String phoneNumber;

    public CoolSmsProperties(String key, String secret, String phoneNumber) {
        this.key = key;
        this.secret = secret;
        this.phoneNumber = phoneNumber;
    }

}
