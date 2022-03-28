package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.facade.TeacherFacade;
import com.walkhub.walkhub.domain.teacher.presentation.dto.response.ClassResponse;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class QueryMyClassService {

    private final UserFacade userFacade;
    private final TeacherFacade teacherFacade;

    public ClassResponse execute() {
        User user = userFacade.getCurrentUser();

        return teacherFacade.buildClassResponse(user);
    }

}
