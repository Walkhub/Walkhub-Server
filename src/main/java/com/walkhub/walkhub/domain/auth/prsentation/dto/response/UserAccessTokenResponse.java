package com.walkhub.walkhub.domain.auth.prsentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserAccessTokenResponse {

    private final String accessToken;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:SS")
    private final LocalDateTime expiredAt;
}
