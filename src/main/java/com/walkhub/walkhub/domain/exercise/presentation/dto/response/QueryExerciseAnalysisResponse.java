package com.walkhub.walkhub.domain.exercise.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryExerciseAnalysisResponse {

    private final List<ExerciseAnalysisResponse> exerciseAnalysisList;

    @Getter
    @Builder
    public static class ExerciseAnalysisResponse {
        private final List<WalkCountListResponse> walkCountList;
        private final Integer dailyWalkCountGoal;
        private final Integer walkCount;
        private final Integer calorie;
        private final Integer distance;
        private final LocalDateTime walkTime;
    }

    @Getter
    @Builder
    public static class WalkCountListResponse {
        private final Integer walkCount;
    }
}
