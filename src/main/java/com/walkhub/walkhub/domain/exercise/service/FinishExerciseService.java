package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.facade.ExerciseFacade;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.FinishExerciseRequest;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class FinishExerciseService {

    private final UserFacade userFacade;
    private final ExerciseFacade exerciseFacade;

    @Transactional
    public void execute(Long exerciseId, FinishExerciseRequest request) {
        User user = userFacade.getCurrentUser();
        Exercise exercise = exerciseFacade.getById(exerciseId);

        exercise.closeExercise(
                request.getWalkCount(),
                request.getDistance(),
                request.getCalorie(),
                request.getImageUrl(),
                request.getPausedTime()
        );

        user.updateIsMeasuring(false);
    }

}
