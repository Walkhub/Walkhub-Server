package com.walkhub.walkhub.domain.exercise.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryExerciseAnalysisResponse {
    private final List<Integer> walkCountList;
    private final Integer dailyWalkCountGoal;
    private final Integer walkCount;
    private final Double calorie;
    private final Integer distance;
}
