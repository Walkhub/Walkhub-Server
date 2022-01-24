package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class JoinGroupRequest {

    @NotBlank(message = "class_code는 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 7, max = 7, message = "class_code는 7글자 여야 합니다.")
    private String classCode;

}
