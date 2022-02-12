package com.walkhub.walkhub.domain.exercise.cache;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
        Double doubleWalkCount = zSetOperations.score(EXERCISE_ANALYSIS_KEY, userId);
        Assert.notNull(doubleWalkCount);
        Integer walkCount = doubleWalkCount.intValue();
        Integer ranking = zSetOperations.rank(EXERCISE_ANALYSIS_KEY, userId).intValue();

        return ExerciseAnalysisDto.builder()
                .walkCount(walkCount)
                .ranking(ranking)
                .userId(userId)
                .build();
    }

    @Override
    public List<ExerciseAnalysisDto> getUserIdsByRankTop100() {
        Set<ZSetOperations.TypedTuple<Object>> rankUserIds = zSetOperations.reverseRangeWithScores(EXERCISE_ANALYSIS_KEY, 0, 99);
        assert rankUserIds != null;

        List<ExerciseAnalysisDto> exerciseAnalysisDtos = new ArrayList<>(rankUserIds.size());
        int rank = 1;

        for (ZSetOperations.TypedTuple<Object> tuple : rankUserIds) {
            ExerciseAnalysisDto exerciseAnalysisDto = ExerciseAnalysisDto.builder()
                    .walkCount(tuple.getScore().intValue())
                    .userId((long) tuple.getValue())
                    .ranking(rank)
                    .build();
            exerciseAnalysisDtos.add(exerciseAnalysisDto);
            rank++;
        }

        return exerciseAnalysisDtos;
    }
}
