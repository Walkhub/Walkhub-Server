package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.user.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    Optional<Exercise> findByIsExercisingTrueAndUser(User user);
    List<Exercise> findByUser(User user);
}
