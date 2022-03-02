package com.walkhub.walkhub.domain.exercise.presentation;

import com.walkhub.walkhub.domain.exercise.presentation.dto.request.CreateExerciseRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.FinishExerciseRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.SaveExerciseAnalysisRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.SaveLocationRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.*;
import com.walkhub.walkhub.domain.exercise.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/exercises")
@RestController
public class ExerciseController {

    private final CreateExerciseService createExerciseService;
    private final FinishExerciseService finishExerciseService;
    private final SaveLocationService saveLocationService;
    private final QueryExerciseListService queryExerciseListService;
    private final SaveOrUpdateExerciseAnalysisService saveOrUpdateExerciseAnalysisService;
    private final QueryExerciseAnalysisService queryExerciseAnalysisService;
    private final QueryExerciseDetailsService queryExerciseDetailsService;
    private final QueryExercisingUserListService queryExercisingUserListService;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/locations/{exercise-id}")
    public void saveLocation(@PathVariable(name = "exercise-id") Long exerciseId,
                             @RequestBody @Valid SaveLocationRequest request) {
        saveLocationService.execute(exerciseId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void saveOrUpdateTodayExercise(@RequestBody @Valid SaveExerciseAnalysisRequest request) {
        saveOrUpdateExerciseAnalysisService.execute(request);
    }

    @GetMapping("/analysis")
    public QueryExerciseAnalysisResponse queryExerciseAnalysis() {
        return queryExerciseAnalysisService.execute();
    }

    @GetMapping("/lists")
    public ExerciseListResponse queryExerciseList() {
        return queryExerciseListService.execute();
    }

    @GetMapping("{exercise-id}")
    public QueryExerciseDetailsResponse queryExerciseDetails(@PathVariable("exercise-id") Long exerciseId) {
        return queryExerciseDetailsService.execute(exerciseId);
    }

    @GetMapping("/users/lists")
    public QueryExercisingUserListResponse queryExercisingUserList() {
        return queryExercisingUserListService.execute();
    }
}
