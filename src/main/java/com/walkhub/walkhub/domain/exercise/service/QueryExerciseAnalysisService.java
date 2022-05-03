package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.facade.ExerciseAnalysisFacade;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseAnalysisResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryExerciseAnalysisService {

    private final ExerciseAnalysisRepository exerciseAnalysisRepository;
    private final UserFacade userFacade;
    private final ExerciseAnalysisFacade exerciseAnalysisFacade;

    public QueryExerciseAnalysisResponse execute() {
        User user = userFacade.getCurrentUser();
        LocalDate startAt = LocalDate.now().minusDays(27);
        LocalDate now = LocalDate.now();

        ExerciseAnalysis todayExerciseAnalysis = exerciseAnalysisRepository.findByUserAndDate(user, now)
                .orElse(ExerciseAnalysis.builder().walkCount(0).calorie(0.0).distance(0).exerciseTime(0.0).build());

        List<Integer> walkCountList = exerciseAnalysisFacade.getWalkCountList(user, startAt, now);

        return QueryExerciseAnalysisResponse.builder()
                .walkCountList(walkCountList)
                .dailyWalkCountGoal(user.getDailyWalkCountGoal())
                .walkCount(todayExerciseAnalysis.getWalkCount())
                .calorie(todayExerciseAnalysis.getCalorie())
                .distance(todayExerciseAnalysis.getDistance())
                .exerciseTime(todayExerciseAnalysis.getExerciseTime())
                .build();
    }
}