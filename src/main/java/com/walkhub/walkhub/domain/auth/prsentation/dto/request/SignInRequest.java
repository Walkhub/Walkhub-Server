package com.walkhub.walkhub.domain.auth.prsentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "account_id는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String accountId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String password;

    @NotBlank(message = "device_token은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String deviceToken;

}
