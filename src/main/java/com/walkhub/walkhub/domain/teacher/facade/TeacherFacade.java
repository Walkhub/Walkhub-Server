package com.walkhub.walkhub.domain.teacher.facade;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse.SectionResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse.TeacherResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeacherFacade {

    private final UserRepository userRepository;

    public ClassResponse buildClassResponse(User teacher) {
        Section section = teacher.hasSection() ? teacher.getSection() : Section.builder().build();
        Integer userCount = userRepository.countBySectionAndAuthority(section, Authority.USER);

        return ClassResponse.builder()
                .section(SectionResponse.builder()
                        .sectionId(section.getId())
                        .grade(section.getGrade())
                        .classNum(section.getClassNum())
                        .userCount(userCount)
                        .build())
                .teacher(TeacherResponse.builder()
                        .userId(teacher.getId())
                        .name(teacher.getName())
                        .profileImageUrl(teacher.getProfileImageUrl())
                        .build())
                .build();
    }

}
