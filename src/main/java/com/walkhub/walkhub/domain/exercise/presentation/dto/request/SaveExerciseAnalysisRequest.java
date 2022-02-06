package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SaveExerciseAnalysisRequest {

    private LocalDate date;

    private Integer distance;

    private Integer walkCount;

    private Integer calorie;

}
