package com.walkhub.walkhub.domain.exercise.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryExerciseHistoryResponse {

    private final List<ExerciseHistory> exerciseAnalysisList;

    @Getter
    public static class ExerciseHistory {
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
        public ExerciseHistory(Long exerciseId, String certifyingShot, Integer walkCount, Double speed, Double calorie,
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

}
