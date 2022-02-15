package com.walkhub.walkhub.domain.exercise.cache;

import java.util.List;

public interface ExerciseAnalysisCacheRepository {
    void saveExerciseCache(Long userId, Double walkCount);

    ExerciseAnalysisDto getUserTodayRank(Long userId);

    List<ExerciseAnalysisDto> getUserIdsByRankTop100();
}
