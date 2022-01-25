package com.walkhub.walkhub.domain.exercise.facade;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.exception.ExerciseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExerciseFacade {

    private final ExerciseRepository exerciseRepository;

    public Exercise getById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> ExerciseNotFoundException.EXCEPTION);
    }

}
