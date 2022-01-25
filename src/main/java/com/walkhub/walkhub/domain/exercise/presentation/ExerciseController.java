package com.walkhub.walkhub.domain.exercise.presentation;

import com.walkhub.walkhub.domain.exercise.presentation.dto.request.CreateExerciseRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.FinishExerciseRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.CreateExerciseResponse;
import com.walkhub.walkhub.domain.exercise.service.CreateExerciseService;
import com.walkhub.walkhub.domain.exercise.service.FinishExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/exercises")
@RestController
public class ExerciseController {

	private final CreateExerciseService createExerciseService;
	private final FinishExerciseService finishExerciseService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreateExerciseResponse createExercise(@RequestBody CreateExerciseRequest request) {
		return createExerciseService.execute(request);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PatchMapping("/{exercise-id}")
	public void finishExercise(@PathVariable(name = "exercise-id") Long exerciseId,
							   @RequestBody @Valid FinishExerciseRequest request) {
		finishExerciseService.execute(exerciseId, request);
	}

}
