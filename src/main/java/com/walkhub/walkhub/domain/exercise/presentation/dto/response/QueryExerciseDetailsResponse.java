package com.walkhub.walkhub.domain.exercise.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryExerciseDetailsResponse {

    private final String certifyingShot;
    private final List<ExerciseResponse> exerciseList;

    @Getter
    @Builder
    public static class ExerciseResponse {
        private final Integer order;
        private final BigDecimal latitude;
        private final BigDecimal longitude;
    }
}
