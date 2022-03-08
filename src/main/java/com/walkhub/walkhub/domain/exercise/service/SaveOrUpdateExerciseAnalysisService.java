package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.cache.ExerciseAnalysisCacheRepository;
import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseAnalysisRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.SaveExerciseAnalysisRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@WalkhubService
public class SaveOrUpdateExerciseAnalysisService {

    private final ExerciseAnalysisRepository exerciseAnalysisRepository;
    private final ExerciseAnalysisCacheRepository exerciseAnalysisCacheRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(SaveExerciseAnalysisRequest request) {
        User user = userFacade.getCurrentUser();
        exerciseAnalysisCacheRepository.saveExerciseCache(user.getSchool().getId(), user.getId(), request.getWalkCount().doubleValue());
        ExerciseAnalysis exerciseAnalysisToSave = buildOrUpdateExerciseAnalysis(request, user);
        exerciseAnalysisRepository.save(exerciseAnalysisToSave);
    }

    private ExerciseAnalysis buildOrUpdateExerciseAnalysis(SaveExerciseAnalysisRequest request, User user) {
        Optional<ExerciseAnalysis> optionalExerciseAnalysis = exerciseAnalysisRepository.findByUserAndDate(user, request.getDate());

        optionalExerciseAnalysis.ifPresent(exercise -> exercise.updateExerciseAnalysis(request));

        return optionalExerciseAnalysis
                .orElseGet(() -> buildExerciseAnalysis(request, user));
    }

    private ExerciseAnalysis buildExerciseAnalysis(SaveExerciseAnalysisRequest request, User user) {
        return ExerciseAnalysis.builder()
                .date(request.getDate())
                .distance(request.getDistance())
                .walkCount(request.getWalkCount())
                .calorie(request.getCalorie())
                .exerciseTime(request.getExerciseTime())
                .user(user)
                .build();
    }
}
