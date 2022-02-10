package com.walkhub.walkhub.domain.exercise.cache;

import java.util.List;

public interface ExerciseAnalysisCacheRepository {
    void saveExerciseCache(Long userId, Double walkCount);

    Long getUserTodayRank(Long userId);

    List<Long> getUserIdsByRankTop100();
}
