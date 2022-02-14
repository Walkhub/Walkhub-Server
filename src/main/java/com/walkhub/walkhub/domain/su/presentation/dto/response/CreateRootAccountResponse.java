package com.walkhub.walkhub.domain.su.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRootAccountResponse {

    private final String accountId;
    private final String password;
}
