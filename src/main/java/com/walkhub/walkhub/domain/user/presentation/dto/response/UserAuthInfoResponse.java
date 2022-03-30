package com.walkhub.walkhub.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAuthInfoResponse {
    private final String accountId;
    private final String phoneNumber;
}
