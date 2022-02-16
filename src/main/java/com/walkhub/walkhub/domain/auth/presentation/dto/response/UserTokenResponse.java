package com.walkhub.walkhub.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.walkhub.walkhub.domain.user.domain.type.Sex;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserTokenResponse {

    private final String accessToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:SS")
    private final LocalDateTime expiredAt;
    private final String refreshToken;
    private final Authority authority;
    private final BigDecimal height;
    private final Integer weight;
    private final Sex sex;

}
