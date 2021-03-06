package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.repository.LocationRepository;
import com.walkhub.walkhub.domain.exercise.facade.ExerciseFacade;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseDetailsResponse;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseDetailsResponse.ExerciseResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryExerciseDetailsService {

    private final ExerciseFacade exerciseFacade;
    private final LocationRepository locationRepository;

    public QueryExerciseDetailsResponse execute(Long exerciseId) {
        Exercise exercise = exerciseFacade.getById(exerciseId);

        List<ExerciseResponse> locations = locationRepository.findAllByExercise(exercise)
                .stream()
                .map(location -> ExerciseResponse.builder()
                        .sequence(location.getSequence())
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude())
                        .build())
                .collect(Collectors.toList());

        return new QueryExerciseDetailsResponse(locations);
    }
}
