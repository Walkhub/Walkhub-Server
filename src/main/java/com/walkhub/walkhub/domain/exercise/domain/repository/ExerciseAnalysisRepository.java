package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.domain.ExerciseAnalysis;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExerciseAnalysisRepository extends CrudRepository<ExerciseAnalysis, Long>, ExerciseAnalysisRepositoryCustom {
    Optional<ExerciseAnalysis> findByUserAndDate(User user, LocalDate date);

    List<ExerciseAnalysis> findAllByUserAndDateBetween(User user, LocalDate startAt, LocalDate endAt);

    Integer countAllByUserAndWalkCountGreaterThanEqual(User user, Integer walkCount);

    Integer countAllByUserAndWalkCountGreaterThan(User user, Integer walkCount);

    @Query("SELECT SUM(e.walkCount) FROM ExerciseAnalysis e WHERE e.user = :user")
    Integer sumWalkCountByUserId(User user);

    List<ExerciseAnalysis> findAllByUserAndDateBetweenOrderByDate(User user, LocalDate startAt, LocalDate endAt);
}
