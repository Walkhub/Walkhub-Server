package com.walkhub.walkhub.domain.user.presentation.dto.request;

import com.walkhub.walkhub.domain.user.domain.type.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateUserInfoRequest {

    @Size(min = 1, max = 10, message = "name은 1글자와 10글자 사이여야 합니다.")
    private String name;

    private String profileUrl;

    @Size(min = 8, max = 8, message = "name은 8글자 여야 합니다.")
    private String birthday;

    @Size(min = 1, max = 1, message = "sex는 1글자 여야 합니다.")
    private Sex sex;

}
