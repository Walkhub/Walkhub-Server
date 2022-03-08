package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.QueryUserListRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.WalkhubService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@WalkhubService
public class QueryUserListService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public QueryUserListResponse execute(QueryUserListRequest request) {
        return QueryUserListResponse.builder()
                .userList(userRepository.queryUserList(request.getPage(), request.getScope(), request.getSort(), request.getGrade(), request.getClassNum(), userFacade.getCurrentUser())
                        .stream().map(users -> QueryUserListResponse.UserListInfo.builder()
                                .userId(users.getUserId())
                                .name(users.getName())
                                .profileImageUrl(users.getProfileImageUrl())
                                .grade(users.getGrade())
                                .classNum(users.getClassNum())
                                .number(users.getNumber())
                                .averageWalkCount(users.getAverageWalkCount())
                                .totalWalkCount(users.getTotalWalkCount())
                                .averageDistance(users.getAverageDistance())
                                .totalDistance(users.getTotalDistance())
                                .isTeacher(users.getIsTeacher())
                                .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
