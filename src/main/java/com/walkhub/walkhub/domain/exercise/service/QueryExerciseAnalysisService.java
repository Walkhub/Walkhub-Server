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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryExerciseAnalysisService {

    private final ExerciseAnalysisRepository exerciseAnalysisRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryExerciseAnalysisResponse execute() {
        User user = userFacade.getCurrentUser();
        LocalDate startAt = LocalDate.now().minusDays(27);
        LocalDate now = LocalDate.now();

        ExerciseAnalysis todayExerciseAnalysis = exerciseAnalysisRepository.findByUserAndDate(user, now)
                .orElse(ExerciseAnalysis.builder().walkCount(0).calorie(0.0).distance(0).exerciseTime(0.0).build());

        Map<LocalDate, List<ExerciseAnalysis>> exerciseAnalysisDateList = exerciseAnalysisRepository.findAllByUserAndDateBetweenOrderByDate(user, startAt, now)
                .stream()
                .collect(Collectors.groupingBy(ExerciseAnalysis::getDate));

        List<Integer> walkCountList = new LinkedList<>();

        for (LocalDate i = startAt; !i.isAfter(LocalDate.now()); i = i.plusDays(1)) {
            List<ExerciseAnalysis> exerciseAnalyseOfToday = exerciseAnalysisDateList.get(i);
            if (exerciseAnalyseOfToday == null) {
                walkCountList.add(0);
            } else {
                walkCountList.add(exerciseAnalyseOfToday.get(0).getWalkCount());
            }
        }

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