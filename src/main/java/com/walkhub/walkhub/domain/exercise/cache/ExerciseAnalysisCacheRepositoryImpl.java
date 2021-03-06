package com.walkhub.walkhub.domain.exercise.cache;

import com.walkhub.walkhub.domain.exercise.exception.RedisTransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class ExerciseAnalysisCacheRepositoryImpl implements ExerciseAnalysisCacheRepository {

    public static final String EXERCISE_ANALYSIS_KEY = "exercise_analysis_";

    private final ZSetOperations<String, Object> zSetOperations;

    @Override
    public void saveExerciseCache(Long schoolId, Long userId, Double walkCount) {
        String exerciseAnalysisKey = getExerciseAnalysisKey(schoolId);
        zSetOperations.add(exerciseAnalysisKey, userId, walkCount);
        setExpiration(exerciseAnalysisKey);
    }

    @Override
    public ExerciseAnalysisDto getUserTodayRank(Long schoolId, Long userId) {
        Double doubleWalkCount = zSetOperations.score(getExerciseAnalysisKey(schoolId), userId);
        if (doubleWalkCount == null) {
            return null;
        }

        Integer walkCount = doubleWalkCount.intValue();
        Long ranking = zSetOperations.rank(getExerciseAnalysisKey(schoolId), userId);

        try {
            return ExerciseAnalysisDto.builder()
                    .walkCount(walkCount)
                    .ranking(ranking == null ? 0 : Math.toIntExact(ranking) + 1)
                    .userId(userId)
                    .build();
        } catch (NullPointerException e) {
            throw RedisTransactionException.EXCEPTION;
        }
    }

    @Override
    public List<ExerciseAnalysisDto> getUserIdsByRankTop100(Long schoolId) {
        Set<TypedTuple<Object>> rankUserIds =
                zSetOperations.reverseRangeWithScores(getExerciseAnalysisKey(schoolId), 0, 99);
        int rank = 1;
        if (rankUserIds == null) {
            return Collections.emptyList();
        }

        List<ExerciseAnalysisDto> exerciseAnalysisDtos = new ArrayList<>(rankUserIds.size());

        try {
            for (TypedTuple<Object> tuple : rankUserIds) {
                ExerciseAnalysisDto exerciseAnalysisDto = ExerciseAnalysisDto.builder()
                        .walkCount(Objects.requireNonNull(tuple.getScore()).intValue())
                        .userId(Long.valueOf(String.valueOf(tuple.getValue())))
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

    private String getExerciseAnalysisKey(Long schoolId) {
        return EXERCISE_ANALYSIS_KEY + schoolId;
    }

    private void setExpiration(String exerciseAnalysisKey) {
        Date tomorrow = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
        zSetOperations.getOperations().expireAt(exerciseAnalysisKey, tomorrow);
    }
}
