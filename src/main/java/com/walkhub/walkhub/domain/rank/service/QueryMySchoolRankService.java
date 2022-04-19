package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.MySchoolResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
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
        Section section = user.hasSection() ? user.getSection() : Section.builder().build();

        return MySchoolResponse.builder()
                .schoolId(user.getSchool().getId())
                .name(user.getSchool().getName())
                .logoImageUrl(user.getSchool().getLogoImageUrl())
                .grade(section.getGrade())
                .classNum(section.getClassNum())
                .build();
    }
}
