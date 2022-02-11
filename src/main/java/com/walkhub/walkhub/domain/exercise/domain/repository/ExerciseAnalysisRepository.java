package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExerciseAnalysisRepository extends CrudRepository<ExerciseAnalysis, Long>, CustomExerciseRepository {
    Optional<ExerciseAnalysis> findByUserAndDate(User user, LocalDate date);
}
