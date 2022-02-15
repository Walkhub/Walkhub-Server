package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.exception.AlreadyJoinedException;
import com.walkhub.walkhub.domain.user.exception.SectionNotFoundException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.JoinSectionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JoinSectionService {

    private final UserFacade userFacade;
    private final SectionRepository sectionRepository;

    @Transactional
    public void execute(JoinSectionRequest request) {
        User user = userFacade.getCurrentUser();

        if (user.hasSection()) {
            throw AlreadyJoinedException.EXCEPTION;
        }

        Section section = sectionRepository.findByClassCodeAndSchool(request.getClassCode(), user.getSchool())
                .orElseThrow(() -> SectionNotFoundException.EXCEPTION);

        user.setSection(section);
        user.setNumber(request.getNumber());
    }

}
