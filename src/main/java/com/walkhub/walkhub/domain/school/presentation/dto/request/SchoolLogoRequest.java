package com.walkhub.walkhub.domain.school.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SchoolLogoRequest {

    @NotBlank(message = "image_url은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String imageUrl;
}
