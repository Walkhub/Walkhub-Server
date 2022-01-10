package com.walkhub.walkhub.domain.auth.prsentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {
    private String accountId;

    private String password;
}
