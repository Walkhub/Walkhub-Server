package com.walkhub.walkhub.domain.user.domain.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class HealthInfo implements Serializable {

    @Column(columnDefinition = "TINYINT unsigned")
    private Integer weight;

    @Column(precision = 4, scale = 1)
    private BigDecimal height;

}
