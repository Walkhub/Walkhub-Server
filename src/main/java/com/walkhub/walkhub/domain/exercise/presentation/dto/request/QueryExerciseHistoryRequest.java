package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryExerciseHistoryRequest {

    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Integer page;

}
