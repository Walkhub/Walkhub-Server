package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.domain.Exercise;
import com.walkhub.walkhub.domain.exercise.domain.repository.ExerciseRepository;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExercisingUserListResponse;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExercisingUserListResponse.ExercisingUserListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryExercisingUserListService {

    private final UserFacade userFacade;
    private final ExerciseRepository exerciseRepository;

    @Transactional(readOnly = true)
    public QueryExercisingUserListResponse execute() {
        User user = userFacade.getCurrentUser();

        List<ExercisingUserListResponse> exercisingList =
                exerciseRepository.findAllByUserSectionAndUserIsMeasuring(user.getSection())
                        .stream()
                        .map(this::buildExercisingUserList)
                        .collect(Collectors.toList());

        return new QueryExercisingUserListResponse(exercisingList);

    }

    private ExercisingUserListResponse buildExercisingUserList(Exercise exercise) {
        return ExercisingUserListResponse.builder()
                .userId(exercise.getUser().getId())
                .name(exercise.getUser().getName())
                .profileImageUrl(exercise.getUser().getProfileImageUrl())
                .build();
    }
}
