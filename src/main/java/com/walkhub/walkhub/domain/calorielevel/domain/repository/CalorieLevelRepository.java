package com.walkhub.walkhub.domain.calorielevel.domain.repository;

import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalorieLevelRepository extends JpaRepository<CalorieLevel, Long> {
    List<CalorieLevel> findAllByOrderByLevelAsc();

    Optional<CalorieLevel> findByLevel(Integer level);
}
