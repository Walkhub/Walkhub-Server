package com.walkhub.walkhub.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class InputHealthInformationRequest {

    @NotNull(message = "height는 null일 수 없습니다.")
    @Digits(integer=3, fraction=1)
    private BigDecimal height;

    @NotNull(message = "weight는 null일 수 없습니다.")
    @Positive(message = "weight는 양수여야 합니다.")
    private Integer weight;
}