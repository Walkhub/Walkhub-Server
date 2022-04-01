package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.school.domain.repository.SchoolRepository;
import com.walkhub.walkhub.domain.teacher.facade.TeacherFacade;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ManageTeachersResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.domain.repository.UserRepository;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import com.walkhub.walkhub.global.enums.Authority;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class ManageTeacherService {
    private final TeacherFacade teacherFacade;
    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    public ManageTeachersResponse execute() {
        User root = userFacade.getCurrentUser();

        List<ClassResponse> teacherList = userRepository.findAllBySchoolAndAuthority(root.getSchool(), Authority.TEACHER)
                .stream()
                .map(teacherFacade::buildClassResponse)
                .collect(Collectors.toList());

        return ManageTeachersResponse.builder()
                .authCode(root.getSchool().getAuthCode())
                .teacherList(teacherList)
                .build();
    }
}
