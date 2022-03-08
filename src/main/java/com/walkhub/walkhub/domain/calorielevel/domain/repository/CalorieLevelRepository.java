package com.walkhub.walkhub.domain.calorielevel.domain.repository;

import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CalorieLevelRepository extends CrudRepository<CalorieLevel, Long> {
    List<CalorieLevel> findAllBy();

    Optional<CalorieLevel> findByLevel(Integer level);
}
