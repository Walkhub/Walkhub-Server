package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryUserListService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public QueryUserListResponse execute(Integer page, AuthorityScope scope, SortStandard sort, Integer grade, Integer classNum) {
        return QueryUserListResponse.builder()
                .userList(userRepository.queryUserList(page, scope, sort, grade, classNum, userFacade.getCurrentUser()))
                .build();
    }
}
