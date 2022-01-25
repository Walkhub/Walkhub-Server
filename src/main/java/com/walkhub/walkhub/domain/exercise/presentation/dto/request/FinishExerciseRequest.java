package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class FinishExerciseRequest {

    @NotNull(message = "walk_count는 null, 공백을 허용하지 않습니다.")
    private Integer walkCount;

    @NotNull(message = "distance는 null, 공백을 허용하지 않습니다.")
    private Integer distance;

    @NotNull(message = "calorie는 null, 공백을 허용하지 않습니다.")
    private Integer calorie;

    @NotEmpty(message = "image_url은 공백을 허용하지 않습니다.")
    private List<String> imageUrl;

}
