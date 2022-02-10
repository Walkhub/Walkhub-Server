package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseAnalysisResponse;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseAnalysisResponse.ExerciseAnalysisResponse;
import com.walkhub.walkhub.domain.user.domain.CalorieLevel;
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
        List<ExerciseAnalysisResponse> queryExerciseAnalysisResponseList =
                exerciseAnalysisRepository.findByUser(user)
                        .stream()
                        .map(exerciseAnalysis -> ExerciseAnalysisResponse.builder()
                                .dailyWalkCountGoal(user.getDailyWalkCountGoal())
                                .walkCount(exerciseAnalysis.getWalkCount())
                                .calorie(exerciseAnalysis.getCalorie())
                                .distance(exerciseAnalysis.getDistance())
                                .build())
                        .collect(Collectors.toList());

        return new QueryExerciseAnalysisResponse(queryExerciseAnalysisResponseList);
    }
}