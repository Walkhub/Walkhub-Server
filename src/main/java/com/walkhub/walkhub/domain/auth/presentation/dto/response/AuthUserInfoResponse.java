package com.walkhub.walkhub.domain.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthUserInfoResponse {

    private final String accountId;

    private final String phoneNumber;

}
