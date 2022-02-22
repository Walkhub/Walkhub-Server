package com.walkhub.walkhub.domain.exercise.cache;

import java.util.List;

public interface ExerciseAnalysisCacheRepository {
    void saveExerciseCache(Long schoolId, Long userId, Double walkCount);

    ExerciseAnalysisDto getUserTodayRank(Long schoolId, Long userId);

    List<ExerciseAnalysisDto> getUserIdsByRankTop100(Long schoolId);
}
