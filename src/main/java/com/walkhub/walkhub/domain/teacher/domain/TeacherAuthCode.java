package com.walkhub.walkhub.domain.teacher.domain;

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
public class TeacherAuthCode {

    @Id
    @Column(name = "agency_code", columnDefinition = "char(7)")
    private String school;

    @Column(columnDefinition = "char(7)")
    private String authCode;

    @TimeToLive
    private Long expiredAt;

    @Builder
    public TeacherAuthCode(String school, String authCode) {
        this.school = school;
        this.authCode = authCode;
        this.expiredAt = 300L;
    }

}
