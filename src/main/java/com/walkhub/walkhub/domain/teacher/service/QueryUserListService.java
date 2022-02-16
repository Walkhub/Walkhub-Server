package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.request.QueryUserListRequest;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
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
                                .isTeacher(users.getIsTeacher())
                                .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
