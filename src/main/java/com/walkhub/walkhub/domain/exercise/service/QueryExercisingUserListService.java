package com.walkhub.walkhub.domain.exercise.service;

import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExercisingUserListResponse;
import com.walkhub.walkhub.domain.exercise.presentation.dto.response.QueryExercisingUserListResponse.ExercisingUserListResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@WalkhubService
public class QueryExercisingUserListService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public QueryExercisingUserListResponse execute() {
        User user = userFacade.getCurrentUser();

        List<ExercisingUserListResponse> exercisingList =
                userRepository.findAllBySchoolAndIsMeasuringTrue(user.getSchool())
                        .stream()
                        .map(this::buildExercisingUserList)
                        .collect(Collectors.toList());

        return new QueryExercisingUserListResponse(exercisingList);
    }

    private ExercisingUserListResponse buildExercisingUserList(User user) {
        return ExercisingUserListResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
