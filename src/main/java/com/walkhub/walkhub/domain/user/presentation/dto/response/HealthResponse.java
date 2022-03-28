package com.walkhub.walkhub.domain.user.presentation.dto.response;

import com.walkhub.walkhub.domain.user.domain.type.Sex;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class HealthResponse {
    private final BigDecimal height;
    private final Integer weight;
    private final Sex sex;
}
