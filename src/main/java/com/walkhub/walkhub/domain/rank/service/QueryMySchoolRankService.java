package com.walkhub.walkhub.domain.rank.service;

import com.walkhub.walkhub.domain.rank.presentation.dto.response.SchoolRankResponse.MySchoolResponse;
import com.walkhub.walkhub.domain.school.domain.School;
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
        School school = user.getSchool();
        Section section = user.hasSection() ? user.getSection() : Section.builder().build();

        return MySchoolResponse.builder()
                .schoolId(school.getId())
                .name(school.getName())
                .logoImageUrl(school.getLogoImageUrl())
                .grade(section.getGrade())
                .classNum(section.getClassNum())
                .build();
    }
}
