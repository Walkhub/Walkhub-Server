package com.walkhub.walkhub.domain.auth.prsentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTokenResponse {

    private String accessToken;

    private String refreshToken;

    private Long expiredAt;
}
