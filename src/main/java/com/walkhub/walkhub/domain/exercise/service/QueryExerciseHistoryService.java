package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.QueryExerciseHistoryRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseHistoryResponse;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExerciseHistoryResponse.ExerciseHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryExerciseHistoryService {

    private final ExerciseRepository exerciseRepository;

    public QueryExerciseHistoryResponse execute(Long userId, QueryExerciseHistoryRequest request) {

        ZonedDateTime startAt = request.getStartAt().atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endAt = request.getEndAt().atStartOfDay(ZoneId.systemDefault());
        Integer page = request.getPage();


        return new QueryExerciseHistoryResponse(
                exerciseRepository.queryExerciseHistoryList(userId, startAt, endAt, page)
                        .stream()
                        .map(ExerciseHistory::new)
                        .collect(Collectors.toList())
        );
    }

}
