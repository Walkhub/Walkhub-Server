package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.SectionFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class DeleteClassService {

    private final UserFacade userFacade;
    private final SectionFacade sectionFacade;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    @Transactional
    public void execute(Long sectionId) {
        Section section = sectionFacade.getSectionById(sectionId);

        User user = userFacade.getCurrentUser();

        if (user.getAuthority() != Authority.TEACHER || !user.getSection().equals(section)) {
            throw InvalidRoleException.EXCEPTION;
        }

        userRepository.setUserSectionNull(section);
        sectionRepository.delete(section);
    }

}
