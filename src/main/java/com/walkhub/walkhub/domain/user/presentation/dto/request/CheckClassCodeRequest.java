package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CheckClassCodeRequest {

    @NotBlank(message = "code는 비어있으면 안됩니다.")
    private String code;

}
