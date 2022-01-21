package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "accountId는 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String accountId;

    @NotBlank(message = "phoneNumber는 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String phoneNumber;

    @NotBlank(message = "authCode는 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String authCode;

    @NotBlank(message = "new_password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;<=>?@＼^_`{|}~]{8,30}$",
            message = "new_password는 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String newPassword;
}
