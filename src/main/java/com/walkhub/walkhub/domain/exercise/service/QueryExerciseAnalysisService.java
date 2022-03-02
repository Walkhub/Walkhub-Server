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

        ExerciseAnalysis exerciseAnalysis = exerciseAnalysisRepository.findByUserAndDate(user, now)
                .orElse(ExerciseAnalysis.builder().build());

        Map<LocalDate, List<ExerciseAnalysis>> walkCountList = exerciseAnalysisRepository.findAllByUserAndDateBetweenOrderByDate(user, startAt, now)
                .stream()
                .collect(Collectors.groupingBy(ExerciseAnalysis::getDate));

        List<Integer> walkCountList2 = new LinkedList<>();

        for (LocalDate i = startAt; i.isBefore(LocalDate.now()); i = i.plusDays(1)) {
            List<ExerciseAnalysis> exerciseAnalyseOfToday = walkCountList.get(i);
            if (exerciseAnalyseOfToday == null) {
                walkCountList2.add(0);
            } else {
                walkCountList2.add(exerciseAnalyseOfToday.get(0).getWalkCount());
            }
        }

        return QueryExerciseAnalysisResponse.builder()
                .walkCountList(walkCountList2)
                .dailyWalkCountGoal(user.getDailyWalkCountGoal())
                .walkCount(exerciseAnalysis.getWalkCount())
                .calorie(exerciseAnalysis.getCalorie())
                .distance(exerciseAnalysis.getDistance())
                .build();
    }
}