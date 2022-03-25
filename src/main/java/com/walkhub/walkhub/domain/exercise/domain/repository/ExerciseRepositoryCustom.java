package com.walkhub.walkhub.domain.exercise.domain.repository;

import com.walkhub.walkhub.domain.exercise.presentation.dto.request.QueryExerciseHistoryRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseHistoryResponse.ExerciseHistory;

import java.util.List;

public interface ExerciseRepositoryCustom {
    List<ExerciseHistory> queryExerciseHistoryList(Long userId, QueryExerciseHistoryRequest request);
}
