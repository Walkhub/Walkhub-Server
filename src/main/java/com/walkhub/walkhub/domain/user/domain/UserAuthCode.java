package com.walkhub.walkhub.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class UserAuthCode {

    @Id
    @Column(columnDefinition = "char(11)")
    private String phoneNumber;

    @Column(columnDefinition = "char(5)")
    private String code;

    @TimeToLive
    private Long expired_at;

    @Builder
    public UserAuthCode(String phoneNumber, String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.expired_at = 300L;
    }

    public void updateCode(String code) {
        this.code = code;
    }

}
