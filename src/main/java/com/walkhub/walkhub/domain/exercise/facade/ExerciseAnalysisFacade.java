package com.walkhub.walkhub.domain.exercise.facade;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ExerciseAnalysisFacade {

    private final ExerciseAnalysisRepository exerciseAnalysisRepository;

    public List<Integer> getWalkCountList(User user, LocalDate startAt, LocalDate endAt) {
        Map<LocalDate, List<ExerciseAnalysis>> exerciseAnalysisDateList =
                exerciseAnalysisRepository.findAllByUserAndDateBetweenOrderByDate(user, startAt, endAt)
                        .stream()
                        .collect(Collectors.groupingBy(ExerciseAnalysis::getDate));

        List<Integer> walkCountList = new LinkedList<>();

        for (LocalDate i = startAt; !i.isAfter(endAt); i = i.plusDays(1)) {
            List<ExerciseAnalysis> exerciseAnalyseOfToday = exerciseAnalysisDateList.get(i);
            if (exerciseAnalyseOfToday == null) {
                walkCountList.add(0);
            } else {
                walkCountList.add(exerciseAnalyseOfToday.get(0).getWalkCount());
            }
        }

        return walkCountList;
    }

}
