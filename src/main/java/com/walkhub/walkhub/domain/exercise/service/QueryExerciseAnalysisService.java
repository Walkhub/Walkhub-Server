package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseAnalysisResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
        LocalDate startAt = LocalDate.now().minusDays(28);

        ExerciseAnalysis exerciseAnalysis = exerciseAnalysisRepository.findByUserAndDate(user, LocalDate.now())
                .orElse(ExerciseAnalysis.builder().build());

        List<Integer> walkCountList = exerciseAnalysisRepository.findAllByUserAndDateBetween(user, startAt, LocalDate.now())
                .stream().map(ExerciseAnalysis::getWalkCount)
                .collect(Collectors.toList());

        return QueryExerciseAnalysisResponse.builder()
                .walkCount(exerciseAnalysis.getWalkCount())
                .walkCountList(walkCountList)
                .calorie(exerciseAnalysis.getCalorie())
                .dailyWalkCountGoal(user.getDailyWalkCountGoal())
                .distance(exerciseAnalysis.getDistance())
                .build();
    }
}