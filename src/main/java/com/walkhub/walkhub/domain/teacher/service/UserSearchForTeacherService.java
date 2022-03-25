package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.UserSearchRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.teacher.vo.UserListInfoVO;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UserSearchForTeacherService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public QueryUserListResponse execute(UserSearchRequest request) {
        return QueryUserListResponse.builder()
                .userList(userRepository.searchUser(request.getScope(), request.getSort(), request.getGrade(),
                        request.getClassNum(), userFacade.getCurrentUser(), request.getName())
                        .stream().map(this::buildUserListResponse)
                        .collect(Collectors.toList())
                )
                .build();
    }

    private QueryUserListResponse.UserListInfo buildUserListResponse(UserListInfoVO users) {
        return QueryUserListResponse.UserListInfo.builder()
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
                .build();
    }
}
