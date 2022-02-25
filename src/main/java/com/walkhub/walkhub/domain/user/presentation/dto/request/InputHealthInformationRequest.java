package com.walkhub.walkhub.domain.user.presentation.dto.request;

import com.walkhub.walkhub.domain.user.domain.type.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class InputHealthInformationRequest {

    @Digits(integer = 3, fraction = 1)
    private BigDecimal height;

    @Positive(message = "weight는 양수여야 합니다.")
    private Integer weight;

    private Sex sex;
}