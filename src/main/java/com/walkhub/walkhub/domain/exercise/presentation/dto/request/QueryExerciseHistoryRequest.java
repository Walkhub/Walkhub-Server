package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryExerciseHistoryRequest {

    @NotNull(message = "start_at은 Null일 수 없습니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startAt;

    @NotNull(message = "end_at은 Null일 수 없습니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endAt;

    @NotNull(message = "page는 Null일 수 없습니다.")
    private Integer page;

}
