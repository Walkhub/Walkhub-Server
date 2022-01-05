package com.walkhub.walkhub.domain.exercise.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(LocationId.class)
@Entity
public class Location {

    @Id
    private Integer order;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(nullable = false)
    @Digits(integer = 2, fraction = 8)
    private BigDecimal latitude;

    @Column(nullable = false)
    @Digits(integer = 3, fraction = 8)
    private BigDecimal longitude;

}
