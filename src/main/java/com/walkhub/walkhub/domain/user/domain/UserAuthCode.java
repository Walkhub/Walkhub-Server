package com.walkhub.walkhub.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class UserAuthCode {

    @Id
    private String phoneNumber;

    private String code;

    @TimeToLive
    private Long expiredAt;

    @Builder
    public UserAuthCode(String phoneNumber, String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.expiredAt = 300L;
    }

}
