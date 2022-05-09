package com.walkhub.walkhub.domain.exercise.presentation.dto.response;

import com.walkhub.walkhub.domain.exercise.vo.ExerciseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryExerciseHistoryResponse {

    private final Integer totalPage;
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

        public ExerciseHistory(ExerciseVO exerciseVO) {
            this.exerciseId = exerciseVO.getExerciseId();
            this.certifyingShot = exerciseVO.getCertifyingShot();
            this.walkCount = exerciseVO.getWalkCount();
            this.speed = exerciseVO.getSpeed();
            this.calorie = exerciseVO.getCalorie();
            this.time = exerciseVO.getTime();
            this.latitude = exerciseVO.getLatitude();
            this.longitude = exerciseVO.getLongitude();
            this.endAt = exerciseVO.getEndAt();
        }
    }

}
