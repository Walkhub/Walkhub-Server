package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.facade.ExerciseFacade;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.FinishExerciseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FinishExerciseService {

    private final ExerciseFacade exerciseFacade;

    @Transactional
    public void execute(Long exerciseId, FinishExerciseRequest request) {
        Exercise exercise = exerciseFacade.getById(exerciseId);

        exercise.closeExercise(
                request.getWalkCount(),
                request.getDistance(),
                request.getCalorie(),
                request.getImageUrl(),
                request.getPausedTime()
        );
    }

}
