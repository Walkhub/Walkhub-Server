package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateExerciseRequest {

    private Integer goal;

    @NotNull(message = "goal_type은 null, 공백을 허용하지 않습니다.")
    private GoalType goalType;

}
