package com.walkhub.walkhub.domain.auth.prsentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserTokenResponse {

    private String accessToken;

    private String refreshToken;

    @DateTimeFormat(pattern = "yyyy-MM-dd`T`hh:mm:SS")
    private LocalDateTime expiredAt;
}
