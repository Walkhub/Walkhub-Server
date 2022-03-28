package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.response.QueryMyInformationResponse;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryMyInformationService {
    private final UserFacade userFacade;
    private final SectionRepository sectionRepository;

    public QueryMyInformationResponse queryMyInformation() {
        User user = userFacade.getCurrentUser();
        Section section = sectionRepository.getById(user.getSection().getId());
        return QueryMyInformationResponse.builder()
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .schoolName(section.getSchool().getName())
                .grade(section.getGrade())
                .classNum(section.getClassNum())
                .build();
    }

}
