package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.rank.presentation.dto.response.UserListResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.request.UserSearchRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserSearchForTeacherService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public QueryUserListResponse execute(UserSearchRequest request) {
        return QueryUserListResponse.builder()
                .userList(userRepository.searchUser(request.getScope(), request.getSort(), request.getGrade(), request.getClassNum(), userFacade.getCurrentUser(), request.getName())
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
