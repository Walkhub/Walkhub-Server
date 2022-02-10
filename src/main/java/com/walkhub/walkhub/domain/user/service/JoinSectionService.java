package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.school.exception.AgencyCodeNotMatchException;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.exception.ClassCodeNotMatchException;
import com.walkhub.walkhub.domain.user.facade.SectionFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.JoinSectionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JoinSectionService {

    private final UserFacade userFacade;
    private final SectionFacade sectionFacade;

    @Transactional
    public void execute(Long sectionId, JoinSectionRequest request) {
        User user = userFacade.getCurrentUser();

        Section section = sectionFacade.getSectionById(sectionId);

        if (!section.getClassCode().equals(request.getClassCode())) {
            throw ClassCodeNotMatchException.EXCEPTION;
        }

        if (!user.getSchool().equals(section.getSchool())) {
            throw AgencyCodeNotMatchException.EXCEPTION;
        }

        user.setSection(section);
        user.setNumber(request.getNumber());
    }

}
