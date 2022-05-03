package com.walkhub.walkhub.domain.excel.service;

import com.walkhub.walkhub.domain.excel.presentation.dto.request.UserInfoExcelRequest;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.UserInfoExcelResponse;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.UserInfoExcelResponse.UserInfoVo;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UserInfoExcelService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public UserInfoExcelResponse execute(UserInfoExcelRequest request) {
        User user = userFacade.getCurrentUser();

        List<UserInfoVo> userInfoVoList = userRepository.getPrintExcelVoList(request, user.getUserSchoolId());

        return new UserInfoExcelResponse(userInfoVoList);
    }
}
