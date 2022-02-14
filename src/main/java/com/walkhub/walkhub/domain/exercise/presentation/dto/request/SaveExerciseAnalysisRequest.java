package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SaveExerciseAnalysisRequest {

    @NotNull(message = "date는 Null을 허용하지 않습니다.")
    private LocalDate date;

    @PositiveOrZero(message = "distance는 음수를 허용하지 않습니다.")
    @NotNull(message = "distance는 Null을 허용하지 않습니다.")
    private Integer distance;

    @PositiveOrZero(message = "walk_count는 음수를 허용하지 않습니다.")
    @NotNull(message = "walk_count는 Null을 허용하지 않습니다.")
    private Integer walkCount;

    @PositiveOrZero(message = "calorie는 음수를 허용하지 않습니다.")
    @NotNull(message = "calorie는 Null을 허용하지 않습니다.")
    private Double calorie;

}
