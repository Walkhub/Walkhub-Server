package com.walkhub.walkhub.global.date;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class DateTimeZoneConfiguration {
    @PostConstruct
    public void setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
