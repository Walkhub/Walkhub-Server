package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.QueryExerciseHistoryRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseHistoryResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryExerciseHistoryService {

    private final ExerciseRepository exerciseRepository;

    public QueryExerciseHistoryResponse execute(Long userId, QueryExerciseHistoryRequest request) {
        return new QueryExerciseHistoryResponse(exerciseRepository.queryExerciseHistoryList(userId, request));
    }

}
