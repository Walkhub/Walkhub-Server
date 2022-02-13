package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.Challenge;

import java.time.LocalDate;
import java.util.List;

public interface CustomExerciseRepository {
    List<LocalDate> querySuccessDateList(Long userId, Challenge challenge);
}
