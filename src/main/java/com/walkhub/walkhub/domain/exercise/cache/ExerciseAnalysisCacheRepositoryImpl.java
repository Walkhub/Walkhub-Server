package com.walkhub.walkhub.domain.exercise.cache;

import com.walkhub.walkhub.domain.exercise.exception.RedisTransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ExerciseAnalysisCacheRepositoryImpl implements ExerciseAnalysisCacheRepository {

    public static final String EXERCISE_ANALYSIS_KEY = "exercise_analysis";

    private final ZSetOperations<String, Object> zSetOperations;

    @Override
    public void saveExerciseCache(Long userId, Double walkCount) {
        zSetOperations.add(EXERCISE_ANALYSIS_KEY, userId, walkCount);
    }

    @Override
    public Long getUserTodayRank(Long userId) {
        return zSetOperations.reverseRank(EXERCISE_ANALYSIS_KEY, userId);
    }

    @Override
    public List<Long> getUserIdsByRankTop100() {
        Set<Object> rankUserIds = zSetOperations.reverseRange(EXERCISE_ANALYSIS_KEY, 0, 99);
        if (rankUserIds == null) {
            throw RedisTransactionException.EXCEPTION;
        }

        return rankUserIds.stream()
                .map(value -> (long) value)
                .collect(Collectors.toList());
    }
}
