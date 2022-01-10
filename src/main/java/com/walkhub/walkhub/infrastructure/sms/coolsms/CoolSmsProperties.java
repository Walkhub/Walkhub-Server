package com.walkhub.walkhub.infrastructure.sms.coolsms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "coolsms")
public class CoolSmsProperties {

    private final String key;
    private final String secret;
    private final String phoneNumber;

}
