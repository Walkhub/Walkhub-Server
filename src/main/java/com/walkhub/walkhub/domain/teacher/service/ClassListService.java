package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.school.domain.School;
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
        User teacher = userFacade.getCurrentUser();
        School school = teacher.getSchool();

        List<ClassResponse> classList = school.getSections()
                .stream()
                .map(this::buildClassList)
                .collect(Collectors.toList());

        return new ClassListResponse(classList);
    }

    private ClassResponse buildClassList(Section section) {
        User user = userRepository.findBySectionAndAuthority(section, Authority.TEACHER);
        Integer userCount = userRepository.findAllBySectionAndAuthority(section, Authority.USER).size();
        return ClassResponse.builder()
                .userCount(userCount)
                .section(SectionResponse.builder()
                        .sectionId(section.getId())
                        .grade(section.getGrade())
                        .classNum(section.getClassNum())
                        .build())
                .teacher(TeacherResponse.builder()
                        .userId(user.getId())
                        .name(user.getName())
                        .profileImageUrl(user.getProfileImageUrl())
                        .build())
                .build();
    }
}



