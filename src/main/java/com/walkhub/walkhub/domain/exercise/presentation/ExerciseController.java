package com.walkhub.walkhub.domain.exercise.presentation;

import com.walkhub.walkhub.domain.exercise.presentation.dto.request.*;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.*;
import com.walkhub.walkhub.domain.exercise.service.CreateExerciseService;
import com.walkhub.walkhub.domain.exercise.service.FinishExerciseService;
import com.walkhub.walkhub.domain.exercise.service.QueryExerciseAnalysisService;
import com.walkhub.walkhub.domain.exercise.service.QueryExerciseDetailsService;
import com.walkhub.walkhub.domain.exercise.service.QueryExerciseListService;
import com.walkhub.walkhub.domain.exercise.service.QueryExercisingUserListService;
import com.walkhub.walkhub.domain.exercise.service.SaveLocationService;
import com.walkhub.walkhub.domain.exercise.service.SaveOrUpdateExerciseAnalysisService;
import com.walkhub.walkhub.domain.exercise.service.QueryExerciseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    private final QueryExerciseHistoryService queryExerciseHistoryService;

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
    public ExerciseListResponse queryExerciseList(@RequestParam int page) {
        return queryExerciseListService.execute(page);
    }

    @GetMapping("{exercise-id}")
    public QueryExerciseDetailsResponse queryExerciseDetails(@PathVariable("exercise-id") Long exerciseId) {
        return queryExerciseDetailsService.execute(exerciseId);
    }

    @GetMapping("/users/lists")
    public QueryExercisingUserListResponse queryExercisingUserList() {
        return queryExercisingUserListService.execute();
    }

    @GetMapping("/{user-id}")
    public QueryExerciseHistoryResponse queryExerciseHistory(@PathVariable("user-id") Long userId, QueryExerciseHistoryRequest request) {
        return queryExerciseHistoryService.execute(userId, request);
    }

}
