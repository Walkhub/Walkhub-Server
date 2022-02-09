package com.walkhub.walkhub.domain.teacher.service;

import com.walkhub.walkhub.domain.teacher.presentation.dto.response.CodeResponse;
import com.walkhub.walkhub.domain.user.domain.Group;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.domain.user.facade.GroupFacade;
import com.walkhub.walkhub.domain.user.facade.UserFacade;
import com.walkhub.walkhub.global.exception.InvalidRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryStudentCodeService {

    private final UserFacade userFacade;
    private final GroupFacade groupFacade;

    public CodeResponse execute() {
        User user = userFacade.getCurrentUser();
        Group group = groupFacade.getGroup(user.getGroup().getId());

        if (group == null) {
            throw InvalidRoleException.EXCEPTION;
        }

        return new CodeResponse(group.getClassCode());
    }
}
