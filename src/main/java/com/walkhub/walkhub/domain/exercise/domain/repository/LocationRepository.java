package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.Location;
import com.walkhub.walkhub.domain.exercise.domain.LocationId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, LocationId> {
    Location findTop1ByExerciseOrderBySequenceDesc(Exercise exercise);

    List<Location> findAllByExercise(Exercise exercise);
}
