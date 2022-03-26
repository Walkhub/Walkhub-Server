package com.walkhub.walkhub.domain.exercise.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
public class ExerciseVO {
    private final Long exerciseId;
    private final String certifyingShot;
    private final Integer walkCount;
    private final Double speed;
    private final Double calorie;
    private final Integer time;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final ZonedDateTime endAt;

    @QueryProjection
    public ExerciseVO(Long exerciseId, String certifyingShot, Integer walkCount, Double speed, Double calorie,
                      Integer time, BigDecimal latitude, BigDecimal longitude, ZonedDateTime endAt) {
        this.exerciseId = exerciseId;
        this.certifyingShot = certifyingShot;
        this.walkCount = walkCount;
        this.speed = speed;
        this.calorie = calorie;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.endAt = endAt;
    }
}
