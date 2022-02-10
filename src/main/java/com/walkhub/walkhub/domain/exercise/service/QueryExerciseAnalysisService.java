package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseAnalysisResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryExerciseAnalysisService {

    private final ExerciseAnalysisRepository exerciseAnalysisRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryExerciseAnalysisResponse execute() {
        User user = userFacade.getCurrentUser();
        List<QueryExerciseAnalysisResponse.ExerciseAnalysisResponse> queryExerciseAnalysisResponseList =
                exerciseAnalysisRepository.findExerciseAnalysisByUser(user)
                        .stream()
                        .map(exerciseAnalysis -> QueryExerciseAnalysisResponse.ExerciseAnalysisResponse.builder()
                                .levelId(exerciseAnalysis.getLevelId())
                                .foodCalorie(exerciseAnalysis.getFoodCalorie())
                                .dailyWalkCountGoal(user.getDailyWalkCountGoal())
                                .walkCount(exerciseAnalysis.getWalkCount())
                                .calorie(exerciseAnalysis.getCalorie())
                                .distance(exerciseAnalysis.getDistance())
                                .build())
                        .collect(Collectors.toList());

        return new QueryExerciseAnalysisResponse(queryExerciseAnalysisResponseList);
    }
}