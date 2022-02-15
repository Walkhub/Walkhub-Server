package com.walkhub.walkhub.domain.auth.presentation.dto.response;

import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserTokenResponse {

    private final String accessToken;

    private final String refreshToken;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:SS")
    private final LocalDateTime expiredAt;

    private final Authority authority;

    private final BigDecimal height;

    private final Integer weight;

    private final Sex sex;
}
