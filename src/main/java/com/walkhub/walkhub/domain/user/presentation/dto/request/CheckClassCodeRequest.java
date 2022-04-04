package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CheckClassCodeRequest {

    @NotBlank(message = "code는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String code;

}
