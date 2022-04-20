package com.walkhub.walkhub.domain.user.presentation.dto.request;

import com.walkhub.walkhub.domain.user.domain.type.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "account_id는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 5, max = 30, message = "account_id는 5글자 이상, 30글자 이하여야 합니다.")
    private String accountId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" +
            "<=>?@＼^_`{|}~]{8,30}$",
            message = "password는 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 10, message = "name은 10글자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "phone_number는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 11, max = 11, message = "phone_number는 11글자여야 합니다.")
    private String phoneNumber;

    @NotBlank(message = "auth_code는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String authCode;

    @NotNull(message = "school_id는 Null일 수 없습니다.")
    private Long schoolId;

    @Positive(message = "weight는 양수여야 합니다.")
    private Integer weight;

    @Digits(integer = 3, fraction = 1)
    private BigDecimal height;

    private Sex sex;

    @NotBlank(message = "device_token은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String deviceToken;

}
