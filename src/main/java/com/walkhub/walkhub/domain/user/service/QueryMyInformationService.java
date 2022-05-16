package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.school.domain.School;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryMyInformationResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryMyInformationService {

    private final UserFacade userFacade;

    public QueryMyInformationResponse execute() {
        User user = userFacade.getCurrentUser();
        Section section = user.hasSection() ? user.getSection() : Section.builder().build();
        School school = user.getSchool() != null ? user.getSchool() : School.builder().build();

        return QueryMyInformationResponse.builder()
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .schoolName(school.getName())
                .grade(section.getGrade())
                .classNum(section.getClassNum())
                .build();
    }

}
