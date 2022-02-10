package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.SectionRepository;
import com.walkhub.walkhub.domain.user.facade.SectionFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteClassService {

    private final UserFacade userFacade;
    private final SectionFacade sectionFacade;
    private final SectionRepository sectionRepository;

    @Transactional
    public void execute(Long groupId) {
        Section section = sectionFacade.getSectionById(groupId);

        User user = userFacade.getCurrentUser();

        if (user.getAuthority() == Authority.TEACHER && !user.getSection().equals(section)) {
            throw InvalidRoleException.EXCEPTION;
        }

        sectionRepository.delete(section);
    }

}
