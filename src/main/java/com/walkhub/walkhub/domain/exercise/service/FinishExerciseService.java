package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.CertifyingShot;
import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.repository.CertifyingShotRepository;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.exception.ExerciseNotFoundException;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.FinishExerciseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FinishExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final CertifyingShotRepository certifyingShotRepository;

    @Transactional
    public void execute(Long exerciseId, FinishExerciseRequest request) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> ExerciseNotFoundException.EXCEPTION);
        exercise.closeExercise(request.getWalkCount(), request.getDistance(), 0);

        for (String image : request.getImageUrl()) {
            CertifyingShot certifyingShot = CertifyingShot.builder()
                    .exercise(exercise)
                    .photo(image)
                    .build();

            certifyingShotRepository.save(certifyingShot);
        }
    }

}
