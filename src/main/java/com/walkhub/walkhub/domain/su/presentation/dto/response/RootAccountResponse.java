package com.walkhub.walkhub.domain.su.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RootAccountResponse {

    private final String accountId;

    private final String password;

}
