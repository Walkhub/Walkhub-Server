package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.user.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<QueryUserListResponse.UserListInfo> queryUserList(Integer page, String scope, String sort, Integer grade, Integer classNum, User currentUser);
}
