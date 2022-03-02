package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateExerciseRequest {

	private Integer goal;

	@NotNull(message = "goal_type은 null, 공백을 허용x")
	private GoalType goalType;

}
