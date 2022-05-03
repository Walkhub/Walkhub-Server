package com.walkhub.walkhub.domain.user.domain.repository;

import com.walkhub.walkhub.domain.excel.presentation.dto.request.UserInfoExcelRequest;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.UserInfoExcelResponse.UserInfoVo;
import com.walkhub.walkhub.domain.teacher.type.AuthorityScope;
import com.walkhub.walkhub.domain.teacher.type.SortStandard;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.vo.UserDetailsVO;
import com.walkhub.walkhub.domain.user.domain.repository.vo.UserListInfoVO;

import java.time.LocalDate;
import java.util.List;

public interface UserRepositoryCustom {
    List<UserListInfoVO> queryUserList(Integer page, AuthorityScope scope, SortStandard sort, Integer grade,
                                       Integer classNum, User currentUser);

    List<UserListInfoVO> searchUser(AuthorityScope scope, SortStandard sort, Integer grade, Integer classNum,
                                    User currentUser, String name);

    UserDetailsVO queryUserDetails(Long userId, LocalDate startAt, LocalDate endAt);

    List<UserInfoVo> getPrintExcelVoList(UserInfoExcelRequest request, Long schoolId);
}
