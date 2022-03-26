package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise, Long>, ExerciseRepositoryCustom {
    Optional<Exercise> findByIsExercisingTrueAndUser(User user);

    Page<Exercise> findAllByUser(User user, Pageable pageable);

    @Query("SELECT SUM(e.cheeringCount) FROM Exercise e WHERE e.user = :user")
    Integer sumCheeringCountAndUserId(User user);
}
