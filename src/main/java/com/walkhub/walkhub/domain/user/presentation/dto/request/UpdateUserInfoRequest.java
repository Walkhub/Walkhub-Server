package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateUserInfoRequest {

    @NotBlank(message = "name은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 10, message = "name은 1글자와 10글자 사이여야 합니다.")
    private String name;

    @NotBlank(message = "profile_image_url은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String profileImageUrl;

}
