package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.DetailsClassResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.DetailsClassResponse.TeacherResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.exception.UserNotFoundException;
import com.walkhub.walkhub.domain.user.facade.SectionFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class DetailsClassService {

    private final UserRepository userRepository;
    private final SectionFacade sectionFacade;

    public DetailsClassResponse execute(Long sectionId) {
        Section section = sectionFacade.getSectionById(sectionId);

        Integer userCount = userRepository.countBySectionAndAuthority(section, Authority.USER);

        User teacher = section.getUsers()
                .stream()
                .filter(user -> user.getAuthority() == Authority.TEACHER)
                .findFirst()
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        TeacherResponse teacherResponse = TeacherResponse.builder()
                .userId(teacher.getId())
                .name(teacher.getName())
                .profileImageUrl(teacher.getProfileImageUrl())
                .build();

        return DetailsClassResponse.builder()
                .userCount(userCount)
                .grade(section.getGrade())
                .classNum(section.getClassNum())
                .classCode(section.getClassCode())
                .teacher(teacherResponse)
                .build();
    }
}
