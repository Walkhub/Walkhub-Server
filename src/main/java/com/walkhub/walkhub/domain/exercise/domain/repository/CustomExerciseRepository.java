package com.walkhub.walkhub.domain.exercise.domain.repository;

import java.time.LocalDate;
import java.util.List;

public interface CustomExerciseRepository {
    List<LocalDate> querySuccessDateList(Long userId, Long challengeId);
}
