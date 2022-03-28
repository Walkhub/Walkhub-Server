package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.facade.TeacherFacade;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassListResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse.SectionResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse.TeacherResponse;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse;
import com.walkhub.walkhub.domain.user.domain.Section;
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
public class ClassListService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final TeacherFacade teacherFacade;

    public ClassListResponse execute() {
        User user = userFacade.getCurrentUser();
        List<User> teachers = userRepository.findAllBySchoolAndAuthority(user.getSchool(), Authority.TEACHER);

        List<ClassResponse> classList = teachers.stream()
                .map(teacherFacade::buildClassResponse)
                .collect(Collectors.toList());

        return new ClassListResponse(user.getSchool().getAuthCode(), classList);
    }
}



