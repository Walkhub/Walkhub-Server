package com.walkhub.walkhub.domain.exercise.presentation;

import com.walkhub.walkhub.domain.exercise.presentation.dto.request.CreateExerciseRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.CreateExerciseResponse;
import com.walkhub.walkhub.domain.exercise.service.CreateExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/exercises")
@RestController
public class ExerciseController {

	private final CreateExerciseService createExerciseService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateExerciseResponse createExercise(@RequestBody CreateExerciseRequest request) {
		return createExerciseService.execute(request);
	}
}
