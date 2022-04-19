package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.MySchoolResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryMySchoolRankService {

    private final UserFacade userFacade;

    public MySchoolResponse execute() {
        User user = userFacade.getCurrentUser();

        return MySchoolResponse.builder()
                .schoolId(user.getSchool().getId())
                .name(user.getSchool().getName())
                .logoImageUrl(user.getSchool().getLogoImageUrl())
                .grade(user.getSection().getGrade())
                .classNum(user.getSection().getClassNum())
                .build();
    }
}
