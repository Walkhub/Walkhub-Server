package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.Location;
import com.walkhub.walkhub.domain.exercise.domain.repository.LocationRepository;
import com.walkhub.walkhub.domain.exercise.facade.ExerciseFacade;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.SaveLocationRequest;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class SaveLocationService {

    private final ExerciseFacade exerciseFacade;
    private final LocationRepository locationRepository;

    @Transactional
    public void execute(Long exerciseId, SaveLocationRequest request) {
        Exercise exercise = exerciseFacade.getById(exerciseId);

        List<Location> locationList = request.getLocationList()
                .stream()
                .map(locationInfo -> Location.builder()
                        .sequence(locationInfo.getSequence())
                        .exercise(exercise)
                        .latitude(locationInfo.getLatitude())
                        .longitude(locationInfo.getLongitude())
                        .build())
                .collect(Collectors.toList());

        locationRepository.saveAll(locationList);
    }

}
