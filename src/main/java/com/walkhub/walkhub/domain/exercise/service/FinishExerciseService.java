package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.CertifyingShot;
import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.repository.CertifyingShotRepository;
import com.walkhub.walkhub.domain.exercise.facade.ExerciseFacade;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.FinishExerciseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FinishExerciseService {

    private final ExerciseFacade exerciseFacade;
    private final CertifyingShotRepository certifyingShotRepository;

    @Transactional
    public void execute(Long exerciseId, FinishExerciseRequest request) {
        Exercise exercise = exerciseFacade.getById(exerciseId);

        exercise.closeExercise(request.getWalkCount(), request.getDistance(), request.getCalorie());

        List<CertifyingShot> certifyingShotList = request.getImageUrl()
                .stream()
                .map(image -> buildCertifyingShot(exercise, image))
                .collect(Collectors.toList());

        certifyingShotRepository.saveAll(certifyingShotList);
    }

    private CertifyingShot buildCertifyingShot(Exercise exercise, String image) {
        return CertifyingShot.builder()
                .exercise(exercise)
                .photo(image)
                .build();
    }

}
