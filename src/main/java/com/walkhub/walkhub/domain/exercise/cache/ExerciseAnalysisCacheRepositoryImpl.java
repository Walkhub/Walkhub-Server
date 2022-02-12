package com.walkhub.walkhub.domain.exercise.cache;

import com.walkhub.walkhub.domain.exercise.exception.RedisTransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
    public ExerciseAnalysisDto getUserTodayRank(Long userId) {
        Double doubleWalkCount = Optional.ofNullable(zSetOperations.score(EXERCISE_ANALYSIS_KEY, userId))
                .orElseThrow(() -> RedisTransactionException.EXCEPTION);

        Integer walkCount = doubleWalkCount.intValue();
        Long ranking = zSetOperations.rank(EXERCISE_ANALYSIS_KEY, userId);

        try {
            return ExerciseAnalysisDto.builder()
                    .walkCount(walkCount)
                    .ranking(Objects.requireNonNull(ranking).intValue())
                    .userId(userId)
                    .build();
        } catch (NullPointerException e) {
            throw RedisTransactionException.EXCEPTION;
        }
    }

    @Override
    public List<ExerciseAnalysisDto> getUserIdsByRankTop100() {
        Set<ZSetOperations.TypedTuple<Object>> rankUserIds = Optional.ofNullable(
                zSetOperations.reverseRangeWithScores(EXERCISE_ANALYSIS_KEY, 0, 99))
                .orElseThrow(() -> RedisTransactionException.EXCEPTION);
        int rank = 1;

        List<ExerciseAnalysisDto> exerciseAnalysisDtos = new ArrayList<>(rankUserIds.size());

        try {
            for (ZSetOperations.TypedTuple<Object> tuple : rankUserIds) {
                ExerciseAnalysisDto exerciseAnalysisDto = ExerciseAnalysisDto.builder()
                        .walkCount(Objects.requireNonNull(tuple.getScore()).intValue())
                        .userId((Long) tuple.getValue())
                        .ranking(rank)
                        .build();
                exerciseAnalysisDtos.add(exerciseAnalysisDto);
                rank++;
            }
        } catch (NullPointerException e) {
            throw RedisTransactionException.EXCEPTION;
        }

        return exerciseAnalysisDtos;
    }
}
