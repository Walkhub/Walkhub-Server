package com.walkhub.walkhub.domain.exercise.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ExerciseListResponse {

    private final List<ExerciseResponse> exerciseList;

    @Getter
    @Builder
    public static class ExerciseResponse {
        private final Long exerciseId;
        private final String imageUrl;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:SS")
        private final LocalDateTime startAt;
        private final BigDecimal latitude;
        private final BigDecimal longitude;
    }
}
