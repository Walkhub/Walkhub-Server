package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.QueryUserListResponse;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import com.walkhub.walkhub.domain.teacher.vo.UserListInfoVO;
import com.walkhub.walkhub.domain.user.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserListInfoVO> queryUserList(Integer page, AuthorityScope scope, SortStandard sort, Integer grade, Integer classNum, User currentUser);
}
