package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.challenge.domain.ChallengeStatus;

import java.time.LocalDate;
import java.util.List;

public interface CustomExerciseRepository {
    List<LocalDate> querySuccessDateList(ChallengeStatus challengeStatus);
}
