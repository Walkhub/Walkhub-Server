package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import com.walkhub.walkhub.domain.exercise.domain.type.GoalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateExerciseRequest {

	private Integer goal;

	private GoalType goalType;

}
