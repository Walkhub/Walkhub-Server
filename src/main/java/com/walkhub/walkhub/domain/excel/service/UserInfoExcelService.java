package com.walkhub.walkhub.domain.excel.service;

import com.walkhub.walkhub.domain.excel.domain.vo.UserInfoExcelVo;
import com.walkhub.walkhub.domain.excel.presentation.dto.request.UserInfoExcelRequest;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.UserInfoExcelResponse;
import com.walkhub.walkhub.domain.excel.presentation.dto.response.UserInfoExcelResponse.UserInfoResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UserInfoExcelService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public UserInfoExcelResponse execute(UserInfoExcelRequest request) {
        User user = userFacade.getCurrentUser();

        List<UserInfoResponse> userInfoList =
                userRepository.getUserInfoExcelList(request, user.getUserSchoolId())
                        .stream()
                        .map(this::buildUserInfoExcel)
                        .collect(Collectors.toList());

        return new UserInfoExcelResponse(userInfoList);
    }

    private UserInfoResponse buildUserInfoExcel(UserInfoExcelVo vo) {
        return UserInfoResponse.builder()
                .name(vo.getName())
                .grade(vo.getGrade())
                .classNum(vo.getClassNum())
                .number(vo.getNumber())
                .allWalkCount(vo.getAllWalkCount())
                .averageWalkCount(vo.getAverageWalkCount())
                .allDistance(vo.getAllDistance())
                .averageDistance(vo.getAverageDistance())
                .authority(vo.getAuthority())
                .schoolName(vo.getSchoolName())
                .build();
    }
}
