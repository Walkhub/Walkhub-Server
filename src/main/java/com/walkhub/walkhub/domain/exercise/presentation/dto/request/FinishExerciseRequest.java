package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
public class FinishExerciseRequest {

    @NotNull(message = "walk_count는 null, 공백을 허용하지 않습니다.")
    private Integer walkCount;

    @NotNull(message = "distance는 null, 공백을 허용하지 않습니다.")
    private Integer distance;

    @PositiveOrZero(message = "calorie는 음수일 수 없습니다.")
    @NotNull(message = "calorie는 null, 공백을 허용하지 않습니다.")
    private Double calorie;

    private String imageUrl;

}