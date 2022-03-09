package com.walkhub.walkhub.domain.calorielevel.domain.repository;

import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalorieLevelRepository extends JpaRepository<CalorieLevel, Long> {
    List<CalorieLevel> findAllByOrderByLevelAsc();

    Optional<CalorieLevel> findByLevel(Integer level);
}
