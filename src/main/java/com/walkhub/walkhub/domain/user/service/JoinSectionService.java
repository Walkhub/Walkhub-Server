package com.walkhub.walkhub.domain.user.service;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.AlreadyJoinedException;
import com.walkhub.walkhub.domain.user.exception.AlreadyNumberException;
import com.walkhub.walkhub.domain.user.exception.SectionNotFoundException;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.domain.user.presentation.dto.request.JoinSectionRequest;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class JoinSectionService {

    private final UserFacade userFacade;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    @Transactional
    public void execute(JoinSectionRequest request) {
        User user = userFacade.getCurrentUser();

        if (user.hasSection()) {
            throw AlreadyJoinedException.EXCEPTION;
        }

        Section section = sectionRepository.findByClassCodeAndSchool(request.getClassCode(), user.getSchool())
                .orElseThrow(() -> SectionNotFoundException.EXCEPTION);

        if (userRepository.findBySectionAndNumber(section, request.getNumber()).isPresent()) {
            throw AlreadyNumberException.EXCEPTION;
        }

        user.setSection(section);
        user.setNumber(request.getNumber());
    }

}
