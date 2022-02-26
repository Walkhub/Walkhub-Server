package com.walkhub.walkhub.domain.calorielevel.domain.repository;

import com.walkhub.walkhub.domain.calorielevel.domain.CalorieLevel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalorieLevelRepository extends CrudRepository<CalorieLevel, Long> {
    List<CalorieLevel> findAllBy();

    Optional<CalorieLevel> findByLevel(Integer level);
}
