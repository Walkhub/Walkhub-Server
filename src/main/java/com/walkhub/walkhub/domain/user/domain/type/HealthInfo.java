package com.walkhub.walkhub.domain.user.domain.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class HealthInfo implements Serializable {

    @Column(columnDefinition = "TINYINT unsigned")
    private Integer weight;

    @Digits(integer = 3, fraction = 1)
    private BigDecimal height;

}
