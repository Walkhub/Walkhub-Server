package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.exception.AlreadyExercisingException;
import com.walkhub.walkhub.domain.exercise.presentation.dto.request.CreateExerciseRequest;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.CreateExerciseResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@WalkhubService
public class CreateExerciseService {

    private final UserFacade userFacade;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public CreateExerciseResponse execute(CreateExerciseRequest request) {

        User user = userFacade.getCurrentUser();

        if (exerciseRepository.findByIsExercisingTrueAndUser(user).isPresent()) {
            throw AlreadyExercisingException.EXCEPTION;
        }

        Exercise exercise = exerciseRepository.save(
                Exercise.builder()
                        .user(user)
                        .goalType(request.getGoalType())
                        .goal(request.getGoal())
                        .build()
        );

        user.updateIsMeasuring(true);

        return new CreateExerciseResponse(exercise.getId());
    }
}
