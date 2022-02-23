package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassListResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassListResponse.ClassResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassListResponse.SectionResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassListResponse.TeacherResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClassListService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ClassListResponse execute() {
        User user = userFacade.getCurrentUser();
        List<User> teachers = userRepository.findAllBySchoolAndAuthority(user.getSchool(), Authority.TEACHER);

        List<ClassResponse> classList = teachers.stream()
                .map(this::buildClassResponse)
                .collect(Collectors.toList());

        return new ClassListResponse(school.getAuthCode(), classList);
    }

    private ClassResponse buildClassResponse(User teacher) {
        Section section;

        if (teacher.hasSection()) {
            section = teacher.getSection();
        } else {
            section = Section.builder().build();
        }

        return ClassResponse.builder()
                .section(SectionResponse.builder()
                        .sectionId(section.getId())
                        .grade(section.getGrade())
                        .classNum(section.getClassNum())
                        .build())
                .teacher(TeacherResponse.builder()
                        .userId(teacher.getId())
                        .name(teacher.getName())
                        .profileImageUrl(teacher.getProfileImageUrl())
                        .build())
                .build();

    }
}



