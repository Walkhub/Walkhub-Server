package com.walkhub.walkhub.domain.teacher.facade;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class TeacherFacade {

    public ClassResponse buildClassResponse(User teacher) {
        Section section = teacher.hasSection() ? teacher.getSection() : Section.builder().build();

        return ClassResponse.builder()
                .section(ClassResponse.SectionResponse.builder()
                        .sectionId(section.getId())
                        .grade(section.getGrade())
                        .classNum(section.getClassNum())
                        .build())
                .teacher(ClassResponse.TeacherResponse.builder()
                        .userId(teacher.getId())
                        .name(teacher.getName())
                        .profileImageUrl(teacher.getProfileImageUrl())
                        .build())
                .build();
    }

}
