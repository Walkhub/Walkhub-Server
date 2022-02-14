package com.walkhub.walkhub.domain.exercise.cache;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ExerciseAnalysisDto {
    private final Integer walkCount;
    private final Integer ranking;
    private final Long userId;
}
