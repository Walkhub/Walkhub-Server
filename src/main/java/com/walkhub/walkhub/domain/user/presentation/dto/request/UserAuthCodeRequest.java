package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserAuthCodeRequest {

    @Pattern(regexp = "^\\\\d{3}\\\\d{3,4}\\\\d{4}$", message = "잘못된 전화번호입니다.")
    private String phoneNumber;

}
